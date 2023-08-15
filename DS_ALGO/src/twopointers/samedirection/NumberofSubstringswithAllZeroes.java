package twopointers.samedirection;

import java.util.Arrays;

/*
 * 1870 · Number of Substrings with All Zeroes
Algorithms
Medium
Accepted Rate
65%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a string str containing only 0 or 1, please return the number of substrings that consist of 0 .

Wechat reply the 【1870】 get the latest frequent Interview questions . (wechat id : jiuzhang15)

1<=|str|<=30000

Example
Example 1:

Input:
"00010011"
Output:
9
Explanation:
There are 5 substrings of "0",
There are 3 substrings of "00",
There is 1 substring of "000".
So return 9
Example 2:

Input:
"010010"
Output:
5
Tags
Same Direction Two Pointers
Two Pointers
String
Company
Google
 */
public class NumberofSubstringswithAllZeroes {

	public int stringCount(String str) {
        // Write your code here.

        int count=0, j=0;

        for (int i=0; i<str.length(); i++) {
          j = Math.max(j, i+1);
          if (str.charAt(i) != '0') continue;
          j = i;
          while(j<str.length() && str.charAt(j)=='0') {
            //count++;  //Slower this way
            j++;
          }

          count += j-i;
        }

        return count;
    }
	
	//Another Implementation of two pointers:
	public int stringCount1(String str) {
        // Write your code here.

      if (str==null || str.length()==0) return 0;
		int n=str.length(), cnt=0, j=0;
		for (int i=0; i<n; i++) {
			j=i;
			while(j<n && str.charAt(j)=='0') {
				cnt++;
				j++;
			}
		}

	   return cnt; 
    }
	
	//Another way not using the template:
	public int stringCountByCalculation(String str) {
		if (str==null || str.length()==0) return 0;
	
	    int i=0, res = 0;
	
	    while(i<str.length()) {
	
	        if (str.charAt(i)=='0') {
	            int j=i;
	
	            while(j<str.length() && str.charAt(j)=='0') {
	                j++;
	            }    
	
	            int n=j-i;
	            res += n*(n+1)/2;   //Calculation by formula
	            i=j;
	            continue;
	        }
	        i++;
	
	    }
	
	    return res;
	}
}

