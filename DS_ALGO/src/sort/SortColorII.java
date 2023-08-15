package sort;

/*
 143. Sort Colors II

Given an array of n objects with k different colors (numbered from 1 to k), sort them so that objects of the same color are adjacent, with the colors in the order 1, 2, ... k.

Example
Example1

Input:
[3,2,2,1,4]
4
Output:
[1,2,2,3,4]
Example2

Input:
[2,1,1,2,2]
2
Output:
[1,1,2,2,2]
Challenge
A rather straight forward solution is a two-pass algorithm using counting sort. That will cost O(k) extra memory. Can you do it without using extra memory?

Notice
You are not suppose to use the library's sort function for this problem.
k <= n
 */

public class SortColorII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//Rainbow sort
	
	public void sortColors2(int[] colors, int k) {
        // write your code here
        if (k<=1) return;

        rbsort(colors, 0, colors.length-1, 1, k);
    }

    private void rbsort(int[] nums, int start, int end, int cfrom, int cto) {
        if (start>=end || cfrom==cto) return;

        int mid = cfrom + (cto - cfrom)/2;

        int left = start, right = end;

        while(left<=right) {
            while(left<=right && nums[left]<=mid) {
                left++;
            }

            while(left<=right && nums[right] >mid) {
                right--;
            }

            if (left<=right) {
                int tmp = nums[left];
                nums[left] = nums[right];
                nums[right] = tmp;
            }
        }

        rbsort(nums, start, right, cfrom, mid);
        rbsort(nums, left, end, mid+1, cto);
    }
    
    public void sortColors2CountingSort(int[] colors, int k) {
    	if (colors==null || colors.length<=1 || k<=1) return;
    	
    	int index=0; 
    	
    	while(index<colors.length) {
    		
    		if (colors[index]<=0) {
    			index++;
    		} else {
    			int tmp = colors[index] - 1;
    			
    			if (colors[tmp] > 0) {
    				
    				swap(colors, index, tmp);
    				
    				colors[tmp] = -1;
    			} else {
    				
    				colors[tmp]--;
    				colors[index] = 0;
    				index++;
    				
    			}
    		}
    	}
    	
    	//System.out.println(Arrays.toString(colors));
    	
    	int i=colors.length-1;
    	for (int j=k-1; j>=0; j--) {
    		for (int m = 0; m>colors[j]; m--) {
    			colors[i--] = j+1;
    		}
    	}
    	
    	/* - will also work
    	int i = len - 1;  

        while(k > 0) {

            for(int j = 0; j>colors[k-1]; j--) {

                colors[i--] = k;

            }

            k--;
		
        } */
    }
    
    private void swap(int[] grid, int a, int b) {
		int tmp = grid[a];
		grid[a] = grid[b];
		grid[b] = tmp;
	}
}
