package dfs.twodimension;

public class NumberOfFriendCircles {

	
	/**
     * @param M: a matrix of integer
     * @return: return an Integer
     */
    public int findCircleNum(int[][] M) {
        // write your code here
    	int n = M.length;
    	int[] vis = new int[n];
    	
    	int cnt=0;
    	
    	for (int i=0; i<n; i++) {
    		if (vis[i]==0) {
    			cnt++;
    		}
    		
    		dfs(i, vis, M);
    	}
    	
    	return cnt;
    }
    
    private void dfs(int index, int[] vis, int[][] M) {
    	//if (vis[index]==1) return;  //this is wrong!! vis[i] <already> =1
    	int n = M.length;
    	for (int i=0; i<n; i++) {
    		if (M[index][i]==1&&vis[i]==0) {
    			vis[i]=1;
    			dfs(i, vis, M);
    		}
    	}
    }
}
