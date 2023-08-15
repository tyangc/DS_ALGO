package dfs.onedimension;
/*
 * https://leetcode.com/problems/word-pattern-ii/
 * 
 829 · Word Pattern II
Algorithms Hard Accepted Rate 47%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a pattern and a string str, find if str follows the same pattern.

Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty substring in str.(i.e if a corresponds to s, then b cannot correspond to s. For example, given pattern = "ab", str = "ss", return false.)

You may assume both pattern and str contains only lowercase letters.

Example
Example 1

Input:
pattern = "abab"
str = "redblueredblue"
Output: true
Explanation: "a"->"red","b"->"blue"
Example 2

Input:
pattern = "aaaa"
str = "asdasdasdasd"
Output: true
Explanation: "a"->"asd"
Example 3

Input:
pattern = "aabb"
str = "xyzabcxzyabc"
Output: false
Tags
Company
Uber
Related Problems
828
Word Pattern  Easy

 */

import java.util.HashMap;
import java.util.Map;

public class WordPatternII {

	/**
     * @param pattern: a string,denote pattern string
     * @param str: a string, denote matching string
     * @return: a boolean
     */
    public boolean wordPatternMatch(String pattern, String str) {
        // write your code here
        if (pattern.length()==0) return false;

        return dfs(pattern, str, new HashMap<Character, String>());
    }

	private boolean dfs(String p, String s, Map<Character, String> map) {
		if (p.length() == 0 && s.length() == 0)
			return true;

		if (p.length() == 0 || s.length() == 0)
			return false;

		char pChar = p.charAt(0);

		//Process this unique situation first, always extract and process the unique logic first. LAYERS of LOGIC!!!
		if (map.containsKey(pChar)) {
			if (!s.startsWith(map.get(pChar))) return false;

			return dfs(p.substring(1), s.substring(map.get(pChar).length()), map);
		}

		for (int i = 1; i <= s.length(); i++) {
			String sub = s.substring(0, i);

			if (map.values().contains(sub)) continue;
			map.put(pChar, sub);

			if (dfs(p.substring(1), s.substring(i), map)) return true;

			map.remove(pChar);

		}

		return false;
	}
	
	private boolean dfsWithVisited(String p, String s,  Map<Character, String> map, Set<String> visited ) {
        if (p.length()==0) return s.length()==0;
        //System.out.println(p+"|"+s);
        char a = p.charAt(0);
        if (map.containsKey(a)) {
             if (!s.startsWith(map.get(a))) {
                 return false;
             }else {
                return dfs(p.substring(1), s.substring(map.get(a).length()), map, visited);
             }
                            
        } 
        
        for (int i=1; i<=s.length(); i++) {
            String cur = s.substring(0,i);
            if (visited.contains(cur)) continue;
            map.put(p.charAt(0), cur);
            visited.add(cur);
            if (dfs(p.substring(1), s.substring(i), map, visited)) return true;
            map.remove(p.charAt(0));
            visited.remove(cur);
        }
        
        //System.out.println(map);
        
        return false;
    }
}


