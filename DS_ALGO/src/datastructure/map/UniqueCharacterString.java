package datastructure.map;

import java.util.HashMap;
import java.util.Map;

/*
 209 · First Unique Character in a String
Easy
Accepted Rate
59%

DescriptionSolutionNotesDiscussLeaderboard
Description
Find the first unique character in a given string. You can assume that there is at least one unique character in the string.

Example
Example 1:
	Input: "abaccdeff"
	Output:  'b'
	
	Explanation:
	There is only one 'b' and it is the first one.


Example 2:
	Input: "aabccd"
	Output:  'b'
	
	Explanation:
	'b' is the first one.

Tags
Company
Google
Amazon
Microsoft
Bloomberg
Related Problems
1233
Sort Characters By Frequency
Medium
960
First Unique Number in Data Stream II
Medium
157
Unique Characters
Easy
 */
public class UniqueCharacterString {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param str: str: the given string
     * @return: char: the first unique character in a given string
     */
    public char firstUniqChar(String str) {
        // Write your code here
        char[] chars = str.toCharArray();

        Map<Character, Integer> map = new HashMap<>();
        
        for (char x : chars) {

          if (!map.containsKey(x)) {
            
            map.put(x, 1);
          } else {
            map.put(x, map.get(x)+1);
          }
            
        }

        for (Map.Entry e : map.entrySet()) {
          if (((Integer)e.getValue()).intValue()==1) {
            return ((Character)e.getKey()).charValue();
          }
        }

        return ' ';
    }
}
