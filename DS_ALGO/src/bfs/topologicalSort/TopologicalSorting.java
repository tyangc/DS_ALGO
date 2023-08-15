package bfs.topologicalSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import bfs.topologicalSort.TopologicalSorting.DirectedGraphNode;

/*
 *Given an directed graph, a topological order of the graph nodes is defined as follow:

For each directed edge A -> B in graph, A must before B in the order list.
The first node in the order can be any node in the graph with no nodes direct to it.
Find any topological order for the given graph.

Example
For graph as follow:


The topological order can be:

[0, 1, 2, 3, 4, 5]
[0, 2, 3, 1, 5, 4]
...
Challenge
Can you do it in both BFS and DFS?
 */

public class TopologicalSorting {

	 class DirectedGraphNode {
		 int label;
		 ArrayList<DirectedGraphNode> neighbors;
		 DirectedGraphNode(int x) { label = x; neighbors = new ArrayList<DirectedGraphNode>(); }
	 }
		 
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param graph: A list of Directed graph node
     * @return: Any topological order for the given graph.
     */
    public ArrayList<DirectedGraphNode> topSort(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> res = new ArrayList<>();
        if (graph==null) return res;
        
        int[] inDegree = new int[graph.size()];
        
        Queue<DirectedGraphNode> que = new LinkedList<>();
        
        for (DirectedGraphNode outNode : graph) {
            for ( DirectedGraphNode inNode : outNode.neighbors) {
                inDegree[inNode.label]++;
            }
        }
        
        for (int i=0; i<inDegree.length; i++ ) {
            if (inDegree[i]==0) {
                que.offer(graph.get(i));
                res.add(graph.get(i));
            }
        }
        
        while(!que.isEmpty()) {
            DirectedGraphNode cur = que.poll();
            
            for (DirectedGraphNode subNode : cur.neighbors) {
                inDegree[subNode.label]--;
                if (inDegree[subNode.label]==0) {
                    que.offer(subNode);
                    res.add(subNode);
                }
            }
            
        }
        
        if (res.size()==graph.size()) return res;
        
        return null;
        
    }
    
    //Slightly better structured implementation with more detailed comments
    public ArrayList<DirectedGraphNode> topSort1(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        if (graph == null || graph.size() == 0) return ans;
        int n=graph.size();

        int[] inDegree = new int[n];
        //Map<DirectedGraphNode, Integer> inDegree = new HashMap();
        
        for (DirectedGraphNode outNode : graph) {   //Nodes categorized only as out or in, so two layers of loop is enough to cover all cases
            for (DirectedGraphNode inNode : outNode.neighbors) {
                inDegree[inNode.label]++;
            }
        }
        
        Queue<DirectedGraphNode> que = new ArrayDeque<>();
       

        //Set<DirectedGraphNode> visited = new HashSet();
        /*  This is wrong! is it a loop which will cause NullPointerException???
        for (DirectedGraphNode node : graph) {
            if (inDegree[node.label] == 0) {
                que.offer(node);
                ans.add(node);
                bfs(inDegree, que, ans);
                break;
            }
        }
        */

        for (int i=0; i<inDegree.length; i++) {  //With assumption the label == index of the graph, otherwise no reverse lookup
            if (inDegree[i] == 0) {
                que.offer(graph.get(i));
                ans.add(graph.get(i));
            }
        }
        
        bfs(inDegree, que, ans);

        System.out.println(Arrays.toString(inDegree));
        
        if (ans.size() == graph.size()) return ans;
        return null;
    }

    void bfs(int[] inDegree, Queue<DirectedGraphNode> que, ArrayList<DirectedGraphNode> ans) {
        while(!que.isEmpty()) {
            DirectedGraphNode cur = que.poll();

            for (DirectedGraphNode subNode : cur.neighbors) {
                inDegree[subNode.label]--;   //The parentNode has been processed so in degree decreased by 1
                if (inDegree[subNode.label] == 0) {
                    ans.add(subNode);
                    que.offer(subNode);
                }
            }
            //System.out.println(Arrays.toString(inDegree));
        }
    }
    
    //Using dfs:
    public ArrayList<DirectedGraphNode> topSort2(ArrayList<DirectedGraphNode> graph) {
        // write your code here
        ArrayList<DirectedGraphNode> ans = new ArrayList<>();
        HashMap<DirectedGraphNode, Integer> mark = new HashMap<>();
                                
        for (DirectedGraphNode outNode : graph) {
            dfs(outNode, mark, ans);
        }
      
        Collections.reverse(ans);

        
        //System.out.println(Arrays.toString(inDegree));
        
        for (DirectedGraphNode node : ans) System.out.print(node.label);
        //if (ans.size() == graph.size()) return ans;
        return ans;
    }

    void dfs(DirectedGraphNode node, HashMap<DirectedGraphNode, Integer> mark, ArrayList<DirectedGraphNode> ans) {
        if (mark.computeIfAbsent(node, value->0) == 1) return;

        mark.compute(node, (key, value) -> 1);

        if (node.neighbors.size()==0) {
            ans.add(node);
            return;  //If no return here, than duplicated entry will be returned
        }

        for (DirectedGraphNode subNode : node.neighbors) {
            dfs(subNode, mark, ans);
        }
        ans.add(node);
    }
}
