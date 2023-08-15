package twopointers.samedirection;
import java.util.Set;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/*
 547. Intersection of Two Arrays

Given two arrays, write a function to compute their intersection.

Example
Example 1:

Input: nums1 = [1, 2, 2, 1], nums2 = [2, 2],
Output: [2].
Example 2:

Input: nums1 = [1, 2], nums2 = [2],
Output: [2].
Challenge
Can you implement it in three different algorithms?

Notice
Each element in the result must be unique.
 */
public class IntersectionTwoArrays {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums1: an integer array
     * @param nums2: an integer array
     * @return: an integer array
     */
    public int[] intersection(int[] nums1, int[] nums2) {
        // write your code here

        Set<Integer> set = new HashSet<>();
        Set<Integer> res = new HashSet<>();

        for (int i : nums1) {
            set.add(i);
        }

        for (int i : nums2) {
            if (set.contains(i)) res.add(i);
        }

        return  res.stream().mapToInt(Integer::intValue).toArray();
    }
    
    public int[] intersectionTwoPointers(int[] nums1, int[] nums2) {
        // write your code here

        int i=0, j=0;
        List<Integer> res = new ArrayList<>();

        Arrays.sort(nums1);
        Arrays.sort(nums2);

        while(i<nums1.length && j<nums2.length) {

            if (nums1[i] > nums2[j]) {
                j++;
            } else if (nums1[i] < nums2[j]) {
                i++;
            } else {
                if (res.size()==0 || res.get(res.size()-1)!=nums1[i]) {
                    res.add(nums1[i]);
                }
                i++;
                j++;
            }

        }

        return  res.stream().mapToInt(Integer::intValue).toArray();
    }
}
