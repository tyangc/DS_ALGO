package dfs;

public class StrStr {

	/*
	 For a given source string and a target string, you should output the first index(from 0) of target string in source string.

If target does not exist in source, just return -1.

Example
Example 1:

Input: source = "source" ，target = "target"
Output: -1	
Explanation: If the source does not contain the target content, return - 1.
Example 2:

Input:source = "abcdabcdefg" ，target = "bcd"
Output: 1	
Explanation: If the source contains the target content, return the location where the target first appeared in the source.
Challenge
O(n2) is acceptable. Can you implement an O(n) algorithm? (hint: KMP)

Clarification
Do I need to implement KMP Algorithm in a real interview?

Not necessary. When you meet this problem in a real interview, the interviewer may just want to test your basic implementation ability. But make sure you confirm with the interviewer first.
	 */
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(strStr("adfabcfrewa", "abc"));
	}

	/**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public static int strStr(String source, String target) {
        // Write your code here
        if (source==null || target==null || target.length()>source.length()) return -1;
        
        if (target.equals("")) return 0;
        
        for (int i=0; i<=source.length()-target.length();i++) {
            if (source.charAt(i)==target.charAt(0)) {
                if(isMatch(source, target, i, 0)) {
                    return i;
                }
            } else {
                continue;
            }
        }
        return -1;
    }
    
    private static boolean isMatch(String s, String t, int i, int j) {
        if (j==t.length()) return true;
        
        if (s.charAt(i)==t.charAt(j)) {
            return isMatch(s, t, i+1, j+1);
        } else {
            return false;
        }
    }
}
