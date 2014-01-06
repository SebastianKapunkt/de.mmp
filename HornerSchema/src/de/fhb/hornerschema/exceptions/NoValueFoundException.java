package de.fhb.hornerschema.exceptions;

public class NoValueFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoValueFoundException() {
		super("Nicht alle Zahlen eingegeben");
	}
}
