package twopointers.samedirection;

import java.util.HashMap;
import java.util.Map;

/*
 Pick Fruits

Xiaohong went to the orchard to pick fruit. There are 2 baskets that can hold countless fruits, but each baskert can only hold one type of fruit. 
Start from the tree at any position and pick it to the right. Stop picking when one of the following two conditions occurs, 
1. encountered the third type of fruit, no basket can be put, 
2. meet the end. Returns the maximum number of fruits that can be picked.The fruit array is represented by arr.

The length of the array does not exceed 100,000

Example 1:

Input：[1,2,1,3,4,3,5,1,2]
Output：3
Explanation：
Select [1, 2, 1] or [3, 4, 3]. The length is 3.
Example 2:

Input：[1,2,1,2,1,2,1]
Output：7
Explanation：
Select  [1, 2, 1, 2, 1, 2, 1].The length is 7.
 */
public class PickFruits {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param arr: the arr
     * @return: the length of the longset subarray
     */
    public int pickFruits(int[] arr) {
        // Write your code here.
        if (arr==null ||arr.length==0) return 0;
        
        int j=0, differentNumber=0, maxLength=0;
        Map<Integer, Integer> count = new HashMap<>();
        
        for (int i=0; i<arr.length; i++) {
            while(j<arr.length && differentNumber<3) {
                count.put(arr[j], count.getOrDefault(arr[j], 0) + 1);
                if (count.get(arr[j])==1) {
                    differentNumber++;
                }
                j++;
                if (differentNumber<=2) {
                    maxLength = Math.max(maxLength, j-i);
                } 
                
            }
            
            count.put(arr[i], count.getOrDefault(arr[i], 0) - 1);
            if (count.get(arr[i])==0) {
                differentNumber--;
            }
        }
        return maxLength;
    }
    
    //Little bit concise
    public int pickFruits1(int[] arr) {
        // Write your code here.
        Map<Integer, Integer> count = new HashMap<>();
        int j = 0, max = 0;

        for (int i=0; i<arr.length; i++) {
          while(j<arr.length && count.size()<=2) {
            count.put(arr[j], count.getOrDefault(arr[j], 0)+1);
            
            j++;
            if (count.size() == 2) max = Math.max(max, j-i);
            
          }

          if (count.get(arr[i]) == 1) count.remove(arr[i]);
          else count.put(arr[i], count.get(arr[i]) - 1);
        }

        return max;
    }
}
