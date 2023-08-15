package amazon;
/*
 * https://leetcode.com/problems/maximum-units-on-a-truck/submissions/
You are also given an integer truckSize, which is the maximum number of boxes that can be put on the truck. You can choose any boxes to put on the truck as long as the number of boxes does not exceed truckSize.

Return the maximum total number of units that can be put on the truck.

 

Example 1:

Input: boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
Output: 8
Explanation: There are:
- 1 box of the first type that contains 3 units.
- 2 boxes of the second type that contain 2 units each.
- 3 boxes of the third type that contain 1 unit each.
You can take all the boxes of the first and second types, and one box of the third type.
The total number of units will be = (1 * 3) + (2 * 2) + (1 * 1) = 8.
Example 2:

Input: boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
Output: 91
 

Constraints:

1 <= boxTypes.length <= 1000
1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
1 <= truckSize <= 106
Accepted
122,218
Submissions
169,532
Seen this question in a real interview before?

Yes

No

 */

import java.util.Arrays;
import java.util.Comparator;

public class LoadTruckWithBoxes {
		
	public int maximumUnits(int[][] boxTypes, int truckSize) {
        /*
        List<int[]> boxLists = new ArrayList<>();
        for (int[] boxType : boxTypes) {
            //System.out.println(Arrays.toString(boxType));
            //boxLists.add(Arrays.copyOf(boxType, boxType.length));
            boxLists.add(boxType);
        }
        */
        
        /*
        Collections.sort(boxLists, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        */
        
        Arrays.sort(boxTypes, new Comparator<int[]>(){
            public int compare(int[] a, int[] b) {
                return b[1] - a[1];
            }
        });
        
        /*
        for (int[] box : boxLists) {
            System.out.println(Arrays.toString(box));
        }
        */
        
        /*
        int sum = truckSize, units = 0, i=0, n = boxLists.size();
        
        while(i<n) {
            int[] box = boxLists.get(i);
            if (sum>=box[0]) {
                sum -=box[0];
                units += box[0]*box[1];
            } else {
                units += sum*box[1];
                break;
            }
            i++;
        }
        */
        int sum = truckSize, units = 0, i=0, n = boxTypes.length;
        
        while(i<n) {
            int[] box = boxTypes[i];
            if (sum>=box[0]) {
                sum -=box[0];
                units += box[0]*box[1];
            } else {
                units += sum*box[1];
                break;
            }
            i++;
        }
        return units;
    }
}
