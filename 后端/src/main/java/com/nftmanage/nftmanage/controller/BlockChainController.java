package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.service.BlockChainService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.web3j.crypto.Credentials;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *区块链接口
 */
@Api(tags="区块链接口类")
@RestController
@CrossOrigin
public class BlockChainController {

    @Autowired
    BlockChainService blockChainService;

    @PostMapping("getHeight")
    public Result getHeight() {
        return blockChainService.getHeight();
    }

    @PostMapping("getAccount")
    public Result getAccount(){
        return blockChainService.getAccounts();
    }

    @PostMapping("getBalanceOf")
    public Result getBalanceOf(@RequestBody Map<String, String> map){
        return blockChainService.getBlanceOf(map.get("address"));
    }

    @PostMapping("sendTransaction")
    public Result sendTransaction(@RequestBody Map<String, String> map) throws IOException {
        return blockChainService.sendEtherTransaction(map.get("from"),map.get("to"),new BigInteger(map.get("value")+"000000000000000000"),map.get("password"));
    }

    @PostMapping("withdraw")
    public Result withdraw(@RequestBody Map<String, String> map) throws Exception {
        return blockChainService.withdraw(Credentials.create(map.get("privateKey")),map.get("amount"));
    }

    @PostMapping("mintNFT")
    public Result mintNFT(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        blockChainService.mintNFT(Credentials.create(map.get("privateKey")),map.get("tokenURI"));
        return Result.ok();
    }

    @PostMapping("totalSupply")
    public Result totalSupply(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        return Result.ok(blockChainService.totalSupply(Credentials.create(map.get("privateKey"))));
    }

    @PostMapping("burnNFT")
    public Result burnNFT(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        blockChainService.burnNFT(Credentials.create(map.get("privateKey")),new BigInteger(map.get("tokenId")));
        return Result.ok();
    }

    @PostMapping("tranNFT")
    public Result tranNFT(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        blockChainService.tranNFT(Credentials.create(map.get("privateKey")),map.get("to"),new BigInteger(map.get("tokenId")));
        return Result.ok();
    }

    @PostMapping("tokenURI")
    public Result tokenURI(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        return blockChainService.tokenURI(Credentials.create(map.get("privateKey")),new BigInteger(map.get("tokenId")));
    }
}
