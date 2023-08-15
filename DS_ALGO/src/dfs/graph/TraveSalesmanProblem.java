package dfs.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

/*
 816 · Traveling Salesman Problem
Algorithms
Hard
Accepted Rate
47%

DescriptionSolutionNotesDiscussLeaderboard
Description
Give n cities(labeled from 1 to n), and the undirected road's cost among the cities as a three-tuple [A, B, c](i.e there is a road between city A and city B and the cost is c). We need to find the smallest cost to travel all the cities starting from 1.

1.A city can only be passed once.
2.You can assume that you can reach all the rest cities.

Example
Example 1

Input: 
n = 3
tuple = [[1,2,1],[2,3,2],[1,3,3]]
Output: 3
Explanation: The shortest path is 1->2->3
Example 2

Input:
n = 1
tuple = []
Output: 0
Tags
Related Problems
814
Shortest Path in Undirected Graph
Medium
 */
public class TraveSalesmanProblem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param n: an integer,denote the number of cities
     * @param roads: a list of three-tuples,denote the road between cities
     * @return: return the minimum cost to travel all cities
     */
    public int minCost(int n, int[][] roads) {
        // Write your code here
        int[][] grid = new int[n+1][n+1];
        getAdjGrid(roads, grid);

        boolean[] visited  = new boolean[n+1];
        visited[1] = true;
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        //int[] sum = new int[1];
        //sum[0] = 0;

        dfs(1, grid, visited, 0, res);
        return res[0];

    }

    //Can pass level as well to terminate the dfs , level :  1~n
    private void dfs(int start, int[][] grid, boolean[] visited, int sum, int[] res) {
      if (sum>=res[0]) return;  //Optimized
      
      if (isDone(visited)) {
        res[0] = Math.min(res[0], sum);  //can directly use sum here
        return;
      }

      

      for (int i=1; i<grid.length; i++) {
        if (grid[start][i]>0 && !visited[i] && i!=start) { //i!=start not necessary
            visited[i] = true;
            //sum[0] += Math.min(grid[start][i], grid[i][start]);
            dfs(i, grid, visited, sum+grid[start][i], res);
            visited[i] = false;
            //sum[0] -= grid[start][i];
        }
      }
    }

    private boolean isDone(boolean[] visited) {

      for (int i=1; i<visited.length; i++) {
        if (!visited[i]) return false;
      }
      return true;
    }

    private void getAdjGrid(int[][] roads, int[][] grid) {
      for (int[] road : roads) {
        grid[road[0]][road[1]] = grid[road[0]][road[1]] == 0 ? road[2] : Math.min(grid[road[0]][road[1]], road[2]);   //there are might be two routes between two points
        grid[road[1]][road[0]] = grid[road[1]][road[0]] == 0 ? road[2] : Math.min(grid[road[1]][road[0]], road[2]);		//To get the minimum one then choose the smaller one
      }

      for (int[] line : grid) {
        System.out.println(Arrays.toString(line));
      }
    }

    
    /**
    * Another implementation of dfs, using count down/up counter:
    * For count up: cnt = 1 when cnt == n  
    */
    
    public int minCostDfs(int n, int[][] roads) {
        // Write your code here
        //List<Node> graph = buildGraph(n, roads);
        int res = Integer.MAX_VALUE;
        int[][] cost = buildGraph(n, roads);
        
        for (int i=0; i<cost.length; i++) {
            for (int j=0; j<cost[i].length; j++) {
                System.out.print(cost[i][j]+ " ");
                
            }
            System.out.println("");
        }
        //System.out.println(cost);
        boolean[] visited = new boolean[n+1];
        visited[1] = true;
        AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
        dfs1(1, n, cost, n-1, 0, visited, min);
        /*
        for (int i=1; i<=n; i++) {
            visited[i] = true;
            AtomicInteger min = new AtomicInteger(Integer.MAX_VALUE);
            dfs(1, n, cost, 1, 0, visited, min);
            res = Math.min(res, min.get());
        }
        */
        return min.get();
    }

    public void dfs1(int start, int n, int[][] cost, int cnt, int tmp, boolean[] visited, AtomicInteger min) {
        if (cnt == 0) {
            if (min.get() > tmp) {
                min.set(tmp);
            }
            return;
        }
        
        if (tmp>min.get()) return; //optimized

        for (int i = 1; i<cost[start].length; i++) {
            if (!visited[i] && cost[start][i]<Integer.MAX_VALUE) {
                //tmp += cost[start][i]
                visited[i] = true;
                dfs1(i, n, cost, cnt-1, tmp+cost[start][i], visited, min);
                visited[i] = false;

            }
        }
    }

    private int[][]  buildGraph(int n, int[][] roads) {
        //List<Node> graph = new ArrayList<>();
        int[][] cost = new int[n+1][n+1];
        for (int i=0; i<cost.length; i++) {
            for (int j=0; j<cost[i].length; j++) {
                cost[i][j] = Integer.MAX_VALUE;
            }
        }
        
        //boolean[] mark = new boolean[n+1];
        for (int[] r : roads ) {
            /*
            if (!mark[r[0]]) {
                Node n = new Node(r[0], new ArrayList<>());
                n.neighbors.add(r[1]);
                mark[r[0]] = true;
            } */
            cost[r[0]][r[1]] = Math.min(cost[r[0]][r[1]], r[2]);
            cost[r[1]][r[0]] = Math.min(cost[r[1]][r[0]], r[2]);
        }
        return cost;
    }
    
    /**
     * Using DFS with prone
     */
    
    public int minCostDFSProne(int n, int[][] roads) {
        // Write your code here
        int[][] grid = fillGrid(n);
        getAdjGrid(roads, grid);
 
        boolean[] visited  = new boolean[n+1];
        visited[1] = true;
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        //int[] sum = new int[1];
        //sum[0] = 0;
        List<Integer> path = new ArrayList<>();
        path.add(1);
        dfs(1, path, grid, visited, 0, res);
        return res[0];
 
    }
 
    //Can pass level as well to terminate the dfs , level :  1~n
    private void dfs(int start, List<Integer> path, int[][] grid, boolean[] visited, int sum, int[] res) {
      if (sum>=res[0]) return;  //Optimized
      //System.out.println(path);
      //if (isDone(visited)) {
      if (path.size()==grid.length-1) {  //This is faster than using isDone
        res[0] = Math.min(res[0], sum);  //can directly use sum here
        return;
      }
      
      //if (hasBetterPath(start, path, grid)) return;
 
      for (int i=1; i<grid.length; i++) {
        if ( !visited[i] && grid[start][i]<1000000000) {
            if (hasBetterPath(i, path, grid)) continue;
            visited[i] = true;
            path.add(i);
            //sum[0] += Math.min(grid[start][i], grid[i][start]);
            dfs(i, path, grid, visited, sum+grid[start][i], res);
            path.remove(path.size()-1);
            visited[i] = false;
            
            //sum[0] -= grid[start][i];
        }
      }
    }
 
    /*
    private boolean isDone(boolean[] visited) {
 
      for (int i=1; i<visited.length; i++) {
        if (!visited[i]) return false;
      }
      return true;
    }*/
 
    /**
    private void getAdjGrid(int[][] roads, int[][] grid) {
 
      for (int[] road : roads) { 
        grid[road[0]][road[1]] = Math.min(grid[road[0]][road[1]], road[2]);
        grid[road[1]][road[0]] = Math.min(grid[road[1]][road[0]], road[2]);
      }
      /*
      for (int[] road : roads) {
        grid[road[0]][road[1]] = grid[road[0]][road[1]] == 0 ? road[2] : Math.min(grid[road[0]][road[1]], road[2]);   //there are might be two routes between two points
        grid[road[1]][road[0]] = grid[road[1]][road[0]] == 0 ? road[2] : Math.min(grid[road[1]][road[0]], road[2]);
      }
      */
 
        /*
     for (int[] line : grid) {
       System.out.println(Arrays.toString(line));
      } */
    //}
	
 
    private boolean hasBetterPath(int city, List<Integer> path, int[][] grid) {
      for (int i=1; i<path.size(); i++) {
        if (grid[path.get(i-1)][path.get(i)] + grid[path.get(path.size()-1)][city] > grid[path.get(i-1)][path.get(path.size()-1)] + grid[path.get(i)][city]) return true;
      }
      return false;
    }
 
    private int[][] fillGrid(int n) {
      int[][] grid = new int[n+1][n+1];
      for (int i=0; i<grid.length; i++) {
        for (int j=0; j<grid[0].length; j++) {
          grid[i][j] = 1000000000; //Integer.MAX_VALUE;  For java MAX_VALUE + value < MAX_VALUE
        }
      }
      return grid;
 
    }
    
    /**
     * Using BFS
     */ 
    public int minCostBFS(int n, int[][] roads) {
        // Write your code here
                
        Map<Integer, List<int[]>> map = buildMap(roads);
                
        Queue<Point> que = new PriorityQueue<>((p1, p2) -> p1.cost-p2.cost);  //***** template

        que.offer(new Point(1, n-1, 0, new boolean[n+1]));

        while(!que.isEmpty()) {
          Point cur = que.poll();
          if (cur.remain==0) return cur.cost;
          cur.visited[cur.city] = true;

          for (int[] nxt : map.get(cur.city)) {
            int cty = nxt[0], cst = nxt[1];
            if (cur.visited[cty]) continue;
            que.offer(new Point(cty, cur.remain-1, cur.cost+cst, Arrays.copyOf(cur.visited, cur.visited.length)));
          }

        }

        return -1;
    }

    private Map<Integer, List<int[]>>  buildMap( int[][] roads) {
      
      Map<Integer, List<int[]>> map = new HashMap<>();

      for (int[] road : roads) {
        int c1=road[0], c2= road[1], cost = road[2];
        map.putIfAbsent(c1, new ArrayList<>());   //***** template
        map.get(c1).add(new int[]{c2, cost});

        map.putIfAbsent(c2, new ArrayList<>());
        map.get(c2).add(new int[]{c1, cost});
      }

      return map;
    }

    //Using state compression Dynamic Programming:
    public int minCostDP(int n, int[][] roads) {
        // Write your code here
 
        int[][] grid = new int[n+1][n+1]; 
        fillGrid(grid);
        getAdjGrid(roads, grid);
 
 
        int state_size = 1 << n;
 
        int[][] f = new int[state_size][n+1];
        fillGrid(f);
        f[1][1] = 0;
        int state = 0, prev_state = 0;
 
        for (state = 1; state<state_size; state++) {
          for (int i=2; i<=n; i++) {
            if ((state & 1<<(i-1)) == 0) continue;
            prev_state = state ^ (1<<(i-1));
              for (int j=1; j<=n; j++) {
                if ((prev_state & 1<<(j-1)) == 0) continue;
                //prev_state = state ^ j<<(j-1);
                f[state][i] = Math.min(f[state][i], f[prev_state][j] + grid[j][i]);
              }
          }
        }

        /*    
        for (state = 0; state<state_size; state++) {
          System.out.println(Arrays.toString(f[state]));
        } 
        */ 
 
        int res=1000000000;
        for (int i=1; i<=n; i++) {
          //System.out.println(f[state_size-1][i]);
          res = Math.min(res, f[state_size-1][i]);
        }
        return  res;
      }

    private void fillGrid(int[][] grid) {
      //int[][] grid = new int[n+1][n+1];
      for (int i=0; i<grid.length; i++) {
        for (int j=0; j<grid[0].length; j++) {
          grid[i][j] = 1000000000; //Integer.MAX_VALUE >> 4 will also work;
        }
      }
      //return grid;
 
    }

}

class Point {
	  int city, remain, cost;  //Cost so far
	  boolean[] visited;
	  public Point(int city, int remain,  int cost, boolean[] visited) {
	    this.city = city; 
	    this.remain = remain;
	    this.cost = cost;
	    this.visited = visited;
	  }
	}
