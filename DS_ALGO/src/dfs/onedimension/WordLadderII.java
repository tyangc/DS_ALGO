package dfs.onedimension;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/*
 * 121 · Word Ladder II
Algorithms
Hard
Accepted Rate
28%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given two words (start and end), and a dictionary, find all shortest transformation sequence(s) from start to end.
Transformation rule such that:

Only one letter can be changed at a time
Each intermediate word must exist in the dictionary
All words have the same length.
All words contain only lowercase alphabetic characters.
At least one solution exists.
The number of words is less than or equal to 10000
The word length is less than or equal to 10
Example
Example 1:

Input:

start = "a"
end = "c"
dict =["a","b","c"]
Output:

[["a","c"]]
Explanation:

"a"->"c"

Example 2:

Input:

start ="hit"
end = "cog"
dict =["hot","dot","dog","lot","log"]
Output:

[["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
Explanation:

1."hit"->"hot"->"dot"->"dog"->"cog"
2."hit"->"hot"->"lot"->"log"->"cog"

Tags
Depth First Search/DFS
Breadth First Search/BFS
Related Problems
790
Parser
Medium
120
Word Ladder
Hard
 */
public class WordLadderII {
	public List<List<String>> findLadders(String start, String end, Set<String> dict) {
        // write your code here
        //Edge consideration
        List<List<String>> res = new ArrayList<>();

        Map<String, Integer> distances = new HashMap<>();
        dict.add(start);
        dict.add(end);

        bfs(end, start, dict, distances);
        //System.out.println(distances);
        List<String> lst = new ArrayList<>();
        lst.add(start);
        dfs(distances.get(start), start,  end, distances, dict, lst, res);

        return res;

    }

    private void dfs(
                     int limit,
                     String cur,
                     String end, 
                     Map<String, Integer> distances, 
                     Set<String> dict, 
                     List<String> tmp, 
                     List<List<String>> res) {
        
        if (cur.equals(end)) {
            res.add(new ArrayList<>(tmp));
            return;
        }

        //if (distances.containsKey(cur)) return;
        List<String> nextLst = getNextWord(cur, dict, distances);
        //System.out.println(nextLst);
        for (String word : nextLst) {
            if (distances.get(word) > limit-1) continue;
            tmp.add(word);
            dfs( limit-1, word, end, distances, dict, tmp, res);
            tmp.remove(word);
        }

    }

    private void bfs(String end, String start, Set<String> dict, Map<String, Integer> map ) {
        Queue<String> que = new ArrayDeque<>();
        que.offer(end);
        int dist = 0;

        while(!que.isEmpty()) {
            
            /*
            if (cur.equals(start)) {
                map.put(start, level+1);
            }
            */
            
            int size = que.size();
            
            for (int i=0; i<size; i++) {
                String cur = que.poll();
                if (map.containsKey(cur)) continue;  //CHECK VISITED: If missing this kind of check will time out
                map.put(cur, dist);
                List<String> lst = getNextWord(cur, dict, map);
                //System.out.println(lst);
                for (String word : lst) {
                    que.offer(word);
                }

            }
            dist++;
            
        }
    }

    //Can use cache to optimize the ferformance:
    /*
    private List<String> getNextWord(String str, Set<String> dict, Map<String, List<String>> cache) {
        List<String> res = new ArrayList<>();

        for (int i=0; i<str.length(); i++) {
            for ( char c='a'; c<='z'; c++ ) {
                char cur  = str.charAt(i);
                if (cur == c) continue;

                String tmp = replaceChar(str, i, c);
                if (dict.contains(tmp)) {
                    res.add(tmp);
                }
            }
        }
        cache.put(str, res);
        return res;
    }
     */
    private List<String> getNextWord(String str, Set<String> dict, Map<String, Integer> map) {
        List<String> res = new ArrayList<>();

        for (int i=0; i<str.length(); i++) {
            for ( char c='a'; c<='z'; c++ ) {
                char cur  = str.charAt(i);
                if (cur == c) continue;

                String tmp = replaceChar(str, i, c);
                if (dict.contains(tmp)) {
                    res.add(tmp);
                }
            }
        }
        return res;
    }

    private String replaceChar(String str, int index, char c) {
        char[] arr = str.toCharArray();
        arr[index] = c;
        return new String(arr);
    }

}
