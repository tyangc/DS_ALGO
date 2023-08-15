package amazon;

import java.util.Arrays;

/*
 * https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 * 1010. Pairs of Songs With Total Durations Divisible by 60
Medium

2972

115

Add to List

Share
You are given a list of songs where the ith song has a duration of time[i] seconds.

Return the number of pairs of songs for which their total duration in seconds is divisible by 60. Formally, we want the number of indices i, j such that i < j with (time[i] + time[j]) % 60 == 0.
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 (a+b) % 60=0
⇓
((a % 60)+(b % 60)) % 60=0
⇓
Therefore, either { 
a % 60
b % 60
​
  
=0
=0
​
 , or (a % 60)+(b % 60)=60
~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
Example 1:

Input: time = [30,20,150,100,40]
Output: 3
Explanation: Three pairs have a total duration divisible by 60:
(time[0] = 30, time[2] = 150): total duration 180
(time[1] = 20, time[3] = 100): total duration 120
(time[1] = 20, time[4] = 40): total duration 60
Example 2:

Input: time = [60,60,60]
Output: 3
Explanation: All three pairs have a total duration of 120, which is divisible by 60.
 

Constraints:

1 <= time.length <= 6 * 104
1 <= time[i] <= 500
Accepted
180,974
Submissions
336,357
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Amazon
|
15

Citadel
|
13

Bloomberg
|
5

ServiceNow
|
4

Cisco
|
2

Mathworks
|
2

Salesforce
|
2

Twilio
|
2
We only need to consider each song length modulo 60.
We can count the number of songs with (length % 60) equal to r, and store that in an array of size 60.
 */
public class MusicPair_PairOfSongs {

	public int numPairsDivisibleBy60(int[] time) {
		if (time==null || time.length==0) return 0;
		int[] remain = new int[60];
        int count=0;
        
        for (int t : time) {
            if (t%60==0) {
                count += remain[0];
            } else {
                count += remain[60-t%60];
            }
            
            remain[t%60]++;
        }
        return count;
	}
	
	
	//This one will fail test cases, result difference : 102 vs 103 , don't know why
	public int numPairsDivisibleBy60A(int[] time) {
        if (time==null || time.length==0) return 0;
        
        //Brute Force:  will have : time limit exceeded
        /*
        int count = 0, n = time.length;
        for (int i = 0; i < n; i++) {
            // j starts with i+1 so that i is always to the left of j
            // to avoid repetitive counting
            for (int j = i + 1; j < n; j++) {
                if ((time[i] + time[j]) % 60 == 0) {
                    count++;
                }
            }
        }
        return count;
        */
    
       
        Arrays.sort(time);
        int len = time.length;
        int maxTime = time[len-1];
        int n = len*maxTime/60, ret=0;
        
        for (int i=n; i>0; i--) {
            ret += twoSum(time, i*60);
        }
        return ret;
        
        
        
    }
    
    private int twoSum(int[] arr, int target) {
        int ret = 0, i=0, j=arr.length-1;
        
        while(i<j) {
            if (arr[i] + arr[j] == target) {
                ret++;
                i++;
                j--;
                while(i<j && arr[i] == arr[i-1]) {
                    i++;
                    ret++;
                }
                
                
                while(i<j && arr[j] == arr[j+1]){
                    j--;
                    ret++;
                }
                
                
            } else if (arr[i] + arr[j] < target) {
                i++;
            } else {
                j--;
            }
            
            
        }
        return ret;
    }
}
