package scanline;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/*
 Given a collection of intervals, merge all overlapping intervals.

Example 1:

Input: [(1,3)]
Output: [(1,3)]
Example 2:

Input:  [(1,3),(2,6),(8,10),(15,18)]
Output: [(1,6),(8,10),(15,18)]
Challenge:

O(n log n) time and O(1) extra space.
 */
public class MergeListOfInterval {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param intervals: interval list.
     * @return: A new interval list.
     */
    public List<Interval> merge(List<Interval> intervals) {
        // write your code here
        
        Comparator<Boundry> comp = new Comparator<Boundry>() {
            public int compare(Boundry a, Boundry b) {
                if (a.num==b.num) {
                    return a.type - b.type;
                }
                
                return a.num - b.num;
            }
        };
        
        List<Boundry> boundries = new LinkedList<>();
        
        for (Interval interval : intervals) {
            boundries.add(new Boundry(interval.start, -1));  //If 1 here then error ???why
            boundries.add(new Boundry(interval.end, 1));
           
        }
        
        Collections.sort(boundries, comp);
        
        int matched=0, left=0, right=0; 
        List<Interval> res = new ArrayList<>();
        
        for (Boundry cur : boundries) {
            if (matched==0) {
                left = cur.num;
            }
            
            matched+=cur.type;
            
            if (matched==0) {
                right = cur.num;
                res.add(new Interval(left, right));
            }
            
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

class Interval {
	 public int start, end;
	 public Interval(int start, int end) {
	 this.start = start;
	 this.end = end;
	}
}
