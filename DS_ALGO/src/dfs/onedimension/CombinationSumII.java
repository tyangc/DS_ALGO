package dfs.onedimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 153 · Combination Sum II
Algorithms Medium Accepted Rate 40%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an array num and a number target. Find all unique combinations in num where the numbers sum to target.

Each number in num can only be used once in one combination.
All numbers (including target) will be positive integers.
Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
Different combinations can be in any order.
The solution set must not contain duplicate combinations.
Example
Example 1:

Input: num = [7,1,2,5,1,6,10], target = 8
Output: [[1,1,6],[1,2,5],[1,7],[2,6]]
Example 2:

Input: num = [1,1,1], target = 2
Output: [[1,1]]
Explanation: The solution set must not contain duplicate combinations.
Tags
Related Problems
790 Parser Medium
740 Coin Change 2 Medium
653 Expression Add Operators Hard
135 Combination Sum Medium
 */
public class CombinationSumII {

	/**
     * @param num: Given the candidate numbers
     * @param target: Given the target number
     * @return: All the combinations that sum to target
     */
    public List<List<Integer>> combinationSum2(int[] num, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (num==null || num.length==0) return res;
        Arrays.sort(num);
        dfs(num, 0, target, new ArrayList<>(), res);
        return res;
    }

    private void dfs(int[] num, int index, int target,  List<Integer> tmp, List<List<Integer>> res) {
       if (0 == target) {
        //tmp.add(target);
        res.add(new ArrayList<>(tmp));
        return;
      } 

      //dfs(num, index+1, target, tmp, res);

      for (int i=index; i<num.length; i++) {

        if (i!=index && num[i-1] == num[i]) continue;

        if (num[i]>target) break;

        tmp.add(num[i]);
      
        dfs(num, i+1, target-num[i], tmp, res);

        tmp.remove(tmp.size()-1);

      }

    }
}
