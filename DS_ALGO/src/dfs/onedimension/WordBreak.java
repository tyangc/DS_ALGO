package dfs.onedimension;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/*
 * 107 · Word Break
Algorithms
Medium
Accepted Rate
19%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string s and a dictionary of words dict, determine if s can be broken into a space-separated sequence of one or more dictionary words.
Because we have used stronger data, the ordinary DFS method can not pass this question now.

Wechat reply question number to get job skills free video package. (wechat id : jiuzhang15)

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
Dynamic Programming/DP
Depth First Search/DFS
Partition DP
Memoization Search
Related Problems
680
Split String
Medium
582
Word Break II
Hard
 */
public class WordBreak {
	//Using DP
	public boolean wordBreakDp(String s, Set<String> wordSet) {
        // write your code here
        //if (wordSet==null || ) return false;
        if (s.length()==0 && wordSet.size()==0) return true;

        int n = s.length();

        boolean[] dp = new boolean[n+1];

        dp[0] = true;
        Set<Integer> lens = new TreeSet<>();
        for (String dic : wordSet) {
            //dp[dic.length()] = true;
            lens.add(dic.length());
        }

        for (int i=0; i<=n; i++) {
            for (int j : lens ) {

            	if (i<j) break;
                if (!dp[i-j]) continue;
                if (wordSet.contains(s.substring(i-j, i))) {
                    dp[i] = true;  //dp[i] = [i-j]; //wrong logic, might reverse back 
                    break;  //optimized
                }
                //System.out.println(i+"|"+j+Arrays.toString(dp));
            }
        }

        return dp[n];
    }
	
	//LintCode reference implementation: much better performance for the test cases provided in LintCode  //? why ?
	public boolean wordBreakLint(String s, Set<String> dict) {
        if (s == null) {
            return true;
        }
        
        int maxLength = 0;
        for (String word : dict) {
            maxLength = Math.max(maxLength, word.length());
        }
      
        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        
        for (int i = 1; i <= n; i++) {
            for (int l = 1; l <= maxLength; l++) {
                if (i < l) {
                    break;
                }
                if (!dp[i - l]) {
                    continue;
                }
                String word = s.substring(i - l, i);
                if (dict.contains(word)) {
                    dp[i] = true;
                    break;
                }
            }
        }
        
        return dp[n];
    }
	
	//Using dfs with Trie and memoization 
	public boolean wordBreakDfs(String s, Set<String> wordSet) {
        // write your code here
        Trie trie = new Trie();
        for (String word : wordSet) {
            trie.add(word);
        }

        return dfs(s, new HashMap<>(), trie);

    }

	//Time limit exceeded at 96% of test cases
    private boolean dfs(String s, Map<String, Boolean> memo, Trie trie) {
        if (s.length()==0) {
            return true;
        }

        if (memo.containsKey(s)) {
            return memo.get(s);
        }

        boolean split = false;
        for (int i=1; i<=s.length(); i++) {
            if (!trie.startsWith(s.substring(0, i))) {
                continue;
            } else {
                if (trie.has(s.substring(0, i))) {  //Use wordSet.contains() here ran into same time out issue 
                    split = dfs(s.substring(i), memo, trie);
                    if (split) break;
                }
                
            }
        }

        memo.put(s, split);
        return split;
    }
    
    class Trie {
        TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        public void add(String word) {
            TrieNode p = root;
            for (int i=0; i<word.length(); i++) {
                char c = word.charAt(i);
                if (p.nodes[c-'a'] == null) {
                    p.nodes[c-'a'] = new TrieNode();
                }

                p = p.nodes[c-'a'];

            } 
            p.isWord = true;
        }

        public boolean startsWith(String word) {
            TrieNode p = root;
            int i=0;

            while(i<word.length()) {
                char c = word.charAt(i);
                if (p.nodes[c-'a'] == null) return false;
                p = p.nodes[c-'a'];
                i++;
            }
            return true;
        }

        public boolean has(String word) {
            TrieNode p = root;
            int i=0;

            while(i<word.length()) {
                char c = word.charAt(i);
                if (p.nodes[c-'a'] == null) {
                    return false;
                }
                p = p.nodes[c-'a'];
                i++;
            }
            return p.isWord;
        }
    }

    class TrieNode {
        TrieNode[] nodes;
        boolean isWord;

        public TrieNode() {
            nodes = new TrieNode[26];
            isWord = false;
        }
    }
}
