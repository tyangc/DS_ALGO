package binarytree.divideconquer;

import java.util.Arrays;

/*
 * 1593 · Construct Binary Tree from Preorder and Postorder Traversal
Algorithms
Medium
Accepted Rate
66%
Description
Return any binary tree that matches the given preorder and postorder traversals.

Values in the traversals pre and post are distinct positive integers.

1 <= pre.length == post.length <= 30
pre[] and post[] are both permutations of 1, 2, ..., pre.length.
It is guaranteed an answer exists. If there exists multiple answers, you can return any of them.
Example
Example 1:

Input：pre = [1,2,4,5,3,6,7], post = [4,5,2,6,7,3,1]
Output：[1,2,3,4,5,6,7]
Explanation：
     1
    / \
   2   3
  / \ / \
 4  5 6  7
Example 2:

Input：pre = [1,2,3,4], post = [3,2,4,1]
Output：[1,2,4,3]
Explanation：
   1
  / \
 2   4
 /
3
Tags
Company
Google
 */
public class ConstructTreePreorderPostorderTraversal {
	public TreeNode constructFromPrePost(int[] pre, int[] post) {
		if (pre.length == 0) return null;
        if (post.length == 0) return null;

        if (pre.length == 1) return new TreeNode(pre[0]);

        TreeNode root = new TreeNode(pre[0]);
        int L = find(pre[1], post) + 1;
        int N = pre.length;

        TreeNode left = constructFromPrePost(Arrays.copyOfRange(pre, 1, L+1), Arrays.copyOfRange(post, 0, L));
        root.left = left;

        TreeNode right = constructFromPrePost(Arrays.copyOfRange(pre, L+1, N), Arrays.copyOfRange(post, L, N));
        root.right = right;

        return root;
	}
	
	private int find(int val, int[] arr) {
	      for (int i=arr.length-1; i>=0; i--) {
	        if (arr[i] == val) return i;
	      }

	      return -1;
	}
}
