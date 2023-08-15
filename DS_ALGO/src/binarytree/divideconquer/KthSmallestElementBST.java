package binarytree.divideconquer;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/*
 * 902 · Kth Smallest Element in a BST
Algorithms
Medium
Accepted Rate
64%

Description
Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

You may assume k is always valid, 1 ≤ k ≤ BST's total elements.

Example
Example 1:

Input：{1,#,2},2
Output：2
Explanation：
	1
	 \
	  2
The second smallest element is 2.
Example 2:

Input：{2,1,3},1
Output：1
Explanation：
  2
 / \
1   3
The first smallest element is 1.
Challenge
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Tags
Binary Search Tree
Binary Tree
Divide and Conquer
Company
Bloomberg
Uber
Google
Related Problems
1094
Second Minimum Node In a Binary Tree
Easy
67
Binary Tree Inorder Traversal
Easy
 */
public class KthSmallestElementBST {
	/**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
	//Use inorder traversal
	public int kthSmallest(TreeNode root, int k) {
		Stack<TreeNode> stack = new Stack<>();
        
        TreeNode p = root;
        int count=0;
        
        while(p!=null) {
            stack.push(p);
            p=p.left;
        }
        
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            count++;
            if (count==k) return cur.val;
            
            cur = cur.right;
           
            while(cur!=null) {
            	stack.push(cur);
                cur=cur.left;
            }
        
           // }
        }
        
        return -1;
	}
	
	//Use count  of each node and divide and conquer
    public int kthSmallest1(TreeNode root, int k) {
        // write your code here
        Map<TreeNode, Integer> cnt = new HashMap<>();
        count(root, cnt);

        return helper(root, k, cnt);
    }

    private int count(TreeNode root, Map<TreeNode, Integer> map ) {
      if (root == null) return 0;

      int left = count(root.left, map);
      int right = count(root.right, map);
      map.put(root, 1 + left + right);
      return 1 + left + right; 
    }

    private int helper(TreeNode root, int k, Map<TreeNode, Integer> map) {
      if (root == null) return 0;

      int left = root.left == null ? 0 : map.get(root.left);
      
      if (left + 1 == k) return root.val;
      if (left + 1 > k) return helper(root.left, k, map);
      else return helper(root.right, k-left-1, map);

    }
}
