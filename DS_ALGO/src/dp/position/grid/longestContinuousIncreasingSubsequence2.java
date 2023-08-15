package dp.position.grid;

import java.util.HashMap;
import java.util.Map;

/*
 398. Longest Continuous Increasing Subsequence II

Given an integer matrix. Find the longest increasing continuous subsequence in this matrix and return the length of it.

The longest increasing continuous subsequence here can start at any position and go up/down/left/right.

Example
Example 1:

Input: 
    [
      [1, 2, 3, 4, 5],
      [16,17,24,23,6],
      [15,18,25,22,7],
      [14,19,20,21,8],
      [13,12,11,10,9]
    ]
Output: 25
Explanation: 1 -> 2 -> 3 -> 4 -> 5 -> ... -> 25 (Spiral from outside to inside.)
Example 2:

Input: 
    [
      [1, 2],
      [5, 3]
    ]
Output: 4
Explanation: 1 -> 2 -> 3 -> 5
Challenge
Assume that it is a N x M matrix. Solve this problem in O(NM) time and memory.
 * 
 */
public class longestContinuousIncreasingSubsequence2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//This one is slow will time out beyond 86% test case
	/**
     * @param matrix: A 2D-array of integers
     * @return: an integer
     */
    public int longestContinuousIncreasingSubsequence2(int[][] matrix) {
        // write your code here

        if (matrix==null || matrix.length==0) return 0;

        if (matrix[0]== null || matrix[0].length==0) return 0;    

        int n=matrix.length, m=matrix[0].length;
        if (n==0 || m==0) return 0;

        Map<Point, Integer> memo = new HashMap<>();       
        int max=1;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {

                
                   max = Math.max(max, dfs1(matrix, memo, i, j));
               

            }
        }
        return max;
    }

    private int dfs1(int[][] grid, Map<Point, Integer> memo, int x, int y) {

        Point cur = new Point(x, y);
        
        if (memo.containsKey(cur)) return memo.get(cur);
        int largest = 1;
        int[] dx = {1,0,-1,0};
        int[] dy = {0, -1, 0, 1};

        for (int k=0; k<4; k++) {
            int _x = x + dx[k];
            int _y = y + dy[k];

            if (!isValid(grid, _x, _y)) continue;

            if (grid[x][y] <= grid[_x][_y]) continue;
            
            largest = Math.max(largest, dfs1(grid, memo, _x, _y)+1);
            
        }
        memo.put(new Point(x, y), largest);
        return largest;

    }

    private boolean isValid(int[][] grid, int x, int y) {
        if (x<0 || x>=grid.length || y<0 || y>=grid[0].length) return false;
        return true;
    }
    
    
    //This is faster way - still using memorization
    public int longestContinuousIncreasingSubsequence2A(int[][] matrix) {
        // write your code here

        if (matrix==null || matrix.length==0) return 0;

        if (matrix[0]== null || matrix[0].length==0) return 0;    

        int n=matrix.length, m=matrix[0].length;
        if (n==0 || m==0) return 0;

        int[][] dp = new int[n][m];

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                dp[i][j] = -1;
            }
        }

        int ans = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                dfs(matrix, dp, i, j);
                ans = Math.max(ans, dp[i][j]);
            }
        }

        return ans;
    }

    private void dfs(int[][] grid,  int[][] dp, int x, int y) {  //return dp[x][y] also works

        if (dp[x][y] != -1) return;

        dp[x][y] = 1;  //This is very critical, otherwise , it might loop back!!!
        
        
        //int largest = 1;
        int[] dx = {1,0,-1,0};
        int[] dy = {0, -1, 0, 1};

        for (int k=0; k<4; k++) {
            int _x = x + dx[k];
            int _y = y + dy[k];

            if (!isValid(grid, _x, _y)) continue;

            if (grid[_x][_y] > grid[x][y]) {

                dfs(grid, dp, _x, _y);
                dp[x][y] = Math.max(dp[x][y], dp[_x][_y]+1);
            
            }
            
        }
 
    }

}

class Point {
    int x, y;

    public Point(int i, int j) {
        this.x = i;
        this.y = j;
    }

    public boolean equals(Point o) {
        return x==o.x && y==o.y;
    }

/*
    public int hashCode() {
        return x*31+y;
    }
    */
}