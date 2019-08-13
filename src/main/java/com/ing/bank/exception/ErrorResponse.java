package com.ing.bank.exception;

import java.util.Date;

public class ErrorResponse {
	
	private String message;
	private Date timestamp;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public ErrorResponse(String message, Date timestamp) {
		super();
		this.message = message;
		this.timestamp = timestamp;
	}
	public ErrorResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
