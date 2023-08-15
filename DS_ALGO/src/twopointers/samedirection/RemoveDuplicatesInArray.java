package twopointers.samedirection;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

/*
 * 521 · Remove Duplicate Numbers in Array
Algorithms
Easy
Accepted Rate
46%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an array of integers, remove the duplicate numbers in it.

You should:

Do it in place in the array.
Put the element after removing the repetition at the beginning of the array.
Return the number of elements after removing duplicate elements.
Wechat reply 【521】 get the latest requent Interview questions . (wechat id : jiuzhang15)

You don't need to keep the original order of the integers.

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
Tags
Hash Table
Same Direction Two Pointers
Two Pointers
Array
Related Problems
1789
Distinguish Username
Easy
487
Name Deduplication
Easy
 */

public class RemoveDuplicatesInArray {
	
	//Use same direction two points template:
	public int deduplication1(int[] nums) {
		int i=0, j=0;
        for (i=0; i<nums.length; i++) {
          while(j<nums.length && nums[i] == nums[j]) {
            j++;
          }

          if (j>=nums.length) break;
          nums[i+1] = nums[j];  //Can not use i++ or ++i here
        }

        return i+1;
	}
	
	//Another way by while loop only:
	public int deduplication2(int[] nums) {
        // write your code here
        if (nums==null || nums.length==0) return 0;
        Arrays.sort(nums);

        int i=0, j=1;

        while(j<nums.length) {
          if (nums[i] != nums[j]) {
            nums[++i] = nums[j]; //Can not use i+1 here
          }
          j++;
        }

        return i+1;
	}
	
	//Use hasSet
	public int deduplication3(int[] nums) {
		
		Set<Integer> set = new LinkedHashSet<>();
	
	    for (int i=0; i<nums.length; i++) {
	        set.add(nums[i]);
	    }
	    
	    //return set.size();
	    
	    int res=0;
	    	
	    for (int i : set) {
	        nums[res++] = i;
	    }
	    return res;
	}
}
