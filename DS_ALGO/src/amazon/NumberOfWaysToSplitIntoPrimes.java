package amazon;
/*
 * Given a string made up of integers 0 to 9, count the number of ways to split the string into prime numbers in the range of 2 to 1000 inclusive, using up all the characters in the string.

Each prime number, pn, must not contain leading 0s, and 2 <= pn <= 1000.

Constraints
The input string does not contain any leading 0s.

Examples
Example 1:
Input: "31173"
Output: 6
Explanation:
The string "31173" can be split into prime numbers in 6 ways:

[3, 11, 7, 3]
[3, 11, 73]
[31, 17, 3]
[31, 173]
[311, 7, 3]
[311, 73]
 * https://algo.monster/problems/amazon_oa_num_ways_to_split_into_primes
 */

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class NumberOfWaysToSplitIntoPrimes {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<String>> res = split("3117353701", 2, 1000);
		
		for (List<String> lst : res) {
			System.out.println(lst);
		}
	}
	
	private static List<List<String>> split(String s, int n, int m) {
		List<String> primes = findPrimes(n, m);
		//System.out.println(primes);
		List<List<String>> res = new ArrayList<>();
		dfs(s, new ArrayList<>(), res, primes);
		return res;
	}
	
	private static void dfs(String s, List<String> tmp, List<List<String>> res, List<String> primes) {
		int slen = s.length();
		if (slen==0) {
			res.add(new ArrayList<>(tmp));
			return;
		}
		
		if (s.startsWith("0")) return;
		
		for (String p : primes) {
			int plen = p.length();
			if (plen>slen) break;
			
			if (s.startsWith(p)) {
				tmp.add(p);
				dfs(s.substring(plen), tmp, res, primes);
				tmp.remove(tmp.size()-1);
			}
		}
	}
	
	private static List<String> findPrimes(int n, int m) {
		Set<Integer> res = new TreeSet<>();
		
		for (int i=n; i<=m; i++) {
			isPrime(i, res);
		}
		
		//System.out.println(res);
		//return res.stream().map(String :: valueOf).collect(Collectors.toSet());  //It won't keep the original order
		List<String> ret = new LinkedList<>();   
		for (int k : res) {
			ret.add(String.valueOf(k));
		}
		
		return ret;
	}
	
	private static void isPrime(int n, Set<Integer> primeSet) {
		if (n==2) {
			primeSet.add(2);
			return;
		}
		
		int m = (int) Math.ceil(Math.sqrt(n));
		
		for (int i=2; i<=m; i++) {
			if (n%i == 0) return ;
			
		}
		
		primeSet.add(n);
		
		
	}
		

}
