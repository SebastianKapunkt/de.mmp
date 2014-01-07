package zahlendarstellung;

public class ZdModel {

	public static void main(String args[]) {
		String inputnumber = "FABCDE";
		int inputnumbersystem = 16;
		int outputnumbersystem = 2;
		String outputnumber = "0";
		int number[] = new int[inputnumber.length()];

		number = transformToArray(inputnumber, number);
		System.out.println("array: ");
		ausgabenumber(number);
		checknumber(number, inputnumbersystem);
		int zw = transformToTen(number, inputnumbersystem);
		System.out.println("zw: " + zw);
		outputnumber = transformFromTen(zw, outputnumbersystem);
		System.out.println("outputnumber: " + outputnumber);
	}

	private static String transformFromTen(int number, int outputnumbersystem) {
		StringBuffer outnumber = new StringBuffer();
		int erg = number;
		while (erg != 0) {
			outnumber.append(erg % outputnumbersystem);
			erg = erg / outputnumbersystem;
		}
		return outnumber.reverse().toString();
	}

	private static int transformToTen(int[] number, int inputnumbersystem) {
		int erg = number[0];
		for (int i = 1; i < number.length; i++) {
			erg = erg * inputnumbersystem + number[i];
		}
		return erg;
	}

	private static void ausgabenumber(int[] number) {
		for (int i = 0; i < number.length; i++) {
			System.out.println(number[i]);
		}

	}

	private static int[] transformToArray(String inputnumber, int[] number) {
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
				System.out.println("Ungueltige eingabe");
			}
		}
		return number;
	}

	public static void checknumber(int[] number, int system) {
		for (int i = 0; i < number.length; i++) {
			if (number[i] >= system) {
				System.out.println("Error");
			}
		}
	}
}