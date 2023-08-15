package binarysearch;

public class LastPositionOfTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int lastPosition(int[] nums, int target) {
        // write your code here
        if (nums==null || nums.length==0) return -1;
        
        int start=0, end=nums.length-1;
        while(start+1<end){
            int mid = start - (start-end)/2;
            
            if (nums[mid]==target) {
                start = mid;
            } else if (nums[mid]<target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[end]==target) return end;
        
        if (nums[start]==target) return start;
        
        return -1;
    }
    
    
    /**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int firstPosition(int[] nums, int target) {
        // write your code here
        if (nums==null || nums.length==0) return -1;
        
        int start=0, end=nums.length-1;
        while(start+1<end){
            int mid = start - (start-end)/2;
            
            if (nums[mid]==target) {
                end = mid;
            } else if (nums[mid]<target) {
                start = mid;
            } else {
                end = mid;
            }
        }
        
        if (nums[start]==target) return start;
        if (nums[end]==target) return end;
        
        
        
        return -1;
    }
}
