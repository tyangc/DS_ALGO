package datastructure.heap;

import java.util.PriorityQueue;
import java.util.Queue;

/*
 606 · Kth Largest Element II
Algorithms Medium Accepted Rate 60%

DescriptionSolutionNotesDiscussLeaderboard
Description
Find K-th largest element in an array, and N is much larger than k. Note that it is the kth largest element in the sorted order, not the kth distinct element.

You can swap elements in the array

Example
Example 1:

Input:[9,3,2,4,8],3
Output:4

Example 2:

Input:[1,2,3,4,5,6,8,9,10,7],10
Output:1

Tags
Related Problems
1281
Top K Frequent Elements
Medium
5
Kth Largest Element
Medium
 */
public class KthLargestElmentII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: an integer unsorted array
     * @param k: an integer from 1 to n
     * @return: the kth largest element
     */
    public int kthLargestElement2(int[] nums, int k) {
        // write your code here
        Queue<Integer> heap = new PriorityQueue<>(k);

        for (int i : nums) {

          heap.offer(i);
          while (heap.size()>k) {
            heap.poll();
          }
         
          //heap.offer(i); Here is wrong!!!
        }

        return heap.peek();

    }
}
