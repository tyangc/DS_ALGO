package twopointers.samedirection;

import java.util.HashMap;

/*
 * 1375 · Substring With At Least K Distinct Characters
Algorithms
Medium
Accepted Rate
46%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string S with only lowercase characters.

Return the number of substrings that contains at least k distinct characters.

Wechat reply 【1375】 get the latest requent Interview questions . (wechat id : jiuzhang15)

10 ≤ length(S) ≤ 1,000,00010≤length(S)≤1,000,000
1 ≤ k ≤ 261≤k≤26
Example
Example 1:

Input: S = "abcabcabca", k = 4
Output: 0
Explanation: There are only three distinct characters in the string.
Example 2:

Input: S = "abcabcabcabc", k = 3
Output: 55
Explanation: Any substring whose length is not smaller than 3 contains a, b, c.
    For example, there are 10 substrings whose length are 3, "abc", "bca", "cab" ... "abc"
    There are 9 substrings whose length are 4, "abca", "bcab", "cabc" ... "cabc"
    ...
    There is 1 substring whose length is 12, "abcabcabcabc"
    So the answer is 1 + 2 + ... + 10 = 55.
Tags
Same Direction Two Pointers
Two Pointers
String
 */

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class SubstringWithAtLeastKDistinctCharacters {

	//j goes back, so this way should be slower
	public static long kDistinctCharacters(String s, int k) {
		if (s==null || s.length()==0 || k > s.length()) return 0L;
		
		Set<Character> set = new HashSet<>();
		long res = 0;
		
		for (int i=0; i<s.length(); i++) {
			int j=i;
			set.clear();
			
			while(j<s.length()) {
				set.add(s.charAt(j));
				
				if (set.size()==k) {
					res += s.length()-j;
					break;
				}
				j++;
			}
		}
		
		return res;
	}
	
	public static void main(String[] args) {
		System.out.println(kDistinctCharacters("abcabcabca", 4));
		
		System.out.println(kDistinctCharacters("abcabcabcabc", 3));
	}
	
	//Same direction two pointers
	
	public long kDistinctCharacters1(String s, int k) {
        // Write your code here
        if (s==null || s.length()==0 || k>s.length()) return 0L;

        long res = 0;

        Map<Character, Integer> count = new HashMap<>();
        int j=0, len = s.length();

        for (int i=0; i<len; i++) {
          //j=Math.max(j, i+1);  
          while(j<len && count.size()<k) {
            count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0)+1);
            j++;

          }
          //System.out.println(len-j);
          if (count.size()>=k) res += len-j+1;   //This should be res += len - 1 - (j-1) + 1
          //res += len-j; is Wrong: MISSING Judgement!!! 
          
          //System.out.println(res);

          if (count.get(s.charAt(i))==1) {
            count.remove(s.charAt(i));
          } else {
            count.put(s.charAt(i), count.get(s.charAt(i))-1);
          }

        }
        return res;
    }
	
	//Better implementation: using a distinctChars counter: clean to think
	
	public long kDistinctCharacters2(String s, int k) {
        // Write your code here
        if (s==null || s.length()==0 || k>s.length()) return 0L;

        long res = 0;
        int n=s.length(), j=0;

        Map<Character, Integer> char2Count = new HashMap<>();
        int distinctChars = 0;

        for (int i=0; i<n; i++) {
          while(j<n && distinctChars<k) {
            
            char c = s.charAt(j);
            if (!char2Count.containsKey(c) || char2Count.get(c) == 0) {  //or use int numOfThisChar = char2Count.getOrDefault(c, 0);
              char2Count.put(c, 1);
              distinctChars++;
            } else {
              char2Count.put(c, char2Count.get(c)+1);
            }
            j++;
          }

          if (distinctChars>=k) {
            res += n-j+1;
          }

          char old = s.charAt(i);
          char2Count.put(old, char2Count.get(old)-1);
          if (char2Count.get(old) == 0) distinctChars--;
        }

        return res;
    }
	
}
