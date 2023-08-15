package dfs.onedimension;
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
Dynamic Programming/DP  Partition DP
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//This solution did not work 100% , 82% passed, failed for memory usage.

public class WordBreakIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param s: A string
     * @param dict: A set of word
     * @return: the number of possible sentences.
     */
    
    Trie trie;
    public int wordBreak3(String s, Set<String> dict) {
        // Write your code here
        /*
        s = s.toLowerCase();

        Set<String> set = new HashSet<>();

        for (String word: dict) {
            set.add(word.toLowerCase());
        }
        */
        
        if (trie==null) trie = new Trie();

        for (String word : dict) {
          trie.add(word.toLowerCase());  
        }

        List<String> result = dfs(s.toLowerCase(), trie, new HashMap<String, List<String>>());

        return result.size();


    }

    private List<String> dfs(String s, Trie root, Map<String, List<String>> memo) {
      List<String> res = new ArrayList<>();
      if (s.length()==0) return res;
      if (memo.containsKey(s)) {
        return memo.get(s);
      }

      if (root.hasWord(s)) res.add(s);

      for (int len=1; len<s.length(); len++) {
        String prefix = s.substring(0, len);
        if (!root.startWith(prefix)) break;
        String posfix = s.substring(len);
        if (root.hasWord(prefix)) {
          List<String> next = dfs(posfix, root, memo);
          for (String str : next) {
            res.add(prefix + " " + str);
          }

        }
      }

      if (res.size()>0) {
        memo.put(s, res);
      }

      return res;

    }
    
}

class TrieNode {
    Map<Character, TrieNode> nodes;
    String word;

    public TrieNode() {
        nodes = new HashMap<>();
        word = null;
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode();
    }

    public void add(String word) {
        TrieNode t = root;

        char[] arr = word.toCharArray();

        for (int i=0; i<arr.length; i++) {
            if (!t.nodes.containsKey(arr[i])) {
                t.nodes.put(arr[i], new TrieNode());

            }
            t = t.nodes.get(arr[i]);

        }

        t.word = word;
        //System.out.println("Trie add: " + word);
    }

    public boolean startWith(String word) {
        TrieNode t = root;
        char[] arr = word.toCharArray();

        for (char c  : arr ) {
          if (!t.nodes.containsKey(c)) {
            return false;
          } else {
            t = t.nodes.get(c);
          }

          
        }

        return true;
      }

      public boolean hasWord(String word) {
        TrieNode t = root;
        char[] arr = word.toCharArray();

        for (char c  : arr ) {
          if (!t.nodes.containsKey(c)) {
            return false;
          } else {
            t = t.nodes.get(c);
          }

          
        }

        return t.word!=null;
      }
}
