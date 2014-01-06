package de.fhb.hornerschema;

import java.util.Observable;

import de.fhb.hornerschema.exceptions.TooBigNumberSystem;
import de.fhb.hornerschema.exceptions.WrongNumberFormatException;
import de.fhb.hornerschema.exceptions.integerOverflow;

public class HornerModel extends Observable {

	private String inputString;
	private String outputString;
	
	HornerUtilities myUtilities = new HornerUtilities();

	public void transformStringToNumbersystem(String b1, String stringNumber,
											  String b2)
											  throws integerOverflow,
											  WrongNumberFormatException,
											  TooBigNumberSystem {
		if (b1.equals(b2))
			outputString = stringNumber;
		else {
			myUtilities.eliminateLetters(b1, stringNumber, b2);
			outputString = String.valueOf( myUtilities.convertIntToNewSystem());
		}
		setChanged();
		notifyObservers();
	}
	
	public String getInputString() {
		return inputString;
	}
	
	public void setInputString(String inputString) {
		this.inputString = inputString;
	}
	
	public String getOutputString() {
		return outputString;
	}
}
