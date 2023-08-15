package datastructure.tree;

import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 448 · Inorder Successor in BST
Medium Accepted Rate 43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary search tree (See Definition) and a node in it, find the in-order successor of that node in the BST.

If the given node has no in-order successor in the tree, return null.

It's guaranteed p is one node in the given tree. (You can directly compare the memory address to find p)

Example
Example 1:

Input: {1,#,2}, node with value 1
Output: 2
Explanation:
  1
   \
    2
Example 2:

Input: {2,1,3}, node with value 1
Output: 2
Explanation: 
    2
   / \
  1   3
Binary Tree Representation

Challenge
O(h), where h is the height of the BST.


Facebook  Microsoft Pocket Gems

Related Problems
915 Inorder Predecessor in BST
Medium
701 Trim a Binary Search Tree
Medium
691 Recover Binary Search Tree
Medium
95 Validate Binary Search Tree
Medium
86 Binary Search Tree Iterator
Hard


 */
public class InorderSuccessorBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param root: The root of the BST.
     * @param p: You need find the successor node of p.
     * @return: Successor of p.
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        // write your code here
        if (root==null || p==null) return null;

        //TreeNode suc = null;
        boolean hasSuc = false;

        Stack<TreeNode> st = new Stack<>();
        

        while(root!=null) {
          st.push(root);
          root = root.left;
        }

        while(!st.isEmpty()) {
          TreeNode node = st.pop();
          if (hasSuc) {
            
            return node;
          } else {
            if (node==p) hasSuc = true;
          }
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
    
    //dfs
    private TreeNode suc = null;
    public TreeNode inorderSuccessor2(TreeNode root, TreeNode p) {
        // write your code here
        if (root==null || p==null) return null;

        dfs(root, p);

        return suc;
        
    }

    private void dfs(TreeNode root, TreeNode p) {
        if (root==null) return;

        if (p.val<root.val) {
          suc = root;
          dfs(root.left, p);
        } else {
          dfs(root.right, p);
        }
    }
}
