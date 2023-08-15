package dp.partition;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/*
 * https://www.lintcode.com/problem/107
 * 107 · Word Break
Algorithms
Medium
Accepted Rate
20%
Description
Solution62
Notes99+
Discuss11
Leaderboard
Record
Description
Given a string s and a dictionary of words dict, determine if s can be broken into a space-separated sequence of one or more dictionary words.
Because we have used stronger data, the ordinary DFS method can not pass this question now.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang0607)


s.length <= 1e5
dict.size <= 1e5

Example
Example 1:

Input:

s = "lintcode"
dict = ["lint", "code"]
Output:

true
Explanation:

Lintcode can be divided into lint and code.

Example 2:

Input:

s = "a"
dict = ["a"]
Output:

true
Explanation:

a is in the dict.

Tags
Related Problems

582
Word Break II
Hard

680
Split String
Medium
 */
public class WordBreak {
	public boolean wordBreak(String s, Set<String> wordSet) {
        // write your code here

        int n=s.length();

        boolean dp[] = new boolean[n+1];
        dp[0] = true;

        int maxLen = 0;

        for (String str : wordSet) {
            maxLen = Math.max(maxLen, str.length());
        }

        for (int i=1; i<=n; i++) {
            for (int l=1; l<=maxLen; l++) {
            //for (int l=maxLen; l>0; l--) {    
                if (l>i) break;
                if (!dp[i-l]) continue;
                if (wordSet.contains(s.substring(i-l, i))){
                    dp[i] = true;
                    break;
                }
                
            }
        }
        System.out.println(Arrays.toString(dp));
        return dp[n];
    }
	
	//Memory limit exceeded at 96% of test case , recursive depth is too large for the data
	public boolean wordBreakMemoDfs(String s, Set<String> wordSet) {
        // write your code here

        int n=s.length();

        int maxLen=0, minLen=Integer.MAX_VALUE;

        for (String str : wordSet) {
            maxLen = Math.max(maxLen, str.length());
            minLen = Math.min(minLen, str.length());
        }

        return dfs(s, wordSet, new HashMap<String, Boolean>(), minLen, maxLen);

    }

    boolean dfs(String s, Set<String> wordSet, Map<String, Boolean> memo, int min, int max) {
        if (s.length()==0) return true;

        if (memo.containsKey(s)) return memo.get(s);

        for (int i=min; i<=max; i++) {
            if (s.length()<i) break;
            if ( !wordSet.contains(s.substring(0, i)) ) continue;
            if ( dfs(s.substring(i), wordSet, memo, min, max) ) return true;
            //This is wrong !!! : return dfs(s.substring(i), wordSet, memo, min, max); //or relationship among all branches
            
        }

        memo.put(s, Boolean.FALSE);
        return false;
    }
}
