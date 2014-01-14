package exceptions;

public class TooSmallSystemException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TooSmallSystemException() {
		super("Zahlensystem ist zu klein, Zahl kann nicht dargestellt werden");
	}
}
