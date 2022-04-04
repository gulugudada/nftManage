package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.Code;
import com.nftmanage.nftmanage.service.AccountService;
import com.nftmanage.nftmanage.service.CodeService;
import com.nftmanage.nftmanage.utils.Result;
import com.nftmanage.nftmanage.utils.SHA_1;
import com.nftmanage.nftmanage.utils.SMS;
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

    @Autowired
    CodeService codeService;

    @PostMapping("findAllAccount")
    public Result findAllAccount() {
        return Result.ok(accountService.findAllAccount());
    }

    //登录
    @PostMapping("findPasswordByPhone")
    public Result findPasswordByPhone(@RequestBody Map<String, String> map) {
        String phone = map.get("phone");
        String password = map.get("password");
        phone = phone.replace("\"", "");
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (accountService.findAccountByPhone(phone) == 0) {
            return Result.error(-1, "该账号未注册");
        } else if (accountService.findPasswordByPhone(phone).equals(password)) {
            Result result = new Result();
            result.put("username", accountService.findUserNameByPhone(phone));
            result.put("msg", "登录成功");
            return result;
        } else {
            return Result.error(-1, "密码错误");
        }
    }

    //Andriod端登录
    @PostMapping("findPasswordByPhoneAndroid")
    public Result findPasswordByPhoneAndroid(String phone,String password) {
        phone = phone.replace("\"", "");
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (accountService.findAccountByPhone(phone) == 0) {
            return Result.error(-1, "该账号未注册");
        } else if (accountService.findPasswordByPhone(phone).equals(password)) {
            Result result = new Result();
            result.put("username", accountService.findUserNameByPhone(phone));
            result.put("msg", "登录成功");
            return result;
        } else {
            return Result.error(-1, "密码错误");
        }
    }

    //添加账号
    @PostMapping("addAccount")
    public Result addAccount(@RequestBody Map<String, String> map) {
        String phone = map.get("phone");
        String username = map.get("username");
        String password = map.get("password");
        String code = map.get("code");
        if(code.equals(codeService.findCodeByPhone(phone))) {
            if (accountService.findAccountByPhone(phone) == 0) {
                if (accountService.findAccountByUserName(username) == 0) {
                    Account account = new Account(phone, username, password);
                    account.setPassword(SHA_1.jdkSHA1(password));
                    if (accountService.addAccount(account) > 0) {
                        codeService.deleteCodeByPhone(phone);
                        return Result.ok("注册成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1, "该账号已被使用");
        }
        return Result.error(-1,"验证码输入错误");
    }

    //Android端添加账号
    @PostMapping("addAccountAndroid")
    public Result addAccountAndroid(String phone,String username,String password,String code) {
        if(code.equals(codeService.findCodeByPhone(phone))) {
            if (accountService.findAccountByPhone(phone) == 0) {
                if (accountService.findAccountByUserName(username) == 0) {
                    Account account = new Account(phone, username, password);
                    account.setPassword(SHA_1.jdkSHA1(password));
                    if (accountService.addAccount(account) > 0) {
                        codeService.deleteCodeByPhone(phone);
                        return Result.ok("注册成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1, "该账号已被使用");
        }
        return Result.error(-1,"验证码输入错误");
    }

    //获得验证码
    @PostMapping("getCode")
    public void getCode(@RequestBody Map<String, String> map) throws Exception {
        String phone = map.get("phone");
        Code code = new Code();
        code.setPhone(phone);
        code.setCode(SMS.getSMS(phone));
        if(codeService.findPhoneIsUse(phone)){
            codeService.updateCode(code);
        }
        else {
            codeService.addCode(code);
        }
    }

    //Android端获得验证码
    @PostMapping("getCodeAndroid")
    public void getCodeAndroid(String phone) throws Exception {
        Code code = new Code();
        code.setPhone(phone);
        code.setCode(SMS.getSMS(phone));
        if(codeService.findPhoneIsUse(phone)){
            codeService.updateCode(code);
        }
        else {
            codeService.addCode(code);
        }
    }

    //更改用户名
    @PostMapping("updateUserName")
    public Result updateUserName(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String username = map.get("username");
        if(accountService.findAccountByUserName(username) == 0){
            Account account = new Account();
            account.setPhone(phone);
            account.setUsername(username);
            if(accountService.updateUserName(account) > 0){
                return Result.ok("更新成功");
            }
        }
        return Result.error(-1,"该用户名已被使用");
    }

    //Android端更改用户名
    @PostMapping("updateUserNameAndroid")
    public Result updateUserNameAndroid(String phone,String username){
        if(accountService.findAccountByUserName(username) == 0){
            Account account = new Account();
            account.setPhone(phone);
            account.setUsername(username);
            if(accountService.updateUserName(account) > 0){
                return Result.ok("更新成功");
            }
        }
        return Result.error(-1,"该用户名已被使用");
    }

    //更新密码
    @PostMapping("updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String password = map.get("password");
        Account account = new Account();
        account.setPhone(phone);
        account.setPassword(SHA_1.jdkSHA1(password));
        if(accountService.updatePassword(account) > 0) {
            return Result.ok("更新成功");
        }
        else {
            return Result.error(-1,"密码修改失败");
        }
    }

    //Android端更新密码
    @PostMapping("updatePasswordAndroid")
    public Result updatePasswordAndroid(String phone,String password){
        Account account = new Account();
        account.setPhone(phone);
        account.setPassword(SHA_1.jdkSHA1(password));
        if(accountService.updatePassword(account) > 0) {
            return Result.ok("更新成功");
        }
        else {
            return Result.error(-1,"密码修改失败");
        }
    }
}
