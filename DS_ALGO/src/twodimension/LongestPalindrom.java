package twodimension;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class LongestPalindrom {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(longestPalindrome1("abccccdd"));
	}

	/**
     * @param s: a string which consists of lowercase or uppercase letters
     * @return: the length of the longest palindromes that can be built
     */
    public static int longestPalindrome1(String s) {
        // write your code here
        if (s==null || s.length()==0) return 0;
        Map<Character, Long> map = s.chars().mapToObj(c->(char)c)
                .collect(Collectors.groupingBy(c->c, Collectors.counting()));
        System.out.println(map.toString());
        Optional<Long> med = map.entrySet()
        //.entrySet()
        .stream()
        .map(Map.Entry::getValue)
        .reduce((a, b)->a + b.intValue()/2);
    
        int res = med.get().intValue();
        System.out.println(res);
         
        return res*2==s.length()? s.length() : res*2+1;
    }
    
    public static int longestPalindrome(String s) {
        // write your code here
        if (s==null || s.length()==0) return 0;
            
        
        Map<Character, Integer> map = new HashMap<>();
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c)+1);
            } else {
                map.put(c, 1);
            }
        }
        
        int sum = 0;
        for (int cnt : map.values()) {
            sum+=cnt/2;
        }
        
        return sum*2==s.length()?s.length():sum*2+1;
    }
}
