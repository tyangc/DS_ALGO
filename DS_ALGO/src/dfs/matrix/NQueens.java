package dfs.matrix;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NQueens {
	//Naive DFS, using visited[][], 4 moves to check diagonal recurrence - clumsy and lengthy code
	public List<List<String>> solveNQueens(int n) {
        // write your code here
        List<List<String>> res = new ArrayList<>();

        if (n==1) {
            List<String> one = new ArrayList<>();
            one.add("Q");
            res.add(one);
            return res;
        }

        boolean[][] visited = new boolean[n][n];
        getMatrix(0, visited, res);

        return res;
        
    }

    private void getMatrix(int row,  boolean[][] visited, List<List<String>> res) {
        int n = visited.length;
        if (row == n) {
            
            System.out.println(Arrays.toString(visited));
            res.add(getResult(visited));
            return;
        }

        for (int i=0; i<n; i++) {
            visited[row][i] = true;
            if (notValid(row, i, visited)) {
                //printVisited(visited);
                System.out.println(row+"|"+i);
                visited[row][i] = false;
                continue;
            }
            //System.out.println(Arrays.toString(visited));
            System.out.println(row + ":");
            //printVisited(visited);
            getMatrix(row+1, visited, res);
            visited[row][i] = false;
        }


        
    }

    private boolean notValid(int row, int col, boolean[][] visited) {
        int n = visited.length;
        for (int i=0; i<n; i++) {
            if (i!=row && visited[i][col]) return true;
        } 

        int i=row, j=col;
        while(i>0 && j>0) {
            i--;
            j--;
            if (i>=0 && j>=0 && visited[i][j]) return true;
            
        }

        i=row;
        j=col;
        while(i>=0 && j<n) {
            i--;
            j++;
            if (i>=0 && j<n && visited[i][j]) return true;
            
        }
        i=row;
        j=col;
        while(i<n && j<n) {
            i++;
            j++;
            if (i<n && j<n && visited[i][j]) return true;
            
        }
        i=row;
        j=col;
        while(i<n && j>=0) {
            i++;
            j--;
            if (i<n && j>=0 && visited[i][j]) return true;
            
        }

        return false;
    }

    private List<String> getResult(boolean[][] visited) {
        List<String> res = new ArrayList<>();
        for (int i=0; i<visited.length; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j=0; j<visited.length; j++) {
                if (visited[i][j]) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }

            res.add(sb.toString());

        }
        return res;
    }

    private void printVisited(boolean[][] visited) {
        for (int i=0; i<visited.length; i++) {
            System.out.println(Arrays.toString(visited[i]));
        }
    }
    
    
    //Better and faster way
    public List<List<String>> solveNQueensFast(int n) {
        // write your code here
        List<List<String>> res = new ArrayList<>();

        if (n==1) {
            List<String> one = new ArrayList<>();
            one.add("Q");
            res.add(one);
            return res;
        }

        List<Integer> visited = new ArrayList<>();
        getMatrixFast(n, visited, res);

        return res;
        
    }

    private void getMatrixFast(int n,  List<Integer> visited, List<List<String>> res) {
        int len = visited.size();
        if (len == n) {
            
            //System.out.println(Arrays.toString(visited));
            res.add(getResultFast(visited, n));
            return;
        }

        
        for (int i=0; i<n; i++) {
            
            if (notValidFast(n, i, visited)) {
                
                continue;
            }

            visited.add(i);
            //System.out.println(Arrays.toString(visited));
            //System.out.println(row + ":");
            //printVisited(visited);
            getMatrixFast(n, visited, res);
            visited.remove(visited.size()-1);
        }
        
    }

    private boolean notValidFast(int n, int col, List<Integer> visited) {
        int len = visited.size();
        for (int i=0; i<len; i++) {
            if (visited.get(i) == col) return true;

            if (i-visited.get(i) == len-col) return true;

            if (i+visited.get(i) == len+col) return true;

        } 

        return false;
    }

    private List<String> getResultFast(List<Integer> visited, int n) {
        List<String> res = new ArrayList<>();
        for (int i=0; i<n; i++) {
            StringBuilder sb = new StringBuilder();
            int pos = visited.get(i);
            for (int j=0; j<n; j++) {
                if (j == pos) {
                    sb.append("Q");
                } else {
                    sb.append(".");
                }
            }
 
            res.add(sb.toString());

        }
        return res;
    }
}
