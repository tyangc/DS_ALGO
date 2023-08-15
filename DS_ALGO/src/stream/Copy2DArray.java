package stream;

import java.util.Arrays;

public class Copy2DArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.deepToString(copy2d(new int[][] {{1,3,4,}, {2,5,8}, {3,6,9}})));
		boolean[][] orgArr = new boolean[][] {{true,true,false,}, {false,true,false}, {false,true,false}};
		boolean[][] newArr = copy2d(orgArr);
		System.out.println(Arrays.deepToString(newArr));
		System.out.println(newArr==orgArr);
	}
	
	public static int[][] copy2d(int[][] org) {
		return Arrays.stream(org).map(int[] :: clone).toArray(int[][]::new);
	}

	public static boolean[][] copy2d(boolean[][] org) {
		return Arrays.stream(org).map(boolean[] :: clone).toArray(boolean[][]::new);
	}
}
