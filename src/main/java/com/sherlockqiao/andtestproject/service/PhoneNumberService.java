package com.sherlockqiao.andtestproject.service;

import java.util.List;

import com.sherlockqiao.andtestproject.entity.PhoneNumber;

/**
 * Service for phone numbers
 * 
 * @author sherlockq
 *
 */
public interface PhoneNumberService {
	
	/**
	 * get all phone numbers
	 * 
	 * @return all numbers
	 */
	List<PhoneNumber> findAll();

	/**
	 * get all phone numbers of a single custome
	 * 
	 * @param customerId as-is
	 * @return phone numbers of the specified customer, the order is not certain.
	 */
	List<PhoneNumber> findForCustomer(Long customerId) throws NoCustomerException;

	/**
	 * activate a phone number, if it's already activated, then a exception should
	 * be thrown.
	 * 
	 * @param phoneNumberId id for phone number
	 */
	void activate(Long phoneNumberId) throws AlreadyActivatedException;

	/**
	 * if no custmer found
	 * 
	 * @author sherlockq
	 *
	 */
	public static class NoCustomerException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -2707758994283193748L;
	};

	/**
	 * if phone number is already activated
	 * 
	 * @author sherlockq
	 *
	 */
	public static class AlreadyActivatedException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = -7551763353998037342L;
	};
}
