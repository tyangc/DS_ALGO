package datastructure.multiple;

import java.util.HashMap;
import java.util.Map;

/*
 * 134 · LRU Cache
Algorithms
Hard
Accepted Rate
35%

DescriptionSolutionNotesDiscussLeaderboard
Description
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
Tags
Hash Table
Doubly Linked List
Linked List
Related Problems
538
Memcache
Medium
24
LFU Cache
Hard
 */
public class LRUCache {
	int size, capacity;
    ListNode dummy, tail;
    Map<Integer, ListNode> keyToPrev;
    /*
    * @param capacity: An integer
    */public LRUCache(int capacity) {
        // do intialization if necessary
        this.capacity = capacity;
        keyToPrev = new HashMap<>();
        dummy = new ListNode(0,0);
        tail = dummy;
    }

    /*
     * @param key: An integer
     * @return: An integer
     */
    public int get(int key) {
        // write your code here
        if (!keyToPrev.containsKey(key)) return -1;

        moveToTail(key);
        return tail.value;
    }

    /*
     * @param key: An integer
     * @param value: An integer
     * @return: nothing
     */
    public void set(int key, int value) {
        // write your code here
        if (get(key) != -1) {
            tail.value = value;
            return;
        }

        if (size < capacity) {
            ListNode node = new ListNode(key, value);
            /* dummy/tail already initialized 
            if (tail != null) {
                tail.next = node;
                keyToPrev.put(key, tail);
            } else {
                dummy = new ListNode(0, 0);
                dummy.next = node;
                //tail = node;
                keyToPrev.put(key, dummy);
            }*/
            tail.next = node;
            keyToPrev.put(key, tail);

            tail = node;
            size++;
            return;
        }

        ListNode first = dummy.next;
        //if (first.next!=null) keyToPrev.put(first.next.key, dummy);
        //first.key = key;
        //keyToPrev.put(key, dummy);
        
        /*
         * move then replace is not efficient
        keyToPrev.remove(first.key);
        moveToTail(first.key);
        
            ListNode prev = keyToPrev.get(tail.key);
            keyToPrev.put(key, prev);
        
        //ListNode prev = keyToPrev.get(tail.key);
        tail.key = key;
        tail.value = value;
        */

        keyToPrev.remove(first.key);
        first.key = key;
        first.value = value;
        
        keyToPrev.put(key, dummy);
        moveToTail(key);
    }

    private void moveToTail(int key) {
        ListNode prev = keyToPrev.get(key);

        ListNode curt = prev.next;

        if (curt == tail) {
            return;
        }

        prev.next = prev.next.next;
        tail.next = curt;
        curt.next = null;

        if (prev.next!=null) keyToPrev.put(prev.next.key, prev);  //Don't forget this check!!
        //keyToPrev.delete(key);
        keyToPrev.put(key, tail);

        tail = curt;
    }
    
    class ListNode {
        int key, value;
        ListNode next;

        ListNode(int key, int value) {
            this.key = key;
            this.value = value;
            next = null;
        }
    }

}
    
    