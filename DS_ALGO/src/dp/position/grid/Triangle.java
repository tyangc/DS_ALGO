package dp.position.grid;

/*
 * https://www.lintcode.com/problem/109/
 109. Triangle

Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Example
Example 1:

Input the following triangle:
[
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
Output: 11
Explanation: The minimum path sum from top to bottom is 11 (i.e., 2 + 3 + 5 + 1 = 11).
Example 2:

Input the following triangle:
[
     [2],
    [3,2],
   [6,5,7],
  [4,4,8,1]
]
Output: 12
Explanation: The minimum path sum from top to bottom is 12 (i.e., 2 + 2 + 7 + 1 = 12).
Notice
Bonus point if you are able to do this using only O(n) extra space, where n is the total number of rows in the triangle.
 */

public class Triangle {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here

        int m = triangle.length;

        for (int i=m-2; i>=0; i--) {
            for (int j=0; j<triangle[i].length; j++) {
                triangle[i][j] += Math.min(triangle[i+1][j], triangle[i+1][j+1]);
            }

        }

        return triangle[0][0];
    }
    
    //Using top to bottom approach
    public int minimumTotal1(int[][] triangle) {
        // write your code here

        if (triangle.length == 1) return triangle[0][0];
        int n= triangle.length;
        int[][] dp = new int[n][];

        for (int i=0; i<n; i++) {
          dp[i] = new int[triangle[i].length]; 
        }  

        dp[0][0] = triangle[0][0];

        for (int i=1; i<n; i++) {
          dp[i][0] = triangle[i][0] + dp[i-1][0];
          dp[i][i] = triangle[i][i] + dp[i-1][i-1];
        }

        for (int i=2; i<n; i++) {
          for (int j=1; j<i; j++) {
            dp[i][j] = triangle[i][j] + Math.min(dp[i-1][j-1], dp[i-1][j]);
          }
        }
      
        int res = Integer.MAX_VALUE;
        for (int i=0; i<n; i++) {
          res = Math.min(res, dp[n-1][i]);
        }

        return res;
    }

}
