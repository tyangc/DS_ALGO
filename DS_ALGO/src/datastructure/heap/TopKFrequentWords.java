package datastructure.heap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/*
 * https://www.lintcode.com/problem/471/
 * 471 · Top K Frequent Words
Algorithms
Medium
Accepted Rate
34%
Description
Solution37
Notes99+
Discuss3
Leaderboard
Record
Description
Given a list of words and an integer k, return the top k frequent words in the list.

Wechat reply 【Google】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


You should order the words by the frequency of them in the return list, the most frequent one comes first. If two words has the same frequency, the one with lower alphabetical order come first.

Example
Example 1:

Input:
  [
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
  ]
  k = 3
Output: ["code", "lint", "baby"]
Example 2:

Input:
  [
    "yes", "lint", "code",
    "yes", "code", "baby",
    "you", "baby", "chrome",
    "safari", "lint", "code",
    "body", "lint", "code"
  ]
  k = 4
Output: ["code", "lint", "baby", "yes"]
Challenge
Do it in O(nlogk) time and O(n) extra space.

Tags
Company
Pocket Gems
Amazon
Bloomberg
Yelp
Uber
 */
public class TopKFrequentWords {
	class Pair {
        String key;
        int value;

        public Pair(String k, int v) {
            key = k;
            value = v;
        }
    }
    public String[] topKFrequentWords(String[] words, int k) {
        // write your code here

        Map<String, Integer> word2count = new HashMap();

        for (String word : words) {
            word2count.merge(word, 1, Integer::sum);
        }

        Queue<Pair> heap = new PriorityQueue<>((a, b)-> {
            //if (a.value==b.value) return a.key.compareTo(b.key)*-1;
            //return Integer.compare(a.value,b.value);
           if (a.value!=b.value) return a.value - b.value;
           return -1*a.key.compareTo(b.key);
        });
        
        for (Map.Entry<String, Integer> e : word2count.entrySet()) {
            heap.offer(new Pair(e.getKey(), e.getValue()));
            if (heap.size()>k)heap.poll();
        }
        List<String> res = new ArrayList();

        while(heap.size()>0) {
            res.add(heap.poll().key);
        }
        
        Collections.reverse(res);
        //return res.toArray(String[]::new);
        return res.toArray(new String[0]);
        //return heap.stream().map(a->a.key).toArray(String[]::new);
        //Collections.reverse(heap.stream().map(a->a.key).collect(Collectors.toList())).stream().toArray(String[]::new);
    }
}
