package com.epamtraining.bankingsystem.entities;


/*
 * IBankAccount - an interface to declare the basic functionalities,
 * to be provided to user holding a bank account.
 */
public interface IBankAccount {

	void deposit(float amount);		//deposits amount to this bank account.	
	void withdraw(float amount) throws Exception;		//withdraws amount from this bank account and throws exception if transaction unsuccessful.
	void transferFunds(String payeeAccountNumber, float amount) throws Exception; //transfer funds from this bank account to payee's bank account and throws exception if transaction unsuccessful.
	float getBalance(); 	//returns account balance of this bank account
}
