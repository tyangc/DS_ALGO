package binarysearch;

/*
 	Given n pieces of wood with length L[i] (integer array). Cut them into small pieces to guarantee you could have equal or more than k pieces with the same length.
 	What is the longest length you can get from the n pieces of wood? Given L & k, return the maximum length of the small pieces.
	You couldn't cut wood into float length. If you couldn't get >= k pieces, return 0.
	
	Example
Example 1

Input:
L = [232, 124, 456]
k = 7
Output: 114
Explanation: We can cut it into 7 pieces if any piece is 114cm long, however we can't cut it into 7 pieces if any piece is 115cm long.
Example 2

Input:
L = [1, 2, 3]
k = 7
Output: 0
Explanation: It is obvious we can't make it.
Challenge
O(n log Len), where Len is the longest length of the wood.
	
	Test data:
	
	[6,6,5,5,6,5,4,4,5,6,6,6,5,4,5,6,5,6,4,4,4,4,6,4,5,5,4,6,6,6,6,4,6,4,4,5,6,5,5,4,6,4,6,4,4,6,4,5,6,5,6,6,5,4,4,5,6,4,5,5,5,6,6,4,4,5,5,6,5,5,6,4,6,4,5,6,4,4,4,6,4,6,4,5,4,6,5,6,6,5,4,4,6,5,6,5,6,4,4,6,4,6,5,4,4,4,4,6,6,4,6,6,6,5,4,4,6,4,4,4,5,4,6,4,6,6,4,5,6,5,6,5,4,5,5,5,4,6,5,4,5,6,4,4,6,6,5,6,6,4,6,5,6,5,4,6,4,6,4,6,5,6,4,6,6,4,5,4,6,6,5,6,6,5,4,4,4,4,4,5,5,4,6,5,4,6,4,6,5,6,6,5,4,5,6,4,4,6,5,5,6,6,6,4,6,6,5,6,5,4,6,5,4,6,5,6,4,5,4,4,5,4,5,6,6,4,4,4,4,6,6,6,6,6,5,5,4,4,5,5,6,6,5,6,6,6,4,5,5,4,6,4,6,4,4,6,5,6,6,6,5,4,4,4,6,4,6,4,5,6,6,4,5,6,6,6,6,6,4,5,6,4,4,4,4,5,5,5,6,5,5,5,4,4,5,6,6,4,6,6,6,4,5,4,6,5,6,5,5,6,5,4,5,4,6,4,5,5,5,5,5,5,4,5,5,6,4,5,6,5,6,5,6,4,4,5,6,5,6,6,6,5,4,5,4,6,6,6,6,4,5,5,4,5,5,5,4,6,5,5,4,6,5,6,6,6,4,4,4,6,4,6,6,4,5,4,5,6,6,6,5,6,4,6,6,5,6,5,6,4,4,6,4,6,5,6,6,5,4,6,5,6,6,5,6,6,4,6,6,5,6,5,6,4,5,5,5,6,6,4,5,4,4,6,4,6,6,5,6,6,6,5,6,6,4,6,6,5,5,6,5,6,6,6,5,5,6,6,5,4,6,5,6,5,6,5,5,6,5,4,5,6,5,4,6,5,5,4,5,4,5,6,5,6,4,6,5,4,4,4,5,5,4,5,4,6,4,5,5,6,5,6,6,5,4,6,6,5,5,6,5,5,5,5,6,5,5,4,4,6,4,5,4,6,6,5,6,5,6,6,4,5,5,5,5,6,6,4,4,5,6,5,5,6,4,6,6,5,5,6,4,4,4,4,5,4,5,4,5,5,4,5,5,5,4,6,4,4,4,5,4,4,5,4,6,6,5,6,4,4,6,6,4,4,5,6,5,5,4,6,6,4,6,5,4,5,5,6,6,6,4,6,6,5,6,4,5,6,4,6,4,5,5,4,4,6,6,6,4,6,5,5,4,5,5,4,5,6,6,6,4,5,4,5,6,4,6,4,5,4,5,4,6,6,6,5,4,6,4,6,4,5,6,5,6,6,6,5,4,5,6,6,6,6,4,5,6,5,4,4,5,5,4,4,4,5,4,6,6,6,6,4,5,5,4,5,4,5,4,4,6,4,5,5,6,4,6,5,4,6,4,6,5,5,6,5,4,4,5,4,4,5,5,5,5,4,4,5,6,4,4,6,6,5,4,6,5,5,4,4,6,4,6,4,5,6,4,5,4,6,5,4,4,5,6,4,4,5,5,6,6,6,6,6,6,4,6,5,6,6,6,6,4,4,5,6,5,4,5,4,4,5,6,5,5,4,4,4,6,6,6,6,6,4,5,4,4,4,4,5,6,4,4,5,5,6,6,6,6,4,6,6,5,4,6,4,5,4,5,5,4,6,6,4,6,4,5,4,5,5,4,6,5,5,4,4,6,6,5,5,4,6,5,5,6,6,4,6,6,4,6,6,4,5,6,6,5,4,5,4,5,5,5,4,6,5,4,6,5,4,4,5,6,5,5,6,4,4,6,4,4,5,4,5,5,4,5,5,4,4,6,4,5,6,4,4,5,6,6,4,5,4,6,5,5,5,5,6,4,5,6,6,6,4,6,5,4,6,5,4,5,4,5,4,6,4,6,6,4,5,6,4,4,6,5,4,4,5,6,6,4,6,6,4,6,5,6,6,4,4,5,5,6,5,4,4,5,4,5,4,4,6,4,4,4,4,4,4,6,6,6,5,6,5,5,4,5,5,5,5,5,6,5,6,4,6,5,4,5,4,5,4,6,5,5,4,5,6,5,4,5,5,6,4,6,5,6,4,6,6,5,6,4,4,4,5,4,6,4,4,4,5,4,5,4,6,4,4,5,4,5,6,4,6,6,5,4,5,4,5,6,4,5,6,4,5,4,5,5,4,4,5,4,5,6,5,4,5,5,6,4,5,5,4,6,6,6,5,5,5,6,6,4,6,5,4,6,6,6,5,6,6,4,4,5,6,4,4,5,6,5,5,5,6,6,5,4,6,6,5,4,5,6,4,4,6,4,6,4,4,5,4,5,4,5,4,6,4,5,5,6,5,5,4,6,6,4,6,5,5,5,6,5,4,5,6,6,4,6,6,4,5,6,4,6,4,5,4,4,6,5,5,4,6,5,4,6,6,4,4,5,6,4,4,4,4,4,6,6,6,4,5,5,4,4,5,4,4,5,6,5,4,6,6,6,5,6,5,4,5,4,6,6,5,6,5,4,4,5,4,4,4,6,4,5,5,4,4,5,4,5,5,5,5,5,6,4,5,4,4,4,4,4,4,5,4,6,5,5,4,4,5,5,4,4,5,5,6,4,6,4,5,5,6,5,5,6,4,4,5,5,6,5,4,4,4,4,4,6,5,4,5,6,4,4,6,4,4,4,6,4,4,6,5,6,6,6,5,6,5,4,5,4,5,5,6,5,6,4,6,5,6,4,4,6,6,4,4,5,5,5,5,5,4,6,4,5,4,4,4,4,5,6,4,4,5,4,5,5,6,6,5,6,6,5,6,6,5,5,4,5,4,5,5,5,4,4,6,5,4,6,6,6,4,4,6,5,6,5,6,6,4,5,6,6,6,4,4,4,5,4,4,6,5,5,6,4,6,4,6,4,4,4,5,6,6,6,6,5,6,5,5,4,4,6,4,6,4,4,6,6,5,4,4,6,5,4,5,4,4,6,6,6,5,5,5,6,6,6,4,4,6,5,6,4,6,5,6,5,5,5,5,6,5,6,6,4,6,6,6,6,4,5,6,5,6,4,6,6,5,5,4,4,6,4,4,5,4,4,5,6,6,4,4,6,6,6,4,5,4,5,6,6,5,6,4,5,5,5,4,4,4,5,4,5,5,5,5,4,4,5,5,6,4,6,5,5,4,4,6,6,5,6,4,4,6,4,6,4,6,4,4,4,6,5,6,4,6,5,4,4,6,4,6,6,4,5,6,6,4,4,4,4,6,5,4,4,5,5,6,6,5,4,4,4,6,6,5,5,5,6,4,5,4,5,5,4,4,6,5,4,5,4,6,5,6,5,4,4,6,4,5,6,5,4,5,4,5,4,4,5,6,5,6,5,4,5,4,4,5,6,4,6,4,6,4,5,4,4,6,5,5,5,4,5,6,4,5,4,4,6,5,5,6,5,6,6,4,4,6,6,6,6,4,6,4,4,5,4,4,4,6,6,5,4,6,4,6,6,6,5,4,5,6,5,5,5,5,5,4,4,6,4,5,5,5,5,5,5,5,4,6,4,6,6,4,5,4,4,6,5,6,5,4,4,6,5,6,5,6,5,5,6,6,6,5,4,4,4,5,4,6,6,5,5,4,6,5,6,6,5,4,4,5,4,6,5,4,6,6,5,5,5,5,6,6,6,6,6,4,6,6,5,6,6,4,4,4,4,5,4,5,4,4,6,6,6,6,6,6,5,5,5,5,5,5,4,5,4,6,4,4,5,5,5,6,6,5,6,5,4,4,4,6,6,5,6,4,5,5,6,6,4,5,5,4,5,5,4,5,6,5,6,5,6,6,5,5,4,5,4,5,6,5,5,5,4,4,6,5,5,4,4,6,6,6,6,5,6,6,4,5,5,4,5,4,4,4,4,5,6,6,5,4,4,4,5,6,5,5,4,4,6,5,4,6,4,4,4,4,5,6,4,5,5,4,5,4,5,6,5,5,4,5,6,4,5,4,4,4,6,5,4,5,6,4,5,4,4,4,5,5,5,6,6,4,4,6,6,4,4,5,5,6,4,6,6,6,6,4,5,6,4,6,6,6,5,6,5,6,5,5,5,5,5,5,5,4,6,5,4,4,6,6,6,6,6,4,5,4,6,4,5,5,6,6,5,5,6,6,5,6,5,6,4,5,4,5,4,5,6,5,4,5,5,6,4,4,6,6,6,6,5,5,6,4,6,4,6,6,6,6,4,6,5,4,6,4,4,5,6,6,6,6,4,5,6,4,6,4,4,4,6,6,4,5,6,4,6,6,5,6,6,6,5,5,4,4,5,5,5,6,5,6,6,6,6,6,5,5,5,6,4,5,6,5,4,4,5,4,5,5,4,4,6,4,4,6,4,5]
90000

	
 */
