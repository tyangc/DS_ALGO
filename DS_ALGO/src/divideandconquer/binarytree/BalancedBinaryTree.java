package divideandconquer.binarytree;
/*
 * 93 · Balanced Binary Tree
Algorithms
Easy
Accepted Rate
44%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

Example
Example 1:

Input:

tree = {1,2,3}
Output:

true
Explanation:

This is a balanced binary tree.
          1  
         / \                
        2   3
Example 2:

Input:

tree = {1,#,2,3,4}
Output:

false
Explanation:

This is not a balanced tree. 
The height of node 1's right sub-tree is 2 but left sub-tree is 0.
           1  
            \  
            2   
           /  \ 
          3   4
Tags
Depth First Search/DFS
Binary Tree
Divide and Conquer
Related Problems
467
Complete Binary Tree
Easy
95
Validate Binary Search Tree
Medium
 */
public class BalancedBinaryTree {
	class Result {
        boolean isBalanced;
        int height;

        public Result(boolean isBalanced, int height) {
            this.isBalanced = isBalanced;
            this.height = height;
        }
    }

    public boolean isBalanced(TreeNode root) {
        // write your code here
        Result res = helper(root);
        return res.isBalanced;
    }

    private Result helper(TreeNode root) {
        if (root == null) return new Result(true, 0);

        Result resLeft = helper(root.left);
        Result resRight = helper(root.right);

        int height = Math.max(resLeft.height, resRight.height) + 1;
        boolean isBal = true;

        if (!resLeft.isBalanced || !resRight.isBalanced) isBal = false;

        if (Math.abs(resLeft.height - resRight.height) > 1) isBal = false;

        return new Result(isBal, height);
    }
}
