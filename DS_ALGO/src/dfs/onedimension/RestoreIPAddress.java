package dfs.onedimension;

import java.util.ArrayList;
import java.util.List;

/*
 426 · Restore IP Addresses
Algorithms Medium Accepted Rate 32%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string containing only digits, restore it by returning all possible valid IP address combinations.

(Your task is to add three dots to this string to make it a valid IP address. Return all possible IP address.)

You can return all valid IP address in any order.

Example
Example 1:

Input: "25525511135"
Output: ["255.255.11.135", "255.255.111.35"]
Explanation: ["255.255.111.35", "255.255.11.135"] will be accepted as well.
Example 2:

Input: "1116512311"
Output: ["11.165.123.11","111.65.123.11"]
Tags
Related Problems
852
IP to CIDR
Easy
17
Subsets
Medium
 */
public class RestoreIPAddress {

	/**
     * @param s: the IP string
     * @return: All possible valid IP addresses
     */
    public List<String> restoreIpAddresses(String s) {
        // write your code here

        List<String> res = new ArrayList<>();
        if (s==null ||s.length()<4 || s.length()>12 ) return res;
        dfs(s, s.length(), new ArrayList<Integer>(), res, 0);
        return res;
    }

    private void dfs(String s, int k,  List<Integer> tmp, List<String> res, int count) {
      if (count==3) {
        if (s.length()<1) return;  //Do forget two side check
        int cur = Integer.parseInt(s);
        System.out.println(cur);
        if (cur<=255) {
          StringBuilder sb = new StringBuilder();
          String test = "";
          for (int i : tmp) {
            sb.append(i+ ".");
            test+=String.valueOf(i);  //SMART way!
          }
          test+=String.valueOf(cur);
          if (test.length()==k) res.add(sb.toString() + cur);
        }
        return;
      }

      for (int i=1; i<=3 && s.length()>=i; i++) {
        String scur = s.substring(0,i);
        int cur = Integer.parseInt(scur);
        if (cur>255 ) continue;
        //if (cur>0 && scur.substring(0,1).equals("0")) continue;
        tmp.add(cur);
        dfs(s.substring(i), k, tmp, res, count+1);
        tmp.remove(tmp.size()-1);
      }
    }
}
