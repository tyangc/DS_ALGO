package dfs.onedimension;

import java.util.ArrayList;
import java.util.List;

/*
 90 · k数和（二）
Algorithms
Medium
Accepted Rate
49%

DescriptionSolutionNotesDiscussLeaderboard
Description
给定n个不同的正整数，整数k（1<=k<=n1<=k<=n）以及一个目标数字。
在这n个数里面找出K个数，使得这K个数的和等于目标数字，你需要找出所有满足要求的方案。

Example
样例 1：

输入：

数组 = [1,2,3,4]
k = 2
target = 5
输出：

[[1,4],[2,3]]
解释：

1+4=5,2+3=5

样例 2：

输入：

数组 = [1,3,4,6]
k = 3
target = 8
输出：

[[1,3,4]]
解释：

1+3+4=8

Tags
Related Problems
1689
k求和III
Medium
89
K数之和
Hard
 */
public class KSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param targer: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (A==null || A.length==0) return res;
        dfs(A, 0, new ArrayList<>(), k, target, res);
        return res;
    }

    //Combination way of thinking
    private void dfs(int[] A, int i, List<Integer> tmp, int k, int target, List<List<Integer>> res) {
      if (k==0 && target==0) {
        res.add(new ArrayList<>(tmp));
        return;
      }
      
      if (i>=A.length || A[i]>target) return;
    

      dfs(A, i+1, tmp, k, target, res);
      tmp.add(A[i]);
      dfs(A, i+1, tmp, k-1, target-A[i], res);
      tmp.remove(tmp.size()-1);
      
      //System.out.println(i + "|" + target + "|" + tmp);
    }
    
    
    public List<List<Integer>> kSumIIVer2(int[] A, int k, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (A==null || A.length==0) return res;
        dfs(A, 0, new ArrayList<>(), k, target, res);
        return res;
    }
 
    //receding variable recursion  - not a proper name??
    private void dfsVer2(int[] A, int index, List<Integer> tmp, int k, int target, List<List<Integer>> res) {
      if (k==0 && target==0) {
        res.add(new ArrayList<>(tmp));
        return;
      }
      
      if (k<0 || target<0) return;
    
      for (int i = index; i<A.length; i++) {

        if (k<0||A[i]>target) break;
        tmp.add(A[i]);
        dfs(A, i+1, tmp, k-1, target-A[i], res);
        tmp.remove(tmp.size()-1);
      }
      /*
      dfs(A, i+1, tmp, k, target, res);
      tmp.add(A[i]);
      dfs(A, i+1, tmp, k-1, target-A[i], res);
      tmp.remove(tmp.size()-1);
      */
      //System.out.println(i + "|" + target + "|" + tmp);
    }
}
