package com.epamtraining.bankingsystem.entities;

import java.util.HashSet;
import java.util.Set;

/*
 * Simple non-persistent implementation of IBank interface using simple collection.
 * Set collection is used here to manage accounts. Which allows only accounts with unique account number.  
 */
public class BankCollectionImplementation implements IBank{

	String bankName;
	String bankIFSCCode;

	Set<IBankAccount> bankAccountSet;

	public BankCollectionImplementation() {
		this("","",new HashSet<>());
	}

	public BankCollectionImplementation(String bankName, String bankIFSCCode, Set<IBankAccount> bankAccountSet) {
		super();
		this.bankName = bankName;
		this.bankIFSCCode = bankIFSCCode;
		this.bankAccountSet = bankAccountSet;
	}


	/*
	 * Add the newly create account object to the accountSet collection. 
	 * method will return false if account with same account number already exist in bankAccountSet. 
	 */
	@Override
	public boolean addAccount(IBankAccount bankAccount) {
		return bankAccountSet.add(bankAccount);
	}

	/* 
	 * Search bank account with account number;
	 * Returns null if account with given account number is not in bankAccountSet.
	 */
	@Override
	public IBankAccount searchAccount(String accountNumber) {

		/*
		 * create instance of class at higher level of abstraction, which is class BankAccount here;
		 * to make search using account number easy. 
		 * Refer the implementation of equals method of BankAccount.
		 */
		BankAccount bankAccount = new BankAccount(accountNumber, 0.0f, null);

		/*
		 * iterate through bankAccountSet and check if account with provided account number is found or not.
		 */
		for (IBankAccount iBankAccount : bankAccountSet) {
			if(iBankAccount.equals(bankAccount))
				return iBankAccount;
		}
		return null;
	}

	/* 
	 * Remove the given bankAccount from the bankAccountSet.
	 * Requires bankAccount object as an argument;
	 * returns false if null or object with invalid account number is passed.
	 */
	@Override
	public boolean removeAccount(IBankAccount bankAccount) {
		if (bankAccount!=null) //check if null is passed as bankAccount object to avoid NullPointerException; return false in such case
			return bankAccountSet.remove(bankAccount);
		else 
			return false;
	}

	//getters and setters

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankIFSCCode() {
		return bankIFSCCode;
	}

	public void setBankIFSCCode(String bankIFSCCode) {
		this.bankIFSCCode = bankIFSCCode;
	}

	public Set<IBankAccount> getBankAccountSet() {
		return bankAccountSet;
	}

	public void setBankAccountSet(Set<IBankAccount> bankAccountSet) {
		this.bankAccountSet = bankAccountSet;
	}



}
