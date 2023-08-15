package dfs.searchtree;

import java.util.ArrayList;
import java.util.List;

public class PermutationI {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param nums: A list of integers.
     * @return: A list of permutations.
     */
    public List<List<Integer>> permute(int[] nums) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (nums==null ) return res;
        boolean[] visited = new boolean[nums.length];
        dfs(nums, 0, visited, new ArrayList<Integer>(), res);
        
        return res;
        
    }
    
    private void dfs(int[] nums, int index, boolean[] visited, List<Integer> permute, List<List<Integer>> result) {
        if (index==nums.length) {   //Can use permute size to determine exit point, so index not necessary
            result.add(new ArrayList<Integer>(permute));
            return;
        }
        
        for (int i=0; i<nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            
            visited[i] = true;
            permute.add(nums[i]);
            
            dfs(nums, index+1, visited, permute, result);
            
            visited[i] = false;
            permute.remove(permute.size()-1);
        }
        
    
    }
}
