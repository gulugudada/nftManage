package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.service.CommodityService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 *商品接口
 */
@Api(tags="商品接口类")
@RestController
@CrossOrigin
public class CommodityController {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    CommodityService commodityService;

    @PostMapping("findAllCommodity")
    public Result findAllAccount(@RequestBody Map<String, Integer> map) {
        return commodityService.findAllCommodity(map.get("pagenum"));
    }

    @PostMapping("findCommodityByName")
    public Result findCommodityByName(@RequestBody Map<String, String> map){
        return commodityService.findCommodityByName(map.get("name"));
    }

    @PostMapping("findCommodityByOwner")
    public Result findCommodityByOwner(@RequestBody Map<String, String> map){
        return commodityService.findCommodityByOwner(map.get("owener"));
    }

    @PostMapping("addCommodity")
    public Result addCommodity(@RequestParam("privateKey")String privateKey,@RequestParam("name")String name,@RequestParam("owner")String owner,@RequestParam("price")String price,@RequestParam("file")MultipartFile file) throws Exception {
        return commodityService.addCommodity(privateKey,name,owner,price,file);
    }

    @PostMapping("deleteCommodity")
    public Result deleteCommodity(@RequestBody Map<String, String> map) throws IOException, ExecutionException, InterruptedException {
        return commodityService.deleteCommodity(map.get("privateKey"), Integer.parseInt(map.get("tokenId")));
    }

    @PostMapping("buyCommodity")
    public Result buyCommodity(@RequestBody Map<String, String> map) throws Exception {
        return commodityService.buyCommodity(map.get("privateKey"),map.get("password"),map.get("owner"), Integer.parseInt(map.get("tokenId")));
    }
}
