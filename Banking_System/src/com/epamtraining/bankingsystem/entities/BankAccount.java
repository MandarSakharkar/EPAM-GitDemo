package com.epamtraining.bankingsystem.entities;

import com.epamtraining.bankingsystem.exceptions.OperationFailureException;

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
	public void withdraw(float amount) throws OperationFailureException {
		/* 
		 * check if balance is insufficient to perform withdraw;
		 * throw exception in this case.
		 */
		if(this.balance-amount<0)
		{	
			throw new OperationFailureException("Could not perform transaction due to zero balance");
		}
		else
		{
			this.balance = this.balance - amount ; 
		}
	}

	@Override
	public void transferFunds(IBankAccount payeeAccount,float amount) throws OperationFailureException {
		/* 
		 * check if balance is insufficient to perform transaction;
		 * throw exception in this case.
		 */

		float checkedBalance = this.balance; //checkpoint for balance in order to rollback to this balance in case of transaction failure.
		if(this.balance-amount<0)
		{	
			throw new OperationFailureException("Could not perform transaction due to zero balance");
		}
		else
		{
			// check if payee's account is null 

			if(payeeAccount==null)
				throw new OperationFailureException("null payee account");

			try
			{
				//withdraw from this account

				this.withdraw(amount);

				//deposit to payee's account
				payeeAccount.deposit(amount);
			}
			catch(Exception exception)
			{
				//rollback to checkpoint balance
				this.balance = checkedBalance;
				throw new OperationFailureException(exception);
			}

		}
	}

	@Override
	public float getBalance() {
		return this.balance;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountNumber == null) ? 0 : accountNumber.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj) //check if both references holding same objects
			return true;

		if (obj == null) //check if other object is null. To avoid NullPointerException
			return false;

		if (this.getClass() != obj.getClass()) //check class compatibility of both the objects.
			return false;

		BankAccount other = (BankAccount) obj;
		if (accountNumber == null) //check if accountNumber of this object is null
		{
			if (other.accountNumber != null)//check if accountNumber of other object is null
				return false;
		} else if (!accountNumber.equals(other.accountNumber)) //check if both objects have same account number
			return false;
		return true;
	}





}
