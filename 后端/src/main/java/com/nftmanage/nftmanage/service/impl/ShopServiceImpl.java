package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.CommodityMapper;
import com.nftmanage.nftmanage.dao.ShopMapper;
import com.nftmanage.nftmanage.entity.Commodity;
import com.nftmanage.nftmanage.entity.Shop;
import com.nftmanage.nftmanage.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ShopServiceImpl implements ShopService {

    @Autowired
    private ShopMapper shopMapper;

    @Autowired
    private CommodityMapper commodityMapper;

    @Override
    public List<Shop> findAllShop(int pagenum) {
        List<Shop> shopList = shopMapper.findAllShop((pagenum-1)*4);
        List<Commodity> commodityList;
//        for(){
//      
//        }
        return null;
    }

    @Override
    public int findShopCount() {
        return 0;
    }
}
