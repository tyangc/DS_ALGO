package datastructure.linkedlist;

/*
 960 · First Unique Number in Data Stream II

Medium
Accepted Rate
57%

DescriptionSolutionNotesDiscussLeaderboard
Description
We need to implement a data structure named DataStream. There are two methods required to be implemented:

void add(number) // add a new number
int firstUnique() // return first unique number
You can assume that there must be at least one unique number in the stream when calling the firstUnique.

Example
Example 1:

Input:
add(1)
add(2)
firstUnique()
add(1)
firstUnique()
Output:
[1,2]
Example 2:

Input:
add(1)
add(2)
add(3)
add(4)
add(5)
firstUnique()
add(1)
firstUnique()
add(2)
firstUnique()
add(3)
firstUnique()
add(4)
firstUnique()
add(5)
add(6)
firstUnique()
Output:
[1,2,3,4,5,6]
Tags
Related Problems
685
First Unique Number in Data Stream
Medium
646
First Position Unique Character
Easy
209
First Unique Character in a String
Easy
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstUniqueInDataStream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println(new FirstUniqueInDataStream().firstUniqueNumber(new int[] {1,2,2,1,3,4,4,5,6}, 5));
		
	}

	/*
	public int firstUniqueNumber(int[] nums, int number) {
        DataStream ds = new DataStream();
        for (int i = 0; i < nums.length; i++) {
            ds.add(nums[i]);
            if (nums[i] == number) {
                return ds.getFirstUnique();
            }
        }
        
        return -1;
    } */
	
	 public int firstUniqueNumber(int[] nums, int number) {
	        // Write your code here
	        
	        DataStream ds = new DataStream();
	        
	        for (int i : nums) {  //!!! it won't guarantee the order !!! This won't work!!!
	            ds.add(i);
	            if (i==number) {
	                ds.getFirstUnique();
	                System.out.println("get unique");
	            } 
	        }
	        
	        return -1;
	 }
	
}


class DataStream {
    /**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
     
    private ListNode head, tail;
    private Map<Integer, ListNode> numToPrev; 
    private Set<Integer> duplicates;
    
    public DataStream() {
        numToPrev = new HashMap<>();
        
        duplicates = new HashSet<>();
        
        //ListNode dummy = new ListNode(0);
        head = new ListNode(0); //dummy;
        tail = head;
        
    }
    
    public void remove(int number) {
    	/*
        ListNode prev = numToPrev.get(num);
        prev.next = prev.next.next;
        numToPrev.remove(num);
        
        if (prev.next!=null) {
            
            numToPrev.put(prev.next.val, prev);
            
        } else {
            
            //tail = numToPrev.get(tail.val);
            //tail.next = null;
            tail = prev;
        }
        */
    	
    	ListNode prev = numToPrev.get(number);
        prev.next = prev.next.next;
        
        
        // change tail and prev of next
        if (prev.next != null) {
            numToPrev.put(prev.next.val, prev);
        } else {
            tail = prev;
        }
        
        numToPrev.remove(number);
        System.out.println("remove: " + number);
    }
    
    public void add(int number) {
    	
    /*
        if (duplicates.contains(num)) {
            return;
        }
        
        System.out.println(num);
        
        if (numToPrev.containsKey(num)) {
            remove(num);
            duplicates.add(num);
        } else {
            ListNode tmp = new ListNode(num);
            //ListNode t = numToPrev.get(tail.val);
            
            numToPrev.put(num, tail);
            tail.next = tmp;
            tail = tmp;
        }
        */
    	if (duplicates.contains(number)) {
            return;
        }
        
        if (numToPrev.containsKey(number)) {
            remove(number);
            duplicates.add(number);
        } else {
            ListNode node = new ListNode(number);
            numToPrev.put(number, tail);
            tail.next = node;
            tail = node;
        }
    }
    
    public int getFirstUnique() {
        
    	if (head.next != null) {
            return head.next.val;
        }
        return -1;
    	
        /*
        if (head.next!=null) {
            return head.next.val;
        } else {
            return -1;
        }
        */
        
    }
}

class ListNode {
    int val;
    ListNode next;
    
    public ListNode(int val) {
        this.val = val;
        next = null;
    }
}
