package sort;

import java.util.Arrays;


//Least swap times among all sorting algo
public class SelectionSort {
	public static void main(String[] args) {
		System.out.println(Arrays.toString(sort(new int[] {7,3,6,19,2})));
		System.out.println(Arrays.toString(sort(new int[] {7,3,6,19,2, 22, 34, 8789, 1,2,56, -1, -34})));
	}
	
	public static int[] sort(int[] nums) {
		if (nums==null || nums.length<2) return nums;
		int len = nums.length;
		//System.out.println(nums);
		for (int i=0; i<len-1; i++) {
			int min = nums[i], k=i; 
			for (int j=i+1; j<len; j++) {
				if (nums[j] < min) {
					k = j;
					min = nums[j];
				}
			}
			
			if (i != k) swap(nums, i, k);
		}
		
		return nums;
	}
	
	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
		
	}
}
