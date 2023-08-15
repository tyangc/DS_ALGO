package dp.position.linear;

import java.util.Arrays;

/*
 76. Longest Increasing Subsequence

Given a sequence of integers, find the longest increasing subsequence (LIS).

You code should return the length of the LIS.

Example
Example 1:
	Input:  [5,4,1,2,3]
	Output:  3
	
	Explanation:
	LIS is [1,2,3]


Example 2:
	Input: [4,2,4,5,3,7]
	Output:  4
	
	Explanation: 
	LIS is [2,4,5,7]
Challenge
Time complexity O(n^2) or O(nlogn)

Clarification
What's the definition of longest increasing subsequence?

The longest increasing subsequence problem is to find a subsequence of a given sequence in which the subsequence's elements are in sorted order, lowest to highest, and in which the subsequence is as long as possible. This subsequence is not necessarily contiguous, or unique.

https://en.wikipedia.org/wiki/Longest_increasing_subsequence
 */

public class LongestIncreasingSubsequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /**
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequenceDP(int[] nums) {
        // write your code here
        if (nums==null || nums.length==0) return 0;

        int n = nums.length;

        int[] dp = new int[n];

        Arrays.fill(dp, 1);

        for (int i=0; i<n; i++) {
            for (int j=0; j<i; j++) {
                if (nums[j]<nums[i] && dp[j]+1>dp[i]) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int max=1;

        for (int i=0; i<n; i++) {
            if (dp[i]>max) {
                max = dp[i];
            }
        }

        return max;
    }
    
    public int longestIncreasingSubsequenceBinarySearch(int[] nums) {
        // write your code here
        if (nums==null || nums.length==0) return 0;

        int n = nums.length;

        int[] lis = new int[n+1];  //index of lis is the length of increased sequence and lis[i] stores the current least of end of the rising sequence 

        Arrays.fill(lis, Integer.MAX_VALUE);
        lis[0] = Integer.MIN_VALUE;
        int longest = 0;

        for (int i=0; i<n; i++) {
            int idx = getFirstGTE(lis, nums[i]);
            lis[idx] = nums[i];
            longest = Math.max(longest, idx);
        }

        return longest;
       
    }

    private int getFirstGTE(int[] lis, int target) {
        int start = 0, end = lis.length-1;

        while(start+1<end) {
            int mid = start + (end-start)/2;

            if (lis[mid]>=target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (lis[start]>=target) return start;
        return end;
    }
}
