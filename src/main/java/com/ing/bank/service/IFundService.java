package com.ing.bank.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ing.bank.dto.FundTransferDto;
import com.ing.bank.entity.User;
import com.ing.bank.exception.FundTransferException;

@Service
public interface IFundService {

	public String fundTransfer(Long fromAcc, Long toAcc, Double amount) throws FundTransferException;

	public List<User> getAccountNumbers(Long accountNumber);

	public List<FundTransferDto> getTransactions(Long accountNo) throws FundTransferException;

}
