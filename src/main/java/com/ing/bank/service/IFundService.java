package com.ing.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.bank.entity.User;

@Service
public interface IFundService {

	public String fundTransfer(Long fromAcc, Long toAcc, Double amount);

	public List<User> getAccountNumbers(Long accountNumber);

}
