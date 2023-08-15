package twopointers.samedirection.shiftBySeperator;
/*
 172 · Remove Element
Algorithms
Easy
Accepted Rate
36%

Description
Given an array and a value, remove all occurrences of that value in place and return the new length.

The order of elements can be changed, and the elements after the new length don't matter.

Example
Example 1:
	Input: [], value = 0
	Output: 0


Example 2:
	Input:  [0,4,4,0,0,2,4,4], value = 4
	Output: 4
	
	Explanation: 
	the array after remove is [0,0,0,2]
Tags
Array
Related Problems
539
Move Zeroes
Easy
101
Remove Duplicates from Sorted Array II
Easy
100
Remove Duplicates from Sorted Array
Easy
 */
public class RemoveElements {
	public int removeElement(int[] A, int elem) {
        // write your code here

        if (A.length == 0) return 0;

        int left = 0, right = 0;
        while(right < A.length) {
          if (A[right] != elem) {
            A[left] = A[right];
            left++;
          }

          right++;
        }
        int[] tmp = new int[left+1];
        //A = Arrays.copyOf(A, left+1);
        //System.arraycopy(A, 0, tmp, 0, left);
        //A = tmp;
        return left;
    }
}
