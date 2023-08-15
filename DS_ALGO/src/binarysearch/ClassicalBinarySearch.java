package binarysearch;

public class ClassicalBinarySearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: An integer array sorted in ascending order
     * @param target: An integer
     * @return: An integer
     */
    public int findPosition(int[] nums, int target) {
        // write your code here
        
        if (nums==null || nums.length==0) return -1;
        
        int start=0, end=nums.length-1;
        while(start<end){
            int mid = (start+end)/2;
            if(nums[mid]==target) return mid;
            if(nums[mid]<target) {
                start=mid+1;
            } else {
                end = mid-1;
            }
        } 
        return -1;
    }
    
    public int binarySearchFristPosition(int[] nums, int target) {
        // write your code here
        if(nums==null || nums.length<1) return -1;

        int i=0, j=nums.length-1;
        while(i+1<j) {
          int mid = i + (j-i)/2;
          if (nums[mid]>=target) {
            j=mid;
          } else {
            i=mid+1;  //i=mid also works
          }
        }

        if (nums[i]==target) return i;
        if (nums[j]==target) return j;

        return -1;
    }
    
    public int binarySearchLastPosition(int[] nums, int target) {
        // write your code here
        if(nums==null || nums.length<1) return -1;

        int i=0, j=nums.length-1;
        while(i+1<j) {
          int mid = i + (j-i)/2;
          if (nums[mid]<=target) {
            i=mid;
          } else {
            j=mid-1; //j=mid also works
          }
        }

        if (nums[j]==target) return j;
        if (nums[i]==target) return i;

        return -1;
    
    }
}
