package twopointers.differentdirection.towards;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	
	    /**
	     * @param numbers: Give an array numbers of n integer
	     * @return: Find all unique triplets in the array which gives the sum of zero.
	     */
	    public List<List<Integer>> threeSum(int[] numbers) {
	        // write your code here
	        List<List<Integer>> res = new ArrayList<>();
	        int n = numbers.length;
	        if(numbers==null || n<3) return res;
	        Arrays.sort(numbers);
	        
	        for (int i=0; i<n-2; i++) {
	            if (i>=1&&i<n-2&&numbers[i-1]==numbers[i]) {
	                continue;
	            }
	            int j=i+1, k=n-1;
	            while(j<k){
	                int sum=numbers[i]+numbers[j]+numbers[k];
	                if (sum==0) {
	                    List<Integer> tmp = new ArrayList<>();
	                    tmp.add(numbers[i]);
	                    tmp.add(numbers[j]);
	                    tmp.add(numbers[k]);
	                    res.add(tmp);
	                    j++;
	                    k--;
	                    while(j<n-1&&numbers[j]==numbers[j-1]) {
	                        j++;
	                    }
	                    while(k>=0&&numbers[k]==numbers[k+1]) {
	                        k--;
	                    }
	                } else if (sum>0) {
	                    k--;
	                } else {
	                    j++;
	                }
	                
	            }
	            
	        }
	        return res;
	    }
	

}
