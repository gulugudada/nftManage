package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.ManageAccountMapper;
import com.nftmanage.nftmanage.entity.Account;
import com.nftmanage.nftmanage.entity.ManageAccount;
import com.nftmanage.nftmanage.service.ManageAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManageAccountServiceImpl implements ManageAccountService {

    @Autowired
    private ManageAccountMapper manageAccountMapper;

    @Override
    public List<ManageAccount> findAllManageAccount() {
        return manageAccountMapper.findAllManageAccount();
    }

    @Override
    public List<Account> findAllAccount(int pagenum) {
        return manageAccountMapper.findAllAccount((pagenum-1)*5);
    }

    @Override
    public int findManageAccountByPhone(String phone) {
        return manageAccountMapper.findManageAccountByPhone(phone);
    }

    @Override
    public String findPasswordByPhone(String phone) {
        return manageAccountMapper.findPasswordByPhone(phone);
    }

    @Override
    public String findUserNameByPhone(String phone) {
        return manageAccountMapper.findUserNameByPhone(phone);
    }

    @Override
    public boolean updateManagePassword(String phone, String password) {
        if(manageAccountMapper.updateManagePassword(phone,password) > 0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateManageUsername(String phone, String username) {
        if(manageAccountMapper.updateManageUsername(phone,username) > 0){
            return true;
        }
        return false;
    }

    //查询该用户名是否已被使用
    @Override
    public boolean findUsernameIsUse(String username) {
        if(manageAccountMapper.findUsernameIsUse(username) == 0){
            return false;
        }
        return true;
    }

    //查询该手机号是否已被使用
    @Override
    public boolean findPhoneIsUse(String phone) {
        if(manageAccountMapper.findPhoneIsUse(phone) == 0){
            return false;
        }
        return true;
    }

    //添加管理员账号
    @Override
    public boolean addManageAccount(ManageAccount manageAccount) {
        if(manageAccountMapper.addManageAccount(manageAccount) > 0){
            return true;
        }
        return false;
    }

    @Override
    public int findAccountCount() {
        return manageAccountMapper.findAccountCount();
    }
}
