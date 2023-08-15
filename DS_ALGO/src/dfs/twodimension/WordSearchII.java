package dfs.twodimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 132. Word Search II

Given a matrix of lower alphabets and a dictionary. Find all words in the dictionary that can be found in the matrix. A word can start from any position in the matrix and go left/right/up/down to the adjacent position. One character only be used once in one word. No same word in dictionary

Example
Example 1:

Input：["doaf","agai","dcan"]，["dog","dad","dgdg","can","again"]
Output：["again","can","dad","dog"]
Explanation：
  d o a f
  a g a i
  d c a n
search in Matrix，so return ["again","can","dad","dog"].
Example 2:

Input：["a"]，["b"]
Output：[]
Explanation：
 a
search in Matrix，return [].
Challenge
Using trie to implement your algorithm.
 */
public class WordSearchII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/*
	 This version is not optimal and has bug:
	 pass only 30% test cases:
	 
	 30% test cases passed         Total runtime 787 ms
	Input:

	["abce","sfes","adee"]
	["abceseeefs","abceseedasfe"]
	
	Output:
	
	["abceseeefs"]
	
	Expected:
	
	["abceseedasfe","abceseeefs"]
	 
	 */

	/**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    
    TrieNode root = new TrieNode(); 
    
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
     
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        
        
        for (String str : words) {
            addWord(str);
    
        }
        
        int m=board.length, n=board[0].length;
        boolean[][] visited = new boolean[m][n];
        Set<String> result = new HashSet<>();
        //List<String> result = new ArrayList<>();
        StringBuilder sb = new StringBuilder();; 
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                 
                visited[i][j] = true;
                
                dfs(board, i, j, sb, visited, result);
                
                visited[i][j] = false;
            }
        }
        
        return new ArrayList<>(result);
        
    }
    
    private void dfs(char[][] board, int i, int j, StringBuilder sb, boolean[][] visited, Set<String> result) {
        if (sb.length()>0 && !startWith(sb.toString())) {
            return;
        }
        
        if (searchWord(sb.toString())) { 
            result.add(sb.toString());
        }
        
        
        
        for (int k=0; k<dx.length; k++) {
            int newX = i+dx[k];
            int newY = j+dy[k];
            
            if (isValid(newX, newY, board) && !visited[newX][newY]) {
                
                visited[i][j] = true;   //This is logically wrong , should be outside!
                sb.append(board[i][j]);
                dfs(board, newX, newY, sb, visited, result);
                visited[i][j] = false;
                sb.deleteCharAt(sb.length()-1);
            }
        }
    }
    
    private boolean isValid(int x, int y, char[][] board) {
        int m=board.length, n=board[0].length;
        return x>=0 && x<m && y>=0 && y<n;
        
    }
    private void addWord(String word) {
        TrieNode t = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (t.nodes[c-'a']==null) {
                t.nodes[c-'a'] = new TrieNode();
                
            }
            t = t.nodes[c-'a'];
        }
        t.isWord = true;
        t.word = word;
        
    }
    
    private boolean searchWord(String word) {
        if (word==null) return false;
        TrieNode t = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (t.nodes[c-'a']==null) {
                return false;
            }
            
            t = t.nodes[c-'a'];
        }
        
        return t.isWord;
    }
    
    private boolean startWith(String word) {
        if (word==null) return false;
        TrieNode t = root;
        char[] arr = word.toCharArray();
        for (char c : arr) {
            if (t.nodes[c-'a']==null) {
                return false;
            }
            
            t = t.nodes[c-'a'];
        }
        
        return true;
    }

}

class TrieNode {
    TrieNode[] nodes; 
    boolean isWord;
    String word;
    
    TrieNode() {
        nodes = new TrieNode[26];
        isWord = false;
        word = null;
    }
}
