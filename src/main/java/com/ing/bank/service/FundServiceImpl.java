package com.ing.bank.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ing.bank.dto.FundTransferDto;
import com.ing.bank.entity.FundTransfer;
import com.ing.bank.entity.User;
import com.ing.bank.exception.FundTransferException;
import com.ing.bank.repository.FundRepository;
import com.ing.bank.repository.UserRepository;

@Service
public class FundServiceImpl implements IFundService {

	@Autowired
	FundRepository fundRepository;

	@Autowired
	UserRepository userRepository;

	private static final Logger logger = LoggerFactory.getLogger(FundServiceImpl.class);

	@Override
	public String fundTransfer(Long fromAcc, Long toAcc, Double amount) throws FundTransferException {
		User user1 = userRepository.findByaccountNo(fromAcc);
		User user2 = userRepository.findByaccountNo(toAcc);
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
			throw new FundTransferException("insuffiecient balance");
		}

	}

	public List<User> getAccountNumbers(Long accountNumber) {
		return userRepository.findByaccountNoNotLike(accountNumber);
	}

	public List<FundTransferDto> getTransactions(Long accountNo) throws FundTransferException {
		logger.info("entered into fund service get transaction");
		List<FundTransfer> fundTransfer = fundRepository.getMyTransactions(accountNo);
		List<FundTransferDto> fundTransferDto = new ArrayList<FundTransferDto>();
		if (fundTransfer.size() != 0) {
			for (int i = 0; i < fundTransfer.size(); i++) {
				FundTransferDto convertedFundTransferDto = new FundTransferDto();
				BeanUtils.copyProperties(fundTransfer.get(i), convertedFundTransferDto);
				fundTransferDto.add(convertedFundTransferDto);
			}
			return fundTransferDto;
		} else {
			throw new FundTransferException("No data found");
		}
	}

}
