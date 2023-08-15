package microsoft.oa;

/*
 
 https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/
 
 The frequency of a character in a string is the number of times it appears in the string. For example, in the string "aab", the frequency of 'a' is 2, while the frequency of 'b' is 1.

 

Example 1:

Input: s = "aab"
Output: 0
Explanation: s is already good.
Example 2:

Input: s = "aaabbbcc"
Output: 2
Explanation: You can delete two 'b's resulting in the good string "aaabcc".
Another way it to delete one 'b' and one 'c' resulting in the good string "aaabbc".
Example 3:

Input: s = "ceabaacb"
Output: 2
Explanation: You can delete both 'c's resulting in the good string "eabaab".
Note that we only care about characters that are still in the string at the end (i.e. frequency of 0 is ignored).
 

Constraints:

1 <= s.length <= 105
s contains only lowercase English letters.
Accepted
37,330
Submissions
66,850
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Microsoft
|
13

Amazon
|
3

Apple
|
3
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MinDeletionsToUniqueFrequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
	}

	public int minDeletions(String s) {
        if (s==null || s.length()==0) return 0;
        
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        
        for (int i=0; i<s.length(); i++) {
            char c = s.charAt(i);
            int cnt = map.getOrDefault(c, 0);
            
            map.put(c, cnt+1);
            if (cnt+1>max) max = cnt+1;
        }
        
        int n = map.size();
        
        List<Integer> lst = new ArrayList(map.values());
        int[] nums = new int[lst.size()];
        int k=0;
        for (int a : lst) {
            nums[k++] = a;  
        }
        
        Arrays.sort(nums);
        int rmv = 0;
        for (int j=nums.length-2; j>=0; j--) {
            if (nums[j] >= nums[j+1]) {
                if (nums[j+1]>1) {
                    
                    rmv += nums[j] - nums[j+1] + 1;
                    nums[j] = nums[j+1]-1;
                } else {
                    rmv += nums[j];
                    nums[j] = 0;
                }
            }
        }
        
        return rmv;
        
        //Using global formular way  - not working
        
        /*
        int ans = 0;
        if (max > n) {
            ans =  s.length() - max*(max+1)/2 - n*(max-n)/2 ;
        } else {
            ans =  s.length() - max*(max+1)/2; 
        }
        
        if (ans<0) ans = 0;
        
        return ans;
        */
    }

	//using PriorityQueue
	public int minDeletions1(String s) {
        if (s==null || s.length()==0) return 0;
        
        Map<Character, Long> map = s.chars()
        							.mapToObj(i->(char)i)
        							.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
        
        PriorityQueue<Long> pq = new PriorityQueue<>((x, y) -> Long.compare(y, x)); 
        
        for (Map.Entry<Character, Long> e : map.entrySet()) {
        	pq.offer(e.getValue());
        }
        int cnt=0;
        while(!pq.isEmpty()) {
        	long cur = pq.poll();
        	if (!pq.isEmpty()) {
        		long nxt = pq.peek();
        		if (cur == nxt && cur >= 1) {
 
        			pq.offer(cur-1);
        			cnt++;
        		}
        	}
        }
        
        return cnt++;
	}
}
