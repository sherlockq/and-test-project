package com.sherlockqiao.andtestproject.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Because phone number
 * @author sherlockq
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.ALWAYS)
public class Customer {

	private Long id;
	private Set<PhoneNumber> phoneNumbers;
	private String name;

	
	public Customer(Long id, Set<PhoneNumber> phoneNumbers, String name) {
		super();
		this.id = id;
		this.phoneNumbers = phoneNumbers;
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Set<PhoneNumber> getPhoneNumbers() {
		return phoneNumbers;
	}

	public void setPhoneNumbers(Set<PhoneNumber> phoneNumbers) {
		this.phoneNumbers = phoneNumbers;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
