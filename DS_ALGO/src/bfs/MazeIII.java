package bfs;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*  https://www.lintcode.com/problem/789/
789 · The Maze III
Algorithms
Hard
Accepted Rate
47%
Description
Solution19
Notes31
Discuss4
Leaderboard
Record
Description
There is a ball in a maze with empty spaces and walls. The ball can go through empty spaces by rolling up (u), down (d), left (l) or right (r), but it won't stop rolling until hitting a wall. When the ball stops, it could choose the next direction. There is also a hole in this maze. The ball will drop into the hole if it rolls on to the hole.

Given the position of the ball, the position of the hole and the maze, find out how the ball falls into the hole by moving the shortest distance. The distance is defined by the number of empty spaces the ball passes from the starting position (excluded) to the hole (included). Use "u", "d", "l" and "r" to output the direction of movement. Since there may be several different shortest paths, you should output the shortest method in alphabetical order. If the ball doesn't go into the hole, output "impossible".

The maze is represented by a binary 2D array. 1 means the wall and 0 means the empty space. You may assume that the borders of the maze are all walls. The ball and the hole coordinates are represented by row and column indexes.

Wechat reply 【Two Sigma】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


1.There is only one ball and one hole in the maze.
2.Both the ball and hole exist on an empty space, and they will not be at the same position initially.
3.The given maze does not contain border (like the red rectangle in the example pictures), but you could assume the border of the maze are all walls.
4.The maze contains at least 2 empty spaces, and the width and the height of the maze won't exceed 30.

Example
Example 1:

Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[4,3]
[0,1]

Output:
"lul"
Example 2:

Input:
[[0,0,0,0,0],[1,1,0,0,1],[0,0,0,0,0],[0,1,0,0,1],[0,1,0,0,0]]
[0,0]
[1,1]
[2,2]
[3,3]
Output:
"impossible"
Tags
Breadth First Search/BFS
Related Problems

787
The Maze
Medium

788
The Maze II
Medium

1685
The mazeIV
Medium
*/
public class MazeIII {
	/**
     * @param maze: the maze
     * @param ball: the ball position
     * @param hole: the hole position
     * @return: the lexicographically smallest way
     */
    public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
        // write your code here
        int n = maze.length, m = maze[0].length;
        Queue<int[]> que = new ArrayDeque<>();

        PointValue[][] visited = new PointValue[n][m];
        visited[ball[0]][ball[1]] = new PointValue(0, "");
        //Map<int[], PointValue> map = new HashMap<>();

        Map<String, int[]> map = new HashMap<>();
        map.put("d", new int[]{1, 0});
        map.put("l", new int[]{0, -1}); 
        map.put("u", new int[]{-1, 0});
        map.put("r", new int[]{0, 1});
        que.offer(new int[]{ball[0], ball[1]});

        while(!que.isEmpty()) {
            int[] cur = que.poll();
            int x = cur[0], y = cur[1];

            for (String direction : map.keySet()) {
                String prevPath = visited[x][y] == null ? "" : visited[x][y].path;
                int prevSteps = visited[x][y] == null ? 0 : visited[x][y].steps;
                if (prevPath.length()>0 && direction.equals(prevPath.substring(prevPath.length()-1))) continue;
                int[] next = kickToNext(maze, cur, direction, map.get(direction)[0], map.get(direction)[1], visited, hole);
                //printVisited(visited);
                int newSteps = Math.abs(next[0]-x) + Math.abs(next[1]-y) + prevSteps;
                String newPath = prevPath + direction;
                PointValue newPoint = new PointValue(newSteps, newPath);
                PointValue nextPoint = visited[next[0]][next[1]];
                if ( nextPoint == null || nextPoint.steps>newSteps || (nextPoint.steps==newSteps && nextPoint.path.compareTo(newPath)>0)) { 
                //if ( nextPoint == null || nextPoint.compareTo(newPoint)>0) { 
                  //(nextPoint.steps==newPoint.steps && nextPoint.compareTo(newPoint)>0)) {
                    visited[next[0]][next[1]] = newPoint;
                    if (next[0] != hole[0] || next[1] != hole[1]) {
                        que.offer(next);
                    }
                }
            }
        }

        return  visited[hole[0]][hole[1]] == null ? "impossible": visited[hole[0]][hole[1]].path;
    }

    int[] kickToNext(int[][] maze, int[] cur, String direction, int dx, int dy, PointValue[][] visited, int[] hole) {
        int x = cur[0], y = cur[1];
        PointValue curValue = visited[x][y];
        while(isValid(maze, x, y, hole)) {
            x += dx;
            y += dy;
        }

        //int newSteps = x==cur[0] ? y-cur[1]-1 : x-cur[0]-1;
        //if (x==hole[0] && y==hole[1]) newSteps = x==cur[0] ? y-cur[1] : x-cur[0];
       
        //PointValue newPointVal = new PointValue(curValue.steps+newSteps, curValue.path+direction);
       /*
        if (visited[x][y] == null || visited[x][y].compareTo(newPointVal)>0) {
            visited[x][y] = newPointVal;
        } */

        if (x==hole[0] && y==hole[1]) {
            /*
            if (visited[x][y] == null || visited[x][y].compareTo(newPointVal)>0) {
                visited[x][y] = newPointVal;
            } */
            return new int[]{x, y};

        } else {
            /*
            if (visited[x-dx][y-dy] == null || visited[x-dx][y-dy].compareTo(newPointVal)>0) {
                visited[x-dx][y-dy] = newPointVal;
            } */
            return new int[]{x-dx, y-dy};
        }
    }

    boolean isValid(int[][] maze, int x, int y, int[] hole ) {
        int n = maze.length, m = maze[0].length;
        if (x<0 || x>=n || y<0 || y>=m || maze[x][y] == 1) return false;
        return x!=hole[0] || y!=hole[1];
    }

    void printVisited(PointValue[][] visited) {
        int n = visited.length;

        for (int i=0; i<n; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
        System.out.println("---------------");
    }
    
}

class PointValue { //implements Comparable<PointValue> {
        int steps;
        String path;

        public PointValue(int steps , String path) {
            this.steps = steps;
            this.path = path;
        }

        /* Not necessarily here
        public int compareTo(PointValue p) {
            if (this.steps != p.steps) {
                return this.steps - p.steps;
            } else {
                if (this.path.length()>p.path.length()) return 1;
                return this.path.compareTo(p.path);
            }
        }

        public String toString() {
            if (this == null) return ".";
            return "(" + steps + "," + path + ")"; 
        }
        */
}