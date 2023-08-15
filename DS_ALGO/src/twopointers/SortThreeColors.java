package twopointers;

/*
 Given an array with n objects colored red, white or blue, sort them so that objects of the same color are adjacent, with the colors in the order red, white and blue.

Here, we will use the integers 0, 1, and 2 to represent the color red, white, and blue respectively.

You are not suppose to use the library's sort function for this problem.
You should do it in-place (sort numbers in the original array).
 
 */
public class SortThreeColors {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param nums: A list of integer which is 0, 1 or 2 
     * @return: nothing
     */
    public void sortColors(int[] nums) {
        // write your code here
        if (nums==null || nums.length<2) return;
        
        int low=0, mid=0, high=nums.length-1;
        
        while(mid<=high) {
            if (nums[mid]==0) {
                swap(nums, low++, mid++);
            } else if (nums[mid]==1) {
                mid++;
            } else {
                swap(nums, mid, high--);
            }
        }
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
