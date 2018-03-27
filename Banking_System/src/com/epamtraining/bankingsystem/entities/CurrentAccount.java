package com.epamtraining.bankingsystem.entities;

import com.epamtraining.bankingsystem.exceptions.OperationFailureException;

public class CurrentAccount extends BankAccount {

	private static float overdraftLimit; 
	
	

	//static getters and setters
	
	public static float getOverdraftLimit() {
		return overdraftLimit;
	}

	public static void setOverdraftLimit(float overdraftLimit) {
		CurrentAccount.overdraftLimit = overdraftLimit;
	}
	
	/* 
	 * As Current Account provides overdraft facility, 
	 * account holder can perform withdraw operations even if balance is low or zero.
	 * however account holder only allowed to withdraw till overdraft limit reached.
	 */
	@Override
	public void withdraw(float amount) throws OperationFailureException {
		/* 
		 * check if overdraft limit reached or not
		 * throw exception if limit is reached.
		 */
		
		if(this.getBalance()-amount < -1*overdraftLimit)
		{	
			throw new OperationFailureException("Could not perform transaction as overdraft limit is reached.");
		}
		else
		{
			this.setBalance(this.getBalance() - amount ); 
		}
	}

	@Override
	public void transferFunds(IBankAccount payeeAccount, float amount) throws OperationFailureException {
		
		/* 
		 * check if overdraft limit reached or not
		 * throw exception if limit is reached.
		 */

		float checkedBalance = this.getBalance(); //checkpoint for balance in order to rollback to this balance in case of transaction failure.
		if(this.getBalance()-amount < -1*overdraftLimit)
		{	
			throw new OperationFailureException("Could not perform transaction as overdraft limit is reached.");
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
				//rollback to checkpoint balance.
				this.setBalance(checkedBalance);
				throw new OperationFailureException(exception);
			}

		}
	}


	
}
