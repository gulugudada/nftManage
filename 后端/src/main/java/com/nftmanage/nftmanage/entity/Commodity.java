package com.nftmanage.nftmanage.entity;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class Commodity {
    private int id;
    private String name;
    private String author;
    private String sort;
    private Date saletime;
    private BigDecimal price;
    private String store;
    private String root;
    private String introduction;
    private String information;
}
