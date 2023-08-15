package twopointers.samedirection;

import java.util.Arrays;

/*
 64. Merge Sorted Array (easy version)

Given two sorted integer arrays A and B, merge B into A as one sorted array.

Example
Example 1:

Input：[1, 2, 3] 3  [4,5]  2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
Example 2:

Input：[1,2,5] 3 [3,4] 2
Output：[1,2,3,4,5]
Explanation:
After merge, A will be filled as [1, 2, 3, 4, 5]
Notice
You may assume that A has enough space (size that is greater or equal to m + n) to hold additional elements from B. The number of elements initialized in A and B are m and n respectively.
 * 
 */

public class MergeSortedArrayEasy {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Time out at 83% if system.out
	public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here

        int i=m-1, j=n-1, k=m+n-1;

        while(i>=0 || j>=0) {
            System.out.println(Arrays.toString(A));    
            if (i>=0&&j>=0) {
                A[k--] = A[i] > B[j] ? A[i--] : B[j--];
            } else {

                if (i>=0) A[k--] = A[i--];

                if (j>=0) A[k--] = B[j--];

            }

        }
    }
	
	//Better way
	 public void mergeSortedArray1(int[] A, int m, int[] B, int n) {
	        // write your code here

	        int i=m-1, j=n-1, k=m+n-1;

	        while(i>=0 && j>=0) {
	            //System.out.println(Arrays.toString(A));    
	           
	                A[k--] = A[i] > B[j] ? A[i--] : B[j--];
	           
	        }
	        
	        while(i>=0) A[k--] = A[i--];

	        while(j>=0) A[k--] = B[j--];

	    }

}
