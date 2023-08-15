package bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 137. Clone Graph

Clone an undirected graph. Each node in the graph contains a label and a list of its neighbors. Nodes are labeled uniquely.

You need to return a deep copied graph, which has the same structure as the original graph, and any changes to the new graph will not have any effect on the original graph.

Example
Example1

Input:
{1,2,4#2,1,4#4,1,2}
Output: 
{1,2,4#2,1,4#4,1,2}
Explanation:
1------2  
 \     |  
  \    |  
   \   |  
    \  |  
      4   
Clarification
How we serialize an undirected graph: http://www.lintcode.com/help/graph/

Notice
You need return the node with the same label as the input node.
 */
		
public class CloneGraph {

	class UndirectedGraphNode {
		 int label;
		 List<UndirectedGraphNode> neighbors;
		 UndirectedGraphNode(int x) {
		 label = x;
		 neighbors = new ArrayList<UndirectedGraphNode>();
		 }
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraphAtOnce(UndirectedGraphNode node) {
        // write your code here
        if(node==null) return node;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        Queue<UndirectedGraphNode> que = new LinkedList<>();
        
        que.offer(node);
        
        while(!que.isEmpty()) {
            
            UndirectedGraphNode cur = que.poll();
            UndirectedGraphNode newNode = null;
            if (!map.containsKey(cur)){
                newNode = new UndirectedGraphNode(cur.label);
                map.put(cur, newNode);
            } else {
                newNode = map.get(cur);
            }
            
            for (UndirectedGraphNode subNode : cur.neighbors) {
                if (!map.containsKey(subNode)) {
                    
                    UndirectedGraphNode subNewNode = new UndirectedGraphNode(subNode.label);
                    map.put(subNode, subNewNode);
                    
                    que.offer(subNode);
                    newNode.neighbors.add(subNewNode);
                } else {
                    newNode.neighbors.add(map.get(subNode));
                }   
            }
     
        }
        
        return map.get(node);
    }
    
    /**
     * @param node: A undirected graph node
     * @return: A undirected graph node
     */
    public UndirectedGraphNode cloneGraphBySteps(UndirectedGraphNode node) {
        // write your code here
        if(node==null) return node;
        
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<>();
        List<UndirectedGraphNode>  list = getNodes(node);
        dupNodes(list, map);
        dupEdges(list, map);
        
        return map.get(node);
        
    }
    
    private List<UndirectedGraphNode> getNodes(UndirectedGraphNode node) {
        Queue<UndirectedGraphNode> que = new LinkedList<>();
        Set<UndirectedGraphNode> set = new HashSet<>();
        
        que.offer(node);
        set.add(node);
        while(!que.isEmpty()) {
            UndirectedGraphNode cur = que.poll();
      
            for (UndirectedGraphNode subNode : cur.neighbors) {
                if (!set.contains(subNode)) {
                    set.add(subNode);
                    que.offer(subNode);
                }
            }
        }
        
        return new ArrayList<>(set);
    } 
    
    private void dupNodes(List<UndirectedGraphNode> list, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
    
        for (UndirectedGraphNode orgNode : list) {
            UndirectedGraphNode newNode = new UndirectedGraphNode(orgNode.label);
            map.put(orgNode, newNode);
         }
 
    }
    
    private void dupEdges(List<UndirectedGraphNode> orgList, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        
        for (UndirectedGraphNode orgNode : orgList) {
            UndirectedGraphNode dupNode = map.get(orgNode);
            
            for (UndirectedGraphNode orgSubNode : orgNode.neighbors) {
                dupNode.neighbors.add(map.get(orgSubNode));
            }
            
        }
    }
}
