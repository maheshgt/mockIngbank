package com.ing.bank.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.FundTransferDto;
import com.ing.bank.dto.UserDto;
import com.ing.bank.exception.FundTransferException;
import com.ing.bank.service.IFundService;
import com.ing.bank.service.IUserService;

@RestController
@RequestMapping("/fundtransfer")
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
public class FundController {
	
	@Autowired
	IFundService fundService;
	
	@Autowired 
	IUserService userService;
	
	private static final Logger logger = LoggerFactory.getLogger(FundController.class);
	
	@PostMapping("/{fromAcc}/{toAcc}/{amount}")
	public ResponseEntity<String> fundTransfer(@PathVariable Long fromAcc, @PathVariable Long toAcc, @PathVariable Double amount) throws FundTransferException{
		return new ResponseEntity<String>(fundService.fundTransfer(fromAcc, toAcc, amount),HttpStatus.OK);
	}
	
	@GetMapping("/view/{accountNumber}")
	public ResponseEntity<List<UserDto>> viewAccounts(@PathVariable Long accountNumber) {
		return new ResponseEntity<List<UserDto>>(userService.viewAccounts(accountNumber),HttpStatus.OK);
	}
	

	
	//controller for my transactions
	@GetMapping("/mytransactions")
	public ResponseEntity<List<FundTransferDto>> getMyTransactions(@RequestParam Long accountNo) throws FundTransferException
	{
		logger.info("entered into fund controller get transaction");
		return new ResponseEntity<List<FundTransferDto>>(fundService.getTransactions(accountNo),HttpStatus.OK);
	}
}
