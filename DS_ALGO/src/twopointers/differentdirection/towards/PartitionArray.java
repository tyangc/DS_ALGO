package twopointers.differentdirection.towards;

/*
 * https://www.lintcode.com/problem/31/
 * Description
Given an array nums of integers and an int k, partition the array (i.e move the elements in "nums") such that:

All elements < k are moved to the left
All elements >= k are moved to the right
Return the partitioning index, i.e the first index i nums[i] >= k.
 */
public class PartitionArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	  /**
     * @param nums: The integer array you should partition
     * @param k: An integer
     * @return: The index after partition
     */
    public int partitionArray(int[] nums, int k) {
        // write your code here
        if (nums==null||nums.length<2) return 0;
        
        int i=0, j=nums.length-1;
        
        while(i<j) {
            while(i<j&&nums[i]<k) {
            	
                i++;
            }
            
            while(i<j&&nums[j]>=k) {
                j--;
            }
            
            if (i<j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
        }
        
        return nums[i]>=k ? i : i+1;
    }
}
