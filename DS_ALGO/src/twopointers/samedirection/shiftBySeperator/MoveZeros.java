package twopointers.samedirection.shiftBySeperator;
/*
 539 · Move Zeroes
Algorithms
Easy
Accepted Rate
49%

Description
Given an array nums, write a function to move all 0's to the end of it while maintaining the relative order of the non-zero elements.

You must do this in-place without making a copy of the array.
Minimize the total number of operations.
Example
Example 1:

Input: nums = [0, 1, 0, 3, 12],
Output: [1, 3, 12, 0, 0].
Example 2:

Input: nums = [0, 0, 0, 3, 1],
Output: [3, 1, 0, 0, 0].
Tags
Company
Facebook
Related Problems
172
Remove Element
 */
public class MoveZeros {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public void moveZeroes(int[] nums) {
        // write your code here
        int left = 0, right = 0;

        while(right<nums.length) {
          if (nums[right] != 0) {
            nums[left] = nums[right];
            left++;
          } 
          right++;
          
        }

        while(left<nums.length) {
          nums[left] = 0;
          left++;
        }
    }
}
