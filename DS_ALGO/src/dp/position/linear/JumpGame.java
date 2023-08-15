package dp.position.linear;
/*
 * 116 · Jump Game
Algorithms
Medium
Accepted Rate
43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an array of non-negative integers, you are initially positioned at the first index of the array.

Each element in the array represents your maximum jump length at that position.

Determine if you are able to reach the last index.

Wechat reply the 【116】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

The array A contains 𝑛 integers 𝑎1, 𝑎2, …, 𝑎𝑛 (1≤𝑎𝑖≤5000) (1≤n≤5000 )

Example
Example 1:

Input:

A = [2,3,1,1,4]
Output:

true
Explanation:

0 -> 1 -> 4 (the number here is subscript) is a reasonable scheme.

Example 2:

Input:

A = [3,2,1,0,4]
Output:

false
Explanation:

There is no solution that can reach the end.

Challenge
This problem have two method which is Greedy and Dynamic Programming.

The time complexity of Greedy method is O(n)O(n).

The time complexity of Dynamic Programming method is O(n^2)O(n 
2
 ).

We manually set the small data set to allow you pass the test in both ways. This is just to let you learn how to use this problem in dynamic programming ways. If you finish it in dynamic programming ways, you can try greedy method to make it accept again.

Tags
Dynamic Programming/DP
Greedy
Coordinate DP
Related Problems
752
Rogue Knight Sven
Medium
117
Jump Game II
Medium
Recommend Courses
 */
public class JumpGame {
	public boolean canJump(int[] A) {
        // write your code here
        if (A==null || A.length==0) return false;
        int n = A.length;
        boolean[] dp = new boolean[n];

        dp[0] = true;
        
        for (int i=1; i<n; i++) {
          for (int j=0; j<n; j++) {
            if (dp[j] && A[j] + j >= i) {
              dp[i] = true;
              break;
            }
          }
        }

        return dp[n-1];

    }
}
