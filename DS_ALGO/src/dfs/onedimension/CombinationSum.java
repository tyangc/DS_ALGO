package dfs.onedimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 135 · Combination Sum
Algorithms
Medium
Accepted Rate
36%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a set of candidate numbers candidates and a target number target. Find all unique combinations in candidates where the numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

All numbers (including target) will be positive integers.
Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
Different combinations can be in any order.
The solution set must not contain duplicate combinations.
Example
Example 1:

Input: candidates = [2, 3, 6, 7], target = 7
Output: [[7], [2, 2, 3]]
Example 2:

Input: candidates = [1], target = 3
Output: [[1, 1, 1]]
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
652
Factorization
Medium
153
Combination Sum II
Medium
 */
public class CombinationSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param candidates: A list of integers
     * @param target: An integer
     * @return: A list of lists of integers
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (candidates==null) return res;
        Arrays.sort(candidates);
        dfs(candidates, 0, target, new ArrayList<Integer>(), res);
        return res;
    }

    private void dfs(int[]  arr, int index,  int target, List<Integer> tmp, List<List<Integer>> res) {
      
      if (target==0) {
        res.add(new ArrayList<>(tmp));
        return;
      } else if (target<0) {
        //tmp.remove(tmp.size()-1);
        return;
      }
      

      for (int i=index; i<arr.length; i++) {
        if (i>0 && arr[i-1]==arr[i]) continue;
         /*
        if (arr[i]==target) {
           tmp.add(arr[i]);
           res.add(new ArrayList<>(tmp));
           //if (tmp.size()>0) tmp.remove(tmp.size()-1);
           //tmp = new ArrayList<>();
           return;

        } else if (arr[i]>target) {
          //if (tmp.size()>0) tmp.remove(tmp.size()-1);
          return;
        }
        */
        tmp.add(arr[i]);
        dfs(arr, i,  target-arr[i], tmp, res);
        if (tmp.size()>0)tmp.remove(tmp.size()-1);
      }
    }
    
    //Another way of dfs: this way seems faster
    
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();

        if (candidates == null) return res;
        Arrays.sort(candidates);

        dfs1( candidates, 0, 0, target, new ArrayList<>(), res);
        return res;

    }

    private void dfs1(int[] nums, int index, int sum, int target, List<Integer> tmp, List<List<Integer>> res) {
        if (sum == target) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        

        if (sum > target || index == nums.length ) return;

        //if (index>0 && nums[index] == nums[index-1]) return;

        /*
        for (int i=index; i<nums.length; i++) {
            tmp.add(nums[i]);
            dfs(nums, index, sum+nums[i], target, tmp, res);
            tmp.remove(tmp.size()-1);
        }
        */
        if (index==0 || (index>0 && nums[index] != nums[index-1])) {
            tmp.add(nums[index]);
            dfs1(nums, index, sum+nums[index], target, tmp, res);
            tmp.remove(tmp.size()-1);
        }
        dfs1(nums, index+1, sum, target, tmp, res);
        //tmp.remove(tmp.size()-1);

        /*
        tmp.add(nums[index+1]);
        dfs(nums, index+1, sum+nums[index+1], target, tmp, res);
        tmp.remove(tmp.size()-1);
        */
    }
}
