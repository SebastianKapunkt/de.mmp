package de.fhb.hornerschema.exceptions;

public class integerOverflow extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public integerOverflow () {
	
	super("Number bigger than IntMax");
	}
}
