package exceptions;

public class ToSmallSystemException extends Exception {
	public ToSmallSystemException() {
		super("Zahlensystem ist zu klein, Zahl kann nicht dargestellt werden");
	}
}
