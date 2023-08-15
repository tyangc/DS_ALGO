package scanline;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import scanline.MergeListOfInterval.Boundry;
//import scanline.MergeListOfInterval.Interval;

/*
 Meeting Rooms II

Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.

Example1

Input: intervals = [(0,30),(5,10),(15,20)]
Output: 2
Explanation:
We need two meeting rooms
room1: (0,30)
room2: (5,10),(15,20)
Example2

Input: intervals = [(2,7)]
Output: 1
Explanation: 
Only need one meeting room
 */
public class MinMeetingRooms {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param intervals: an array of meeting time intervals
     * @return: the minimum number of conference rooms required
     */
    public int minMeetingRooms(List<Interval> intervals) {
        // Write your code here
    	
        
        PriorityQueue<Boundry> que = new PriorityQueue<Boundry>(new Comparator<Boundry>(){
            public int compare(Boundry a, Boundry b) {
                if (a.num==b.num) {
                    return a.type-b.type;
                }
                
                return a.num - b.num;
            }
        });
        
        for (Interval interval : intervals) {
            que.add(new Boundry(interval.start, 1)); //if -1 here then error
            que.add(new Boundry(interval.end, -1));
        }
        
        int meetings = 0, res=0;
        
        while(!que.isEmpty()){
            Boundry boundry = que.poll();
            meetings +=boundry.type;
            res = Math.max(res, meetings);
        } 
        return res;
    }
    
    public class Boundry {
        int num;
        int type;
        
        public Boundry(int n, int t) {
            num = n;
            type = t;
        }
    }
    
}

/*
class Interval {
	 public int start, end;
	 public Interval(int start, int end) {
	 this.start = start;
	 this.end = end;
	}
}
*/