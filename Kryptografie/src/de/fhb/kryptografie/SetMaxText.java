/*
 * 
 * Author: http://forum.chip.de/java/jtextfield-eingabezeichen-begrenzen-834905.html#post5186423
 * 
 */

package de.fhb.kryptografie;
import javax.swing.text.PlainDocument;
import javax.swing.text.*;

public class SetMaxText extends PlainDocument {
 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int limit;
		// optional uppercase conversion
	private boolean toUppercase = false;
	  
	SetMaxText(){
		super();
		this.limit = 10;
		this.toUppercase = true;
	}
	
	SetMaxText(int limit) {
		super();
		this.limit = limit;
	}
	   
	SetMaxText (int limit, boolean upper) {
	   super();
	   this.limit = limit;
	   toUppercase = upper;
	}
	 
	public void insertString (int offset, String  str, AttributeSet attr) throws BadLocationException {
	   if (str == null) return;
	
	   if ((getLength() + str.length()) <= limit) {
		   	if (toUppercase) str = str.toUpperCase();
		   		super.insertString(offset, str, attr);
	     	}
	   }
}
