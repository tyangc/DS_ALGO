package binarysearch;

public class MinInRotatedArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: a rotated sorted array
     * @return: the minimum number in the array
     */
    public int findMin(int[] nums) {
        // write your code here
        if (nums==null) return -1;
        
        int start=0, end = nums.length-1;
        
        while(start+1<end) {
            int mid=(start+end)/2;
            
            if (nums[mid]>nums[end]) {
                start=mid;
            } else {
                end=mid;
            }
        }
        
        return nums[start]<nums[end]? nums[start]:nums[end];
    }
	
}
