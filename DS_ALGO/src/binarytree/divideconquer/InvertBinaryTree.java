package binarytree.divideconquer;
/*
 175 · Invert Binary Tree
Algorithms
Easy
Accepted Rate
57%

Description
Invert a binary tree.Left and right subtrees exchange.

Example
Example 1:

Input: {1,3,#}
Output: {1,#,3}
Explanation:
	  1    1
	 /  =>  \
	3        3
Example 2:

Input: {1,2,3,#,#,4}
Output: {1,3,2,#,4}
Explanation: 
	
      1         1
     / \       / \
    2   3  => 3   2
       /       \
      4         4
Challenge
Do it in recursion is acceptable, can you do it without recursion?

Tags
Depth First Search/DFS
Binary Tree
Divide and Conquer
 */
public class InvertBinaryTree {

	public void dfs(TreeNode root) {
	      if (root== null) return;
	      dfs(root.left);
	      dfs(root.right);
	      TreeNode tmp = root.left;
	      root.left = root.right;
	      root.right = tmp;
	      //dfs(root.left);  Here also works
	      //dfs(root.right);

	    }
}
