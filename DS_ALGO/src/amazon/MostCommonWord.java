package amazon;
/*
 1369. Most Common Word

Given a paragraph and a list of banned words, return the most frequent word that is not in the list of banned words. It is guaranteed there is at least one word that isn't banned, and that the answer is unique.

Words in the list of banned words are given in lowercase, and free of punctuation. Words in the paragraph are not case sensitive. The answer is in lowercase.

Example
Example1

Input:  paragraph = "Bob hit a ball, the hit BALL flew far after it was hit." and banned = ["hit"]
Output: "ball"
Explanation:
"hit" occurs 3 times, but it is a banned word.
"ball" occurs twice (and no other word does), so it is the most frequent non-banned word in the paragraph. 
Note that words in the paragraph are not case sensitive,
that punctuation is ignored (even if adjacent to words, such as "ball,"), 
and that "hit" isn't the answer even though it occurs more because it is banned.
Example2

Input:  paragraph = "a a a b b c c d" and banned = ["a","b"]
Output: "c"
Explanation:
"a" and "b" are banned words
"c" occurs 2 times and "d" occurs only once
So output "c"
Notice
1 <= paragraph.length <= 1000.
1 <= banned.length <= 100.
1 <= banned[i].length <= 10.
The answer is unique, and written in lowercase (even if its occurrences in paragraph may have uppercase symbols, and even if it is a proper noun.)
paragraph only consists of letters, spaces, or the punctuation symbols !?',;.
Different words in paragraph are always separated by a space.
There are no hyphens or hyphenated words.
Words only consist of letters, never apostrophes or other punctuation symbols.
 */

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;

public class MostCommonWord {
	 /**
     * @param paragraph: 
     * @param banned: 
     * @return: nothing
     */
    public String mostCommonWord(String paragraph, String[] banned) {
        // 
        
        String[] arr = paragraph.split(" ");
        Set<String> dict = new HashSet(Arrays.asList(banned));
        
        Map<String, Integer> map = new HashMap<>();
        
        for (String word : arr) {
            
            String inStr = removePuncture(word).toLowerCase();
            if (dict.contains(inStr)) {
                continue;
            }
            
            if (!map.containsKey(inStr)) {
                map.put(inStr, 1);
            } else {
                map.put(inStr, map.get(inStr)+1);
            }
            
        }
        System.out.println(map);
        
        String res = Collections.max(map.entrySet(), (entry1, entry2) -> entry1.getValue() - entry2.getValue()).getKey();
        
        return res;
    }
    
    private String removePuncture(String inStr) {
        String res = inStr;
        while(!isChar(res.charAt(res.length()-1))) {
            res = res.substring(0, res.length()-1);
        }
        
        return res;
    }
    
    private boolean isChar(char c) {
        
        if (('a'<=c && c<='z') || ('A'<=c && c<='Z' )) {
            return true;
        }
        
        return false;
    }
}
