package binarysearch;
/*
 460 · Find K Closest Elements
Algorithms
Medium
Accepted Rate
35%

Description
Given target, a non-negative integer k and an integer array A sorted in ascending order, find the k closest numbers to target in A, sorted in ascending order by the difference between the number and target. Otherwise, sorted in ascending order by number if the difference is same.

The value k is a non-negative integer and will always be smaller than the length of the sorted array.
Length of the given array is positive and will not exceed 10^410 
4
 
Absolute value of elements in the array will not exceed 10^410 
4
 
Example
Example 1:

Input: A = [1, 2, 3], target = 2, k = 3
Output: [2, 1, 3]
Example 2:

Input: A = [1, 4, 6, 8], target = 3, k = 3
Output: [4, 1, 6]
Challenge
O(logn + k) time

Tags
Company
Google
 */
import java.util.Arrays;

public class FindKClosestNumbers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(kClosestNumbers(new int[] {1,2,3}, 2, 3)));
	}
	
	
	 /**
     * @param A: an integer array
     * @param target: An integer
     * @param k: An integer
     * @return: an integer array
     */
    public static int[] kClosestNumbers(int[] A, int target, int k) {
        // write your code here
        
        if (A==null || k>A.length) return A;
        
        if (k==0) return new int[]{};
        
        int start=0, end=A.length-1;
        
        int m = 0;
        
        while(start+1<end) {
            int mid=(start+end)/2;
            
            if (A[m]==target) {
                m = mid;
                break;
            } else if (A[mid]>target) {
                end = mid;
            } else {
                start = mid;
            }
            
        }
        
        if (A[m]!=target) {
            m = target-A[start]<=A[end] - target? start:end;
        }
        
        System.out.println("m: " + m);
        
        int[] res = new int[k];
        res[0] = A[m];
        int n = 1, i=m-1, j=m+1;
        while(n<k) {
            res[n] = getCloserOne(A, target, i, j);
            if (i>=0&&res[n]==A[i])i--;
            if (j<A.length&&res[n]==A[j])j++;
            n++;
        }
        return res;
    }
    
    private static int getCloserOne(int[] A, int target, int i, int j) {
        if (i<0) return A[j];
        
        if (j>=A.length) return A[i];
        
        if (target-A[i]==A[j]-target) return A[i];
        
        return target-A[i]<A[j]-target ? A[i]:A[j];
        
    }
    
    //Better way:
    public int[] kClosestNumbers1(int[] A, int target, int k) {
        // write your code here
        
        if (A==null || k>A.length) return A;
        
        if (k==0) return new int[]{};
        
        int low = getLowerBorder(A, target);
        System.out.println(low);
        int left = low, right = low+1, n = 0;

        int[] res = new int[k];

        while(n<k) {
          if ( isLeftCloser(A, left, right, target) ) {
            res[n++] = A[left];
            left--;
          } else {
            res[n++] = A[right];
            right++;
          }
        }

        return res;
        
    }
    
    private int getLowerBorder(int[] A, int target) {
      int i=0, j=A.length-1;

      while(i+1<j) {
        int mid = i + (j-i)/2;

        if (A[mid] < target) {
          i = mid;
        } else {
          j = mid;
        }
      }
      if (A[j] < target) return j;

      if (A[i] < target) return i;
      return -1;
    }

    private boolean isLeftCloser(int[] A, int left, int right, int target) {
      if (left<0) {
        return false;
      }
      if (right >= A.length) {
        return true;
      }

      if (target-A[left] != A[right]-target) {
        return target-A[left] < A[right]-target ? true : false;
      }

      return true;
    }
}
