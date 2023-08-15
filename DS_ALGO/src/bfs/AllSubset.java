package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class AllSubset {
	
	public static void main(String[] args) {
		System.out.println(getSubset1(new int[] {1,2,3}));
	}

	//BFS - appending to existing list
	public static List<List<Integer>> getSubset(int[] nums) {
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> empty = new ArrayList<>();
		res.add(empty);
		if (nums==null || nums.length==0) return res;
		
		//List<Integer> que = new ArrayList<>();
		Arrays.sort(nums);
		int index = 0;
		//que.add(nums[index]);
		
		
		
		while(index<res.size()) {
			List<Integer> tail = res.get(index++);
			System.out.println(tail);
			
			for (int i=0; i<nums.length; i++) {
				
				if ( tail.size() >0 && tail.get(tail.size()-1)>=nums[i]) continue;
				List<Integer> tmp = new ArrayList<>(tail);
				tmp.add(nums[i]);
				res.add(tmp);
				System.out.println(res.size());
			}
			
			//index++;
		}
		return res;
	}
	
	//BFS:  for each digit of add to the previous list in the queue
	public static List<List<Integer>> getSubset1(int[] nums) {
		List<List<Integer>> res = new LinkedList<>();
		List<Integer> empty = new LinkedList<>();
		res.add(empty);
		if (nums==null || nums.length==0) return res;
		
		for (int i=0; i<nums.length; i++) {
			List<List<Integer>> inter = new LinkedList<>();
			for (List<Integer> a : res) {                            //or use int size = res.size(); for (int j=0; j<size; j++) {} to avoid using intermediate List
				List<Integer> tmp = new LinkedList<>(a);
				tmp.add(nums[i]);
				inter.add(tmp);   //res.add(tmp) will cause ConcurrentModificationException
			}
			res.addAll(inter);
		}
		
		return res;
	}
}
