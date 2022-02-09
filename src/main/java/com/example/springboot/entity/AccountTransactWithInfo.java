package com.example.springboot.entity;

import java.io.Serializable;



public class AccountTransactWithInfo implements Serializable {

    private Integer accountNumber;

    private String accountName;

    private Long transcationID;

    private Double deposit;

    private Double withdrawal;

    public AccountTransactWithInfo(Integer accountNumber, String accountName, Long transcationID, Double deposit, Double withdrawal) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.transcationID = transcationID;
        this.deposit = deposit;
        this.withdrawal = withdrawal;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getTranscationID() {
        return transcationID;
    }

    public void setTranscationID(Long transcationID) {
        this.transcationID = transcationID;
    }

    public Double getDeposit() {
        return deposit;
    }

    public void setDeposit(Double deposit) {
        this.deposit = deposit;
    }

    public Double getWithdrawal() {
        return withdrawal;
    }

    public void setWithdrawal(Double withdrawal) {
        this.withdrawal = withdrawal;
    }
}
