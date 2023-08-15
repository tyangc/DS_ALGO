package onedimension.array.twopointers.samedirection;

import java.util.HashMap;
import java.util.Map;

public class LongestSubstringKDistChar {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(lengthOfLongestSubstringKDistinct("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16));
		System.out.println(lengthOfLongestSubstringKDistinct2("eqgkcwGFvjjmxutystqdfhuMblWbylgjxsxgnoh", 16));
	}

	public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        // write your code here
        if (s==null || s.length()==0 || k==0) return 0;
        if (s.length()<=k) return s.length();
        //if (k==0 && s!=null)return 0;
        int i=0, j=0;
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        char[] chars = s.toCharArray();
        int n = chars.length;
        for (i=0; i<n; i++) {
            //j=i;
            
            while(j<n && map.size()<k) {
                
                if (j<n) {
                    //set.add(chars[j]);
                	if (map.containsKey(chars[j])) {
                		map.put(chars[j], map.get(chars[j])+1);
                	} else {
                		map.put(chars[j], 1);
                	}
                }
                res = Math.max(res, j-i);
                j++;
            }
            /*
            if (j>n) {
                break;
            }*/
            
            
            
            //if (j>n) j=n;
            res = Math.max(res, j-i);
            
            System.out.println(i + "|" + j + "|" + map.size() );
            
            if (map.get(chars[i])==1) {
            	map.remove(chars[i]);
            } else {
            	
            	map.put(chars[i], map.get(chars[i])-1);
            }
            
            /*
            set.remove(chars[i]);
            while(i<chars.length-1 && chars[i]==chars[i+1]) {
            	i++;
            }*/
        }
        
        
        return res;
    }
	
	public static int lengthOfLongestSubstringKDistinct2(String s, int k) {
	    int result = 0;
	    int i=0;
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	 
	    for(int j=0; j<s.length(); j++){
	        char c = s.charAt(j);
	        if(map.containsKey(c)){
	            map.put(c, map.get(c)+1);
	        }else{
	            map.put(c, 1);
	        }
	 
	        if(map.size()<=k){
	            result = Math.max(result, j-i+1); 
	        }else{
	            while(map.size()>k){
	                char l = s.charAt(i);
	                int count = map.get(l);
	                if(count==1){
	                    map.remove(l);
	                }else{
	                    map.put(l, map.get(l)-1);
	                }
	                i++;
	            }
	        }
	 
	    }
	 
	    return result;
	}
}
