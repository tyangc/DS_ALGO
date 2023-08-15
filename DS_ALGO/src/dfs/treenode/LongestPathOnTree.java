package dfs.treenode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Queue;

/*
 1469. Longest Path On The Tree
中文English
Given a tree consisting of n nodes, n-1 edges. Output the distance between the two nodes with the furthest distance on this tree.
Given three arrays of size n-1, starts, ends, and lens, indicating that the i-th edge is from starts[i] to ends[i] and the length is lens[i].

Example
Example 1:

Input：n=5,starts=[0,0,2,2],ends=[1,2,3,4],lens=[1,2,5,6]
Output：11
Explanation:
(3→2→4)the length of this path is `11`,as well as(4→2→3)。
Example 2:

Input：n=5,starts=[0,0,2,2],ends=[1,2,3,4],lens=[5,2,5,6]
Output：13
Explanation:
(1→0→2→4)the length of this path is`13`,as well as(4→2→0→1)。
Notice
Return the farthest distance between any two nodes on the tree, not the depth of the tree. Note that the given edges are undirected edge.
It is guaranteed that the given edges are able to constitute a tree.

1 \leq n \leq 1* 10^51≤n≤1∗10
​5
​​ 
1 \leq lens[i] \leq 1* 10^31≤lens[i]≤1∗10
​3
​​
 */
