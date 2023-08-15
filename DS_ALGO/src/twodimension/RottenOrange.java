package twodimension;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicBoolean;

public class RottenOrange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(new RottenOrange().orangesRotting(new int[][] {{2,1,1},{1,1,0},{0,1,1}}));
		System.out.println(new RottenOrange().orangesRotting(new int[][] {{2,1,1},{0,1,1},{1,0,1}}));
		System.out.println(new RottenOrange().orangesRotting(new int[][] {{2,0}}));
	}

	public int orangesRotting(int[][] grid) {
        
		printArray(grid);
        int day = 2;
        //System.out.println(check(grid));
                
        while(!check(grid) ) {
            AtomicBoolean anyChange = new AtomicBoolean(false);
            for (int i=0; i<grid.length;i++) {
                for (int j=0;j<grid[0].length;j++) {
                    if (grid[i][j]==day) {
                    	
                        impact(grid, i+1, j, day, anyChange);
                        
                        impact(grid, i-1, j, day, anyChange);
                        
                        impact(grid, i, j-1, day, anyChange);
                        
                        impact(grid, i, j+1, day, anyChange);
                        
                    }
                }
            }
            //System.out.println(anyChange);
            if (!anyChange.get()) break;
            //printArray(grid);
            day++;
        }
        
        if (!check(grid)) return -1;
        
        return day-2;
    }
    
    public void impact(int[][] ar, int i, int j, int d, AtomicBoolean changed) {
        if (i<0||i>=ar.length||j<0||j>=ar[0].length) return;
        if (ar[i][j]==1) {
            ar[i][j] = d+1;
            //System.out.println(i+"|" + j + "|" + ar[i][j]);
            changed.set(true);
        }
    }
    
    public boolean check(int[][] grid) {
        for (int i=0; i<grid.length;i++) {
            for (int j=0;j<grid[0].length;j++) {
                if (grid[i][j]==1) return false;
            }
        }
        return true;
    }
    
    public void printArray(int[][] ar) {
    	for (int[] line : ar) {
    		System.out.println(Arrays.toString(line));
    	}
    	
    }
}
