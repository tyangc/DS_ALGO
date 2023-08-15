package datastructure.map;
/*
 1883 · 前K个高频关键词
算法
Medium
Accepted Rate
33%

DescriptionSolutionNotesDiscussLeaderboard
Description
给定一个评论列表reviews，一个关键字列表 keywords 以及一个整数k。
找出在不同评论中出现次数最多的前k个关键词，这k个关键词按照出现次数的由多到少来排序。
字符串不区分大小写，如果关键字在不同评论中出现的次数相等，请按字母顺序从小到大排序。

如果K大于列表keywords的长度，则直接输出keywords
keywords 的列表长度范围是: [1, 100]
reviews 的列表长度范围是: [1: 1000]
kewords[i] 由小写字母组成
reviews[i] 由大小写字母以及标点符号: [ "[", "\", "!", "?", ",", ";" , ".", "]", " "] 组成
Example
示例 1:
输入:
k = 2
keywords = ["anacell", "cetracular", "betacellular"]
reviews = [
  "Anacell provides the best services in the city",
  "betacellular has awesome services",
  "Best services provided by anacell, everyone should use anacell",
]
输出:
["anacell", "betacellular"]
解释: 
"anacell" 在2个不同的评论，"betacellular" 在1个评论中出现。
示例 2:
输入:
k = 2
keywords = ["anacell", "betacellular", "cetracular", "deltacellular", "eurocell"]
reviews = [
  "I love anacell Best services; Best services provided by anacell",
  "betacellular has great services",
  "deltacellular provides much better services than betacellular",
  "cetracular is worse than anacell",
  "Betacellular is better than deltacellular.",
]
输出:
["betacellular", "anacell"]
解释: 
"betacellular" 在3个不同的评论中出现了，"anacell" 以及 "deltacellular" 在两个不同的评论中出现了，但是"anacell" 在字典序中最小。
Tags
哈希表
排序算法
Related Problems
550
最常使用的K个单词II
Hard
471
最高频的K个单词
Medium
 */

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.*;

public class KFrequenKeyWords {

	//This following using stream won't work out
	
	public List<String> TopkKeywords(int K, String[] keywords, String[] reviews) {
        // write your code here

        Map<String, Integer> keyCount = new HashMap<>();

        for (String word : keywords) {
          keyCount.put(word, 0);

        }

        for (String review : reviews) {
          /*
          String[] words = clean(review).split(" ");

          
          for (String word: words) {
            if (keyCount.containsKey(word)) {
              keyCount.put(word, keyCount.get(word)+1);
            }
          }
          */

          
          for (String key : keyCount.keySet()) {
            if (review.toLowerCase().contains(key)) {
              keyCount.put(key, keyCount.get(key)+1);
            }
          }
          
        }

        List<String> res = keyCount
                            .entrySet()
                            .stream()
                            .sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue().thenComparing(Map.Entry.<String, Integer>comparingByKey())))
                            //.sorted(Collections.reverseOrder(Map.Entry.<String, Integer>comparingByValue()).thenCompareing(Map.Entry.comparingByKey()))
                            //.sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                            //.collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2)->e1, LinkedHashMap::new))
                            //.entrySet()
                            //.stream()
                            .limit(K)
                            .map(Map.Entry::getKey)
                            .collect(Collectors.toList());
        return res;
      
    }

    /*
    private String clean(String str) {
      String res = str;
      for (char st : punc) {
        res = res.replace(st, '\0');
      }
      return res.toLowerCase();
    }
    */
}
