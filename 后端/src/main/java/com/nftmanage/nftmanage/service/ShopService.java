package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.utils.Result;
import org.springframework.stereotype.Service;


@Service
public interface ShopService {
    public Result findAllShop(int pagenum);
    public int findShopCount();
}
