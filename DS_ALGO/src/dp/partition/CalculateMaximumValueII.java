package dp.partition;


public class CalculateMaximumValueII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(maxValue("012345"));
	}

	/**
     * @param str: a string of numbers
     * @return: the maximum value
     */
    public static int maxValue(String str) {
        // write your code here
        if (str==null || str.length()<2) return 0;
        
        int n = str.length();
        
        int[] nums = new int[n];
        
        char[] arr = str.toCharArray();
        
        for (int i=0; i<n; i++) {
            nums[i] = arr[i] - '0';
        }
        
        int[][] dp = new int[n][n];
        
        /*
        int[][] dpPlus = new int[n][n];
        
        int[][] dpTimes = new int[n][n];
        */
        
        for (int i=0; i<n; i++) {
            //dp[i][i] = dpPlus[i][i] = dpTimes[i][i] = nums[i];
        	dp[i][i] = nums[i];
            
        }
        /*
        for (int i=0; i<n; i++) {
            for (int j=i+1; j<n; j++) {
                dpPlus[i][j] = dpPlus[i][j-1] + nums[j];
                dpTimes[i][j] = dpTimes[i][j-1] * nums[j];
            }
        }
        */
        for (int length=2; length<=n; length++) {
            for (int i = 0; i<n-length+1; i++) {
                int j = i + length-1;
                for (int mid = i; mid<j; mid++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][mid] * dp[mid+1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][mid] + dp[mid+1][j]);
                }
            }
        }
     
        return dp[0][n-1];   
    }
}
