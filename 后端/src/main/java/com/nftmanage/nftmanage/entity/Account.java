package com.nftmanage.nftmanage.entity;

import lombok.Data;

@Data
public class Account {
    public Account(){

    }

    public Account(String phone,String username,String password){
        this.phone = phone;
        this.username = username;
        this.password = password;
    }

    private int id;
    private String phone;
    private String username;
    private String password;
}
