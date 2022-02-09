package com.example.springboot.controller.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

@Component
public class HashAccount {

    private ArrayList<String> hashAccounts = new ArrayList<>();

    public String getHash(String accountNumber, String accountName) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");

        byte[] messageDigest = md.digest((accountNumber + accountName).getBytes());

        BigInteger no = new BigInteger(1, messageDigest);

        String hashtext = no.toString(16);
        while (hashtext.length() < 32) {
            hashtext = "0" + hashtext;
        }
        hashAccounts.add(hashtext);
        return hashtext;
    }

    public int getNumberOfAccount() {
        return hashAccounts.size();
    }


    @Bean
    @RequestScope
    public HashAccount requestScopedHashAccount() {
        return new HashAccount();
    }

}
