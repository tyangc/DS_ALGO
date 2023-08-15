package datastructure.trie;

import java.util.HashMap;
import java.util.Map;

/*
 Add and Search Word - Data structure design

Design a data structure that supports the following two operations: addWord(word) and search(word)

search(word) can search a literal word or a regular expression string containing only letters a-z or ..

A . means it can represent any one letter.

You may assume that all words are consist of lowercase letters a-z.


Example 1:

Input:
  addWord("a")
  search(".")
Output:
  true
Example 2:

Input:
  addWord("bad")
  addWord("dad")
  addWord("mad")
  search("pad")  
  search("bad")  
  search(".ad")  
  search("b..")  
Output:
  false
  true
  true
  true
 */

public class AddSearchWordWithDot {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param word: Adds a word into the data structure.
     * @return: nothing
     */
    
    TrieNode root= new TrieNode(); 
    
    public void addWord(String word) {
        // write your code here
        //if (root==null) root= new TrieNode(); 
        TrieNode node = root;
        for (int i=0; i<word.length(); i++) {
            char letter = word.charAt(i);
            if (!node.children.containsKey(letter)) {
                node.children.put(letter, new TrieNode());
            }
            
            /*
            if (i==word.length()-1) {
                node.isWord = true;
                node.word = word;
            }
            */
            node = node.children.get(letter);
            
        }
        
        node.isWord = true;
        node.word = word;
    }

    /*
     * @param word: A word could contain the dot character '.' to represent any one letter.
     * @return: if the word is in the data structure.
     */
    public boolean search(String word) {
        // write your code here
        
        return dfs(root, word, 0); 
    }
    
    private boolean dfs(TrieNode node, String word, int index) {
        if (index==word.length()) {  //Height of the Tire is the length of word+1 !!!
            return node.isWord;
        }
        char letter = word.charAt(index);
        if (letter=='.') {
            for (Character c : node.children.keySet()) {
                if(dfs(node.children.get(c), word, index+1)) {  //Can not just return dfs(node.children.get(c), word, index+1); since this is OR logic
                    return true;
                }
            }
            return false;
        }
        
        if(node.children.containsKey(letter)) {
            
            return dfs(node.children.get(letter), word, index+1);
           
        }
        
        return false;
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
}
