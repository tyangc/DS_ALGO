package datastructure.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeKSortedArrays {
	public int[] mergekSortedArrays(int[][] arrays) {
        // write your code here

        if (arrays.length == 0 || arrays == null) {
            return new int[]{};
        }

        int k = arrays.length;

        List<Integer> res = new ArrayList<>();
        
        Queue<Element> heap = new PriorityQueue<>(k, new Comparator<Element>(){
            public int compare(Element a, Element b) {
                return a.val - b.val;
            }
        });

        for (int i = 0; i<k; i++) {
            if (arrays[i].length==0) continue;
            heap.offer(new Element(arrays[i][0], i, 0));
        }

        while(!heap.isEmpty()) {
            Element cur = heap.poll();
            res.add(cur.val);
            int x = cur.row;
            int y = cur.col;
            int[] arr = arrays[x];
            if (y+1>=arr.length) {
                continue;
              
            } 
            heap.offer(new Element(arr[y+1], x, y+1));
        }

        return res.stream().mapToInt(i->i).toArray();//res.toArray(new int[k]);

    }

    class Element {
        int val, row, col;

        public Element(int v, int r, int c) {
            val = v;
            row = r;
            col = c;
        }
    }
    
    //Also other ways :  自顶向下的分治法, 自底向上两两归并
    public int[] mergekSortedArraysDivideConquer(int[][] arrays) {
        // write your code here
        return mergeRange(arrays, 0, arrays.length-1);  //NOT arrays.length !!!
        
    }

    int[] mergeRange(int[][] arrays, int start, int end) {
        if (start == end) return arrays[start];

        int mid = start + (end-start)/2;
        int[] left = mergeRange(arrays, start, mid);
        int[] right = mergeRange(arrays, mid+1, end);
        return merge(left, right);
    }

    private int[] merge(int[] left, int[] right) {

        int m = left.length;
        int n = right.length;

        int[] ret = new int[m+n]; 
        int i=0, j=0, k=0;

        while(i<left.length && j<right.length) {
            if (left[i] < right[j]) {
                ret[k++] = left[i++];
            } else {
                ret[k++] = right[j++];
            }
        }

        while(i<left.length) {
            ret[k++] = left[i++];
        }

        while(j<right.length) {
            ret[k++] = right[j++];
        }

        return ret;
    }
    
    public int[] mergekSortedArraysBottomToTop(int[][] arrays) {
        // write your code here

        List<int[]> list = Arrays.asList(arrays); //!!!note

        while(list.size()>1) {
            int len = list.size();
            List<int[]> newList = new ArrayList<>();    
            for (int i=0; i+1<len; i=i+2) {
                newList.add(merge(list.get(i), list.get(i+1)));
            }
            if (len%2==1) {
                newList.add(list.get(len-1));
            }
            list = newList;
        }  
        return list.get(0);
    }
}
