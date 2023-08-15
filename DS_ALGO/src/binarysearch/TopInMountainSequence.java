package binarysearch;

public class TopInMountainSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: a mountain sequence which increase firstly and then decrease
     * @return: then mountain top
     */
    public int mountainSequence(int[] nums) {
        // write your code here
        if (nums==null||nums.length==0) return -1;
        
        int start=0, end=nums.length-1;
        
        while(start+1<end) {
            int mid = start - (start-end)/2;
            
            if (nums[mid]>=nums[mid-1]) {
                if (nums[mid]>=nums[mid+1]) return nums[mid];
                else start = mid;
            } else {
                end = mid;
            }
        }
        return nums[start]>nums[end]?nums[start]:nums[end];
    }
}
