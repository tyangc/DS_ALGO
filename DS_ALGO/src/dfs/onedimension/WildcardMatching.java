package dfs.onedimension;
/*
 mplement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).

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
Notice
1<=|s|, |p| <= 1000
It is guaranteed that 𝑠 only contains lowercase Latin letters and p contains lowercase Latin letters , ? and *
 */
public class WildcardMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatch(String s, String p) {
        // write your code here

        if (s.length()==0) return p.equals("*");

        return dfs(s, p, 0, 0);
        
    }

    //This will pass 93% but run time too long for example:
    
    /*
     input:
"abbabaaabbabbaababbabbbbbabbbabbbabaaaaababababbbabababaabbababaabbbbbbaaaabababbbaabbbbaabbbbababababbaabbaababaabbbababababbbbaaabbbbbabaaaabbababbbbaababaabbababbbbbababbbabaaaaaaaabbbbbaabaaababaaaabb"
"**aa*****ba*a*bb**aa*ab****a*aaaaaa***a*aaaa**bbabb*b*b**aaaaaaaaa*a********ba*bbb***a*ba*bb*bb**a*b*bb"
     */
    public boolean dfs(String s, String p, int i, int j) {
        if (i==s.length()) {
            if ( j==p.length() || (p.charAt(j)=='*' && j==p.length()-1)) return true;
            return false;
        }

        if (j==p.length()) {
            if ( p.charAt(j-1)=='*' || i==s.length()) return true;
            return false;
        }
        char sChar = s.charAt(i);
        char pChar = p.charAt(j);

        if (sChar==pChar || pChar=='?') {
            return dfs(s, p, i+1, j+1);
        } 

        if (pChar=='*') {
            
            for (int k=i; k<s.length(); k++) {
                if (j<p.length() && dfs(s, p, k, j+1)) {
                    return true;
                }
            } 
        }

        return false;
    }
    
    
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatchIterative(String s, String p) {
        // write your code here

        if (s.length()==0) return p.equals("*");

        int i=0, j=0;
        int iIdx= -1, jIdx=-1;

        while(i<s.length()) {
            if (j<p.length() && (s.charAt(i)==p.charAt(j) || p.charAt(j)=='?')) {
                i++;
                j++;
            } else if (j<p.length() && p.charAt(j)=='*') {
                iIdx = i;
                jIdx = j;
                j++;
            } else if (jIdx!=-1) {
                j = jIdx +1;
                i = iIdx +1;
                iIdx++;
            } else {
                return false;
            }
        }
        
        while(j<p.length() && p.charAt(j)=='*') {
            j++;
        }
        return j==p.length();
    }
    
    //dfs implementation with memorized data, this won't run exceed time limit
    
    /**
     * @param s: A string 
     * @param p: A string includes "?" and "*"
     * @return: is Match?
     */
    public boolean isMatchMemo(String s, String p) {
        // write your code here
        if (s==null || p==null) return false;

        int slen = s.length(), plen = p.length();

        boolean[][] memo = new boolean[slen][plen];
        boolean[][] visited = new boolean[slen][plen];

        return dfsMemo(s, 0, p, 0, memo, visited);

        
    }

    private boolean dfsMemo(String s, int sIdx, String p, int pIdx, boolean[][] memo, boolean[][] visited) {
        if (pIdx == p.length()) {
            return sIdx == s.length();

        }

        if (sIdx == s.length()) {
            return ifAllStar( p, pIdx);
        }

        if (visited[sIdx][pIdx]) return memo[sIdx][pIdx];

        char schar = s.charAt(sIdx);
        char pchar = p.charAt(pIdx);
        boolean match;

        if (pchar=='*') {
            match = dfsMemo(s, sIdx+1, p, pIdx, memo, visited) || dfsMemo(s, sIdx, p, pIdx+1, memo, visited) ;
        } else {
            if (isMatch(schar, pchar)) {
                match = dfsMemo(s, sIdx+1, p, pIdx+1, memo, visited);
            } else {
                match = false;
            }
        }

        visited[sIdx][pIdx] = true;
        memo[sIdx][pIdx] = match;

        return match;

     }

     private boolean ifAllStar(String p, int pIdx) {
         while(pIdx<p.length() && p.charAt(pIdx)=='*') {
             pIdx++;
         }

         return pIdx == p.length();
     }

     private boolean isMatch(char s, char p) {
         return s==p || p == '?';
     }
   
}
