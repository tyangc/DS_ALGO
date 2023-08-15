package datastructure.trie;

import java.util.HashMap;
import java.util.Map;

/*
 Implement a Trie with insert, search, and startsWith methods.

You may assume that all inputs are consist of lowercase letters a-z.

Example 1:

Input:
  insert("lintcode")
  search("lint")
  startsWith("lint")
Output:
  false
  true
Example 2:

Input:
  insert("lintcode")
  search("code")
  startsWith("lint")
  startsWith("linterror")
  insert("linterror")
  search("lintcode“)
  startsWith("linterror")
Output:
  false
  true
  false
  true
  true

 */
public class Trie {

	TrieNode root;
    
    public Trie() {
        // do intialization if necessary
        root = new TrieNode();
    }

    /*
     * @param word: a word
     * @return: nothing
     */
    public void insert(String word) {
        // write your code here
        if (word==null || word.length()==0) return;
        TrieNode node = root;
        for (int i=0; i<word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                node.children.put(letter, new TrieNode());
            }
            node = node.children.get(letter);
        }
        
        node.isWord = true;
        node.word = word;
    }

    /*
     * @param word: A string
     * @return: if the word is in the trie.
     */
    public boolean search(String word) {
        // write your code here
        TrieNode node = root;
        if (word==null || word.length()==0) return false;
        for (int i=0; i<word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                return false;
            }
            
            node = node.children.get(letter);
        }
        
        return node.isWord;
        
    }

    /*
     * @param prefix: A string
     * @return: if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        // write your code here
        TrieNode node = root;
        if (prefix==null || prefix.length()==0) return false;
        for (int i=0; i<prefix.length(); i++) {
            char letter = prefix.charAt(i);
            if (!node.children.containsKey(letter)) {
                return false;
            }
            
            node = node.children.get(letter);
        }
        
        return true;
        
    }
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

class TrieNode {
    Map<Character, TrieNode> children;
    boolean isWord;
    String word;
    
    public TrieNode() {
        children = new HashMap<>();
        isWord = false;
        word = null;
    }
}
