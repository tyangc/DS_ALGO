package microsoft.oa;

/*
 https://leetcode.com/discuss/interview-question/364618/
 */
import java.util.Arrays;

public class MinStepMakePileEqualHeight {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[][] testcases = {{5, 2, 1},  {4,5,5,4,2}};
		
		for ( int[] test : testcases ) {
			System.out.println(minSteps(test));
		}
	}
	
	public static int minSteps(int[] piles){
		int len = piles.length;
		if (len <= 1) return 0;
		
		Arrays.sort(piles);
		
		int res=0, distinctNum=0;
		
		for (int i=1; i<len; i++) {
			if (piles[i]==piles[i-1]) {
				res += distinctNum;
			} else {
				distinctNum++;
				res += distinctNum;
			}
		}
		
		return res;
	}

}
