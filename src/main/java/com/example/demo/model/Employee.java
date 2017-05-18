package com.example.demo.model;

/**
 * Entity object for Employee.
**/

public class Employee {
	/**
     * ID of the employee
    **/
	private Long id;
	/**
     * Name of the employee
    **/
	private String name;
	/**
     * Address of the employee
    **/
	private String address;
	/**
     * Phone number of the employee
    **/
	private String phoneNumber;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
