package com.example.springboot.controller.pojo;

public class AccountPojo {

    private String accountNumber;
    private String accountName;
    private String accountHash;
    private int numberOfAcct;

    public AccountPojo(String accountNumber, String accountName, String accountHash, int numberOfAcct) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountHash = accountHash;
        this.numberOfAcct = numberOfAcct;
    }

    public AccountPojo() {
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountHash() {
        return accountHash;
    }

    public void setAccountHash(String accountHash) {
        this.accountHash = accountHash;
    }

    public int getNumberOfAcct() {
        return numberOfAcct;
    }

    public void setNumberOfAcct(int numberOfAcct) {
        this.numberOfAcct = numberOfAcct;
    }
}
