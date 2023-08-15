package amazon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;
import java.util.stream.Collectors;

/*
 * https://algo.monster/problems/amazon_oa_number_game
 * https://leetcode.com/problems/maximize-score-after-n-operations/
 * 
 1799. Maximize Score After N Operations
Hard

339

32

Add to List

Share
You are given nums, an array of positive integers of size 2 * n. You must perform n operations on this array.

In the ith operation (1-indexed), you will:

Choose two elements, x and y.
Receive a score of i * gcd(x, y).
Remove x and y from nums.
Return the maximum score you can receive after performing n operations.

The function gcd(x, y) is the greatest common divisor of x and y.

 

Example 1:

Input: nums = [1,2]
Output: 1
Explanation: The optimal choice of operations is:
(1 * gcd(1, 2)) = 1
Example 2:

Input: nums = [3,4,6,8]
Output: 11
Explanation: The optimal choice of operations is:
(1 * gcd(3, 6)) + (2 * gcd(4, 8)) = 3 + 8 = 11
Example 3:

Input: nums = [1,2,3,4,5,6]
Output: 14
Explanation: The optimal choice of operations is:
(1 * gcd(1, 5)) + (2 * gcd(2, 4)) + (3 * gcd(3, 6)) = 1 + 4 + 9 = 14
 

Constraints:

1 <= n <= 7
nums.length == 2 * n
1 <= nums[i] <= 106
Accepted
10,857
Submissions
23,459
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
10
Find every way to split the array until n groups of 2. Brute force recursion is acceptable.
Calculate the gcd of every pair and greedily multiply the largest gcds.
Test case: 
3
1 2 3 4 5 6
 *
 */


public class NumberGameGCD {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		int n = Integer.parseInt(scanner.nextLine());
		List<Integer> cards = splitWords(scanner.nextLine()).stream().map(Integer :: parseInt).collect(Collectors.toList());
		System.out.println(maxScore(cards.stream().mapToInt(i->i).toArray(), n));
		scanner.close();	
	}
	
	public static List<String> splitWords(String words) {
		return words.isEmpty() ? new ArrayList<>() : Arrays.asList(words.split(" "));
	}

	public static int maxScore(int[] nums, int n) {
        if (nums == null || nums.length == 0 || nums.length/2 != n) return 0;
        return dfs((1<<nums.length)-1, nums.length/2, nums, new HashMap<Integer, Integer>());
    }
    
    private static int dfs(int mask, int level, int[] nums, Map<Integer, Integer> memo) {
        if (mask == 0) return 0;
        
        if (memo.containsKey(mask)) return memo.get(mask);
        int score = 0, maxScore=0;
        for (int i=0; i<nums.length; i++) {
            if ((mask & (1<<i)) == 0) continue;
            for (int j=i+1; j<nums.length; j++) {
                if ((mask & (1<<j)) == 0) continue;
                int m = mask & ~(1<<i) & ~(1<<j);
                score = dfs(m, level-1, nums, memo) + level*gcd(nums[i], nums[j]);
                maxScore = Math.max(maxScore, score);
                
            }
        }
        memo.put(mask, maxScore);
        return maxScore;
    }
    
    
    private static int gcd(int a, int b) {
        if (b==0) return a;
        
        return gcd(b, a%b);
    }
    
    
    //Time limit exceeded
    private int bfs(int[] nums) {
        int level = 0;
        int[] scores = new int[1<<nums.length];
        int full = (1<<nums.length)-1; //scores.length-1;  is OK !  1<<nums.length-1 is wrong!! Operator precedence!!!
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(0);
        
        while(!que.isEmpty()) {
            level++;
            int row = que.size();
            
            for (int c=0; c<row; c++) {
                int m = que.poll();
                
                for (int i=0; i<nums.length; i++) {
                    if ((m & 1<<i) !=0) continue;
                    for (int j=i+1; j<nums.length; j++) {
                        if ((m & 1<<j) !=0) continue;
                        
                        int mask = m | 1<<i | 1<<j;
                        //int score = scores[m] + level*gcd(nums[i], nums[j]);
                        scores[mask] = Math.max(scores[mask], scores[m] + level*gcd(nums[i], nums[j]));
                        if (mask!=full) {
                            que.offer(mask);
                        }
                    }
                }
            }
        }
        
        return scores[full];
    }
}
