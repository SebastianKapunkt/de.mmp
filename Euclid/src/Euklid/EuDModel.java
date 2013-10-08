package Euklid;

import java.util.Observable;

import Euklid.Exception.NegativeNumberException;

public class EuDModel extends Observable {
		
	int a, b, zw, ggt, x, y;	
	
	public void setA(int a) {
		this.a = a;
	}

	public void setB(int b) {
		this.b = b;
	}

	public int getGgt() {
		return ggt;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public EuDModel(int a, int b){
		super();
		
		this.a = a;
		this.b = b;
	}

	public EuDModel() {
		super();
		a = 0;
		b = 0;
	}
/**
 * BRAUCHT KEINER MEHR
 * @throws NegativeNumberException
 * @throws ArithmeticException
 */
//	public  void ggt() {
//		if(a!= 0 && b == 0){
//			b = a;
//			a = 0;
//		}
//		ggt = 0;
//		zw = 1;
//		while(zw!=0){
//			zw = a%b;
//			a = b;
//			b = zw;
//		}
//		ggt = a;
//	      setChanged();
//	      notifyObservers();
//	}
	
	public void ggt() throws NegativeNumberException, ArithmeticException{
		int rk=0, rk1, rk2, q, xk=0, xk1=0, xk2=1, yk=0, yk1=1, yk2=0;
		
		if(a!= 0 && b == 0){
			b = a;
			a = 0;
		}
		if (a == 0 && b == 0){
			throw new ArithmeticException(); 
		} else if (a < 0 || b < 0){
			throw new NegativeNumberException();
		} else {
			rk2=a;
			rk1=b;
		}
		
		do {
			rk = rk2 % rk1;
			q = rk2 / rk1;
			xk = xk2 - q * xk1;
			yk = yk2 - q * yk1;
			rk2 = rk1;
			rk1 = rk;
			xk2 = xk1;
			xk1 = xk;
			yk2 = yk1;
			yk1 = yk;
		} while (rk != 0);
		
		ggt = rk2;
		x = xk2;
		y = yk2;
		setChanged();
	    notifyObservers();
	}
}
