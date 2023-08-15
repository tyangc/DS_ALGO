package dfs.searchtree;

import java.util.ArrayList;
import java.util.List;

/*
 90. k Sum II
中文English
Given n unique postive integers, number k (1<=k<=n) and target.

Find all possible k integers where their sum is target.

Example
Example 1:

Input: [1,2,3,4], k = 2, target = 5
Output:  [[1,4],[2,3]]
Example 2:

Input: [1,3,4,6], k = 3, target = 8
Output:  [[1,3,4]]	
 */
public class KSum2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[A.length];
        dfs(A, 0, k, target, visited, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void dfs(int[] A, int index, int k, int target, boolean[] visited, List<Integer> tmp, List<List<Integer>> res) {
        //if (tmp.size()>k || target<0 ) return;
        
        if (tmp.size()==k && target==0) {
            res.add(new ArrayList<Integer>(tmp));
            
            return;
        }
        
        for (int i=index; i<A.length; i++) {
        
            if (tmp.size()>k || target<A[i]) {
                break;
            }
            
            visited[i] = true;
            tmp.add(A[i]);
        
            dfs(A, i+1, k, target-A[i], visited, tmp, res);
        
            visited[i] = false;
            tmp.remove(tmp.size()-1);
        }
        
    }
}
