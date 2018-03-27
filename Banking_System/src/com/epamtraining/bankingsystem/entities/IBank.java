package com.epamtraining.bankingsystem.entities;


/*
 * Interface to declare basic functions of a bank while managing users' accounts.
 * Implementation of the same can be done using simple collections,file system and database.
 */
public interface IBank {


	boolean addAccount(IBankAccount bankAccount);	//Add new account in bank
	IBankAccount searchAccount(String accountNumber);	//search Account and return null if account with search key is not in account list.
	boolean removeAccount(IBankAccount bankAccount);	//delete an account from account list.
}
