package twopointers.samedirection;

/*
 13. Implement strStr()

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

public class StrStr {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param source: 
     * @param target: 
     * @return: return the index
     */
    public int strStr(String source, String target) {
        // Write your code here
        
        if (source==null || target == null) return -1;
        
        int m=source.length(), n=target.length();
        
        if (n==0 || source.equals(target)) return  0;
        
        
        int k=0;
        
        for (int i=0; i<=m-n; i++) {
            //j = i;
            k=i;
            for (int j=0; j<n; j++) {
            
                if (source.charAt(k)!=target.charAt(j)) {
                   break;
                }
                k++;
            }
            
            if (k-i==n) return i;
            
            
        }
          
        return -1;  
    }
    
    //Use two layer of function/method
    public int strStr1(String source, String target) {
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
    
    /*  Implement with recursion
    private boolean isMatch(String s, String t, int i, int j) {
        if (j==t.length()) return true;
        
        if (s.charAt(i)==t.charAt(j)) {
            return isMatch(s, t, i+1, j+1);
        } else {
            return false;
        }
    }
    */
    private boolean isMatch(String s, String t, int i, int j) {
      int m = i;
      for (int k=j; k<t.length(); k++) {
        if (s.charAt(m) != t.charAt(k)) {
          return false;
        }
        m++;
      }
      return true;
    }
}
