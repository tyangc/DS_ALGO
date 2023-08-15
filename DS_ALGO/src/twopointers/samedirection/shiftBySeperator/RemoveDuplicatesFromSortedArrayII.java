package twopointers.samedirection.shiftBySeperator;
/*
 101 · Remove Duplicates from Sorted Array II
Algorithms
Easy
Accepted Rate
34%

Description
Given a sorted array, remove the duplicates in place such that each element appear at most twice and return the new length.
If a number appears more than two times, then keep the number appears twice in array after remove.

Need to operate in the original array

Example
Example 1:

Input:

array = []
Output:

0
Explanation:

Empty array, length is 0.

Example 2:

Input:

array = [1,1,1,2,2,3]
Output:

5
Explanation:

the length is 5: [1,1,2,2,3]

Tags
Related Problems
172
Remove Element
Easy
100
Remove Duplicates from Sorted Array
Easy
 */
public class RemoveDuplicatesFromSortedArrayII {

	public int removeDuplicates(int[] nums) {
		if (nums == null || nums.length == 0) {
            return 0;
        }
        int i=0, cnt = 1;
        for (int j=1; j<nums.length; j++) {
          if (nums[i] == nums[j]) {
            if (cnt<2) {
              nums[++i] = nums[j];
              cnt++;
            }
          } else {
            nums[++i] = nums[j];
            cnt = 1;
          }
        
        }
        return i+1;
        
    }
}
