package binarysearch;
/*
 61 · Search for a Range

Medium
Accepted Rate
30%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a sorted array of n integers, find the starting and ending position of a given target value.

If the target is not found in the array, return [-1, -1].

Example
Example 1:

Input:
[]
9
Output:
[-1,-1]

Example 2:

Input:
[5, 7, 7, 8, 8, 10]
8
Output:
[3, 4]
Challenge
O(log n) time.
 */
public class SearchForRange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
        if (A==null || A.length==0) return new int[] {-1, -1};

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

        return  new int[] {left,right};
    }
}
