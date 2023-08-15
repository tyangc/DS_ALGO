package bfs;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://www.lintcode.com/problem/630/description
 * Description
Given a knight in a chessboard n * m (a binary matrix with 0 as empty and 1 as barrier). the knight initialze position is (0, 0) and he wants to reach position (n - 1, m - 1), Knight can only be from left to right. Find the shortest path to the destination position, return the length of the route. Return -1 if knight can not reached.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x - 1, y + 2)
(x + 2, y + 1)
(x - 2, y + 1)
Example
Example 1:

Input:
[[0,0,0,0],[0,0,0,0],[0,0,0,0]]
Output:
3
Explanation:
[0,0]->[2,1]->[0,2]->[2,3]
Example 2:

Input:
[[0,1,0],[0,0,1],[0,0,0]]
Output:
-1
Tags
Company
Amazon
 */
public class KnightShortestPathII {
	public int shortestPath2(boolean[][] grid) {
        // write your code here
        if(grid==null || grid.length==0 || grid[0].length<=1) return -1;
        int res = Integer.MAX_VALUE;
        int n=grid.length, m = grid[0].length;
        int[] dx = new int[] { 1, -1, 2, -2};
        int[] dy = new int[] { 2, 2, 1, 1};
        Queue<int[]> que = new LinkedList<>();
        que.offer(new int[] {0, 0, 0});
        
        while(!que.isEmpty()) {
        	int[] cur = que.poll();
        	
        	int x=cur[0], y= cur[1], step=cur[2];
        	if (x==n-1&&y==m-1) res = Math.min(res, step);
        	
        	 for (int k=0; k<dx.length; k++) {
        		 int i=x+dx[k], j=y+dy[k];
        		 if (i<0 || j<0 || i>=n || j>=m || grid[i][j]) continue;
        		 grid[i][j]=true;
        		 que.offer(new int[]{i, j, step+1});
        	 }
        }
        
        if (res==Integer.MAX_VALUE) return -1;
        return res;
        
        
    }
}
