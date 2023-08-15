package dfs.onedimension;

import java.util.ArrayList;
import java.util.List;

/*
 152 · Combinations
Algorithms Medium Accepted Rate 44%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given two integers n and k. Return all possible combinations of k numbers out of 1, 2, ... , n.

You can return all combinations in any order, but numbers in a combination should be in ascending order.

Example
Example 1:

Input: n = 4, k = 2
Output: [[1,2],[1,3],[1,4],[2,3],[2,4],[3,4]]
Example 2:

Input: n = 4, k = 1
Output: [[1],[2],[3],[4]]
Tags
Related Problems
740
Coin Change 2
Medium
739
24 Game
Hard
653
Expression Add Operators
Hard
34
N-Queens II
Medium
33
N-Queens
Medium
 */
public class KofNCombination {

	/**
     * @param n: Given the range of numbers
     * @param k: Given the numbers of combinations
     * @return: All the combinations of k numbers out of 1..n
     */
    public List<List<Integer>> combine(int n, int k) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[n+1];

        dfs(n, k, 1, visited, new ArrayList<>(), res);
        return res;
        
    }

    //Can not use Set of  Sum to exclude duplicates e.g. {2,3} vs {1, 4} 
    
    private void dfs(int n, int k, int start, boolean[] visited, List<Integer> tmp, List<List<Integer>> res) {
      if (tmp.size()==k) {
        
          res.add(new ArrayList<>(tmp));
          return;
      }
   

      for (int i=start; i<=n; i++) {  //Use direction to exclude duplicates
        if (visited[i]) continue;

        visited[i] = true;
        tmp.add(i);
        dfs(n, k, i+1, visited, tmp,  res);
        visited[i] = false;
        tmp.remove(tmp.size()-1);
      }
    }
}
