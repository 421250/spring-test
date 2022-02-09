package com.example.springboot.entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "account_addr1")
public class AccountAddress implements Serializable {

    public AccountAddress(){

    }

    public AccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String accountAddress;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumns({
            @JoinColumn(name = "account_number", nullable = false)
    })
    private AccountInfo accountInfo;


    public String getAccountAddress() {
        return accountAddress;
    }

    public void setAccountAddress(String accountAddress) {
        this.accountAddress = accountAddress;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
