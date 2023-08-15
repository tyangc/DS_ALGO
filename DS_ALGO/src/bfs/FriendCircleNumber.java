package bfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/*
 There are N students in a class. Some of them are friends, while some are not. Their friendship is transitive in nature. For example, if A is a direct friend of B, and B is a direct friend of C, then A is an indirect friend of C. And we defined a friend circle is a group of students who are direct or indirect friends.

Given a N*N matrix M representing the friend relationship between students in the class. If M[i][j] = 1, then the ith and jth students are direct friends with each other, otherwise not. And you have to output the total number of friend circles among all the students.

1.1≤N≤200.
2.M[i][i] = 1 for all students.
3.If M[i][j] = 1, then M[j][i] = 1.

Example 1:

Input: [[1,1,0],[1,1,0],[0,0,1]]
Output: 2
Explanation:
The 0th and 1st students are direct friends, so they are in a friend circle. 
The 2nd student himself is in a friend circle. So return 2.
Example 2:

Input: [[1,1,0],[1,1,1],[0,1,1]]
Output: 1
Explanation:
The 0th and 1st students are direct friends, the 1st and 2nd students are direct friends, 
so the 0th and 2nd students are indirect friends. All of them are in the same friend circle, so return 1.
 */
public class FriendCircleNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param M: a matrix
     * @return: the total number of friend circles among all the students
     */
    public int findCircleNum(int[][] M) {
        // Write your code here
        if (M==null || M.length==0) return 0;
        
        int n=M.length;
   
        List<List<Integer>> result = new ArrayList<>();
        
        Queue<Integer> que= new LinkedList<>();
        List<Integer> list = new ArrayList<>();
        
        for (int i=0; i<n; i++) {
            if (M[i][i]==1){
                que.offer(i);
                M[i][i]=2;
               
            }
                    
            while(!que.isEmpty()) {
                int cur = que.poll();
                list.add(cur);
                for (int j=0;j<n;j++) {
                    if (j!=cur&&M[cur][j]==1) {
                        que.offer(j);
                        
                        M[j][j]=2;
                        M[cur][j]=2;
                        M[j][cur]=2;
                    }
                }
            }
            if(list.size()>0) result.add(list);
            list = new ArrayList<>();
            
        }
     
     
        return result.size();
    }
    
    public int findCircleNumII(int[][] M) {
        // Write your code here
        if (M==null || M[0].length==0) return 0;

        int n=M.length;

        boolean[][] visited = new boolean[n][n];
        List<Set<Integer>> res = new ArrayList<>();

        for (int i=0; i<n; i++) {
          for (int j=0; j<n; j++) {
            if (M[i][j]==1 && !visited[i][j]) {

              Set<Integer> cnt = new HashSet<>();
              //cnt.add(i);
              //cnt.add(j);
              bfs(M, visited, i, j, cnt);
              res.add(cnt);
            }
          }
        }

        return res.size();
    }

    void bfs(int[][] M, boolean[][] visited, int i, int j, Set<Integer> cnt) {
        int n = M.length;
        Queue<Point> que = new LinkedList<>();

        que.offer(new Point(i, j));
        //visited[i][j] = true;
        if (j!=i) que.offer(new Point(i, j));

        while(!que.isEmpty()) {
          Point cur = que.poll();
          cnt.add(cur.x);
          cnt.add(cur.y);
          visited[cur.x][cur.y] = true;
          visited[cur.y][cur.x] = true;

          for (int c=0; c<n; c++) {
            if (M[c][cur.y]==1 && !visited[c][cur.y]) {
              que.offer(new Point(c, cur.y));
            }

            if (M[cur.x][c]==1 && !visited[cur.x][c]) {
              que.offer(new Point(cur.x, c));
            }
          }
        }

    }
}

class Point {
	  int x, y;
	  Point(int x, int y) {
	    this.x = x;
	    this.y = y;
	  }
	}

