package exceptions;

public class NoInputFoundException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NoInputFoundException() {
		super("ein oder mehrere Eingabefelder sind leer");
	}
}
