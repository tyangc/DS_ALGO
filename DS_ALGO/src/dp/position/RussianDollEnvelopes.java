package dp.position;

import java.util.Arrays;
import java.util.Comparator;

/*
 * https://www.lintcode.com/problem/602/
 * 602 · Russian Doll Envelopes
Algorithms
Hard
Accepted Rate
26%
Description
Solution23
Notes99+
Discuss
Leaderboard
Record
Description
Give a number of envelopes with widths and heights given as a pair of integers (w, h). One envelope can fit into another if and only if both the width and height of one envelope is greater than the width and height of the other envelope.
Find the maximum number of nested layers of envelopes.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang0607)


Example
Example 1:

Input：[[5,4],[6,4],[6,7],[2,3]]
Output：3
Explanation：
the maximum number of envelopes you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).
Example 2:

Input：[[4,5],[4,6],[6,7],[2,3],[1,1]]
Output：4
Explanation：
the maximum number of envelopes you can Russian doll is 4 ([1,1] => [2,3] => [4,5] / [4,6] => [6,7]).
Tags
Company
Facebook
Google
Related Problems

76
Longest Increasing Subsequence
Medium
 */
public class RussianDollEnvelopes {
	
	//Solitaire DP: 91% passed
	public int maxEnvelopes(int[][] envelopes) {
        // write your code here

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] a1, int[] a2) {
                if (a1[0] == a2[0]) {
                    return a2[1] - a1[1];
                } else {
                    return a1[0] - a2[0];
                }
            }
        });
        int n=envelopes.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);

        for (int i=1; i<n; i++) {
            for (int j=0; j<i; j++) {
                if ( envelopes[i][1] > envelopes[j][1]  && dp[j]+1>dp[i] ) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        int ret = 0;
        for (int i : dp) {
            if (i>ret) ret = i;
        }
        return ret;
    }
}
