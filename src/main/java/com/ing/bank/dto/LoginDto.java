package com.ing.bank.dto;

public class LoginDto {
	
	private Long accountNo;
	private String password;
	
	
	
	
	public Long getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(Long accountNo) {
		this.accountNo = accountNo;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
