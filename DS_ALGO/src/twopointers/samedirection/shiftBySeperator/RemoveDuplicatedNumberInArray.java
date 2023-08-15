package twopointers.samedirection;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 521. Remove Duplicate Numbers in Array

Given an array of integers, remove the duplicate numbers in it.

You should:

Do it in place in the array.
Move the unique numbers to the front of the array.
Return the total number of the unique numbers.
Example
Example 1:

Input:
nums = [1,3,1,4,4,2]
Output:
[1,3,4,2,?,?]
4
Explanation:

Move duplicate integers to the tail of nums => nums = [1,3,4,2,?,?].
Return the number of unique integers in nums => 4.
Actually we don't care about what you place in ?, we only care about the part which has no duplicate integers.
Example 2:

Input:
nums = [1,2,3]
Output:
[1,2,3]
3
Challenge
Do it in O(n) time complexity.
Do it in O(nlogn) time without extra space.
Notice
You don't need to keep the original order of the integers.
 */
public class RemoveDuplicatedNumberInArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 public int deduplication(int[] nums) {
	        // write your code here
	        if (nums==null || nums.length==0) return 0;

	        Set<Integer> set = new LinkedHashSet<>(); //Keep the iterate order

	        for (int i=0; i<nums.length; i++) {
	            set.add(nums[i]);


	        }
	        
	        int res=0;

	        for (int i : set) {
	            nums[res++] = i;
	        }

	        
	        System.out.println(Arrays.toString(nums));
	        return res;
	    }
}
