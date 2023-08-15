package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ItemsInContainers {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Integer> startIndices = IntStream.of(1,1).boxed().collect(Collectors.toList()); // new ArrayList<>();
		List<Integer> endIndices = IntStream.of(5,17).boxed().collect(Collectors.toList());//new ArrayList<>();
		
		System.out.println(numberOfItems("|**||*|*|*|||***|", startIndices, endIndices));
		//System.out.println(numberOfItems("|**|*|*", startIndices, endIndices));
	}

	//use string replacement
	public static List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
		List<Integer> res = new ArrayList<>();
		
		if (s==null || s.length()==0 || startIndices.size()!=endIndices.size() ) return res;
		
		for (int i : endIndices) {
			if (i>=s.length()) return res;
		}
		
		int n = startIndices.size();
		
		for (int i=0; i<n; i++) {
			int cnt = getCount2Pointers(s, startIndices.get(i)-1, endIndices.get(i)-1);
			//int cnt = getCount(s, startIndices.get(i)-1, endIndices.get(i)-1);
			//System.out.println("cnt: " + cnt);
			res.add(cnt);
		}
		
		return res;
	}
	
	private static int getCount(String s, int start, int end) {
		//System.out.println(start + "|" + end);
		while (start<end && s.charAt(start)=='*') {
			start++;
		}
			
		while (start<end && s.charAt(end)=='*') {
			end--;
		}
		
		//System.out.println(start + "|" + end);
		if (start>=end) return 0;
		//String tmp = s.substring(start, end+1); 
		//System.out.println("tmp: " + tmp);
		String star = s.substring(start, end+1).replace("|", ""); //Can not use s.replace('|', '') or s.replace('|', '\0');
		//String star = s.substring(start, end+1).replace('|', '\0'); 
		//System.out.println("star: " + star);
		return star.length();
	}
	
	//Using two pointers to count the "*"
	private static int getCount2Pointers(String s, int start, int end) {
		System.out.println(start + "|" + end);
		while (start<end && s.charAt(start)=='*') {
			start++;
		}
			
		while (start<end && s.charAt(end)=='*') {
			end--;
		}
		
		//System.out.println(start + "|" + end);
		if (start>=end) return 0;
		//String tmp = s.substring(start, end+1); 
		//System.out.println("tmp: " + tmp);
		//String star = s.substring(start, end+1).replace("|", ""); //Can not use s.replace('|', '') or s.replace('|', '\0');
		//String star = s.substring(start, end+1).replace('|', '\0'); 
		//System.out.println("star: " + star);
		//return star.length();
		int  res=0, j=0;
		for (int i=start;i<=end; i++ ) {
			 if (s.charAt(i) == '*') {
				 j = i;
			 
				 while(j<=end && s.charAt(j)=='*') {
						j++;
				 }
				 if (j<end) {
					 res += j-i;
					 i = j;
				 }
				 else {
					 res += end-i;
					 break;
				 }
			 }
		}
					
				
		return res;	
	}
	
	//use prefix-sum like array cache
	
	public static List<Integer> numberOfItems1(String s, List<Integer> startIndices, List<Integer> endIndices) {
		List<Integer> res = new ArrayList<>();
		
		if (s==null || s.length()==0 || startIndices.size()!=endIndices.size()) return res;
		for (int i : endIndices) {
			if (i>=s.length()) return res;
		}
		
		int n = startIndices.size(), cnt=0;
		
		int[] nums = new int[s.length()];
		
		for (int i=0; i<nums.length; i++) { 
			if (s.charAt(i)=='*') cnt++;
			nums[i] = cnt;
		}
		//System.out.println(Arrays.toString(nums));
		for (int i=0; i<n; i++) {
			
			//System.out.println("cnt: " + cnt);
			res.add(getCount1(s, startIndices.get(i)-1, endIndices.get(i)-1, nums));
		}
		
		return res;
	}
	
	private static int getCount1(String s, int start, int end, int[] nums) {
		//System.out.println(start + "|" + end);
		while (start<end && s.charAt(start)=='*') {
			start++;
		}
			
		while (start<end && s.charAt(end)=='*') {
			end--;
		}
		
		//System.out.println(start + "|" + end);
		if (start>=end) return 0;
		//String tmp = s.substring(start, end+1); 
		//System.out.println("tmp: " + tmp);
		
		//String star = s.substring(start, end+1).replace("|", "");
		//System.out.println("star: " + star);
		return nums[end] - nums[start];
	}
}
