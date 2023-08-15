package bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/*
 Given a boolean 2D matrix, 0 is represented as the sea, 1 is represented as the island. If two 1 is adjacent, we consider them in the same island. We only consider up/down/left/right adjacent.

Find the number of islands.

Example
Example 1:

Input:
[
  [1,1,0,0,0],
  [0,1,0,0,1],
  [0,0,0,1,1],
  [0,0,0,0,0],
  [0,0,0,0,1]
]
Output:
3
Example 2:

Input:
[
  [1,1]
]
Output:
1
 */
public class NumberOfIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param grid: a boolean 2D matrix
     * @return: an integer
     */
    public int numIslands(boolean[][] grid) {
        // write your code here
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;
        
        boolean[][] visited = Arrays.stream(grid).map(boolean[]::clone).toArray(boolean[][]::new);
        
        //Queue<int[]> que = new LinkedList<>();
        Queue<int[]> que = new ArrayDeque<>();  //Seems faster than LinkedList
        
        int n=grid.length, m=grid[0].length, count=0;
        //int[] dx = new int[] {1, 0, -1, 0};
        //int[] dy = new int[] {0, 1, 0, -1};
        
        int[] dx =  {1, 0, -1, 0};
        int[] dy = {0, 1, 0, -1};
        
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (visited[i][j]) {
                    count++;
                    que.offer(new int[] {i, j});
                    
                    while(!que.isEmpty()) {
                        int[] cur = que.poll();
                        for (int k=0; k<dx.length; k++) {
                            int x = cur[0] + dx[k], y=cur[1] + dy[k];
                            if (inBound(visited, x, y) && visited[x][y]) {
                                visited[x][y] = false;
                                que.offer(new int[]{x, y});
                            }
                            
                        }
                    }
                    
                }   
            }
        }
        
        return count;
    }
    
    //Shared by both approaches
    private boolean inBound(boolean[][] grid, int x, int y) {
        int n=grid.length, m=grid[0].length;
        
        return x>=0 && x<n && y>=0 && y<m;
    }
    
    //Another implementation, using compressed coordination and better coding style with subroutine for bfs section
    public int numIslands1(boolean[][] grid) {
        // write your code here
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;
        
        boolean[][] visited = Arrays.stream(grid).map(boolean[]::clone).toArray(boolean[][]::new);
        Queue<Integer> que = new LinkedList<>();

        int n=grid.length, m=grid[0].length, count=0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (visited[i][j]) {  //if use grid[i][j] here, then it is wrong!!! more counts will be returned
                    count++;
                    visited[i][j] = false;  //Not necessary here, since the scanner won't go back
                    que.offer(i*m+j);
                    bfs(visited, que);
                }
            }
        }
        
        return count;
    }

    void bfs(boolean[][] visited, Queue<Integer> que) {
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        int m = visited[0].length;

        while(!que.isEmpty()) {
            int cur = que.poll();
            int i = cur / m;
            int j = cur % m;
            for (int k=0; k<dx.length; k++) {
                int x = i + dx[k];
                int y = j + dy[k];
                if (inBound(visited, x, y) && visited[x][y]) {
                    visited[x][y] = false;
                    que.offer(x*m + y);
                }
            }
        }
        return;
    }
}
