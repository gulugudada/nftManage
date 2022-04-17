package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.service.ShopService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 *商家接口
 */
@Api(tags="商家接口类")
@RestController
@CrossOrigin
public class ShopController {

    @Autowired
    ShopService shopService;

    @PostMapping("findAllShop")
    public Result findAllShop(@RequestBody Map<String, Integer> map){
        return shopService.findAllShop(map.get("pagenum"));
    }
}
