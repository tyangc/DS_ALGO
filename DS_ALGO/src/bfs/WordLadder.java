package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 120. Word Ladder
中文English
Given two words (start and end), and a dictionary, find the shortest transformation sequence from start to end, output the length of the sequence.
Transformation rule such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary. (Start and end words do not need to appear in the dictionary )
Example
Example 1:

Input：start = "a"，end = "c"，dict =["a","b","c"]
Output：2
Explanation：
"a"->"c"
Example 2:

Input：start ="hit"，end = "cog"，dict =["hot","dot","dog","lot","log"]
Output：5
Explanation：
"hit"->"hot"->"dot"->"dog"->"cog"
Notice
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
 */

public class WordLadder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param start: a string
     * @param end: a string
     * @param dict: a set of string
     * @return: An integer
     */
    public int ladderLength(String start, String end, Set<String> dict) {
        // write your code here
        dict.add(end);
        
        Queue<String> que = new LinkedList<>();
        Map<String, Integer> map = new HashMap<>();
        
        que.offer(start);
        map.put(start, 0);
        
        while(!que.isEmpty()) {
            String cur = que.poll();
            int step = map.get(cur);
            if (cur.equals(end)) return map.get(end)+1;  //The judgement best be here
            
            for (String word : getNextWord(cur, dict)) {
                
                if(!map.containsKey(word)) {  //If missing this judgement then return unnecessary more steps
                    que.offer(word);
                    map.put(word, step+1);
                    //System.out.println(map);
                }
            }
        }
        
        
        return 0;
        
    }
    
    private List<String> getNextWord(String word,  Set<String> dict) {
        
        List<String> res = new ArrayList<>();
        
        for (char c='a'; c<='z'; c++) { //Doesn't matter which loop first
            for (int i=0; i<word.length(); i++) {
                if (word.charAt(i)!=c) {
                    String newStr = replace(word, i, c);
                    if (dict.contains(newStr)) {
                        res.add(newStr);
                    }
                }
            }
        }
        
        return res;
    }
    
    private String replace(String word, int i, char c) {
        char[] array = word.toCharArray();
        array[i] = c;
        return new String(array);
    }
    
    //Bi-direction BFS;
    
public int ladderLengthBiBFS(String start, String end, Set<String> dict) {
        
        dict.add(start);
        dict.add(end);
        Queue<String> queF = new ArrayDeque<>();
        Queue<String> queB = new ArrayDeque<>();

        Set<String> visitedF = new HashSet<>();
        Set<String> visitedB = new HashSet<>();

        queF.offer(start);
        visitedF.add(start);
        queB.offer(end);
        visitedB.add(end);
        int len=1;


        while (!queF.isEmpty() && !queB.isEmpty()) {
          len++;
          if (extendQueueMove(queF, visitedF, visitedB, dict)) {
            return len;

          }

          len++;
          if (extendQueueMove(queB, visitedB, visitedF, dict)) {
            return len;
            
          }
        }

        return -1;

    }

    private boolean extendQueueMove(Queue<String> que, Set<String> visited, Set<String> opposite, Set<String> dict) {
      int size = que.size();
      for (int i=0; i<size; i++) {  //Have to go breath of the same layer first!!!
      String cur = que.poll(); 
        for (String next : getNextStringList(cur, dict)) {
          if (opposite.contains(next)) return true;
          if (!visited.contains(next) ) {
            que.offer(next);
            visited.add(next);
          }
        
        }
      }
      return false;
    }

    private List<String> getNextStringList(String str, Set<String> dict) {
      List<String> res = new ArrayList<>();
      char[] arr = str.toCharArray();
      
      for (char c='a'; c<='z'; c++) {
        for (int i=0; i<str.length(); i++) {
          if (c!=str.charAt(i)) {
            String tmp = swap(arr, i, c); 
            if (dict.contains(tmp)) {
              res.add(tmp);
            } 
            
          }
        }
      }
      return res;
    }


    private String swap(char[] arr, int pos, char c) {
     // System.out.println(pos);
     //System.out.println(Arrays.toString(arr))
      char[] copy = Arrays.copyOf(arr, arr.length);
      copy[pos] = c;

      return new String(copy);
    } 
}
