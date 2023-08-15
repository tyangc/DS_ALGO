package bfs;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

//import bfs.KnightShortestPath.PointLen;

/*
 788 · The Maze II
Medium Accepted Rate 55%

DescriptionSolutionNotesDiscussLeaderboard
Description
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up, down, left or right, but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction.

Given the ball's start position, the destination and the maze, find the shortest distance for the ball to stop at the destination. The distance is defined by the number of empty spaces traveled by the ball from the start position (excluded) to the destination (included). If the ball cannot stop at the destination, return -1.

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The start and destination coordinates are represented by row and column indexes.

1.There is only one ball and one destination in the maze.
2.Both the ball and the destination exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
4.The maze contains at least 2 empty spaces, and both the width and height of the maze won't exceed 100.

Example
Example 1:
	Input:  
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (4,4)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  12
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(2,0)->(2,1)->(2,2)->(3,2)->(4,2)->(4,3)->(4,4)

Example 2:
	Input:
	(rowStart, colStart) = (0,4)
	(rowDest, colDest)= (0,0)
	0 0 1 0 0
	0 0 0 0 0
	0 0 0 1 0
	1 1 0 1 1
	0 0 0 0 0

	Output:  6
	
	Explanation:
	(0,4)->(0,3)->(1,3)->(1,2)->(1,1)->(1,0)->(0,0)
	
 */
public class MazeII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        // write your code here
        int n = maze.length, m = maze[0].length;
        Queue<PointLen> que = new PriorityQueue<>((p1, p2) -> (p1.len - p2.len));
        que.add(new PointLen(start[0], start[1], 0));
        boolean[][] visited = new boolean[n][m];
        //visited[start[0]][start[1]] = true;
        //int level = 0, step = 0, res=Integer.MAX_VALUE;
        int[] dx = {0, 0, 1, -1};
        int[] dy = {1, -1, 0, 0};

        while(!que.isEmpty()) {
          PointLen cur = que.poll();
          int x = cur.x, y = cur.y;
          System.out.println(x + "|" + y + "|" + cur.len);
          if (x == destination[0] && y == destination[1]) {  
            return cur.len; 
          }    

          if (visited[x][y]) continue;
          visited[x][y] = true;

          //int nx = x, ny = y, nlen = cur.len;
          for (int i=0; i<dx.length; i++) {
            int nx = x, ny = y, nlen = cur.len;
            while(nx>=0 && nx<n && ny>=0 && ny<m && maze[nx][ny]==0) {
              nx += dx[i];
              ny += dy[i];
              nlen +=1;
            }

            nx = nx - dx[i];
            ny = ny - dy[i];
            nlen--;
            if (nx==x && ny==y) continue;
            que.offer(new PointLen(nx, ny, nlen));

          }

        }

        return -1;

    }
          

	
	
	 /**
     * @param maze: the maze
     * @param start: the start
     * @param destination: the destination
     * @return: the shortest distance for the ball to stop at the destination
     */
	
	//only 79% passed
    public int shortestDistanceParitalPassed(int[][] maze, int[] start, int[] destination) {
        // write your code here

        Queue<PointLen> que = new LinkedList<>();
        que.add(new PointLen(start[0], start[1]));
        boolean[][] visited = new boolean[maze.length][maze[0].length];
        //visited[start[0]][start[1]] = true;
        int level = 0, step = 0, res=Integer.MAX_VALUE;
        while(!que.isEmpty()) {
          int size = que.size();
          System.out.println("size: " + size);
          for (int i=0; i<size; i++) {
            PointLen cur = que.poll();
            if (cur.x==destination[0] && cur.y == destination[1]) {
                PointLen p = cur;
                step = 0;
                  while(p.prev!=null) {
                    step += steps(p, p.prev);
                    p = p.prev;
                  } 
                res = Math.min(res, step);
                //continue;
            }
            if (visited[cur.x][cur.y]) continue;
            visited[cur.x][cur.y] = true;
            int[][] next = goNext(maze, cur);
            for (int j=0; j<4; j++) {
               int nx = next[j][0], ny = next[j][1];
               PointLen nxt = new PointLen(nx, ny);
               System.out.println(nx + "|" + ny);
               
               //return step;//level; 
               
               if (visited[nx][ny]) continue;
               if (nx!=cur.x || ny!=cur.y) {
                 
                 nxt.prev = cur;
                 que.offer(nxt);
                // visited[nx][ny] = true;
               }
            }
          }
          level++;
          System.out.println(level);
        }

        if (res==Integer.MAX_VALUE) return -1;
        return res;
    }

    int[][] goNext(int[][] maze, PointLen p) {
        int x = p.x, y = p.y;
        int[][] res = new int[4][2];
        int i=0;
        //go up
        while( x-i>=0 && maze[x-i][y]==0 ) {
          i++;
        }

        
          res[0][0] = x-i+1;
          res[0][1] = y;
        
        //go down
        i=0;
        while( x+i<maze.length && maze[x+i][y]==0 ) {
          i++;
        }

        
          res[1][0] = x+i-1;
          res[1][1] = y;
        
        //go left
        i=0;
        while(y-i>=0 && maze[x][y-i]==0 ) {
          i++;
        }

        res[2][0] = x;
        res[2][1] = y-i+1;

        //go right

        i=0;
        while( y+i<maze[0].length && maze[x][y+i]==0) {
          i++;
        }

        res[3][0] = x;
        res[3][1] = y+i-1;

        return res;

    }

    int steps(PointLen a, PointLen b) {

      if (a.x == b.x) {
        return Math.abs(a.y-b.y);
      } else {
        return Math.abs(a.x-b.x);
      }

    }
    
    class PointLen {
    	  int x, y;
    	  PointLen prev;
    	  int len;


    	  PointLen(int x, int y) {
    	    this.x = x;
    	    this.y = y;
    	    prev = null;
    	  }
    	  
    	  PointLen(int x, int y, int len) {
    		    this.x = x;
    		    this.y = y;
    		    this.len = len;
    		  }
    		      
    	}
    
    
}


