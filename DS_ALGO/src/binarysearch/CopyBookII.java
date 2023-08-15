package binarysearch;

/**
 Copy Books II
描述
Given n books and each book has the same number of pages. There are k persons to copy these books and the i-th person needs times[i] minutes to copy a book.

Each person can copy any number of books and they start copying at the same time. What's the best strategy to assign books so that the job can be finished at the earliest time?

Return the shortest time.

Example 1:

Input: n = 4, times = [3, 2, 4]
Output: 4
Explanation: 
    First person spends 3 minutes to copy 1 book.
    Second person spends 4 minutes to copy 2 books.
    Third person spends 4 minutes to copy 1 book.
Example 2:

Input: n = 4, times = [3, 2, 4, 5]
Output: 4
Explanation: Use the same strategy as example 1 and the forth person does nothing.
 * @author tyang
 *
 */
public class CopyBookII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param n: An integer
     * @param times: an array of integers
     * @return: an integer
     */
    public int copyBooksII(int n, int[] times) {
        // write your code here
        if (times==null || times.length==0) return -1;
        if (times.length==1) return times[0]*n;
        int max = 0;
        for ( int i : times) {
            if (i>max) max = i;
        }
        int start = 0, end = max*n;
       
        while(start+1<end) {
            int mid = start + (end-start)/2;
        
            if (check(n, times, mid)) {
            	end = mid;
            } else {
                start=mid+1;
            }
    
        }

        if (check(n, times, start)) return start;
        else return end;
    }
    
    private boolean check(int n, int[] times, int time) {
        int sum=0;
        for (int i : times) {
            sum+=time/i;
        }
        return sum>=n;
    }
}
