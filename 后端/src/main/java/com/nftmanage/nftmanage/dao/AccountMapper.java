package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Account;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 账号对应Mapper类
 */
@Mapper
public interface AccountMapper {
    public List<Account> findAllAccount();
    public int findAccountByPhone(String phone);
    public String findPasswordByPhone(String phone);
    public String findUserNameByPhone(String phone);
    public int findAccountByUserName(String username);
    public int addAccount(Account account);
    public int updateUserName(Account account);
    public int updatePassword(Account account);
}
