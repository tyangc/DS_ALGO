package binarytree.nonrecursive;

import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 453. Flatten Binary Tree to Linked List

Flatten a binary tree to a fake "linked list" in pre-order traversal.

Here we use the right pointer in TreeNode as the next pointer in ListNode.

Example
Example 1:

Input:{1,2,5,3,4,#,6}
Output：{1,#,2,#,3,#,4,#,5,#,6}
Explanation：
     1
    / \
   2   5
  / \   \
 3   4   6

1
\
 2
  \
   3
    \
     4
      \
       5
        \
         6
Example 2:

Input:{1}
Output:{1}
Explanation：
         1
         1
Challenge
Do it in-place without any extra memory.

Notice
Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.
 */
public class FlatBinaryTreeToLinkedList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
    public void flatten(TreeNode root) {
        // write your code here
        if (root==null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode p = root;
        
        while(p!=null || !stack.isEmpty()) {
            if (p.right!=null) {
                stack.push(p.right);
            }
            
            if (p.left!=null) {
                p.right = p.left;
                p.left = null;
            } else if (!stack.isEmpty()) {
                 TreeNode tmp = stack.pop();
                 p.right = tmp;
            }
            p = p.right;
        } 
        
        
    }
}
