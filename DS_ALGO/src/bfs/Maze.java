package bfs;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/*
 787 · The Maze
Medium Accepted Rate 47%

DescriptionSolutionNotesDiscussLeaderboard
Description
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, determine whether the ball could stop at the destination.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
5.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

Example
Example 1:

Input:
map = 
[
 [0,0,1,0,0],
 [0,0,0,0,0],
 [0,0,0,1,0],
 [1,1,0,1,1],
 [0,0,0,0,0]
]
start = [0,4]
end = [3,2]
Output:
false
Example 2:

Input:
map = 
[[0,0,1,0,0],
 [0,0,0,0,0],
 [0,0,0,1,0],
 [1,1,0,1,1],
 [0,0,0,0,0]
]
start = [0,4]
end = [4,4]
Output:
true
 */
public class Maze {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
	
	/**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: whether the ball could stop at the destination
     */
    public boolean hasPath(int[][] maze, int[] start, int[] destination) {
        // write your code here
        Queue<Point> que = new LinkedList<>();
        
        int n = maze.length, m = maze[0].length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0}; 

        boolean[][] visited = new boolean[n][m];
        que.add(new Point(start[0], start[1]));
     
        while(!que.isEmpty()) {
          
            Point cur = que.poll();
            int x = cur.x, y = cur.y;
            if (x==destination[0] && y==destination[1]) {
              return true;
            }

            if (visited[x][y]) continue;
            visited[x][y] = true;

            for (int i=0; i<4; i++) {
              int nx = x, ny = y;
              while(nx>=0 && nx<n && ny>=0 && ny<m && maze[nx][ny]==0) {
                nx += dx[i];
                ny += dy[i];

              }

              nx -= dx[i];
              ny -= dy[i];

              if (x==nx && y==ny) continue;
              que.add(new Point(nx, ny));
            }
          
        }

        return false;
    }

    

	
	//Double Side approach won't work in a straightforward way  !!!
    public boolean hasPathDoubleDirection(int[][] maze, int[] start, int[] destination) {
        // write your code here
        Queue<Point> queForward = new LinkedList<>();
        Queue<Point> queBackward = new LinkedList<>();
        int n = maze.length, m = maze[0].length;
        boolean[][] visitedForward = new boolean[n][m];
        boolean[][] visitedBackward = new boolean[n][m];

        queForward.add(new Point(start[0], start[1]));
        queBackward.add(new Point(destination[0], destination[1]));

        
        AtomicBoolean res = new AtomicBoolean(false);
        while(!queForward.isEmpty() || !queBackward.isEmpty()) {
          helper(maze, queForward, visitedForward, visitedBackward, res);
          if (res.get()) return true;
          helper(maze, queBackward, visitedBackward, visitedForward, res);
          if (res.get()) return true;
        }

        return false;
    }

    void helper(int[][] maze, Queue<Point> que, boolean[][] thisVisited, boolean[][] otherVisited, AtomicBoolean res) {
        int n = maze.length, m = maze[0].length;
        int[] dx = {0, 0, -1, 1};
        int[] dy = {1, -1, 0, 0}; 

        if (!que.isEmpty()) {
            Point cur = que.poll();
            int x = cur.x, y = cur.y;
            if (otherVisited[x][y]) {
              res.set(true);
              return;
            }

            if (thisVisited[x][y]) return;
            thisVisited[x][y] = true;

            for (int i=0; i<4; i++) {
              int nx = x, ny = y;
              while(nx>=0 && nx<n && ny>=0 && ny<m && maze[nx][ny]==0) {
                nx += dx[i];
                ny += dy[i];

              }

              nx -= dx[i];
              ny -= dy[i];

              if (x==nx && y==ny) continue;
              que.add(new Point(nx, ny));
            }
          }
    }
	
    class Point {
  	  int x, y;
  	 
  	  Point(int x, int y) {
  	    this.x = x;
  	    this.y = y;
  	    
  	  }
  	  
  	}
}


