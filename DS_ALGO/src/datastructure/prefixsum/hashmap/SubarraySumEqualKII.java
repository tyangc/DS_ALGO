package datastructure.prefixsum.hashmap;

import java.util.HashMap;
import java.util.Map;

/*
 * https://www.lintcode.com/problem/1844/
 * 1844 · subarray sum equals to k II
Algorithms
Medium
Accepted Rate
47%
Description
Solution22
Notes49
Discuss3
Leaderboard
Record
Description
Given an array of integers and an integer k, you need to find the minimum size of continuous no-empty subarrays whose sum equals to k, and return its length.

if there are no such subarray, return -1.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


the integer nums[i] may lower than 0

Example
Example1

Input: 
nums = [1,1,1,2] and k = 3
Output: 
2
Example2

Input: 
nums = [2,1,-1,4,2,-3] and k = 3
Output: 
2
Tags
Hash Table
Prefix Sum Array
Array
 */

public class SubarraySumEqualKII {
	// write your code here
	public int subarraySumEqualsKII(int[] nums, int k) {
        // write your code here
        int n = nums.length;
        int[] prefixSum = getPrefixSum(nums);
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0,0);
        int minLen = Integer.MAX_VALUE;

        for (int end=0; end<n; end++) {
            if (map.containsKey(prefixSum[end+1]-k)) {
                minLen = Math.min(minLen, end+1-map.get(prefixSum[end+1]-k));

            } //else {   //If need to get the max value, then else here is a must
                map.put(prefixSum[end+1], end+1);
            //}
        }

        return minLen == Integer.MAX_VALUE ? -1 : minLen;
    }

    private int[] getPrefixSum(int[] nums) {
        int n = nums.length;
        int[] res = new int[n+1];

        for (int i=0; i<n; i++) {
            res[i+1] = res[i] + nums[i];
        }

        return res;
    }
}
