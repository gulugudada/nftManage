package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.AccountMapper;
import com.nftmanage.nftmanage.dao.ManageAccountMapper;
import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.ManageAccount;
import com.nftmanage.nftmanage.service.ManageAccountService;
import com.nftmanage.nftmanage.utils.Result;
import com.nftmanage.nftmanage.utils.SHA_1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ManageAccountServiceImpl implements ManageAccountService {

    @Autowired
    private ManageAccountMapper manageAccountMapper;

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public Result findAllManageAccount() {
        return Result.ok(manageAccountMapper.findAllManageAccount());
    }

    @Override
    public Result findAllAccount(int pagenum) {
        Result result = new Result();
        int total = manageAccountMapper.findAccountCount();
        if(total%5 == 0){
            result.put("total",total/5);
        }
        else{
            result.put("total",total/5+1);
        }
        result.put("accountList",manageAccountMapper.findAllAccount((pagenum-1)*5));
        return result;
    }

    @Override
    public Result findPasswordByPhone(String phone,String password) {
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (manageAccountMapper.findManageAccountByPhone(phone) == 0) {
            return Result.error(-1, "该账号未注册");
        } else if (manageAccountMapper.findPasswordByPhone(phone).equals(password)) {
            Result result = new Result();
            result.put("username", manageAccountMapper.findUserNameByPhone(phone));
            result.put("msg", "登录成功");
            return result;
        } else {
            return Result.error(-1, "密码错误");
        }
    }

    @Override
    public Result updateManagePassword(String phone, String password) {
        password = SHA_1.jdkSHA1(password.replace("\"", ""));;
        if(manageAccountMapper.updateManagePassword(phone,password) > 0){
            return Result.ok("修改成功");
        }
        return Result.error(-1,"修改失败");
    }

    @Override
    public Result updateManageUsername(String phone, String username) {
        if(manageAccountMapper.findUsernameIsUse(username) != 0) {
            if (manageAccountMapper.updateManageUsername(phone, username) > 0) {
                return Result.ok("修改成功");
            }
            return Result.error(-1, "修改失败");
        }
        return Result.error(-1, "该用户名已被使用");
    }

    /**
     * 添加管理员账号
     *
     * @param phone
     * @param username
     * @param password
     * @param code       假设最高权限码，只用来添加管理员账号  000000
     * @return
     */
    @Override
    public Result addManageAccount(String phone,String username,String password,String code) {
        password = SHA_1.jdkSHA1(password.replace("\"",""));
        if(code.equals("000000")){
            if(manageAccountMapper.findPhoneIsUse(phone) == 0){
                if(manageAccountMapper.findUsernameIsUse(username) == 0){
                    ManageAccount manageAccount = new ManageAccount();
                    manageAccount.setPhone(phone);
                    manageAccount.setUsername(username);
                    manageAccount.setPassword(password);
                    if(manageAccountMapper.addManageAccount(manageAccount) >0){
                        return Result.ok("添加成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1,"该手机号已被使用");
        }
        return Result.error(-1,"最高权限码错误");
    }

    @Override
    public Result addAccount(String phone, String username, String password) {
        if (accountMapper.findAccountByPhone(phone) == 0) {
            if (accountMapper.findAccountByUserName(username) == 0) {
                Account account = new Account(phone, username, password);
                account.setPassword(SHA_1.jdkSHA1(password));
                if (accountMapper.addAccount(account) > 0) {
                    return Result.ok("注册成功");
                }
            }
            return Result.error(-1, "该用户名已被使用");
        }
        return Result.error(-1, "该账号已被使用");
    }
}
