package exceptions;

public class NoValueFoundException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public NoValueFoundException() {
		super("Kein Inhalt gefunden");
	}
}
