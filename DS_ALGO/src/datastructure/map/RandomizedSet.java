package datastructure.map;
/*
 * 657 · O(1)实现数组插入/删除/随机访问
算法
Medium
Accepted Rate
53%

DescriptionSolutionNotesDiscussLeaderboard
Description
设计一个数据结构实现在平均 O(1) 的复杂度下执行以下所有的操作。

insert(val): 如果这个元素不在set中，则插入。

remove(val): 如果这个元素在set中，则从set中移除。

getRandom: 随机从set中返回一个元素。每一个元素返回的可能性必须相同。

Example
// 初始化空集set
RandomizedSet randomSet = new RandomizedSet();

// 1插入set中。返回正确因为1被成功插入
randomSet.insert(1);

// 返回错误因为2不在set中
randomSet.remove(2);

// 2插入set中，返回正确，set现在有[1,2]。
randomSet.insert(2);

// getRandom 应该随机的返回1或2。
randomSet.getRandom();

// 从set中移除1，返回正确。set现在有[2]。
randomSet.remove(1);

// 2已经在set中，返回错误。
randomSet.insert(2);

// 因为2是set中唯一的数字，所以getRandom总是返回2。
randomSet.getRandom();
Tags
Company
推特
亚马逊
脸书
优步
谷歌
Related Problems
954
Insert Delete GetRandom O(1) - 允许重复
Hard
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
public class RandomizedSet {

    ArrayList<Integer> nums;
    HashMap<Integer, Integer> num2index;
    Random rand;

    public RandomizedSet() {
        // do initialize if necessary 
        nums = new ArrayList<Integer>();
        num2index = new HashMap<Integer, Integer>();  
        rand = new Random();
    }
    
    // Inserts a value to the set
    // Returns true if the set did not already contain the specified element or false
    public boolean insert(int val) {
        if (num2index.containsKey(val)) {
            return false;
        }
        
        num2index.put(val, nums.size());
        nums.add(val);
        return true;
    }
    
    // Removes a value from the set
    // Return true if the set contained the specified element or false
    public boolean remove(int val) {
        if (!num2index.containsKey(val)) {
            return false; 
        } 
        
        int index = num2index.get(val);
        if (index < nums.size() - 1) { // not the last one than swap the last one with this val
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            num2index.put(last, index);
        }
        num2index.remove(val);
        nums.remove(nums.size() - 1);
        return true;
    }
    
    // Get a random element from the set
    public int getRandom() {
        return nums.get(rand.nextInt(nums.size()));
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param = obj.insert(val);
 * boolean param = obj.remove(val);
 * int param = obj.getRandom();
 */