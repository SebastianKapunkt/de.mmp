package Euklid;

import java.util.Observable;
//test Daethyx1337 PC
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
		int zw = 1;
		while(zw!=0){
			zw = a%b;
			a = b;
			b = zw;
		}
		ggt = a;
	      setChanged();
	      notifyObservers();
	}
}
