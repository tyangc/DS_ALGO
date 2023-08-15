package lessthan.o.n;

public class GreatestCommonDivisor {

	public static void main(String[] args) {
        int a = Integer.parseInt(args[0]);
        int b = Integer.parseInt(args[1]);
        // write your code here
        // please print the greatest common divisor of a and b
        int ans = a>b ? gcd(a, b) : gcd(b, a);
        System.out.println(ans);
        
    }

    private static int gcd(int a, int b) {
        if (a%b==0) {
            return b;
        } else {
            return gcd(b, a%b);
        }
    }

}
