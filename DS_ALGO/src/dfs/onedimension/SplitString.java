package dfs.onedimension;

import java.util.ArrayList;
import java.util.List;

/*
 680 · Split String Algorithms Medium Accepted Rate 44%

DescriptionSolutionNotesDiscussLeaderboard
Description
Give a string, you can choose to split the string after one character or two adjacent characters, and make the string to be composed of only one character or two characters. Output all possible results.

Example
Example1

Input: "123"
Output: [["1","2","3"],["12","3"],["1","23"]]
Example2

Input: "12345"
Output: [["1","23","45"],["12","3","45"],["12","34","5"],["1","2","3","45"],["1","2","34","5"],["1","23","4","5"],["12","3","4","5"],["1","2","3","4","5"]]
Tags
Related Problems
702
Concatenated String with Uncommon Characters of Two Strings
Easy
582
Word Break II
Hard
107
Word Break
Medium
18
Subsets II
Medium
17
Subsets
Medium
 */
public class SplitString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param : a string to be split
     * @return: all possible split string array
     */
    public List<List<String>> splitString(String s) {
        // write your code here
        List<List<String>> res = new ArrayList<>();
        if (s==null || s.length()==0) {
          res.add(new ArrayList<String>());
          return res;
        }
        List<String> tmp = new ArrayList<>();
        dfs(s,  tmp, res);
        return res;
    }

    private void dfs(String s, List<String> tmp, List<List<String>> res) {

      if (s.length()==0) {
        res.add(new ArrayList<>(tmp));
        return;
      }

      //if (pos+1>=s.length()) {
        for (int i=1; i<=2; i++) {
          if (s.length()>=i) {
        
            tmp.add(s.substring(0, i)); 
            dfs(s.substring(i), tmp, res);
            tmp.remove(tmp.size()-1);
          }
        }

/*
        tmp.add(s.substring(0, 1));
        dfs(s.substring(1), tmp, res);
        tmp.remove(tmp.size()-1);
      //} else {

      
      
      if (s.length()>=2) {
        
        tmp.add(s.substring(0, 2)); 
        dfs(s.substring(2), tmp, res);
        tmp.remove(tmp.size()-1);
      } */
    }
}
	

