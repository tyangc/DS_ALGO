package dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DivisibleSubset {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param nums: a set of distinct positive integers
     * @return: the largest subset 
     */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        // write your code here
        
        List<Integer> res = new ArrayList<>();
        if (nums==null || nums.length==0) return res;
        Arrays.sort(nums);

        Map<Integer, Integer> dp = new HashMap<>();
        Map<Integer, Integer> prev = new HashMap<>();

        int lastNum = nums[0];
        int n= nums.length;
        for (int i=0; i<n; i++) {
            dp.put(nums[i], 1);
            prev.put(nums[i], -1); 
        }

        

        for (int i=0; i<n; i++) {
            int num = nums[i];
            for (int factor : getFactors(nums[i])) {
                if (!dp.containsKey(factor)) continue;

                if (dp.get(num) < dp.get(factor) + 1) {
                    dp.put(num, dp.get(factor) + 1);
                    prev.put(num, factor);
                    
                }

                if (dp.get(num)>dp.get(lastNum)) {
                    lastNum = nums[i];
                }
            }
        }

        res = getPath(prev, lastNum);
        //Collections.reverse(res);
        return res;
    }

    private List<Integer> getFactors(int num) {

         List<Integer> res = new ArrayList<>();   

        if (num==1) {
            //res.add(1);
            return res;
        }

        int factor=1;

        while(factor * factor <= num) {
            if (num%factor == 0) {
                res.add(factor);

                if ( factor!=1 && num/factor!=factor) {
                    res.add(num/factor);
                }
            }
            factor++;
        }
        //res.add(num);
        System.out.println(res);
        return res;
    }

    private List<Integer> getPath(Map<Integer, Integer> prev, int lastNum) {
        List<Integer> res = new ArrayList<>();
        System.out.println(lastNum);
        int last = lastNum;
           

           while(last>0) {
               res.add(last);
               last = prev.get(last);
               
               
           } 
        Collections.reverse(res);
        return res;
    }
    
    //Without the optimization:
    public List<Integer> largestDivisibleSubset1(int[] nums) {
        // write your code here
        
        Arrays.sort(nums);
        int[] f = new int[nums.length];
        int[] pre = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            f[i] = 1;
            pre[i] = i;
            for (int j = 0; j < i; j++) {
                if (nums[i] % nums[j] == 0 && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                    pre[i] = j;
                }
            }
        }
        
        List<Integer> ans = new ArrayList<Integer>();
        if (nums.length == 0) {
            return ans;
        }
        int max = 0;
        int max_i = 0;
        for (int i = 0; i < nums.length; i++) {
            if (f[i] > max) {
                max = f[i];
                max_i = i;
            }
        }
        ans.add(nums[max_i]);
        while (max_i != pre[max_i]) {
            max_i = pre[max_i];
            ans.add(nums[max_i]);
        }
        Collections.reverse(ans);
        return ans;
    
    }
}
