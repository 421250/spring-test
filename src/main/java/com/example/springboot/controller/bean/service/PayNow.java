package com.example.springboot.controller.bean.service;

import com.example.springboot.controller.pojo.TransferPojo;
import com.example.springboot.entity.AccountInfoRepository;
import com.example.springboot.entity.AccountTransact;
import com.example.springboot.entity.AccountTransactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@Service
public class PayNow {

    @Autowired
    AccountTransactRepository accountTransactRepository;

    @Autowired
    AccountInfoRepository accountInfoRepository;

    @Transactional(rollbackFor=Exception.class)
    public void transferMoney(TransferPojo transferPojo) throws Exception{

        AccountTransact senderAccountTransact = new AccountTransact();
        senderAccountTransact.setDeposit(0d);
        senderAccountTransact.setWithdrawal(transferPojo.getCash());

        accountInfoRepository.findById(Integer.parseInt(transferPojo.getSenderAccountNumber())).map(accountInfo -> {
            senderAccountTransact.setAccountInfo(accountInfo);
            return accountTransactRepository.save(senderAccountTransact);
        });

        Double balance =
                accountTransactRepository.getAccountBalance(Integer.parseInt(transferPojo.getSenderAccountNumber())).get(0).getTotalBalance();


        if(balance < 0){
            throw new Exception("Insufficient Funds");
        }



        AccountTransact receiverAccountTransact = new AccountTransact();
        receiverAccountTransact.setDeposit(transferPojo.getCash());
        receiverAccountTransact.setWithdrawal(0d);


        accountInfoRepository.findById(Integer.parseInt(transferPojo.getReceiverAccountNumber())).map(accountInfo -> {
            receiverAccountTransact.setAccountInfo(accountInfo);
            return accountTransactRepository.save(receiverAccountTransact);
        });

    }


}
