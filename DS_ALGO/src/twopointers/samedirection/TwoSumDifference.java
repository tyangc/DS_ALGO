package twopointers.samedirection;

public class TwoSumDifference {
	
	//Use towards two points
	public int[] twoSum7(int[] nums, int target) {
        // write your code here
        int n=nums.length, j=0;
        for (int i=0; i<n-1; i++ ) {
            j=n-1;
            while (i<j) {
                if (nums[j] - nums[i]==Math.abs(target)) {
                    return new int[]{nums[i], nums[j]};
                } else {
                    j--;
                }
            }
        }

        return new int[]{-1, -1};
    }
	
	//Use same direction two pointers
	
	public int[] twoSum7SDTP(int[] nums, int target) {
        // write your code here
        
        if (nums==null || nums.length<2) return new int[]{-1, -1};
        int len = nums.length;
        target = Math.abs(target);
        int j=1;
        for (int i=0; i<len-1;i++) {
          //int j=i+1; not optimized way ~ will O(n^2)
        	
          j = Math.max(j, i+1);  //in case i>=j 
          while(j<len && nums[j]-nums[i]<target) {
            j++;
          }

          if (nums[j]-nums[i] == target) return new int[]{nums[i], nums[j]};

        }
        
        return new int[]{-1, -1};
    }
	
	//Use binary search: 
	
	public int[] twoSum7BS(int[] nums, int target) {
        // write your code here
        if (nums==null || nums.length<2) return new int[]{-1, -1};

        //int t = Math.abs(target); //Not necessary
        target = Math.abs(target);
        for (int i=0; i<nums.length-1; i++) {
          int j = binarySearch(nums, i+1, nums.length-1, nums[i]+target);
          if (j!=-1) {
            //if (i==j) continue; //For the case [0, 1, 2, 2] target = 0;  if pass i+1 for the start position then this is not necessary
            return new int[]{nums[i], nums[j]};
          }
        }

        return new int[]{-1, -1};
    }

    private int binarySearch(int[] nums, int start, int end, int target) {
      int i = start, j=end;
      while(i+1<j) {
        int mid = i + (j-i)/2;
        if (nums[mid]>=target) {
          j = mid;
        } else {
          i = mid;
        }
      }

      if (nums[j]==target) return j;
      if (nums[i]==target) return i;
      return -1;
    }

	
}
