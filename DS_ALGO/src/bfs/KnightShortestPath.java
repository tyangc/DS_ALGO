package bfs;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/**
 Given a knight in a chessboard (a binary matrix with 0 as empty and 1 as barrier) with a source position, find the shortest path to a destination position, return the length of the route.
Return -1 if destination cannot be reached.

Example
Example 1:

Input:
[[0,0,0],
 [0,0,0],
 [0,0,0]]
source = [2, 0] destination = [2, 2] 
Output: 2
Explanation:
[2,0]->[0,1]->[2,2]
Example 2:

Input:
[[0,1,0],
 [0,0,1],
 [0,0,0]]
source = [2, 0] destination = [2, 2] 
Output:-1
Clarification
If the knight is at (x, y), he can get to the following positions in one step:

(x + 1, y + 2)
(x + 1, y - 2)
(x - 1, y + 2)
(x - 1, y - 2)
(x + 2, y + 1)
(x + 2, y - 1)
(x - 2, y + 1)
(x - 2, y - 1)
Notice
source and destination must be empty.
Knight can not enter the barrier.
Path length refers to the number of steps the knight takes.
 */
public class KnightShortestPath {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public int shortestPath(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid==null || grid.length==0|| grid[0].length==0) return -1;
        
        int n = grid.length, m=grid[0].length;
        int[] dx = {1,1,-1,-1,2,2,-2,-2};
        int[] dy = {2,-2,2,-2,1,-1,1,-1};
        
        Map<Point, Integer> map = new HashMap<>();
        Queue<Point> que = new ArrayDeque<>(); //LinkedList<>();
        
        que.offer(source);
        map.put(source, 0);
        
        while(!que.isEmpty()) {
            Point cur = que.poll();
            if (cur.x==destination.x && cur.y==destination.y) {
                return map.get(cur);
            }
            
            for (int i=0; i<dx.length; i++) {
                int x = cur.x+dx[i];
                int y= cur.y+dy[i];
                
                if (!inBound(grid, x, y) || grid[x][y]) continue;
                Point next = new Point(x,y);
                if (map.containsKey(next)) continue;
                
                que.offer(next);
                map.put(next, map.get(cur)+1);
            }
        }
        
        
        return -1;
        
        
    }
    
    public int shortestPathBiDireaction(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid==null || grid.length==0|| grid[0].length==0) return -1;
        if (source.x==destination.x && source.y==destination.y) return 0;
        int n = grid.length, m=grid[0].length;
        
        Map<Integer, Integer> mapF = new HashMap<>();
        Map<Integer, Integer> mapB = new HashMap<>();

        Queue<Integer> queF = new ArrayDeque<>();
        Queue<Integer> queB = new ArrayDeque<>();

        queF.offer(source.x*m + source.y);
        queB.offer(destination.x*m + destination.y);
        mapF.put(source.x*m + source.y, 0);
        mapB.put(destination.x*m + destination.y, 0);

        while(!queF.isEmpty() && !queB.isEmpty()) {
            int cur = queF.poll();

            for (int i=0; i<dx.length; i++) {
                int x = cur/m+dx[i];
                int y = cur%m+dy[i];
                int next = x*m+y;

                
                if (notValid(grid, x, y)) continue;
                if (mapF.containsKey(next)) continue;
                if (mapB.containsKey(next)) {
                    return mapF.get(cur) + mapB.get(next) + 1;
                }
                mapF.put(next, mapF.get(cur)+1);
                queF.offer(next);
            }

            cur = queB.poll();
            for (int i=0; i<dx.length; i++) {
                int x = cur/m+dx[i];
                int y = cur%m+dy[i];
                int next = x*m+y;

                
                if (notValid(grid, x, y)) continue;
                if (mapB.containsKey(next)) continue;
                if (mapF.containsKey(next)) {
                    return mapB.get(cur) + mapF.get(next) + 1;
                }
                mapB.put(next, mapB.get(cur)+1);
                queB.offer(next);
            }
        }

        return -1;
        
        
    }
    
    private boolean inBound(boolean[][] grid, int x, int y) {
        int n = grid.length, m=grid[0].length;
        
        return x>=0&&x<n&&y>=0&&y<m;
    }

    class Point {
    	 int x;
    	 int y;
    	 Point() { x = 0; y = 0; }
    	 Point(int a, int b) { x = a; y = b; }
    }
    
    /**
     * @param grid: a chessboard included 0 (false) and 1 (true)
     * @param source: a point
     * @param destination: a point
     * @return: the shortest path 
     */
    public static final int[] dx = {1,1,-1,-1,2,2,-2,-2};
    public static final int[] dy = {2,-2,2,-2,1,-1,1,-1}; 
     
    public int shortestPath1(boolean[][] grid, Point source, Point destination) {
        // write your code here
        if (grid==null || grid.length==0|| grid[0].length==0) return -1;
        
        int n = grid.length, m=grid[0].length;
        
        
        Map<Integer, Integer> map = new HashMap<>();
        Queue<Integer> que = new ArrayDeque<>(); //LinkedList<>();
        
        que.offer(source.x*m+source.y);
        map.put(source.x*m+source.y, 0);
        
        while(!que.isEmpty()) {
            int cur  = (int)que.poll();
           
            if (cur/m==destination.x && cur%m==destination.y) {
                return (int)map.get(cur);
            }
            
            for (int i=0; i<dx.length; i++) {
                int x = cur/m+dx[i];
                int y= cur%m+dy[i];
                
                if (notValid(grid, x, y)) continue;
                int next = x*m+y;
                if (map.containsKey(next)) continue;
                
                que.offer(next);
                map.put(next, map.get(cur)+1);
            }
        }
        
        
        return -1;
        
        
    }
    
    private boolean notValid(boolean[][] grid, int x, int y) {
        int n = grid.length, m=grid[0].length;
        
        if (x<0 || x>=n || y<0 || y>=m) return true;
        
        return grid[x][y];
    }
}
