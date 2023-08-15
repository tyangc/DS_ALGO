package unionfind;
/*
 Connecting Graph II

Given n nodes in a graph labeled from 1 to n. There is no edges in the graph at beginning.

You need to support the following method:

connect(a, b), an edge to connect node a and node b
query(a), Returns the number of connected component nodes which include node a.

Example 1:

Input:
ConnectingGraph2(5)
query(1)
connect(1, 2)
query(1)
connect(2, 4)
query(1)
connect(1, 4)
query(1)
Output:[1,2,3,3]
Example 2:

Input:
ConnectingGraph2(6)
query(1)
query(2)
query(1)
query(5)
query(1)

Output:
[1,1,1,1,1]
 */

import java.util.HashMap;
import java.util.Map;

public class ConnectingGraphII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Map<Integer, Integer> father;
    Map<Integer, Integer> setNum;
    int numCount;
    
    /*
    * @param n: An integer
    */public ConnectingGraphII(int n) {
        // do intialization if necessary
        father = new HashMap<>();
        setNum = new HashMap<>();
        numCount=0;
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
            setNum.put(bRoot, setNum.get(aRoot)+setNum.get(bRoot));
        }
    }

    /*
     * @param a: An integer
     * @return: An integer
     */
    public int query(int a) {
        // write your code here
        add(a);
        //if (father.get(a)==null) return setNum.get(x);
        int root = find(a);
        return setNum.get(root);
    }
    
    private void add(int x) {
        if (father.containsKey(x)) {
            return;
            
        }
        father.put(x, null);
        setNum.put(x, 1);
        numCount++;
    }
    
    private int find(int x) {
        int root = x;
        
        if(father.get(x)==null) return x;
        
        while(father.get(root)!=null) {
            root = father.get(root);
        }
        
        //Path compression
        while(x!=root) {
            int orgFather = father.get(x);
            father.put(x, root);
            x=orgFather;
            
        }
        return root;
    }
}

