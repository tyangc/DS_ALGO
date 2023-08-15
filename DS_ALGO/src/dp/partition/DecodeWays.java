package dp.partition;
/*
 * https://www.lintcode.com/problem/512/
 * Description
A message containing letters from A-Z is being encoded to numbers using the following mapping:

'A' -> 1
'B' -> 2
...
'Z' -> 26
Given an encoded message containing digits, determine the total number of ways to decode it.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang0607)


we can't decode an empty string. So you should return 0 if the message is empty.
The length of message n \leq 100n≤100

Example
Example 1:

Input: "12"
Output: 2
Explanation: It could be decoded as AB (1 2) or L (12).
Example 2:

Input: "10"
Output: 1
Tags
Company
Facebook
Microsoft
Uber
Google
 */
public class DecodeWays {
	public int numDecodings(String s) {
        // write your code here
        if (s==null || s.length()==0 || s.equals("0")) return 0;
        int n=s.length();
    
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        
        for (int i=2; i<=n; i++) {
            dp[i] = dp[i-1] * decodeOk(s.substring(i-1, i)) + dp[i-2] * decodeOk(s.substring(i-2, i));
        }
        return dp[n];
    }

    private int decodeOk(String str) {
        int a = Integer.valueOf(str);
        if (str.length()==1) {
            if (a==0) return 0;
            else return 1;
        }
        
        if ( a>=10 && a<=26 ) return 1;
        return 0;
    }
    
    public int numDecodingsCycleArray(String s) {
        // write your code here
        if (s==null || s.length()==0 || s.equals("0")) return 0;
        int n=s.length();
    
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;  //dp[1] = decodeOk(s.substring(0,1));  //also ok
        
        for (int i=2; i<=n; i++) {
            dp[i%3] = dp[(i-1)%3] * decodeOk(s.substring(i-1, i)) + dp[(i-2)%3] * decodeOk(s.substring(i-2, i));
        }
        return dp[n%3];
    }

}
