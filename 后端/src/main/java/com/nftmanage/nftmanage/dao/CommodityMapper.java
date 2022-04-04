package com.nftmanage.nftmanage.dao;

import com.nftmanage.nftmanage.entity.Commodity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommodityMapper {
    public List<Commodity> findAllCommodity(int pagenum);
    public Commodity findCommodityByName(String name);
    public int findCommodityCount();
}
