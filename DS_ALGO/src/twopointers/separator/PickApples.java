package twopointers.separator;
/*
 Alice and Bob work in a beautiful orchard. There are N apple trees in the orchard. The apple trees are arranged in a row and they are numbered from 1 to N.
Alice is planning to collect all the apples from K consecutive trees and Bob is planning to collect all the apples from L consecutive trees.
They want to choose to disjoint segements (one consisting of K trees of Alice and the other consisting of L trees for Bob) so as not to disturb each other. 
you should return the maximum number of apples that they can collect.

N is an integer within the range: [2, 600]
K and L are integers within the range: [1, N - 1]
each element of array A is an integer within the range: [1, 500]
说明
样例
Example 1:
input:
A = [6, 1, 4, 6, 3, 2, 7, 4]
K = 3
L = 2
Output: 24
Explanation: beacuse Alice can choose tree 3 to 5 and collect 4 + 6 + 3 = 13 apples, and Bob can choose trees 7 to 8 and collect 7 + 4 = 11 apples.Thus, they will collect 13 + 11 = 24.
Example 2:
Input:
A = [10, 19, 15]
K = 2
L = 2
Output: -1
Explanation: beacause it is not possible for Alice and Bob to choose two disjoint intervals.
 */
public class PickApples {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param A: a list of integer
     * @param K: a integer
     * @param L: a integer
     * @return: return the maximum number of apples that they can collect.
     */
    public int PickApples(int[] A, int K, int L) {
        // write your code here
        if (A==null || A.length<1 || K+L>A.length) return -1;
        int n=A.length, max=-1;
        
        for (int i=0; i<n; i++) {
            int leftMaxK = getMax(A, K, 0, i);
            int rightMaxL = getMax(A, L, i, n);
            int leftMaxL = getMax(A, L, 0, i);
            int rightMaxK = getMax(A, K, i, n);
            
            if (leftMaxK!=-1 && rightMaxL!=-1) {
                max = Math.max(max, leftMaxK + rightMaxL);
            }
            
            if (leftMaxL!=-1 && rightMaxK!=-1) {
                max = Math.max(max, leftMaxL + rightMaxK);
            }
        }
        
        return max;
    }
    
    private int getMax(int[] A, int k, int start, int end) {  //start point closed end point open [start, end)
        if (k>end-start) return -1;  //Not end-start+1 since end is open
        
        int apples=0, maxApples=0;
        
        for (int i=start; i<start+k; i++) {
            apples+=A[i];
        }
        maxApples = apples;
        int left=start, right = start+k;
        while(right<end) {
            apples-=A[left];
            apples+=A[right];
            maxApples=Math.max(maxApples, apples);
            left++;
            right++;
        }
        return maxApples;
    }
}
