package twodimension;
/*
 * 381 · Spiral Matrix II
Algorithms
Medium
Accepted Rate
45%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an integer n, generate a square matrix filled with elements from 1 to n^2n 
2
  in spiral order.

(The spiral rotates clockwise from the outside to the inside, referring to examples)

Wechat reply the 【381】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

Example
Example 1:

input: 2
output:
[
  [1, 2],
  [4, 3]
]
Example 2:

input: 3
output:
[
  [ 1, 2, 3 ],
  [ 8, 9, 4 ],
  [ 7, 6, 5 ]
]
Tags
Array
Simulation
Related Problems
374
Spiral Matrix
Medium
 */
public class SpiralMatrixII {
	public int[][] generateMatrix(int n) {
        // write your code here
        int[][] arr = new int[n][n];

        int r=n, c=n, x=0, y=0, j=1;

        while(r>0 && c>0) {
            if (r==1) {
                for (int i=0; i<c; i++) {
                    arr[x][y++] = j++;
                }
                break;
            }
            if (c==1) {
                for (int i=0; i<r; i++) {
                    arr[x++][y] = j++;
                }
                break;
            }

            for (int i=0; i<c-1; i++) {
                arr[x][y++] = j++;
            }

            for (int i=0; i<r-1; i++) {
                arr[x++][y] = j++;
            }

            for (int i=0; i<c-1; i++) {
                arr[x][y--] = j++;
            }

            for (int i=0; i<r-1; i++) {
                arr[x--][y] = j++;
            }
            x++;
            y++;
            r = r-2;
            c = c-2;
        }
        return arr;
	} 
}
