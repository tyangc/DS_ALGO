package dfs.searchtree;

import java.util.ArrayList;
import java.util.List;

/*
 * 425. Letter Combinations of a Phone Number
中文English
Given a digit string excluded 0 and 1, return all possible letter combinations that the number could represent.

A mapping of digit to letters (just like on the telephone buttons) is given below.

1	2 	3
	ABC DEF
4	5	6
GHI JKL MNO
7	8	9
PQRS TUV WXYZ

Example
Example 1:

Input: "23"
Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
Explanation: 
'2' could be 'a', 'b' or 'c'
'3' could be 'd', 'e' or 'f'
Example 2:

Input: "5"
Output: ["j", "k", "l"]
Notice
Although the answer above is in lexicographical order, your answer could be in any order you want.
 */
public class LetterCombinationPhoneNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param digits: A digital string
     * @return: all posible letter combinations
     * 
     * 
     */
     
    public static final String[] combination = new String[] {
        "",
        "",
        "ABC",
        "DEF",
        "GHI",
        "JKL",
        "MNO",
        "PQRS",
        "TUV",
        "WYXZ"
        
    };
     
    public List<String> letterCombinations(String digits) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (digits==null||digits.isEmpty()) {
            return res;
        }
        
        char[] nums = digits.toCharArray();
        int n = nums.length;
        String current = "";
        
        dfs(nums, n, 0,  current, res); 
        return res;
    }
    
    private void dfs(char[] nums, int n, int index, String current, List<String> result) {
        if (current.length()==n) {
            result.add(current);
            return;
        }
          
        int num = nums[index] - '0';  
        
        char[] letters = combination[num].toLowerCase().toCharArray();
        
        for (int i=0; i<letters.length; i++) {
            dfs(nums, n, index+1, current+letters[i], result);
        }
            
    }
}
