package com.sherlockqiao.andtestproject.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.service.PhoneNumberService;
import com.sherlockqiao.andtestproject.service.PhoneNumberService.*;

import io.swagger.annotations.*;

@RestController
@Api
// we are using a restful style here.
class PhoneNumberController {

	private PhoneNumberService service;

	@Autowired
	public PhoneNumberController(PhoneNumberService service) {
		this.service = service;
	}

	@GetMapping(value = "/phone-numbers")
	@ApiOperation(value = "get all phone numbers")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success") })
	public ResponseEntity<List<PhoneNumber>> list() {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
	}

	@GetMapping(value = "/customers/{customerId}/phone-numbers")
	@ApiOperation(value = "get all phone numbers of a single customer")
	@ApiResponses({ @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 404, message = "The customer doesn't exist") })
	public ResponseEntity<List<PhoneNumber>> listForCustomer(
			@PathVariable @ApiParam("Customer Id. Try 1, 2 or 3") Long customerId) {
		try {
			return new ResponseEntity<>(service.findForCustomer(customerId), HttpStatus.OK);
		} catch (NoCustomerException e) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping(value = "/phone-numbers/{phoneNumberId}/activations")
	@ApiOperation(value = "activate a phone number", notes = "You can only activate a number once. A successful activation will return 201 as code and true as result.")
	@ApiResponses({ @ApiResponse(code = 201, message = "Activation succeed"),
			@ApiResponse(code = 404, message = "The phone id doesn't exist"),
			@ApiResponse(code = 422, message = "The phone number is already activated before.") })
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Boolean> activatePhoneNumber(
			@PathVariable @ApiParam("Phone number id. Try 11, 21 or 22.") Long phoneNumberId) {
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
