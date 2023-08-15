package dp.prefix.match;

/*
Implement wildcard pattern matching with support for '?' and '*'.

'?' Matches any single character.
'*' Matches any sequence of characters (including the empty sequence).
The matching should cover the entire input string (not partial).
1<=|s|, |p| <= 1000
It is guaranteed that 𝑠 only contains lowercase Latin letters and p contains lowercase Latin letters , ? and *

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
     * dpi 表示 source 的前 i 个字符是否能匹配得上 pattern 的前 j 个字符
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
