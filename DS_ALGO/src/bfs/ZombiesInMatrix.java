package bfs;

import java.util.ArrayDeque;
import java.util.Queue;

/*
 * 598 · Zombie in Matrix
Algorithms
Medium
Accepted Rate
43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Give a two-dimensional grid, each grid has a value, 2 for wall, 1 for zombie, 0 for human (numbers 0, 1, 2).Zombies can turn the nearest people(up/down/left/right) into zombies every day, 
but can not through wall. How long will it take to turn all people into zombies? Return -1 if can not turn all people into zombies.

Example
Example 1:

Input:
[[0,1,2,0,0],
 [1,0,0,2,1],
 [0,1,0,0,0]]
Output:
2
Example 2:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,1]]
Output:
4
Tags
Breadth First Search/BFS
Related Problems
573
Build Post Office II
Hard
Recommend Courses

 */
public class ZombiesInMatrix {
	/**
     * @param grid: a 2D integer grid
     * @return: an integer
     */
    public int zombie(int[][] grid) {
        // write your code here
        if (grid == null || grid[0] == null) return 0;
        int cnt = 0, converted = 0, days = 0, n = grid.length, m = grid[0].length; 
        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};
        Queue<Integer> que = new ArrayDeque<>();
 
        for (int i=0; i<grid.length; i++) {
          for (int j=0; j<grid[0].length; j++) {
            if (grid[i][j] == 1) {
              que.offer(i*m + j);
            } else if (grid[i][j] == 0) {
              cnt++;
            }
          }
        }
 
        if (cnt==0) return 0;
 
        while (!que.isEmpty()) {
          days++;
          int size = que.size();
          for (int i=0; i<size; i++) {
            int cur = que.poll();
            for (int k=0; k<dx.length; k++) {
              int nx = cur/m + dx[k];
              int ny = cur%m + dy[k];
              if (isValid(nx, ny, grid)) {
                que.offer(nx*m + ny);
                grid[nx][ny] = 1;
                //converted++;
                cnt--;
                if (cnt == 0) return days;   //Good way!!!
              }
            }
          }
          //days++;
        }
 
        /*
        if (converted < cnt) {
          return -1;
        } 
        return days-1;
        */
        return -1;
    }
    
    public int zombieAnotherImpletation(int[][] grid) {
        // write your code here
        if (grid == null || grid[0] == null) return 0;
        int n=grid.length, m=grid[0].length, cnt=0, days=0;
        Queue<int[]> que = new ArrayDeque<>();

        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        for (int i=0; i<n; i++) {
          for (int j=0; j<m; j++) {
            if (grid[i][j]==0) cnt++;
            if (grid[i][j]==1) que.offer(new int[] {i, j});
          }
        }

        while(!que.isEmpty()) {
          int siz = que.size();
          days++;
          for (int i=0; i<siz; i++) {
            
            int[] cur = que.poll();
            for (int k=0; k<dx.length; k++) {
              int x = cur[0] + dx[k];
              int y = cur[1] + dy[k];
              if (!isValid(x, y, grid)) continue;
              grid[x][y] = 1;
              cnt--;
              que.offer(new int[]{x, y});
            }
          }
  
        }

        if (cnt!=0) return -1;
        else return days-1;
    }
 
    private boolean isValid(int x, int y, int[][] grid) {
      int n = grid.length, m = grid[0].length; 
      if (x<0 || x>=n || y<0 || y>=m || grid[x][y]!=0) return false;
      return true;
    }
	
}
