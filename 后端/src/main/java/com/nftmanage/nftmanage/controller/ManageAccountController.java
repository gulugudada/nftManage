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

    @PostMapping("findAllManageAccount")
    public Result findAllManageAccount() {
        return manageAccountService.findAllManageAccount();
    }

    //管理员登录
    @PostMapping("findPasswordByManagePhone")
    public Result findPasswordByPhone(@RequestBody Map<String, String> map) {
        return manageAccountService.findPasswordByPhone(map.get("phone"),map.get("password"));
    }

    //修改管理员账号用户名
    @PostMapping("updateManageUsername")
    public Result updateManageUsername(@RequestBody Map<String, String> map){
        return manageAccountService.updateManageUsername(map.get("phone"),map.get("username"));
    }

    //修改管理员账号密码
    @PostMapping("updateManagePassword")
    public Result updateManagePassword(@RequestBody Map<String, String> map){
        return manageAccountService.updateManagePassword(map.get("phone"),map.get("password"));
    }

    @PostMapping("addManageAccount")
    public Result addManageAccount(@RequestBody Map<String, String> map){
        return manageAccountService.addManageAccount(map.get("phone"),map.get("username"),map.get("password"),map.get("code"));
    }

    //分页获取普通用户账号
    @PostMapping("ManagefindAllAccount")
    public Result findAllAccount(@RequestBody Map<String, Integer> map) {
        return manageAccountService.findAllAccount(map.get("pagenum"));
    }

    @PostMapping("managerAddAccount")
    public Result managerAddAccount(@RequestBody Map<String, String> map){
        return manageAccountService.addAccount(map.get("phone"),map.get("username"),map.get("password"));
    }
}
