package com.ing.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.LoginDto;
import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.User;
import com.ing.bank.exception.UserAccountException;
import com.ing.bank.repository.UserRepository;
import com.ing.bank.response.ApiResponse;



@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	UserRepository userRepository;

	public List<UserDto> viewAccounts(Long accountNumber) {
		User user = userRepository.findByaccountNo(accountNumber);
		List<User> accountNumbers = userRepository.findByaccountNoNotLike(accountNumber);
		List<UserDto> list = new ArrayList<>();

		for (User an : accountNumbers) {
			UserDto userd = new UserDto();
			userd.setFirstName(an.getFirstName());
			userd.setAccountNo(an.getAccountNo());
			list.add(userd);
		}

		return list;

	}

	@Override
	public User findByaccountNo(Long accountNumber) {
		return userRepository.findByaccountNo(accountNumber);
	}

	public User userRegistration(UserDto userDto) {
		int accountNumber = 100000 + new Random().nextInt(900000);
		Long accnNum = Long.valueOf(accountNumber);

		User user = new User();
		userDto.setAccountNo(accnNum);
		if (userDto.getPassword().equals(userDto.getConfirmPassword())) {
			BeanUtils.copyProperties(userDto, user);
			return userRepository.save(user);
		}

		return user;

	}

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public ApiResponse login(LoginDto logindto) {

		User user = userRepository.findByaccountNo(logindto.getAccountNo());
		if (user == null) {
			throw new RuntimeException("User doesn't exist.");

		}
		if (!user.getPassword().equals(logindto.getPassword())) {

			throw new RuntimeException("Password mismatch");
		}
		return new ApiResponse(200, "Login Sucess", null);
	}

	// service for user get details
	public UserDto getUserDetails(Long accountNo) throws UserAccountException {
		User user = userRepository.findByaccountNo(accountNo);
		logger.info("Eneterd into user service");
		if (user != null) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		} else {
			throw new UserAccountException("No user details found");
		}
	}

}
