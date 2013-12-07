/** @author:
 * Eric Dobritius 	 20110009
 * Gordan Just		 20091313 
 * Sebastian Kindt 	 20120023
 * 
 * @version: 1.0
 */
package de.fhb.kryptografie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Hashtable;
import java.util.Observable;

import de.fhb.kryptografie.exceptions.WrongNumberFormatException;

public class KgModel extends Observable {

	/**
	 * Deklaration der Variablen
	 */
	private String plainText = new String("");
	private String cipherText = new String("");
	private String key;
	private Hashtable<Character, Integer> signCount = new Hashtable<Character, Integer>();
	private Hashtable<Character, Double> FREQUENCYTABLE = new Hashtable<Character, Double>();
	private char trychar = 'e';
	private int alphabetlength = 26;

	/**
	 * IO-Elemente zum arbeiten mit Files
	 */
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	/**
	 * Getter für Key, PlainText, CypherText und signCount
	 */
	public String getKey() {
		return key;
	}

	public String getPlainText() {
		return plainText;
	}

	public String getCypherText() {
		return cipherText;
	}

	public Hashtable<Character, Integer> getSignCount() {
		return signCount;
	}

	/**
	 * Setter für Key, PlainText, CypherText und signCount
	 */
	public void setCypherText(String cypherText) {
		this.cipherText = cypherText;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setSignCount(Hashtable<Character, Integer> keyfrequent) {
		this.signCount = keyfrequent;
	}

	/**
	 * Die Methode transfrom(String) bearbeitet einen String so, dass nach
	 * Bearbeitung alle Sonderzeichen, Leerzeichen und Zahlen entfernt werden.
	 * Sowie Ä Ö Ü ß in ae oe ue ss umgewandelt: Es werden nur noch klein und
	 * Großbuchstaben in den String geschrieben.
	 * 
	 * @param text
	 * @return myText
	 */
	public String transform(String text) {

		StringBuilder myText = new StringBuilder("");

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) > 96 && text.charAt(i) < 123) {
				myText.append(text.charAt(i));
			}
			if (text.charAt(i) > 64 && text.charAt(i) < 91) {
				myText.append((char) (text.charAt(i) + 32));
			}
			if (text.charAt(i) == 'ä' || text.charAt(i) == 'Ä') {
				myText.append("ae");
			}
			if (text.charAt(i) == 'ö' || text.charAt(i) == 'Ö') {
				myText.append("oe");
			}
			if (text.charAt(i) == 'ü' || text.charAt(i) == 'Ü') {
				myText.append("ue");
			}
			if (text.charAt(i) == 'ß') {
				myText.append("ss");
			}
		}

		return myText.toString();
	}

	/**
	 * Die Methode calcutateKey bearbeitet den eingegebenen Key. Es werden nur
	 * Eingaben von Buchstaben akzeptiert. Der String wird bei angenommenen
	 * Eingaben in Chars zerlegt und um einen Wert verringert der dem Abstand
	 * von 0 bis zum a bzw A in der Asci-Tabelle entspricht. Um so an späterer
	 * Stelle besser mit dem String arbeiten zu können.
	 * 
	 * @param text
	 * @return key
	 * @throws WrongNumberFormatException
	 */
	public String calculateKey(String text) throws WrongNumberFormatException {
		StringBuilder key = new StringBuilder("");

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) >= 65 && text.charAt(i) <= 90) {
				key.append((char) (text.charAt(i) - 65));
			} else if ((text.charAt(i) >= 97 && text.charAt(i) <= 122)) {
				key.append((char) (text.charAt(i) - 97));
			} else {
				throw new WrongNumberFormatException();
			}
		}
		this.key = text.toUpperCase();
		return key.toString();
	}

	/**
	 * Die Methode openFile öffnet wie der Name schon sagt eine Datei. Je nach
	 * dem mit welchem File-chooser diese Methode aufgerufen wird, wird auch der
	 * Inhalt der Datei in dem entsprechenden Feld übergeben.
	 * 
	 * @param selectedFile
	 * @param plain
	 * @throws IOException
	 */
	public void openFile(File selectedFile, boolean plain) throws IOException {
		fileReader = new FileReader(selectedFile);
		bufferedReader = new BufferedReader(fileReader);
		StringBuilder myString = new StringBuilder();

		do {
			myString.append(bufferedReader.readLine());
		} while (bufferedReader.readLine() != null);

		bufferedReader.close();

		if (plain == true) {
			plainText = myString.toString();
			cipherText = "";
		} else {
			plainText = "";
			cipherText = myString.toString();
		}
		setChanged();
		notifyObservers();
	}

	/**
	 * Die Methode enciher chiffriert einen String mit einem gegebenen Key auf
	 * der Basis des Vigenère Verfahrens.
	 * 
	 * @param transformed
	 * @param key
	 */
	public void encipher(String transformed, String key) {
		StringBuilder myText = new StringBuilder("");
		int j = 0;

		for (int i = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			if ((transformed.charAt(i) + key.charAt(j)) <= 122) {
				myText.append((char) (transformed.charAt(i) + key.charAt(j)));
			}
			if (((char) transformed.charAt(i) + key.charAt(j)) > 122) {
				myText.append((char) (transformed.charAt(i) + key.charAt(j) - alphabetlength));
			}
		}
		cipherText = myText.toString().toUpperCase();
		plainText = transformed;

		setChanged();
		notifyObservers();
	}

	/**
	 * Die Methode decipher dechiffriert einen String mit einem gegebenen Key
	 * auf der Basis des Vigenère Verfahrens.
	 * 
	 * @param transformed
	 * @param key
	 */
	public void decipher(String transformed, String key) {
		StringBuilder myText = new StringBuilder("");
		int j = 0;

		for (int i = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			if ((transformed.charAt(i) - key.charAt(j)) >= 97) {
				myText.append((char) (transformed.charAt(i) - key.charAt(j)));
			}
			if ((transformed.charAt(i) - key.charAt(j)) < 97) {
				myText.append((char) ((transformed.charAt(i) - key.charAt(j)) + alphabetlength));
			}
		}

		plainText = myText.toString().toLowerCase();
		cipherText = transformed.toUpperCase();

		setChanged();
		notifyObservers();
	}

	/**
	 * Die Methode decipherCaesar entschlüsselt eine Text mit der Schlüssellänge
	 * 1 auf Basis des Caesar Verfahrens
	 * 
	 * @param transform
	 */
	public void decipherCaesar(String transform, boolean flagM)
			throws WrongNumberFormatException {
		// Wenn flagM == true, dann wird das verfahren neu begonnen.
		// flagM == false --> wird der nächst wahrscheinlichst Buchstabe
		// genommen.
		if (flagM) {
			trychar = 'e';
			clearHashtable();

			for (int i = 0; i < transform.length(); i++) {
				signCount.put(transform.charAt(i),
						signCount.get(transform.charAt(i)) + 1);
			}
			decipher(
					transform,
					calculateKey(((char) calculatedifference(
							getMostSign(signCount), trychar)) + ""));
		} else {
			trychar = nextTryChar(trychar);
			decipher(
					transform,
					calculateKey(((char) calculatedifference(
							getMostSign(signCount), trychar)) + ""));
		}
	}

	private int calculatedifference(char ms, char trychar) {
		int value = (int) ms - trychar;

		if ((97 + value) < 97) {
			value = 97 + value + alphabetlength;
		} else {
			value = 97 + value;
		}
		return value;
	}

	private char nextTryChar(char trychar) {
		Double max = FREQUENCYTABLE.get(trychar);
		double newmax = 0;
		char sign = 'e';
		for (int i = 0; i < alphabetlength; i++) {
			if (FREQUENCYTABLE.get((char) ('a' + i)) < max
					&& FREQUENCYTABLE.get((char) ('a' + i)) > newmax) {
				newmax = FREQUENCYTABLE.get((char) (97 + i));
				sign = (char) (97 + i);
			}
		}
		return sign;
	}

	private char getMostSign(Hashtable<Character, Integer> signCount2) {
		int max = 0;
		char sign = 'e';
		for (int i = 0; i < alphabetlength; i++) {
			if (signCount2.get((char) (97 + i)) > max) {
				max = signCount2.get((char) (97 + i));
				sign = (char) (97 + i);
			}
		}
		return sign;
	}

	private void clearHashtable() {
		for (int i = 0; i < alphabetlength; i++) {
			signCount.put((char) (97 + i), 0);
		}
	}

	public void decipherVigenere(String transform) {
		// TODO Auto-generated method stub
	}

	public void generateConstSignProbability() {
		FREQUENCYTABLE.put('a', 6.51);
		FREQUENCYTABLE.put('b', 1.89);
		FREQUENCYTABLE.put('c', 3.06);
		FREQUENCYTABLE.put('d', 5.08);
		FREQUENCYTABLE.put('e', 17.40);
		FREQUENCYTABLE.put('f', 1.66);
		FREQUENCYTABLE.put('g', 3.01);
		FREQUENCYTABLE.put('h', 4.76);
		FREQUENCYTABLE.put('i', 7.55);
		FREQUENCYTABLE.put('j', 0.27);
		FREQUENCYTABLE.put('k', 1.21);
		FREQUENCYTABLE.put('l', 3.44);
		FREQUENCYTABLE.put('m', 2.53);
		FREQUENCYTABLE.put('n', 9.78);
		FREQUENCYTABLE.put('o', 2.51);
		FREQUENCYTABLE.put('p', 0.79);
		FREQUENCYTABLE.put('q', 0.02);
		FREQUENCYTABLE.put('r', 7.00);
		FREQUENCYTABLE.put('s', 7.27);
		FREQUENCYTABLE.put('t', 6.15);
		FREQUENCYTABLE.put('u', 4.35);
		FREQUENCYTABLE.put('v', 0.67);
		FREQUENCYTABLE.put('w', 1.89);
		FREQUENCYTABLE.put('x', 0.03);
		FREQUENCYTABLE.put('y', 0.04);
		FREQUENCYTABLE.put('z', 1.13);
	}

}
