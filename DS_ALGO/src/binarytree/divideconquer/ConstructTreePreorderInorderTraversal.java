package binarytree.divideconquer;
/*
 * 73 · Construct Binary Tree from Preorder and Inorder Traversal
Algorithms
Medium


Description
Given preorder and inorder traversal of a tree, construct the binary tree.

You may assume that duplicates do not exist in the tree.

Example
Example 1:

Input:

preorder traversal = []
inorder traversal = []
Output:

{}
Explanation:

Binary tree is empty

Example 2:

Input:

preorder traversal = [2,1,3]
inorder traversal = [1,2,3]
Output:

{2,1,3}
Explanation:

The binary tree is as follows
2
/ \
1    3

Tags
Depth First Search/DFS
Binary Tree
 */
public class ConstructTreePreorderInorderTraversal {
	/**
     * @param preorder: A list of integers that preorder traversal of a tree
     * @param inorder: A list of integers that inorder traversal of a tree
     * @return: Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here

        TreeNode root = build(preorder, 0, preorder.length-1, inorder, 0, inorder.length-1);
        return root;
    }

    private int find(int[] arr, int start, int end, int key) {
      for (int i=start; i<=end; i++) {
        if (arr[i] == key) return i;
      }

      return -1;
    }

    private  TreeNode build(int[] preorder, int prestart, int preend, int[] inorder, int instart, int inend) {
      //if (instart > inend) return null;
      if (prestart > preend) return null;

      TreeNode root = new TreeNode(preorder[prestart]);

      int pos = find(inorder, instart, inend, preorder[prestart]);

      TreeNode left = build(preorder, prestart+1, prestart + pos-instart, inorder, instart, pos-1);
      root.left = left;

      TreeNode right = build(preorder, preend - inend + pos+1, preend, inorder, pos+1, inend);
      root.right = right;

      return root;

      
    }
}
