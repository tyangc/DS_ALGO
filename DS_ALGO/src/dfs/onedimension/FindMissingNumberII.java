package dfs.onedimension;
/*
 570 · Find the Missing Number II
Algorithms Medium Accepted Rate 35%

DescriptionSolutionNotesDiscussLeaderboard
Description
To give a random string sequence composed of 1 - n integers, in which an integer is lost, please find it.

n < 100
Data guarantees have only one solution.
if the list that you've found has more than one missing numbers, which could be that you didn't find the correct way to split the string.

Example
Example1

Input: n = 20 and str = 19201234567891011121314151618
Output: 17
Explanation:
19'20'1'2'3'4'5'6'7'8'9'10'11'12'13'14'15'16'18
Example2

Input: n = 6 and str = 56412
Output: 3
Explanation:
5'6'4'1'2
Tags
Related Problems
681
First Missing Prime Number
Medium
633
Find the Duplicate Number
Hard
196
Missing Number
Easy
189
First Missing Positive
Medium
237
Missing Integer
Easy
 */

import java.util.HashSet;
import java.util.Set;

public class FindMissingNumberII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param n: An integer
     * @param str: a string with number from 1-n in random order and miss one number
     * @return: An integer
     */
    public int findMissing2(int n, String str) {
        // write your code here
        int k = String.valueOf(n).length();
        System.out.println(k);
        Set<Integer> map = new HashSet<>();
        Set<Integer> res = new HashSet<>();
        dfs(str, k, n, map, res, 0);

        int sum=0;
        for (int i : res) {
          sum+=i;
        }

        return n*(n+1)/2 - sum;
    }

    private void dfs(String s, int k, int n, Set<Integer> map, Set<Integer> res, int count) {   //Do not need to pass count , only for debugging
        
    	if (s.substring(0,1).equals("0")) return;  //Missing this one will error out for testing cases
    	
    	if (s.length()==0) {
         // System.out.println("to the end: " + map.size());
          if (map.size()==n-1)  {
            //res = new HashSet<>(map);  //This is wrong , can not just change the container address, only can update the content of the container
            res.addAll(map);
          }
          return;
        }
       // System.out.println(map.size());
        

        //System.out.println(count);
        for (int i=1; i<=k; i++) {
          if (s.length()>=i) {
            int cur = Integer.parseInt(s.substring(0, i));
            
            if ( map.contains(cur) || cur>n || cur==0) {
              continue;
 
            } else {
              map.add(cur);
              //System.out.println(cur);
            }
            dfs(s.substring(i), k, n, map, res, count+1);
            map.remove(cur);
          } 
        }
    }
    
}
