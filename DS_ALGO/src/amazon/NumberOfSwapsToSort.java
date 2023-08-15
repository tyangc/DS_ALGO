package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
import java.util.Scanner;
import java.util.stream.Collectors;
import javafx.util.Pair;


public class NumberOfSwapsToSort {

	static int count=0;
    public static List<Pair<Integer, Integer>> mergeSort(List<Pair<Integer, Integer>> list) {
        if (list.size()<=1) return list;
        
        int mid = list.size()/2;
        
        List<Pair<Integer, Integer>> left = mergeSort(list.subList(0, mid));
        List<Pair<Integer, Integer>> right = mergeSort(list.subList(mid, list.size()));      
        
        return merge(left, right);
        
    }
    
    public static List<Pair<Integer, Integer>> merge(List<Pair<Integer, Integer>> left, List<Pair<Integer, Integer>> right) {
        List<Pair<Integer, Integer>> res = new ArrayList<>();
        
        int l=0, r=0;
        
        while(l<left.size() || r < right.size()) {
            if ( r>=right.size() || (l<left.size() && left.get(l).getValue() <= right.get(r).getValue()) ) {
                res.add(left.get(l));
                count += r;
                l++;        
            } else {
                res.add(right.get(r));
                r++;        
            }
        }
                        
        return res;
    }
    
    public static int numberOfSwapsToSort(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (nums==null || nums.size()<2) return 0;
        
        List<Pair<Integer, Integer>> list = new ArrayList<>();
        
        for (int i=0; i<nums.size(); i++) {
            list.add(new Pair<Integer, Integer>(i, nums.get(i)));
        }
        
        List<Pair<Integer, Integer>> result = mergeSort(list);
        return count;
    }

    //Brutal force - selection sort
    public static int numberOfSwapsToSort1(List<Integer> nums) {
        // WRITE YOUR BRILLIANT CODE HERE
        if (nums==null || nums.size()==0) return 0;
        
        int[] arr = nums.stream().mapToInt(Integer::intValue).toArray();
        
        int ans = 0, len = arr.length;
        
        for (int i=0; i<len-1; i++) {
            int cnt=0;
            for (int j=i+1; j<len; j++) {
                if (arr[i] > arr[j]) cnt++;
            }
            ans += cnt;
        }
        
        return ans;
        
    }

    public static List<String> splitWords(String s) {
        //return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));  //Only for Java 9 above
    	return s.isEmpty() ? new ArrayList<>() : Arrays.asList(s.split(" "));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Integer> nums = splitWords(scanner.nextLine()).stream().map(Integer::parseInt).collect(Collectors.toList());
        scanner.close();
        int res = numberOfSwapsToSort(nums);
        System.out.println(res);
    }
}
