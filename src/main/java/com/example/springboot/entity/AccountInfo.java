package com.example.springboot.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class AccountInfo implements Serializable {

    public AccountInfo(){

    }

    public AccountInfo(Integer accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

    @Id
    private Integer accountNumber;

    private String accountName;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "accountInfo")
    private AccountAddress accountAddress;




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

    public AccountAddress getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(AccountAddress accountAddress) {
        this.accountAddress = accountAddress;
    }
}
