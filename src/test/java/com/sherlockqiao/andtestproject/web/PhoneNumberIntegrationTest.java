package com.sherlockqiao.andtestproject.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.*;

import org.junit.Test;

/**
 * test case for all apis
 * 
 * @author sherlockq
 *
 */
public class PhoneNumberIntegrationTest extends AbstractIntegrationTests {

	@Test
	public void findAll() throws Exception {

		mockMvc.perform(get("/phone-numbers")).andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(3)));

	}

	@Test
	public void findForCustomer() throws Exception {

		mockMvc.perform(get("/customers/1/phone-numbers")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)));
		mockMvc.perform(get("/customers/2/phone-numbers")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)));
		mockMvc.perform(get("/customers/3/phone-numbers")).andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));

	}

	@Test
	public void customerNotFound() throws Exception {

		mockMvc.perform(get("/customers/100/phone-numbers")).andExpect(status().isNotFound());

	}

	@Test
	public void activate() throws Exception {
		mockMvc.perform(post("/phone-numbers/11/activations")).andExpect(status().isCreated())
				.andExpect(jsonPath("$", equalTo(Boolean.TRUE)));

	}

	@Test
	public void activateNotExisted() throws Exception {
		mockMvc.perform(post("/phone-numbers/100/activations")).andExpect(status().isNotFound());

	}

	@Test
	public void activateAlreadyActivated() throws Exception {

		mockMvc.perform(post("/phone-numbers/21/activations")).andExpect(status().is4xxClientError())
				.andExpect(jsonPath("$", equalTo(Boolean.FALSE)));

	}

}
