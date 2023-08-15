package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 
https://www.lintcode.com/problem/598/  zombie in matrix
https://leetcode.com/problems/rotting-oranges/submissions/
994. Rotting Oranges
Medium

3528

217

Add to List

Share
You are given an m x n grid where each cell can have one of three values:

0 representing an empty cell,
1 representing a fresh orange, or
2 representing a rotten orange.
Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.

Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.

 

Example 1:


Input: grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
Example 2:

Input: grid = [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation: The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
Example 3:

Input: grid = [[0,2]]
Output: 0
Explanation: Since there are already no fresh oranges at minute 0, the answer is just 0.
 

Constraints:

m == grid.length
n == grid[i].length
1 <= m, n <= 10
grid[i][j] is 0, 1, or 2.
Accepted
222,179
Submissions
445,842
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
27

Google
|
3

Microsoft
|
3

Oracle
|
2

Accolite
|
2

Walmart Labs
|
2

Samsung
|
2
 */
public class RottingOrange {
	int[] dx = new int[] {0, -1, 0, 1};
    int[] dy = new int[] {1, 0, -1, 0};
    
    public int orangesRotting(int[][] grid) {
    	if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int m = 2;
        Queue<Point> que = new LinkedList<>();
        while(helper(grid, que, m)) {
            while(!que.isEmpty()) {
                Point p = que.poll();
                grid[p.x][p.y] = m+1;
            }
            m++;
        }
        
        if (hasFresh(grid)) return -1;
        return m-2;
    }
    
    private boolean helper(int[][] grid, Queue<Point> que, int min){
        int m = grid.length, n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]==min) {
                    for (int k=0; k<4; k++) {
                        int x = i+dx[k];
                        int y = j+dy[k];
                        if (isValid(grid, x, y) && grid[x][y]==1) {
                            que.offer(new Point(x, y));
                        }
                    }
                } 
            }
        }
        
        return que.size()>0;
    }
    
    private boolean isValid(int[][] grid, int x, int y) {
        int m = grid.length, n = grid[0].length;
        return x>=0 && x<m && y>=0 && y<n;
    }
    
    private boolean hasFresh(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (grid[i][j]==1) return true;
            }
        }
        
        return false;
    }
}

class Point {
    int x, y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
