package dp.prefix.divide;

import java.util.HashSet;
import java.util.Set;
/*
683. Word Break III

Give a dictionary of words and a sentence with all whitespace removed, return the number of sentences you can form by inserting whitespaces to the sentence so that each word can be found in the dictionary.

Example
Example1

Input:
"CatMat"
["Cat", "Mat", "Ca", "tM", "at", "C", "Dog", "og", "Do"]
Output: 3
Explanation:
we can form 3 sentences, as follows:
"CatMat" = "Cat" + "Mat"
"CatMat" = "Ca" + "tM" + "at"
"CatMat" = "C" + "at" + "Mat"
Example1

Input:
"a"
[]
Output: 
0
Notice
Ignore case
*/
//

//Use divide and conquer
public class WordBreakIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param s: A string
     * @param dict: A set of word
     * @return: the number of possible sentences.
     */
    public int wordBreak3(String s, Set<String> dict) {
        // Write your code here

        s = s.toLowerCase();

        Set<String> set = new HashSet<>();

        for (String word: dict) {
            set.add(word.toLowerCase());
        }

        int len = s.length();
        int[] dp = new int[len+1];

        dp[0] = 1; 

        for (int i=0; i<len; i++) {
            for (int j=i; j<len; j++) {
                if (set.contains(s.substring(i, j+1))) {
                    dp[j+1] +=dp[i];
                }
            }
        }

        return dp[len];
    }


}
