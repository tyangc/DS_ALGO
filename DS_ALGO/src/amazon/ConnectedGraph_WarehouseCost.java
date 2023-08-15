package amazon;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class ConnectedGraph_WarehouseCost {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(costEvaluation(10, new int[][] {{2,6}, {3,5}, {0,1}, {2,9}, {5,6}}));
		//System.out.println(costEvaluation(4, new int[][] {{0,2}, {1,2}}));
		//System.out.println(costEvaluation(4, new int[][] {{0,2}, {1,2}}));
	}

	public static int costEvaluation(int n , int[][] edges) {
		Map<Integer, List<Integer>> graph = buildGraph1(n, edges); 
		//boolean[] visited = new boolean[n];
		Set<Integer> visited = new HashSet<>();
		List<List<Integer>> conn = new ArrayList<>();
		Queue<Integer> que = new ArrayDeque<>();
		
		for (int i=0; i<n; i++) {
			if (visited.contains(i)) continue;
			que.offer(i);
			List<Integer> tmp = new ArrayList<>();
			while(!que.isEmpty()) {
				int head = que.poll();
				tmp.add(head);
				visited.add(head);
				for (int adj : graph.get(head)) {
					if (!visited.contains(adj)) {
						que.offer(adj);
					}
				}
				
			}
			conn.add(tmp);
		}
		
		int res = 0;
		
		for (List<Integer> tmp : conn) {
			if (tmp.size() == 1) {
				res++;
			} else {
				res += (int)Math.ceil(Math.sqrt(tmp.size()));
			}
			
		}
		return res;
	}
	
	private static Map<Integer, List<Integer>> buildGraph1(int n , int[][] edges){
		Map<Integer, List<Integer>> graph = new HashMap<>();
		for (int i=0; i<n; i++) {
			graph.put(i, new ArrayList<>());
		}
		
		for (int[] edge : edges) {
			int u = edge[0], v = edge[1];
			graph.get(u).add(v);
			graph.get(v).add(u);
		}
		
		return graph;
	}
}
