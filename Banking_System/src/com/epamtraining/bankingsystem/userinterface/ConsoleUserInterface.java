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
	final static String ACCOUNTMANAGERUSERNAME = "AM123";
	final static String ACCOUNTMANAGERPASSWORD = "AMPASS123";
	final static String TRANSACTIONMANAGERUSERNAME = "TM123";
	final static String TRANSACTIONMANAGERPASSWORD = "TMPASS123";
	
	Scanner scan;
	public ConsoleUserInterface() {
		 scan = new Scanner(System.in);
	}
	
	final static Set<IBankAccount> ACCOUNTS = new HashSet<>();

	static IBank bank = new BankCollectionImplementation(BANKNAME, BANKIFSC, ACCOUNTS);

	AccountHolder createAccountHolderDetails()
	{
		
		System.out.println("Enter account holder's detail as mentioned \n");
		System.out.println("Full Name : ");
		String fullName = scan.nextLine();

		System.out.println("UID(Adhar) Number : ");
		long uidNumber = scan.nextLong();

		scan.nextLine();
		System.out.println("Date of Birth in the format yyyy-mm-dd : ");
		Date dateOfBirth = Date.valueOf(scan.nextLine());

		Address address = createAddressDetails();
		return new AccountHolder(fullName, uidNumber, dateOfBirth, address);
	}

	Address createAddressDetails()
	{
		
		System.out.println("Enter following address Details: ");

		System.out.println("Street : ");
		String street = scan .nextLine();

		System.out.println("City : ");
		String city = scan.nextLine();

		System.out.println("State : ");
		String state = scan.nextLine();

		System.out.println("Zip Code : ");
		int zipCode = scan.nextInt();

		return new Address(street, city, state, zipCode);
	}

	//Administrator functions - begin

	
	void openAccount()
	{


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

	void closeAccount()
	{
		
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
	}

	//Administrator functions - end 

	//Transaction Handler functions - begin
	void doDeposit()
	{
		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scan.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{
			System.out.print("Enter Amount to deposit into account : ");
			bankAccount.deposit(scan.nextFloat());
			System.out.println("Ammount deposited successfully!\n Current Balance :"+bankAccount.getBalance());
		}
	}

	void doWithdraw()
	{

		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scan.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{
			System.out.print("Enter Amount to withdraw from account : ");
			try 
			{
				bankAccount.withdraw(scan.nextFloat());

				System.out.println("Ammount withdrawn successfully!\n Current Balance :"+bankAccount.getBalance());
			}
			catch (OperationFailureException e) 
			{
				System.out.println(e.getMessage());
			}
		}
	}

	void doFundTransfer()
	{	
		System.out.print("Enter the account number : ");
		IBankAccount bankAccount = bank.searchAccount(scan.nextLine());

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{

			System.out.print("Enter the account number : ");
			IBankAccount payeeAccount = bank.searchAccount(scan.nextLine());

			//check whether payee account exist or not. null value of payee account indicate that account with given account number doesn't exist. 
			if(payeeAccount==null)
				System.out.println("Sorry! payee's account with given account number not exist.");
			
			else{
				System.out.print("Enter Amount to be transfer to payee account : ");
				try 
				{
					
					bankAccount.transferFunds(payeeAccount, scan.nextFloat());
					System.out.println("Ammount transffered successfully!\n Current Balance :"+bankAccount.getBalance());
				}
				catch (OperationFailureException e) 
				{
					System.out.println(e.getMessage());
				}
			}
			
		}
	}
	
	void checkBalance()
	{	
		System.out.print("Enter the account number : ");
		String accountNumber = scan.nextLine();
		IBankAccount bankAccount = bank.searchAccount(accountNumber);

		//check whether account exist or not. null value of bank account indicate that account with given account number doesn't exist. 
		if(bankAccount==null)
			System.out.println("Sorry! Account with given account number not exist.");
		else
		{
			System.out.println(bankAccount); 
		}
	}
	
	//Transaction Handler functions - end

	
	
	
	
	
	//menu functions - begin
	int getAccountManagerMenu()
	{
		System.out.println("************Account Manager Menu************");		
		System.out.println("0.logout");
		System.out.println("1.open new bank account");
		System.out.println("2.close the bank account");
		System.out.println("3.print details of all bank accounts");
		System.out.println("Enter the choice (0 to 3) : ");
		int choice = scan.nextInt();
		scan.nextLine();
		return choice;
	}

	int getTransactionManagerMenu()
	{
		System.out.println("************Transaction Manager Menu************");			
		System.out.println("0.logout");
		System.out.println("1.Deposit Amount");
		System.out.println("2.Withdraw Amount");
		System.out.println("3.Transfer Amount");
		System.out.println("4.Check Balance");
		System.out.println("Enter the choice (0 to 4) : ");
		int choice = scan.nextInt();
		scan.nextLine();
		return choice;
	}
	
	void printAllAccounts()
	{
		for (IBankAccount bankAccount : ACCOUNTS) {
			System.out.println(bankAccount+", account type : "+bankAccount.getClass().getSimpleName());
		}
	}
	
	void manageAccounts() //account menu handler
	{
		int choice;
		while( ( choice = getAccountManagerMenu() ) != 0)
		{
			switch (choice) {
			case 1:openAccount();
				break;
			case 2:closeAccount();
				break;
			case 3:printAllAccounts();
				break;	
			default:
				System.out.println("Wrong choice! Please try again with choice between 0-3.");
				break;
			}
		}
		getMainMenu(); //back to main menu when user enters 0.
	}
	
	void doTransactions() // transaction menu handler
	{
		int choice = 0;
		while( ( choice = getTransactionManagerMenu() ) != 0)
		{
			switch (choice) {
			case 1:	doDeposit();
				break;
			case 2:	doWithdraw();
				break;
			case 3:	doFundTransfer();
				break;
			case 4:	checkBalance();
				break;
			default:
				System.out.println("Wrong choice! Please try again with choice between 0-4.");
				break;
			}			
		}
		getMainMenu(); //back to main menu when user enters 0.
	}
	
	
	
	int getMainMenu() //to display the main menu.
	{
		System.out.println("************Main Menu************");			
		System.out.println("0.Exit");
		System.out.println("1.Login");
		System.out.print("Enter the choice (0 or 1) : ");
		int choice = scan.nextInt();
		scan.nextLine();
		return choice;
	}

	void doLogin() //bank employee login handler function.
	{
		System.out.print("Enter Username : ");
		String username = scan.nextLine();
		System.out.print("Enter Password : ");
		String password = scan.nextLine();
		
		if (username.equalsIgnoreCase(ACCOUNTMANAGERUSERNAME) && password.equals(ACCOUNTMANAGERPASSWORD)) {
			this.manageAccounts();
		}
		else if (username.equalsIgnoreCase(TRANSACTIONMANAGERUSERNAME) && password.equals(TRANSACTIONMANAGERPASSWORD)) {
			this.doTransactions();
		}	
		else
			System.out.println("Wrong credentials.");
	}
	//menu function - end
	public static void main(String[] args) {
		int choice;
		ConsoleUserInterface cui = new ConsoleUserInterface();
		while( (choice = cui.getMainMenu() )!= 0)
		{
			switch (choice) {
			case 1:cui.doLogin();
				break;

			default:
				break;
			}
		}
	}

}
