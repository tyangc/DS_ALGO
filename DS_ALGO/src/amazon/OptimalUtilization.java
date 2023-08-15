package amazon;
/*
 * https://leetcode.com/discuss/interview-question/373202/Amazon-or-OA-2019-or-Optimal-Utilization/391917
 * Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id and the second integer represents a value. Your task is to find an element from a and an element form b such that the sum of their values is less or equal to target and as close to target as possible. Return a list of ids of selected elements. If no pair is possible, return an empty list.

Example 1:

Input:
a = [[1, 2], [2, 4], [3, 6]]
b = [[1, 2]]
target = 7

Output: [[2, 1]]

Explanation:
There are only three combinations [1, 1], [2, 1], and [3, 1], which have a total sum of 4, 6 and 8, respectively.
Since 6 is the largest sum that does not exceed 7, [2, 1] is the optimal pair.
Example 2:

Input:
a = [[1, 3], [2, 5], [3, 7], [4, 10]]
b = [[1, 2], [2, 3], [3, 4], [4, 5]]
target = 10

Output: [[2, 4], [3, 2]]

Explanation:
There are two pairs possible. Element with id = 2 from the list `a` has a value 5, and element with id = 4 from the list `b` also has a value 5.
Combined, they add up to 10. Similarily, element with id = 3 from `a` has a value 7, and element with id = 2 from `b` has a value 3.
These also add up to 10. Therefore, the optimal pairs are [2, 4] and [3, 2].
Example 3:

Input:
a = [[1, 8], [2, 7], [3, 14]]
b = [[1, 5], [2, 10], [3, 14]]
target = 20

Output: [[3, 1]]
Example 4:

Input:
a = [[1, 8], [2, 15], [3, 9]]
b = [[1, 8], [2, 11], [3, 12]]
target = 20

Output: [[1, 3], [3, 2]]
 */
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.*;

public class OptimalUtilization {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int aLen = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
		
		List<List<Integer>> a = new ArrayList<>();
		
		for (int i=0; i<aLen; i++) {
			a.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
		}
		
		int bLen = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
		
		List<List<Integer>> b = new ArrayList<>();
		
		for (int i=0; i<bLen; i++) {
			b.add(splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList()));
		}
		
		int target = Integer.parseInt(scanner.nextLine());
		scanner.close();
		//System.out.println(target);
		for (List<Integer> lst : getPairs(a, b, target)) {
			System.out.println(lst);
		}
		
	}
	
	public static List<String> splitWords(String s) {
		return s.isEmpty() ? new ArrayList<String>() : Arrays.asList(s.split(" "));
	}
	
	public static List<List<Integer>> getPairs(List<List<Integer>> a, List<List<Integer>> b, int target) {
		
		if (a==null || a.size()==0 || b==null || b.size()==0) return new ArrayList<List<Integer>>();
		
		List<List<Integer>> res = new ArrayList<List<Integer>>();
		
		//Another way for Comparator
		Comparator<List<Integer>> cmp = Comparator.comparing(p->p.get(1));
		Collections.sort(a, cmp);
		Collections.sort(b, cmp);
		/*
		Collections.sort(a, new Comparator<List<Integer>>() {
			public int compare(List<Integer> a, List<Integer> b) {
				return a.get(1) - b.get(1);
			}
		});
		
		Collections.sort(b, new Comparator<List<Integer>>() {
			public int compare(List<Integer> a, List<Integer> b) {
				return a.get(1) - b.get(1);
			}
		});
		*/
		
		int i=0, j = b.size()-1, maxSum = Integer.MIN_VALUE;
		//boolean hasTarget = false;
		
		while(i<a.size() && j>=0) {
			if (a.get(i).get(1) + b.get(j).get(1) > target) {
				
				j--;
			} else {
				int cur = a.get(i).get(1) + b.get(j).get(1);
				if (cur>maxSum) {
					res.clear();
					
				}
				
				if (cur >= maxSum) {
					List<Integer> tmp = new ArrayList<>();
					tmp.add(a.get(i).get(0));
					tmp.add(b.get(j).get(0));
					res.add(tmp);
					maxSum = cur;
				}
				
				i++;
				
			}
			
			/*
			if (a.get(i).get(1) + b.get(j).get(1) == target) {
				
				List<Integer> tmp = new ArrayList<>();
				tmp.add(a.get(i).get(0));
				tmp.add(b.get(j).get(0));
				res.add(tmp);
				i++;
				j--;
			} else if (a.get(i).get(1) + b.get(j).get(1) > target) {
				j--;
			} else {
				
				i++;
			}
			*/
		}
		
		return res;
	}
	
	//Another implementation:
	public static List<List<Integer>> getPairs1(List<List<Integer>> a, List<List<Integer>> b, int target) {
        Comparator<List<Integer>> cmp = Comparator.comparing(p -> p.get(1));
        Collections.sort(a, cmp);
        Collections.sort(b, cmp.reversed());
        int maxSum = Integer.MIN_VALUE;
        ArrayList<List<Integer>> maxPairs = new ArrayList<>();
        for (int i = 0, j = 0; i < a.size() && j < b.size();) {
            List<Integer> x = a.get(i);
            List<Integer> y = b.get(j);
            int curSum = x.get(1) + y.get(1);
            if (curSum > target) {
                j++;
                continue;
            }
            if (curSum > maxSum) {
                maxSum = curSum;
                maxPairs.clear();
            }
            for (int k = j; k < b.size(); k++) {
                List<Integer> z = b.get(k);
                if (z.get(1) != y.get(1))
                    break;
                //maxPairs.add(List.of(x.get(0), z.get(0)));  //JDK 9 and above
            }
            i++;
        }
        return maxPairs;
    }


}
