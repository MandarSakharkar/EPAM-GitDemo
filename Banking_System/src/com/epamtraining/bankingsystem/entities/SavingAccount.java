package com.epamtraining.bankingsystem.entities;

public class SavingAccount extends BankAccount {
	
	//static fields
	private static float rateOfInterest;  //rate of interest for saving bank account
	private static float minimumBalance;  //minimum balance to be maintain by account holder
	private static float penaltyAmount;	  //amount of penalty user need to pay if not maintained monthly balance. 
	
	//instance fields
	private float penaltyPending = 0; 
	
	//static getters and setters.
	public static float getRateOfInterest() {
		return rateOfInterest;
	}

	public static void setRateOfInterest(float rateOfInterest) {
		SavingAccount.rateOfInterest = rateOfInterest;
	}

	public static float getMinimumBalance() {
		return minimumBalance;
	}

	public static void setMinimumBalance(float minimumBalance) {
		SavingAccount.minimumBalance = minimumBalance;
	}
	
	public static float getPenaltyAmount() {
		return penaltyAmount;
	}

	public static void setPenaltyAmount(float penaltyAmount) {
		SavingAccount.penaltyAmount = penaltyAmount;
	}
	

	public void creditInterest() //credit or deposit monthly interest into Saving Account
	{
		this.deposit( (rateOfInterest / 100 )*this.getBalance() );
	}
	
	//business logic of Bank Account class
	
	public void debitPenaltyAmount() throws OperationFailureException //debit or withdraw penalty amount from user's account. 
	{
		/*
		 * Add penalty to pending penalty if Saving Account balance is already low.
		 * debit penalty only if there is enough balance in Saving Account.
		 */
		if( this.getBalance()< penaltyAmount || this.getBalance()-penaltyAmount < minimumBalance )
		{
			this.penaltyPending+=penaltyAmount;
		}
		else
		{
			withdraw(penaltyAmount+penaltyPending);
			penaltyPending = 0;     //
		}
		
	}
}
