package dfs.twodimension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sudoku {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//Using two dimensional coordinate
	private boolean solveSudoku1(int[][] board) {
		return canSolve(board, 0, 0);

	}
	
	private boolean canSolve(int[][] board, int i, int j) {
		if (i==9 && j==0) {
			return true;
		}
		
		int nextI = j+1==9?i+1:i;
		int nextJ = j+1==9?0:j+1;
		
		if (board[i][j]!=0) {
			return canSolve(board, nextI, nextJ);
		}
		
		for (int c=1; c<=9; c++) {
			if (!isValid(board, i, j, c)) {
				continue;
			}
			board[i][j] = c;
			if ( canSolve(board,nextI, nextJ)) {
				return true;
			}
			board[i][j] = 0;
		}
		
		return false;
		
	}
	
	private boolean isValid(int[][] board, int row, int col, int c) {
		for (int i=0; i<9; i++) {
			
			if (board[row][i]==c) return false;
			if (board[i][col]==c) return false;
				
			int x = row / 3 * 3 + i / 3;
			int y = col / 3 * 3 + i % 3;
			
			if (board[x][y]==c) return false;
		}
		
		return true;
	}
	
	//Using linear index number converted from coordinate:
	
	public void solveSudoku2(int[][] board) {
        // write your code here
        if (dfs(board, 0)) {

          for (int i=0; i<board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
          }
        }
    }

    private boolean dfs(int[][] board, int index) {
      if (index==81) return true;

      int x = index/9;
      int y = index%9;

      if (board[x][y] != 0) {
        return dfs(board, index+1);
      }

      for (int v=1; v<=9; v++) {

          if (isValid1(board, x, y, v)) {
            board[x][y] = v;
            if (dfs(board, index+1)) return true;
            board[x][y] = 0;
          }

      }
      return false;
    }

    private boolean isValid1(int[][] board, int x, int y, int v) {
      for (int i=0; i<9; i++) {
        if (board[i][y] == v) return false;
        if (board[x][i] == v) return false;

        if (board[x/3*3 + i/3][y/3*3 + i%3] == v) return false; 
      }
      return true;
    } 
    
    //using optimization of searching order:
    
    public void solveSudoku3(int[][] board) {
        // write your code here
        boolean[][] usedRow = new boolean[9][10];
        boolean[][] usedCol = new boolean[9][10];
        boolean[][] usedBox = new boolean[9][10];

        for (int i=0; i<9; i++) {
          for (int j=0; j<9; j++) {
            int val = board[i][j];
            usedRow[i][val] = true;
            usedCol[j][val] = true;
            usedBox[i/3*3 + j/3][val] = true;
          }
        }
        
        dfs(board, usedRow, usedCol, usedBox);
        /*
        if (dfs(board, usedRow, usedCol, usedBox)) {

          for (int i=0; i<board.length; i++) {
            System.out.println(Arrays.toString(board[i]));
          }
        }
        */
    }

    private boolean dfs(int[][] board, boolean[][] usedRow, boolean[][] usedCol, boolean[][] usedBox) {
      
      Position leastPos = getLeastPosition(board, usedRow, usedCol, usedBox);
      if (leastPos == null) return true;
      
   
      for (int i=0; i<leastPos.vals.size(); i++) {
      //for (int v : leastPos.vals) {  //This is wrong useXXX already contains the iteration of all values !!
        int x = leastPos.x;
        int y = leastPos.y;
        int v = leastPos.vals.get(i);
        board[x][y] = v;
        usedRow[x][v] = true;
        usedCol[y][v] = true;
        usedBox[x/3*3 + y/3][v] = true;
        
        if (dfs(board, usedRow, usedCol, usedBox)) return true;
        usedRow[x][v] = false;
        usedCol[y][v] = false;
        usedBox[x/3*3 + y/3][v] = false;
        board[x][y] = 0;

      }
      return false;
    }

    private boolean isValid(int[][] board, int x, int y, int v, boolean[][] usedRow, boolean[][] usedCol, boolean[][] usedBox) {
     // for (int i=0; i<9; i++) {
        if (usedRow[x][v]) return false;
        if (usedCol[y][v]) return false;
        if (usedBox[x/3*3+y/3][v]) return false;

      //}
      return true;
    } 

    private Position getLeastPosition(int[][]board, boolean[][] usedRow, boolean[][] usedCol, boolean[][] usedBox) {
      Position leastPos = null;
      
      for (int i=0; i<9; i++) {
          for (int j=0; j<9; j++) {
            if (board[i][j] != 0) continue;  //If miss this line , will get the original board 
            List<Integer> choices =  new ArrayList<>();
            for (int v=1; v<=9; v++) {
              if (isValid(board, i, j, v, usedRow, usedCol, usedBox)) {
                choices.add(v);
              }
            }
            if (leastPos == null || leastPos.vals.size() > choices.size()) {
              leastPos = new Position();
              leastPos.x = i;
              leastPos.y = j;
              leastPos.vals = choices;
            }

          }
      }
      return leastPos;
    }

    class Position {
      int x, y;
      List<Integer> vals;
    }
}
