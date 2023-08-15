package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 794 · Sliding Puzzle II
Hard Accepted Rate 58%

DescriptionSolutionNotesDiscussLeaderboard
Description
On a 3x3 board, there are 8 tiles represented by the integers 1 through 8, and an empty square represented by 0.

A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.

Given an initial state of the puzzle board and final state, return the least number of moves required so that the initial state to final state.

If it is impossible to move from initial state to final state, return -1.

Example
Example 1:

Input:
[
 [2,8,3],
 [1,0,4],
 [7,6,5]
]
[
 [1,2,3],
 [8,0,4],
 [7,6,5]
]
Output:
4

Explanation:
[                 [
 [2,8,3],          [2,0,3],
 [1,0,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [2,0,3],          [0,2,3],
 [1,8,4],   -->    [1,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [0,2,3],          [1,2,3],
 [1,8,4],   -->    [0,8,4],
 [7,6,5]           [7,6,5]
]                 ]

[                 [
 [1,2,3],          [1,2,3],
 [0,8,4],   -->    [8,0,4],
 [7,6,5]           [7,6,5]
]                 ]
Example 2：

Input:
[[2,3,8],[7,0,5],[1,6,4]]
[[1,2,3],[8,0,4],[7,6,5]]
Output:
-1
Challenge
How to optimize the memory?
Can you solve it with A* algorithm?
Tags
Breadth First Search/BFS
Related Problems
941
Sliding Puzzle
Hard
796
Open the Lock
Hard
110
Minimum Path Sum
Easy
45 : 00
293027282425262223161718192021141511121389103456712

          for (String str : getNext(cur)) {
            if (dist.containsKey(str)) continue;

            que.offer(str);
            dist.put(str, curDist+1);
          }

        }
        return -1;


 */

public class SlidingPuzzleII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param init_state: the initial state of chessboard
     * @param final_state: the final state of chessboard
     * @return: return an integer, denote the number of minimum moving
     */
    public int minMoveStep(int[][] init_state, int[][] final_state) {
        // # write your code here
        Queue<String> que = new LinkedList<>();
        Map<String, Integer> dist = new HashMap<>();
        String start = getState(init_state), end = getState(final_state);
        que.offer(start);
        dist.put(start, 0);
        while(!que.isEmpty()) {
          String cur = que.poll();
          //System.out.println(cur);
          int curDist = dist.get(cur);
          if (cur.equals(end)) {
            return curDist;
          }

          for (String str : getNext(cur)) {
            if (dist.containsKey(str)) continue;

            que.offer(str);
            dist.put(str, curDist+1);
          }

        }
        return -1;
    }
    
    public int minMoveStepDoubleSideNotOptimal(int[][] init_state, int[][] final_state) {
        // # write your code here
        Queue<String> queF = new LinkedList<>();
        Queue<String> queB = new LinkedList<>();
        Map<String, Integer> distF = new HashMap<>();
        Map<String, Integer> distB = new HashMap<>();
        String start = getState(init_state), end = getState(final_state);
        queF.offer(start);
        distF.put(start, 0);
        queB.offer(end);
        distB.put(end, 0);
        //int curDistF = 0; 
        while(!queF.isEmpty() || !queB.isEmpty()) {
          if (!queF.isEmpty()){
            String curF = queF.poll();
            //System.out.println(cur);
            int curDistF = distF.get(curF);
            if (distB.containsKey(curF)) {
              return curDistF + distB.get(curF);
            }

            for (String str : getNext(curF)) {
              if (distF.containsKey(str) ) continue;  //This is wrong :   if (distF.containsKey(str) || distB.containsKey(str) ) continue;

              queF.offer(str);
              distF.put(str, curDistF+1);
            }
          }

          if (!queB.isEmpty()){
            String curB = queB.poll();
            //System.out.println(cur);
            int curDistB = distB.get(curB);
            if (distF.containsKey(curB)) {
              return curDistB + distF.get(curB);
            }

            for (String str : getNext(curB)) {
              if (distB.containsKey(str)) continue;   //This is wrong :   if (distF.containsKey(str) || distB.containsKey(str) ) continue;

              queB.offer(str);
              distB.put(str, curDistB+1);
            }
          }

        }
        return -1;
    }

    public int minMoveStepDoubleSideAsTemplate(int[][] init_state, int[][] final_state) {
        String source = getState(init_state);
        String target = getState(final_state);
        
        if (source.equals(target)) {
            return 0;
        }
        
        Queue<String> forwardQueue = new ArrayDeque<>();
        Set<String> forwardSet = new HashSet<>();
        forwardQueue.offer(source);
        forwardSet.add(source);

        Queue<String> backwardQueue = new ArrayDeque<>();
        Set<String> backwardSet = new HashSet<>();
        backwardQueue.offer(target);
        backwardSet.add(target);
        
        int steps = 0;
        while (!forwardQueue.isEmpty() || !backwardQueue.isEmpty()) {
            steps++;
            if (extendQueue(forwardQueue, forwardSet, backwardSet)) {
                return steps;
            }
            
            steps++;
            if (extendQueue(backwardQueue, backwardSet, forwardSet)) {
                return steps;
            }
        }
        
        return -1;
    }
    
    private boolean extendQueue(Queue<String> queue,
                                Set<String> set,
                                Set<String> targetSet) {
        int size = queue.size();
        for (int i = 0; i < size; i++) {
            String curt = queue.poll();
            for (String next : getNext(curt)) {
                if (set.contains(next)) {
                    continue;
                }
                if (targetSet.contains(next)) {
                    return true;
                }
                queue.offer(next);
                set.add(next);
            }
        }
        return false;
    }

    
    String getState(int[][] num) {
      StringBuilder sb = new StringBuilder();
      for (int i=0; i<3; i++) {
        for (int j=0; j<3; j++) {
          sb.append(num[i][j]);
        }
      }
      return sb.toString();
    }

    List<String> getNext(String cur) {
      List<String> res = new ArrayList<>();
      
      int[] dx = {0, 0,-1, 1};
      int[] dy = {-1, 1, 0, 0};
      //char[] chars = cur.toCharArray();  Here is wrong, the changes will cumulate!!!
      int zero = cur.indexOf('0');
      int x = zero/3;
      int y = zero%3;

      for (int i=0; i<4; i++ ) {
        int nx = x+dx[i];
        int ny = y+dy[i];
        char[] chars = cur.toCharArray();

        if(nx<0 || nx>2 || ny<0 || ny>2) continue;
        chars[x*3+y] = chars[nx*3+ny];
        chars[nx*3+ny] = '0';

        res.add(new String(chars));
      }
      return res;
    }
}
