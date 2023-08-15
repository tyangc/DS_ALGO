package twopointers;

import java.util.ArrayList;
import java.util.List;

/*
 * https://www.lintcode.com/problem/839/description
 */
public class MergeTwoSortedInterval {
	/**
     * @param list1: one of the given list
     * @param list2: another list
     * @return: the new sorted list of interval
     */
    public List<Interval> mergeTwoInterval(List<Interval> list1, List<Interval> list2) {
        // write your code here
        List<Interval> ret = new ArrayList<>();
        if (list1==null || list2 == null) return ret;
        Interval last=null, curt=null; 
        int i=0, j=0;

        while(i<list1.size() && j<list2.size()) {
            if (list1.get(i).start < list2.get(j).start) {
                curt = list1.get(i);
                i++;
            } else {
                curt = list2.get(j);
                j++;
            }

            last = merge(ret, last, curt);
            
        }

        while(i<list1.size()) {
            last = merge(ret, last, list1.get(i));
            i++;
        }

        while(j<list2.size()) {
            last = merge(ret, last, list2.get(j));
            j++;
        }
        if (last!=null) ret.add(last);
        return ret;
    }

    private Interval merge(List<Interval> results, Interval last, Interval curt) {
        if (last==null) {
            return curt;
        }
        if (curt.start > last.end) {
            results.add(last);
            return curt;
        } else {
            last.end = Math.max(last.end, curt.end);
            //results.add(last);
            return last;
        }
    }
    
    class Interval {
    	int start;
    	int end;
    	public Interval(int start, int end) {
    		this.start = start;
    		this.end = end;
    	}
    }
}
