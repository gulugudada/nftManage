package com.nftmanage.nftmanage.service.impl;

import com.nftmanage.nftmanage.dao.CommodityMapper;
import com.nftmanage.nftmanage.entity.Commodity;
import com.nftmanage.nftmanage.service.BlockChainService;
import com.nftmanage.nftmanage.service.CommodityService;
import com.nftmanage.nftmanage.utils.PicUtil;
import com.nftmanage.nftmanage.utils.RedisUtils;
import com.nftmanage.nftmanage.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.web3j.crypto.Credentials;

import java.io.IOException;
import java.math.BigInteger;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private CommodityMapper commodityMapper;

    @Autowired
    private RedisUtils redisUtils;

    @Autowired
    private BlockChainService blockChainService;

    //目前的NFT合约中的下一个NFT的tokenId
    int tokenId = 3;

    @Override
    public Result findAllCommodity(int pagenum) {
        Result result = new Result();
        //总数
        int count = commodityMapper.findCommodityCount();
        //这一页的数量
        int count1 = 0;
        //总页数
        int total  = 1;
        if(count%20 == 0){
            total = count/20;
        }
        else {
            total = count/20 + 1;
        }
        result.put("total", total / 20 + 1);
        if(pagenum == total){
            count1 = count - (total-1)*20;
        }
        else{
            count1 = 20;
        }
        //查询缓存中是否存在
        boolean hasKey = redisUtils.exists("commodityList");
        List<Commodity> commodityList = new ArrayList<>();
        if(hasKey) {
            //获取缓存
            ArrayList<Object> commodityList3 = (ArrayList<Object>) redisUtils.lRange("commodityList",(pagenum-1)* 20L,(pagenum-1)* 20L +count1);
            for (Object o : commodityList3) {
                commodityList.add((Commodity) o);
            }
            System.out.println("缓存输出");
        }
        else {
            commodityList = commodityMapper.findAllCommodity();
            //  lPush方法
            for(Commodity commodity:commodityList){
                redisUtils.lPush("commodityList",commodity);
            }
            List<Commodity> commodityList3 = new ArrayList<Commodity>();
            for(int i = (pagenum-1)*20;i<(pagenum-1)*20+count1;i++){
                commodityList3.add(commodityList.get(i));
            }
            commodityList = commodityList3;
            System.out.println("数据库输出");
        }
        List<List> commodityList1 = new ArrayList<List>();
        //n用来记录有多少行
        int n = commodityList.size()%4==0?commodityList.size()/4:commodityList.size()/4+1;
        for(int i = 0,m = commodityList.size();i<n;i++){
            List<Commodity> commodityList2 = new ArrayList<>();
            for(int j = 0;j<4;j++){
                commodityList2.add(commodityList.get(i*4+j));
                m--;
                if(m == 0){
                    break;
                }
            }
            commodityList1.add(commodityList2);
            if(m == 0){
                break;
            }
        }
        result.put("commodityList",commodityList1);
        return result;
    }

    @Override
    public Result findCommodityByName(String name) {
        return Result.ok(commodityMapper.findCommodityByName(name));
    }

    @Override
    public Result findCommodityByOwner(String owner) {
        return Result.ok(commodityMapper.findCommodityByOwner(owner));
    }

    @Override
    public Result addCommodity(String privateKey,String name,String owner,String price,MultipartFile file) throws Exception {
        String path = "commodity/picture/";
        String relativeAddr = PicUtil.singleFileUpload(file);
        String tokenURI = path + relativeAddr;
        Commodity commodity = new Commodity();
        commodity.setName(name);
        commodity.setOwner(owner);
        commodity.setPrice(Integer.parseInt(price));
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        commodity.setSaletime(df.format(date));
        Credentials credentials = Credentials.create(privateKey);
        blockChainService.mintNFT(credentials,tokenURI);
        BigInteger total = blockChainService.balanceOfOwner(credentials);
        commodity.setPrivatekey(privateKey);
        commodity.setIndexid(String.valueOf(total));
        commodity.setTokenid(tokenId++);
        commodity.setTokenURI(tokenURI);
        int rel = commodityMapper.addCommodity(commodity);
        //清除缓存
        redisUtils.remove("commodityList");
        return rel == 1?Result.ok("添加成功"):Result.error(-1,"添加失败");
    }

    @Override
    public Result deleteCommodity(String privateKey, int tokenId) throws IOException, ExecutionException, InterruptedException {
        Credentials credentials = Credentials.create(privateKey);
        blockChainService.burnNFT(credentials,new BigInteger(tokenId+"000000000000000000"));
        int rel = commodityMapper.deleteCommodity(tokenId);
        //清除缓存
        redisUtils.remove("commodityList");
        return rel == 1?Result.ok("删除成功"):Result.error(-1,"删除失败");
    }

    @Override
    public Result buyCommodity(String privateKey,String password,String owner,int tokenId) throws Exception {
        Commodity commodity = commodityMapper.findPriceAndPrivateKeyByTokenId(tokenId);
        //原拥有者的私钥
        Credentials credentials = Credentials.create(commodity.getPrivatekey());
        //购买者的私钥
        Credentials credentials1 = Credentials.create(privateKey);
        //购买者发送以太交易给原拥有者
        blockChainService.sendEtherTransaction(credentials1.getAddress(),credentials.getAddress(),new BigInteger(commodity.getPrice()+"000000000000000000"),password);
        //用原拥有者的私钥给购买者发送NFT交易
        blockChainService.tranNFT(credentials,credentials1.getAddress(),new BigInteger(""+tokenId));
        Commodity commodity1 = new Commodity();
        commodity1.setOwner(owner);
        BigInteger total = blockChainService.balanceOfOwner(credentials);
        commodity1.setIndexid(String.valueOf(total));
        commodity1.setPrivatekey(privateKey);
        int rel = commodityMapper.updateCommodity(commodity1);
        //清除缓存
        redisUtils.remove("commodityList");
        return rel == 1?Result.ok("购买成功"):Result.error(-1,"购买失败");
    }
}
