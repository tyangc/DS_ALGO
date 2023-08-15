package twopointers.slidingwindow;

import java.util.HashMap;
import java.util.Map;

/*
 1246. Longest Repeating Character Replacement

Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Example
Example1

Input:
"ABAB"
2
Output:
4
Explanation:
Replace the two 'A's with two 'B's or vice versa.
Example2

Input:
"AABABBA"
1
Output:
4
Explanation:
Replace the one 'A' in the middle with 'B' and form "AABBBBA".
The substring "BBBB" has the longest repeating letters, which is 4.
Notice
Both the string's length and k will not exceed 10^4.
 */
public class LongestRepeatingCharacterReplacement {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	 /**
     * @param s: a string
     * @param k: a integer
     * @return: return a integer
     */
    public int characterReplacement(String s, int k) {
        if (s==null ||s.length()==0) return 0;
        //if (k==0) return 
        int l = s.length();
        if (k>=l) return s.length();

       int j=0, maxFreq=0, ans = 1, count=0;
        Map<Character, Integer> map = new HashMap<>();

        for (int i=0; i<l; i++) {

            while(j<l && j-i-maxFreq<=k) {
                count = map.getOrDefault(s.charAt(j), 0)+1;
                
                map.put(s.charAt(j), count);
                System.out.println(map);
                maxFreq = Math.max(maxFreq, count);     
                j++;
            }

            ans = Math.max(ans, j-i-maxFreq>k ? j-i-1 : j-i);

            map.put(s.charAt(i), map.get(s.charAt(i))-1);
        }
        return ans;
    }
}
