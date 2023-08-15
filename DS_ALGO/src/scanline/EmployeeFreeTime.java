package scanline;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//import scanline.MergeListOfInterval.Boundry;

public class EmployeeFreeTime {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /**
     * @param schedule: a list schedule of employees
     * @return: Return a list of finite intervals 
     */
    public List<Interval> employeeFreeTime(int[][] schedule) {
        // Write your code here
    	/*
    	Comparator<Boundry> comp = new Comparator<Boundry>() {
            public int compare(Boundry a, Boundry b) {
                if (a.num==b.num) {
                    return a.type - b.type;
                }
                
                return a.num - b.num;
            }
        };
    	*/
        PriorityQueue<Boundry> que = new PriorityQueue<>(new Comparator<Boundry>(){
            public int compare(Boundry a, Boundry b){
                if (a.num==b.num) {
                    return a.type - b.type;
                }
                return a.num - b.num;
            }
        });
            
        for (int[] sch : schedule) {
            for (int i=0; i<sch.length; i=i+2) {
                que.add(new Boundry(sch[i], -1));
                que.add(new Boundry(sch[i+1], 1));
            }
        }
        
        List<Interval> res = new ArrayList<>();
        int count=0; //, left=0, right=0;
        
        while(que.size()>1) {
            Boundry left = que.poll();
            Boundry right = que.peek();
            
            count+=left.type;
            
            if (left.type==-1 && right.type==1 && count==0) {
                res.add(new Interval(left.num, right.num));
            }
            
            /*  also works
            Boundry cur = que.poll();
            count+=cur.type;
            if (count==0) {
                //if (!que.isEmpty()) {
                    left = cur.num;
                    right = que.peek().num;
                    res.add(new Interval(left, right));
                //}
            }
            */
        }
        
        return res;
    }
}

class Boundry {
    int num, type;
    public  Boundry(int n, int t) {
        num = n;
        type = t;
    }
}
