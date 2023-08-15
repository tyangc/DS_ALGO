package datastructure.multiple;
/*
 685 · First Unique Number in Data Stream
Algorithms
Medium
Accepted Rate
43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a continuous stream of data, write a function that returns the first unique number (including the last number) when the terminating number arrives. If the terminating number is not found, return -1.

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
Tags
Hash Table
Data Stream
Related Problems
960
First Unique Number in Data Stream II
Medium
642
Moving Average from Data Stream
Easy
81
Find Median from Data Stream
Hard
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FirstUniqueNumberDataStream {
	/**
     * @param nums: a continuous stream of numbers
     * @param number: a number
     * @return: returns the first unique number
     */
    ListNode dummy, tail;
    Map<Integer, ListNode> keyToPrev;
    Set<Integer> dups;

    public int firstUniqueNumber(int[] nums, int number) {
        // Write your code here

        dummy = new ListNode(0);
        tail = dummy;
        keyToPrev = new HashMap<>();
        dups = new HashSet<>();

        for (int i : nums) {
          add(i);
          /*
          ListNode p = dummy;
          while (p.next !=null ) {
            System.out.print(p.next.val + "->");
            p = p.next;
          }
          System.out.println(" ");
          System.out.println(dups);
          */
          if ( i == number) {
            if (keyToPrev.containsKey(number) || dups.contains(number)) {
              return dummy.next.val;
            } 
          }
       
        }

        return -1;
    }

    private void add(int num) {
      if (dups.contains(num)) return;

      if (keyToPrev.containsKey(num)) {
        ListNode prev = keyToPrev.get(num);

        prev.next = prev.next.next;
        if (prev.next != null) {
          keyToPrev.put(prev.next.val, prev);
        } else {
          tail = prev;
        }
        
        keyToPrev.remove(num);
        
        dups.add(num);

      } else {
        ListNode node = new ListNode(num);
        tail.next = node;
        keyToPrev.put(num, tail);
        tail = node;
      }

    }

    class ListNode {
      int  val;
      ListNode next;

      public ListNode(int val) {
        this.val = val;
        next = null;
      }
    }
}
