package dfs.searchtree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: A set of numbers
     * @return: A list of lists
     */
	
	//Combination type
    public List<List<Integer>> subsets(int[] nums) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (nums==null ) return result;
        List<Integer> subset = new ArrayList<>();
        //boolean[] visited = new boolean[nums.length];
        Arrays.sort(nums);
        dfs(nums, 0,  subset, result);
        
        return result;
    }
    
    private void dfs(int[] nums, int index, List<Integer> subset, List<List<Integer>> res) {
        if (index==nums.length) {
            res.add(new ArrayList<Integer>(subset));
            return;
        }
        
        subset.add(nums[index]);
        dfs(nums, index+1, subset, res);
        
        subset.remove(subset.size()-1);
        dfs(nums, index+1, subset, res);
    }
	
}
