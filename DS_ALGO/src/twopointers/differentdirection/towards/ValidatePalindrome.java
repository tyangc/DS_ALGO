package twopointers.differentdirection.towards;

/*
 415. Valid Palindrome

Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.

Example
Example 1:

Input: "A man, a plan, a canal: Panama"
Output: true
Explanation: "amanaplanacanalpanama"
Example 2:

Input: "race a car"
Output: false
Explanation: "raceacar"
Challenge
O(n) time without extra memory.

Notice
Have you consider that the string might be empty? This is a good question to ask during an interview.

For the purpose of this problem, we define empty string as valid palindrome.
 */

public class ValidatePalindrome {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param s: A string
     * @return: Whether the string is a valid palindrome
     */
    public boolean isPalindrome(String s) {
        // write your code here
    	if (s.isEmpty()) {
    		return true;
    	}
    	
        if(s == null) {
        	return false;
        }
 
	    s = s.toLowerCase();
	 
	    int i=0;
	    int j=s.length()-1;
	 
	    while(i<j){
	        while(i<j && !((s.charAt(i)>='a' && s.charAt(i)<='z') 
	                    || (s.charAt(i)>='0'&&s.charAt(i)<='9'))){
	            i++;
	        }
	 
	        while(i<j && !((s.charAt(j)>='a' && s.charAt(j)<='z') 
	                    || (s.charAt(j)>='0'&&s.charAt(j)<='9'))){
	            j--;
	        }
	 
	        if(s.charAt(i) != s.charAt(j)){
	            return false;
	        }
	 
	        i++;
	        j--;
	    }
	 
	    return true;
    }
	

}
