package com.ing.bank.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.User;
import com.ing.bank.repository.UserRepository;

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

}
