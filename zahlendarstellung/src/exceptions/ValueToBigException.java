package exceptions;

public class ValueToBigException extends Exception {
	public ValueToBigException() {
		super("Wert überschreitet den Wertebereich von Integer");
	}
}
