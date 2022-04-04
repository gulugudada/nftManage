package com.nftmanage.nftmanage.controller;

import com.nftmanage.nftmanage.entity.Commodity;
import com.nftmanage.nftmanage.service.CommodityService;
import com.nftmanage.nftmanage.utils.Result;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *商品接口
 */
@Api(tags="商品接口类")
@RestController
@CrossOrigin
public class CommodityController {

    @Autowired
    CommodityService commodityService;

    @PostMapping("findAllCommodity")
    public Result findAllAccount(@RequestBody Map<String, Integer> map) {
        Result result = new Result();
        int pagenum = map.get("pagenum");
        int total = commodityService.findCommodityCount();
        if(total%20 == 0){
            result.put("total",total/20);
        }
        else{
            result.put("total",total/20+1);
        }
        List<Commodity> commodityList = commodityService.findAllCommodity(pagenum);
        List<List> commodityList1 = new ArrayList<List>();
        for(int i = 0;i<5;i++){
            List<Commodity> commodityList2 = new ArrayList<Commodity>();
            for(int j = 0;j<4;j++){
                commodityList2.add(commodityList.get(i*4+j));
            }
            commodityList1.add(commodityList2);
        }
        result.put("commodityList",commodityList1);
        return result;
    }

    @PostMapping("findCommodityByName")
    public Result findCommodityByName(@RequestBody Map<String, String> map){
        String name = map.get("name");
        return Result.ok(commodityService.findCommodityByName(name));
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
//    @PostMapping(value = "/uploadFile")
//    // 参数：(图片名称，图片（不进行特殊处理）)前端统一放到file中，传给后端
//    public Result uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request, HttpServletResponse response) {
//        String url = null;
//        int update = 0;
//        BaseUser user = SessionUtil.getUser();
//        //判断文件是否为空
//        if (file.isEmpty()) {
//            return new JsonResult(false, "上传文件不能为空");
//        }
//        // 获取文件名
//        String fileName = file.getOriginalFilename();
//        fileName = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + "_" + fileName;
//        //加个时间戳，尽量避免文件名称重复
//        String path = "C:/fileUpload/" + fileName;
//        //创建文件路径
//        File dest = new File(path);
//        //判断文件是否已经存在q
//        if (dest.exists()) {
//            return new JsonResult(false, "文件已经存在");
//        }
//        //判断文件父目录是否存在
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdir();
//        }
//        try {
//            //上传文件
//            file.transferTo(dest); //保存文件
//            String  url = "http://183.121.136.142:8087" + "/file/image/" + fileName;//外部访问路径，对应本地的c:/fileupload
//        } catch (IOException e) {
//            return new JsonResult(false, "上传失败");
//        }
//        return new JsonResult(update>0,update>0?"上传图片成功":"上传图片失败");
//    }
}
