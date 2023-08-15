package dfs.searchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 135. Combination Sum

Given a set of candidtate numbers candidates and a target number target. Find all unique combinations in candidates where the numbers sums to target.

The same repeated number may be chosen from candidates unlimited number of times.

Example
Example 1:

Input: candidates = [2, 3, 6, 7], target = 7
Output: [[7], [2, 2, 3]]
Example 2:

Input: candidates = [1], target = 3
Output: [[1, 1, 1]]
Notice
All numbers (including target) will be positive integers.
Numbers in a combination a1, a2, … , ak must be in non-descending order. (ie, a1 ≤ a2 ≤ … ≤ ak)
Different combinations can be in any order.
The solution set must not contain duplicate combinations.
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
        if (candidates==null) {
            res.add(new ArrayList<Integer>());
            return res;
        }
        
        int[] nums = removeDups(candidates);
        
        dfs(nums, 0, target, new ArrayList<Integer>(), res);
        
        return res;
    }
    
    private void dfs(int[] nums, int index, int target, List<Integer> tmp, List<List<Integer>>res) {
        if (target==0) {
            res.add(new ArrayList<>(tmp));
            return;
            
        }
        
        /*
        if (nums[index]>target) {
            return;
        }
        */
        for (int i=index; i<nums.length; i++) {
            if (nums[i]>target) {
                break;
            }
            tmp.add(nums[i]);
            dfs(nums, i, target-nums[i], tmp, res);
            tmp.remove(tmp.size()-1);
        }
        
    }
    
    private int[] removeDups(int[] nums) {
        Arrays.sort(nums);
        
        int j=0;
        
        for(int i=0; i<nums.length; i++) {
            if (nums[j]!=nums[i]) {
                nums[++j]= nums[i];
            }
        }
        
        int[] res = Arrays.copyOf(nums, j+1);
        return res;
    }
}
