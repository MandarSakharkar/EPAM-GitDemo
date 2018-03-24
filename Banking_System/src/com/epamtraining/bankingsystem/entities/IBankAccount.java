package com.epamtraining.bankingsystem.entities;


/*
 * IBankAccount - an interface to declare the basic functionalities,
 * to be provided to user holding a bank account.
 */
public interface IBankAccount {

	boolean deposit(float amount);		//deposits amount to this bank account and returns true if transaction successful, otherwise returns false.	
	boolean withdraw(float amount);		//withdraws amount from this bank account and returns true if transaction successful, otherwise returns false.
	boolean transferFunds(String payeeAccountNumber, float amount); //transfer funds from this bank account to payee's bank account and returns true if transaction successful, otherwise returns false.
	float getBalance(); 	//returns account balance of this bank account
}
