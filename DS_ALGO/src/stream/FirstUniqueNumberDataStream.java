package stream;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 685. First Unique Number in Data Stream

Given a continuous stream of data, write a function that returns the first unique number (including the last number) when the terminating number arrives. If the unique number is not found, return -1.

Example
Example1

Input: 
[1, 2, 2, 1, 3, 4, 4, 5, 6]
5
Output: 3
Example2

Input: 
[1, 2, 2, 1, 3, 4, 4, 5, 6]
7
Output: -1
Example3

Input: 
[1, 2, 2, 1, 3, 4]
3
Output: 3
 */
public class FirstUniqueNumberDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here
        DataStream ds = new DataStream();
        return ds.getFirstUniqueFromStream(nums, number);
    }
}

class ListNode {
    int val;
    ListNode next;
    
    public ListNode(int value) {
        val = value;
        next = null;
    }
}

class DataStream {
    Map<Integer, ListNode> mapToPrev;
    Set<Integer> duplicates;
    ListNode head, tail;
    
    public DataStream() {
        
        mapToPrev = new HashMap<>();
        duplicates = new HashSet<>();
        
        ListNode dummy = new ListNode(0);
        
        head = dummy;
        tail = head;
    }
    
    private void remove(int num) {
        ListNode prev = mapToPrev.get(num);
        prev.next = prev.next.next;
        mapToPrev.remove(num);
        if (prev.next==null) {
            tail = prev;
        } else {
            mapToPrev.put(prev.next.val, prev);
        }
        
        
    }
    
    private void add(int num) {
        if (duplicates.contains(num)) {
            return;
            
        }
        
        if (mapToPrev.containsKey(num)) {
            remove(num);
            duplicates.add(num);
        } else {
            ListNode tmp = new ListNode(num);
            tail.next = tmp;
            mapToPrev.put(num, tail);
            tail = tmp;
        }
    } 
    
    private int getFirstUnique() {
        if (head.next!=null) {
            return head.next.val;
        }
        
        return -1;
    }
    
    public int getFirstUniqueFromStream(int[] nums, int number) {
        
        for (int i : nums) {
            add(i);
            if (i==number) {
                return getFirstUnique();
            }
        }
        
        return -1;
    }
    
}    
