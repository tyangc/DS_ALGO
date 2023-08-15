package datastructure.multiple;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/*
 * 4 · Ugly Number II
Algorithms
Medium
Accepted Rate
31%

DescriptionSolutionNotesDiscussLeaderboard
Description
Ugly number is a number that only have prime factors 2, 3 and 5.

Design an algorithm to find the Nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

Note that 1 is typically treated as an ugly number.

1 \leq n \leq 10^{5}1≤n≤10 
5
 

Example
Example 1:

Input:

n = 9
Output:

10
Explanation:

[1,2,3,4,5,6,8,9,10,....],the ninth ugly number is 10.

Example 2:

Input:

n = 1
Output:

1
Challenge
O(n log n) or O(n) time.

Tags
Dynamic Programming/DP
Heap
Mathmatics
Related Problems
518
Super Ugly Number
Medium
517
Ugly Number
Easy
 */
public class UglyNumberII {
	/**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    public int nthUglyNumberON(int n) {
        // write your code here

        List<Integer> uglys= new ArrayList<>();
        uglys.add(1);
        int p2=0, p3=0, p5=0;

        for (int i=1; i<n; i++) {
          int lastNum = uglys.get(i-1);

          while(uglys.get(p2)*2<=lastNum) p2++;
          while(uglys.get(p3)*3<=lastNum) p3++;
          while(uglys.get(p5)*5<=lastNum) p5++;
          uglys.add(Math.min(uglys.get(p5)*5, Math.min(uglys.get(p2)*2, uglys.get(p3)*3)));
        }

        return uglys.get(n-1);
    }
    
    public int nthUglyNumberHeap(int n) {
        // write your code here

    	Queue<Long> que = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        que.offer(1L); 
        visited.add(1L);
        int[] factors = {2, 3, 5};

        for (int i=1; i<n; i++) {
          long cur = que.poll();

          for (int j : factors) {
            if (!visited.contains(cur*j)) {
              que.offer(cur*j);
              visited.add(cur*j);
            }
          }
          
        }

        return que.peek().intValue();
       
    }
}
