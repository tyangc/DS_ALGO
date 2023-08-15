package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DegreeConnectedTrio_ShoppingPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//passed as 45.88% rating in leetcode 
	public int minTrioDegree(int n, int[][] edges) {
        
        Set<Integer>[] graph = buildGraph1(edges, n);
        int minDegree = Integer.MAX_VALUE;
        for (int i=1; i<=n; i++) { 
            for (int j : graph[i]) {
                if (i<=j) continue;  //Prevent backward tracking
                for (int k : graph[j]) {
                    if (j<=k) continue;
                    if (graph[k].contains(i)) {
                        minDegree = Math.min(minDegree, graph[i].size() + graph[j].size() + graph[k].size()-6);  
                        //Why 6 still work here, should it be -3 , since only one direction loop????  - The answer is the internal degree counted 6 times
                    }
                }    
            }
        } 
        
        return minDegree==Integer.MAX_VALUE?-1:minDegree;
        
    }
    
    private Set<Integer>[] buildGraph1(int[][] edges, int n) {
        @SuppressWarnings("unchecked")
		Set<Integer>[] graph = new Set[n+1];
        
        for (int i=1; i<=n; i++) {
            graph[i] = new HashSet<>();
        }
        
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
  
            graph[edge[1]].add(edge[0]);
   
        }
        
        return graph;
    }
    

	//The following is not working - wrong understanding of the original problem
	//Should use simpler data structure and flat loop node 1 ~ n
	public int minTrioDegreeWrong(int n, int[][] edges) {
        
        Node[] graph = buildGraph(edges, n);
        /*
        System.out.println(graph.length);
        for (Node nd : graph[1].neighbors) {
           System.out.println(nd.label);
        }
        */
        //List<Set<Node>> trios = findTrios(edges, graph);
        
        List<Integer> degree = new ArrayList<>();

        int[] flag = new int[n+1];
        
        List<Set<Integer>> trios = findTrios(edges, graph, flag);
        System.out.println(Arrays.toString(flag));
        System.out.println(trios.size());
        
        for (Set<Integer> trio : trios) {
            
            for (Integer i : trio) {
                Node nod = graph[i];
                for (Node sub : nod.neighbors) {
                    if(flag[sub.label] > 0) continue;
                    degree.add(sub.label);
                }
            }
        }
        
        return degree.size();
    }
    
    private Node[] buildGraph(int[][] edges, int n) {
        Node[] graph = new Node[n+1];
        for (int[] edge : edges) {
            Node u = null;
            if (graph[edge[0]] !=null) {
                u = graph[edge[0]];
            } else {
              u = new Node(edge[0]);
              graph[edge[0]] = u;  
            }
            Node v = null;
            if (graph[edge[1]] !=null) {
                v = graph[edge[1]];
            } else {
              v = new Node(edge[1]);
              graph[edge[1]] = v;  
            }
            u.neighbors.add(v);
            v.neighbors.add(u);
            
            
        }
        
        return graph;
    }
    
    private List<Set<Integer>> findTrios(int[][] edges, Node[] graph, int[] flag) {
        int cnt=0;
        List<Set<Integer>> res = new ArrayList<>();
        for (int[] edge : edges) {
            
            if (flag[edge[0]] > 0 || flag[edge[1]]> 0) {
                continue;
            }
            
            Node u = graph[edge[0]];
            Node v = graph[edge[1]];
            
            Set<Node> uAdj = new HashSet(u.neighbors);
            uAdj.retainAll(v.neighbors);
            if (uAdj.size()==1) {
                cnt++;
                flag[edge[0]] = cnt;
                flag[edge[1]] = cnt;
                int nod = 0;
                for (Node n : uAdj) {
                    flag[n.label] = cnt;
                    nod = n.label;
                }
                
                Set<Integer> tmp = new HashSet<>();
                tmp.add(edge[0]);
                tmp.add(edge[1]);
                tmp.add(nod);
                res.add(tmp);
            }
            
        }
        
        return res;
    }
    
    class Node {
        int label;
        Set<Node> neighbors;
        
        public Node(int lab) {
            label = lab;
            neighbors = new HashSet<>();
        }
    }
}
