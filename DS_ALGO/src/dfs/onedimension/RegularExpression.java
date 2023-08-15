package dfs.onedimension;
/*
 * 154 · Regular Expression MatchingPRE
Algorithms
Hard
Accepted Rate
31%

DescriptionSolutionNotesDiscussLeaderboard
This topic is a pre-release topic. If you encounter any problems, please contact us via "Problem Correction", and we will upgrade your account to VIP as a thank you.
Description
The implementation supports regular expression matching for '.' and '*'. '.' matches any single character. '*' matches zero or more of the preceding elements, before '*' is guaranteed to be a non-'*' element. The match should cover the entire input string, not just a part of it. The function that needs to be implemented is:
bool isMatch(string s, string p)

isMatch("aa","a") → false
isMatch("aa","aa") → true
isMatch("aaa","aa") → false
isMatch("aa", "a*") → true
isMatch("aa", ".*") → true
isMatch("ab", ".*") → true
Wechat reply the 【154】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

Example
Example 1:

Input: "aa", "a"
Output: false
Explanation:
Can't match
Example 2:

Input: "aa", "a*"
Output: true
Explanation:
'*' can repeat a
Example 3:

Input: "aab", "c*a*b"
Output: true
Explanation:
"c*" matches 0'c' as a whole, which is ""
"a*" matches 2'a' as a whole, which is "aa"
"b" matches "b"
So "c*a*b" can match "aab"
``` repeat a
Tags
Dynamic Programming/DP
Two Sequences DP
Memoization Search
Related Problems
192
Wildcard Matching
Hard
 */
public class RegularExpression {
	
	//Will only pass 66% of case
	public boolean isMatch(String s, String p) {
	       
        if (p.length()==0) {
            return s.length()==0;
        }

        if (p.length()==1 || p.charAt(1)!='*') {
            if (s.length()<1 || (s.charAt(0)!=p.charAt(0) && p.charAt(0)!='.')) return false;
            return  isMatch(s.substring(1), p.substring(1));
        } else {

            int len = s.length();
            int i=-1;

            while(i<len && (i<0 || p.charAt(0)=='.' || s.charAt(i) == p.charAt(0))) {
                if (isMatch(s.substring(i+1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
	
	//Use Memoization: very clean way of thinking and presentation
	
	public boolean isMatchMemo(String s, String p) {
	      int m = s.length(), n = p.length();
	      return isMatchHelper(s, 0, p, 0, new boolean[m][n], new boolean[m][n]);
	    }
	    
	    public boolean isMatchHelper(String s, int sIndex, String p, int pIndex, boolean[][] visited, boolean[][] memo) {
	        //System.out.println(s + "|" + p + "|" + sIndex + "|" + pIndex);  //Right way for debugging
	        
	        if (pIndex==p.length()) {
	          return s.length() == sIndex;
	        }

	        if (sIndex==s.length()) {
	          return allEmpty(p, pIndex);
	        }
	        
	        if (visited[sIndex][pIndex]) {
	          return memo[sIndex][pIndex];
	        }

	        boolean match = false;
	        char sChar = s.charAt(sIndex);
	        char pChar = p.charAt(pIndex);


	        if (pIndex+1<p.length() && p.charAt(pIndex+1)=='*') {
	          match = (charMatch(sChar, pChar) && isMatchHelper(s, sIndex+1, p, pIndex, visited, memo)) || 
	                    isMatchHelper(s, sIndex, p, pIndex+2, visited, memo);

	        } else {
	          if (charMatch(sChar, pChar)) {
	            match = isMatchHelper(s, sIndex+1, p, pIndex+1, visited, memo);
	          } else {
	            match = false;
	          }
	        }

	        visited[sIndex][pIndex] = true;
	        memo[sIndex][pIndex] = match;
	        return match;
	    }  

	    private boolean charMatch(char s, char p) {
	      if (s == p || p == '.') {
	        return true;
	      }

	      return false;
	    }

	    private boolean allEmpty(String p, int index) {
	      for (int i=index; i<p.length(); i=i+2) {
	        if (i+1>=p.length() || p.charAt(i+1)!='*') return false;
	      }
	      return true;
	    }
}
