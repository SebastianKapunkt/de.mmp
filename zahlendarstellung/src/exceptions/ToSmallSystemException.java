package exceptions;

public class ToSmallSystemException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ToSmallSystemException() {
		super("Zahlensystem ist zu klein, Zahl kann nicht dargestellt werden");
	}
}
