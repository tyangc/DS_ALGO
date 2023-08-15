package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TravelingSalesmanProblem {

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
        
        Set<Integer> visited = new HashSet<>();
        List<Integer> path = new ArrayList<>();
        int[][] graph = constructGraph(n, roads);
        
        for (int i=0; i<graph.length; i++) {
            System.out.println(Arrays.toString(graph[i]));
        }
        
        //int cost = 0;
        path.add(1);
        visited.add(1);
        //int[] result = new int[1];
        //result[0] = 1000000+11;
        Result result = new Result();
        dfs(1, n, graph, path, visited, 0, result);
        
        return result.minCost;
        
    }
    
    private void dfs(int city, 
                    int n, 
                    int[][] graph, 
                    List<Integer> path, 
                    Set<Integer> visited, 
                    int cost,  
                    Result result) {
                        
        if (visited.size()==n) {
            System.out.println("lowest cost:" + cost);
            result.minCost = Math.min(result.minCost, cost);
        }
        
        
        
        path.add(city);
        
        for (int i=1; i<graph[city].length; i++) {
            if (graph[city][i]<1000000) { //Integer.MAX_VALUE) {
            
                System.out.println(visited.size() + "|" + n);
                if (visited.contains(i)) {
                    continue;
                }
            
                
                if (hasBetterPath(graph, path, i)) {
                    continue;
                }
                visited.add(i);
                path.add(i);
                System.out.println(cost+graph[city][i]);
                dfs(i, n, graph, path, visited, cost+graph[city][i], result);
                
                visited.remove(i);
                path.remove(path.size()-1);
            }
        }
        
    
    }
    
    private int[][] constructGraph(int n, int[][] roads) {
        
        int[][] graph = new int[n+1][n+1];
        
        for (int i=0; i<=n; i++) {
            for (int j=0; j<=n; j++) {
                graph[i][j] = 1000000; //Integer.MAX_VALUE;
            }
        }
        
        for (int[] road : roads) {
            int a = road[0], b = road[1], c = road[2];
            
            graph[a][b] = Math.min(graph[a][b], c);
            graph[b][a] = Math.min(graph[b][a], c);
        }
        
        return graph;
    }
    
    
    private boolean hasBetterPath(int[][] graph, List<Integer> path, int city) {
        for (int i=1; i<path.size(); i++) {
            int path_i_1 = path.get(i-1);
            int path_i = path.get(i);
            int last = path.get(path.size()-1);
            if (graph[path_i_1][path_i] + graph[last][city] > 
                graph[path_i_1][last] + graph[path_i][city]) {
                return true;
            }
            
        }
        
        return false;
    }
    
    /*
    boolean hasBetterPath(int[][] graph, List<Integer> path, int city) {
        int pathLength = path.size();
        for (int i = 1; i < pathLength; i++){
            int path_i_1 = path.get(i - 1);
            int path_i = path.get(i);
            int path_last = path.get(pathLength - 1);
            if (graph[path_i_1][path_i] + graph[path_last][city] >
                graph[path_i_1][path_last] + graph[path_i][city]) {
                return true;
            }
        }
        
        return false;
    }
    */
}

class Result {
    int minCost;
    public Result(){
        this.minCost = 1000000;
    }
}
