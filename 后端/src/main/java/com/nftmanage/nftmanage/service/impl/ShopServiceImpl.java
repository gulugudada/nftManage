package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.CommodityMapper;
import com.nftmanage.nftmanage.dao.ShopMapper;
import com.nftmanage.nftmanage.entity.Commodity;
import com.nftmanage.nftmanage.entity.Shop;
import com.nftmanage.nftmanage.service.ShopService;
import com.nftmanage.nftmanage.utils.Result;
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
    public Result findAllShop(int pagenum) {
        Result result = new Result();
        int total = shopMapper.findShopCount();
        if(total%4 == 0){
            result.put("total",total/4);
        }
        else{
            result.put("total",total/4+1);
        }
        result.put("shopList",shopMapper.findAllShop((pagenum-1)*4));
        return result;
    }

    @Override
    public int findShopCount() {
        return 0;
    }
}
