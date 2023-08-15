package binarysearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FindRangeInAscendingArray {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> nums = Arrays.stream(new int[] {5,7,7,8,8,10}).boxed().collect(Collectors.toList()); 
				// This won't work :Arrays.asList(new int[] {5,7,7,8,8,10});
		System.out.println(searchRange(nums, 8));
	}

	/**
     * @param nums: the array of integers
     * @param target: 
     * @return: the starting and ending position
     */
    public static List<Integer> searchRange(List<Integer> nums, int target) {
        // Write your code here.
        List<Integer> res = new ArrayList<>();
        if (nums==null || nums.size()==0) {
            res.add(-1);
            res.add(-1);
            return res;
        }
        
        //res.add(findLeft(nums.stream().mapToInt(Integer::intValue).toArray(), target));
        //res.add(findRight(nums.stream().mapToInt(Integer::intValue).toArray(), target));
        
        res.add(findLeft(nums, target));
        res.add(findRight(nums, target));
        
        return res;
    }
    
    private static int findLeft(List<Integer> nums, int target) {
        int start = 0, end = nums.size()-1;
        int mid = start -(start-end)/2;
        
        while(start+1<end) {
        	mid = start -(start-end)/2;
        	
            if (nums.get(mid)>=target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums.get(start)==target){
            
            return start;
        } else if (nums.get(end)==target) {
            return end;
        } else {
            return -1;
        }
    }
    
    private static int findRight(List<Integer> nums, int target) {
        int start = 0, end = nums.size()-1;
        int mid = start -(start-end)/2;
        
        while(start+1<end) {
        	mid = start -(start-end)/2;
            if (nums.get(mid)>=target) {
                end = mid;
            } else {
                start = mid;
            }
        }
        
        if (nums.get(end)==target){
            
            return start;
        } else if (nums.get(start)==target) {
            return end;
        } else {
            return -1;
        }
    }
}
