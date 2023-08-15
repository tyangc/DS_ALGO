package binarysearch;

public class SquareRoot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(sqrt(2, null));
	}

	public static double sqrt(int t, Double precision) {
		
		if (t<0) {
			throw new RuntimeException("Negative integer doesn't have square root.");
		}
		
		int i=0;
		for ( ; i<=t; i++) {
			if (i*i==t) return i;
			
			if (i*i>t) break;
			
		}
		
		double low=i-1, high=i, prec = precision!=null?precision:1e-7;
		double middle;

		while(high-low>prec) {
			middle=(high+low)/2;
			
			if (middle*middle>t) {
				high=middle;
			} else {
				low=middle;
			}
		}
		
		return (high+low)/2;
		
	}
}
