package de.fhb.kryptografie;

import java.util.Observable;


public class KgModel extends Observable{

	private String cypherText = new String("");
	private String deCypherText = new String("");

	
	public String getCypherText() {
		return cypherText;
	}


	public void setCypherText(String cypherText) {
		this.cypherText = cypherText;
	}


	public String getDeCypherText() {
		return deCypherText;
	}


	public void setDeCypherText(String deCypherText) {
		this.deCypherText = deCypherText;
	}

	public void transform() {
		StringBuilder myText = new StringBuilder("");
		
		System.out.println(text);
		System.out.println(text.length());
		
		for (int i = 0; i < text.length(); i++) {
			if(text.charAt(i)>96 && text.charAt(i)<123){
				myText.append((char) (text.charAt(i)-32));
			}
			if(text.charAt(i)>64 && text.charAt(i)<91){
				myText.append(text.charAt(i));
			}
		}
		
		if(what == true){
			cypherText = myText.toString();
		}else{
			deCypherText = myText.toString();
		}
		System.out.println(myText);
	}
}
