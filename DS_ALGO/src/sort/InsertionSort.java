package sort;

import java.util.Arrays;

public class InsertionSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(Arrays.toString(sort(new int[] {7,3,6,19,2, 22, 34, 8789, 1,2,56, -1, -34})));
	}
	
	public static int[] sort(int[] arr) {
		
		if (arr == null || arr.length < 2) return arr;
		int len = arr.length;
		for (int i=1; i<len; i++) {
			for (int j=0; j<i; j++) {
				if (arr[j]>arr[i]) {
					swap(arr, i, j);
				}
			}
		}
		
		return arr;
	}

	private static void swap(int[] nums, int i, int j) {
		int tmp = nums[i];
		nums[i] = nums[j];
		nums[j] = tmp;
		
	}
}
