package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 531 · Six Degrees
Medium Accepted Rate 45%

DescriptionSolutionNotesDiscussLeaderboard
Description
Six degrees of separation is a philosophical problem, which means that everyone and everything can be connected through six steps or less.

Now give you a friendship, calculate how many steps two people can be connected through, if not, return -1.

Example
Example1

Input: {1,2,3#2,1,4#3,1,4#4,2,3} and s = 1, t = 4 
Output: 2
Explanation:
    1------2-----4
     \          /
      \        /
       \--3--/
Example2

Input: {1#2,4#3,4#4,2,3} and s = 1, t = 4
Output: -1
Explanation:
    1      2-----4
                 /
               /
              3
Tags
Company
Microsoft
Related Problems
137 Clone Graph
 */
public class SixDegrees {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param graph: a list of Undirected graph node
     * @param s: Undirected graph node
     * @param t: Undirected graph nodes
     * @return: an integer
     */
    public int sixDegrees(List<UndirectedGraphNode> graph, UndirectedGraphNode s, UndirectedGraphNode t) {
        // write your code here

        Queue<UndirectedGraphNode> que = new LinkedList<>();
        Set<UndirectedGraphNode> visited = new HashSet<>();

        que.offer(s); 
        visited.add(s);
        int step = 0;

        while(!que.isEmpty()) {
          
          int size = que.size();
          for (int i=0; i<size; i++) {
            UndirectedGraphNode cur = que.poll();
            if (cur==t) {
              return step;
            } 

            for (UndirectedGraphNode n : cur.neighbors) {
                if (!visited.contains(n)) {
                  que.offer(n);
                }
            }

          }

          step++;

        }

        return -1;
    }
}

/**
* Definition for Undirected graph.
*/
class UndirectedGraphNode {
     int label;
     List<UndirectedGraphNode> neighbors;
     UndirectedGraphNode(int x) { 
         label = x;
         neighbors = new ArrayList<UndirectedGraphNode>(); 
     }
 };
