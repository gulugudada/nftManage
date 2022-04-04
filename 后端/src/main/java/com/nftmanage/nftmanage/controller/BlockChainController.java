package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.service.BlockChainService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.Map;

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
        return Result.ok(blockChainService.getHeight());
    }

    @PostMapping("getAccount")
    public Result getAccount(){
        return Result.ok(blockChainService.getAccounts());
    }

    @PostMapping("getBalanceOf")
    public Result getBalanceOf(@RequestBody Map<String, String> map){
        String address = map.get("address");
        System.out.println(address);
        return Result.ok(blockChainService.getBlanceOf(address));
    }

    @PostMapping("sendTransaction")
    public Result sendTransaction(@RequestBody Map<String, String> map){
        String from = map.get("from");
        String to = map.get("to");
        BigInteger value = new BigInteger(map.get("value"));
        String data = map.get("data")!=null?map.get("data"):null;
        String password = map.get("password");
        return Result.ok(blockChainService.sendEtherTransaction(from,to,value,password));
    }
}
