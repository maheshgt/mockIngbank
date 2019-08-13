package com.ing.bank.service;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

import com.ing.bank.entity.User;
import com.ing.bank.repository.FundRepository;
import com.ing.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class FundServiceImplTest {
	
	@Mock
	UserRepository userRepository;
	@Mock
	FundRepository fundRepository;
	
	/*
	 * @InjectMocks UserServiceImpl userServiceImpl;
	 */
	@InjectMocks
	FundServiceImpl fundServiceImpl;
	
	@Autowired
	MockMvc mockMvc;
	
	static List<User> list = new ArrayList<>();
	User user = null;
	User user1 = null;
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
	
	
	  @Test public void testFundTransfer() { double amount=5000;
	  Mockito.when(userRepository.findByaccountNo(Mockito.anyLong())).thenReturn(user); 
	 // Mockito.when(fundRepository.save(Mockito.any());
			  String status = fundServiceImpl.fundTransfer(user.getAccountNo(), user1.getAccountNo(), amount);
	  
	  }
	 

}
