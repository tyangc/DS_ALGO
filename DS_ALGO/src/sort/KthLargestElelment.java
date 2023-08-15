package sort;

public class KthLargestElelment {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(kthLargestElement(5, new int[] {9,5,2,1,4,7,8,7,4}));
	}
	
	/**
     * @param n: An integer
     * @param nums: An array
     * @return: the Kth largest element
     */
    public static int kthLargestElement(int n, int[] nums) {
        // write your code here
        if(nums==null) return -1;
        
        return quickSelect(nums, 0, nums.length-1, n);
    }
    
    private static int quickSelect(int[] nums, int start, int end, int k) {
        if (start==end) return nums[start];
        
        int i=start;
        int j=end;
        int pivot =nums[j-(j-i)/2];
        
        while(i<=j) {
            while(i<=j && nums[i]>pivot) {
                i++;
            }
            while(i<=j && nums[j]<pivot) {
                j--;
            }
            
            if (i<=j) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;
            }
        }
        
        if (start+k-1<=j) return quickSelect(nums, start, j, k);
        
        if (start+k-1>=i) return quickSelect(nums, i, end, k-(i-start));
        
        return nums[j+1];
        
    }
    
    //Another way of implementation for kth smallest:
    
    public int kthSmallest(int k, int[] nums) {
        // write your code here
        return recursive(nums, k-1, 0, nums.length-1);

    }

    private int recursive(int[] nums, int k, int left, int right) {

      if (left == right) return nums[left];

      int randomIndex = getRandomIndex(left, right);
      System.out.println(randomIndex);
      int part = partition(nums, left, right, randomIndex);
      System.out.println("partion: " + part);

      if (part == k) {
        return nums[part];
      } else if (part>k) {
        return recursive(nums, k, left, part-1);

      } else {
        return recursive(nums, k, part+1, right);

      }
    }

    private int partition(int[] nums, int left, int right, int pivotIndex) { 
      int pivotValue = nums[pivotIndex];
      swap(nums, pivotIndex, right);

      int storeIndex = left;

      

      for (int i=left; i<right; i++) {
        if (nums[i] < pivotValue) {
          swap(nums, i, storeIndex);
          storeIndex++;
        }
      }

      swap(nums, storeIndex, right);

      return storeIndex;
    } 

    private void swap(int[] nums, int left, int right) {
      int tmp = nums[left];
      nums[left] = nums[right];
      nums[right] = tmp;
    }

    private int getRandomIndex(int left, int right) {
      return left + (int) Math.floor(Math.random()*(right - left + 1));
    }
    
    
    
    
}
