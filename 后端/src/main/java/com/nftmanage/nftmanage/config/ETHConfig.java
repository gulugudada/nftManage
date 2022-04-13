package com.nftmanage.nftmanage.config;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.admin.Admin;
import org.web3j.protocol.geth.Geth;
import org.web3j.protocol.http.HttpService;

import java.util.concurrent.TimeUnit;

@Configuration
public class ETHConfig {

    @Value("${web3j.client-address}")
    private String rpc;

    @Bean
    public Web3j web3j() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60*1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60*1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(60*1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        Web3j web3j = Web3j.build(new HttpService(rpc,httpClient,false));
        return web3j;
    }

    @Bean
    public Admin admin() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        Admin admin = Admin.build(new HttpService(rpc, httpClient, false));
        return admin;
    }

    @Bean
    public Geth geth() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.connectTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.writeTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        builder.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        OkHttpClient httpClient = builder.build();
        Geth geth = Geth.build(new HttpService(rpc, httpClient, false));
        return geth;
    }
}
