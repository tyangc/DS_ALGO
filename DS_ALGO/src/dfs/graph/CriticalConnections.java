package dfs.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

//https://leetcode.com/problems/critical-connections-in-a-network/
//https://www.lintcode.com/problem/1271/solution/17457
/*
 1192. Critical Connections in a Network
Hard

2570

124

Add to List

Share
There are n servers numbered from 0 to n - 1 connected by undirected server-to-server connections forming a network where connections[i] = [ai, bi] represents a connection between servers ai and bi. Any server can reach other servers directly or indirectly through the network.

A critical connection is a connection that, if removed, will make some servers unable to reach some other server.

Return all critical connections in the network in any order.

 

Example 1:



Input: n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
Output: [[1,3]]
Explanation: [[3,1]] is also accepted.
 

Constraints:

2 <= n <= 105
n - 1 <= connections.length <= 105
0 <= ai, bi <= n - 1
ai != bi
There are no repeated connections.
Accepted
118,636
Submissions
230,056
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
24

Apple
|
2

 */
public class CriticalConnections {
	private Map<Integer, HashSet<Integer>> initGraph(List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = new HashMap<>();
        
        for(List<Integer> connection:connections) {
            graph.putIfAbsent(connection.get(0), new HashSet<>());
            graph.get(connection.get(0)).add(connection.get(1));
            graph.putIfAbsent(connection.get(1), new HashSet<>());
            graph.get(connection.get(1)).add(connection.get(0));
        }
        return graph;
    } 
    
    private int dfs(int child, int father, int step, int[] steps, Map<Integer, HashSet<Integer>> graph, 
                    List<List<Integer>> res) {
        int curStep = step + 1;
        steps[child] = curStep;
        for(int connection:graph.get(child)) {
            if(connection == father) {
                continue;
            } else if(steps[connection] == -1) {
                steps[child] = Math.min(steps[child], dfs(connection, child, curStep, steps, graph, res));
            } else {
                steps[child] = Math.min(steps[child], steps[connection]);
            }
        }

        if(child != 0 && steps[child] == curStep) {
            List<Integer> critial = new ArrayList<>();
            if(father > child) {
                int tmp = child;
                child = father;
                father = tmp;
            }

            critial.add(father);
            critial.add(child);
            res.add(critial);
        }
        
        return steps[child];
    }
    
    public List<List<Integer>> criticalConnectionsinaNetwork(int n, List<List<Integer>> connections) {
        Map<Integer, HashSet<Integer>> graph = initGraph(connections);
        int[] steps = new int[n];
        Arrays.fill(steps, -1);
        
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, -1, 0, steps, graph, res);
        return res;
    }
}
