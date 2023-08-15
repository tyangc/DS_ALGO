package amazon;
/*
 * https://leetcode.com/problems/minimum-difficulty-of-a-job-schedule/
 * 1335. Minimum Difficulty of a Job Schedule
Hard

1149

131

Add to List

Share
You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the ith job, you have to finish all the jobs j where 0 <= j < i).

You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done on that day.

You are given an integer array jobDifficulty and an integer d. The difficulty of the ith job is jobDifficulty[i].

Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

 

Example 1:


Input: jobDifficulty = [6,5,4,3,2,1], d = 2
Output: 7
Explanation: First day you can finish the first 5 jobs, total difficulty = 6.
Second day you can finish the last job, total difficulty = 1.
The difficulty of the schedule = 6 + 1 = 7 
Example 2:

Input: jobDifficulty = [9,9,9], d = 4
Output: -1
Explanation: If you finish a job per day you will still have a free day. you cannot find a schedule for the given jobs.
Example 3:

Input: jobDifficulty = [1,1,1], d = 3
Output: 3
Explanation: The schedule is one job per day. total difficulty will be 3.
 

Constraints:

1 <= jobDifficulty.length <= 300
0 <= jobDifficulty[i] <= 1000
1 <= d <= 10
Accepted
63,172
Submissions
111,221
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Uber
|
5

Amazon
|
4

Google
|
2
Use DP. Try to cut the array into d non-empty sub-arrays. Try all possible cuts for the array.
Use dp[i][j] where DP states are i the index of the last cut and j the number of remaining cuts. Complexity is O(n * n * d).
 */
public class MinimumDifficultyJobSchedule {
	public int minDifficulty(int[] jobDifficulty, int d) {
        if (jobDifficulty == null || jobDifficulty.length==0 || jobDifficulty.length<d) return -1;
        int[] res = new int[1];
        res[0] = Integer.MAX_VALUE;
        dfs(jobDifficulty, 0, 1, d, new int[d], res);
        
        return res[0];
        
    }
    
    private void dfs(int[] arr, int start, int day, int totalDays, int[] dayDiff, int[] res) {
        //if (start>=arr.length) return;
        if (day == totalDays) {
            //System.out.println(Arrays.toString(dayDiff));
            dayDiff[dayDiff.length-1] = getMax(arr, start, arr.length-start);//arr[start];
            //dayDiff[dayDiff.length-1] = arr[start];  //This is WRONG! last day can still cover multiple jobs
            //System.out.println(Arrays.toString(dayDiff));
            int sum = getDifficultySum(dayDiff);
            //System.out.println(sum);
            res[0] = Math.min(res[0], sum);
            return;
        }
        
        /*
        if (days==1 && start == arr.length-1) {
            dayDiff[dayDiff.length-1] = arr[start];
            int sum = getDifficultySum(dayDiff);
            res[0] = Math.max(res[0], sum);
            return;
        }
        */
        
        int len = arr.length - start - (totalDays - day);
        
        //if (len<1) return;
        
        for (int i=1; i<=len; i++) {
            dayDiff[day-1] = getMax(arr, start, i);
            dfs(arr, start+i, day+1, totalDays, dayDiff, res);
            //dayDiff[day-1] = 0;  //track back is not necessary here
        }
 
    }
    
    private int getDifficultySum(int[] dayDiff) {
        int ret = 0;
        for (int i=0; i<dayDiff.length; i++) {
            ret += dayDiff[i];
        }
        return ret;
    }
    
    private int getMax(int[] arr, int start, int days) { 
        int ret = arr[start];
        
        for (int i=1; i<days; i++) {
            ret = Math.max(ret, arr[start+i]);
        }
        return ret;
    }
}
