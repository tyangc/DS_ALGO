package dp.partition;
/*
 683 · Word Break III
Algorithms
Medium
Accepted Rate
44%

DescriptionSolutionNotesDiscussLeaderboard
Description
Give a dictionary of words and a sentence with all whitespace removed, return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.

Ignore case

Example
Example1

Input:
"CatMat"
["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
Output: 3
Explanation:
we can form 3 sentences, as follows:
"CatMat" = "Cat" + "Mat"
"CatMat" = "Ca" + "tM" + "at"
"CatMat" = "C" + "at" + "Mat"
Example1

Input:
"a"
[]
Output: 
0
Tags
Dynamic Programming/DP Partition DP
 */
import java.util.HashSet;
import java.util.Set;

public class WordBreakIII {

	/*
     * @param : A string
     * @param : A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        if (s == null ||s.length() == 0 || dict == null || dict.size() == 0) {
            return 0;
        }
        //将字符全部转化为小写，并将dict转换成hash_stet存储，降低判断子串存在性的时间复杂度
        s = s.toLowerCase();
        Set<String> set = new HashSet<String>();
        for (String word : dict) {
            String str = word.toLowerCase();
            set.add(str);
        }
        
        //dp[i]表示s[0:i](不含s[i])的拆分方法数
        int len = s.length();
        int[] dp = new int[len + 1];

        //dp[0]表示空串的拆分方法数
        dp[0] = 1;
        
        for (int i = 0; i < len; i++) {
            for (int j = i; j < len; j++) {
                //若存在匹配，则进行状态转移
                if (set.contains(s.substring(i, j + 1))) {
                    dp[j + 1] += dp[i];
                }
            }
        }
        return dp[len];
    }
}
