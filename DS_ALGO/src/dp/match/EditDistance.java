package dp.match;
/*
 Given two words word1 and word2, find the minimum number of steps required to convert word1 to word2. (each operation is counted as 1 step.)

You have the following 3 operations permitted on a word:

Insert a character
Delete a character
Replace a character
  
 */
public class EditDistance {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minDistance("horse", "rose"));
	}

	/**
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public static int minDistance(String word1, String word2) {
        // write your code here
        if (word1==null||word2==null) return -1;
        
        int m=word1.length(), n=word2.length();
        int[][] dp = new int[m+1][n+1];
        
        //dp[0][0] = 0
        
        for (int i=0; i<=m; i++) {
            dp[i][0] = i;
        }
        
        for (int i=0; i<=n; i++) {
            dp[0][i] = i;
        }
        
        for (int i=1; i<=m; i++) {
            for (int j=1; j<=n; j++) {
                dp[i][j] = Math.min(dp[i-1][j]+1, dp[i][j-1]+1);
                if (word1.charAt(i-1)==word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                } else {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]+1);
                }
            }
        }
        
        return dp[m][n];
    }
}
