package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Shop;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface ShopMapper {
    public List<Shop> findAllShop(int pagenum);
    public int findShopCount();
}
