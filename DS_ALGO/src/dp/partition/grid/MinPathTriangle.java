package dp.grid;

import java.util.HashMap;
import java.util.Map;
/*
 * 109 · Triangle
Algorithms
Medium
Accepted Rate
33%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a triangle, find the minimum path sum from top to bottom. Each step you may move to adjacent numbers on the row below.

Bonus point if you are able to do this using only O(n)O(n) extra space, where n is the total number of rows in the triangle.

Example
Example 1:

Input:

triangle = [
     [2],
    [3,4],
   [6,5,7],
  [4,1,8,3]
]
Output:

11
Explanation:

The minimum path sum from top to bottom is 11 (2 + 3 + 5 + 1 = 11).

Example 2:

Input:

triangle = [
     [2],
    [3,2],
   [6,5,7],
  [4,4,8,1]
]
Output:

12
Explanation:

The minimum path sum from top to bottom is 12 (2 + 2 + 7 + 1 = 12).

Tags
Dynamic Programming/DP
Coordinate DP
Memoization Search
Related Problems
110
Minimum Path Sum
Easy
 */

public class MinPathTriangle {
	
	//DP bottom up using  the same triangle, but it will modify the original array
	public int minimumTotalDP1(int[][] triangle) {
		
		int m = triangle.length;
		
		for (int i=m-2; i<=0; i--) {
			for (int j=0; j<triangle[i].length; j++) {
				triangle[i][j] += Math.min(triangle[i+1][j], triangle[i+1][j+1]);
			}
		}
		
		return triangle[0][0];
	}
	
	//DP: from bottom up, using a new dp array:
	public int minimumTotalDBottomUp(int[][] triangle) {
        // write your code here

        if (triangle.length == 1) return triangle[0][0];
        int n= triangle.length;
        int[][] dp = new int[n][];

        for (int i=0; i<n; i++) {
          dp[i] = new int[triangle[i].length]; 
        }  

        for (int i=0; i<n; i++) {
          dp[n-1][i] = triangle[n-1][i];
        }

        for (int i=n-2; i>=0; i--) {
          for (int j=0; j<i+1; j++) {
            dp[i][j] = Math.min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j];
          }
        }

        return dp[0][0];
      
    }

	//DP: from top down, using a new dp array:
	
	public int minimumTotalDPTopDown(int[][] triangle) {
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
	
	//Using memoization search and divide and conquer:
	
	public int minimumTotalMemo(int[][] triangle) {
        // write your code here

        //if (triangle.length == 1) return triangle[0][0];
        

        Map<Integer, Integer> map = new HashMap<>();  //Can use int[] point = new int[] {i, j}; as hash key, but it is slower only 90% of test case passed

        return divideAndConquer(triangle, 0, 0, map);
    }

    private int divideAndConquer(int[][] arr, int i, int j, Map<Integer, Integer> map) {
      int m = arr.length;
      if (i == m) return 0;
      //int[] point = new int[]{i, j};
      int point = i*m + j;
      if (map.containsKey(point)) return map.get(point);

      int res = arr[i][j] + Math.min(divideAndConquer(arr, i+1, j, map), divideAndConquer(arr, i+1, j+1, map));
      map.put(point, res);
      return res;
    }
    
    
}
