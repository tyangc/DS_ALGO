package amazon;

import java.util.ArrayList;
import java.util.List;

/*
 * Find all combinations of numbers sum to a target
 * target 4
 * [1,3]
 * [2,2],
 * [1,1,2]
 * [1,1,1,1]
 * 
 */

//INTERNALLY: relationship between sorting and removing duplicated combinations
public class AllCombinationSumToTarget {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<List<Integer>> res = getCombinations(4);
		
		for (List<Integer> lst : res) {
			System.out.println(lst);
		}
	}
	
	private static List<List<Integer>> getCombinations(int num) {
		/*
		List<Integer> res = new ArrayList<>();
		if (num == 1) {
			res.add(1);
			return res;
		}
		if (num == 2) {
			res.add(1);
			res.add(1);
			return res;
		}
		*/
		return dfs(num, 1, 0);
	}
	
	private static List<List<Integer>> dfs(int target, int start, int curSum) {
		
		if (target == curSum) {
			List<List<Integer>> res = new ArrayList<>();
			List<Integer> tmp = new ArrayList<>();
			res.add(tmp);
			return res;
		}
		List<List<Integer>> res = new ArrayList<>();
		for (int i=start; i<target; i++) {
			if (curSum+i<=target) {  //This is very critical , otherwise it will cause java.lang.StackOverflowError
				
				//System.out.println(curSum+i); //How to trouble shooting the StackOverFlow
				for (List<Integer> list : dfs(target, i, curSum+i)) {
					List<Integer> tmp = new ArrayList<>(list);
					tmp.add(0, i);
					res.add(tmp);
				}
			}
		}
		
		return res;
		
		/*
		
		List<List<Integer>> res = new ArrayList<>();
		List<Integer> tmp = new ArrayList<>();
		if (num ==0) {
			res.add(tmp);
			return res;
		}
		if (num == 1) {
			tmp.add(1);
			res.add(tmp);
			return res;
		}
		if (num == 2) {
			tmp.add(1);
			tmp.add(1);
			res.add(tmp);
			return res;
			
		}
		
		
		for (int i=1; i<=num/2; i++) {
			List<List<Integer>> lists = dfs(num-i);
			
			for (List<Integer> lst : lists) {
				List<Integer> temp = new ArrayList<>(lst);
				temp.add(0, i);
				res.add(temp);
			}
		}
		
		List<Integer> temp = new ArrayList<>();
		temp.add(num);
		res.add(temp);
		return res;
		*/
		
	}

}
