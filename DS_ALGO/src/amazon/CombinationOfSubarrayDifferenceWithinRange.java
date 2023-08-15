package amazon;

public class CombinationOfSubarrayDifferenceWithinRange {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getCount(new int[]{1,2,4,6,7,8}, 3));  //Should be 17
	}
	
	public static int getCount(int[] A, int k) {
		if (A==null || A.length==0) return 0;
		int n = A.length;
		int j=0, min=0, max=0, cnt=0; 
		for (int i=0; i<n; i++) {
			j=i;
			min=A[j];
			max=A[j];
			
			while(j<n && max-min<=k) {
				//j++; //Wrong position of j++
				if(j<n) min = Math.min(min, A[j]);
				if(j<n) max = Math.max(max, A[j]);
				j++;
				cnt++;
				System.out.println(i+"|"+j+"|"+min+"|"+max+"|"+cnt);
			}
			
			
		}
		
		return cnt;
	}

}
