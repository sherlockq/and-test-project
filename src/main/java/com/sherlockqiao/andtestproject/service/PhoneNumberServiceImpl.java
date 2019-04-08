package com.sherlockqiao.andtestproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sherlockqiao.andtestproject.entity.Customer;
import com.sherlockqiao.andtestproject.entity.PhoneNumber;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	PhoneNumberRepositoryFake repository;

	public PhoneNumberServiceImpl() {
		this.repository = new PhoneNumberRepositoryFake();
	}

	@Override
	public List<PhoneNumber> findAll() {
		return repository.findAll();
	}

	@Override
	public List<PhoneNumber> findForCustomer(Long customerId) throws NoCustomerException {
		Optional<Customer> customer = repository.getCustomer(customerId);
		if (!customer.isPresent()) {
			throw new NoCustomerException();
		} else {
			return repository.findForCustomer(customer.get());
		}
	}

	@Override
	public void activate(Long phoneNumberId) throws AlreadyActivatedException, NoNumberException {
		Optional<PhoneNumber> phoneNumber = repository.findPhoneNumber(phoneNumberId);
		if (!phoneNumber.isPresent()) {
			throw new NoNumberException();
		}

		boolean result = phoneNumber.get().activate();

		if (!result) {
			throw new AlreadyActivatedException();
		}

	}

}
