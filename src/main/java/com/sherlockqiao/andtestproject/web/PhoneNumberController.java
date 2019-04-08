package com.sherlockqiao.andtestproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.service.PhoneNumberService;
import com.sherlockqiao.andtestproject.service.PhoneNumberService.*;

@RestController
@RequestMapping()
// we are using a restful style here.
class PhoneNumberController {

	private PhoneNumberService service;

	@Autowired
	public PhoneNumberController(PhoneNumberService service) {
		this.service = service;
	}

	@GetMapping(value = "/phone-numbers")
	public ResponseEntity<List<PhoneNumber>> list() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/customers/{customerId}/phone-numbers")
	public ResponseEntity<List<PhoneNumber>> listForCustomer(@PathVariable Long customerId) {
		try {
			return new ResponseEntity<>(service.findForCustomer(customerId), HttpStatus.OK);
		} catch (NoCustomerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/phone-numbers/{phoneNumberId}/activations")
	public ResponseEntity<Boolean> activatePhoneNumber(@PathVariable Long phoneNumberId) {
		try {
			service.activate(phoneNumberId);
			return new ResponseEntity<>(true, HttpStatus.CREATED);

		} catch (NoNumberException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} catch (AlreadyActivatedException e) {
			return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
		}
	}

}
