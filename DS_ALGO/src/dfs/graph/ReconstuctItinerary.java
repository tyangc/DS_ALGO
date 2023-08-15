package dfs.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

/*
 * https://leetcode.com/problems/reconstruct-itinerary/
 * https://www.lintcode.com/problem/1288/solution/17204
 * 
 332. Reconstruct Itinerary
Medium

2855

1303

Add to List

Share
You are given a list of airline tickets where tickets[i] = [fromi, toi] represent the departure and the arrival airports of one flight. Reconstruct the itinerary in order and return it.

All of the tickets belong to a man who departs from "JFK", thus, the itinerary must begin with "JFK". If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string.

For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
You may assume all tickets form at least one valid itinerary. You must use all the tickets once and only once.

 

Example 1:


Input: tickets = [["MUC","LHR"],["JFK","MUC"],["SFO","SJC"],["LHR","SFO"]]
Output: ["JFK","MUC","LHR","SFO","SJC"]
Example 2:


Input: tickets = [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"] but it is larger in lexical order.
 

Constraints:

1 <= tickets.length <= 300
tickets[i].length == 2
fromi.length == 3
toi.length == 3
fromi and toi consist of uppercase English letters.
fromi != toi
Accepted
219,455
Submissions
568,537
Seen this question in a real interview before?

Yes

No
0 ~ 6 months6 months ~ 1 year1 year ~ 2 years

Microsoft
|
7

Uber
|
6

Google
|
4

Expedia
|
4

Amazon
|
4

Bloomberg
|
3

Intuit
|
3

Snapchat
|
3

Qualtrics
|
3

eBay
|
2

Twilio
|
2

Walmart Labs
|
2
 */
public class ReconstuctItinerary {
	public List<String> findItinerary(String[][] tickets) {
        Map<String, PriorityQueue<String>> hashmap = new HashMap<String, PriorityQueue<String>>();
        List<String> list = new ArrayList<String>();
        String cur = "JFK";
        int length = tickets.length;
        for (int i = 0; i < length; i++) {
            if (!hashmap.containsKey(tickets[i][0])) {
                hashmap.put(tickets[i][0], new PriorityQueue<String>());
            }
            hashmap.get(tickets[i][0]).add(tickets[i][1]);
        }
        dfs(cur, hashmap, list);
        Collections.reverse(list);
        return list;
    }
    public void dfs(String cur, Map<String, PriorityQueue<String>> hashmap, List<String> list) {
        while (hashmap.containsKey(cur) && !hashmap.get(cur).isEmpty()) {
            dfs(hashmap.get(cur).poll(), hashmap, list);
        }
        list.add(cur);
    }
}
