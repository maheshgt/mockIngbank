package com.ing.bank.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ing.bank.dto.LoginDto;
import com.ing.bank.dto.UserDto;
import com.ing.bank.entity.User;
import com.ing.bank.exception.UserAccountException;
import com.ing.bank.response.ApiResponse;
import com.ing.bank.service.IUserService;


@RestController
@CrossOrigin(allowedHeaders = { "*", "*/" }, origins = { "*", "*/" })
@RequestMapping("/ingbank")
public class UserController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	@Autowired
	IUserService userService;

	@PostMapping("/registration")
	public ResponseEntity userRegistration(@RequestBody UserDto user) {
		User user1 = userService.userRegistration(user);
		String msg = "user registered successfully with account number  ";
		Long accountNumber = user1.getAccountNo();
		String ac = accountNumber.toString();
		String mesg1 = msg.concat(ac);
		return new ResponseEntity<>(mesg1, HttpStatus.ACCEPTED);

	}

	@PostMapping("/login")
	public ApiResponse login(@RequestBody LoginDto logindto) {

		return (ApiResponse) userService.login(logindto);

	}

	// controller for user get account details
	@GetMapping("/getAccountDetails")
	public ResponseEntity<UserDto> getAccountDetails(@RequestParam Long accountNo) throws UserAccountException {
		logger.info("Eneterd into user controller");

		return new ResponseEntity<UserDto>(userService.getUserDetails(accountNo), HttpStatus.OK);

	}
}
