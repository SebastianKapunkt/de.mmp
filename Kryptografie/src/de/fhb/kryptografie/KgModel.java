package de.fhb.kryptografie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import de.fhb.kryptografie.exceptions.WrongNumberFormatException;

public class KgModel extends Observable {

	private String plainText = new String("");
	private String cipherText = new String("");
	private FileReader fileReader;
	private BufferedReader bufferedReader;

	public String getCypherText() {
		return plainText;
	}

	public void setCypherText(String plainText) {
		this.plainText = plainText;
	}

	public String getDeCypherText() {
		return cipherText;
	}

	public void setDeCypherText(String cypherText) {
		this.cipherText = cypherText;
	}

	public String transform(String text) {

		StringBuilder myText = new StringBuilder("");

		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) > 96 && text.charAt(i) < 123) {
				myText.append((char) (text.charAt(i) - 32));
			}
			if (text.charAt(i) > 64 && text.charAt(i) < 91) {
				myText.append(text.charAt(i));
			}
			if (text.charAt(i) == '�' || text.charAt(i) == '�') {
				myText.append("AE");
			}
			if (text.charAt(i) == '�' || text.charAt(i) == '�') {
				myText.append("OE");
			}
			if (text.charAt(i) == '�' || text.charAt(i) == '�') {
				myText.append("UE");
			}
			if (text.charAt(i) == '�') {
				myText.append("SS");
			}
		}

		plainText = myText.toString();

		return myText.toString();
	}

	public void encipher(String transformed, String key) {
		StringBuilder myText = new StringBuilder("");
		int j = 0;

		for (int i = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			if ((transformed.charAt(i) + key.charAt(j)) <= 90) {
				myText.append((char) (transformed.charAt(i) + key.charAt(j)));
			} else if (((char) transformed.charAt(i) + key.charAt(j)) >= 90) {
				myText.append((char) (transformed.charAt(i) + key.charAt(j) - 26));
			} else {
				myText.append((char) (transformed.charAt(i) + key.charAt(j) + 26));
			}
		}

		cipherText = myText.toString();

		setChanged();
		notifyObservers();
	}

	public String calculateKey(String text) throws WrongNumberFormatException {
		StringBuilder key = new StringBuilder();
		for (int i = 0; i < text.length(); i++) {
			if (text.charAt(i) < 65 || text.charAt(i) > 90) {
				throw new WrongNumberFormatException();
			} else {
				key.append((char) (text.charAt(i) - 64));
			}
		}
		return key.toString();
	}

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

	public void decipher(String transformed, String key) {
		StringBuilder myText = new StringBuilder("");
		int j = 0;

		for (int i = 0; i < transformed.length(); i++, j++) {
			if (j == key.length()) {
				j = 0;
			}
			if ((transformed.charAt(i) - key.charAt(j)) >= 65) {
				myText.append((char) (transformed.charAt(i) - key.charAt(j)));
			} else if (((char) transformed.charAt(i) - key.charAt(j)) <= 65) {
				myText.append((char) (transformed.charAt(i) - key.charAt(j) + 26));
			} else {
				myText.append((char) (transformed.charAt(i) - key.charAt(j) - 26));
			}
		}

		plainText = myText.toString();

		setChanged();
		notifyObservers();
	}
}
