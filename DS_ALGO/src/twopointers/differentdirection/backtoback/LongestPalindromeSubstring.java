package twopointers.differentdirection.backtoback;

public class LongestPalindromeSubstring {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param s: input string
     * @return: a string as the longest palindromic substring
     */
    public String longestPalindrome(String s) {
        // write your code here
        if (s==null || s.length()<2) return s;
        
        String longest = "";
        
        for (int i=0; i<s.length(); i++) {
            String oddPalindrome = isPanlindromeFrom(s, i, i);
            if (oddPalindrome.length()>longest.length()) {
                longest = oddPalindrome;
            }
            
            String evenPalindrome = isPanlindromeFrom(s, i, i+1);
            if (evenPalindrome.length()>longest.length()) {
                longest = evenPalindrome;
            }
        }
        
        
        return longest;
    }
    
    private String isPanlindromeFrom(String s, int i, int j) {
        //if (j>i) return "";
        int left = i, right=j;
        while(left>=0&&right<s.length()&& s.charAt(left)==s.charAt(right)) {
            
            /*
            if(s.charAt(left)!=s.charAt(right)) {
                break;
            }
            */
            left--;
            right++;
        }
        
        return s.substring(left+1, right);
    }
}
