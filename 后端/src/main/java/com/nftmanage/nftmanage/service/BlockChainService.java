package com.nftmanage.nftmanage.service;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public interface BlockChainService {
    public long getHeight();
    public List<String> getAccounts();
    public String getBlanceOf(String address);
    public String sendEtherTransaction(String from,String to,BigInteger value,String password);
}
