package exceptions;

public class ValueToBigException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ValueToBigException() {
		super("Wert überschreitet den Wertebereich von Integer");
	}
}
