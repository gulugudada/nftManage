package com.nftmanage.nftmanage.service;

import com.nftmanage.nftmanage.utils.Result;
import org.springframework.stereotype.Service;
import org.web3j.crypto.Credentials;

import java.io.IOException;
import java.math.BigInteger;
import java.util.concurrent.ExecutionException;

@Service
public interface BlockChainService {
    public Result getHeight();
    public Result getAccounts();
    public Result getBlanceOf(String address);
    public Result sendEtherTransaction(String from,String to,BigInteger value,String password) throws IOException;
    public Result withdraw(Credentials credentials, String amount) throws Exception;
    public void mintNFT(Credentials credentials,String tokenURI) throws IOException, ExecutionException, InterruptedException;
    public void burnNFT(Credentials credentials,BigInteger tokenID) throws IOException, ExecutionException, InterruptedException;
    public BigInteger totalSupply(Credentials credentials) throws ExecutionException, InterruptedException, IOException;
    public BigInteger balanceOfOwner(Credentials credentials) throws Exception;
    public void tranNFT(Credentials credentials,String to,BigInteger tokenId) throws IOException, ExecutionException, InterruptedException;
    public Result tokenURI(Credentials credentials,BigInteger tokenId) throws IOException, ExecutionException, InterruptedException;
}
