package datastructure.unionfind;

import java.util.HashMap;
import java.util.Map;

/*
 Connecting Graph

Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:

connect(a, b), add an edge to connect node a and node b`.
query(a, b), check if two nodes are connected

Example 1:

Input:
ConnectingGraph(5)
query(1, 2)
connect(1, 2)
query(1, 3) 
connect(2, 4)
query(1, 4) 
Output:
[false,false,true]

Example 2:

Input:
ConnectingGraph(6)
query(1, 2)
query(2, 3)
query(1, 3)
query(5, 6)
query(1, 4)

Output:
[false,false,false,false,false]

 */
public class ConnectingGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Map<Integer, Integer> father;
    
    /*
    * @param n: An integer
    */public ConnectingGraph(int n) {
        // do intialization if necessary
        father = new HashMap<>();
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: nothing
     */
    public void connect(int a, int b) {
        // write your code here
        add(a);
        add(b);
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot!=bRoot) {
            father.put(aRoot, bRoot);
        } 
        
    }

    /*
     * @param a: An integer
     * @param b: An integer
     * @return: A boolean
     */
    public boolean query(int a, int b) {
        // write your code here
        int aRoot = find(a);
        int bRoot = find(b);
        if (aRoot!=bRoot) {
            return false;
        } 
        return true;
    }
    
    public void add(int x) {
        if (father.containsKey(x)) {
            return;
        }
        
        father.put(x, null);
    }
    
    public int find(int x) {
        int root = x;
        while(father.get(root)!=null) {
            root = father.get(root);
        }
        return root;
    }
}
