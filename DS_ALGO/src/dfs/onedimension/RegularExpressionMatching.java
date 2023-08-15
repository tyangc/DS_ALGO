package dfs.onedimension;

/*
 154. Regular Expression Matching

Implement regular expression matching with support for '.' and '*'.

'.' Matches any single character.
'*' Matches zero or more of the preceding element.
The matching should cover the entire input string (not partial).


The function prototype should be:

bool isMatch(string s, string p)

isMatch("aa","a") → false

isMatch("aa","aa") → true

isMatch("aaa","aa") → false

isMatch("aa", "a*") → true

isMatch("aa", ".*") → true

isMatch("ab", ".*") → true

isMatch("aab", "c*a*b") → true

Example
Example 1:

Input："aa"，"a"
Output：false
Explanation：
unable to match
Example 2:

Input："aa"，"a*"
Output：true
Explanation：
'*' can repeat a
 */
		
public class RegularExpressionMatching {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param s: A string 
     * @param p: A string includes "." and "*"
     * @return: A boolean
     */
	
	//Use dfs plus while loop for "x*" case:  525ms
    public boolean isMatch1(String s, String p) {
       
        if (p.length()==0) {
            return s.length()==0;
        }

        if (p.length()==1 || p.charAt(1)!='*') {
            if (s.length()<1 || (s.charAt(0)!=p.charAt(0) && p.charAt(0)!='.')) return false;
            return  isMatch1(s.substring(1), p.substring(1));
        } else {

            int len = s.length();
            int i=-1;

            while(i<len && (i<0 || p.charAt(0)=='.' || s.charAt(i) == p.charAt(0))) {
                if (isMatch1(s.substring(i+1), p.substring(2))) {
                    return true;
                }
                i++;
            }
            return false;
        }
    }
    
    //Without memo 231 ms
    public boolean isMatch2(String s, String p) {
        
        return dfs(s, 0, p, 0);
       
    }
    
    private boolean dfs(String s, int sIdx, String p, int pIdx ) {
        if (pIdx==p.length()) {
            return sIdx==s.length();
        }

        if (sIdx==s.length()) {
            return isEmpty(pIdx, p);
        }

        boolean match;

        if (pIdx+1<p.length() && p.charAt(pIdx+1)=='*') {
            match = dfs(s, sIdx, p, pIdx+2) || (equals(s.charAt(sIdx), p.charAt(pIdx)) && dfs(s, sIdx+1, p, pIdx));
        } else {
            match = equals(s.charAt(sIdx), p.charAt(pIdx)) && dfs(s, sIdx+1, p, pIdx+1);
        }

        return match;
    }

    //with memo: 182ms
    public boolean isMatchMemo(String s, String p) {
        
        int m = s.length(), n=p.length();
        boolean[][] memo = new boolean[m][n];
        boolean[][] visited = new boolean[m][n];

         
         return dfsMemo(s, 0, p, 0, memo, visited);
        
     }

     private boolean dfsMemo(String s, int sIdx, String p, int pIdx, boolean[][] memo, boolean[][] visited ) {
         if (pIdx==p.length()) {
             return sIdx==s.length();
         }

         if (sIdx==s.length()) {
             return isEmpty(pIdx, p);
         }

         boolean match;

         if (visited[sIdx][pIdx]) {
             return memo[sIdx][pIdx];
         }

         if (pIdx+1<p.length() && p.charAt(pIdx+1)=='*') {
             match = dfsMemo(s, sIdx, p, pIdx+2, memo, visited) || (equals(s.charAt(sIdx), p.charAt(pIdx)) && dfsMemo(s, sIdx+1, p, pIdx, memo, visited));
         } else {
             match = equals(s.charAt(sIdx), p.charAt(pIdx)) && dfsMemo(s, sIdx+1, p, pIdx+1, memo, visited);
         }

         visited[sIdx][pIdx] = true;
         memo[sIdx][pIdx] = match;

         return match;
     }

    
    
    private boolean isEmpty(int pIdx,String p) {

        for (int i=pIdx; i<p.length(); i+=2) {
            if (i+1>=p.length() ||  p.charAt(i+1)!='*') {
                return false;
            }
        }

        return true;
    }

    private boolean equals(char a, char b) {
        return a==b || b=='.';
    }
}
