package amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * https://leetcode.com/problems/shopping-offers/solution/
 * 638. Shopping Offers
Medium

948

612

Add to List

Share
In LeetCode Store, there are n items to sell. Each item has a price. However, there are some special offers, and a special offer consists of one or more different kinds of items with a sale price.

You are given an integer array price where price[i] is the price of the ith item, and an integer array needs where needs[i] is the number of pieces of the ith item you want to buy.

You are also given an array special where special[i] is of size n + 1 where special[i][j] is the number of pieces of the jth item in the ith offer and special[i][n] (i.e., the last integer in the array) is the price of the ith offer.

Return the lowest price you have to pay for exactly certain items as given, where you could make optimal use of the special offers. You are not allowed to buy more items than you want, even if that would lower the overall price. You could use any of the special offers as many times as you want.

 

Example 1:

Input: price = [2,5], special = [[3,0,5],[1,2,10]], needs = [3,2]
Output: 14
Explanation: There are two kinds of items, A and B. Their prices are $2 and $5 respectively. 
In special offer 1, you can pay $5 for 3A and 0B
In special offer 2, you can pay $10 for 1A and 2B. 
You need to buy 3A and 2B, so you may pay $10 for 1A and 2B (special offer #2), and $4 for 2A.
Example 2:

Input: price = [2,3,4], special = [[1,1,0,4],[2,2,1,9]], needs = [1,2,1]
Output: 11
Explanation: The price of A is $2, and $3 for B, $4 for C. 
You may pay $4 for 1A and 1B, and $9 for 2A ,2B and 1C. 
You need to buy 1A ,2B and 1C, so you may pay $4 for 1A and 1B (special offer #1), and $3 for 1B, $4 for 1C. 
You cannot add more items, though only $9 for 2A ,2B and 1C.
 

Constraints:

n == price.length
n == needs.length
1 <= n <= 6
0 <= price[i] <= 10
0 <= needs[i] <= 10
1 <= special.length <= 100
special[i].length == n + 1
0 <= special[i][j] <= 50
Accepted
44,298
Submissions
81,437
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Google
|
3

Apple
|
2

Amazon
|
2
 */
public class ShopperMultipleOffers {
	public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
        int base = 0;
        List<Integer> lst = new ArrayList<>();
        for (int i=0; i<needs.size(); i++) {
            base += price.get(i)*needs.get(i);
            lst.add(1);
        }
        lst.add(base);
        special.add(lst);
        
        
        return Math.min(base, dfs(new int[special.size()], price, special, needs, new HashMap<int[], Integer>()));
    }
    
	
	//Think too complex of state recording/expression, has bug, should do two way thinking
    private int dfs(int[] specCnt,  List<Integer> price, List<List<Integer>> special, List<Integer> needs, Map<int[], Integer> memo ) {
        
        if (memo.containsKey(specCnt)) return memo.get(specCnt);
        
        boolean[] candidates = canAddSpecial(special, needs);
        if (allFalse(candidates)) {
            int res = 0;
            for (int i=0; i< specCnt.length; i++) {
                List<Integer> spec = special.get(i);
                res += specCnt[i] * spec.get(spec.size()-1);
            }
            
            for (int i=0; i<needs.size(); i++) {
                res += price.get(i)*needs.get(i);
            }
            return res;
        }
        
        int result = Integer.MAX_VALUE;
        
        for (int i=0; i<candidates.length; i++)  {
            if (candidates[i]) {
                int[] specCntNew = Arrays.copyOf(specCnt, specCnt.length);
                specCntNew[i] = specCnt[i] + 1;   
                List<Integer> spec = special.get(i);
                List<Integer> newNeeds = new ArrayList<>();
                for (int j=0; j<spec.size()-1; j++) {
                    newNeeds.add(needs.get(j) - spec.get(j));
                }
                result = Math.min(result, dfs(specCntNew, price, special, newNeeds, memo));
            }
        }
        
        memo.put(specCnt, result);
        return result;
    }
    
    /*
    private int[] getLimitNum(List<List<Integer>> special, List<Integer> needs) {
        int[] res = new int[special.size()];
        
        int i=0, tmpMin = Integer.MAX_VALUE;
        
        for (List<Integer> spec : special) {
            tmpMin = Integer.MAX_VALUE;
            
            for (int j=0; j<needs.size(); j++) {
                if (spec.get(j) == 0) continue;
                int tmp = needs.get(j) / spec.get(j);
                tmpMin = Math.min(tmpMin, tmp);
            }
            
            res[i++] = tmpMin;
        }
        
        return res;
    }
    */
    
    private boolean[] canAddSpecial( List<List<Integer>> special, List<Integer> needs ) {
        //int[] res = null;
        
        boolean[] res = new boolean[special.size()];
        int k = 0; 
        
        for (int i=0; i<special.size(); i++) {
            List<Integer> spec = special.get(i);
            boolean canAdd = true;
            for (int j=0; j<needs.size(); j++) {
                if (spec.get(j) > needs.get(j)) {
                    canAdd = false;
                    break;
                }
            }
            res[k++] = canAdd;
        }
        return res;
    }
    
    private boolean allFalse(boolean[] candidates) {
        for (boolean v : candidates) {
            if (v) return false;
        }
        
        return true;
    }
    
    
    //LeetCode solution:
    public int shoppingOffers1(List < Integer > price, List < List < Integer >> special, List < Integer > needs) {
        Map < List < Integer > , Integer > map = new HashMap();
        return shopping(price, special, needs, map);
    }
    public int shopping(List < Integer > price, List < List < Integer >> special, List < Integer > needs, Map < List < Integer > , Integer > map) {
        if (map.containsKey(needs))
            return map.get(needs);
        int j = 0, res = dot(needs, price);
        for (List < Integer > s: special) {
            ArrayList < Integer > clone = new ArrayList < > (needs);
            for (j = 0; j < needs.size(); j++) {
                int diff = clone.get(j) - s.get(j);
                if (diff < 0)
                    break;
                clone.set(j, diff);
            }
            if (j == needs.size())
                res = Math.min(res, s.get(j) + shopping(price, special, clone, map));
        }
        map.put(needs, res);
        return res;
    }
    public int dot(List < Integer > a, List < Integer > b) {
        int sum = 0;
        for (int i = 0; i < a.size(); i++) {
            sum += a.get(i) * b.get(i);
        }
        return sum;
    }

}
