package com.sherlockqiao.andtestproject.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.service.PhoneNumberRepositoryFake;
import com.sherlockqiao.andtestproject.service.PhoneNumberService;
import com.sherlockqiao.andtestproject.service.PhoneNumberService.*;

/**
 * test case for all apis
 * 
 * @author sherlockq
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class PhoneNumberControllerTest {

	@Mock
	private PhoneNumberService phoneNumberServiceMock;

	private PhoneNumberController controller;

	@Before
	public void setUp() throws Exception {
		PhoneNumberRepositoryFake fake = new PhoneNumberRepositoryFake();
		controller = new PhoneNumberController(phoneNumberServiceMock);
		when(phoneNumberServiceMock.findAll()).thenReturn(fake.findAll());
		when(phoneNumberServiceMock.findForCustomer(1L)).thenReturn(fake.findForCustomer(fake.getCustomer(1L).get()));
		when(phoneNumberServiceMock.findForCustomer(100L)).thenThrow(new NoCustomerException());
		doThrow(new AlreadyActivatedException()).when(phoneNumberServiceMock).activate(21L);
		doThrow(new NoNumberException()).when(phoneNumberServiceMock).activate(100L);
	}

	@Test
	public void findAll() throws Exception {

		ResponseEntity<List<PhoneNumber>> response = controller.list();
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().size(), equalTo(3));
	}

	@Test
	public void findForCustomer() throws Exception {

		ResponseEntity<List<PhoneNumber>> response = controller.listForCustomer(1L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().size(), equalTo(1));

	}

	@Test
	public void customerNotFound() throws Exception {

		ResponseEntity<List<PhoneNumber>> response = controller.listForCustomer(100L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void activate() throws Exception {

		ResponseEntity<Boolean> response = controller.activatePhoneNumber(11L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(response.getBody(), equalTo(true));
	}

	@Test
	public void activateAlreadyActivated() throws Exception {

		ResponseEntity<Boolean> response = controller.activatePhoneNumber(21L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNPROCESSABLE_ENTITY));
		assertThat(response.getBody(), equalTo(false));
	}

	@Test
	public void activateNotFound() throws Exception {

		ResponseEntity<Boolean> response = controller.activatePhoneNumber(100L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

	}

}
