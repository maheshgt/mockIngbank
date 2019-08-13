package com.ing.bank.service;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.User;
import com.ing.bank.exception.UserAccountException;
import com.ing.bank.repository.UserRepository;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

	@Mock
	UserRepository userRepository;
	@InjectMocks
	UserServiceImpl userService;
	UserDto user;
	User userEntity;

	@Test
	public void userRegistrationtest() {

		user = new UserDto();
		user.setEmailId("dimple.ten123");
		user.setFirstName("Prince");
		user.setLastName("Kumar");
		user.setBalance(10000.0);
		user.setPhoneNo(8275082261l);
		user.setAccountNo(123456l);
		user.setPassword("smvdu123");
		user.setConfirmPassword("smvdu123");
		

		userEntity = new User();
		userEntity.setEmailId("dimple.ten123");
		userEntity.setFirstName("Prince");
		userEntity.setPassword("smvdu123");
		userEntity.setConfirmPassword("smvdu123");
		userEntity.setPhoneNo(8275082261l);
		userEntity.setAccountNo(123456l);
		userEntity.setBalance(10000.0);
		userEntity.setLastName("Kumar");
		Mockito.when(userRepository.save(Mockito.any())).thenReturn(userEntity);
		assertEquals(userEntity.getFirstName(), userService.userRegistration(user).getFirstName());
	}
	
	public User getUser()
	{
		User user = new User();
		user.setAccountNo(123456L);
		user.setBalance((double) 10000);
		user.setConfirmPassword("test@123");
		user.setEmailId("sharathgs777@gmail.com");
		user.setFirstName("sharath");
		user.setId(1L);
		user.setLastName("Kumar");
		user.setPassword("test@123");
		user.setPhoneNo(1234567890L);
		return user;
	}

	
	@Test
	public void testGetUserDetailsPositive() throws UserAccountException
	{
		User user = getUser();
		Mockito.when(userRepository.findByaccountNo(Mockito.anyLong())).thenReturn(user);
		UserDto responseUser = userService.getUserDetails(Mockito.anyLong());
		Assert.assertEquals(responseUser.getEmailId(), "sharathgs777@gmail.com");
	}
	
	@Test(expected= UserAccountException.class) 
	public void testGetUserDetailsNegative() throws UserAccountException
	{
		User user = null;
		Mockito.when(userRepository.findByaccountNo(Mockito.anyLong())).thenReturn(user);
		userService.getUserDetails(Mockito.anyLong());
	}

}
