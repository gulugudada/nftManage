package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.ManageAccount;
import com.nftmanage.nftmanage.service.AccountService;
import com.nftmanage.nftmanage.service.ManageAccountService;
import com.nftmanage.nftmanage.utils.Result;
import com.nftmanage.nftmanage.utils.SHA_1;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *管理账号接口
 */
@Api(tags="管理账号接口类")
@RestController
@CrossOrigin
public class ManageAccountController {

    @Autowired
    ManageAccountService manageAccountService;

    @Autowired
    AccountService accountService;

    @PostMapping("findAllManageAccount")
    public Result findAllManageAccount() {
        return Result.ok(manageAccountService.findAllManageAccount());
    }

    //管理员登录
    @PostMapping("findPasswordByManagePhone")
    public Result findPasswordByPhone(@RequestBody Map<String, String> map) {
        String managephone = map.get("phone");
        String password = map.get("password");
        managephone = managephone.replace("\"", "");
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (manageAccountService.findManageAccountByPhone(managephone) == 0) {
            return Result.error(-1, "该账号未注册");
        } else if (manageAccountService.findPasswordByPhone(managephone).equals(password)) {
            Result result = new Result();
            result.put("username", manageAccountService.findUserNameByPhone(managephone));
            result.put("msg", "登录成功");
            return result;
        } else {
            return Result.error(-1, "密码错误");
        }
    }

    //修改管理员账号用户名
    @PostMapping("updateManageUsername")
    public Result updateManageUsername(@RequestBody Map<String, String> map){
        String managephone = map.get("phone");
        String username = map.get("username");
        managephone = managephone.replace("\"", "");
        username = username.replace("\"", "");
        if(!manageAccountService.findUsernameIsUse(username)) {
            if (manageAccountService.updateManageUsername(managephone, username)) {
                return Result.ok("修改成功");
            }
            return Result.error(-1, "修改失败");
        }
        return Result.error(-1, "该用户名已被使用");
    }

    //修改管理员账号密码
    @PostMapping("updateManagePassword")
    public Result updateManagePassword(@RequestBody Map<String, String> map){
        String managephone = map.get("phone");
        String password = map.get("password");
        managephone = managephone.replace("\"", "");
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if(manageAccountService.updateManagePassword(managephone,password)){
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @PostMapping("addManageAccount")
    public Result addManageAccount(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String username = map.get("username");
        String password = map.get("password");
        String code = map.get("code");//假设最高权限码，只用来添加管理员账号  000000
        phone = phone.replace("\"","");
        username = username.replace("\"","");
        password = SHA_1.jdkSHA1(password.replace("\"",""));
        code = code.replace("\"","");
        if(code.equals("000000")){
            if(!manageAccountService.findPhoneIsUse(phone)){
                if(!manageAccountService.findUsernameIsUse(username)){
                    ManageAccount manageAccount = new ManageAccount();
                    manageAccount.setPhone(phone);
                    manageAccount.setUsername(username);
                    manageAccount.setPassword(password);
                    if(manageAccountService.addManageAccount(manageAccount)){
                        return Result.ok("添加成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1,"该手机号已被使用");
        }
        return Result.error(-1,"最高权限码错误");
    }

    //分页获取普通用户账号
    @PostMapping("ManagefindAllAccount")
    public Result findAllAccount(@RequestBody Map<String, Integer> map) {
        Result result = new Result();
        int pagenum = map.get("pagenum");
        int total = manageAccountService.findAccountCount();
        if(total%5 == 0){
            result.put("total",total/5);
        }
        else{
            result.put("total",total/5+1);
        }
        result.put("accountList",manageAccountService.findAllAccount(pagenum));
        return result;
    }

    @PostMapping("managerAddAccount")
    public Result managerAddAccount(@RequestBody Map<String, String> map){
        String phone = map.get("phone");
        String username = map.get("username");
        String password = map.get("password");
        if (accountService.findAccountByPhone(phone) == 0) {
            if (accountService.findAccountByUserName(username) == 0) {
                Account account = new Account(phone, username, password);
                account.setPassword(SHA_1.jdkSHA1(password));
                if (accountService.addAccount(account) > 0) {
                    return Result.ok("注册成功");
                }
            }
            return Result.error(-1, "该用户名已被使用");
        }
        return Result.error(-1, "该账号已被使用");
    }
}
