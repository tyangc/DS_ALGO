package dfs.onedimension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*
582. Word Break II

Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

Example
Example 1:

Input："lintcode"，["de","ding","co","code","lint"]
Output：["lint code", "lint co de"]
Explanation：
insert a space is "lint code"，insert two spaces is "lint co de".
Example 2:

Input："a"，[]
Output：[]
Explanation：dict is null.
*/

public class WordBreakII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param s: A string
     * @param wordDict: A set of words.
     * @return: All possible sentences.
     */
    public List<String> wordBreak(String s, Set<String> wordDict) {
        // write your code here
        Map<String, List<String>> memo = new HashMap<>();
        return dfs1(s, wordDict, memo);

    }

    //Divide and conquer - dfs - memorized dfs
    
    private List<String> dfs1(String s, Set<String> dict, Map<String, List<String>> memo) {
        if (memo.containsKey(s)) {
            return memo.get(s);
        }
        
        if (s.length()==0) return new ArrayList<String>();
        
        List<String> res = new ArrayList<String>();
        
        if (dict.contains(s)) {
            res.add(s);
        }
        
        for (int i=1; i<s.length(); i++) {
            String cur = s.substring(0, i);
            if (!dict.contains(cur)) continue;
            
            List<String> segs = dfs1(s.substring(i), dict, memo);
            
            for (String seg : segs) {
                res.add(cur + " " + seg);
            }
        }
        
        memo.put(s, res);
        return res;
    }
    
    //Another way, too complex:
    
    public List<String> wordBreak2(String s, Set<String> wordDict) {
        // write your code here

        Trie trie = new Trie();
        for (String word : wordDict) {
            trie.add(word);
        }

        List<String> ret = dfs(s, new HashMap<String, List<String>>(), trie);
        if (ret==null) return new ArrayList<>();
        return ret;
    }

    private List<String> dfs(String s, Map<String, List<String>> memo, Trie trie) {
        if (s.length()==0) {
            return new ArrayList<String>();
        }

        if (memo.containsKey(s)) return memo.get(s);
        List<String> res = null; //new ArrayList<>();    
        for (int i=1; i<=s.length(); i++) {
            if (!trie.startsWith(s.substring(0,i))) continue;

            if (trie.has(s.substring(0,i))) {
                System.out.println(s.substring(0,i));
                List<String> nxt = dfs(s.substring(i), memo, trie);
                System.out.println(nxt);
                if (nxt==null) continue;
                if (res==null) res = new ArrayList<>();
                if (nxt.size()==0) {
                    
                    res.add(s.substring(0,i));
                } else {
                    for (String tmp : nxt) {
                        res.add(s.substring(0,i) + " " + tmp);
                    }
                }
            }
        }

        memo.put(s, res);
        return res;
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
