package com.sherlockqiao.andtestproject.web;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.service.PhoneNumberService;

@RestController
@RequestMapping()
// we are using a restful style here.
class PhoneNumberController {

	@Autowired
	private PhoneNumberService service;

	@Autowired
	public PhoneNumberController(PhoneNumberService service) {
		this.service = service;
	}

	@GetMapping(value = "/phone-numbers")
	public ResponseEntity<List<PhoneNumber>> list() {
		return new ResponseEntity<>(new ArrayList<PhoneNumber>(), HttpStatus.ACCEPTED);
	}

	@GetMapping(value = "/customers/{customId}/phone-numbers")
	public ResponseEntity<List<PhoneNumber>> listForCustomer(Long customId) {
		return new ResponseEntity<>(new ArrayList<PhoneNumber>(), HttpStatus.ACCEPTED);
	}

	@PostMapping(value = "/phone-numbers/{phoneNumberId}/activations")
	public ResponseEntity<Boolean> activatePhoneNumber(Long phoneNumberId) {
		return new ResponseEntity<>(false, HttpStatus.ACCEPTED);
	}

}
