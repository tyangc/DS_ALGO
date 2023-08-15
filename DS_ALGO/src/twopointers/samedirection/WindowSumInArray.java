package twopointers.samedirection;

/*
 * 604 · Window Sum
Algorithms
Easy
Accepted Rate
42%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an array of n integers, and a moving window(size k), move the window at each iteration from the start of the array, find the sum of the element inside the window at each moving.

Wechat reply the 【604】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

Example
Example 1

Input：array = [1,2,7,8,5], k = 3
Output：[10,17,20]
Explanation：
1 + 2 + 7 = 10
2 + 7 + 8 = 17
7 + 8 + 5 = 20
Tags
Same Direction Two Pointers
Prefix Sum Array
Two Pointers
Array
Company
Amazon
Related Problems
360
Sliding Window Median
Hard
642
Moving Average from Data Stream
Easy
362
Sliding Window Maximum
Hard
32
Minimum Window Substring
Medium
 */
public class WindowSumInArray {
	public int[] winSum(int[] nums, int k) {
        // write your code here

        if (nums==null || nums.length<1 || nums.length<k) return new int[]{};
        
        if (k==0) return nums;  //Edge case
        
        int n=nums.length, j=0, sum = 0;
        int[] res = new int[n-k+1];

        for (int i=0; i<n-k+1; i++) {

          while(j<n && j-i<k) {
            sum += nums[j];
            j++;
          }

          res[i] = sum;
          sum -= nums[i];

        }

        return res;
    }
}
