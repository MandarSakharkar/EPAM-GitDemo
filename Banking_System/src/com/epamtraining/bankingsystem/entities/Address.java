package com.epamtraining.bankingsystem.entities;

public class Address {

	private String street;	//street in which account holder lives.
	private String city;	//city in which account holder lives.
	private String state;	//state in which account holder lives.
	private int zipCode; 	// six digit zip-code of account holder.
	
	public Address() {
	}
	
	public Address(String street, String city, String state, int zipCode) {
		super();
		this.street = street;
		this.city = city;
		this.state = state;
		this.zipCode = zipCode;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getZipCode() {
		return zipCode;
	}

	public void setZipCode(int zipCode) {
		this.zipCode = zipCode;
	}
	
	
}
