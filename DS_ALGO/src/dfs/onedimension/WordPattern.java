package dfs.onedimension;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/*
 * https://leetcode.com/problems/word-pattern/
 */
public class WordPattern {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}
	
	
	public boolean wordPattern(String pattern, String s) {
        if (pattern==null || s==null || pattern.length()>s.length()) return false;
        
        String[] arr = s.split(" ");
        
        if (pattern.length()!=arr.length) return false;
        Map<Character, String> map = new HashMap<>();
        Set<String> visited = new HashSet<>();
        
        for (int i=0; i<arr.length; i++) {
            char a = pattern.charAt(i);
            
            if (map.containsKey(a)) {
                if (!arr[i].equals(map.get(a))) return false;
                continue;
            }
            
            if (visited.contains(arr[i])) return false;
            
            map.put(a, arr[i]);
            visited.add(arr[i]);
        }
        
        return true;
        //return dfs(pattern, s, new HashMap<>(), new HashSet<>());
  
    }
    
	//Not suitable for dfs
    private boolean dfs(String p, String s, Map<Character, String> map, Set<String> visited) {
        if (p.length()==0) return s.length()==0;
        
        System.out.println(p+"|"+s);
         
        char a = p.charAt(0);
        if (map.containsKey(a)) {
            String cur = map.get(a);
            if (!s.startsWith(cur)) {
                return false;
            } else {
                if (p.length()==1) return dfs(p.substring(1), "", map, visited);
                else return dfs(p.substring(1), s.substring(cur.length()+1), map, visited);
            }
        }
        String cur = "";
        if (s.contains(" "))cur = s.substring(0, s.indexOf(" ")); 
        else cur = s;
        if (visited.contains(cur)) return false;
        map.put(a, cur);
        
        visited.add(cur);
        if (p.length()==1) return dfs(p.substring(1), "", map, visited);
        else return dfs(p.substring(1), s.substring(cur.length()+1), map, visited);
        
    }

}
