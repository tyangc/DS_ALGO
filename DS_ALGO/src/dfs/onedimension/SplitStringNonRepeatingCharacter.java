package dfs.onedimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SplitStringNonRepeatingCharacter {
	
	public static void main(String[] args) {
		System.out.println(new SplitStringNonRepeatingCharacter().split("adacefgghf"));
	}

	public List<String> split(String str) {
		
		List<List<String>> lst = new ArrayList<>();
		
		Set<Character> set = new HashSet<>();
		
		dfs(str, set, new ArrayList<String>(), lst);
		return lst.get(0);
	}
	
	private void dfs(String str,  Set<Character> set, List<String> tmp, List<List<String>> lst) {
		if (str==null||str.length()==0) {
			lst.add(new ArrayList<>(tmp));
			return;
		}
		
		Set<Character> innerSet = new HashSet<>();
		for (int i=1; i<=str.length(); i++) {
			if (set.contains(str.charAt(i-1))) return;
			innerSet.add(str.charAt(i-1));
			set.addAll(innerSet);
			tmp.add(str.substring(0, i));
			dfs(str.substring(i), set, tmp, lst);
			tmp.remove(tmp.size()-1);
			set.removeAll(innerSet);
			
			
		}
	}
}
