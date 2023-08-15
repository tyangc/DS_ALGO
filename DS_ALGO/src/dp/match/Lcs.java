package dp.match;

/*
 Given two strings, find the longest common subsequence (LCS).

Your code should return the length of LCS.

 
 */
public class Lcs {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestCommonSubsequence("bcfa","cafdaf"));
	}

	/**
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public static int longestCommonSubsequence(String A, String B) {
        // write your code here
        
        if (A==null || B==null) return 0;
        
        int m=A.length(), n=B.length();
        
        int[][] dp = new int[m+1][n+1];
        
        //dp[0][0] = 0;
        
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (A.charAt(i-1)==B.charAt(j-1)) 
                    dp[i][j] = Math.max(dp[i][j], dp[i-1][j-1]+1);
            }
        }
        return dp[m][n];
    }
}
