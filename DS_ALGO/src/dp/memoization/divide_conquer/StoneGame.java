package dp.memoization.divide_conquer;

import java.util.HashMap;
import java.util.Map;

/*
 * https://www.lintcode.com/problem/476/description
 * 476 · Stone Game
Algorithms
Medium
Accepted Rate
45%

DescriptionSolutionNotesDiscussLeaderboard
Description
There is a stone game.At the beginning of the game the player picks n piles of stones in a line.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The cost of each combination is the sum of the weights of the two piles of stones combined.
Please find out the minimum cost of merging.

Example
Example 1:

Input: [3, 4, 3]
Output: 17
Example 2:

Input: [4, 1, 1, 4]
Output: 18
Explanation:
  1. Merge second and third piles => [4, 2, 4], score = 2
  2. Merge the first two piles => [6, 4]，score = 8
  3. Merge the last two piles => [10], score = 18
Tags
Interval DP
Dynamic Programming/DP
Related Problems
741
Calculate Maximum Value II
Medium
593
Stone Game II
Medium
168
Burst Balloons
Hard
 */

public class StoneGame {
	//100% test cases passed
	public int stoneGame(int[] A) {
        // write your code here
        if (A==null || A.length<2) return 0;
        /*
        Map<int[], Integer> memo = new HashMap<>();
        return dfs(A, 0, A.length-1, memo);
        */

        int n = A.length;
        int[][] dp = new int[n][n]; 
        int[] sums = new int[n+1];
   
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        /*
        for (int i=0; i<n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        */

        //Use prefix sum to help calculating the sum of interval
        for (int i=0; i<n; i++) {
            sums[i+1] = sums[i] + A[i];
        }

        return search(A, 0, A.length-1, dp, sums);

    }

    private int search(int[] A, int l, int r, int[][] dp, int[] sums) {
        if (dp[l][r] != Integer.MAX_VALUE) {
            return dp[l][r];
        }
        int res = Integer.MAX_VALUE;
        for (int k=l; k<r; k++) {
            int left = search(A, l, k, dp, sums);
            int right = search(A, k+1, r, dp, sums);
            res = Math.min(res, left+right+sums[r+1]-sums[l]);
                        
        }

        dp[l][r] = res;
        return res;
    }

    //Using dp:
    
    public int stoneGameDp(int[] A) {
        // write your code here
        if (A==null || A.length<2) return 0;
        int n=A.length;

        int[][] dp = new int[n][n];
        int[] sum = new int[n+1];
        
        /* This section can be replaced by in place initialization in the triple for loop 
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        */
        for (int i=0; i<n; i++) {
            sum[i+1] = sum[i] + A[i];
        }

        for (int len=2; len<=n; len++) {
            for (int i=0; i<n-len+1; i++) {
                int j=i+len-1;
                dp[i][j] = Integer.MAX_VALUE;
                for (int k=i; k<j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + sum[j+1] - sum[i]);
                }
            }
        }

        return dp[0][n-1];

        
    }

	
	//memory limit exceeded - 41% 
	public int stoneGameMLE(int[] A) {
        // write your code here
        
        Map<int[], Integer> memo = new HashMap<>();
        return dfs(A, 0, A.length-1, memo);
    }

    private int dfs(int[] A, int start, int end, Map<int[], Integer> memo) {
        int[] cur = new int[]{start, end};
        if (memo.containsKey(cur)) return memo.get(cur);

        if (start>=end) return 0;

        int cost = getSum(A, start, end);
        //System.out.println(start+"|" + end + "|" + cost);
        int min = Integer.MAX_VALUE, left=0, right=0;
        for (int mid = start; mid<end; mid++) {
            left = dfs(A, start, mid, memo);
            right = dfs(A, mid+1, end, memo);
            min = Math.min(min, left+right+cost);
        }
        min = min==Integer.MAX_VALUE ? cost : min;
        memo.put(cur, min);
        
        return min;

    }

    private int getSum(int[] A, int start, int end) {
        if (start==end) return A[start];
        int res = 0;

        for (int i=start; i<=end; i++) {
            res += A[i];
        }
        return res;
    }
}
