package twopointers.differentdirection.towards;

import java.util.Arrays;

/*
 *
 *382 · Triangle Count
 *Description
Given an array of integers, how many three numbers can be found in the array, so that we can build an triangle whose three edges length is the three numbers that we find?

Example
Example 1:

Input: [3, 4, 6, 7]
Output: 3
Explanation:
They are (3, 4, 6), 
         (3, 6, 7),
         (4, 6, 7)
Example 2:

Input: [4, 4, 4, 4]
Output: 4
Explanation:
Any three numbers can form a triangle. 
So the answer is C(3, 4) = 4
Tags
Opposite Direction Two Pointers
Two Pointers
Related Problems
57
3Sum
Medium
56
Two Sum
Easy
 */
public class TriangleCount {
	/**
     * @param S: A list of integers
     * @return: An integer
     */
	// O(n^3)
    public int triangleCount(int[] S) {
        // write your code here
        int n = S.length, cnt = 0;
        if (n<3) return 0;

        Arrays.sort(S);

        for (int i=0; i<n-2; i++) {
          for (int j=i+1; j<n-1; j++ ) {
            int k = n-1;

            while(j<k && S[i] + S[j] <= S[k]) {
              k--;
            }

            if (j<k) cnt += k-j;
          }
          
        }

        return cnt;
    }
    
    //This one much faster  O(n^2)
    public int triangleCount1(int[] S) {
        // write your code here
        int n = S.length, cnt = 0, left = 0, right;
        if (n<3) return 0;

        Arrays.sort(S);

        for (int i=0; i<n; i++) {
          left = 0;
          right = i-1;
          while(left<right) {
            if (S[left] + S[right] > S[i]) {
              cnt += right - left;
              right--;
            } else {
              left++;
            }
          }
        }
        return cnt;
    }
}
