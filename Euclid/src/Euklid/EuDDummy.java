package Euklid;


public class EuDDummy {
	
	boolean test(double x, int n){
		double y = 0;
		boolean b;
		for (int i = 0; i < n; i++) y = y + Math.sqrt(x);
		if  (y > 10) b = true; 
		else b = false;
		return b;
	}

}
