package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Code;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CodeMapper {
    public String findCodeByPhone(String phone);
    public int findPhoneIsUse(String phone);
    public int updateCode(Code code);
    public int addCode(Code code);
    public int deleteCodeByPhone(String phone);
}
