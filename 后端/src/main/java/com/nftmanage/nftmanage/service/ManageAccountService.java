package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.utils.Result;
import org.springframework.stereotype.Service;

/**
 * 管理账号服务层
 */
@Service
public interface ManageAccountService {
    public Result findAllManageAccount();
    public Result findAllAccount(int pagenum);
    public Result findPasswordByPhone(String phone,String password);
    public Result updateManagePassword(String phone,String password);
    public Result updateManageUsername(String phone,String username);
    public Result addManageAccount(String phone,String username,String password,String code);
    public Result addAccount(String phone,String username,String password);
}
