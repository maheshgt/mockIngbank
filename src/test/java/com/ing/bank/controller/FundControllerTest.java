package com.ing.bank.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import com.ing.bank.entity.FundTransfer;
import com.ing.bank.entity.User;
import com.ing.bank.exception.FundTransferException;
import com.ing.bank.service.FundServiceImpl;

@RunWith(MockitoJUnitRunner.Silent.class)
public class FundControllerTest {

	@InjectMocks
	FundController fundController;

	@Mock
	FundServiceImpl fundServiceImpl;


	
	  @Test
	  
	  public void testFundTransfer() throws FundTransferException {
		  User user  = new User();
		  user.setId(1L);
			user.setFirstName("mahesh");
			user.setLastName("gowtham");
			user.setAccountNo(223344L);
			user.setBalance(20000.00);
			user.setEmailId("mahi@gmail.com");
			user.setPhoneNo(9701307810L);
			user.setPassword("mahesh");
			user.setConfirmPassword("mahesh");
			FundTransfer fundTransfer = new FundTransfer();
			fundTransfer.setAmount((double) 5000);
			fundTransfer.setFromAccount(201415L);
			fundTransfer.setId(1L);
			fundTransfer.setRemarks("test");
			fundTransfer.setToAccount(201516L);
		  
	  Mockito.when(fundServiceImpl.fundTransfer(fundTransfer.getFromAccount(), fundTransfer.getToAccount(), fundTransfer.getAmount())).thenReturn("transfer successfully");
		  ResponseEntity<String> s = fundController.fundTransfer(user.getAccountNo(), 334455L, 5000.00);
		  String m=s.toString();
		  assertNotNull(s);
		  assertEquals("<200 OK OK,[]>", m);
		
	  
	  }
	 

	
}
