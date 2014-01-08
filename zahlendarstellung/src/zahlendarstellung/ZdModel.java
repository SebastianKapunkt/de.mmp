package zahlendarstellung;

import java.util.Observable;

public class ZdModel extends Observable {
	private String outputnumber, inputnumber;
	private int inputsystem, outputsystem;
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

	public String getInputnumber() {
		return inputnumber;
	}

	public void setInputnumber(String inputnumber) {
		this.inputnumber = inputnumber;
	}

	public int getInputsystem() {
		return inputsystem;
	}

	public void setInputsystem(int inputsystem) {
		this.inputsystem = inputsystem;
	}

	public int getOutputsystem() {
		return outputsystem;
	}

	public void setOutputsystem(int outputsystem) {
		this.outputsystem = outputsystem;
	}

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
	public int[] transformToArray(String inputnumber)
			throws WrongInputException {
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
	 * @throws ToSmallSystemException
	 */
	public void checknumber(int inputsystem) throws WrongNumberInputException,
			ToBigSystemException, ToSmallSystemException {
		for (int i = 0; i < number.length; i++) {
			if (number[i] >= inputsystem) {
				throw new WrongNumberInputException();
			}
			if (inputsystem > 36) {
				throw new ToBigSystemException();
			}
			if (inputsystem < 2) {
				throw new ToSmallSystemException();
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
	 * @throws ValueToBigException
	 */
	public int transformToTen(int inputnumbersystem) throws ValueToBigException {
		long erg = number[0];
		for (int i = 1; i < number.length; i++) {
			erg = erg * inputnumbersystem + number[i];
			if (erg > Integer.MAX_VALUE) {
				throw new ValueToBigException();
			}
		}
		return (int) erg;
	}

	/**
	 * Methode zum umwandeln einer Zahl aus dem 10ner System in ein belibiges
	 * System. z.B.: 16 zu 10000 (2er)
	 * 
	 * @param number
	 * @param outputnumbersystem
	 * @return Zielsystem Zahl
	 * @throws ToBigSystemException
	 * @throws ToSmallSystemException
	 */
	public void transformFromTen(int number, int outputnumbersystem)
			throws ToBigSystemException, ToSmallSystemException {
		StringBuffer outnumber = new StringBuffer();
		if (outputnumbersystem > 36) {
			throw new ToBigSystemException();
		}
		if (outputnumbersystem < 2) {
			throw new ToSmallSystemException();
		}

		while (number != 0) {
			outnumber.append(convertToSymbol(number % outputnumbersystem));
			number = number / outputnumbersystem;
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