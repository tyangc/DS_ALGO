package dfs.matrix;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 * 132 · Word Search II
Algorithms
Hard
Accepted Rate
35%

DescriptionSolutionNotesDiscussLeaderboard
Description
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

Tags
Related Problems
635
Boggle Game
Hard
 */
public class WordSearchII {
	public List<String> wordSearchII(char[][] board, List<String> words) {
        // write your code here
        Trie trie = new Trie();
        for (String word : words) {
            trie.addWord(word);
        }
        //System.out.println(trie.root.isWord);
        //List<String> res = new ArrayList<>(); //? should be set
        Set<String> res = new HashSet<>();
        if (board == null || board.length == 0 ) return new ArrayList<String>(res);

        int n = board.length, m = board[0].length;
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                char c = board[i][j];
                if (trie.root.nodes[c-'a'] == null) continue;
                boolean[][] visited = new boolean[n][m];
                visited[i][j] = true;
                dfs(board, i, j, visited, c+"", res, trie.root, 0);
                  
            }
        }
        return new ArrayList<String>(res);
    }

    private void dfs(char[][] board, int i, int j, boolean[][] visited, String tmp, Set<String> res, TrieNode node, int level) {
        char c = board[i][j];
        //System.out.println(c + " at " + level + " index: " + (c-'a'));
        
        if (node.isWord[c-'a']) {
            res.add(tmp);
            //return;  //the word might be a part of a longer word
        }

        if (node.nodes[c-'a'] == null) return;

        int n = board.length, m = board[0].length;

        int[] dx = {1, 0, -1, 0};
        int[] dy = {0, -1, 0, 1};

        for (int k=0; k<4; k++) {
            int ni = i+dy[k];
            int nj = j+dx[k];
            //System.out.println(ni + "|" + nj);

            if (!isValid(board, visited, ni, nj, node.nodes[c-'a'])) continue; 
            visited[ni][nj] = true;
            char nc = board[ni][nj];
            dfs(board, ni, nj, visited, tmp + nc, res, node.nodes[c-'a'], level+1);
            visited[ni][nj] = false;
        }
    }

    private boolean isValid(char[][] board, boolean[][] visited, int i, int j, TrieNode node) {
        int n = visited.length, m = visited[0].length;

        if (i<0 || i>=n || j<0 || j>=m || visited[i][j]) return false;

        char c = board[i][j];
        //System.out.println("new c: " + c);

        if (node.nodes[c-'a'] == null) return false;

        return true;
    }
    
    //A faster way if no duplicates in the dictionary  beat 90% more submissions
    
    public List<String> wordSearchII2(char[][] board, List<String> words) {
        // write your code here
        
        Set<String> res = new HashSet<>();
        if (board == null || board.length == 0 || board[0].length == 0) return new ArrayList<>(res);

        int n = board.length, m = board[0].length;
        

        for (String word : words) {
            boolean[][] visited = new boolean[n][m];
            //int orgSize = res.size();
            //outerloop:
            for (int i=0; i<n; i++) {
                for (int j=0; j<m; j++) {
                    if (board[i][j] == word.charAt(0)) {
                        //boolean[][] visited = new boolean[n][m];
                        visited[i][j] = true;
                        dfs1(board, i, j, word, 0, visited, res, 0);
                        //if (res.size()>orgSize) break outerloop;  //This trick will work but seems not optimizing the performance
                        visited[i][j] = false;
                    }
                }
            }
        }

        return new ArrayList<>(res);

    }

    private void dfs1(char[][] board, int i, int j,  String str, int index, boolean[][] visited, Set<String> res, int level) {
        char c = board[i][j];
        //System.out.println(c + " at " + level + " index: " + index + " pos: " + i + "|" + j);

        if (str.length()-1 == index) { //index == str.length() is wrong!!! Find a simplified test case will reveal this !
            res.add(str);
            return;
        }

        int[] dir = {0, 1, 0, -1, 0};
        
        for (int k=0; k<4; k++) {
            int ni = i+dir[k];
            int nj = j+dir[k+1];
            //System.out.println(ni + "|" + nj);
            if (!isValid1(board, visited, ni, nj, str, index+1)) continue; 
            visited[ni][nj] = true;
            dfs1(board, ni, nj, str, index+1, visited, res, level+1);
            visited[ni][nj] = false;  //It has to be here!!! Otherwise test case like "adee" "eeda" won't work
        }

    }

    private boolean isValid1(char[][] board, boolean[][] visited, int i, int j, String str, int index) {
        int n = visited.length, m = visited[0].length;
        if (index>=str.length()) return false;

        if (i<0 || i>=n || j<0 || j>=m || visited[i][j]) return false;

        char c = board[i][j];
        //System.out.println("new c: " + c);

        //if (node.nodes[c-'a'] == null) return false;

        return c == str.charAt(index);
    }
}

class Trie {
    TrieNode root;

    Trie() {
        root = new TrieNode();
    }

    void addWord(String str) {

        if (str == null || str.length() == 0) return;
        char[] arr = str.toCharArray();

        TrieNode p = root;
        int level = 0;  //For debug
        String trace = ""; //For debug
        for (int i=0; i<arr.length; i++ ) {
            level++;
            if (p.nodes[arr[i]-'a'] == null) {
                p.nodes[arr[i]-'a'] = new TrieNode();
                trace += arr[i];
                //System.out.println(level);
            }

            //p = p.nodes[arr[i]-'a'];  //apparently wrong place
            
            if (i == arr.length-1) {
                p.isWord[arr[i]-'a'] = true;
                //System.out.println(arr[i]);
                //System.out.println(trace);
            } else {
                p = p.nodes[arr[i]-'a'];
            }
                
            
        }
    }
}

class TrieNode {
    TrieNode[] nodes;  
    boolean[] isWord;

    TrieNode() {
        nodes = new TrieNode[26];
        isWord = new boolean[26];
    }
}
