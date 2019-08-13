package com.ing.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.entity.FundTransfer;
import com.ing.bank.entity.User;
import com.ing.bank.repository.FundRepository;
import com.ing.bank.repository.UserRepository;

@Service
public class FundServiceImpl implements IFundService {

	@Autowired
	FundRepository fundRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public String fundTransfer(Long fromAcc, Long toAcc, Double amount) {
		User user1 = userRepository.findByaccountNo(fromAcc);
		User user2 =userRepository.findByaccountNo(toAcc);
		if (user1.getBalance() >= amount) {
			user1.setBalance(user1.getBalance() - amount);
			userRepository.save(user1);
			user2.setBalance(user2.getBalance() + amount);
			userRepository.save(user1);
			FundTransfer ft = new FundTransfer();
			ft.setFromAccount(fromAcc);
			ft.setToAccount(toAcc);
			ft.setAmount(amount);
			ft.setRemarks("transfer successfully");
			fundRepository.save(ft);
			return "amount transfer successfully";
		} else {
			return "Insufficient Balance in your account";
		}

	}
	
	public List<User> getAccountNumbers(Long accountNumber){
		return userRepository.findByaccountNoNotLike(accountNumber);
	}

}
