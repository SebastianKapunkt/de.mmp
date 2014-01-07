package de.fhb.hornerschema.exceptions;

public class WrongNumberFormatException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public WrongNumberFormatException() {
		super("Falsche Eingabe, Zahl größer als Zahlensystem");
	}
}
