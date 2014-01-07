package zahlendarstellung;

public class ZdModel {

	public static void main(String args[]) {
		String inputnumber = "1";
		int inputnumbersystem = 1;
		int outputnumbersystem = 10;
		String outputnumber = "0";
		int number[] = new int[inputnumber.length()];

		// Array erzeugen
		number = transformToArray(inputnumber, number);
		System.out.println("array: ");
		ausgabenumber(number);

		// Überprüfen auf ungültige Zeichen
		checknumber(number, inputnumbersystem);

		// von einem belibigen system ins 10ner System Transformieren
		int zw = transformToTen(number, inputnumbersystem);
		System.out.println("zw: " + zw);

		// Vom 10ner in ein belibiges System umwandeln
		outputnumber = transformFromTen(zw, outputnumbersystem);
		System.out.println("outputnumber: " + outputnumber);
	}

	/**
	 * Zerlegt die ausgangs Zahl in ein Integer-Array und filter ungültige
	 * Zeichen heraus. z.B.: &, % oder =
	 * 
	 * Alsrückgabe wird ein int Array gefüllt mit den umgewandelten Werten
	 * geliefert.
	 * 
	 * @param inputnumber
	 * @param number
	 * @return int array
	 */
	private static int[] transformToArray(String inputnumber, int[] number) {
		for (int i = 0; i < inputnumber.length(); i++) {
			if (inputnumber.charAt(i) >= 'a' && inputnumber.charAt(i) <= 'z') {
				number[i] = inputnumber.charAt(i) - 'a' + 10;
			} else if (inputnumber.charAt(i) >= 'A'
					&& inputnumber.charAt(i) <= 'Z') {
				number[i] = inputnumber.charAt(i) - 'A' + 10;
			} else if (inputnumber.charAt(i) >= '0'
					&& inputnumber.charAt(i) <= '9') {
				number[i] = inputnumber.charAt(i) - '0';
			} else {
				System.out.println("Ungueltige eingabe");
			}
		}
		return number;
	}

	// Nur zur überprüfung für das spätere programm nicht notwendig
	private static void ausgabenumber(int[] number) {
		for (int i = 0; i < number.length; i++) {
			System.out.println(number[i]);
		}

	}

	/**
	 * Überprüft das Array mit dem inhalt der einzelnen Zeichen der Zahl ob
	 * Zeichen enthalten sind die in dem System nicht zulässig sind.
	 * 
	 * @param number
	 * @param system
	 */
	public static void checknumber(int[] number, int system) {
		for (int i = 0; i < number.length; i++) {
			if (number[i] >= system) {
				System.out.println("Error");
			}
		}
	}

	/**
	 * Methode zum umwandeln einer Zahl aus einem belibigen Zahlensystem in das
	 * 10ner System. z.B.: hex: F zu dez: 15 Unter der Verwendung des
	 * Honorschemas.
	 * 
	 * @param number
	 * @param inputnumbersystem
	 * @return
	 */
	private static int transformToTen(int[] number, int inputnumbersystem) {
		int erg = number[0];
		for (int i = 1; i < number.length; i++) {
			erg = erg * inputnumbersystem + number[i];
		}
		return erg;
	}

	/**
	 * Methode zum umwandeln einer Zahl aus dem 10ner System in ein belibiges
	 * System. z.B.: 16 zu 10000 (2er)
	 * 
	 * @param number
	 * @param outputnumbersystem
	 * @return
	 */
	private static String transformFromTen(int number, int outputnumbersystem) {
		StringBuffer outnumber = new StringBuffer();
		int erg = number;
		while (erg != 0) {
			outnumber.append(erg % outputnumbersystem);
			erg = erg / outputnumbersystem;
		}
		return outnumber.reverse().toString();
	}
}