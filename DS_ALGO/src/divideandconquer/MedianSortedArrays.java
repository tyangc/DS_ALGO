package divideandconquer;

public class MedianSortedArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(findMedianSortedArrays(new int[] {1,4,8,10, 20}, new int[] {4,5,7,16,17}));
		System.out.println(findMedianSortedArrays(new int[] {3}, new int[] {4}));
	}

	 /*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public static double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here
        if ((A==null||A.length==0) && (B==null||B.length==0)) return 0.0;
        
        int m=A.length, n=B.length, k=(m+n)/2;
        
        if ((m+n)%2==1) return getKth(A, B, 0, 0, k+1);
        else return (getKth(A, B, 0, 0, k) + getKth(A, B, 0, 0, k+1))/2.0;
        
    }
    
    private static int getKth(int[] A, int[] B, int start1, int start2, int k) {
        if (start1>=A.length) return B[start2+k-1];
        if (start2>=B.length) return A[start1+k-1];
        
        if (k==1) return Math.min(A[start1], B[start2]);
        
        int mid1 = start1+k/2-1<A.length? A[start1+k/2-1] : Integer.MAX_VALUE;
        int mid2 = start2+k/2-1<B.length? B[start2+k/2-1] : Integer.MAX_VALUE;
        
        if (mid1<=mid2) return getKth(A, B, start1+k/2, start2, k-k/2);
        else return getKth(A, B, start1, start2+k/2, k-k/2);
        
        
    }
}
