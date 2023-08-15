package datastructure.trie;
/*
 Identifying Strings
 
Given n character strings containing only lower case letters, find the minimum prefix strings that can identify each string.
That is, the minimum prefix string Ap which identifies string A will not be a prefix string of other n-1 character strings.

1 <= n <= 500
The length of strings would not exceed 100.
If string S is a profix of string T, the answer of S will be itself.

example:

Input:["aaa","bbc","bcd"]
Output:["a","bb","bc"]
Explanation:"a" is only the profix of "aaa".
"bb" is only the profix of "bbc".
"bc" is only the profix of "bcd".
 */
public class IdentifyMinPrefix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


    TrieNode root = new TrieNode();
    
    /**
     * @param stringArray: a string array
     * @return: return every strings'short peifix
     */
    public String[] ShortPerfix(String[] stringArray) {
        // write your code here
        for (String word : stringArray) {
            addWord(root, word);
        }
        
        String[] res = new String[stringArray.length];
        
        for (int i=0; i<res.length; i++) {
            res[i] = getPrefix(stringArray[i]);
            
        }
        
        return res;
   
    }
    
    private String getPrefix(String word) {
        TrieNode node = root;
        for (int i=0; i<word.length(); i++) {
            char letter = word.charAt(i);
            int index = letter - 'a';
            int pre = node.children[index].prefixCount;
            if (pre==1) {
                return word.substring(0, i+1);
            }
            node=node.children[index];
        }
        
        return word;
    }
    
    private void addWord(TrieNode root, String word) {
        
        TrieNode node = root;
        
        for (int i=0; i<word.length(); i++) {
            char letter = word.charAt(i);
            int index = letter - 'a';
            
            if (node.children[index]==null) {
                node.children[index] = new TrieNode();
                
            }
            node.children[index].prefixCount++;
            node = node.children[index];
            
        }
        
        node.isWord = true;
        node.word = word;
        
    }
    
    class TrieNode {
        TrieNode[] children;
        boolean isWord;
        String word;
        int prefixCount;
        
        public TrieNode() {
            
            children = new TrieNode[26];
            isWord = false;
            word = null;
            prefixCount = 0;
            
            
        }
    }
}
