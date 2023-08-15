package dp.partition;
/*
 * https://www.lintcode.com/problem/437/
 * 437 · Copy Books
Algorithms
Medium
Accepted Rate
40%
Description
Solution54
Notes99+
Discuss6
Leaderboard
Record
Description
Given n books and the i-th book has pages[i] pages. There are k persons to copy these books.

These books list in a row and each person can claim a continous range of books. For example, one copier can copy the books from i-th to j-th continously, but he can not copy the 1st book, 2nd book and 4th book (without 3rd book).

They start copying books at the same time and they all cost 1 minute to copy 1 page of a book. What's the best strategy to assign books so that the slowest copier can finish at earliest time?

Return the shortest time that the slowest copier spends.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang0607)


The sum of book pages is less than or equal to 2147483647

Example
Example 1:

Input: pages = [3, 2, 4], k = 2
Output: 5
Explanation: 
    First person spends 5 minutes to copy book 1 and book 2.
    Second person spends 4 minutes to copy book 3.
Example 2:

Input: pages = [3, 2, 4], k = 3
Output: 4
Explanation: Each person copies one of the books.
Challenge
O(nk) time

Tags
Dynamic Programming/DP
Binary Search on Answer
Partition DP
Binary Search
Related Problems

1219
Heaters
Medium
 */
public class CopyBooks {
	
	public int copyBooks(int[] pages, int k) {
        // write your code here
        int n=pages.length;
        int[][] dp = new int[n+1][k+1];
        for (int i=1; i<=n; i++) {
            for (int j=0; j<=k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        int[] prefixSum = new int[n+1];

        for (int i=1; i<=n; i++) {
            prefixSum[i] = prefixSum[i-1] + pages[i-1];
        }

        for (int i=0; i<=n; i++) {
            for (int j=1; j<=k; j++) {
                //dp[i][j] = Integer.MAX_VALUE;
                for (int p=0; p<i; p++)
                dp[i][j] = Math.min(dp[i][j], Math.max(dp[p][j-1], prefixSum[i] - prefixSum[p] ));
            }
        }

        return dp[n][k];

    }
	
	//Better way of implementation:  with better initialization
	public int copyBooksFasterImplementation(int[] pages, int k) {
        // write your code here
        int n=pages.length;
        int[][] dp = new int[n+1][k+1];
        /*
        for (int i=1; i<=n; i++) {
            for (int j=0; j<=k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }
        */
        for (int i=1; i<n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }
        int[] prefixSum = new int[n+1];

        for (int i=1; i<=n; i++) {
            prefixSum[i] = prefixSum[i-1] + pages[i-1];
        }

        for (int i=1; i<=n; i++) {
            for (int j=1; j<=k; j++) {
                dp[i][j] = Integer.MAX_VALUE;
                for (int p=0; p<i; p++)
                dp[i][j] = Math.min(dp[i][j], Math.max(dp[p][j-1], prefixSum[i] - prefixSum[p] ));
            }
        }

        return dp[n][k];

    }
}
