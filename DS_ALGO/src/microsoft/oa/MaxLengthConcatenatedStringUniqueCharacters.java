package microsoft.oa;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 https://leetcode.com/problems/maximum-length-of-a-concatenated-string-with-unique-characters/
 Given an array of strings arr. String s is a concatenation of a sub-sequence of arr which have unique characters.

Return the maximum possible length of s.

 

Example 1:

Input: arr = ["un","iq","ue"]
Output: 4
Explanation: All possible concatenations are "","un","iq","ue","uniq" and "ique".
Maximum length is 4.
Example 2:

Input: arr = ["cha","r","act","ers"]
Output: 6
Explanation: Possible solutions are "chaers" and "acters".
Example 3:

Input: arr = ["abcdefghijklmnopqrstuvwxyz"]
Output: 26
 

Constraints:

1 <= arr.length <= 16
1 <= arr[i].length <= 26
arr[i] contains only lower case English letters.
Accepted
68,103
Submissions
134,659
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Tesla
|
8

Microsoft
|
7

DiDi
|
2

Oracle
|
2
 */
public class MaxLengthConcatenatedStringUniqueCharacters {
	
	//Using DFS and Integer as bit map
	public int maxLength(List<String> arr) {
        return dfs(arr, 0 , 0 , new HashMap<String, Integer>());
    }
    
    public int dfs(List<String> arr, int usedChars, int index, Map<String, Integer> cache) {
        if (index>=arr.size()) return 0;
        
        String key = usedChars + "," + index;
        if (cache.containsKey(key)) 
            return cache.get(key);
        
        int curChars = 0, ans = 0;
             
        
        boolean valid = true;
        
        String s = arr.get(index);
        
        for (int i=0; i<s.length(); i++) {
            if ( (curChars & 1<<(s.charAt(i)-'a')) == 0 ) {
                
                curChars |= 1<<(s.charAt(i)-'a');
                //System.out.println(Integer.toString(curChars, 2));
            } else {
                valid = false;
                break;
            }
        }
        
        //System.out.println(valid);
        ans = dfs(arr, usedChars, index+1, cache);
        //System.out.println(ans);
        if (valid && (usedChars & curChars) == 0) {
            ans = Math.max(ans, s.length() + dfs(arr, usedChars | curChars, index+1, cache));
        }
        
        cache.put(key, ans);
        return ans;
    }
    
    //Using BitSet and BFS/queue:
    
    public int maxLength1(List<String> arr) {
        
        BitSet init = new BitSet(26);
        
        List<BitSet> v = new ArrayList<>();
        v.add(init);
        int max = 0;
        
        for (String str : arr) {
            BitSet cur = new BitSet(26);
            boolean valid = true;
            for (int i=0; i<str.length(); i++) {
                if (cur.get(str.charAt(i)-'a')) {
                    valid = false;
                    break;
                }
                cur.set(str.charAt(i)-'a');
            }
            
            if (!valid) continue;
            //System.out.println(cur);
            
            //for (BitSet bs : v) {  //This approach will cause java ConcurrentModificationException
            for (int i=v.size()-1; i>=0; i--) {
                BitSet bs = v.get(i);
                if (cur.intersects(bs)) {
                    continue;
                } else {
                    BitSet tmp = new BitSet(26);
                    tmp.or(cur);
                   // System.out.println(tmp);
                    tmp.or(bs);   
                    //System.out.println(tmp.cardinality());
                    v.add(tmp);
                    
                    max = Math.max(max, tmp.cardinality());
                }
            }
        }
        
        return max;
    }
}
