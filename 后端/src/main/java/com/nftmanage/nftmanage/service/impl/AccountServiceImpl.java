package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.AccountMapper;
import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public List<Account> findAllAccount() {
        return accountMapper.findAllAccount();
    }

    @Override
    public int findAccountByPhone(String phone) {
        return accountMapper.findAccountByPhone(phone);
    }

    @Override
    public String findPasswordByPhone(String phone) {
        return accountMapper.findPasswordByPhone(phone);
    }

    @Override
    public String findUserNameByPhone(String phone) {
        return accountMapper.findUserNameByPhone(phone);
    }

    @Override
    public int findAccountByUserName(String username) {
        return accountMapper.findAccountByUserName(username);
    }

    @Override
    public int addAccount(Account account) {
        return accountMapper.addAccount(account);
    }

    @Override
    public int updateUserName(Account account) {
        return accountMapper.updateUserName(account);
    }

    @Override
    public int updatePassword(Account account) {
        return accountMapper.updatePassword(account);
    }
}
