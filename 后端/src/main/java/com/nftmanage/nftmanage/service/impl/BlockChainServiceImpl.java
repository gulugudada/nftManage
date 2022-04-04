package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.service.BlockChainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.admin.methods.response.PersonalUnlockAccount;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.DefaultBlockParameterName;
import org.web3j.protocol.core.Request;
import org.web3j.protocol.core.methods.request.Transaction;
import org.web3j.protocol.core.methods.response.*;
import org.web3j.protocol.geth.Geth;
import org.web3j.tx.Transfer;
import org.web3j.utils.Convert;

import java.io.IOException;
import java.math.BigInteger;
import java.util.List;

@Service
public class BlockChainServiceImpl implements BlockChainService {

    @Autowired
    private Web3j web3j;

    @Autowired
    private Admin admin;

    @Autowired
    private Geth geth;

    // 获得区块高度
    public long getHeight(){
        try {
            EthBlockNumber blockNumber = web3j.ethBlockNumber().send();
            return blockNumber.getBlockNumber().longValue();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public List<String> getAccounts() {
        try {
            EthAccounts accounts = web3j.ethAccounts().send();
            return accounts.getAccounts();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获得账号的账户余额
     *
     * @param address 地址
     * @return
     */
    @Override
    public String getBlanceOf(String address) {
        EthGetBalance balance = null;
        try {
            // DefaultBlockParameter.valueOf("latest") 可以理解为不同区块数量时链不同的状态  latest就是指最新区块挖出来时链的状态
            balance = web3j.ethGetBalance(address, DefaultBlockParameter.valueOf("latest")).send();
            //格式转化 wei-ether
            return Convert.fromWei(balance.getBalance().toString(), Convert.Unit.ETHER).toPlainString().concat("ether");
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
     * @param duration 解锁有效时间，单位秒
     * @return
     * @throws IOException
     */
    public boolean unlockAccount(String address, String password, BigInteger duration) throws IOException {
        Request<?, PersonalUnlockAccount> request = admin.personalUnlockAccount(address, password, duration);
        PersonalUnlockAccount account = request.send();
        return account.accountUnlocked();
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
     * 发送交易
     *
     * @param from      发送者地址
     * @param to        接收者地址
     * @param value     交易值
     * @param password  发送者的密码
     * @return
     */
    @Override
    public String sendEtherTransaction(String from,String to,BigInteger value,String password) {
        BigInteger nonce = null;
        try {
            nonce = getNonce(from);
            System.out.println(nonce);
            BigInteger gasPrice = web3j.ethGasPrice().send().getGasPrice();
            System.out.println(gasPrice);
            Transaction transaction = Transaction.createEtherTransaction(from,nonce,gasPrice,Transfer.GAS_LIMIT,to,value);
            System.out.println(transaction.getValue());
            Request<?, EthSendTransaction> request = admin.personalSendTransaction(transaction, password);
            System.out.println(request.toString());
            EthSendTransaction ethSendTransaction = request.send();
            return ethSendTransaction.getTransactionHash();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
