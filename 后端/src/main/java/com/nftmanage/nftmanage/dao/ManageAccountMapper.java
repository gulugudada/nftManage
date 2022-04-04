package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.ManageAccount;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ManageAccountMapper {
    public List<ManageAccount> findAllManageAccount();
    public List<Account> findAllAccount(int pagenum);
    public int findManageAccountByPhone(String phone);
    public String findPasswordByPhone(String phone);
    public String findUserNameByPhone(String phone);
    public int updateManagePassword(String phone,String password);
    public int updateManageUsername(String phone,String username);
    public int findUsernameIsUse(String username);
    public int findPhoneIsUse(String phone);
    public int addManageAccount(ManageAccount manageAccount);
    public int findAccountCount();
}
