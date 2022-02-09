package com.example.springboot.controller.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class HashAccountStorage {

    private ArrayList<String> hashAccounts = new ArrayList<>();

    public int getNumberOfAccount() {
        return hashAccounts.size();
    }

    public void addHashAccount(String hashCode) {
        hashAccounts.add(hashCode);
    }

    @Bean
    @Scope("singleton")
    public HashAccountStorage singletonHashAccounts() {
        return new HashAccountStorage();
    }
}
