package prefixsum;
/*
 194. Matrix restoration

There is a matrix before before with nn rows and mm columns. For each element in before before[i][j]before[i][j], we will use the following algorithm to convert it to after [i] [j]after[i][j]. Given the afterafter matrix, please restore the original matrix beforebefore.

s = 0
for i1: 0 -> i
    for j1: 0 -> j
        s = s + before[i1][j1]
after[i][j] = s
n,m \leq 1\,000n,m≤1000

Input:
2
2
[[1,3],[4,10]]
Output: [[1,2],[3,4]]
Explanation:
before:
1 2
3 4

after:
1 3
4 10
 */
public class MatrixRestoration {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param n: the row of the matrix
     * @param m: the column of the matrix
     * @param after: the matrix
     * @return: restore the matrix
     */
    public int[][] matrixRestoration(int n, int m, int[][] after) {
        // write your code here
        int[][] before = new int[n][m];
        
        before[0][0] = after[0][0];
        
        for (int i=1; i<m; i++) {
            before[0][i] = after[0][i]-after[0][i-1];
        }
        
        for (int i=1; i<n; i++) {
            before[i][0] = after[i][0] - after[i-1][0];
        }
        
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                before[i][j] = after[i][j] + after[i-1][j-1] - after[i][j-1] - after[i-1][j];
            }
        }
        
        return before;
    }
}
