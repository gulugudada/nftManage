package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.utils.Result;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

@Service
public interface CommodityService {
    public Result findAllCommodity(int pagenum);
    public Result findCommodityByName(String name);
    public Result findCommodityByOwner(String owner);
    public Result addCommodity(String privateKey,String name,String owner,String price,MultipartFile file) throws Exception;
    public Result deleteCommodity(String privateKey,int tokenId) throws IOException, ExecutionException, InterruptedException;
    public Result buyCommodity(String privateKey,String password,String owner,int tokenId) throws Exception;
}
