package onedimension.array.twopointers;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(characterReplacement("AABABBA", 3));
	}

	/**
     * @param s: a string
     * @param k: a integer
     * @return: return a integer
     */
    public static int characterReplacement(String s, int k) {
        // write your code here
        if (s==null || s.length()==0) return 0;
        //if (k==0) return 1;
        
        Map<Character, Integer> counter = new HashMap<>();
        
        int i=0, j=0, maxFreq=0, L=s.length(), count=0, result=0;
        //counter.put(s.charAt(0),0);
        for(i=0; i<L; i++) {
            
            while(j<L&&j-i-maxFreq<=k) {
                
                
                if (j>=L) break;
                count = counter.getOrDefault(s.charAt(j), 0);
                counter.put(s.charAt(j), count+1);
                /*
                if (counter.containsKey(s.charAt(j))) {
                    counter.put(s.charAt(j), counter.get(s.charAt(j))+1);
                } else {
                    counter.put(s.charAt(j), 1);
                }
                */
                maxFreq = Math.max(maxFreq, getMaxCount(counter));
                j++;
                
            }
            
            if (j-i-maxFreq>k) {
                result = Math.max(result, j-i-1);
                
            } else {
                result = Math.max(result, j-i);
            }
            //count = counter.get(s.charAt(i));
            counter.put(s.charAt(i), counter.get(s.charAt(i))-1);
            maxFreq = Math.max(maxFreq, getMaxCount(counter));
        }
        
        return result;
    }
    
    private static int getMaxCount(Map<Character, Integer> map) {
        if (map==null || map.size()==0) return 0;
        return map.entrySet()
            .stream()
            .max((e1, e2)->e1.getValue().compareTo(e2.getValue()))
            .get().getValue();
            //.map(e->e.getValue().intValue())
            //.mapToInt().max().orElseThrow(NoSuchElementException::new);
    }
}
