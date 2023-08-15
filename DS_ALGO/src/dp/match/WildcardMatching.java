package dp.match;

/*
192 · Wildcard Matching
Algorithms
Hard
Accepted Rate
34%

DescriptionSolutionNotesDiscussLeaderboard
Description
Implement wildcard pattern matching with support for '?' and '*'.The matching rules are as follows：

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

1<=|s|, |p| <= 1000
It is guaranteed that 𝑠 only contains lowercase Latin letters and p contains lowercase Latin letters , ? and *

Example
Example 1

Input:
"aa"
"a"
Output: false
Example 2

Input:
"aa"
"aa"
Output: true
Example 3

Input:
"aaa"
"aa"
Output: false
Example 4

Input:
"aa"
"*"
Output: true
Explanation: '*' can replace any string
Example 5

Input:
"aa"
"a*"
Output: true
Example 6

Input:
"ab"
"?*"
Output: true
Explanation: '?' -> 'a' '*' -> 'b'
Example 7

Input:
"aab"
"c*a*b"
Output: false
Tags
Related Problems
154
Regular Expression Matching
Hard

 */
public class WildcardMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(isMatch("abcfda", "a?c*a"));
	}

	/**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public static boolean isMatch(String s, String p) {
        // write your code here
        if (s==null || p==null) return false;
        int n=s.length(), m=p.length();
        boolean[][] dp = new boolean[n+1][m+1];
        
        dp[0][0] = true;
        for (int i=1; i<=m; i++) {
            dp[0][i] = dp[0][i-1] && p.charAt(i-1)=='*';
        }
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (p.charAt(j-1)=='*') {
                    dp[i][j] = dp[i-1][j] || dp[i][j-1];
                } else {
                    dp[i][j] = dp[i-1][j-1] && (s.charAt(i-1)==p.charAt(j-1) || p.charAt(j-1)=='?');
                }
                
            }
        }
        
        return dp[n][m];
    }
}
