package dfs.twodimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueensII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new NQueensII().totalNQueens(4));
	}
	
	public int totalNQueens(int n) {
        // write your code here
        if (n==1) return 1;
       // if (n==2 || n==3) return 0; not necessary

        List<List<int[]>> res = new ArrayList<>();
        //boolean[][] grid = new boolean[n][n];
        dfs(0, n, new boolean[n][n], new ArrayList<int[]>(), res);
        return res.size();
    }

    private void dfs(int line, int n, boolean[][] grid, List<int[]> tmp, List<List<int[]>> res) {
      if (line==n) return;      

      for (int i=0; i<n; i++) {
        
        if (isValid(grid, line, i)) {
          if (line==n-1) {
            tmp.add(new int[]{line, i});
            res.add(new ArrayList<>(tmp));
            return;
          }  
          grid[line][i] = true;
          tmp.add(new int[]{line, i});
          dfs(line+1, n, grid, tmp, res);
          grid[line][i] = false;
          tmp.remove(tmp.size()-1);
        }
      }
    }

    private boolean isValid(boolean[][] grid, int x, int y) {
      int n = grid.length;
      //int[] dx = {0, 0, 1, -1, 1, 1, -1, -1 };
      //int[] dy = {1, -1, 0, 0, 1, -1, 1, -1};
      
      //Can be optimized:
      int[] dx = {  -1, -1, -1 };
      int[] dy = {  0,  1, -1};

      for (int i=0; i<dx.length; i++) {
        int nx = x + dx[i];
        int ny = y + dy[i];

        while(nx>=0 && nx<n && ny>=0 && ny<n) {
          if (grid[nx][ny]) return false;
          nx += dx[i];
          ny += dy[i];
 
        }
       
      }

      for (int i=0; i<n; i++) {
        System.out.println(Arrays.toString(grid[i]));
      }
      System.out.println(x + "|" + y);
      return true;
    }
}
