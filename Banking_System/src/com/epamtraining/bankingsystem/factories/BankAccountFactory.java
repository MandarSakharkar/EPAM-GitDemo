package com.epamtraining.bankingsystem.factories;

import com.epamtraining.bankingsystem.entities.BankAccount;
import com.epamtraining.bankingsystem.entities.CurrentAccount;
import com.epamtraining.bankingsystem.entities.SavingAccount;

public class BankAccountFactory {

	public static BankAccount getBankAccount(String accountType)
	{
		if(accountType.equalsIgnoreCase("Saving")) 
			return new SavingAccount();
		else if (accountType.equalsIgnoreCase("Current"))
			return new CurrentAccount();
		return null;
	}
}
