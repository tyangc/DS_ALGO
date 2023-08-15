package bfs.topologicalSort;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;

/*
 * 892 · Alien Dictionary
Algorithms
Hard
Accepted Rate
29%

DescriptionSolutionNotesDiscussLeaderboard
Description
There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you. You receive a list of non-empty words from the dictionary, where words are sorted lexicographically by the rules of this new language. Derive the order of letters in this language.

You may assume all letters are in lowercase.
The dictionary is invalid, if a is prefix of b and b is appear before a.
If the order is invalid, return an empty string.
There may be multiple valid order of letters, return the smallest in normal lexicographical order
Example
Example 1:

Input：["wrt","wrf","er","ett","rftt"]
Output："wertf"
Explanation：
from "wrt"and"wrf" ,we can get 't'<'f'
from "wrt"and"er" ,we can get 'w'<'e'
from "er"and"ett" ,we can get 'r'<'t'
from "ett"and"rftt" ,we can get 'e'<'r'
So return "wertf"
Example 2:

Input：["z","x"]
Output："zx"
Explanation：
from "z" and "x"，we can get 'z' < 'x'
So return "zx"
Tags
Company
Twitter
Pocket Gems
Airbnb
Facebook
Snapchat
Google
Related Problems
616
Course Schedule II
Medium
 * 
 */
public class AlienDictionary {
	/**
     * @param words: a list of words
     * @return: a string which is correct order
     */
    public String alienOrder(String[] words) {
        // Write your code here
        Map<Character, Set<Character>> graph = buildGraph(words);
        System.out.println(graph);
        if (graph == null) return "";

        return topologicalSort(graph);
        
        /* This is totally wrong idea
        Queue<Character> que = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        for (char c : graph.keySet()) {
            if (graph.get(c).size()==0) {
                que.offer(c);
                sb.append(c);
            }
        }

        while(!que.isEmpty()) {
            char tmp = que.poll();
            for (char c : graph.keySet()) {
                Set<Character> set = graph.get(c);
                if (set.contains(tmp)) {
                    set.remove(tmp);
                    if(set.size()==0) {
                        que.offer(c);
                        sb.append(c);
                    }
                }
                

            }
        }

        if (sb.length() < graph.size()) return "";
        return sb.reverse().toString();
        */

    }


    private Map<Character, Set<Character>> buildGraph(String[] words) {
        Map<Character, Set<Character>> graph = new TreeMap<>();  //Can use HashMap which will be much faster

        for (String word : words) {
            for (int i=0; i<word.length(); i++) {
                graph.putIfAbsent(word.charAt(i), new HashSet<>());
            }
        }
        //System.out.println(graph);

        for (int i=0; i<words.length-1; i++) {
            int j = 0;

            while(j<words[i].length() && j<words[i+1].length()) {
                if (words[i].charAt(j) != words[i+1].charAt(j)) {
                    Set<Character> set = graph.get(words[i].charAt(j));
                    set.add(words[i+1].charAt(j));
                    break;
                }
                j++;
            }

            if (j==words[i+1].length() && j<words[i].length()) {
                return null;
            }
        }

        return graph;
    }

    private String topologicalSort(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> inDegrees = getInDegrees(graph);
        Queue<Character> que = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();

        for (char c : inDegrees.keySet()) {
            if (inDegrees.get(c) == 0) {
                que.offer(c);
            }
        }

        while(!que.isEmpty()) {
            char cur = que.poll();
            sb.append(cur);
            for (char neighbor : graph.get(cur)) {
                inDegrees.put(neighbor, inDegrees.get(neighbor)-1);
                if (inDegrees.get(neighbor) == 0 ) {
                    que.offer(neighbor);
                }
            }
        }

        if (sb.length() != graph.size()) return "";
        return sb.toString();
    }

    private Map<Character, Integer> getInDegrees(Map<Character, Set<Character>> graph) {
        Map<Character, Integer> inDegrees = new HashMap<>();

        for (char c : graph.keySet()) {
            inDegrees.put(c, 0);
        }

        for (char u : graph.keySet()) {
            for (char v : graph.get(u)) {
                inDegrees.put(v, inDegrees.get(v)+1);
            }
        }

        return inDegrees;
    }
}
