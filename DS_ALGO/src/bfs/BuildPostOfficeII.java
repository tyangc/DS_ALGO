package bfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
 * 573 · Build Post Office II
Algorithms
Hard
Accepted Rate
36%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a 2D grid, each cell is either a wall 2, an house 1 or empty 0 (the number zero, one, two), find a place to build a post office so that the sum of the distance from the post office to all the houses is smallest.

Returns the sum of the minimum distances from all houses to the post office.Return -1 if it is not possible.

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
Breadth First Search/BFS
Enumerate
Company
Zenefits
Google
Related Problems
803
Shortest Distance from All Buildings
Hard
663
Walls and Gates
Medium
598
Zombie in Matrix
Medium
574
Build Post Office
Medium
 */
public class BuildPostOfficeII {
	//The following method using pos and hashMap won't work:   Pos can not be used with Map<pos, dist> together
	class Pos {
        int r, c;

        public Pos(int row, int col) {
            this.r = row;
            this.c = col;
        }
    }

    public int shortestDistance(int[][] grid) {
        // write your code here
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;

        int num = 0, res = Integer.MAX_VALUE, n = grid.length, m = grid[0].length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 1) {
                    num++;
                }
            
            }
        }

        System.out.println("num " + num);
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 0) {
                    int ret = 0;
                    if (i==1 && j==1) ret = getDistance(i, j, grid, num, true);
                    ret = getDistance(i, j, grid, num, false);
                    System.out.println(i + "|" + j + "|" + ret);
                    res = Math.min(res, ret);
                    //System.out.println(res);
                }
            }
        }

        if (res == Integer.MAX_VALUE) return -1;
        return res;
    }

    private int getDistance(int r, int c, int[][] grid, int num, boolean debug) {

        int  dis = 0, n = grid.length, m = grid[0].length;
        int[] dx = {0,1,-1,0}; //{1, 0, -1, 0};
        int[] dy = {1,0,0,-1}; //{0, 1, 0, -1};
        Map<Pos, Integer> map = new HashMap<>();
        Queue<Pos> que = new ArrayDeque<>();
        Pos root = new Pos(r, c);
        que.offer(root);
        map.put(root, 0);

        while(!que.isEmpty()) {
            //int siz = que.size();
            //for (int i=0; i<siz; i++) {
                Pos cur = que.poll();
                if (grid[cur.r][cur.c] == 1) {
                    dis+= map.get(cur);
                    num--;
                    if (num==0) return dis;
                    continue;
                }
                if(debug) System.out.println(cur.r + "|" + cur.c);
                for (int j=0; j<4; j++) {
                    int nr = cur.r + dy[j];
                    int nc = cur.c + dx[j];
                    Pos next = new Pos(nr, nc);
                    if (isValid(next, grid, map)) {
                        /*
                        if (grid[nr][nc] == 1 && !map.containsKey(next)) {
                            dis += map.get(cur)+1;
                            
                            num--;
                            if (num==0) return dis;
                        } else {
                            que.offer(next);
                           // map.put(next, map.get(cur)+1);
                        }
                        map.put(next, map.get(cur)+1);
                        */
                        que.offer(next);
                        map.put(next, map.get(cur)+1);
                    }
                }
            //}
            
        }

        if (num>0) return Integer.MAX_VALUE;
        return dis;

    }

    private boolean isValid(Pos pos, int[][] grid, Map<Pos, Integer> map) {
        int n = grid.length, m = grid[0].length, r = pos.r, c = pos.c;

        if (r<0 || r>=n || c<0 || c>=m) return false;
        if (map.containsKey(pos)) return false;
        return grid[r][c] != 2;
    }
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~
    //The following method using r*col + c works:
    
    public int shortestDistance1(int[][] grid) {
        // write your code here
        if (grid==null || grid.length==0 || grid[0].length==0) return 0;

        int num = 0, res = Integer.MAX_VALUE, n = grid.length, m = grid[0].length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 1) {
                    num++;
                }
            
            }
        }

        //System.out.println("num " + num);
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (grid[i][j] == 0) {
                    //int ret = 0;
                    //if (i==1 && j==1) ret = getDistance(i, j, grid, num, true);
                    //int ret = getDistance(i, j, grid, num, false);
                   // System.out.println(i + "|" + j + "|" + ret);
                    res = Math.min(res, getDistance1(i, j, grid, num, false));
                    //System.out.println(res);
                }
            }
        }

        if (res == Integer.MAX_VALUE) return -1;
        return res;
    }

    private int getDistance1(int r, int c, int[][] grid, int num, boolean debug) {

        int  dis = 0, n = grid.length, m = grid[0].length;
        int[] dx = {0,1,-1,0}; //{1, 0, -1, 0};
        int[] dy = {1,0,0,-1}; //{0, 1, 0, -1};
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> que = new ArrayDeque<>();
        
        que.offer(r*m+c);
        map.put(r*m+c, 0);

        while(!que.isEmpty()) {
            //int siz = que.size();
            //for (int i=0; i<siz; i++) {
                Integer cur = que.poll();
                /*
                if (grid[cur/m][cur%m] == 1) {
                    dis+= map.get(cur);
                    num--;
                    if (num==0) return dis;
                   // continue;
                }
                */
                //if(debug) System.out.println(cur.r + "|" + cur.c);
                for (int j=0; j<4; j++) {
                    int nr = cur/m + dy[j];
                    int nc = cur%m + dx[j];
                    int next = nr*m + nc;
                    //Pos next = new Pos(nr, nc);
                    if (isValid(nr, nc, grid, map)) {
                        
                        if (grid[nr][nc] == 1) {
                            dis += map.get(cur)+1;
                            
                            num--;
                            if (num==0) return dis;
                        } else {
                            que.offer(next);
                           // map.put(next, map.get(cur)+1);
                        }
                        map.put(next, map.get(cur)+1);
                        
                       // que.offer(nr*m+nc);
                       // map.put(nr*m+nc, map.get(cur)+1);
                    }
                }
            //}
            
        }

        if (num>0) return Integer.MAX_VALUE;
        return dis;

    }

    private boolean isValid(int r, int c, int[][] grid, Map<Integer, Integer> map) {
        int n = grid.length, m = grid[0].length;

        if (r<0 || r>=n || c<0 || c>=m) return false;
        if (map.containsKey(r*m+c)) return false;
        return grid[r][c] != 2;
    }
}
