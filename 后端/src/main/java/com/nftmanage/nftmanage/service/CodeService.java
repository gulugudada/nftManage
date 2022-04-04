package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Code;
import org.springframework.stereotype.Service;

/**
 * 验证码服务层
 */
@Service
public interface CodeService {
    public String findCodeByPhone(String phone);
    public boolean findPhoneIsUse(String account);
    public void updateCode(Code code);
    public void addCode(Code code);
    public void deleteCodeByPhone(String phone);
}
