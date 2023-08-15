package sort;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] A = new int[] {5,2,8,4,7,1,9,3,4};
		mergeSort(A);
		System.out.println(Arrays.toString(A));
	}
	
	public static void mergeSort(int[] nums) {
		if (nums==null || nums.length<2) return;
		
		int[] temp = new int[nums.length]; 
		sort(nums, 0, nums.length-1, temp);
		/*
		for (int i=0; i<nums.length; i++) {
			nums[i] = temp[i];
		}
		*/
	}

	private static void sort(int[] A, int start, int end, int[] temp) {
		if (start==end) return;
		//System.out.println("Sort: " + start + " | " + end);
		int mid = start-(start-end)/2;
		sort(A, start, mid, temp);
		sort(A, mid+1, end, temp);
		
		merge(A, start, end, temp);
		//System.out.println(Arrays.toString(temp));
		
		
	}
	
	private static void merge(int[] A, int start, int end, int[] temp) {
		//if (start==end) return;
		//System.out.println("Merge: " + start + " | " + end);
		int mid = (start+end)/2;
		int i = start;
		int j = mid+1, k= start;
		
		while(i<=mid && j<=end) {
			if (A[i]<A[j]) {
				temp[k++] = A[i++];
			} else {
				temp[k++] = A[j++];
			}
		}
		
		while(i<=mid) {
			temp[k++] = A[i++];
		}
		
		while(j<=end) {
			temp[k++] = A[j++];
		}
		
		for (int n=start; n<=end; n++ ) {
			A[n] = temp[n];
		}
		/*
		for (int n=start; n<=end; n++ ) {
			System.out.print(temp[n] + "|");
			
		}
		System.out.println(" ");
		*/
	}
}
