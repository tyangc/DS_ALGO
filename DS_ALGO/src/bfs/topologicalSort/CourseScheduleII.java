package bfs.topologicalSort;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Queue;

/*
 * 616 · Course Schedule II
Algorithms
Medium
Accepted Rate
32%

DescriptionSolutionNotesDiscussLeaderboard
Description
There are a total of n courses you have to take, labeled from 0 to n - 1.
Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]

Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.

There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.

Example
Example 1:

Input: n = 2, prerequisites = [[1,0]] 
Output: [0,1]
Example 2:

Input: n = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]] 
Output: [0,1,2,3] or [0,2,1,3]
Tags
Company
Facebook
Amazon
Zenefits
Related Problems
916
Palindrome Permutation
Easy
892
Alien Dictionary
Hard
815
Course Schedule IV
Hard
696
Course Schedule III
Hard
615
Course Schedule
Medium
605
Sequence Reconstruction
Medium
127
Topological Sorting
Medium
 */
public class CourseScheduleII {
	/*
     * @param numCourses: a total of n courses
     * @param prerequisites: a list of prerequisite pairs
     * @return: the course order
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // write your code here
        Map<Integer, Integer> map = new HashMap<>();  //Since the key is a integer from zero , so can also use int[]  array
        Map<Integer, List<Integer>> neighbors = new HashMap<>();  //Since the key is a integer from zero , so can also use ArrayList[] array

        Queue<Integer> que = new ArrayDeque<>();

        List<Integer> res = new ArrayList<>(); 

        for (int[] arr : prerequisites) {
            map.put(arr[0], map.getOrDefault(arr[0], 0)+1);
            List<Integer> list = neighbors.getOrDefault(arr[1], new ArrayList<Integer>());
            list.add(arr[0]);
            neighbors.put(arr[1], list);
        }

        for (int i=0; i<numCourses; i++) {
            if (!map.containsKey(i)) {
                res.add(i);
                que.offer(i);
            }
        }

        while(!que.isEmpty()) {
            int cur = que.poll();
            if (neighbors.containsKey(cur)) {
            for (int n  : neighbors.get(cur)) {
                
                    map.put(n, map.get(n)-1);
                    if (map.get(n) == 0) {
                        que.offer(n);
                        res.add(n);
                    }
                
            }
            }
        }
        
        int[] ret = new int[]{};
        if (res.size() == numCourses) ret = res.stream().mapToInt(i -> i).toArray();
        return ret;
    }
}
