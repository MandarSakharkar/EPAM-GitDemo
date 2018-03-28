package com.epamtraining.bankingsystem.userinterface;

import java.sql.Date;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import com.epamtraining.bankingsystem.entities.AccountHolder;
import com.epamtraining.bankingsystem.entities.Address;
import com.epamtraining.bankingsystem.entities.BankAccount;
import com.epamtraining.bankingsystem.entities.BankCollectionImplementation;
import com.epamtraining.bankingsystem.entities.IBank;
import com.epamtraining.bankingsystem.entities.IBankAccount;
import com.epamtraining.bankingsystem.exceptions.OperationFailureException;
import com.epamtraining.bankingsystem.factories.BankAccountFactory;

public class ConsoleUserInterface {

	final static String BANKNAME = "Citi Bank";
	final static String BANKIFSC = "12345";
	final static Set<IBankAccount> ACCOUNTS = new HashSet<>();

	static IBank bank = new BankCollectionImplementation(BANKNAME, BANKIFSC, ACCOUNTS);

	static AccountHolder createAccountHolderDetails()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter account holder's detail as mentioned \n");
		System.out.println("Full Name : ");
		String fullName = scan.nextLine();

		System.out.println("UID(Adhar) Number : ");
		long uidNumber = scan.nextLong();

		scan.nextLine();
		System.out.println("Date of Birth in the format yyyy-mm-dd : ");
		Date dateOfBirth = Date.valueOf(scan.nextLine());

		Address address = createAddressDetails();
		scan.close();
		return new AccountHolder(fullName, uidNumber, dateOfBirth, address);
	}

	static Address createAddressDetails()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter following address Details: ");

		System.out.println("Street : ");
		String street = scan .nextLine();

		System.out.println("City : ");
		String city = scan.nextLine();

		System.out.println("State : ");
		String state = scan.nextLine();

		System.out.println("Zip Code : ");
		int zipCode = scan.nextInt();

		scan.close();
		return new Address(street, city, state, zipCode);
	}

	//Administrator functions - begin

	
	static void openAccount()
	{
		Scanner scan = new Scanner(System.in);


		System.out.println("Enter account type (saving/current) : ");
		String accountType = scan.nextLine();

		//get account type from user and create bankAccount object according to that.
		BankAccount bankAccount = BankAccountFactory.getBankAccount(accountType);

		//add account-holder details
		AccountHolder accountHolder = createAccountHolderDetails();		
		bankAccount.setAccountHolder(accountHolder);


		bankAccount.setAccountNumber( ( (BankCollectionImplementation)bank ).getGeneratedAccountNumber());

		System.out.println("Initial deposit amount : ");
		float amount = scan.nextFloat();


		bankAccount.setBalance(amount);

		//add new account into the collection of accounts
		if(ACCOUNTS.add(bankAccount)) 
			System.out.println("Account created with account Number :"+bankAccount.getAccountNumber());
		else
			System.out.println("Unable to create account/account already exists");

	}

	static void closeAccount()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("Enter the account number of account to be closed");

		String accountNumber = scan.nextLine();

		IBankAccount bankAccount = bank.searchAccount(accountNumber);


		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Bank account not exist");
		else
		{
			try 
			{
				bankAccount.withdraw(bankAccount.getBalance()); //withdraw the remaining amount in account.
				ACCOUNTS.remove(bankAccount);
			}
			catch (OperationFailureException e) 
			{
				e.printStackTrace();
			}
		}
		scan.close();	
	}

	//Administrator functions - end 

	//Transaction Handler functions - begin
	void doDeposit()
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scanner.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{
			System.out.print("Enter Amount to deposit into account : ");
			bankAccount.deposit(scanner.nextFloat());
		}
		scanner.close();
	}

	void doWithdraw()
	{
		Scanner scanner = new Scanner(System.in);

		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scanner.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{
			System.out.print("Enter Amount to withdraw from account : ");
			try 
			{
				bankAccount.withdraw(scanner.nextFloat());
			}
			catch (OperationFailureException e) 
			{
				System.out.println(e.getMessage());
			}
		}
		scanner.close();
	}

	void doFundTransfer()
	{
		Scanner scanner = new Scanner(System.in);
		
		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scanner.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{

			System.out.print("Enter the account number : ");
			IBankAccount payeeAccount = bank.searchAccount(scanner.nextLine());

			//check whether payee account exist or not. null value of payee account indicate that account with given account number doesn't exist. 
			if(payeeAccount==null)
				System.out.println("Sorry! payee's account with given account number not exist.");
			
			else{
				System.out.print("Enter Amount to be transfer to payee account : ");
				try 
				{
					
					bankAccount.transferFunds(payeeAccount, scanner.nextFloat());
				}
				catch (OperationFailureException e) 
				{
					System.out.println(e.getMessage());
				}
			}
			
		}
		
		scanner.close();
	}
	//Transaction Handler functions - end

	//menu function - begin
	static int getAdministratorMenu()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("************Administrator Menu************");		
		System.out.println("0.logout");
		System.out.println("1.open new bank account");
		System.out.println("2.close the bank account");
		System.out.println("Enter the choice (0 to 2) : ");
		int choice = scan.nextInt();
		scan.close();
		return choice;
	}

	static int getTransactionHandlerMenu()
	{
		Scanner scan = new Scanner(System.in);

		System.out.println("************Transaction Handler Menu************");			
		System.out.println("0.logout");
		System.out.println("1.Deposit Amount");
		System.out.println("2.Withdraw Amount");
		System.out.println("3.Transfer Amount");
		System.out.println("Enter the choice (0 to 3) : ");
		scan.close();
		return 0;
	}

	//menu function - end
	public static void main(String[] args) {



	}

}
