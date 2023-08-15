package dp;

public class LongestPalindromicSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getLongest("abccdedccab"));
		
		//System.out.println(getLongest("abb"));

	}

	private static String getLongest(String s) {
		if (s==null || s.length()<2) return s;
		int n=s.length();
		int start=0, longest=1;
		boolean[][] dp = new boolean[n][n];
				
		for (int i=0; i<n-1; i++) {
			dp[i][i] = true;
			if (s.charAt(i)==s.charAt(i+1)) {
				dp[i][i+1] = true;
				start=i;
				longest = 2;
			}
		}
		
		dp[n-1][n-1] = true;
		
		for (int i=n-1; i>=0; i--) {
			System.out.println(i+ ":");
			for (int j=i+2; j<n; j++) {
				System.out.println(i+" | " + j);
				dp[i][j] = dp[i+1][j-1] && s.charAt(i)==s.charAt(j);
				if (dp[i][j]&&j-i+1>longest) {
					start=i;
					longest=j-i+1;
				}
			}
		}
		
		return s.substring(start, start+longest);
	}
}
