package binarysearch;
/*
 600 · Smallest Rectangle Enclosing Black Pixels

Hard
Accepted Rate
38%

DescriptionSolutionNotesDiscussLeaderboard
Description
An image is represented by a binary matrix with 0 as a white pixel and 1 as a black pixel. The black pixels are connected, i.e., there is only one black region. Pixels are connected horizontally and vertically. Given the location (x, y) of one of the black pixels, return the area of the smallest (axis-aligned) rectangle that encloses all black pixels.

Example
Example 1:

Input：["0010","0110","0100"]，x=0，y=2
Output：6
Explanation：
The upper left coordinate of the matrix is (0,1), and the lower right coordinate is (2,2).
Example 2:

Input：["1110","1100","0000","0000"], x = 0, y = 1
Output：6
Explanation：
The upper left coordinate of the matrix is (0, 0), and the lower right coordinate is (1,2).
 */

public class SmallestRectangleEnclosingBlackPixels {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minArea(new char[][] {{'0','0','1','0'},{'0','1','1','0'},{'0','1','0','0'}}, 0, 2));
	}

	/**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public static int minArea(char[][] image, int x, int y) {
        // write your code here
        
        if (image==null) return 0;
        
        int m=image.length;
        int n = image[0].length;
        
        
        int left = findUpLeftMost(image, 0, y, false);
        int up = findUpLeftMost(image, 0, x, true);
        int right = findDownRightMost(image, y, n-1, false);
        int down = findDownRightMost(image, x, m-1, true);
        
        return (right-left+1)*(down-up+1);
        
    }
    
    private static int findUpLeftMost(char[][] image, int start, int end, boolean isRow) {
        
        while(start+1<end) {
            int mid = start - (start-end)/2;
            if (hasPixel(image, mid, isRow)) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (hasPixel(image, start, isRow)) return start;
        return end;
    }
    
    private static int findDownRightMost(char[][] image, int start, int end, boolean isRow){
        while(start+1<end) {
            int mid = start - (start-end)/2;
            if (hasPixel(image, mid, isRow)) {
                start = mid;
            } else {
                end = mid;
            }
        }
        System.out.println(start + "|" + end);
        if (hasPixel(image, end, isRow)) return end;
        return start;
    }
    
    private static boolean hasPixel(char[][] image, int x, boolean isRow) {
        int m=image.length;
        int n = image[0].length;
        //boolean result = false;
        
        if (isRow) {
            for (int i=0; i<n; i++) {
                if (image[x][i]=='1') return true;
            }
            return false;
        } else {
            for (int i=0; i<m; i++) {
                if (image[i][x]=='1') return true;
            }
            return false;
        }
        
    }
    
    
    /**
     * @param image: a binary matrix with '0' and '1'
     * @param x: the location of one of the black pixels
     * @param y: the location of one of the black pixels
     * @return: an integer
     */
    public int minAreaAnotherWay(char[][] image, int x, int y) {
        // write your code here

        int n=image.length, m=image[0].length;

        return (getLargestRow(image, x, n-1) - getSmallestRow(image, 0, x) + 1) * (getLargestCol(image, y, m-1) - getSmallestCol(image, 0, y) + 1);
    }


    int getSmallestRow(char[][]image, int start, int end) {
        while(start+1<end) {
          int mid = start + (end-start)/2;

          if (rowHasBlack(image, mid)) {
            end = mid;
          } else {
            start = mid;
          }

        }

        if (rowHasBlack(image, start)) return start;
        else return end;
    }

    int getLargestRow(char[][]image, int start, int end) {
        while(start+1<end) {
          int mid = start + (end-start)/2;

          if (rowHasBlack(image, mid)) {
            start = mid;
          } else {
            end = mid;
          }

        }

        if (rowHasBlack(image, end)) return end;
        else return start;
    }

    int getSmallestCol(char[][]image, int start, int end) {
        while(start+1<end) {
          int mid = start + (end-start)/2;

          if (colHasBlack(image, mid)) {
            end = mid;
          } else {
            start = mid;
          }

        }

        if (colHasBlack(image, start)) return start;
        else return end;
    }

    int getLargestCol(char[][]image, int start, int end) {
        while(start+1<end) {
          int mid = start + (end-start)/2;

          if (colHasBlack(image, mid)) {
            start = mid;
          } else {
            end = mid;
          }

        }

        if (colHasBlack(image, end)) return end;
        else return start;
    }

    boolean rowHasBlack(char[][] image, int row) {
      for (int i=0; i<image[row].length; i++) {
        if (image[row][i]=='1') return true;
      }
      return false;
    }

    boolean colHasBlack(char[][] image, int col) {
      for (int i=0; i<image.length; i++) {
        if (image[i][col]=='1') return true;
      }
      return false;
    }
}
