package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Commodity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommodityService {
    public List<Commodity> findAllCommodity(int pagenum);
    public Commodity findCommodityByName(String name);
    public int findCommodityCount();
}
