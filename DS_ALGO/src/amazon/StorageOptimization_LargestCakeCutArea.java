package amazon;

import java.util.Arrays;

/*
 * 1465. Maximum Area of a Piece of Cake After Horizontal and Vertical Cuts  GREEDY ALGO
Medium

985

229

Add to List

Share
You are given a rectangular cake of size h x w and two arrays of integers horizontalCuts and verticalCuts where:

horizontalCuts[i] is the distance from the top of the rectangular cake to the ith horizontal cut and similarly, and
verticalCuts[j] is the distance from the left of the rectangular cake to the jth vertical cut.
Return the maximum area of a piece of cake after you cut at each horizontal and vertical position provided in the arrays horizontalCuts and verticalCuts. Since the answer can be a large number, return this modulo 109 + 7.

 

Example 1:


Input: h = 5, w = 4, horizontalCuts = [1,2,4], verticalCuts = [1,3]
Output: 4 
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green piece of cake has the maximum area.
Example 2:


Input: h = 5, w = 4, horizontalCuts = [3,1], verticalCuts = [1]
Output: 6
Explanation: The figure above represents the given rectangular cake. Red lines are the horizontal and vertical cuts. After you cut the cake, the green and yellow pieces of cake have the maximum area.
Example 3:

Input: h = 5, w = 4, horizontalCuts = [3], verticalCuts = [3]
Output: 9
 

Constraints:

2 <= h, w <= 109
1 <= horizontalCuts.length <= min(h - 1, 105)
1 <= verticalCuts.length <= min(w - 1, 105)
1 <= horizontalCuts[i] < h
1 <= verticalCuts[i] < w
All the elements in horizontalCuts are distinct.
All the elements in verticalCuts are distinct.
 */
public class StorageOptimization_LargestCakeCutArea {

	public int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        if (horizontalCuts == null || horizontalCuts.length==0 || verticalCuts ==null || verticalCuts.length==0) return h*w;
        Arrays.sort(horizontalCuts);  
        Arrays.sort(verticalCuts);
        long maxhGap = horizontalCuts[0] - 0;  //If use int it will overflow, use long inside the method
        for (int i=1; i<horizontalCuts.length; i++) {
            maxhGap = Math.max(maxhGap, horizontalCuts[i]-horizontalCuts[i-1]);
            
        }
        maxhGap = Math.max(maxhGap, h-horizontalCuts[horizontalCuts.length-1]);
        //System.out.println(maxhGap);
        long maxvGap = verticalCuts[0] - 0;
        for (int i=1; i<verticalCuts.length; i++) {
            maxvGap = Math.max(maxvGap, verticalCuts[i]-verticalCuts[i-1]);
            
        }
        maxvGap = Math.max(maxvGap, w-verticalCuts[verticalCuts.length-1]);
       // System.out.println(maxvGap);
        return (int)((maxhGap * maxvGap)%1000000007);  
    }
}
