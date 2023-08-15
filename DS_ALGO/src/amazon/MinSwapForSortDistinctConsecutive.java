package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javafx.util.Pair;

public class MinSwapForSortDistinctConsecutive {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(minSwap2(new int[] {4, 3, 2, 1}));
		System.out.println(minSwap2(new int[] {2, 4, 5, 1, 3}));
		System.out.println(minSwap2(new int[] {4, 5, 1, 3, 2}));
		//System.out.println(minSwap1(new int[] {4, 3, 2, 1}));
		//System.out.println(minSwap1(new int[] {2, 4, 5, 1, 3}));
	}
	
	public static int minSwap(int[] nums) {
		if (nums==null || nums.length==0) return 0;
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i=0; i<nums.length; i++) {
			map.put(nums[i], i);
		}
		System.out.println(map);
		Arrays.sort(nums);
		int n = nums.length;
		
		boolean[] visited = new boolean[n];
		int ans = 0;
		for (int i=0; i<n; i++) {
			if (visited[i] || map.get(nums[i])==i) continue;
			
			int j=i, cycle_size=0;
			while(!visited[j]) {
				
				visited[j] = true;
				
				j = map.get(nums[j]);
				cycle_size++;
				
			}
			if (cycle_size>0) {
				ans += cycle_size-1;
			}
		}
		
		return ans;
	}
	
	//Using Pair
	//@SuppressWarnings("unchecked")
	public static int minSwap1(int[] nums) {
		if (nums==null || nums.length==0) return 0;
		
		List<Pair<Integer, Integer>> posArr = new ArrayList<>();
		int len = nums.length;
		for (int i=0; i<len; i++) {
			posArr.add(new Pair<>(nums[i], i));
			
		}
		
		/*
		posArr.sort(new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
				if (p1.getKey().intValue()>p2.getKey().intValue()) {
					return 1;
				} else if (p1.getKey().intValue()==p2.getKey().intValue()) {
					return 0;
					
				} else {
					return -1;
				}
			}
		});
		*/
		
		posArr.sort(new Comparator<Pair<Integer, Integer>>() {
			public int compare(Pair<Integer, Integer> p1, Pair<Integer, Integer> p2) {
				return p1.getKey().compareTo(p2.getKey());
			}
		});
		System.out.println(posArr);
		
		int ans=0;
		boolean[] vis = new boolean[len];
		
		for (int i=0; i<len; i++) {
			if (vis[i] || posArr.get(i).getValue()==i) continue;
			
			int j=i, cycle = 0;
			while(!vis[j]) {
				vis[j] = true;
				
				j = posArr.get(j).getValue();
				cycle++;
			}
			
			if (cycle>0) {
				ans += cycle-1;
			}
		}
		
		return ans;
	}
	
	//Better way, easier to understand
	public static int minSwap2(int[] nums) {
		if (nums==null || nums.length<2) return 0;
		int len = nums.length, cur = 0, swap = 0;
		boolean[] visited = new boolean[len+1];
		
		Map<Integer, Integer> map = new HashMap<>();
		
		for (int i=1; i<=len; i++) {
			map.put(i, nums[i-1]);
		}
		
		System.out.println(map);
		//System.out.println(Arrays.toString(visited));
		for (int i=1; i<=len; i++) {
			if (visited[i]) continue;
			visited[i] = true;
			cur = map.get(i);
			if (cur == i) {
				System.out.println(i + "|" + cur + "|" + nums[i-1]);
				continue;
			} else {
				//cur = i; //map.get(i);
				//System.out.println(cur);
				while(!visited[cur]) {
					visited[cur] = true;
					int nxt = map.get(cur);
					System.out.println(cur);
					cur = nxt;
					swap++;
					//System.out.println(swap);
				}
			}
			
		}
		
		return swap;
		
	}

}
