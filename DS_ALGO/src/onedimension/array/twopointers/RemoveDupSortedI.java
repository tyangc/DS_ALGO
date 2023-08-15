package onedimension.array.twopointers;

import java.util.Arrays;

public class RemoveDupSortedI {
	
	public static void main(String args[]) {
		/*
		System.out.println(Arrays.toString(removeDupSortedI(new int[] {1,2,2,2,3,4,4,5,5,5})));
		System.out.println(Arrays.toString(removeDupSortedII_b(new int[] {1,1,1,1,2,2,2,3,4,4,5,5,5})));
		System.out.println(Arrays.toString(removeDupSortedII_b(new int[] {1,2,2,2,3,4,4,5,5,5})));
		*/
		System.out.println(Arrays.toString(removeElement(new int[] {1,4,4,2,2,3,4,4,5,5}, 4)));
	}

	public static int[] removeDupSortedI(int[] ar) {
		if (ar==null ||ar.length<2) return ar;
		
		int i=0, j=1;
		while (j<ar.length) {
			if(ar[i]!=ar[j]) {
				i++;
				ar[i] = ar[j];
				
			}
			
			j++;
		}
		
		return Arrays.copyOf(ar, i+1);
	}
	
	public static int[] removeDupSortedII_a(int[] ar) {
		if (ar==null ||ar.length<3) return ar;
		int i=0, j=1;
		while (j<ar.length) {
			
			if (ar[i]==ar[j]) {
				if (i==0) {
					i++;
					j++;
					
				} else if (ar[i]==ar[i-1]) {
					j++;
				} else {
					i++;
					ar[i]=ar[j];
					j++;
				}
			} else {
				i++;
				ar[i]=ar[j];
				j++;
			}
			
		}
		
		return Arrays.copyOf(ar, i+1);
		
	}
	
	
	public static int[] removeDupSortedII_b(int[] ar) {
		if (ar==null ||ar.length<3) return ar;
		
		//int i=1, j=2;
		/*
		while (j < ar.length) {

			//if (i == 0 && ar[i] == ar[j])
			//	i++;

			if (ar[i] != ar[j] || ar[i] != ar[i - 1]) {
				i++;
				ar[i] = ar[j];
			}

			j++;
		}
		*/
		int i=1;
		for (int j=2;j<ar.length;j++) {
			if (ar[i] != ar[j] || ar[i] != ar[i - 1]) {
				i++;
				ar[i] = ar[j]; 
			}
		}
		return Arrays.copyOf(ar, i+1);
	}

	public static int[] removeElement(int[] ar, int e) {
		if (ar==null || ar.length==0) return ar;
		
		int i=0, j=0;
		while(j<ar.length) {
			/* Wrong: i move wrongly
			if (ar[i]!=e || ar[j]!=e) {
				
				ar[i] = ar[j];
				i++;
			} 
			j++;
			*/
			
			if (ar[j]!=e) {
				
				ar[i] = ar[j];
				i++;
			} 
			j++;
			System.out.println(i+ "|" +j + " : " + Arrays.toString(ar));
		}
		
		return Arrays.copyOf(ar, i); //Not i+1
 	}
}
