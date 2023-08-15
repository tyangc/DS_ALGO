package lessthan.o.n;

import java.util.ArrayList;
import java.util.List;

/*
 * https://www.lintcode.com/problem/235/
 */
public class PrimeFactorization {
	public List<Integer> primeFactorization(int num) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        int up = (int)Math.sqrt(num);

        for (int k=2; k<=up && num>1; k++) {
            while(num%k==0) {
                num /= k;
                res.add(k);
            }
        }

        if (num>1) res.add(num);
        return res;
    }
}
