package binarysearch;

/**
 Suppose a sorted array is rotated at some pivot unknown to you beforehand.

(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).

You are given a target value to search. If found in the array return its index, otherwise return -1.

You may assume no duplicate exists in the array.
 * @author tyang
 *
 */
public class SearchRotatedSortedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param A: an integer rotated sorted array
     * @param target: an integer to be searched
     * @return: an integer
     */
    public int search(int[] A, int target) {
        // write your code here
        if (A==null || A.length==0) return -1;
        
        int start=0, end=A.length-1;
        
        while(start+1<end) {
            int mid = start+(end-start)/2;
            
            if (A[start] <= A[mid]) {
                if (A[start]<=target && target<A[mid]) { //A[start]<=target is critical, especially "equals"
                    end = mid;
                } else  {
                    start = mid;
                } 
                
            } else {
                if (A[mid]<=target && target<=A[end]) { //target<=A[end] is also critical, especially "equals"
                    start = mid;
                } else {
                    end = mid;
                } 
            }
            
        }
        
        if (A[end]==target) return end;
        if (A[start]==target) return start;
        return -1;
        
        
    }
}