public class LongestPathOnTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param n: The number of nodes
     * @param starts: One point of the edge
     * @param ends: Another point of the edge
     * @param lens: The length of the edge
     * @return: Return the length of longest path on the tree.
     */
    public int longestPath(int n, int[] starts, int[] ends, int[] lens) {
        // Write your code here
        List<int[]>[] neighbors = (List<int[]>[]) new List[n];
        
        for (int i=0; i<n; i++) {
        	neighbors[i] = new ArrayList<>();
        }
        
        for (int i=0; i<n-1; i++) {
            
        	
        	int start = starts[i];
            int end = ends[i];
            int len = lens[i];
            
            int[] dataStart = {end, len};
            int[] dataEnd = {start, len};
            neighbors[start].add(dataStart);
            neighbors[end].add(dataEnd);
        
        }
        
        int[] res = {0};
        
        dfs(0, -1, neighbors, res);
        
        return res[0];
    }
    
    private int[] dfs(int root, int parent, List<int[]>[] neighbors, int[]longestPath) {
    	int childLongestChain = 0, childSecondLongestChain=0, curLongestChain = 0, curSecondLongestChain=0;
    	List<Integer> list = new ArrayList<>();
    
    	for (int[] neighbor : neighbors[root]) {
    		
    		if (neighbor[0]==parent) continue;
    		
    		int[] childRet = dfs(neighbor[0], root, neighbors, longestPath);
    		
    		childLongestChain=Math.max(childRet[0], childRet[1]);
    		//childSecondLongestChain=childRet[1];
    		
    		list.add(childLongestChain+neighbor[1]);
    		//list.add(childSecondLongestChain);
    		//list.add(curLongestChain);
    		//list.add(curSecondLongestChain);
    		//Collections.sort(list);
    		//curLongestChain = list.get(3);
    		//curSecondLongestChain = list.get(2);
  
    	}
    	
    	Collections.sort(list);  //Use sort to get the first two largest chain from children
    	curLongestChain = list.size()==0?0:list.get(list.size()-1);
    	curSecondLongestChain = list.size()>1?list.get(list.size()-2):0;
    	
    	longestPath[0] = Math.max(longestPath[0], curLongestChain+curSecondLongestChain);
    	
    	System.out.println(curLongestChain+"|"+curSecondLongestChain);
    	
    	return new int[] {curLongestChain, curSecondLongestChain};
    }
    
    //Another implementation: originally missing the end->start neighbors, but easy to fix
    int maxDistance = 0;
    public int longestPath1(int n, int[] starts, int[] ends, int[] lens) {
        // Write your code here
        HashMap<Integer, List<Integer[]>> graph = new HashMap<>();

        for (int i=0; i<n-1; i++) {
            graph.computeIfAbsent(starts[i], k-> new ArrayList<Integer[]>()).add(new Integer[]{ends[i], lens[i]});
            graph.computeIfAbsent(ends[i], k -> new ArrayList<Integer[]>()).add(new Integer[]{starts[i], lens[i]}); //Importantly add to complete the graph mapping
        }
        /*
        for( int key : graph.keySet()) {
            List<Integer[]> list = graph.get(key);
            for (Integer[] arr : list) {
                System.out.println(key + ":" + Arrays.toString(arr));
            }
        } */
        dfs1(0, -1, graph, 0);
        return maxDistance;
    }

    //Pass 93%
    int dfs1(int node, int parent, HashMap<Integer, List<Integer[]>> graph, int len) {
        //System.out.println("Enter:" + node + " len: " + len);
        if (!graph.containsKey(node)) return len;  //Not going back

        int first=0, firstIndex = 0, second=0, secondIndex=0;

        List<Integer[]> list = graph.get(node);

        for (int i=0; i<list.size(); i++) {
        	if (list.get(i)[0] == parent) continue; //To avoid go back
        	
            int cur = dfs1(list.get(i)[0], node, graph, list.get(i)[1]);
            //System.out.println("Cur: " + cur);
            //Use round robin for two numbers to get the first and second largest chain
            if (cur > first) {
                second = first;
                secondIndex = firstIndex;
                first = cur;
                firstIndex = i;
                
            } else if(cur > second) {
                second = cur;
                secondIndex = i;
            } 
            
        }

        //System.out.println(first + "|" + second);
        /*
        for (int[] subNode : graph.get(node)) {
            int cur = dfs(subNode[0], graph, subNode[1]);

            if (cur > first) {
                first = cur;
            } else if(cur<first && cur>second) {
                second = cur;
            } 
        }*/

        //if (first + second + list.get(firstIndex)[1] + list.get(secondIndex)[1]>maxDistance) maxDistance = first + second + list.get(firstIndex)[1] + list.get(secondIndex)[1];
        if (first + second > maxDistance) maxDistance = first + second;
        //if (first > maxDistance) maxDistance = first;
        return first+len;//list.get(firstIndex)[1] + len;

    }
    
    //Using bfs:   execution time limit exceeded at 66%
    public int longestPathBfs(int n, int[] starts, int[] ends, int[] lens) {
        // Write your code here
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        int maxDistance = 0;
        for (int i=0; i<n-1; i++) {
            graph.computeIfAbsent(starts[i], k -> new ArrayList<int[]>()).add(new int[]{ends[i], lens[i]});
            graph.computeIfAbsent(ends[i], k -> new ArrayList<int[]>()).add(new int[]{starts[i], lens[i]});
        }
        /*
        for( int key : graph.keySet()) {
            List<int[]> list = graph.get(key);
            for (int[] arr : list) {
                System.out.println(key + ":" + Arrays.toString(arr));
            }
        } */

        Queue<int[]> que = new ArrayDeque<>();
        
        for (int i=0; i<n-1; i++) {
            //que.clear();
            int cur = bfs(i, -1, que, graph);
            if (cur>maxDistance) maxDistance = cur;
        } 
        return maxDistance;
    }

    int bfs(int start, int parent, Queue<int[]> que, HashMap<Integer, List<int[]>> graph) {
        que.offer(new int[]{start, 0, -1});
        //Set<Integer> visited = new HashSet<>();
        int result = 0;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            //visited.add(cur[0]);
            //System.out.println(cur[0] + "|" + cur[1]);
            if (cur[1] > result) result = cur[1];
            for (int[] subNode : graph.get(cur[0])) {
                if (subNode[0] == cur[2]) continue;
                //if (visited.contains(subNode[0])) continue;
                que.offer(new int[] {subNode[0],  subNode[1] + cur[1], cur[0]});
            } 
        } 
        //System.out.println("Return: " + maxRad[0]);
        return result;
    }
    
    
    /**With analytical trick: 
       Two times of bfs, firstly:  find from any point to the farthest reach; 
    	secondly: then use this point as the start of the longest Path to find the result
    */
    public int longestPathBfsWithAnalysis(int n, int[] starts, int[] ends, int[] lens) {
        // Write your code here
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        //int maxDistance = 0;
        for (int i=0; i<n-1; i++) {
            graph.computeIfAbsent(starts[i], k -> new ArrayList<int[]>()).add(new int[]{ends[i], lens[i]});
            graph.computeIfAbsent(ends[i], k -> new ArrayList<int[]>()).add(new int[]{starts[i], lens[i]});
        }
        /*
        for( int key : graph.keySet()) {
            List<int[]> list = graph.get(key);
            for (int[] arr : list) {
                System.out.println(key + ":" + Arrays.toString(arr));
            }
        } */

        Queue<int[]> que = new ArrayDeque<>();
    
        int[] root = bfs1(0, -1, que, graph);
        int[] result = bfs1(root[0], -1, que, graph); 
        return result[1]; //maxDistance;
    }

    int[] bfs1(int start, int parent, Queue<int[]> que, HashMap<Integer, List<int[]>> graph) {
        que.offer(new int[]{start, 0, -1});
        //Set<Integer> visited = new HashSet<>();
        int resultNode = -1, resultDistance = 0;
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            //visited.add(cur[0]);
            //System.out.println(cur[0] + "|" + cur[1]);
            if (cur[1] > resultDistance) {
                resultNode = cur[0];
                resultDistance = cur[1];
            }
            for (int[] subNode : graph.get(cur[0])) {
                if (subNode[0] == cur[2]) continue;
                //if (visited.contains(subNode[0])) continue;
                que.offer(new int[] {subNode[0],  subNode[1] + cur[1], cur[0]});
            } 
        } 
        //System.out.println("Return: " + maxRad[0]);
        return new int[]{resultNode, resultDistance};
    }

}
