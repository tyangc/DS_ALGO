package datastructure.multiple;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

/*
 * 960 · First Unique Number in Data Stream II
Algorithms
Medium
Accepted Rate
58%

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
Hash Table
Data Stream
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
public class FirstUniqueNumberDataStreamII {
	Map<Integer, Integer> map;
    Queue<Integer> que;
    


    public FirstUniqueNumberDataStreamII(){
        // do intialization if necessary
        map = new HashMap<>();
        que = new ArrayDeque<>();
    }
    /**
     * @param num: next number in stream
     * @return: nothing
     */
    public void add(int num) {
        // write your code here
        if (map.containsKey(num)) {
          map.put(num, map.get(num)+1);
        } else {
          map.put(num, 1);
        }
          
        que.offer(num);

    }
    

    

    /**
     * @return: the first unique number in stream
     */
    public int firstUnique() {
        // write your code here
        while(!que.isEmpty() && map.get(que.peek())>1) {
          que.poll();
        }

        if (que.size()>0) return que.peek();
        return -1;
    }
}
