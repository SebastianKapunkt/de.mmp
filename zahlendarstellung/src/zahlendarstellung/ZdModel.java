/** @author:
 * Eric Dobritius 	 20110009
 * Gordan Just		 20091313 
 * Sebastian Kindt 	 20120023
 * 
 * @version: 1.0
 */
package zahlendarstellung;

import java.util.Observable;

import exceptions.TooBigSystemException;
import exceptions.TooSmallSystemException;
import exceptions.ValueToBigException;
import exceptions.WrongInputException;
import exceptions.WrongNumberInputException;

public class ZdModel extends Observable {
	private String outputnumber, inputnumber;
	private int inputsystem, outputsystem;
	private int number[];
	private int modPot, a, n, m;

	public int getA() {
		return a;
	}

	public void setA(int a) {
		this.a = a;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public int getM() {
		return m;
	}

	public void setM(int m) {
		this.m = m;
	}

	public int getModPot() {
		return modPot;
	}

	public void setModPot(int modPot) {
		this.modPot = modPot;
		setChanged();
		notifyObservers();
	}

	public String getOutputnumber() {
		return outputnumber;
	}

	public void setOutputnumber(String outputnumber) {
		this.outputnumber = outputnumber;
		setChanged();
		notifyObservers();
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
	 * Zerlegt die ausgangs Zahl(String) in ein Integer-Array und meldet ungültige
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
	 * bei bedarf eine Exception.
	 * 
	 * @param inputsystem
	 * @throws WrongNumberInputException
	 * @throws TooBigSystemException
	 * @throws TooSmallSystemException
	 */
	public void checknumber(int inputsystem) throws WrongNumberInputException,
			TooBigSystemException, TooSmallSystemException {
		for (int i = 0; i < number.length; i++) {
			if (number[i] >= inputsystem) {
				throw new WrongNumberInputException();
			}
			if (inputsystem > 36) {
				throw new TooBigSystemException();
			}
			if (inputsystem < 2) {
				throw new TooSmallSystemException();
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
	public int toDecimalSystem(int inputnumbersystem) throws ValueToBigException {
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
	 * @param outputNumberSystem
	 * @return Zielsystem Zahl
	 * @throws TooBigSystemException
	 * @throws TooSmallSystemException
	 */
	public String fromDecimalSystem(int number, int outputNumberSystem)
			throws TooBigSystemException, TooSmallSystemException {
		StringBuffer outNumber = new StringBuffer();
		
		if (outputNumberSystem > 36) {
			throw new TooBigSystemException();
		}
		if (outputNumberSystem < 2) {
			throw new TooSmallSystemException();
		}

		if (number == 0) {
			outNumber.append('0');
		} else {

			while (number != 0) {
				outNumber.append(convertToSymbol(number % outputNumberSystem));
				number = number / outputNumberSystem;
			}

		}
		return outNumber.reverse().toString();
	}

	/**
	 * Wandelt den int Wert einer Zahl in ein Zeichen um. z.B.: 15 zu F
	 * 
	 * @param i
	 * @return umgewandeltes Zeichen
	 */
	private char convertToSymbol(int i) {
		char symbol;
		if (i > 9) {
			symbol = (char) (i + 55);
		} else {
			symbol = (char) (i + 48);
		}
		return symbol;
	}

	// MODULARE POTENZ FUNKTIONEN
	/**
	 * Invertiert einen String.
	 * 
	 * @param binExp
	 * @return String.reverse
	 */
	public String stringReverse(String binExp) {
		StringBuilder revbinExp = new StringBuilder();
		for (int i = 0; i < binExp.length(); i++) {
			revbinExp.append(binExp.charAt(i));
		}
		return revbinExp.reverse().toString();
	}

	/**
	 * Berechnet die Modulare Potenz
	 * 
	 * @param modulo
	 * @param base
	 * @param binaryExponent
	 * @return Ergebnis
	 * @throws WrongInputException
	 * @throws ValueToBigException
	 * @return Modulare Potenz
	 */
	public int modularePotenz(int modulo, int base, String binaryExponent)
			throws WrongInputException, ValueToBigException {
		int[] potenzValues = new int[binaryExponent.length() + 1];
		int[] factors = new int[binaryExponent.length() + 1];
		potenzValues[0] = 1;
		factors[0] = base;

		if (base < 0 || modulo <= 0 || n < 0) {
			throw new WrongInputException();
		} else {
			for (int i = 0; i < binaryExponent.length(); i++) {
				if (i == 0) {
					factors[i + 1] = factors[i];
				} else if ((long) Math.pow(factors[i], 2) > Integer.MAX_VALUE) {
					throw new ValueToBigException();
				} else {
					factors[i + 1] = (int) Math.pow(factors[i], 2) % modulo;
				}
				if (binaryExponent.charAt(i) == '0') {
					potenzValues[i + 1] = potenzValues[i];
				} else {
					potenzValues[i + 1] = potenzValues[i] * factors[i + 1] % modulo;
				}
			}
		}

		return potenzValues[binaryExponent.length()];
	}
}