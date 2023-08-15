package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * https://algo.monster/problems/amazon_oa_find_all_combination_of_numbers_sum_to_target
 * A customer wants to buy a pair of jeans, a pair of shoes, a skirt, and a top but has a limited budget in dollars. Given different pricing options for each product, 
 * determine how many options our customer has to buy 1 of each product. You cannot spend more money than the budgeted amount.

Example
priceOfJeans = [2, 3]
priceOfShoes = [4]
priceOfSkirts = [2, 3]
priceOfTops = [1, 2]
budgeted = 10

The customer must buy shoes for 4 dollars since there is only one option. 
This leaves 6 dollars to spend on the other 3 items. Combinations of prices paid for jeans, skirts, and tops respectively 
that add up to 6 dollars or less are [2, 2, 2], [2, 2, 1], [3, 2, 1], [2, 3, 1]. There are 4 ways the customer can purchase all 4 items.

Function Description

Complete the getNumberOfOptions function in the editor below. The function must return an integer which represents the number of options present to buy the four items.

getNumberOfOptions has 5 parameters:
int[] priceOfJeans: An integer array, which contains the prices of the pairs of jeans available.
int[] priceOfShoes: An integer array, which contains the prices of the pairs of shoes available.
int[] priceOfSkirts: An integer array, which contains the prices of the skirts available.
int[] priceOfTops: An integer array, which contains the prices of the tops available.
int dollars: the total number of dollars available to shop with.

Constraints

1 ≤ a, b, c, d ≤ 103
1 ≤ dollars ≤ 109
1 ≤ price of each item ≤ 109
Note: a, b, c and d are the sizes of the four price arrays
 */
public class ShoppingOptions {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
        List<Integer> a = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> b = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> c = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        List<Integer> d = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        
        int[] a1 = a.stream().mapToInt(i->i).toArray(); 
        int[] b1 = b.stream().mapToInt(i->i).toArray(); 
        int[] c1 = c.stream().mapToInt(i->i).toArray(); 
        int[] d1 = d.stream().mapToInt(i->i).toArray(); 
        
        
        int limit = Integer.parseInt(scanner.nextLine());
        scanner.close();
        int res = getNumberOfOptions(a1, b1, c1, d1, limit);
        System.out.println(res);
	}
	
	public static List<String> splitWords(String s) {
        return s.isEmpty() ? new ArrayList<String>() : Arrays.asList(s.split(" "));
    }
	
	
	public static int getNumberOfOptions(int[] a, int[] b, int[] c, int[] d, int budget) {
		
		/* //brutal force , miss the combination point
		List<List<Integer>> res = new ArrayList<>();
		
		int curSum = 0;
		
		for (int i=0; i<a.length; i++) {
			curSum = a[i];
			for (int j=0; j<b.length; j++) {
				curSum += b[j];
				if (curSum>budget) continue;
				for (int n=0; n<c.length; n++) {
					curSum += c[n];
					if (curSum>budget) continue;
					for (int m=0; m<d.length; m++) {
						curSum += d[m];
						if (curSum <= budget) {
							List<Integer> tmp = new ArrayList<>();
							tmp.add(a[i]);
							tmp.add(b[j]);
							tmp.add(c[n]);
							tmp.add(d[m]);
							res.add(tmp);
						}
					}
				}
			}
		}
		*/
		List<List<Integer>> res = new ArrayList<>();
		dfs(a, b, c, d, budget, new ArrayList<Integer>(), res);
		
		
		for (List<Integer> list : res) {
			System.out.println(list);
		}
		return res.size();
	}

	private static void dfs(int[] a, int[] b, int[] c, int[] d, int budget, List<Integer> tmp, List<List<Integer>> res) {
		if (tmp.size() == 4 ) {
			if (listSum(tmp)<=budget) {
				res.add(new ArrayList<>(tmp));
			}
			return;
		}
		
		int[] cur = a;
		
		//if (tmp.size()==0) cur = a;
		if (tmp.size()==1) cur = b;
		if (tmp.size()==2) cur = c;
		if (tmp.size()==3) cur = d;
		
		for (int i=0; i<cur.length; i++) {
			tmp.add(cur[i]);
			dfs(a, b, c, d, budget, tmp, res);
			tmp.remove(tmp.size()-1);
				
		}
				
		//helper(a, b, c, d, budget, tmp, res);
		
	}
	
	private static void helper(int[] a, int[] b, int[] c, int[] d, int budget, List<Integer> tmp, List<List<Integer>> res) {
		int[] cur = a;
		
		if (tmp.size()==0) cur = a;
		if (tmp.size()==1) cur = b;
		if (tmp.size()==2) cur = c;
		if (tmp.size()==3) cur = d;
		
		for (int i=0; i<cur.length; i++) {
			tmp.add(cur[i]);
			dfs(a, b, c, d, budget, tmp, res);
			tmp.remove(tmp.size()-1);
				
		}
		
	}
	
	private static int listSum(List<Integer> list) {
		int ret = 0;
		for (int i : list) {
			ret += i;
		}
		return ret;
	}
}
