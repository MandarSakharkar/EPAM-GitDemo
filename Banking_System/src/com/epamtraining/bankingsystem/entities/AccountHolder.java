package com.epamtraining.bankingsystem.entities;

import java.sql.Date;

public class AccountHolder {

	private String fullName;	//fullname of account holder
	private long uidNumber;		//Uid(Adhar) Number of account holder
	private Date dateOfBirth;	//Date of birth of account holder
	private Address address;	//address of account holder to be set using constructor of address object.

	public AccountHolder() {
	}

	public AccountHolder(String fullName, long uidNumber, Date dateOfBirth, Address address) {
		this.fullName = fullName;
		this.uidNumber = uidNumber;
		this.dateOfBirth = dateOfBirth;
		this.address = address;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public long getUidNumber() {
		return uidNumber;
	}

	public void setUidNumber(long uidNumber) {
		this.uidNumber = uidNumber;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}



}
