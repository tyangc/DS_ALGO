package binarysearch;
/*
 * 28 · Search a 2D Matrix
Algorithms
Easy
Accepted Rate
33%

DescriptionSolutionNotesDiscussLeaderboard
Description
Write an efficient algorithm that searches for a target value in an m x n matrix.

This matrix has the following properties:

Integers in each row are sorted from left to right.
The first integer of each row is greater than the last integer of the previous row.
Contact me on wechat to get more FLAMG requent Interview questions . (wechat id : jiuzhang15)

n × m < 50000n×m<50000

Example
Example 1:

Input:

matrix = [[5]]
target = 2
Output:

false
Explanation:

The matrix does not include 2 , returns false.

Example 2:

Input:

matrix = [
  [1, 3, 5, 7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output:

true
Explanation:

The matrix includes 3, return true.

Challenge
O(log(n) + log(m)) time
 */
public class MatrixSearch {
	public boolean searchMatrix(int[][] matrix, int target) {
        // write your code here
		
		//This way might have some corner case not covered
        if (matrix==null || matrix.length<1 ) return false;

        int n = matrix.length;
        int m = matrix[0].length;

        int i=n-1, j=0;

        while(i>-1 && j<m) {
          if (matrix[i][j] >target) {
            i--;
          } else if (matrix[i][j] < target) {
            j++;
          } else {
            return true;
          }
        
        }

        return false;
        /* using binary search
        int row = matrix.length;
        int col = matrix[0].length;
        int start = 0, end = row*col -1;

        while(start+1<end) {
          int mid = start + (end-start)/2;
          int num = matrix[mid/col][mid%col];
          if (num<target) {
            start = mid;
          } else if (num>target) {
            end = mid;
          } else {
            return true;
          }

          
        }

        if (matrix[start/col][start%col]==target || matrix[end/col][end%col]==target) return true;

        return false;
        */
    }

	//Another way - more comprehensive
	public boolean searchMatrix1(int[][] matrix, int target) {
        // write your code here
        if (matrix==null || matrix.length<1 ) return false;

        if (matrix[0] == null || matrix[0].length<1) return false;

        int n = matrix.length;
        int m = matrix[0].length;

        int start = 0, end = n*m-1;

        while(start+1<end) {
          int mid = start + (end-start)/2;
          if (getValue(matrix, mid) < target) {
            start = mid;
          } else {
            end = mid;
          }

        }

        if (getValue(matrix, start) == target || getValue(matrix, end) == target) return true;
        

        return false;
        
    }

    private int getValue(int[][] nums, int pos) {
      int n = nums.length, m = nums[0].length;

      return nums[pos/m][pos%m];

    }
}
