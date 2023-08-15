package dfs.twodimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//This is the version from Lintcode

public class WordSearchII2 {

	TrieTree trie; 
    
    public static int[] dx = {1, -1, 0, 0};
    public static int[] dy = {0, 0, 1, -1};
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param board: A list of lists of character
     * @param words: A list of string
     * @return: A list of string
     */
    
    
    public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        trie = new TrieTree();
   
        for (String str : words) {
            trie.insert(str);
        }
        
        int m=board.length, n=board[0].length;
        
        Set<String> result = new HashSet<>();
        
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
      
                dfs(board, i, j, trie.root, result);
   
            }
        }
        
        return new ArrayList<>(result);
        
    }
    
    private void dfs(char[][] board, int i, int j, TrieNode root, Set<String> result) {
        
        if (root==null) return;
        if (root.nodes[board[i][j]-'a']==null) return;
        
        TrieNode child = root.nodes[board[i][j]-'a'];
        if (child.word!=null) {
            result.add(child.word);
        }
       
        char tmp = board[i][j];
        board[i][j] = 0;
       
        for (int k=0; k<dx.length; k++) {
            int newX = i+dx[k];
            int newY = j+dy[k];
            
            
            
            if (isValid(newX, newY, board)) {
                
                //char newC = board[newX][newY];
                dfs(board, newX, newY, child, result);
             
            }
        }
        
         board[i][j] = tmp;
    }
    
    private boolean isValid(int x, int y, char[][] board) {
        int m=board.length, n=board[0].length;
        if (x<0 || x>=m || y<0 || y>=n ) {
            return false;
        }
        
        return board[x][y] != 0;
        
    }
}

/*
class TrieNode {
    TrieNode[] nodes; 
    
    String word;
    
    TrieNode() {
        nodes = new TrieNode[26];
        
        word = null;
    }
}
*/

class TrieTree {
    TrieNode root;
    
    public TrieTree() {
        root = new TrieNode();
    }
    
    public void insert(String word) {
        char[] arr = word.toCharArray();
        TrieNode t = root;
        
        for (int i=0; i<arr.length; i++) {
            if (t.nodes[arr[i]-'a']==null) {
                t.nodes[arr[i]-'a']=new TrieNode();
            }
            t = t.nodes[arr[i]-'a'];
        }
        t.word = word;
        System.out.println(word);
    }
    
}
