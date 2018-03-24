package com.epamtraining.bankingsystem.entities;

public class BankAccount implements IBankAccount {

	
	private String accountNumber;
	private float balance;
	private AccountHolder accountHolder;
	
	public BankAccount() {
	}
	
	

	public BankAccount(String accountNumber, float balance, AccountHolder accountHolder) {
		super();
		this.accountNumber = accountNumber;
		this.balance = balance;
		this.accountHolder = accountHolder;
	}

	

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}



	
	// perform deposit operation.
	@Override
	public void deposit(float amount) {
		this.balance = this.balance + amount;
	}

	/*
	 * perform withdraw operation if sufficient balance is there,otherwise throw exception.
	 */
	
	@Override
	public void withdraw(float amount) throws Exception {
		/* 
		 * check if balance is insufficient to perform withdraw;
		 * throw exception in this case.
		 */
		if(this.balance-amount<0)
		{	
			throw new Exception("Could not perform transaction due to insufficient balace");
		}
		else
		{
			this.balance = this.balance - amount ; 
		}
	}

	@Override
	public void transferFunds(String payeeAccountNumber, float amount) throws Exception {
		/* 
		 * check if balance is insufficient to perform transaction;
		 * throw exception in this case.
		 */
		if(this.balance-amount<0)
		{	
			throw new Exception("Could not perform transaction due to insufficient balace");
		}
		else
		{
			/*
			 * check if payee's account exist or not 
			 */
			
			//add code to search for payee's account here
			
			//add code to perform withdraw from this account
			
			//add code to perform deposit to payee's account 			
		}
	}

	@Override
	public float getBalance() {
		return this.balance;
	}

	

}
