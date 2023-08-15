package sort;

import java.util.Arrays;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {4,3,2,5,8,2};
		quickSort(A);
		System.out.println(Arrays.toString(A));
	}

	public static void quickSort(int[] nums) {
		if (nums==null || nums.length<2) return;
		
		sort(nums, 0, nums.length-1);
	}
	
	public static void sort(int[] A, int start, int end) {
		if (start>=end) return;
		
		int pivot = A[start - (start -end)/2];
		int left = start, right = end;
		
		while(left<=right) {
			while(left<=right && A[left]<pivot) {
				left++;
			}
			
			while(left<=right && A[right]>pivot) {
				right--;
			}
			
			if (left<=right)  {
				int temp = A[left];
				A[left] = A[right];
				A[right] = temp;
				left++;
				right--;
			}
		}
		System.out.println(Arrays.toString(A));
		sort(A, start, right);
		sort(A, left, end);
		
		
	}
}
