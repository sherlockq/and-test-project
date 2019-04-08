package com.sherlockqiao.andtestproject.repository;

import java.util.List;
import java.util.Optional;

import com.sherlockqiao.andtestproject.entity.Customer;
import com.sherlockqiao.andtestproject.entity.PhoneNumber;

public interface PhoneNumberRepository {

	List<PhoneNumber> findAll();

	/**
	 * use customer entity instead of id here to make it an OO approach
	 * 
	 * @param customer
	 * @return
	 */
	List<PhoneNumber> findForCustomer(Customer customer);

	/**
	 * find customer
	 * 
	 * @param customerId
	 * @return a optional, could be empty
	 */
	Optional<Customer> getCustomer(Long customerId);

	/**
	 * get phone entity by id
	 * 
	 * @param phoneNumberId
	 * @return
	 */
	Optional<PhoneNumber> findPhoneNumber(Long phoneNumberId);

	/**
	 * To persist changes to phone number;
	 * @param phoneNumber
	 * @return
	 */
	PhoneNumber save(PhoneNumber phoneNumber);
}