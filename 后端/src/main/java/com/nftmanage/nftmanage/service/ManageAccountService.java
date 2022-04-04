package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.ManageAccount;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 管理账号服务层
 */
@Service
public interface ManageAccountService {
    public List<ManageAccount> findAllManageAccount();
    public List<Account> findAllAccount(int pagenum);
    public int findManageAccountByPhone(String phone);
    public String findPasswordByPhone(String phone);
    public String findUserNameByPhone(String phone);
    public boolean updateManagePassword(String phone,String password);
    public boolean updateManageUsername(String phone,String username);
    public boolean findUsernameIsUse(String username);
    public boolean findPhoneIsUse(String phone);
    public boolean addManageAccount(ManageAccount manageAccount);
    public int findAccountCount();
}
