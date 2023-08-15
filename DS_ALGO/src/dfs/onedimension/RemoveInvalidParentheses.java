package dfs.onedimension;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
 780 · Remove Invalid Parentheses
Algorithms Hard Accepted Rate 45%

DescriptionSolutionNotesDiscussLeaderboard
Description
Remove the minimum number of invalid parentheses in order to make the input string valid. Return all possible results.

The input string may contain letters other than the parentheses ( and ).

Example
Example 1:

Input:
"()())()"
Ouput:
["(())()","()()()"]
Example 2:

Input:
"(a)())()"
Output:
 ["(a)()()", "(a())()"]
Example 3:

Input:
")(" 
Output:
 [""]
Tags
Company Facebook
 */

public class RemoveInvalidParentheses {

	/**
     * @param s: The input string
     * @return: Return all possible results
     */
    int max = 0;
    public List<String> removeInvalidParentheses(String s) {
        // Write your code here
        Set<String> res = new HashSet<>();   
        if (s==null || s.length()<1) {
          List<String> tmp = new ArrayList<>();
          tmp.add("");
          return tmp;
        }
        dfs(s, 0, 0, "", res);
        return new ArrayList<>(res);
    }

    //was trying to use return boolean as flow control - serialized accumulation segregation , but it is not necessary at all.
    //It is a parallel run ( branch out )  at this point of execution
    private boolean dfs(String s, int left, int m, String sb, Set<String> res) {
      if (left<0) return false;
      if (s.length()==0 ) {
        //System.out.println(left + "|" + sb.toString());
        if (left==0 ) {
          if (m>max) max = m;
          if (m==max){
             res.add(sb);
             //return true;
          }
          //return true;
        } 

        return false;

      }

     

      if (s.charAt(0)=='(') {
        
        if (dfs(s.substring(1), left+1, m+1, sb+'(', res)) {
          return true;
        }
        //sb.deleteCharAt(sb.length()-1);
        return dfs(s.substring(1), left, m, sb, res);

      } else if (s.charAt(0)==')') {
        if (dfs(s.substring(1), left-1, m, sb+')', res))  {
          return true;
        }
        //sb.deleteCharAt(sb.length()-1);
        return dfs(s.substring(1), left, m, sb, res);
      } else {
        return dfs(s.substring(1), left, m, sb+s.charAt(0), res);
      }
    }
     
    //This works fine, passed all the test cases
    private void dfs1(String s, int left, int m, String sb, Set<String> res) {
        if (left<0) return;
        if (s.length()==0 ) {
          //System.out.println(left + "|" + sb.toString());
          if (left==0 ) {
            if (m>max) max = m;
            if (m==max){
               res.add(sb);
               //return true;
            }
            //return true;
          } 

          return ;

        }

       

        if (s.charAt(0)=='(') {
          
          dfs(s.substring(1), left+1, m+1, sb+'(', res);
          //  return true;
          
          //sb.deleteCharAt(sb.length()-1);   //Use StringBuilder it will only pass 50% of test cases, it failed at ")((())))))()(((l(((("
           dfs(s.substring(1), left, m, sb, res);

        } else if (s.charAt(0)==')') {
          dfs(s.substring(1), left-1, m, sb+')', res);
          
          
          //sb.deleteCharAt(sb.length()-1);
          dfs(s.substring(1), left, m, sb, res);
        } else {
          dfs(s.substring(1), left, m, sb+s.charAt(0), res);
        }
      } 
}
