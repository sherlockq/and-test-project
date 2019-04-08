package com.sherlockqiao.andtestproject.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sherlockqiao.andtestproject.entity.Customer;
import com.sherlockqiao.andtestproject.entity.PhoneNumber;
import com.sherlockqiao.andtestproject.repository.PhoneNumberRepository;

@Service
public class PhoneNumberServiceImpl implements PhoneNumberService {

	PhoneNumberRepository repository;

	@Autowired
	public PhoneNumberServiceImpl(PhoneNumberRepository repository) {
		this.repository = repository;
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
		Optional<PhoneNumber> phoneNumberOp = repository.findPhoneNumber(phoneNumberId);
		if (!phoneNumberOp.isPresent()) {
			throw new NoNumberException();
		}

		PhoneNumber phoneNumber = phoneNumberOp.get();
		boolean result = phoneNumber.activate();
		this.repository.save(phoneNumber);

		if (!result) {
			throw new AlreadyActivatedException();
		}

	}

}
