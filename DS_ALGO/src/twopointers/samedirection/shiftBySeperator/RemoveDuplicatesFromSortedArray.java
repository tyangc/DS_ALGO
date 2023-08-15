package twopointers.samedirection.shiftBySeperator;
/*
 100 · Remove Duplicates from Sorted Array
Algorithms
Easy
Accepted Rate
35%

Description
Given a sorted array, 'remove' the duplicates in place such that each element appear only once and return the 'new' length.

Do not allocate extra space for another array, you must do this in place with constant memory.

Example
Example 1:

Input:

nums = []
Output:

0
Explanation:

The array is empty.
Example 2:

Input:

nums = [1,1,2]
Output:

2
Explanation:

uniqued array: [1,2]

Tags
Array
Simulation
Related Problems
172
Remove Element
Easy
101
Remove Duplicates from Sorted Array II
 */
public class RemoveDuplicatesFromSortedArray {

	public int removeDuplicates(int[] nums) {
        // write your code here
        if (nums.length<=1) return nums.length;

        int i=0, j=1;
        while(j < nums.length) {
          if (nums[j-1] != nums[j]) {
            nums[++i] = nums[j];
            //i++;
          } 

          j++;
        }

        return i+1;
    }
}
