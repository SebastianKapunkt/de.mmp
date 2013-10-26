package de.fhb.kryptografie;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Observable;

import de.fhb.kryptografie.exceptions.WrongNumberFormatException;


public class KgModel extends Observable{

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
			if(text.charAt(i)>96 && text.charAt(i)<123){
				myText.append((char) (text.charAt(i)-32));
			}
			if(text.charAt(i)>64 && text.charAt(i)<91){
				myText.append(text.charAt(i));
			}
			if(text.charAt(i) == 'ä' || text.charAt(i) == 'Ä'){
				myText.append("AE");
			}
			if(text.charAt(i) == 'ö' || text.charAt(i) == 'Ö'){
				myText.append("OE");
			}
			if(text.charAt(i) == 'ü' || text.charAt(i) == 'Ü'){
				myText.append("UE");
			}
			if(text.charAt(i) == 'ß'){
				myText.append("SS");
			}
		}
		
		plainText = myText.toString();
		
		return myText.toString();
	}


//	public void decipher(String transformed,String key) {
//				
//	}

	public void encipher(String transformed,int key) {
		StringBuilder myText = new StringBuilder("");
		
		for (int i = 0; i < transformed.length(); i++) {
			if(((char)transformed.charAt(i)+key) <= 90){
				myText.append((char) (transformed.charAt(i)+key));
			}else if(((char)transformed.charAt(i)+key)>=90){
				myText.append((char) (transformed.charAt(i)+key-26));
			}else{
				myText.append((char) (transformed.charAt(i)+key+26));
			}
		}
		
		cipherText = myText.toString();
		
		setChanged();
	    notifyObservers();
	}


	public int calculateKey(String text) throws WrongNumberFormatException {
		int key = 0;
		key = (int) text.charAt(0);
		if(key > 90 || key < 65){
			throw new WrongNumberFormatException();
		}else{
			key = key - 65;
		}
		return key;
	}


	public void openFile(File selectedFile) throws IOException {
		//Öffnen und in einen String schreiben
		fileReader = new FileReader(selectedFile);
		bufferedReader = new BufferedReader(fileReader);
		StringBuilder myString = new StringBuilder();
		
		do{ 
			myString.append(bufferedReader.readLine());
		}while( bufferedReader.readLine() != null );		
		
		bufferedReader.close();
		
		plainText = myString.toString();
		
		setChanged();
		notifyObservers();
	}
}
