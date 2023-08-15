package twopointers.samedirection;

import java.util.HashMap;
import java.util.Map;

/*
 * https://www.lintcode.com/problem/1246/
 * 1246 · Longest Repeating Character Replacement
Algorithms
Medium
Accepted Rate
45%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string that consists of only uppercase English letters, you can replace any letter in the string with another letter at most k times. 
Find the length of a longest substring containing all repeating letters you can get after performing the above operations.

Both the string's length and k will not exceed 10^4.

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
Tags
Hash Table
Same Direction Two Pointers
Two Pointers
Company
Pocket Gems
 */
public class LongestRepeatingCharacterReplacement {
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
                //System.out.println(map);
                maxFreq = Math.max(maxFreq, count);     
                j++;
            }

            ans = Math.max(ans, j-i-maxFreq>k ? j-i-1 : j-i);

            map.put(s.charAt(i), map.get(s.charAt(i))-1);
            maxFreq = getMaxFreq(map);
        }
        return ans;
    }

    private int getMaxFreq(Map<Character, Integer> count) {
      int res = 0;

      for (int i : count.values()) {
        res = Math.max(res, i);
      }
      return res;
    }
    
    //Use Array for counter:
    public int characterReplacement1(String s, int k) {
        if (s==null ||s.length()==0) return 0;
        
        //Map<Character, Integer> map = new HashMap<>();

        int[] map = new int[26];
        int n=s.length(), res=0, j=0, maxFreq=0;
        //map[s.charAt(0)-'A']++;
        for (int i=0; i<n; i++) {
          while(j<n && j-i-maxFreq<=k) {
         
            char c = s.charAt(j);
            map[c-'A']++;
            maxFreq = getMaxFreq1(s, i, j, map);
             j++;
            //System.out.println(i + "|" + j + "|" +  getMaxFreq(s, i, j, map));
          }

          //if (j==n) return j-i+1;

          res = Math.max(res, j-i-maxFreq>k?j-i-1:j-i);
          map[s.charAt(i)-'A']--;
          if (j<n) maxFreq = getMaxFreq1(s, i, j, map);

        }

        return res;
      }

      private int getMaxFreq1(String s, int i, int j, int[] map) {
        int max = 0;

        for (int k=i; k<=j; k++) {
          max = Math.max(max, map[s.charAt(k)-'A']);
        }
        return max;
      }
}
