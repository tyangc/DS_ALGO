package twopointers.samedirection;
/*
 * https://leetcode.com/problems/number-of-zero-filled-subarrays/solutions/2644259/number-of-zero-filled-subarrays/
 */
public class NumberOfZeroFilledSubarrays {

	public long zeroFilledSubarray(int[] nums) {
        if (nums==null || nums.length==0) return 0L;

        long totalSum = 0;

        int j=0, n=nums.length;

        for (int i=0; i<n; i++) {
            if (nums[i]!=0) {continue;}
            j=i;
            while(j<n && nums[j] == 0) {
                j++;
            }

            
            long len = j-i; //If len is int , then it won't pass large test case 
            totalSum += len*(len+1)/2;
            i = j;

        }

        return totalSum;
    }
	
	//Very lean implementation, but slower than the first one
	public long zeroFilledSubarray1(int[] nums) {
        long ans = 0, numSubarray = 0;
        
        // Iterate over nums, if num = 0, it has 1 more zero-filled subarray
        // than the previous one, otherwise, it has 0 zero-filled subarray.
        for (int num : nums) {
            if (num == 0)
                numSubarray++;
            else
                numSubarray = 0;
            ans += numSubarray;
        }
        
        return ans;
    }
}
