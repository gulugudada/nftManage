package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.service.BlockChainService;
import com.nftmanage.nftmanage.solidity.Faucet;
import com.nftmanage.nftmanage.solidity.NFTMarket;
import com.nftmanage.nftmanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.BooleanResponse;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.geth.Geth;
import org.web3j.tx.RawTransactionManager;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.StaticGasProvider;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Admin admin;

    @Autowired
    private Geth geth;

    // 获得区块高度
    public Result getHeight(){
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            return Result.ok(blockNumber.getBlockNumber().longValue());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    @Override
    public Result getAccounts() {
        try {
            EthAccounts accounts = web3j.ethAccounts().send();
            return Result.ok(accounts.getAccounts());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.error();
    }

    /**
     * 获得账号的账户余额
     *
     * @param address 地址
     * @return
     */
    @Override
    public Result getBlanceOf(String address) {
        EthGetBalance balance = null;
        try {
            // DefaultBlockParameter.valueOf("latest") 可以理解为不同区块数量时链不同的状态  latest就是指最新区块挖出来时链的状态
            balance = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
            //格式转化 wei-ether
            return Result.ok(Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER).toPlainString().concat("ether"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 解锁账户，发送交易前需要对账户进行解锁
     *
     * @param address  地址
     * @param password 密码
     * @return
     * @throws IOException
     */
    public boolean unlockAccount(String address, String password) throws IOException {
        Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password);
        return request.send().accountUnlocked();
    }

    /**
     * 交易发起成功后要锁定账号
     *
     * @param address  账号地址
     * @return
     * @throws IOException
     */
    public boolean lockAccount(String address) throws IOException {
        Request<?, BooleanResponse> request = geth.personalLockAccount(address);
        BooleanResponse response = request.send();
        return response.success();
    }

    /**
     * 指定地址发送交易所需nonce获取
     *
     * @param address 待发送交易地址
     * @return
     * @throws IOException
     */
    public BigInteger getNonce(String address) throws IOException {
        Request<?, EthGetTransactionCount> request = web3j.ethGetTransactionCount(address, DefaultBlockParameterName.LATEST);
        return request.send().getTransactionCount();
    }

    /**
     *   发起以太币交易
     *
     * @param from      发送者地址
     * @param to        接受者地址
     * @param value     交易值
     * @param password  发送者账号密码
     * @return
     */
    @Override
    public Result sendEtherTransaction(String from,String to,BigInteger value,String password) throws IOException {
        if(unlockAccount(from,password)){
            try {
                Transaction transaction = new Transaction(from,null,null,null,to,value,null);
                String transactionHash = web3j.ethSendTransaction(transaction).send().getTransactionHash();
                lockAccount(from);
                return Result.ok(transactionHash);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return Result.error();
    }

    /**
     *
     * @param amount  多少Wei
     * @return
     * @throws IOException
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Override
    public Result withdraw(Credentials credentials,String amount) throws Exception {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        Faucet contract = Faucet.load("0xF700ebBC2B452b1D699e8F9DAB0Cf994BCb4E802",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));

        // 部署合约,获取合约地址
//        LeaveMsg contract = LeaveMsg.deploy(web3,credentials,web3.ethGasPrice().send().getGasPrice()
//                ,Contract.GAS_PRICE).send();
//        System.out.println(contract.getContractAddress());

//        LeaveMsg contract = LeaveMsg.load("0xE3720A6D1dA0b27aCd735aA5Bc121d7AbD55Ff68",web3,credentials,
//                GAS_PRICE,GAS_LIMIT);

        // 异步调用写法
        RemoteFunctionCall<TransactionReceipt> setWord = contract.withdraw(new BigInteger("100"));
        TransactionReceipt transactionReceipt = setWord.sendAsync().get();
        String transactionHash = transactionReceipt.getTransactionHash();
        System.out.println(transactionHash);


//        TransactionReceipt send1 = setWord.send();
//        String blockHash = send1.getBlockHash();
//        System.out.println(blockHash);


//        RemoteFunctionCall<Tuple4<BigInteger, String, String, String>> randomWord = contract.getRandomWord(new BigInteger("7"));
//        Tuple4<BigInteger, String, String, String> send = randomWord.send();
//        System.out.println(send.toString());
        return Result.ok(transactionHash);
    }

    @Override
    public void mintNFT(Credentials credentials,String tokenURI) throws IOException, ExecutionException, InterruptedException {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<TransactionReceipt> setWord = contract.mintNFT(credentials.getAddress(),tokenURI);
        TransactionReceipt transactionReceipt = setWord.sendAsync().get();

        transactionReceipt.getTransactionHash();
    }

    @Override
    public BigInteger totalSupply(Credentials credentials) throws ExecutionException, InterruptedException, IOException {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<BigInteger> setWord = contract.totalSupply();
        BigInteger total = setWord.sendAsync().get();
        return total;
    }

    @Override
    public BigInteger balanceOfOwner(Credentials credentials) throws Exception {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<BigInteger> setWord = contract.balanceOf(credentials.getAddress());
        BigInteger total = setWord.sendAsync().get();
        return total;
    }

    @Override
    public void burnNFT(Credentials credentials,BigInteger tokenId) throws IOException, ExecutionException, InterruptedException {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<TransactionReceipt> setWord = contract.burnNFT(tokenId);
        setWord.sendAsync().get();
    }

    @Override
    public void tranNFT(Credentials credentials,String to,BigInteger tokenId) throws IOException, ExecutionException, InterruptedException {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<TransactionReceipt> setWord = contract.safeTransferFrom(credentials.getAddress(),to,tokenId);
        setWord.sendAsync().get();
    }

    @Override
    public Result tokenURI(Credentials credentials,BigInteger tokenId) throws IOException, ExecutionException, InterruptedException {
        // 加载已经部署在链上的合约
        BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();

        TransactionManager transactionManager = new RawTransactionManager(web3j, credentials, 123);

        NFTMarket contract = NFTMarket.load("0x44F0c18BB444Ce4ff856Ae5938d4c0360e90D81b",web3j,transactionManager,
                new StaticGasProvider(gasPrice,BigInteger.valueOf(3000000L)));
        // 异步调用写法
        RemoteFunctionCall<String> setWord = contract.tokenURI(tokenId);
        String tokenURI = setWord.sendAsync().get();
        return Result.ok(tokenURI);
    }
}
