package com.nftmanage.nftmanage.entity;

import lombok.Data;

import java.sql.Date;
import java.util.List;

@Data
public class Shop {
    private int id;
    private String shopname;
    private String shopkeepper;
    private String shopsort;
    private Date time;
    private int commoditycount;
    private int fans;
    private String headsculpture;
    private List<Commodity> commodityList;
}
