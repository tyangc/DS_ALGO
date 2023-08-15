package datastructure.prefixsum.hashmap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 138. Subarray Sum

Given an integer array, find a subarray where the sum of numbers is zero. Your code should return the index of the first number and the index of the last number.

Example
Example 1:

Input:  [-3, 1, 2, -3, 4]
Output: [0, 2] or [1, 3].
Explanation: return anyone that the sum is 0.
Example 2:

Input:  [-3, 1, -4, 2, -3, 4]
Output: [1,5]	
Notice
There is at least one subarray that it's sum equals to zero.
 */
public class SubarraySum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: A list of integers
     * @return: A list of integers includes the index of the first number and the index of the last number
     */
    public List<Integer> subarraySum(int[] nums) {
        // write your code here
        
        List<Integer> res = new ArrayList<>();
        
        Map<Integer, Integer> map = new HashMap<>();
        
        map.put(0, -1);
        
        int sum=0;
        
        for (int i=0; i<nums.length; i++) {
            sum+=nums[i];
            
            if (map.containsKey(sum)) {
                res.add(map.get(sum)+1);
                res.add(i);
                return res;
            }
            
            map.put(sum, i);
        }
        
    
        return res;
    }
}
