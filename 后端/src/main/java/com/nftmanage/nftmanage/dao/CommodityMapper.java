package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {
    public List<Commodity> findAllCommodity();
    public Commodity findCommodityByName(String name);
    public List<Commodity> findCommodityByOwner(String owner);
    public int findCommodityCount();
    public int addCommodity(Commodity commodity);
    public int deleteCommodity(int tokenid);
    public Commodity findPriceAndPrivateKeyByTokenId(int tokenid);
    public int updateCommodity(Commodity commodity);
}
