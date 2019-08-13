package com.ing.bank.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doNothing;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.ing.bank.dto.FundTransferDto;
import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.FundTransfer;
import com.ing.bank.entity.User;
import com.ing.bank.exception.FundTransferException;
import com.ing.bank.repository.FundRepository;
import com.ing.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class FundServiceImplTest {
	@InjectMocks
	FundServiceImpl fundServiceImpl;
	
	

	@Mock
	UserRepository userRepository;

	@Mock
	FundRepository fundRepository;

	@InjectMocks
	UserServiceImpl userServiceImpl;

	@Autowired
	MockMvc mockMvc;

	static List<User> list = new ArrayList<>();
	User user = null;
	User user1 = null;
	UserDto userd;
	User userEntity;

	void setUp() {
		user.setId(1L);
		user.setFirstName("mahesh");
		user.setLastName("gowtham");
		user.setAccountNo(223344L);
		user.setBalance(20000.00);
		user.setEmailId("mahi@gmail.com");
		user.setPhoneNo(9701307810L);
		user.setPassword("mahesh");
		user.setConfirmPassword("mahesh");
		list.add(user);
		user1.setId(2L);
		user1.setFirstName("rokesh");
		user1.setLastName("gowtham");
		user1.setAccountNo(334455L);
		user1.setBalance(20000.00);
		user1.setEmailId("roke@gmail.com");
		user1.setPhoneNo(9701307810L);
		user1.setPassword("rokesh");
		user1.setConfirmPassword("rokesh");
		list.add(user1);

	}

	@Test
	public void testFundTransfer() throws FundTransferException {
		double amount = 5000;
		Mockito.when(userRepository.findByaccountNo(user.getAccountNo())).thenReturn(user);
		user.setBalance(user.getBalance() - amount);
		Mockito.when(userRepository.findByaccountNo(user1.getAccountNo())).thenReturn(user1);
		user1.setBalance(user.getBalance() + amount);
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setAmount(amount);
		fundTransfer.setFromAccount(user.getAccountNo());
		fundTransfer.setToAccount(user1.getAccountNo());
		Mockito.when(fundRepository.save(Mockito.any())).thenReturn("amount transfer successfully");
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(doNothing());
		String status = fundServiceImpl.fundTransfer(user.getAccountNo(), user1.getAccountNo(), amount);
		assertEquals("amount transfer successfully", status);
	}

	public FundTransfer getFunds() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setAmount((double) 10000);
		fundTransfer.setFromAccount(201415L);
		fundTransfer.setId(1L);
		fundTransfer.setRemarks("test");
		fundTransfer.setToAccount(201516L);
		return fundTransfer;
	}

	public FundTransfer getFunds1() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setAmount((double) 10000);
		fundTransfer.setFromAccount(201415L);
		fundTransfer.setId(1L);
		fundTransfer.setRemarks("test");
		fundTransfer.setToAccount(201516L);
		return fundTransfer;
	}

	public FundTransfer getFunds2() {
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setAmount((double) 10000);
		fundTransfer.setFromAccount(201415L);
		fundTransfer.setId(1L);
		fundTransfer.setRemarks("test");
		fundTransfer.setToAccount(201516L);
		return fundTransfer;
	}

	@Test
	public void testGetMyTransactionsPositive() throws FundTransferException {
		List<FundTransfer> transactList = new ArrayList<FundTransfer>();
		transactList.add(getFunds());
		transactList.add(getFunds1());
		transactList.add(getFunds2());
		Mockito.when(fundRepository.getMyTransactions(Mockito.anyLong())).thenReturn(transactList);
		List<FundTransferDto> resultedList = fundServiceImpl.getTransactions(Mockito.anyLong());
		Assert.assertEquals(resultedList.size(), transactList.size());
	}

	@Test(expected = FundTransferException.class)
	public void testGetMyTransactionsNegative() throws FundTransferException {
		List<FundTransfer> transactList = new ArrayList<FundTransfer>();
		Mockito.when(fundRepository.getMyTransactions(Mockito.anyLong())).thenReturn(transactList);
		List<FundTransferDto> resultedList = fundServiceImpl.getTransactions(Mockito.anyLong());
		Assert.assertEquals(resultedList.size(), transactList.size());
	}

@Test
public void testGetAccountNumbers() {
	UserDto user=new UserDto();
	user.setAccountNo(123456l);
	List<UserDto> user1=new ArrayList<UserDto>();
	user1.add(user);
	
	User user3=new User();
	user.setAccountNo(1234l);
	List<User> user4=new ArrayList<User>();
	user4.add(user3);
	
	Mockito.when(userRepository.findByaccountNoNotLike(123456L)).thenReturn(user4);
	List<User> accountNumbers= fundServiceImpl.getAccountNumbers(123456l);

	assertNotNull(accountNumbers);
	assertEquals(1, accountNumbers.size());
}
}
