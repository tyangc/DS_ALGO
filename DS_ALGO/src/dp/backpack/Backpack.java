package dp.backpack;
/*
 * https://www.lintcode.com/problem/92/
 * 92 · BackpackPRE
Algorithms
Medium
Accepted Rate
37%

DescriptionSolutionNotesDiscussLeaderboard
This topic is a pre-release topic. If you encounter any problems, please contact us via "Problem Correction", and we will upgrade your account to VIP as a thank you.
Description
Given n items with size A_{i}A 
i
​
  an integer m denotes the size of a backpack. How full you can fill this backpack?
(Each item can only be selected once)

You can not divide any item into small pieces.

Example
Example 1:

Input:

array = [3,4,8,5]
backpack size = 10
Output:

9
Explanation:

Load 4 and 5.

Example 2:

Input:

array = [2,3,5,7]
backpack size = 12
Output:

12
Explanation:

Load 5 and 7.

Tags
Backpack DP
Dynamic Programming/DP
Related Problems
1538
Card Game II
Medium
800
Backpack IX
Medium
749
John's backyard garden
Easy
724
Minimum Partition
Medium
700
Cutting a Rod
Medium
669
Coin Change
Medium
588
Partition Equal Subset Sum
Medium
564
Combination Sum IV
Medium
563
Backpack V
Medium
562
Backpack IV
Medium
440
Backpack III
Medium
279
Number of Ways to Represent N Cents
Medium
125
Backpack II
Medium
 */
		
import java.util.Arrays;

public class Backpack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//using boolean[][] as state
	public int backPack(int m, int[] A) {
        // write your code here
        int n = A.length;
        boolean[][] dp = new boolean[n+1][m+1];

    
        /*
         * Not necessary since dp[i][j] = dp[i-1][j];
         * if j starts from 1 then this is necessary
         */
        
        /*
        for (int i=0; i<=n; i++) {
            dp[i][0] = true;
        }
        */

        dp[0][0] = true;

        for (int i=1; i<=n; i++) {
            for (int j=0; j<=m; j++) {
                if (j>=A[i-1]) {   //If j>A[i]  -- then it is wrong!!!
                    dp[i][j] = dp[i-1][j] || dp[i-1][j-A[i-1]];
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        for (int i=0; i<=n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        }

        int v=0;

        for (v=m; v>=0; v--) {
            if (dp[n][v]) {
                break;
            }
        }

        return v;
    }
	
	public int backPack1(int m, int[] A) {
        // write your code here
        int n = A.length;
        int[][] dp = new int[n+1][m+1];
        

        //dp[0][0] = 0;

        for (int i=1; i<=n; i++) {
            for (int j=0; j<=m; j++) {
                if (j>=A[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i-1]] + A[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }

        

        return dp[n][m];
    }
}
