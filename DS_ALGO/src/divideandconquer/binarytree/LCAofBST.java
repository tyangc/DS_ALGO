package divideandconquer.binarytree;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 1311 · Lowest Common Ancestor of a Binary Search Tree
Easy Accepted Rate 67%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary search tree (BST), find the lowest common ancestor (LCA) of two given nodes in the BST.

According to the definition of LCA on Wikipedia: “The lowest common ancestor is defined between two nodes p and q as the lowest node in T that has both p and q as descendants (where we allow a node to be a descendant of itself).”

Given binary search tree: root = {6,2,8,0,4,7,9,#,#,3,5}



All of the nodes' values will be unique.
p and q are different and both values will exist in the BST.
Example
Example 1:

Input: 
{6,2,8,0,4,7,9,#,#,3,5}
2
8
Output: 6
Explanation: The LCA of nodes 2 and 8 is 6.
Example 2:

Input: 
{6,2,8,0,4,7,9,#,#,3,5}
2
4
Output: 2
Explanation: The LCA of nodes 2 and 4 is 2, since a node can be a descendant of itself according to the LCA definition.
Tags
Company
Twitter
Amazon
Facebook
Microsoft
 */
public class LCAofBST {
	
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // write your code here
		if (root==null) return null;
	
	    if (p.val>q.val) {
	      TreeNode tmp = p;
	      p = q;
	      q = tmp;
	    }
	
	    if (p.val<=root.val && root.val<=q.val) {
	      return root;
	    }
	
	    if (p.val<root.val && q.val<root.val) {
	      return lowestCommonAncestor(root.left, p, q);
	    }
	
	    if (p.val>root.val && q.val>root.val) {
	      return lowestCommonAncestor(root.right, p, q);
	    }
	    return null;
	}
}
