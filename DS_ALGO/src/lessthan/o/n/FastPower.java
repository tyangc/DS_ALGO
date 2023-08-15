package lessthan.o.n;
/*
 * https://www.lintcode.com/problem/140/
 * 
 * (a + b)%c = (a%c + b%c)%c
 * (a - b)%c = (a%c - b%c)%c   //(a -b)%c = ((a%c - b%c)%c + c)%c
 * (a * b)%c = (a%c * b%c)%c
 */
public class FastPower {
	public int fastPower(int a, int b, int n) {
        // write your code here
        if (b==1) return 0;
        if (n == 0) return 1;
        if (n == 1) return a%b;

        long product = fastPower(a, b, n/2);

        long res = product * product % b;
        if (n%2 == 1) res =  (res * a % b) % b;

        return (int)res;

    }
	
	//Better way of :
	
	public int fastPower1(int a, int b, int n) {
        // write your code here
        //if (b==1) return 0;
        if (n == 0) return 1%b;
        if (n == 1) return a%b;

        long product = fastPower(a, b, n/2);

        product = product * product % b;

        if ( n % 2 == 1) product = (product * a) %b;
        //long res = product * product % b;
        //if (n%2 == 1) res =  (res * a % b) % b;

        return (int) product;

    }
	
	//Using non-recursive:
	public int fastPower2(int a, int b, int n) {
		
		if (n==0) return 1%b;
        long ans = 1, tmp = a;

        while(n != 0) {
            if (n%2 == 1) {
                ans = (ans * tmp) % b;
            }

            tmp = (tmp * tmp) % b;
            n = n/2;
        }

        return (int)ans;
	}
}
