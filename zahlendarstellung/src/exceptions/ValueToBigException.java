package exceptions;

public class ValueToBigException extends Exception {
	public ValueToBigException() {
		super("Wert �berschreitet den Wertebereich von Integer");
	}
}
