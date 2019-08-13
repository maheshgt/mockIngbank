package com.ing.bank.controller;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
@RunWith(MockitoJUnitRunner.class)
public class FundControllerTest {
	
	@InjectMocks
	FundController fundController;
	
	MockMvc mockMvc;
	
	@Before
	public void setUp(){
		mockMvc = MockMvcBuilders.standaloneSetup(fundController).build();
		
	}
	//@Test
	/*
	 * public void testFundTransfer() {
	 * 
	 * mockMvc.perform(MockMvcRequestBuilders.post("/{fromAcc}/{toAcc}/{amount}").
	 * contentType(MediaType.APPLICATION_JSON)
	 * .accept(MediaType.APPLICATION_JSON).content(
	 * asJsonString("transfer successfully"))).andExpect(status().isCreated()); }
	 */

	@Test
	public void testViewAccounts() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetMyTransactions() {
		fail("Not yet implemented");
	}

}
