package zahlendarstellung;

public class NoInputFoundException extends Exception {
	public NoInputFoundException() {
		super("ein oder mehrere Eingabefelder sind leer");
	}
}
