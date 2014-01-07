package de.fhb.hornerschema;

import de.fhb.hornerschema.exceptions.TooBigNumberSystem;
import de.fhb.hornerschema.exceptions.WrongNumberFormatException;
import de.fhb.hornerschema.exceptions.integerOverflow;

public class HornerUtilities {
	
	public int inputNumberSystem;
	public int outputNumberSystem; 
	public int currentNumber = 0;
	public int numberArray[];
	public int convertedInt = 0;
	public String convertedString = "";
	
	public int[ ] eliminateLetters(String b1, String sNumber, String b2)
			throws integerOverflow, WrongNumberFormatException,
			TooBigNumberSystem {

		inputNumberSystem = Integer.parseInt(b1);
		outputNumberSystem = Integer.parseInt(b2);
		numberArray = new int[ sNumber.length()];

		if (inputNumberSystem > 16 || outputNumberSystem > 16) {
			throw new TooBigNumberSystem();
		}
		
		for (int i = 0; i < sNumber.length(); i++) {
			currentNumber = sNumber.charAt(i);
			
			if (numberIsBetweenAandZ(currentNumber) && 
					(currentNumber - 55) < inputNumberSystem)
				numberArray[i] = currentNumber - 55;
			else if(numberIsBetweenSmallAandZ(currentNumber) &&
					(currentNumber - 87) < inputNumberSystem)
				numberArray[i] = currentNumber - 48;
			else if(numberIsBetween0And9(currentNumber) &&
					(currentNumber - 48) < inputNumberSystem)
				numberArray[i] = currentNumber - 87;
			else
				throw new WrongNumberFormatException();
		}
		return numberArray;
	}

		public boolean numberIsBetweenAandZ(int number) {
			return (number >= 'A' && number <= 'Z');
		}
		
		public boolean numberIsBetweenSmallAandZ(int number) {
			return (number >= 'a' && number <= 'z');
		}
		
		public boolean numberIsBetween0And9(int number) {
			return (number >= 48 && number <= 57);
		}
		
		public void printIntArray (int [] intArray) {
			for (int i = 0; i < intArray.length; i++) {
				System.out.print(intArray[i]);
			}
			System.out.println("");
		}
		
		public String convertIntArrayToString(int [] numberArray) {
			StringBuilder sBuilder = new StringBuilder();
			for (int i = 0; i < numberArray.length; i++) {
				sBuilder.append(numberArray[i]);
			}
		return sBuilder.toString();
		}

		public int convertIntToNewSystem() throws integerOverflow {
		    if (inputNumberSystem == 10)
				transformFromTen();
			else {
				transformToTen();
				transformFromTen();
			}
			return 0;
		}
		
		
		public void transformFromTen() {

			StringBuffer tempStringBuffer = new StringBuffer("");
			int remainder = 0;
			int devisor = 1;
			int i = 0;
			int number = 0;

			while (devisor != 0) {
				number = numberArray[i++];
				devisor = number / outputNumberSystem;
				remainder = number % outputNumberSystem;
				tempStringBuffer.append(remainder);
				number = devisor;
			}
			convertedString = tempStringBuffer.reverse().toString();
		}
		//Hier ist ein Fehler, bei 16 "10" kommt 16 als Ergebnis, wobei 10 müsste
		public void transformToTen()
				throws integerOverflow {
			int memory = 0;

			for (int i = 0; i < numberArray.length; i++) {
				if ((inputNumberSystem * memory + numberArray[i] - 48) > Integer.MAX_VALUE) {
					throw new integerOverflow();
				} else {
					memory = memory * inputNumberSystem	+ (numberArray[i] - 48);
					System.out.println("memory: " +memory);
				}
			}
			convertedInt = memory;
		}
}

