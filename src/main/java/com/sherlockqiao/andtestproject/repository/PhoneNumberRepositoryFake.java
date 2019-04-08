package com.sherlockqiao.andtestproject.repository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.sherlockqiao.andtestproject.entity.Customer;
import com.sherlockqiao.andtestproject.entity.PhoneNumber;

/**
 * this fake repository is based on a customer-numbers static datas.
 * <p>
 * customer(1, 'john') - numbers(11, '132-423-422', false')
 * <p>
 * customer(2, 'amy') - numbers(21, '111-423-422', true'), numbers(22,
 * '111-423-100', false')
 * <p>
 * customer(3, 'may') - none
 * 
 * @author sherlockq
 *
 */
@Repository
public class PhoneNumberRepositoryFake implements PhoneNumberRepository {
	private static final Map<Long, Customer> customers = new HashMap<>();

	public PhoneNumberRepositoryFake() {
		customers.put(1L, new Customer(1L, null, "john"));
		customers.put(2L, new Customer(2L, null, "amy"));
		customers.put(3L, new Customer(3L, null, "may"));

		Set<PhoneNumber> numbers = new HashSet<>();
		numbers.add(new PhoneNumber(11L, "132-423-422", false));
		customers.get(1L).setPhoneNumbers(numbers);

		numbers = new HashSet<>();
		numbers.add(new PhoneNumber(21L, "111-423-422", true));
		numbers.add(new PhoneNumber(22L, "111-423-100", false));
		customers.get(2L).setPhoneNumbers(numbers);

		numbers = new HashSet<>();
		customers.get(3L).setPhoneNumbers(numbers);
	}

	@Override
	public List<PhoneNumber> findAll() {
		return customers.values().stream().flatMap(customer -> customer.getPhoneNumbers().stream())
				.collect(Collectors.toList());
	}

	@Override
	public List<PhoneNumber> findForCustomer(Customer customer) {
		return customer.getPhoneNumbers().stream().collect(Collectors.toList());
	}

	@Override
	public Optional<Customer> getCustomer(Long customerId) {
		return Optional.ofNullable(customers.get(customerId));
	}

	@Override
	public Optional<PhoneNumber> findPhoneNumber(Long phoneNumberId) {
		return customers.values().stream().flatMap(customer -> customer.getPhoneNumbers().stream())
				.filter(number -> number.getId().equals(phoneNumberId)).findFirst();
	}

	@Override
	public PhoneNumber save(PhoneNumber phoneNumber) {
		// do nothing
		return phoneNumber;
	}
}
