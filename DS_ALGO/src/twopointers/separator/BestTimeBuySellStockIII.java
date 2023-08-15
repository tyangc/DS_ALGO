package twopointers.separator;
/*
 Best Time to Buy and Sell Stock III
描述
Say you have an array for which the ith element is the price of a given stock on day i.

Design an algorithm to find the maximum profit. You may complete at most two transactions.

You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).

说明
样例
Example 1

Input : [4,4,6,1,1,4,2,5]
Output : 6
 */
public class BestTimeBuySellStockIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param prices: Given an integer array
     * @return: Maximum profit
     */
    public int maxProfit(int[] prices) {
        // write your code here
        if (prices==null || prices.length<2) return 0;
        int maxCost =0 ;
        for (int i=0; i<prices.length; i++) {
           
            int leftCost = getCost(prices, 0, i);
            int rightCost = getCost(prices, i, prices.length);
            maxCost = Math.max(maxCost, leftCost+rightCost);
        }
        return maxCost;
    }
    
    private int getCost(int[] prices, int left, int right) {
        int cost=0, minPrice = prices[left];
        for (int i=left; i<right; i++) {
            minPrice=Math.min(minPrice, prices[i]);
            cost = Math.max(cost, prices[i]-minPrice);
        }
        
        return cost;
    }
}
