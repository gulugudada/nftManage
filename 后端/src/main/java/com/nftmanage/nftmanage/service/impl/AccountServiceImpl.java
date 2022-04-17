package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.AccountMapper;
import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.Code;
import com.nftmanage.nftmanage.service.AccountService;
import com.nftmanage.nftmanage.service.CodeService;
import com.nftmanage.nftmanage.utils.Result;
import com.nftmanage.nftmanage.utils.SHA_1;
import com.nftmanage.nftmanage.utils.SMS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    CodeService codeService;

    @Override
    public Result findAllAccount() {
        return Result.ok(accountMapper.findAllAccount());
    }

    @Override
    public Result findPasswordByPhone(String phone,String password) {
        password = SHA_1.jdkSHA1(password.replace("\"", ""));
        if (accountMapper.findAccountByPhone(phone) == 0) {
            return Result.error(-1, "该账号未注册");
        } else if (accountMapper.findPasswordByPhone(phone).equals(password)) {
            Result result = new Result();
            result.put("username", accountMapper.findUserNameByPhone(phone));
            result.put("msg", "登录成功");
            return result;
        } else {
            return Result.error(-1, "密码错误");
        }
    }

    @Override
    public Result addAccount(Account account,String code) {
        if(code.equals(codeService.findCodeByPhone(account.getPhone()))) {
            if (accountMapper.findAccountByPhone(account.getPhone()) == 0) {
                if (accountMapper.findAccountByUserName(account.getUsername()) == 0) {
                    account.setPassword(SHA_1.jdkSHA1(account.getPassword()));
                    if (accountMapper.addAccount(account) > 0) {
                        codeService.deleteCodeByPhone(account.getPhone());
                        return Result.ok("注册成功");
                    }
                }
                return Result.error(-1, "该用户名已被使用");
            }
            return Result.error(-1, "该账号已被使用");
        }
        return Result.error(-1,"验证码输入错误");
    }

    @Override
    public Result updateUserName(String username,String phone) {
        if(accountMapper.findAccountByUserName(username) == 0){
            Account account = new Account();
            account.setPhone(phone);
            account.setUsername(username);
            if(accountMapper.updateUserName(account) > 0){
                return Result.ok("更新成功");
            }
        }
        return Result.error(-1,"该用户名已被使用");
    }

    @Override
    public Result updatePassword(String phone,String password) {
        Account account = new Account();
        account.setPhone(phone);
        account.setPassword(SHA_1.jdkSHA1(password));
        if(accountMapper.updatePassword(account) > 0) {
            return Result.ok("更新成功");
        }
        else {
            return Result.error(-1,"密码修改失败");
        }
    }

    @Override
    public void getCode(String phone) throws Exception {
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
}
