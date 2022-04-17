package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.service.AccountService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *账号接口
 */
@Api(tags="账号接口类")
@RestController
@CrossOrigin
public class AccountController {
    @Autowired
    AccountService accountService;

    @PostMapping("findAllAccount")
    public Result findAllAccount() {
        return accountService.findAllAccount();
    }

    //登录
    @PostMapping("findPasswordByPhone")
    public Result findPasswordByPhone(@RequestBody Map<String, String> map) {
        return accountService.findPasswordByPhone(map.get("phone"),map.get("password"));
    }

    //Andriod端登录
    @PostMapping("findPasswordByPhoneAndroid")
    public Result findPasswordByPhoneAndroid(String phone,String password) {
        return accountService.findPasswordByPhone(phone,password);
    }

    //添加账号
    @PostMapping("addAccount")
    public Result addAccount(@RequestBody Map<String, String> map) {
        Account account = new Account(map.get("phone"),map.get("username"),map.get("password"));
        String code = map.get("code");
        return accountService.addAccount(account,code);
    }

    //Android端添加账号
    @PostMapping("addAccountAndroid")
    public Result addAccountAndroid(String phone,String username,String password,String code) {
        Account account = new Account(phone,username,password);
        return accountService.addAccount(account,code);
    }

    //获得验证码
    @PostMapping("getCode")
    public void getCode(@RequestBody Map<String, String> map) throws Exception {
        accountService.getCode(map.get("phone"));
    }

    //Android端获得验证码
    @PostMapping("getCodeAndroid")
    public void getCodeAndroid(String phone) throws Exception {
        accountService.getCode(phone);
    }

    //更改用户名
    @PostMapping("updateUserName")
    public Result updateUserName(@RequestBody Map<String, String> map){
        return accountService.updateUserName(map.get("username"),map.get("phone"));
    }

    //Android端更改用户名
    @PostMapping("updateUserNameAndroid")
    public Result updateUserNameAndroid(String phone,String username){
        return accountService.updateUserName(username,phone);
    }

    //更新密码
    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> map){
        return accountService.updatePassword(map.get("phone"),map.get("password"));
    }

    //Android端更新密码
    @PostMapping("updatePasswordAndroid")
    public Result updatePasswordAndroid(String phone,String password){
        return accountService.updatePassword(phone,password);
    }
}
