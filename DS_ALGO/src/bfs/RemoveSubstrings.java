package bfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/*
 624 · Remove Substrings
Amazon:
Medium
Accepted Rate
38%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string s and a set of n substrings. You are supposed to remove every instance of those n substrings from s so that s is of the minimum length and output this minimum length.

Example
Example 1:

Input:
"ccdaabcdbb"
["ab","cd"]
Output:
2
Explanation: 
ccdaabcdbb -> ccdacdbb -> cacdbb -> cabb -> cb (length = 2)
Example 2:

Input:
"abcabd"
["ab","abcd"]
Output:
0
Explanation: 
abcabd -> abcd -> "" (length = 0)
 */
public class RemoveSubstrings {

	//This one won't exhaust all the possible left over combinations
	public int minLength(String s, Set<String> dict) {
        // write your code here
        Queue<String> que = new LinkedList<>();
        Set<String> hash = new HashSet<>();
        int min = s.length();
        que.offer(s);
        hash.add(s);

        while(!que.isEmpty()) {
          String cur = que.poll();
          for (String str : dict) {
            int found = cur.indexOf(str);
            while(found>-1) {
              //String newStr = cur.replace(str, "");  //This won't work
              String newStr = cur.substring(0, found) + cur.substring(found + str.length(), cur.length());
              min = Math.min(min, newStr.length());
              
              if (!hash.contains(newStr)) {
                que.offer(newStr);
                hash.add(newStr);
              }
              found = cur.indexOf(str, found+1);   //Can not be found = newStr.indexOf(str);  that  is not bfs
            }
          }
        }
        return min;
    }
	
	public int minLengthPartial(String s, Set<String> dict) {
        // write your code here

        boolean found = false; 
        String tmp = s;

        do {
          found = false;
          for (String str : dict) {
            while (tmp.indexOf(str)>-1) {
              found = true;
              tmp = tmp.replace(str, "");
            }
          }
        } while(found && tmp.length()>0);

        return tmp.length();
    }
	
	
}
