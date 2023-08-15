package twopointers.differentdirection.towards;

import java.util.Arrays;

/*
 144 · Interleaving Positive and Negative Numbers
Algorithms
Medium
Accepted Rate
30%

Description
Given an array with positive and negative integers. Re-range it to interleaving with positive and negative integers.

You are not necessary to keep the original order of positive integers or negative integers.

Example
Example 1

Input : [-1, -2, -3, 4, 5, 6]
Outout : [-1, 5, -2, 4, -3, 6]
Explanation :  any other reasonable answer.
Challenge
Do it in-place and without extra memory.

Tags
Opposite Direction Two Pointers
Two Pointers
Related Problems
31
Partition Array
Medium
 */
public class InterleavingPositiveNegativeNumbers {
	public void rerange(int[] A) {
        // write your code here
        if (A == null || A.length<3) return;

        int iNegative = 0, cntPos = 0, n = A.length; 
        //Core tech:  selective swap (tortoise and hare pointers) while scan: put positive numbers in front
        
        for (int i=0; i<n; i++) {
          if (A[i]>0) {
            swap(A, iNegative++, i);
            cntPos++;
          }
        }
        
        System.out.println(Arrays.toString(A));

        int left = 1, right = n-1;  //cntPos>cntNeg
        if (cntPos == n-cntPos) {
        	right = n-2;
        }

        

        while(left < right) {
          swap(A, left, right);
          left += 2;
          right -= 2;
        }
    }

    private void swap(int[] A, int i, int j) {
      int tmp = A[i];
      A[i] = A[j];
      A[j] = tmp;
    }
}
