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
	// number: enhält die distance mit der verschlüsselt wurde.
	private int number = 0;
	// currentText: merkt sich den aktuellen Text zum vergleich ob mit einem
	// anderen gearbeitet wird
	private String currentText = "";
	// currentmin: enthält die aktuell niedrigste und somit Wahrscheinlichste
	// measure-distance.
	private double currentmin = 0;
	// ALPHABETLENGTH: constante die die anzahl Zeichen der "Sprache" enhält.
	// Deutsch: 26 Zeichen a-z
	private int ALPHABETLENGTH = 26;
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
	public void setKey(String key) {
		this.key = key;
	}

	public void setPlainText(String plainText) {
		this.plainText = plainText;
	}

	public void setCypherText(String cypherText) {
		this.cipherText = cypherText;
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
	 * Die Methode cipher chiffriert einen String mit einem gegebenen Key auf
	 * der Basis des Vigenère Verfahrens.
	 * 
	 * @param transformed
	 * @param key
	 */
	public void cipher(String transformed, String key) {
		StringBuilder myText = new StringBuilder("");

		for (int i = 0, j = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			myText.append((char) ((transformed.charAt(i) - 97 + key.charAt(j)) % 26 + 97));
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

		for (int i = 0, j = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			myText.append((char) ((26 - key.charAt(j) + transformed.charAt(i) - 97) % 26 + 97));
		}

		plainText = myText.toString().toLowerCase();
		cipherText = transformed.toUpperCase();

		setChanged();
		notifyObservers();
	}

	/**
	 * Die Methode decipherCaesar entschlüsselt einen Text mit der
	 * Schlüssellänge 1, auf Basis des Caesar Verfahrens. Der Schlüssel ist
	 * nicht bekannt.
	 * 
	 * @param transform
	 */
	public void decipherCaesar(String transform)
			throws WrongNumberFormatException {
		boolean flagM = true;
		// Wenn flagM == true, dann wird das verfahren neu begonnen.
		// flagM == false --> dann wird der nächst wahrscheinlichst Buchstabe
		// genommen.

		// Überprüft Ob ein neuer Versuch gestartet wurde oder
		// ein anderer chiffre Text eingegeben wurde
		// anhand des Textes der im String transform steht
		if (currentText.equals(transform)) {
			flagM = false;
		}

		if (flagM) {
			// Wird auf null gesetzt damit die Wahrscheinlichkeiten wieder von
			// vorne durchgegagnen werden können.
			number = 0;
			// Setzt den currentText gleich dem Aktuellen Text mit dem
			// gearbeitet wird
			currentText = transform;
			// Setzt currentmin auf den Anfangswert zurück
			currentmin = 0;
			// leert die Hashtable Signcount, bzw. setzt sie zurück auf 0.
			clearHashtable();

			// liest die zeichen des Textes von transform in signCount ein
			for (int i = 0; i < transform.length(); i++) {
				signCount.put(transform.charAt(i),
						signCount.get(transform.charAt(i)) + 1);
			}

			// ruft die entschlüsselungsmethde decipher auf mit
			// transform und dem berechneten Schlüssel
			decipher(
					transform,
					calculateKey(nextTryChar(generateFrequency(transform
							.length())) + ""));
		} else {

			// ruft die entschlüsselungsmethde decipher auf mit
			// transform und dem berechneten Schlüssel
			decipher(
					transform,
					calculateKey(nextTryChar(generateFrequency(transform
							.length())) + ""));
		}
	}

	/**
	 * generateFrequency berechnet die Distanz von den Wahrscheinlichkeiten des
	 * vorkommens eines Buchstabens in der Deutschen Sprache und in dem Zu
	 * entschlüsselnden Texts nach der Formel: Summe( | ha(c) - hb(c) | ) und
	 * gibt diese in einem Array zurück.
	 * 
	 * @param length
	 * @return double Array measuredistance
	 */
	private double[] generateFrequency(int length) {
		double[] measuredistance = new double[26];
		measuredistance = definingMeasuredistance(measuredistance);
		char z, y;
		for (int j = 0; j < ALPHABETLENGTH; j++) {
			for (int i = 0; i < ALPHABETLENGTH; i++) {
				z = (char) (97 + i);
				y = (char) (z + j);
				measuredistance[j] = measuredistance[j]
						+ Math.abs(((double) signCount.get(z) / length)
								* 100
								- FREQUENCYTABLE
										.get((char) ((y - 97) % 26 + 97)));
			}
		}
		return measuredistance;
	}

	/**
	 * Bestimmt den Buchstaben mit dem als nächstes Entschlüsselt wird. Gibt
	 * einen Buchstaben zurück.
	 * 
	 * @param double Array easuredistance
	 * @return (char)
	 */
	private char nextTryChar(double[] measuredistance) {
		number = highestProbability(measuredistance);
		return (char) (97 + (26 - number) % 26);
	}

	/**
	 * Berechnet die minimale Distanz um die höchste Wahrscheinlichkeit
	 * auszuwählen Unterbeachtung des vohrigen minimalen Wertes
	 * 
	 * @param measuredistance
	 * @return position
	 */
	private int highestProbability(double[] measuredistance) {
		double min = Double.MAX_VALUE;
		int position = 0;
		// bestimmen der Obergrenze
		double maxvalue = maxvalue(measuredistance);

		// bestimmen des minimalen wertes
		for (int i = 0; i < measuredistance.length; i++) {
			if (measuredistance[i] > currentmin && measuredistance[i] < min) {
				min = measuredistance[i];
				position = i;
			}
		}
		// Bedingung damit wieder von vorne bestimmt wird
		if (measuredistance[position] == maxvalue) {
			currentmin = 0;
		} else {
			currentmin = min;
		}
		return position;
	}

	/**
	 * Bestimmt aus einem Double Array den maximalen Wert.
	 * 
	 * @param measuredistance
	 * @return max
	 */
	private double maxvalue(double[] measuredistance) {
		double max = 0;
		for (int i = 0; i < measuredistance.length; i++) {
			if (measuredistance[i] > max) {
				max = measuredistance[i];
			}
		}
		return max;
	}

	/**
	 * Deklariert eine Hashtabel mit 0 für a-z
	 * 
	 * @param measuredistance
	 * @return
	 */
	private double[] definingMeasuredistance(double[] measuredistance) {
		for (int i = 0; i < measuredistance.length; i++) {
			measuredistance[i] = 0;
		}
		return measuredistance;
	}

	/**
	 * Setz die Werte der Hashtable auf 0 für a-z
	 */
	private void clearHashtable() {
		for (int i = 0; i < ALPHABETLENGTH; i++) {
			signCount.put((char) (97 + i), 0);
		}
	}

	/**
	 * Deklarieren eine Hastabel für a-z mit den Wahrscheinlichkeiten des
	 * vorkmmen eines Buchstaben in der deutschen Sprache
	 */
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

	// Vorbereiteter Quellcode zur Erweiterung von Vigenere entschlüsseln
	// public void decipherVigenere(String transform) {
	// // TODO Auto-generated method stub
	// }
}