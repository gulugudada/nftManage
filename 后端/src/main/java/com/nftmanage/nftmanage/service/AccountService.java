package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.utils.Result;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public Result findAllAccount();
    public Result findPasswordByPhone(String phone,String password);
    public Result addAccount(Account account,String code);
    public Result updateUserName(String username,String phone);
    public Result updatePassword(String phone,String password);
    public void getCode(String phone) throws Exception;
}
