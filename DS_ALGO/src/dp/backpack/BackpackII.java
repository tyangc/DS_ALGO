package dp.backpack;

import java.util.Arrays;

/*
 * 125 · Backpack II
Algorithms
Medium
Accepted Rate
54%

DescriptionSolutionNotesDiscussLeaderboard
Description
There are n items and a backpack with size m. Given array A representing the size of each item and array V representing the value of each item.

What's the maximum value can you put into the backpack?

A[i], V[i], n, m are all integers.
You can not split an item.
The sum size of the items you want to put into backpack can not exceed m.
Each item can only be picked up once
m <= 1000m<=1000\
len(A),len(V)<=100len(A),len(V)<=100

Example
Example 1:

Input:

m = 10
A = [2, 3, 5, 7]
V = [1, 5, 2, 4]
Output:

9
Explanation:

Put A[1] and A[3] into backpack, getting the maximum value V[1] + V[3] = 9

Example 2:

Input:

m = 10
A = [2, 3, 8]
V = [2, 5, 8]
Output:

10
Explanation:

Put A[0] and A[2] into backpack, getting the maximum value V[0] + V[2] = 10

Challenge
O(nm) memory is acceptable, can you do it in O(m) memory?
 */
public class BackpackII {

	public int backPackII(int m, int[] A, int[] V) {
        // write your code here

        int n = A.length;

        int[][] dp = new int[n+1][m+1];


        for (int i=1; i<=n; i++) {
            for (int j=0; j<=m; j++) {
                if (j>=A[i-1]) {
                    dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-A[i-1]] + V[i-1]);
                } else {
                    dp[i][j] = dp[i-1][j];
                }
            }
        }
        for (int i=0; i<=n; i++) {
            System.out.println(Arrays.toString(dp[i]));
        } 
        return dp[n][m];
    }
	
	//Using combination DFS, Time Limit Exceeded at 36% 
	
	/*
	 * input:  1000
[3,68,24,80,76,9,24,2,46,75,56,41,95,46,23,34,64,76,6,48,25,73,87,67,58,7,93,66,55,75,38,27,53,6,100,36,26,17,53,88,21,9,34,90,32,47,4,6,57,50,30,25,41,24,12,74,92,17,32,96,35,76,52,93,64,55,1,70,26,35,2,97,82,22,41,37,63,28,90,13,18,55,28,58,59,74,71,32,71,66,4,5,48,52,70,62,28,36,39,48]
[38,16,29,47,22,25,17,49,15,15,75,11,56,99,51,92,59,37,13,98,61,50,32,17,44,79,41,53,45,29,62,64,2,23,31,45,57,68,57,26,51,26,86,83,94,20,98,24,91,89,1,63,21,46,74,56,64,72,58,8,74,24,27,35,94,49,65,21,16,25,1,45,63,4,37,25,39,68,49,11,31,95,5,79,20,21,52,50,8,19,67,21,24,89,28,88,38,96,64,84]
	 */
	public int backPackIIDfsCombination(int m, int[] A, int[] V) {
        // write your code here
        int[] res = new int[1];

        dfs(m, A, V, 0, 0, 0, res);

        return res[0];
        
    }

    private void dfs(int m, int[] A, int[] V, int index, int sumA, int sumV, int[] res) {
        if (index>=A.length) {
            if (sumA<=m) {
                res[0] = Math.max(res[0], sumV);
            }

            return;
        }

        if (sumA>m) return;

        dfs(m, A, V, index+1, sumA, sumV, res);

        dfs(m, A, V, index+1, sumA+A[index], sumV+V[index], res);
    }
    
    
    //No luck with divide and conquer, It probably won't work, the small part solution might not be applicable for the larger part , it is heterogeneous in this regard.
    public int backPackIIDivde_Conquer(int m, int[] A, int[] V) {
        // write your code here
        //int[] res = new int[1];

        return dc(m, A, V, 0, 0);

        //return res[0];
        
    }

    private int dc(int m, int[] A, int[] V, int index, int sumA ) {
        
        if (sumA>m) return 0;

        /*
        if (index==A.length-1) {
            if (sumA+A[index]<=m) {
                return V[index];
            }
            return 0;
        }
        */

        if (index>=A.length) {
            return 0;
        }
        

        return Math.max( dc(m, A, V, index+1, sumA), V[index] + dc(m, A, V, index+1, sumA+A[index]));
    }
}
