package datastructure.tree;

import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 915 · Inorder Predecessor in BST
Medium Accepted Rate 57%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary search tree and a node in it, find the in-order predecessor of that node in the BST.

If the given node has no in-order predecessor in the tree, return null

Example
Example1

Input: root = {2,1,3}, p = 1
Output: null
Example2

Input: root = {2,1}, p = 2
Output: 1
Tags
Company
Facebook
Microsoft
Related Problems
448 Inorder Successor in BST
Medium
86 Binary Search Tree Iterator
Hard
67 Binary Tree Inorder Traversal
Easy
 */
public class InorderPredecessorBST {

	/**
     * @param root: the given BST
     * @param p: the given node
     * @return: the in-order predecessor of the given node in the BST
     */
	
	//Using inorder traverse template - 
    public TreeNode inorderPredecessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root==null || p ==null) return null;

        Stack<TreeNode> st = new Stack<>();
        TreeNode pre=null;

        

        while(root!=null) {
          st.push(root);
          root = root.left;
        }

        while(!st.isEmpty()) {
          TreeNode node = st.pop();
          if (node==p) return pre;
          pre = node;

          if (node.right!=null) {
            node = node.right;
            while(node!=null) {
              st.push(node);
              node = node.left;
            }
          }
        }

        return null;
    }
    
    //Using dfs
    private TreeNode pre = null;
    public TreeNode inorderPredecessor2(TreeNode root, TreeNode p) {
        // write your code here
        if (root==null || p ==null) return null;
        dfs(root, p);
        return pre;
    }

    private void dfs(TreeNode root, TreeNode p) {
      if (root==null) return;

      if (p.val > root.val) {
        pre = root;

        dfs(root.right, p);
      } else {
        dfs(root.left, p);
      }
    }
}
