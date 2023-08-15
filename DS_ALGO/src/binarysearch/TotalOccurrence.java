package binarysearch;

/*
 462. Total Occurrence of Target
Given a target number and an integer array sorted in ascending order. Find the total number of occurrences of target in the array.

Example
Example1:

Input: [1, 3, 3, 4, 5] and target = 3,
Output: 2.
Example2:

Input: [2, 2, 3, 4, 6] and target = 4,
Output: 1.
Example3:

Input: [1, 2, 3, 4, 5] and target = 6,
Output: 0.
Challenge
Time complexity in O(logn)
 */
public class TotalOccurrence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param A: A an integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int totalOccurrence(int[] A, int target) {
        // write your code here
        if (A==null || A.length==0) return 0;

        int start = 0, end= A.length-1, mid=0, left=0, right=0;

        while(start+1<end) {
            mid = start + (end-start)/2;

            if (A[mid]>=target) {
                end = mid;
            } else {
                start = mid;
            }
        }

        if (A[start]==target) {
            left = start;
        } else if (A[end]==target){
            left = end;
        } else {
            left = -1;
        }

        start = 0;
        end= A.length-1;

        while(start+1<end) {
            mid = start + (end-start)/2;

            if (A[mid]<=target) {
                start = mid;
            } else {
                end = mid;
            }
        }

        if (A[end]==target) {
            right = end;
        } else if (A[start]==target){
            right = start;
        } else {
            right = -1;
        }

        return left==-1? 0 : right - left + 1;
    }
}
