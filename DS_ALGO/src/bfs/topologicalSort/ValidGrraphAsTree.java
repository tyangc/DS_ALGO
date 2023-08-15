package bfs.topologicalSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 178 · Graph Valid Tree
Algorithms
Medium
Accepted Rate
34%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes), write a function to check whether these edges make up a valid tree.

You can assume that no duplicate edges will appear in edges. Since all edges are undirected, [0, 1] is the same as [1, 0] and thus will not appear together in edges.

Example
Example 1:

Input: n = 5 edges = [[0, 1], [0, 2], [0, 3], [1, 4]]
Output: true.
Example 2:

Input: n = 5 edges = [[0, 1], [1, 2], [2, 3], [1, 3], [1, 4]]
Output: false.
Tags
Related Problems
814
Shortest Path in Undirected Graph
Medium
750
Portal
Medium
589
Connecting Graph
Medium
431
Connected Component in Undirected Graph
Medium
 */
public class ValidGrraphAsTree {
	/**
     * @param n: An integer
     * @param edges: a list of undirected edges
     * @return: true if it's a valid tree, or false
     */
    public boolean validTree(int n, int[][] edges) {

        if ( edges.length<n-1) return false;
        // write your code here
        Queue<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        Map<Integer, List<Integer>> adj = buildGraph(n, edges);

        for (int i=0; i<n; i++) {  //just judge edges.length == n-1 and at the end check if visited true number matches n

            if (visited[i]) continue;
            if (i==0) {
                que.offer(i);
            } else {
                return false;
            }
            while(!que.isEmpty()) {
                int cur = que.poll();
                if (visited[cur]) {
                    return false;
                } else {
                    visited[cur] = true;
                }

                for (int neighbor : adj.get(cur)) {
                    if (neighbor != cur && !visited[neighbor]) { //neighbor != cur is not necessary
                        que.offer(neighbor);
                    }
                }
            }
        }
        return true;
    }

    private Map<Integer, List<Integer>> buildGraph(int n, int[][] edges) {
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for (int i=0; i<n; i++) {
            adj.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        return adj;
    }

}
