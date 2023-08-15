package twopointers.samedirection.twoPointersForTwoArrays;

import java.util.Arrays;

/*
 * https://www.lintcode.com/problem/1219/
 * 1219 · Heaters
Algorithms
Medium
Accepted Rate
45%
Description
Solution33
Notes79
Discuss
Leaderboard
Record
Description
Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.

Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters so that all houses could be covered by those heaters.

So, your input will be the positions of houses and heaters seperately, and your expected output will be the minimum radius standard of heaters.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


1.Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
2.Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
3.As long as a house is in the heaters' warm radius range, it can be warmed.
4.All the heaters follow your radius standard and the warm radius will the same.

Example
Example 1:

Input: [1,2,3],[2]
Output: 1
Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard, then all the houses can be warmed.
Example 2:

Input: [1,2,3,4],[1,4]
Output: 1
Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard, then all the houses can be warmed.
Tags
Binary Search
Company
Google
Related Problems

437
Copy Books

 */
public class Heaters {

	public int findRadius(int[] houses, int[] heaters) {
		// Write your code here

		Arrays.sort(houses);
		Arrays.sort(heaters);

		int m = houses.length, n = heaters.length;
		int ret = 0, i = 0, j = 0, curRad = 0, nxtRad = 0;
		curRad = Math.abs(houses[0] - heaters[0]);

		while (i < m && j < n) {

			/*
			 * if (houses[i]>=heaters[j] && houses[i]<=heaters[j+1]) { nxtRad =
			 * Math.abs(houses[i] - heaters[j+1]); ret = Math.max(ret, Math.min(curRad,
			 * nxtRad)); i++; } else { if (j==n-1) { i++; } curRad = nxtRad; j++; }
			 */
			curRad = Math.abs(houses[i] - heaters[j]);
			nxtRad = Integer.MAX_VALUE;
			if (j < n - 1) {
				nxtRad = Math.abs(houses[i] - heaters[j + 1]);
			}

			if (curRad < nxtRad) {
				ret = Math.max(ret, curRad);
				i++;
			} else {
				j++;
			}
		}

		if (i == m)
			return ret;
		else
			return -1;
	}
}
