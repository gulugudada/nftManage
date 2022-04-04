package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.CommodityMapper;
import com.nftmanage.nftmanage.entity.Commodity;
import com.nftmanage.nftmanage.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Commodity> findAllCommodity(int pagenum) {
        return commodityMapper.findAllCommodity((pagenum-1)*20);
    }

    @Override
    public Commodity findCommodityByName(String name) {
        return commodityMapper.findCommodityByName(name);
    }

    @Override
    public int findCommodityCount() {
        return commodityMapper.findCommodityCount();
    }
}
