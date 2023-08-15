package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.*;

public class EncryptedNumber_PascalTriangle {

	public static void main(String[] args) {
		System.out.println(getEncryptedNumber(new int[] {1,2,3,4}));
		System.out.println(getEncryptedNumber(new int[] {4,5,6,7}));
	}
	
	
	private static String getEncryptedNumber(int[] num) {
		if (num == null || num.length <2) return "";
		int[] res = new int[2];
		/*
		dfs(num, res);
		
		return ""+res[0]+res[1]; // String.valueOf(res[0]) + String.valueOf(res[1]);
		*/
		
		List<Integer> lst = Arrays.stream(num).boxed().collect(Collectors.toList());
		
		List<Integer> p = lst, tmp = null;
		while(p.size()>2) {
			tmp = new ArrayList<>();
			for (int i=0; i<p.size()-1; i++) {
				tmp.add((p.get(i) + p.get(i+1))%10);
			}
			
			p = tmp;
		}
		
		return p.get(0) + "" + p.get(1);
	}
	
	//ANother way of use dfs
	private static void dfs(int[] num, int[] res) {
		if (num.length == 2) {
			res[0] = num[0]; //num[0]%10;
			res[1] = num[1]; //num[1]%10;
			return;
		}
		
		int len = num.length;
		int[] next = new int[len-1];
		
		for (int i=1; i<len; i++) {
			next[i-1] = (num[i-1] + num[i])%10;
		}
		
		dfs(next, res);
	}
}
