package zahlendarstellung;

import java.util.Observable;

public class ZdModel extends Observable {
	private String outputnumber;
	private int number[];

	public String getOutputnumber() {
		return outputnumber;
	}

	public void setOutputnumber(String outputnumber) {
		this.outputnumber = outputnumber;
	}

	public int[] getNumber() {
		return number;
	}

	public void setNumber(int[] number) {
		this.number = number;
	}

	// public static void main(String args[]) {
	// String inputnumber = "ABCDEF";
	// int inputnumbersystem = 35;
	// int outputnumbersystem = 35;
	// String outputnumber = "0";
	// int number[] = new int[inputnumber.length()];
	//
	// // Array erzeugen
	// number = transformToArray(inputnumber, number);
	// System.out.println("array: ");
	// ausgabenumber(number);
	//
	// // Überprüfen auf ungültige Zeichen
	// checknumber(number, inputnumbersystem);
	//
	// // von einem belibigen system ins 10ner System Transformieren
	// int zw = transformToTen(number, inputnumbersystem);
	// System.out.println("zw: " + zw);
	//
	// // Vom 10ner in ein belibiges System umwandeln
	// outputnumber = transformFromTen(zw, outputnumbersystem);
	// System.out.println("outputnumber: " + outputnumber);
	// }
	public ZdModel() {
		outputnumber = "";
	}

	/**
	 * Zerlegt die ausgangs Zahl in ein Integer-Array und meldet ungültige
	 * Zeichen. z.B.: &, % oder = --> Error
	 * 
	 * Als Rückgabe wird ein int Array, gefüllt mit den umgewandelten Werten ,
	 * geliefert.
	 * 
	 * @param inputnumber
	 * @return int array
	 * @throws WrongInputException 
	 */
	public int[] transformToArray(String inputnumber) throws WrongInputException {
		number = new int[inputnumber.length()];
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
				throw new WrongInputException();
			}
		}
		return number;
	}

	/**
	 * Überprüft das Array mit dem inhalt der einzelnen Zeichen der Zahl ob
	 * Zeichen enthalten sind die in dem System nicht zulässig sind. Und wirft
	 * bei bedarf einen Error
	 * 
	 * @param inputsystem
	 * @throws WrongNumberInputException 
	 * @throws ToBigSystemException 
	 */
	public void checknumber(int inputsystem) throws WrongNumberInputException, ToBigSystemException {
		for (int i = 0; i < number.length; i++) {
			if (number[i] >= inputsystem) {
				throw new WrongNumberInputException();
			}
			if(inputsystem > 36){
				throw new ToBigSystemException();
			}
		}
	}

	/**
	 * Methode zum umwandeln einer Zahl aus einem belibigen Zahlensystem in das
	 * 10ner System. z.B.: hex: F zu dez: 15 Unter der Verwendung des
	 * Honorschemas.
	 * 
	 * @param inputnumbersystem
	 * @return Zahl im Zehnersystem
	 */
	public int transformToTen(int inputnumbersystem) {
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
	 * @return Zielsystem Zahl
	 * @throws ToBigSystemException 
	 */
	public void transformFromTen(int number, int outputnumbersystem) throws ToBigSystemException {
		StringBuffer outnumber = new StringBuffer();
		if(outputnumbersystem > 36){
			throw new ToBigSystemException();
		}
		int erg = number;
		while (erg != 0) {
			outnumber.append(convertToSymbol(erg % outputnumbersystem));
			erg = erg / outputnumbersystem;
		}

		outputnumber = outnumber.reverse().toString();
		setChanged();
		notifyObservers();
	}

	/**
	 * Wandelt den int Wert einer Zahl in ein Zeichen um. z.B.: 15 zu F
	 * 
	 * @param i
	 * @return umgewandeltes Zeichen
	 */
	public static char convertToSymbol(int i) {
		char symbol;
		if (i > 9) {
			symbol = (char) (i + 55);
		} else {
			symbol = (char) (i + 48);
		}
		return symbol;
	}
}