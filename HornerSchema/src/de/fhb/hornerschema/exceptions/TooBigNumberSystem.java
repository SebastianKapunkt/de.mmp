package de.fhb.hornerschema.exceptions;

public class TooBigNumberSystem extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public TooBigNumberSystem() {
		super("Zahlensystem gr��er als 16");
	}
}
