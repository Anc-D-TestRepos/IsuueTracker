package org.training.issueTracker.service.exceptions;

public class DAOException extends Exception {


	private static final long serialVersionUID = 1L;
	private String message;
	
	public DAOException() {
		super();
		
	}

	public DAOException(String message, Throwable cause) {
		super(cause);
		this.message = message;
	}

	public DAOException(String message) {
		this.message = message;
	}

	public DAOException(Throwable cause) {
		super(cause);

	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

}
