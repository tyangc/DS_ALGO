package monotonestack;

import java.util.Arrays;
import java.util.Stack;

/*
 Tall Building

At the weekend, Xiao Q and his friends came to the big city for shopping. There are many tall buildings.There are n tall buildings in a row, whose height is indicated by arr.
Xiao Q has walked from the first building to the last one. Xiao Q has never seen so many buildings, so he wants to know how many buildings can he see at the location of each building? (When the height of the front building is greater than or equal to the back building, the back building will be blocked)

1 \leq n \leq 1000001≤n≤100000
1 \leq arr[i] \leq 1000001≤arr[i]≤100000


Example 1:

Input:[5,3,8,3,2,5]
Output:[3,3,5,4,4,4]
Explanation:
When Xiao Q is at position 0, he can see 3 tall buildings at positions 0, 1, and 2.
When Xiao Q is at position 1, he can see  3 tall buildings at positions 0, 1, and 2.
When Xiao Q is at position 2, he can see the building at position 0, 1 forward, and the building at position 3, 5 backward, plus the third building, a total of 5 buildings can be seen.
When Xiao Q is at position 3, he can see 4 tall buildings in positions 2, 3, 4, and 5.
When Xiao Q is at position 4, he can see 4 tall buildings in positions 2, 3, 4, and 5.
When Xiao Q is at position 5, he can see 4 tall buildings in positions 2, 3, 4, and 5.
 */
public class TallBuilding {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param arr: the height of all buildings
     * @return: how many buildings can he see at the location of each building
     */
    public int[] tallBuilding(int[] arr) {
        // Write your code here.
        int[] result = new int[arr.length];
        Arrays.fill(result, 1);
        helper(arr, result, 0, arr.length, 1);
        
        helper(arr, result, arr.length-1, -1, -1);
        
        return result;
    }
    
    private void helper(int[] arr, int[] result, int start, int end, int delta) {
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i=start; i!=end; i+=delta) {
            result[i]+=stack.size();
            while(!stack.isEmpty()&&arr[stack.peek()]<=arr[i]) {
                stack.pop();
            }
            
            stack.push(i);
        }
    }   
}
