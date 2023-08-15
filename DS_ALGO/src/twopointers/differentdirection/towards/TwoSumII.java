package twopointers.differentdirection.towards;

import java.util.Arrays;

public class TwoSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: an array of integer
     * @param target: An integer
     * @return: An integer
     */
    public int twoSum(int[] nums, int target) {
        // write your code here
        if (nums==null || nums.length<2) return 0;
        
        Arrays.sort(nums);
        int count=0, i=0, j=nums.length-1;
        
        while(i<j) {
            int sum = nums[i]+nums[j];
            if (sum==target) {
                count++;
                i++;
                j--;
                while(i<j&&nums[i-1]==nums[i]) {
                    i++;
                }
                
                while(i<j&&nums[j+1]==nums[j]) {
                    j--;
                }
            } else if (sum>target) {
                j--;
            } else {
                i++;
            }
        }
        
        return count;
    }
}
