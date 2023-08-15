package divideandconquer.binarytree;

import java.util.ArrayList;
import java.util.List;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 480 · Binary Tree Paths
Easy Accepted Rate 41%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, return all root-to-leaf paths.

Example
Example 1:

Input：{1,2,3,#,5}
Output：["1->2->5","1->3"]
Explanation：
   1
 /   \
2     3
 \
  5
Example 2:

Input：{1,2}
Output：["1->2"]
Explanation：
   1
 /   
2     
Tags
Company
Google
Apple
Facebook
Related Problems
717
Tree Longest Path With Same Value

 */
public class BinaryTreePathAll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: the root of the binary tree
     * @return: all root-to-leaf paths
     */
    public List<String> binaryTreePaths(TreeNode root) {
	        // write your code here
	        List<String> res = new ArrayList<>();

	        if (root==null) return res;

					List<String> leftList = binaryTreePaths(root.left);

					List<String> rightList = binaryTreePaths(root.right);

					for (String left : leftList) {

	        	res.add(root.val + "->" + left);

					}

					for (String right : rightList) {

	        	res.add(root.val + "->" + right);

					}

	        if (res.size()==0) res.add(root.val + "");

	        return res;

	  }

}
