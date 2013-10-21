package Euklid.Exception;

/**
 * Exception zur Beschreibung des Falles, dass negativen Zahlen eingegeben wurden
 *
 */
public class NegativeNumberException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public NegativeNumberException() {
		super("Negative Zahl(en)");
	}
}
