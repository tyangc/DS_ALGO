package dfs.searchtree;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

/*
 10. String Permutation II

Given a string, find all permutations of it without duplicates.

Example
Example 1:

Input: "abb"
Output:
["abb", "bab", "bba"]
Example 2:

Input: "aabb"
Output:
["aabb", "abab", "baba", "bbaa", "abba", "baab"]
 */
public class StringPermutation2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /**
     * @param str: A string
     * @return: all permutations
     */
    public List<String> stringPermutation2(String str) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (str==null || str.isEmpty()) {
            res.add("");
            return res;
        }
        
        char[] arr = str.toCharArray();
        Arrays.sort(arr);
        int n = arr.length;
        boolean[] visited = new boolean[n];
        StringBuilder sb = new StringBuilder();
        
        dfs(arr, n, 0, visited, sb, res);
        
        return res;
    }
    
    private void dfs(char[] arr, int n, int index, boolean[] visited, StringBuilder sb,  List<String> res) {
        
        if (index==n) {
            res.add(new String(sb.toString()));
            return;
        }
        
        for (int i=0; i<n; i++) {
            
            if (visited[i]) {
                continue;
            }
        
            if (i>0 && arr[i-1] == arr[i] && !visited[i-1]) { //Remove duplicates
                continue;
            }
        
            visited[i] = true;
            sb.append(arr[i]);
            dfs(arr, n, index+1, visited, sb, res);
            visited[i] = false;
            sb.deleteCharAt(index);
        }
    
    }
	
	
}
