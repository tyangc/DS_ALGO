package binarysearch;

public class MedianSortedArrays {

	
	/*
     * @param A: An integer array
     * @param B: An integer array
     * @return: a double whose format is *.5 or *.0
     */
    public double findMedianSortedArrays(int[] A, int[] B) {
        // write your code here

        int n = A.length, m = B.length, k = (n+m)/2;

        //if (n==0) return k==0 ? B[k] : B[k-1];
        //if (m==0) return k==0 ? A[k] : A[k-1];

        if ((n+m)%2!=0) {
          return kth(A, B , 0, 0, k+1);
        } else {
          return (kth(A, B , 0, 0, k) + kth(A, B , 0, 0, k+1))/2.0;
        }



    }

    int kth(int[] A, int[] B, int sa, int sb, int k ) {
      

      if (sa>=A.length) return B[sb + k-1];

      if (sb>=B.length) return A[sa + k-1];

      if (k==1) return Math.min(A[sa], B[sb]);  //This line can not be put as the first line of the function , otherwise if sa=0. A.length=0 then will get indexOutOfBound

      int m1 = sa + k/2 - 1 >= A.length? Integer.MAX_VALUE : A[sa + k/2-1];
      int m2 = sb + k/2 - 1 >= B.length? Integer.MAX_VALUE : B[sb + k/2-1];

      if (m1>=m2) {
        return  kth(A, B, sa, sb + k/2 , k - k/2);  //Has to be k-k/2
      } else {
        return  kth(A, B, sa + k/2, sb, k - k/2);
      }

    }
}
