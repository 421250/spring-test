package com.example.springboot.controller.pojo;

public class TranscatePojo {
    private String accountNumber;



    private String transacteID;
    private Double deposit;
    private Double withdrawal;

    public String getTransacteID() {
        return transacteID;
    }

    public void setTransacteID(String transacteID) {
        this.transacteID = transacteID;
    }
    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
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