public class WoodCut {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param L: Given n pieces of wood with length L[i]
     * @param k: An integer
     * @return: The maximum length of the small pieces
     */
    public int woodCut(int[] L, int k) {
        // write your code here
        if (L==null||L.length==0) return 0; 
        int max=0;
        for (int i : L) {
            if (i>max) max=i;
        }
        
        int start=1, end=max;
        while(start+1<end) {
            int mid = start - (start-end)/2;
            int num=getPieces(L, mid);
            
            if (num>=k) {
                start=mid;
            } else {
                end = mid;
            }
            
        }
        
        System.out.println(start+":"+end);
        
        int numStart = getPieces(L, start);
        
        int numEnd = getPieces(L, end);
        
        if (numStart<k) return 0;
        if (numEnd>=k) return end;
        return start;
        
        //return start<end?end:start;
    }
    
    private int getPieces(int[] L, int s) {
        int num=0;
            
            for (int i : L) {
                num+=i/s;
            }
        return num;
    }
    
    //More consideration and more details and cleaner:
    public int woodCut1(int[] L, int k) {
        if (L==null || L.length==0) return 0;

        int maxLen = 0, minLen=Integer.MAX_VALUE;
        long total = 0;   //If total is int , some test case will got int overflow -> wrong result; so it has to be long type here!!!
        for (int size : L) {
           if (size > maxLen) maxLen = size; 
           if (size < minLen) minLen = size; 
           total += size;
        }

        if (total < k) return 0;

        int start=1, end=maxLen;

        while(start+1 < end) {
            int mid = start + (end-start)/2;
            if (isValid(L, mid, k)) {
                start = mid;
            } else {
                end = mid;
            }

        }
        
        /*
        if (isValid(L, end, k)) return end;
        else return start;
        //return 0;
         * 
         */
        
        if (isValid(L, end, k)) return end;
        if (isValid(L, start, k)) return start;
        return 0;
    }

    private boolean isValid(int[] L, int len, int k) {
        int sum = 0;
        for ( int size : L) {   //Don't use for ( int l : L) { -- this 'l'can be mixed up with '1' 
            sum += size/len;
        }

        return sum >= k;
    }

}