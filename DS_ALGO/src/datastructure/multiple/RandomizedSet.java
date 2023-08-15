package datastructure.multiple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/*
 * 657 · Insert Delete GetRandom O(1)
Algorithms
Medium
Accepted Rate
55%

DescriptionSolutionNotesDiscussLeaderboard
Description
Design a data structure that supports all following operations in average O(1) time.

insert(val): Inserts an item val to the set if not already present.
remove(val): Removes an item val from the set if present.
getRandom: Returns a random element from current set of elements. Each element must have the same probability of being returned.
Example
// Init an empty set.
RandomizedSet randomSet = new RandomizedSet();

// Inserts 1 to the set. Returns true as 1 was inserted successfully.
randomSet.insert(1);

// Returns false as 2 does not exist in the set.
randomSet.remove(2);

// Inserts 2 to the set, returns true. Set now contains [1,2].
randomSet.insert(2);

// getRandom should return either 1 or 2 randomly.
randomSet.getRandom();

// Removes 1 from the set, returns true. Set now contains [2].
randomSet.remove(1);

// 2 was already in the set, so return false.
randomSet.insert(2);

// Since 2 is the only number in the set, getRandom always return 2.
randomSet.getRandom();
Tags
Hash Table
Company
Twitter
Amazon
Facebook
Uber
Google
Related Problems
954
Insert Delete GetRandom O(1) - Duplicates allowed
Hard
 */
public class RandomizedSet {
	Map<Integer, Integer> value2Index;
    List<Integer> nums;

    public RandomizedSet() {
        // do intialization if necessary
        value2Index = new HashMap<>();
        nums = new ArrayList<>();
        

    }

    /*
     * @param val: a value to the set
     * @return: true if the set did not already contain the specified element or false
     */
    public boolean insert(int val) {
        // write your code here
        if (value2Index.containsKey(val)) {
            return false;
        }

        
        value2Index.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /*
     * @param val: a value from the set
     * @return: true if the set contained the specified element or false
     */
    public boolean remove(int val) {
        // write your code here
        if (!value2Index.containsKey(val)) {
            return false;
        }

/*
        if (nums.size()==1) {
            nums.remove(0);
            value2Index.remove(val);
            return true;
        }
*/
        
        int index = value2Index.get(val);
        int last = nums.get(nums.size()-1);
        //if (index != nums.size()-1) {
            
            nums.set(index, last);
        //}
        
        nums.remove(nums.size()-1);
        value2Index.remove(val);
        value2Index.put(last, index);
        return true;
    }

    /*
     * @return: Get a random element from the set
     */
    public int getRandom() {
        // write your code here
        int ran = new Random().nextInt(nums.size());
        return nums.get(ran);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */
