package datastructure.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 1512 · Minimum Cost to Hire K Workers
Algorithms Hard Accepted Rate 64%

DescriptionSolutionNotesDiscussLeaderboard
Description
There are N workers. The i-th worker has a quality[i] and a minimum wage expectation wage[i].

Now we want to hire exactly K workers to form a paid group. When hiring a group of K workers, we must pay them according to the following rules:

Every worker in the paid group should be paid in the ratio of their quality compared to other workers in the paid group.
Every worker in the paid group must be paid at least their minimum wage expectation.
Return the least amount of money needed to form a paid group satisfying the above conditions.

1.1 <= K <= N <= 10000, where N = quality.length = wage.length
2.1 <= quality[i] <= 10000
3.1 <= wage[i] <= 10000
4.Answers within 10^-5 of the correct answer will be considered correct.

Example
Example 1:

Input: quality = [10,20,5], wage = [70,50,30], K = 2
Output: 105.00000
Explanation: We pay 70 to 0-th worker and 35 to 2-th worker.
Example 2:

Input: quality = [3,1,10,10,1], wage = [4,8,2,2,7], K = 3
Output: 30.66667
Explanation: We pay 4 to 0-th worker, 13.33333 to 2-th and 3-th workers seperately. 
Tags
Company Google
 */

public class MinimumCostHireKWorkers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param quality: an array
     * @param wage: an array
     * @param K: an integer
     * @return: the least amount of money needed to form a paid group
     */
    public double mincostToHireWorkers(int[] quality, int[] wage, int K) {
        // Write your code here

        List<Pair> lst = new ArrayList<>();

        for (int i=0; i<wage.length; i++) {
          lst.add(new Pair(quality[i], (double) wage[i]/quality[i]));

        }

        Collections.sort(lst, new Comparator<Pair>(){
          public int compare(Pair p1, Pair p2) {
            if (p1.rate == p2.rate) {
              return p1.q-p2.q;
            } else if (p1.rate>p2.rate) {
              return 1;
            } else {
              return -1;
            }
          }
        });

        Queue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        int sum = 0;
        double ans = Double.MAX_VALUE;
        for (int i=0; i<lst.size(); i++) {
          if (que.size()==K) {
            sum -= que.poll();
          }

          que.offer(lst.get(i).q);
          sum+=lst.get(i).q;
          if (que.size()==K) {
            ans = Math.min(ans, sum*lst.get(i).rate);
          }
        }

        return ans;
    }
}

class Pair {
	int q;
	double rate;
	
	Pair(int q, double rate) {
	  this.q = q;
	  this.rate = rate;
	  
	}
}

