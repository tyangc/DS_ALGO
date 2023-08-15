package twodimension;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
	public List<Integer> spiralOrder(int[][] matrix) {
        // write your code here
        List<Integer> ret = new ArrayList<>();
        if (matrix==null || matrix.length==0 || matrix[0].length==0) return ret;

        int x=0, y=0, n=matrix.length, m=matrix[0].length;

        while(n>0 && m>0) {

            if (n==1) {
                for (int i=0; i<m; i++) {
                    ret.add(matrix[x][y++]);
                }
                break;
            }    

            if (m==1) {
                for (int i=0; i<n; i++) {
                    ret.add(matrix[x++][y]);
                }
                break;
            }

            for (int i=0; i<m-1; i++) {
                ret.add(matrix[x][y++]);
                
            } 

            for (int i=0; i<n-1; i++ ) {
                ret.add(matrix[x++][y]);
            }

            for (int i=0; i<m-1; i++) {
                ret.add(matrix[x][y--]);
            }

            for (int i=0; i<n-1; i++) {
                ret.add(matrix[x--][y]);
            }
            x++;
            y++;
            n=n-2;
            m=m-2;
            
        }
        return ret;
    }
    
}
