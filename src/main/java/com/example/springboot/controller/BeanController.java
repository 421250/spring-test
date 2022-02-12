package com.example.springboot.controller;

import com.example.springboot.controller.bean.HashAccount;
import com.example.springboot.controller.bean.HashAccountStorage;
import com.example.springboot.controller.pojo.AccountPojo;
import com.example.springboot.controller.pojo.TranscatePojo;
import com.example.springboot.controller.pojo.TransferPojo;
import com.example.springboot.entity.*;
import com.example.springboot.controller.bean.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
public class BeanController {

	@Resource(name = "requestScopedHashAccount")
	HashAccount hashAccount;

	@Resource(name = "singletonHashAccounts")
	HashAccountStorage hashAccountStorage;

	@Autowired
	@Qualifier("requestScopedHashAccountModified")
	HashAccount hashAccountModified;

	@Autowired
	AccountInfoRepository accountInfoRepository;

	@Autowired
	AccountAddressRepository accountAddressRepository;

	@Autowired
	AccountTransactRepository accountTransactRepository;

	@Autowired
	PayNow paynow;



	@PostMapping(
			value = "bean/postbody",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public AccountPojo postBody(@RequestBody AccountPojo accountPojo) throws NoSuchAlgorithmException {
		String hashCode = hashAccount.getHash(accountPojo.getAccountNumber(), accountPojo.getAccountName());
		accountPojo.setAccountHash(hashCode);
		hashAccountStorage.addHashAccount(hashCode);
		accountPojo.setNumberOfAcct(hashAccountStorage.getNumberOfAccount());
		return accountPojo;
	}


	@PostMapping(
			value = "bean/saveacct",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public AccountPojo postAcct(@RequestBody AccountPojo accountPojo) throws NoSuchAlgorithmException {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountName(accountPojo.getAccountName());
		accountInfo.setAccountNumber(Integer.parseInt(accountPojo.getAccountNumber()));
		accountInfoRepository.save(accountInfo);
		return accountPojo;
	}


	@PostMapping(
			value = "bean/saveacctAndAddress",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public AccountPojo postAcctAndAddress(@RequestBody AccountPojo accountPojo) {
		AccountInfo accountInfo = new AccountInfo();
		accountInfo.setAccountName(accountPojo.getAccountName());
		accountInfo.setAccountNumber(Integer.parseInt(accountPojo.getAccountNumber()));

		AccountAddress accountAddress = new AccountAddress();
		accountAddress.setAccountAddress("test address");

		accountInfo.setAccountAddress(accountAddress);

		accountAddress.setAccountInfo(accountInfo);




		accountInfoRepository.save(accountInfo);

		return accountPojo;
	}


	@PostMapping(
			value = "bean/transcate",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public TranscatePojo postTranscate(@RequestBody TranscatePojo transcatePojo) {

		AccountTransact accountTransact = new AccountTransact();
		accountTransact.setDeposit(transcatePojo.getDeposit());
		accountTransact.setWithdrawal(transcatePojo.getWithdrawal());

		accountInfoRepository.findById(Integer.parseInt(transcatePojo.getAccountNumber())).map(accountInfo -> {
			accountTransact.setAccountInfo(accountInfo);
			return accountTransactRepository.save(accountTransact);
		});


		return transcatePojo;
	}



	@PostMapping(
			value = "bean/transfer",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public  TransferPojo transferMoney(@RequestBody TransferPojo transferPojo) {
		try {
			paynow.transferMoney(transferPojo);
		}catch(Exception e){
			e.printStackTrace();
		}

		return transferPojo;
	}



	@PutMapping(
			value = "bean/updatetranscate",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public TranscatePojo postUpdateTranscate(@RequestBody TranscatePojo transcatePojo) {

		accountTransactRepository.findById(Long.parseLong(transcatePojo.getTransacteID())).map(accountTransaction -> {
			accountTransaction.setDeposit(transcatePojo.getDeposit());
			accountTransaction.setWithdrawal(transcatePojo.getWithdrawal());
			return accountTransactRepository.save(accountTransaction);
		});


		return transcatePojo;
	}



	@GetMapping(
			value = "bean/gettranscate",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<AccountTransactWithInfo> getTranscationInfo(@RequestBody TranscatePojo transcatePojo) {


		return accountTransactRepository.getTransactRecord(Integer.parseInt(transcatePojo.getAccountNumber()));

	}

	@GetMapping(
			value = "bean/getAcctNumber",
			consumes = {MediaType.APPLICATION_JSON_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<AccountBalance> getAcctNumber(@RequestBody TranscatePojo transcatePojo) {


		return accountTransactRepository.getAccountBalance(Integer.parseInt(transcatePojo.getAccountNumber()));

	}




}
