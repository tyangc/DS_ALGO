package bfs;
/*
 * https://algorithms.tutorialhorizon.com/find-the-number-of-distinct-islands-or-connected-components/
 * 
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
//import javafx.util.Pair;

//If replace Point with Point, then it won't work if Point doesn't have hashCode() or equals(Object p)

public class NumberDistinctIslands {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		char[][] grid = {    {'x', 'x', '0', 'x', 'x'},
							 {'x', '0', 'x', '0', '0'},
							 {'0', '0', 'x', 'x', '0'},
							 {'x', '0', '0', '0', '0'},
							 {'x', 'x', '0', 'x', 'x'}  };
		
		Map<String, Integer> map = new NumberDistinctIslands().getShapeCount(grid);
		System.out.println(map.toString());
		System.out.println(map.values());
		
		Map<ArrayList<Point>, Integer> mapDfs = new NumberDistinctIslands().getShapeCountDfs(grid);
		System.out.println(mapDfs.toString());
		System.out.println(mapDfs.values());
	}
	
	public Map<String, Integer> getShapeCount(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		Map<String, Integer> map = new HashMap<>();
		boolean[][] visited = new boolean[m][n];
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (grid[i][j]=='x' && !visited[i][j]) {
					bfsGrid(grid, map, visited, i, j); 
				}
			}
		}
		
		return map;
	}
	
	public Map<ArrayList<Point>, Integer> getShapeCountDfs(char[][] grid) {
		int m = grid.length;
		int n = grid[0].length;
		
		Map<ArrayList<Point>, Integer> map = new HashMap<>();
		
		boolean[][] visited = new boolean[m][n];
		ArrayList<Point> island = null;
		
		for (int i=0; i<m; i++) {
			for (int j=0; j<n; j++) {
				if (grid[i][j]=='x' && !visited[i][j]) {
					island = new ArrayList<>();
					dfsGrid(grid, i, j, i, j, island, visited); 
					
					if (!map.containsKey(island)) {
						map.put(island, 1);
					} else {
						map.put(island, map.get(island)+1);
					}
				}
			}
		}
		
		return map;
	}
	
	private void bfsGrid(char[][] grid, Map<String, Integer> map, boolean[][] visited, int x, int y) {
		Queue<Point> que= new LinkedList<>(); 
		que.offer(new Point(x, y));
		StringBuilder sb = new StringBuilder();
		int m = grid.length;
		int n = grid[0].length;
		
		
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		while(!que.isEmpty()) {
			Point cur = que.poll();
			visited[cur.x][cur.y] = true;
			
			sb.append((cur.x-x) + "+" + (cur.y-y) + "~");
			
			for (int i=0; i<dx.length; i++) {
				int xn = cur.x + dx[i];
				int yn = cur.y + dy[i];
				
				if (validate(grid, visited, xn, yn) && grid[xn][yn]=='x') {
					que.offer(new Point(xn, yn));
				}
			}
			
		}
		
		String key = sb.toString();
		
		if (map.containsKey(key)) {
			map.put(key, map.get(key)+1);
		} else {
			map.put(key, 1);
		}
		
	
	}

	private void dfsGrid(char[][] grid, int x, int y, int x0, int y0, List<Point> island, boolean[][] visited) {
		if (!validate(grid, visited, x, y) || grid[x][y]!='x') return;
		
		island.add(new Point(x-x0, y-y0));
		visited[x][y] = true;
			
		int m = grid.length;
		int n = grid[0].length;
	
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		
		for (int i=0; i<dx.length; i++) {
			dfsGrid(grid, x+dx[i], y+dy[i], x0, y0, island, visited);
		}
	}
	
	private boolean validate(char[][] grid, boolean[][] visited, int x, int y) {
		int m = grid.length;
		int n = grid[0].length;
		
		if (x<0 || x>=m || y<0 || y>=n || visited[x][y]) {
			return false;
		}
		
		return true;
	}
	
	//Using internal class, since it is already defined in the same package
	
	class Point {
		int x, y;
		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		
		public boolean equals(Object p) {
			if (p!=null && p instanceof Point && this.x==((Point)p).x && this.y==((Point)p).y) return true;
			return false;
		}
		
		
		public String toString() {
			return x + "+" + y;
		}
		
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}



