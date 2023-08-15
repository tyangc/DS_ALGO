package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * https://www.lintcode.com/problem/1565
 * 1565 · Modern Ludo I
Algorithms
Medium
Accepted Rate
48%
Description
Solution36
Notes60
Discuss8
Leaderboard
Record
Description
There is a one-dimensional board with a starting point on the far left side of the board and an end point on the far right side of the board. 
There are several positions on the board that are connected to other positions, ie if A is connected to B, then when chess falls at position A,
 you can choose whether to give up to throw dice and move the chess from A to B directly. And the connection is one way, which means that the chess cannot move from B to A. 
 Now given the length and connections of the board, and you have a six-sided dice(1-6), output the minimum steps to reach the end point.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


the index starts from 1.
length > 1
！！！The starting point is not connected to any other location
connections[i][0] < connections[i][1]
Example
Example1

Input: 
length = 10 
connections = [[2, 
10]]
Output: 1
Explanation: 
1->2 (dice)
2->10(for free)
Example2

Input: length = 15 and connections = [[2, 8],
[6, 9]]
Output: 2
Explanation: 
1->6 (dice)
6->9 (for free)
9->15(dice)
Tags
Graph
Shortest Path
Company
Amazon
 */

//（1） Use two BFSs , one for shortest distance, one embedded for all connected subNodes
public class ModernLudoI {
	public int modernLudo(int length, int[][] connections) {
        // Write your code here
        HashMap<Integer, Set<Integer>> graph = buildGraph(connections);
        Queue<Integer> que = new ArrayDeque<>();
        int[] distances = new int[length+1];

        que.offer(1);
        distances[1] = 0;  //the index starts from 1.

        while(!que.isEmpty()) {
            int node = que.poll();
            int end = Math.min(node+6, length);  //Can not pass end of the line

            for (int i=node+1; i<=end; i++) {
                for (int connectedNode : getConnectedNodes(i, graph, distances)) {
                    if (distances[connectedNode]>0) continue;
                    que.offer(connectedNode);
                    distances[connectedNode] = distances[node] + 1;
                }
            }
        }

        return distances[length];
    }

    HashMap<Integer, Set<Integer>> buildGraph(int[][] connections) {

        HashMap<Integer, Set<Integer>> ans = new HashMap<>();

        for (int i=0; i<connections.length; i++) {
            ans.computeIfAbsent(connections[i][0], k -> new HashSet<Integer>()).add(connections[i][1]);
        }

        return ans;
    }
 
    Set<Integer> getConnectedNodes(int node, HashMap<Integer, Set<Integer>> graph, int[] distances) {  //distances here is not 100% necessary, but it might improve the speed
        Set<Integer> unvisited = new HashSet<>();
        unvisited.add(node);
        Queue<Integer> que = new ArrayDeque<>();
        que.offer(node);
        while(!que.isEmpty()) {
            int cur = que.poll();
            //System.out.println(cur);
            if (distances[cur] > 0) continue;  //Should be A real improvement for efficiency 
            if (!graph.containsKey(cur)) continue;
            for (int subNode : graph.get(cur)) {
                if (unvisited.contains(subNode)) continue;
                if (distances[subNode] > 0) continue;  //A real improvement for efficiency ???
                que.offer(subNode);
                unvisited.add(subNode);
            }
        }
        return unvisited;
    }
    
    //（2） Using two queue alternatively, since no poll operation, so using list as queue
    
    public int modernLudoTwoQueues(int length, int[][] connections) {
        // Write your code here
        HashMap<Integer, Set<Integer>> graph = buildGraph1(connections, length);
        //Queue<Integer> que = new ArrayDeque<>();
        int[] distances = new int[length+1];
        List<Integer> queue = new ArrayList<>();
        queue.add(1);
        while(!queue.isEmpty()) {
            for (int i=0; i<queue.size(); i++) {  //This for loop can not be swapped with next for loop
                int cur = queue.get(i);
                
                for (int directNode : graph.get(cur)) {  //if use get(i) here , error!
                    if (distances[directNode] > 0) continue;
                    queue.add(directNode);
                    distances[directNode] = distances[cur];
                }
            }
            List<Integer> nextQueue = new ArrayList<>();
            for (int i=0; i<queue.size(); i++) {
                
                int node = queue.get(i);
                int limit = Math.min(node+7, length+1);

                for (int j=node+1; j<limit; j++) {
                    if (distances[j] > 0) continue;
                    nextQueue.add(j);
                    distances[j] = distances[node] + 1;
                }
                 
            }
            queue = nextQueue; 
        }
        return distances[length];
    }

    HashMap<Integer, Set<Integer>> buildGraph1(int[][] connections, int length) {

        HashMap<Integer, Set<Integer>> ans = new HashMap<>();

        for (int i=1; i<=length; i++) {
            ans.put(i, new HashSet<>());
        }

        for (int i=0; i<connections.length; i++) {
            //ans.computeIfAbsent(connections[i][0], k -> new HashSet<Integer>()).add(connections[i][1]);
            ans.get(connections[i][0]).add(connections[i][1]);
        }

        return ans;
    }
    
    //（3） SPFA: short path fast algorithm
    
    public int modernLudoSPFA(int length, int[][] connections) {
        // Write your code here
        HashMap<Integer, Set<Integer>> graph = buildGraph1(connections, length);
        //Queue<Integer> que = new ArrayDeque<>();
        int[] distances = new int[length+1];
        
        distances[1] = 0;

        for (int i=2; i<=length; i++) {  //i=2 is important here, if i=1 then it will fail all the tests
            distances[i] = Integer.MAX_VALUE;
        }

        Queue<Integer> que = new ArrayDeque<>();  //new PriorityQueue<>(); might be faster, 
        que.offer(1);

        while(!que.isEmpty()) {
            int node = que.poll();

            for (int nextNode : graph.get(node) ) {    //Can switch the order of the two inner for loops, but put the short edge first is better
                if (distances[nextNode] > distances[node]) {
                    distances[nextNode] = distances[node];
                    que.offer(nextNode);
                }
            }

            int limit = Math.min(node+7, length+1);
            for (int nextNode=node+1; nextNode<limit; nextNode++) {
                if (distances[nextNode] > distances[node]+1) {
                    distances[nextNode] = distances[node]+1;
                    que.offer(nextNode);
                }
            }
        }

        return distances[length];
    }

    //（4） DP 68%
    
 // Write your code here
    public int modernLudoDP(int length, int[][] connections) {
        // Write your code here
    	Map<Integer, Set<Integer>> graph = buildGraph3(connections, length);
        int[] dp = new int[length+1];
        for (int i=1; i<length; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        for (int i=length-1; i>0; i--) {
            int limit = Math.min(i+7, length+1);
            for (int j=i+1; j<limit; j++) {
                if (dp[j] == Integer.MAX_VALUE) continue;
                dp[i] = Math.min(dp[i], dp[j]+1);
            } 
            for (int j : graph.get(i)) {
                    dp[i] = Math.min(dp[i], dp[j]);
             
            }
        }
        return dp[1];
     }
    
    Map<Integer, Set<Integer>> buildGraph3(int[][] connections, int length) {
        Map<Integer, Set<Integer>> graph = new HashMap<>();

        for (int i=1; i<=length; i++) {
            graph.put(i, new HashSet<>());
        }

        for (int i=0; i<connections.length; i++) {
            graph.get(connections[i][0]).add(connections[i][1]);
        }

        return graph;
    }
}
