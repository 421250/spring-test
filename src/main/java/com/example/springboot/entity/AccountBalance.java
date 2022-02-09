package com.example.springboot.entity;

public class AccountBalance {



    private Integer accountNumber;
    private Double totalBalance;

    public AccountBalance(Integer accountNumber, Double totalBalance) {
        this.accountNumber = accountNumber;
        this.totalBalance = totalBalance;
    }


    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Double getTotalBalance() {
        return totalBalance;
    }

    public void setTotalBalance(Double totalBalance) {
        this.totalBalance = totalBalance;
    }
}
