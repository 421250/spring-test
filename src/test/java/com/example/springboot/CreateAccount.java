package com.example.springboot;

import com.example.springboot.configuration.BankJPAConfiguration;
import com.example.springboot.controller.pojo.AccountPojo;
import com.example.springboot.entity.AccountInfo;
import com.example.springboot.entity.AccountInfoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.PersistenceContext;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(
        classes = { BankJPAConfiguration.class})
@Transactional("transactionManager")
@PersistenceContext
public class CreateAccount {

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Test
    public void checkIfsaveAccountIsSuccessful() {

        AccountPojo accountPojo = new AccountPojo("123456", "test_account", "", 1);

        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccountName(accountPojo.getAccountName());
        accountInfo.setAccountNumber(Integer.parseInt(accountPojo.getAccountNumber()));
        accountInfoRepository.save(accountInfo);

        assert(accountInfoRepository.findById(123456).get().getAccountName().equals("test_account"));
    }

}
