package Euklid;

import java.util.Observable;

public class EuDModel extends Observable {
		
	int a, b, ggt;	
	
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getGgt() {
		return ggt;
	}
	
	public EuDModel(int a, int b) {
		super();
		this.a = a;
		this.b = b;
	}

	public EuDModel() {
		super();
		a = 0;
		b = 0;
	}

	public  void ggt() {
		ggt = 0;
	      setChanged();
	      notifyObservers();
	}
}
