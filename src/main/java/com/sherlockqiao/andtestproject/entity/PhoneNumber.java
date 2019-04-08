package com.sherlockqiao.andtestproject.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(value = JsonInclude.Include.ALWAYS)
/**
 * Phone number, it has no visibility to customer.
 * 
 * @author sherlockq
 *
 */
public class PhoneNumber {

	private Long id;
	// phone number should be a complex structure with several parts. it's
	// simplified here as a string.
	private String phoneNumber;

	private Boolean isActivated;

	public PhoneNumber(Long id, String phoneNumber, Boolean isActivated) {
		super();
		this.id = id;
		this.phoneNumber = phoneNumber;
		this.isActivated = isActivated;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getIsActivated() {
		return isActivated;
	}

	public void setIsActivated(Boolean isActivated) {
		this.isActivated = isActivated;
	}

	/**
	 * Activate if not yet; return false if is already activated
	 * 
	 * @return false if already activated. otherwise true.
	 */
	public boolean activate() {
		if (this.isActivated) {
			return false;
		}
		{
			this.isActivated = true;
			return true;
		}
	}

}
