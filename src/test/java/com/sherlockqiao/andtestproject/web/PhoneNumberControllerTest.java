package com.sherlockqiao.andtestproject.web;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.*;

import java.util.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.repository.PhoneNumberRepository;
import com.sherlockqiao.andtestproject.repository.PhoneNumberRepositoryFake;
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

	@Test
	public void list_OneDataFromService_ReturnsSame() throws Exception {

		controller = new PhoneNumberController(phoneNumberServiceMock);
		when(phoneNumberServiceMock.findAll()).thenReturn(Arrays.asList(new PhoneNumber(1L, "number", true)));

		ResponseEntity<List<PhoneNumber>> response = controller.list();

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().size(), equalTo(1));
		assertThat(response.getBody().get(0).getPhoneNumber(), equalTo("number"));
	}

	@Test
	public void listForCustomer_OneDataFromService_ReturnsSame() throws Exception {

		controller = new PhoneNumberController(phoneNumberServiceMock);
		when(phoneNumberServiceMock.findForCustomer(1L)).thenReturn(Arrays.asList(new PhoneNumber(1L, "number", true)));

		ResponseEntity<List<PhoneNumber>> response = controller.listForCustomer(1L);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		assertThat(response.getBody().size(), equalTo(1));
		assertThat(response.getBody().get(0).getPhoneNumber(), equalTo("number"));

	}

	@Test
	public void listForCustomer_ServiceThrowNoCustomerException_ReturnNotFound() throws Exception {
		controller = new PhoneNumberController(phoneNumberServiceMock);
		when(phoneNumberServiceMock.findForCustomer(1L)).thenThrow(new NoCustomerException());

		ResponseEntity<List<PhoneNumber>> response = controller.listForCustomer(1L);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

	}

	@Test
	public void activate_ServiceCompleleSilently_ReturnCreatedAndTrue() throws Exception {
		controller = new PhoneNumberController(phoneNumberServiceMock);

		ResponseEntity<Boolean> response = controller.activatePhoneNumber(1L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.CREATED));
		assertThat(response.getBody(), equalTo(true));
	}

	@Test
	public void activate_ServiceThrowAlreadyActivatedException_Return422AsCode() throws Exception {
		controller = new PhoneNumberController(phoneNumberServiceMock);
		doThrow(new AlreadyActivatedException()).when(phoneNumberServiceMock).activate(1L);

		ResponseEntity<Boolean> response = controller.activatePhoneNumber(1L);

		assertThat(response.getStatusCode(), equalTo(HttpStatus.UNPROCESSABLE_ENTITY));
	}

	@Test
	public void activate_ServiceThrowNoNumberException_Return404() throws Exception {
		controller = new PhoneNumberController(phoneNumberServiceMock);

		doThrow(new NoNumberException()).when(phoneNumberServiceMock).activate(1L);
		ResponseEntity<Boolean> response = controller.activatePhoneNumber(1L);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));

	}

}
