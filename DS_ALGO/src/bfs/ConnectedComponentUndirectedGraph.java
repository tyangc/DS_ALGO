package bfs;

/*
 * 
 */
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeSet;

public class ConnectedComponentUndirectedGraph {
	/**
     * @param nodes: a array of Undirected graph node
     * @return: a connected set of a Undirected graph
     */
    public List<List<Integer>> connectedSet(List<UndirectedGraphNode> nodes) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();

        if (nodes == null || nodes.size() == 0) return res;

        Set<UndirectedGraphNode> visited = new HashSet<>();

        Queue<UndirectedGraphNode> que = new ArrayDeque<>();

        for ( UndirectedGraphNode node : nodes ) {
            if (!visited.contains(node)) {
                que.offer(node);
                visited.add(node);  //Don't forget this one!!
                Set<Integer> tmp = new TreeSet<>();
                while(!que.isEmpty()) {
                    UndirectedGraphNode cur = que.poll();
                   // if (!visited.contains(cur)) {   //Not necessary
                        tmp.add(cur.label);
                        //visited.add(cur);
                        for ( UndirectedGraphNode neighbor : cur.neighbors ) {
                            if (!visited.contains(neighbor)) {
                                que.offer(neighbor);
                                visited.add(neighbor);  //Here seems to be better
                            }
                        }
                    //}
                 }
                
                //Can use this if tmp is a list: Collections.sort(tmp);   //Pay attention to the test case data order!
                res.add(new ArrayList<>(tmp));
            }
        }
        return res;

    }
    
    //Using union find:
    
    class UnionFind {
        Map<Integer, Integer> map;
        public UnionFind() {
            map = new HashMap<>();

        }

        public int find(int x) {
            if (!map.containsKey(x)) {
                map.put(x, x);
            }

            int father = map.get(x);

            if (father == x) return father;
            //x = father;
            father = find(father);
            map.put(x, father);
            return father;
        }

        public void connect(int x, int y) {
            int fx = find(x);
            int fy = find(y);
            if (fx != fy ) {
                map.put(Math.min(fx, fy), Math.max(fx, fy));
            }
        }
    }
    public List<List<Integer>> connectedSet1(List<UndirectedGraphNode> nodes) {

        List<List<Integer>> res = new ArrayList<>();
        if (nodes == null || nodes.size() == 0) return res;
        UnionFind uf = new UnionFind();
        Map<Integer, List<Integer>> tmp = new HashMap<>();

        for (UndirectedGraphNode node : nodes) {
            //int fnode = uf.find(node.label);
            uf.find(node.label);  //This can not be skipped e.g. {1}
            for (UndirectedGraphNode neighbor : node.neighbors) {
                //int fneighbor = uf.find(neighbor.label);
                uf.connect(node.label, neighbor.label);
            }

        }

        for ( int key : uf.map.keySet() ) {
            int fa = uf.find(key);

            List<Integer> lst = tmp.getOrDefault(fa, new ArrayList<>());
            lst.add(key);
            tmp.put(fa, lst);
        }

        for (List<Integer> lst : tmp.values()) {
            lst.sort(null);
            res.add(lst);

        }

        return res;
        
    }
}
