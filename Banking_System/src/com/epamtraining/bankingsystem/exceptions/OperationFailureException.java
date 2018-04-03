package com.epamtraining.bankingsystem.exceptions;


/*
 * Custom exception to convey the failure in bank transaction.
 */
public class OperationFailureException extends Exception {

	
	
	
	public OperationFailureException(String message) {
		super("Operation failed : "+message);
	}

	public OperationFailureException(Throwable throwable) {
		super(throwable);
	}

	@Override
	public String getMessage() {
		return super.getMessage();
	}

}
