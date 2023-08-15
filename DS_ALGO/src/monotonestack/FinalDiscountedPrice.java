package monotonestack;

import java.util.Arrays;
import java.util.Stack;

/*
 Final Discounted Price

A shopkeeper has a sale to complete and has arranged the items being sold in an array. Starting from the left, the shopkeeper rings up each item at its full price less the price of the first lower or equal priced item to its right. If there is no item to the right that costs less than or equal to the current item's price, the current item is sold at full price.You should return the actual selling price of each item.

The length of Prices is within range: [1, 100000]
Prices[i] is within range: [1, 1000000]

Example 1:
Input:
Prices = [2, 3, 1, 2, 4, 2]
Output: [1, 2, 1, 0, 2, 2]
Explanation: The item 0 and 1 are each discounted by 1 unit, The item 3 at 2 units, is discounted 2 units, as would the item 4 at 4 units. 
Example 2:
Input:
Prices = [1, 2, 3, 4, 5]
Output: [1, 2, 3, 4, 5]
Explanation: each item should keep full price beacause there are not equal or lower priced items to the right
 */
public class FinalDiscountedPrice {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param prices: a list of integer
     * @return: return the actual prices
     */
    public int[] FinalDiscountedPrice(int[] prices) {
        // write your code here
        int[] result = Arrays.copyOf(prices, prices.length);
        Stack<Integer> stack = new Stack<>();
        
        for (int i=0; i<prices.length; i++) {
            while(!stack.isEmpty() && prices[stack.peek()] >= prices[i]) {
                result[stack.peek()] = result[stack.peek()] - prices[i];
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }
}
