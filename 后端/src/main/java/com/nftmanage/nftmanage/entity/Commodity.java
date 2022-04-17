package com.nftmanage.nftmanage.entity;

import lombok.Data;

@Data
public class Commodity {
    private int id;
    private String name;
    private String owner;
    private String sort;
    private String saletime;
    private int price;
    private String store;
    private String introduction;
    private String information;
    private int tokenid;
    private String tokenURI;
    private String indexid;
    private String privatekey;
}
