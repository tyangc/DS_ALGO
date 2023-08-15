package binarysearch;
/*
 * 38 · Search a 2D Matrix II
Algorithms
Medium
Accepted Rate
44%

DescriptionSolutionNotesDiscussLeaderboard
Description
Write an efficient algorithm that searches for a value in an m x n matrix, return The number of occurrence of it.

This matrix has the following properties:

Integers in each row are sorted from left to right.
Integers in each column are sorted from up to bottom.
No duplicate integers in each row or column.
Contact me on wechat to get more FLAMG requent Interview questions . (wechat id : jiuzhang15)

Example
Example 1:

Input:

matrix = [[3,4]]
target = 3
Output:

1
Explanation:

There is only one 3 in the matrix.

Example 2:

Input:

matrix = [
      [1, 3, 5, 7],
      [2, 4, 7, 8],
      [3, 5, 9, 10]
    ]
target = 3
Output:

2
Explanation:

There are two 3 in the matrix.

Challenge
O(m+n) time and O(1) extra space

Tags
Binary Search
Simulation
Related Problems
832
Count Negative Number
Medium
465
Kth Smallest Sum In Two Sorted Arrays
Hard
28
Search a 2D Matrix
Easy
 */

public class MatrixSearchII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param matrix: A list of lists of integers
     * @param target: An integer you want to search in matrix
     * @return: An integer indicate the total occurrence of target in the given matrix
     */
    public int searchMatrix(int[][] matrix, int target) {
        // write your code here
        if (matrix==null) return 0;
        
        int m = matrix.length;
        int n = matrix[0].length;
        
        int i=m-1, j=0, count=0;
        
        while(i>-1 && j<n) {
            if ( matrix[i][j] > target ) {
                i--;
            } else if (matrix[i][j]<target) {
                j++;
            } else {
                count++;
                i--;
                j++;
            }
        }
        return count;
    }
}
