package datastructure.heap;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
 4. Ugly Number II

Ugly number is a number that only have prime factors 2, 3 and 5.

Design an algorithm to find the nth ugly number. The first 10 ugly numbers are 1, 2, 3, 4, 5, 6, 8, 9, 10, 12...

Example
Example 1:

Input: 9
Output: 10
Example 2:

Input: 1
Output: 1
Challenge
O(n log n) or O(n) time.

Notice
Note that 1 is typically treated as an ugly number.
 */
public class UglyNumberII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /**
     * @param n: An integer
     * @return: return a  integer as description.
     */
    public int nthUglyNumber(int n) {
        // write your code here
        if (n<=1) return n;
        
        PriorityQueue<Long> heap = new PriorityQueue<>();
        Set<Long> visited = new HashSet<>();
        
        int[] factors = {2,3,5};
        
        Long val = new Long(1);
        heap.offer(val);
        
        for (int i=0; i<n; i++) {
            
            val = heap.poll();
           
            
            for (int factor : factors) {
                
                if (!visited.contains(val*factor)) {
                    visited.add(val*factor);
                    heap.offer(val*factor);
                }
            }
            //val = heap.poll();  //Here is wrong
            
        }
        
        return val.intValue();
    }
}
