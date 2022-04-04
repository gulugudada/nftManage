package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.entity.Shop;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShopService {
    public List<Shop> findAllShop(int pagenum);
    public int findShopCount();
}
