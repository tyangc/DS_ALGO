package dfs.onedimension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 425 · Letter Combinations of a Phone Number
Algorithms
Medium
Accepted Rate
43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a digit string excluded 0 and 1, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

1	2
ABC	3
DEF
4
GHI	5
JKL	6
MNO
7
PQRS	8
TUV	9
WXYZ
Although the answer above is in lexicographical order, your answer could be in any order you want.

Example
Example 1:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Explanation: 
'2' could be 'a', 'b' or 'c'
'3' could be 'd', 'e' or 'f'
Example 2:

Input: "5"
Output: ["j", "k", "l"]
Tags
Company
Amazon
Facebook
Dropbox
Uber
Google
Related Problems
935
Cartesian Product
Medium
 */

public class LetterCombinationsOfPhoneNumber {
	/**
     * @param digits: A digital string
     * @return: all posible letter combinations
     */
    public List<String> letterCombinations(String digits) {
        // write your code here

        List<String> res = new ArrayList<>();

        if (digits==null||digits.length()==0) return res;

        Map<Character, String> map = new HashMap<>();
        map.put('2', "ABC");
        map.put('3', "DEF");
        map.put('4', "GHI");
        map.put('5', "JKL");
        map.put('6', "MNO");
        map.put('7', "PQRS");
        map.put('8', "TUV");
        map.put('9', "WXYZ");

        dfs(digits, 0, map, "", res);
        //dfs(digits, 0, map, new StringBuilder(), res);
        return res;
    }

    //162ms
    private void dfs(String st, int idx, Map<Character, String> map, String tmp, List<String> res) {
      if (idx == st.length()) {
        res.add(tmp.toLowerCase());
        return;
      }

      char cur = st.charAt(idx);

      for (char curChar : map.get(cur).toCharArray()) {
        dfs(st, idx+1, map, tmp+curChar, res);
      }
    }
    
    /* This will also work 182ms 
     private void dfs(String st, int idx, Map<Character, String> map, StringBuilder tmp, List<String> res) {
      if (idx == st.length()) {
        res.add(tmp.toString().toLowerCase());
        return;
      }

      char cur = st.charAt(idx);

      for (char curChar : map.get(cur).toCharArray()) {
        tmp.append(curChar);
        dfs(st, idx+1, map, tmp, res);
        tmp.deleteCharAt(tmp.length()-1);
      }
    }
     */
    
}
