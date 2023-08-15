package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;

/*
 * 573 · Build Post Office II
Algorithms
Hard
Accepted Rate
37%
Description
Solution41
Notes99+
Discuss
Leaderboard
Record
Description
Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find a place to build a post office so that the sum of the distance from the post office to all the houses is smallest.

Returns the sum of the minimum distances from all houses to the post office.Return -1 if it is not possible.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


You cannot pass through wall and house, but can pass through empty.
You only build post office on an empty.
Example
Example 1:

Input：[[0,1,0,0,0],[1,0,0,2,1],[0,1,0,0,0]]
Output：8
Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
Example 2:

Input：[[0,1,0],[1,0,1],[0,1,0]]
Output：4
Explanation： Placing a post office at (1,1), the distance that post office to all the house sum is smallest.
Tags
Company
Zenefits
Google
Related Problems

574
Build Post Office
Medium

598
Zombie in Matrix
Medium

663
Walls and Gates
Medium

803
Shortest Distance from All Buildings
Hard
 */
//(1) BFS , starting from empty spaces (possible post office locations)
public class PostOfficeII {
	public int shortestDistance(int[][] grid) {
        // write your code here
        int n=grid.length, m=grid[0].length;
        int ans = Integer.MAX_VALUE;

        Queue<int[]> que = new ArrayDeque<>();
        List<int[]> officeList = new ArrayList<>();
        int houses = 0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == GridType.EMPTY) {
                    
                    officeList.add(new int[]{i, j});
                }

                if (grid[i][j] == GridType.HOUSE) houses++;
            }
        }

        for (int[] office : officeList) {
            que.offer(office);
            int ret = getDistanceBfs(grid, que, houses);
            ans = Math.min(ans, ret);
        }


        /*
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 0) {

                    que.offer(new int[]{i, j});
                    int ret = getDistance(grid, que, houses);
                    ans = Math.min(ans, ret);
                }
            }
        }
        */

        return ans == Integer.MAX_VALUE ? -1 : ans;

    }

    int getDistanceBfs(int[][] grid, Queue<int[]> que, int houses) {
        int n=grid.length, m=grid[0].length;
        int[][] visited = new int[n][m];
        int ans = 0;
        while(!que.isEmpty()) {
            int[] dx = {1, 0, -1, 0};
            int[] dy = {0, -1, 0, 1};
            int[] cur = que.poll();
            int node = grid[cur[0]][cur[1]];
            int curDis = visited[cur[0]][cur[1]];
            
            for (int i=0; i<dx.length; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                if (isValid(grid, visited, x, y)) {
                    if (grid[x][y] == GridType.HOUSE) {
                        ans += curDis + 1;
                        //visited[x][y] = curDis + 1;
                        houses--;
                    } else if (grid[x][y] == GridType.EMPTY) {
                        que.offer(new int[]{x, y});
                        //visited[x][y] = curDis + 1;
                    }
                    visited[x][y] = curDis + 1;
                }
            }

        }
        return houses==0 ? ans : Integer.MAX_VALUE;
    }

    boolean isValid(int[][] grid, int[][] visited, int x, int y) {
        int n=grid.length, m=grid[0].length;
        if (x<0 || x>=n || y<0 || y>=m) return false;
        if (grid[x][y] == GridType.WALL || visited[x][y] > 0) return false;

        return true;
    }
    
    
    //(2) BFS , starting from house spots, optimized if houses are scarce
    public int shortestDistance1(int[][] grid) {
        // write your code here
        int n=grid.length, m=grid[0].length;
        //Global tier counters
        int[][] distanceSum = new int[n][m];
        int[][] countSum = new int[n][m];
        Queue<int[]> que = new ArrayDeque<>();

        int houses = 0;

        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == GridType.HOUSE) {
                    houses++;
                    int[][] visited = new int[n][m];  //initialize local values                 

                    que.offer(new int[]{i, j});
                    bfs(grid, distanceSum, countSum, visited, que);
                    //printGrid(distanceSum);
                    //System.out.println("-------");
                    //printGrid(countSum);
                    //System.out.println("~~~~~~~");
                }
            }
        }

        //System.out.println(houses);
        
        int minSum = Integer.MAX_VALUE;
        //int sum=0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == GridType.EMPTY) {
                    if (countSum[i][j] == houses) {
                        minSum = Math.min(minSum, distanceSum[i][j]);
                        //sum +=distanceSum[i][j]; 
                    }
                }
            }
        }
        return minSum==Integer.MAX_VALUE ? -1 : minSum;
    }

    void bfs(int[][] grid, int[][] distanceSum, int[][] countSum, int[][] visited, Queue<int[]> que) {
        while(!que.isEmpty()) {
            int[] dx = {0, -1, 0, 1};
            int[] dy = {1, 0, -1, 0}; 
            int[] cur = que.poll();
            for (int i=0; i<dx.length; i++) {
                int x = cur[0] + dx[i];
                int y = cur[1] + dy[i];
                if (!isValid1(grid, visited, x, y)) continue;

                visited[x][y] = visited[cur[0]][cur[1]] + 1;  //Local values
                distanceSum[x][y] += visited[x][y];  //Global tier incremented by local tier value
                countSum[x][y]++;
                
                que.offer(new int[]{x, y});
            }
        }
    }

    

    boolean isValid1(int[][] grid, int [][] visited, int x, int y) {
        int n=grid.length, m=grid[0].length;
        if (x<0 || x>=n || y<0 || y>=m) return false;
        if (grid[x][y] == GridType.WALL || grid[x][y] == GridType.HOUSE || visited[x][y] > 0) return false;

        return true;
    }

    //For debugging
    void printGrid(int[][] grid) {
        int n=grid.length, m=grid[0].length;
        for (int i=0; i<n; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }

    }
}

class GridType {
    static int EMPTY=0;
    static int HOUSE=1;
    static int WALL=2;
}
