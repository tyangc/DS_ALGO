package twopointers.samedirection;

public class SumOfTwoStrings {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new SumOfTwoStrings().SumofTwoStrings("987654", "876543"));
	}

	 public String SumofTwoStrings(String A, String B) {
	        // write your code here

	        if (A==null||A.length()==0) return B;

	        if (B==null||B.length()==0) return A;

	        int n = A.length(), m = B.length();

	        int i=n-1, j = m-1;
	        StringBuilder sb = new StringBuilder();

	        int r = 0, d = 0, a=0, b=0;
	        while(i>=0 || j >=0) {
	           
	            a = i<0? 0 : A.charAt(i) - '0';
	            b = j<0? 0 : B.charAt(j) - '0';

	            System.out.println(a+"|"+b + "|" +r);

	            d = (a+b+r) % 10;  //d has to be ahead of r otherwise the result is wrong
	            r = (a+b+r) / 10;
	            

	            System.out.println(r+"$"+d);


	            sb.insert(0, d);
	            i--;
	            j--;
	        }

	        if (r>0) {
	            sb.insert(0, r);
	        }

	        return sb.toString();
	 }
	 
	 /*
	  343. Sum of Two Strings

Given you two strings which are only contain digit character. You need to return a string spliced by the sum of the bits.

Example
Example1:
Input:
A = "99"
B = "111"
Output: "11010"
Explanation: because 9 + 1 = 10, 9 + 1 = 10, 0 + 1 = 1,connect them，so answer is "11010"
Example2:
Input:
A = "2"
B = "321"
Output: "323"
Explanation: because 2 + 1 = 3, 2 + 0 = 2, 3 + 0 = 3, connect them，so answer is "323"
Notice
A and B are strings which are composed of numbers
	  * 
	  */
	 public String SumofTwoStringsSpliced(String A, String B) {
	        // write your code here

	        if (A==null||A.length()==0) return B;

	        if (B==null||B.length()==0) return A;

	        int n = A.length(), m = B.length();

	        int i=n-1, j = m-1;
	        StringBuilder sb = new StringBuilder();

	        int   a=0, b=0;
	        while(i>=0 || j >=0) {
	           
	            a = i<0? 0 : A.charAt(i) - '0';
	            b = j<0? 0 : B.charAt(j) - '0';

	           // System.out.println(a+"|"+b + "|" +r);

	            //d = (a+b+r) % 10;
	            //r = (a+b+r) / 10;
	            

	            //System.out.println(r+"$"+d);


	            sb.insert(0, a+b);
	            i--;
	            j--;
	        }

	        /*
	        if (r>0) {
	            sb.insert(0, r);
	        }
	        */

	        return sb.toString();
	    }
}
