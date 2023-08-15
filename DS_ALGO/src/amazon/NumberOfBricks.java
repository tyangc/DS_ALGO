package amazon;

import java.util.Arrays;

/*
 * https://leetcode.com/discuss/interview-question/1829038/Amazon-or-OA-or-numberOfBricks
 * Problem Statement:

John (1) and Jack (2), are friends who construct the wall as per the number of bricks given to them.
They work turn by turn. John works in the increasing order starting from 1 with an increment of 1. Jack places twice the bricks as John places in previous turn. 
Goal is to find who placed the last brick and how many bricks will be placed in the end.

Example 1:
numberOfBricks: 13
John & Jack will construct the wall
John 1
Total Bricks till now: 1
Jack 1 * 2
Total Bricks till now: 3
John 2
Total Bricks till now: 5
Jack 2 * 2
Total Bricks till now: 9
John 3
Total Bricks till now: 12
Jack 3 * 2
Total Bricks till now: 18
Since total bricks to be placed were 13. But lastly sum became 18, hence lastly Jack has to place on 1 more brick. The correct answer in result array is:
result[0] = 2 // as Jack placed the last brick
result[1] = 1 // only 1 brick was to be placed in the end

Example 2:
numberOfBricks: 10
John & Jack will construct the wall
John 1
Total Bricks till now: 1
Jack 1 * 2
Total Bricks till now: 3
John 2
Total Bricks till now: 5
Jack 2 * 2
Total Bricks till now: 9
John 3
Total Bricks till now: 12
Since total bricks to be placed were 10. But lastly sum became 12, hence lastly John has to place on 1 more brick. The correct answer in result array is:
result[0] = 1 // as John placed the last brick
result[1] = 1 // only 1 brick was to be placed in the end"
 */
public class NumberOfBricks {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(getLastBricks(13)));
		System.out.println(Arrays.toString(getLastBricks(10)));
		System.out.println(Arrays.toString(getLastBricks(20)));
		
	}

	public static int[] getLastBricks(int n) {
		
		int john=1, jack=2;
		if (n==0) return new int[]{};
		if (n==1) return new int[] {1,0};
		if (n==2) return new int[] {2,1};
		
		int[] res = new int[2];
				
		int start = 2, end = n; //(int)Math.ceil(Math.sqrt(n)); //the performance will be optimized this way
		
		while(start<end) {
			int m = start + (end-start)/2;
			
			int low = getLow(m), mid = getMid(m), high = getHigh(m);
			System.out.println(low + "|" + mid + "|" + high);
			
			if (n<low) {
				end = m;
			} else if (n>high) {
				start = m+1;
				
				//It seems end = m-1 & start = m also works, just need real time try out
			} else {
				System.out.println("should be here;");
				if (n<mid) {
					res[0] = john;
					res[1] = n-low;
				} else {
					res[0] = jack;
					res[1] = n - mid;
				}
				return res;
			}
			
		}
		
		//return res;
		return new int[]{0, 0};
	}
	
	private static int getLow(int n) {
		return n*(n-1)*3/2;
	}
	
	private static int getMid(int n) {
		return n*(3*n-1)/2;
	}
	
	private static int getHigh(int n) {
		return 3*n*(n+1)/2;
	}
}
