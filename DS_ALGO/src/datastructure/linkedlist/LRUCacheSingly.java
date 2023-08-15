package datastructure.linkedlist;

import java.util.HashMap;
import java.util.Map;
/*
 134. LRU Cache

Design and implement a data structure for Least Recently Used (LRU) cache. It should support the following operations: get and set.

get(key) Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
set(key, value) Set or insert the value if the key is not already present. When the cache reached its capacity, it should invalidate the least recently used item before inserting a new item.
Finally, you need to return the data from each get.

Example
Example1

Input:
LRUCache(2)
set(2, 1)
set(1, 1)
get(2)
set(4, 1)
get(1)
get(2)
Output: [1,-1,1]
Explanation：
cache cap is 2，set(2,1)，set(1, 1)，get(2) and return 1，set(4,1) and delete (1,1)，because （1,1）is the least use，get(1) and return -1，get(2) and return 1.
Example 2:

Input：
LRUCache(1)
set(2, 1)
get(2)
set(3, 2)
get(2)
get(3)
Output：[1,-1,2]
Explanation：
cache cap is 1，set(2,1)，get(2) and return 1，set(3,2) and delete (2,1)，get(2) and return -1，get(3) and return 2.
 */

public class LRUCacheSingly {
	
	class ListNode {
	    int key;
	    int val;
	    ListNode next;
	    
	    public ListNode(int key, int val) {
	        this.key = key;
	        this.val = val;
	        this.next = null;
	    }
	} 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 	int capacity, size;
	    Map<Integer, ListNode> keyToPrev;
	    ListNode dummy, tail;
	    
	    /*
	    * @param capacity: An integer
	    */
	    public LRUCacheSingly(int capacity) {
	        // do intialization if necessary
	        this.capacity = capacity;
	        size = 0;
	        keyToPrev = new HashMap<>();
	        this.dummy = new ListNode(0,0);
	        tail = dummy;
	    }

	    /*
	     * @param key: An integer
	     * @return: An integer
	     */
	    public int get(int key) {
	        // write your code here
	        if (!keyToPrev.containsKey(key)) {
	            return -1;
	        }
	        
	        
	        moveToTail(key);
	        return tail.val;
	        
	    }


	    public void moveToTail(int key) {
	        ListNode prev = keyToPrev.get(key);
	        ListNode cur = prev.next;
	        
	        if (cur==tail) {
	            return;
	        }
	 
	        //keyToPrev.put(key, tail);
	 
	        prev.next = prev.next.next;
	        
	        if (prev.next!=null) {
	            keyToPrev.put(prev.next.key, prev);
	        }
	        
	        tail.next = cur;
	        cur.next = null;
	       
	        
	        keyToPrev.put(key, tail);
	        tail = cur;
	        
	    }

	    /*
	     * @param key: An integer
	     * @param value: An integer
	     * @return: nothing
	     */
	    public void set(int key, int value) {
	        // write your code here
	        if (get(key)!=-1) {
	            /*
	            ListNode prev = keyToPrev.get(key);
	            prev.next.val = value;
	            */
	            tail.val = value;
	            return;
	        }
	        
	        if (size<capacity) {
	            ListNode tmp = new ListNode(key, value);
	            tail.next = tmp;
	            keyToPrev.put(key, tail);
	            
	            tail = tmp;
	            size++;
	            return;
	        }
	    
	        ListNode head = dummy.next;
	        keyToPrev.remove(head.key);
	        head.key = key;
	        head.val = value;
	        keyToPrev.put(key, dummy);
	        moveToTail(key);
	    }
}
