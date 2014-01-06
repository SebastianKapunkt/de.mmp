package de.fhb.hornerschema;

import de.fhb.hornerschema.exceptions.TooBigNumberSystem;
import de.fhb.hornerschema.exceptions.WrongNumberFormatException;
import de.fhb.hornerschema.exceptions.integerOverflow;

public class HornerTest {
	
	static HornerUtilities myUtilities = new HornerUtilities();
	
	public static void main (String args[]) {
		String b1 = "16";
		String b2 = "2";
		String number = "abcdefg";
		
		//Normaler Programmablauf, keine Fehler
		try {
			System.out.println("b1: " +b1 + " number: " +number  + " b2: " +b2);
			System.out.println(myUtilities.eliminateLetters("10", "123", "2"));
			
			System.out.println("b1: 16 " + "number: 123abcdef " + "b2: 2");
			System.out.println(myUtilities.eliminateLetters("16", "123abcdef", "2"));
			
			
		} catch (integerOverflow | WrongNumberFormatException
				| TooBigNumberSystem e) {
			e.printStackTrace();
		}
		
//			System.out.println("b1: 10 " + "number: 123a " + "b2: 2");
//			System.out.println(myUtilities.convertToNewNumberSystem("10", "123a", "2"));
//			
//			System.out.println("b1: 1 " + "number: 123abc " + "b2: 2");
//			System.out.println(myUtilities.convertToNewNumberSystem("1", "123abc", "2"));
	}

}
