package com.ing.bank.service;

import java.util.List;

import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.User;
import com.ing.bank.exception.UserAccountException;

public interface IUserService {
	
	public User findByaccountNo(Long accountNumber);
	
	public List<UserDto> viewAccounts(Long accountNumber);

	public User userRegistration(UserDto user);

	public UserDto getUserDetails(Long accountNo) throws UserAccountException;
}
