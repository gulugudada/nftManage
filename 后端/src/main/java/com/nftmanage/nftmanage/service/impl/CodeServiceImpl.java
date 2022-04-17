package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.CodeMapper;
import com.nftmanage.nftmanage.entity.Code;
import com.nftmanage.nftmanage.service.CodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 * 验证码服务层
 */
@Service
public class CodeServiceImpl implements CodeService {

    @Autowired
    private CodeMapper codeMapper;

    @Override
    public String findCodeByPhone(String phone) {
        return codeMapper.findCodeByPhone(phone);
    }

    @Override
    public boolean findPhoneIsUse(String phone) {
        return codeMapper.findPhoneIsUse(phone) > 0;
    }

    @Override
    public void updateCode(Code code) {
        codeMapper.updateCode(code);
    }

    @Override
    public void addCode(Code code) {
        codeMapper.addCode(code);
    }

    @Override
    public void deleteCodeByPhone(String phone) {
        codeMapper.deleteCodeByPhone(phone);
    }
}
