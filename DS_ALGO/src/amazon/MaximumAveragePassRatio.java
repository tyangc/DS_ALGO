package amazon;
/*
 * https://leetcode.com/problems/maximum-average-pass-ratio/
 * 1792. Maximum Average Pass Ratio
Medium

451

56

Add to List

Share
There is a school that has classes of students and each class will be having a final exam. You are given a 2D integer array classes, where classes[i] = [passi, totali]. You know beforehand that in the ith class, there are totali total students, but only passi number of students will pass the exam.

You are also given an integer extraStudents. There are another extraStudents brilliant students that are guaranteed to pass the exam of any class they are assigned to. You want to assign each of the extraStudents students to a class in a way that maximizes the average pass ratio across all the classes.

The pass ratio of a class is equal to the number of students of the class that will pass the exam divided by the total number of students of the class. The average pass ratio is the sum of pass ratios of all the classes divided by the number of the classes.

Return the maximum possible average pass ratio after assigning the extraStudents students. Answers within 10-5 of the actual answer will be accepted.

 

Example 1:

Input: classes = [[1,2],[3,5],[2,2]], extraStudents = 2
Output: 0.78333
Explanation: You can assign the two extra students to the first class. The average pass ratio will be equal to (3/4 + 3/5 + 2/2) / 3 = 0.78333.
Example 2:

Input: classes = [[2,4],[3,9],[4,5],[2,10]], extraStudents = 4
Output: 0.53485
 

Constraints:

1 <= classes.length <= 105
classes[i].length == 2
1 <= passi <= totali <= 105
1 <= extraStudents <= 105
Accepted
13,817
Submissions
27,553
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
2
Pay attention to how much the pass ratio changes when you add a student to the class. If you keep adding students, what happens to the change in pass ratio? The more students you add to a class, the smaller the change in pass ratio becomes.
Since the change in the pass ratio is always decreasing with the more students you add, then the very first student you add to each class is the one that makes the biggest change in the pass ratio.
Because each class's pass ratio is weighted equally, it's always optimal to put the student in the class that makes the biggest change among all the other classes.
Keep a max heap of the current class sizes and order them by the change in pass ratio. For each extra student, take the top of the heap, update the class size, and put it back in the heap.
 */
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MaximumAveragePassRatio {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
	 * 
	 * @param classes
	 * @param extraStudents
	 * @return
	 * First of all to understand properly the requirement of the question, analysis the most appropriate one out of all possibilities, have to be comprehensive
	 * * Way of thinking: 1. incremental thinking 2. trending 3. layers of addition - measurement of impact 4. contribution weight analysis/comparison - contribution ratio among contributing sources
	 */
	public double maxAverageRatio(int[][] classes, int extraStudents) {
        
		Queue<int[]> que = new PriorityQueue<>(new Comparator<int[]>() {
	        public int compare(int[] a, int[] b) {
	            double comp = ((double)(b[0]+1)/(double)(b[1]+1) - (double)b[0]/(double)b[1]) - ((double)(a[0]+1)/(double)(a[1]+1) - (double)a[0]/(double)a[1]);
	            if (comp==0.0) return 0;   //can not return comp directly , it would lose precision --> wrong answer
	            if (comp>0.0) return 1;
	            return -1;
	        }
		});
    
        
        for (int i=0; i<classes.length; i++) {
            que.offer(classes[i]);
        }
        
        for (int i=0; i<extraStudents; i++) {
            int[] cur = que.poll();
            que.offer(new int[]{cur[0]+1, cur[1]+1});
        }
        
        double total = 0.0;
        
        while(!que.isEmpty()) {
            int[] cur = que.poll();
            total +=(double)cur[0]/(double)cur[1];
        }
        
        return total/(double)classes.length;
        //double[] ratio = new double[classes.length];
        //double totalRatio = 0.0;
        
        /*
        for (int i=0; i<classes.length; i++) {
            totalRatio +=  (double)classes[i][0]/(double)classes[i][1];
        }
        */
        //System.out.println(totalRatio);
        /*
        Queue<Double> que = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int i=0; i<classes.length; i++) {
            double total = 0.0;
            for (int j=0; j<classes.length; j++) {
                if (i==j) {
                    total += (double)(classes[j][0]+extraStudents)/(double)(classes[j][1]+extraStudents);
                } else {
                    total += (double)classes[j][0]/(double)classes[j][1];
                }
            }
            que.offer(total/classes.length);
            
        }
        */
        
        /*
        for (Double avr : getExtra(classes, totalRatio, extraStudents)) {
            System.out.println(avr);
            que.offer(avr);
        }
        */
        //return que.peek();
    }
    
    /*
    private List<Double> getExtra(int[][] classes, double totalRatio, int extraStudents) {
        List<Double> ret = new ArrayList<>();
 
        double total = 0.0;
        for (int i=0; i<classes.length; i++) {
            
            total = totalRatio - (double)classes[i][0]/(double)classes[i][1];
            total +=  (double)(classes[i][0]+extraStudents)/(double)(classes[i][1]+extraStudents);
            ret.add(total/classes.length);
        }
        return ret;
    }
    */
}
