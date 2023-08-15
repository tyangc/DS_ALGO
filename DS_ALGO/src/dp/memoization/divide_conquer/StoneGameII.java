package dp.memoization.divide_conquer;
/*
 * https://www.lintcode.com/problem/593/description
 * 593 · Stone Game II
Algorithms
Medium
Accepted Rate
49%

DescriptionSolutionNotesDiscussLeaderboard
Description
There is a game of rocks. At the beginning of the game, the players pick n piles of stones and form a circle.

The goal is to merge the stones in one pile observing the following rules:

At each step of the game,the player can merge two adjacent piles to a new pile.
The score is the number of stones in the new pile.
You are to determine the minimum of the total score.

Example
Example 1:

Input:
[1,1,4,4]
Output:18
Explanation:
1. Merge the first two piles => [2, 4, 4], score +2
2. Merge the first two piles => [6, 4]，score +6
3. Merge the last two piles => [10], score +10
Example 2:

Input:
[1, 1, 1, 1]
Output:8
Explanation:
1. Merge the first two piles => [2, 1, 1], score +2
2. Merge the last two piles => [2, 2]，score +2
3. Merge the last two piles => [4], score +4
Tags
Related Problems
741
Calculate Maximum Value II
Medium
476
Stone Game
Medium
 */
public class StoneGameII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public int stoneGame2(int[] A) {
        // write your code here
        if (A==null || A.length<2) return 0;

        int[] B = new int[A.length];
        int[] C = new int[A.length];

        System.arraycopy(A, 0, B, 1, A.length-1); 

        System.arraycopy(A, 1, C, 0, A.length-1);

        B[0] = A[A.length-1];
        C[A.length-1] = A[0];
        //System.out.println(Arrays.toString(B));
        //System.out.println(Arrays.toString(C));

        return Math.min(helper(C), Math.min(helper(A), helper(B)));
        
    }

    private int helper(int[] A) {
        int n = A.length;
        int[][] dp = new int[n][n]; 
        int[] sums = new int[n+1];

        for (int i=0; i<=n; i++) {
            for (int j=i+1; j<n; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for (int i=0; i<n; i++) {
            sums[i+1] = sums[i] + A[i];
        }

        return search(A, 0, n-1, dp, sums);
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
}
