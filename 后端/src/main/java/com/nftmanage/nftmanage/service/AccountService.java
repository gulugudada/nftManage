package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Account;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AccountService {
    public List<Account> findAllAccount();
    public int findAccountByPhone(String phone);
    public String findPasswordByPhone(String phone);
    public String findUserNameByPhone(String phone);
    public int findAccountByUserName(String username);
    public int addAccount(Account account);
    public int updateUserName(Account account);
    public int updatePassword(Account account);
}
