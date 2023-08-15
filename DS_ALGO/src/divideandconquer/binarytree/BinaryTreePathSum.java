package divideandconquer.binarytree;

import java.util.ArrayList;
import java.util.List;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 376 · Binary Tree Path Sum
Easy Accepted Rate 29%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, find all paths that sum of the nodes in the path equals to a given number target.

A valid path is from root node to any of the leaf nodes.

Example
Example 1:

Input:
{1,2,4,2,3}
5
Output: [[1, 2, 2],[1, 4]]
Explanation:
The tree is look like this:
	     1
	    / \
	   2   4
	  / \
	 2   3
For sum = 5 , it is obviously 1 + 2 + 2 = 1 + 4 = 5
Example 2:

Input:
{1,2,4,2,3}
3
Output: []
Explanation:
The tree is look like this:
	     1
	    / \
	   2   4
	  / \
	 2   3
Notice we need to find all paths from root node to leaf nodes.
1 + 2 + 2 = 5, 1 + 2 + 3 = 6, 1 + 4 = 5 
There is no one satisfying it.
Tags
Related Problems
1353
Sum Root to Leaf Numbers
Medium
863
Binary Tree Path Sum IV
Medium
472
Binary Tree Path Sum III
Hard
246
Binary Tree Path Sum II
Medium
 */
public class BinaryTreePathSum {

	public static void main(String[] args) {
	}
		// TODO Auto-generated method stub
		/*
	     * @param root: the root of binary tree
	     * @param target: An integer
	     * @return: all valid paths
	     */
	    public List<List<Integer>> binaryTreePathSum(TreeNode root, int target) {
	        // write your code here
	        List<List<Integer>> res = new ArrayList<>();

	        if (root==null) return res;

	        List<Integer> tmp = new ArrayList<>();

	        helper(root, target, tmp, res);
	        return res;

	    }

	    void helper(TreeNode root, int target, List<Integer> tmp, List<List<Integer>> res) {
	      
	      List<Integer> list = new ArrayList<>(tmp);
	      list.add(root.val);
	      if (root.left==null && root.right==null) {
	        
	        if (root.val == target) {
	          
	          res.add(list);
	        } else {
	          return;
	        }
	      }

	      if (root.left!=null) helper(root.left, target-root.val, list, res);
	      if (root.right!=null) helper(root.right, target-root.val, list, res);
	    }
	

}
