package amazon;

/*
 Amazon | OA 2019 | Top N Buzzwords
4
Anonymous User
Anonymous User
Last Edit: March 13, 2020 7:36 AM

921 VIEWS

You work on a team whose job is to understand the most sought after toys for the holiday season. A teammate of yours has built a webcrawler that extracts a list of quotes about toys from different articles. 
You need to take these quotes and identify which toys are mentioned most frequently. Write an algorithm that identifies the top N toys out of a list of quotes and list of toys.

Your algorithm should output the top N toys mentioned most frequently in the quotes.

Input:
The input to the function/method consists of five arguments:

numToys, an integer representing the number of toys
topToys, an integer representing the number of top toys your algorithm needs to return;
toys, a list of strings representing the toys,
numQuotes, an integer representing the number of quotes about toys;
quotes, a list of strings that consists of space-sperated words representing articles about toys

Output:
Return a list of strings of the most popular N toys in order of most to least frequently mentioned

Note:
The comparison of strings is case-insensitive. If the value of topToys is more than the number of toys, return the names of only the toys mentioned in the quotes. 
If toys are mentioned an equal number of times in quotes, sort alphabetically.

Example 1:

Input:
numToys = 6
topToys = 2
toys = ["elmo", "elsa", "legos", "drone", "tablet", "warcraft"]
numQuotes = 6
quotes = [
"Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
"The new Elmo dolls are super high quality",
"Expect the Elsa dolls to be very popular this year, Elsa!",
"Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
"For parents of older kids, look into buying them a drone",
"Warcraft is slowly rising in popularity ahead of the holiday season"
];

Output:
["elmo", "elsa"]

Explanation:
elmo - 4
elsa - 4
"elmo" should be placed before "elsa" in the result because "elmo" appears in 3 different quotes and "elsa" appears in 2 different quotes.
amazon oa
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class TopNToyBuzzwords {

	public static void main(String[] args) {
        String [] toys = {"elmo", "elsa", "legos", "drone", "tablet", "warcraft"};
        int k = 2;
        String [] reviews = {
                "Elmo is the hottest of the season! Elmo will be on every kid's wishlist!",
                "The new Elmo dolls are super high quality",
                "Expect the Elsa dolls to be very popular this year, Elsa!",
                "Elsa and Elmo are the toys I'll be buying for my kids, Elsa is good",
                "For parents of older kids, look into buying them a drone",
                "Warcraft is slowly rising in popularity ahead of the holiday season"
        };
        String [] solution = topKCompetitors(reviews,toys,k);
        System.out.println(Arrays.toString(solution));
    }

    private static String[] topKCompetitors(String[] reviews, String[] toys, int k) {
        reviews = preProcessReviews(reviews);
        HashMap<String,Integer> countToyMentions = new HashMap<>();
        HashMap<String, Integer> countToyMentionsDistinctReviews = new HashMap<>();
        String [] topKToys = new String[k];

        for (String toy : toys) {
            countToyMentions.put(toy, 0);
            countToyMentionsDistinctReviews.put(toy, 0);
        }


        for (int i = 0; i < reviews.length; i++){
            for (String toy: toys){
                if(reviews[i].contains(toy)){
                    countToyMentionsDistinctReviews.put(toy, countToyMentionsDistinctReviews.get(toy)+1);
                }
            }
        }

        for (int i = 0; i < reviews.length; i++){
                for(String reviewWord : reviews[i].split(" ")) {
                    if (countToyMentions.containsKey(reviewWord)) {
                        countToyMentions.put(reviewWord, countToyMentions.get(reviewWord) + 1);
                    }
                }
        }

        /*
        As mentioned in the name of this heap (worstKToysHeap), the order in which this heap saves items is exactly opposite
        as to how the K best toys are identified. Here's how seniority in this heap is decided (all criteria opposite to k best toys criteria):
        1. First, toys with 'lowest' count of appearance in the review are preferred
        2. Second, if we have a tie from step 1, we prefer a toy with 'least' count of 'distinct' reviews in which toy name appears
        3. Lastly, if we still have a tie after step 1 and step 2, we prefer a toys in 'reverse' alphabetical order
         */
        PriorityQueue<String> worstKToysHeap = new PriorityQueue<>(new Comparator<String>() {
        	@Override
            public int compare(String t1, String t2) {
                if(countToyMentions.get(t1) != countToyMentions.get(t2))
                    return countToyMentions.get(t1) - countToyMentions.get(t2);
                else if(countToyMentionsDistinctReviews.get(t1) != countToyMentionsDistinctReviews.get(t2))
                    return countToyMentionsDistinctReviews.get(t1) - countToyMentionsDistinctReviews.get(t2);
                else
                    return t2.compareTo(t1);
            }
        });

        for (String toy : toys) {
            worstKToysHeap.offer(toy);
            if(worstKToysHeap.size() > k)
                worstKToysHeap.poll();
        }

        int i = 0;
        while (!worstKToysHeap.isEmpty()){
            topKToys[k-1-i++] = worstKToysHeap.poll();
        }
        return topKToys;
    }

    private static String[] preProcessReviews(String[] reviews) {
        for (int i = 0; i < reviews.length; i++){
            reviews[i] = reviews[i].replaceAll("[^ a-zA-Z]", "").toLowerCase();
        }
        return reviews;
    }
	
}
