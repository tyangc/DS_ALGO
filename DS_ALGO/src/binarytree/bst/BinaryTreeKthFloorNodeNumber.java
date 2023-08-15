package binarytree.bst;

/*
 939 · Binary Tree Kth Floor Node

Medium
Accepted Rate
78%

Description
Return the number of nodes in the kth layer(The layer number starts from 1 and the root node is layer 1).

Example
Example1

Input: root = {3,9,20,#,#,15,7}, k = 2
Output: 2
Explanation:
  3
 / \
9  20
  /  \
 15   7
Example2

Input: root = {3,1,2}, k = 1
Output: 1
Explanation:
  3
 / \
1   2
 */
import java.util.LinkedList;
import java.util.Queue;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

public class BinaryTreeKthFloorNodeNumber {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param root: the root node
     * @param k: an integer
     * @return: the number of nodes in the k-th layer of the binary tree
     */
    public int kthfloorNode(TreeNode root, int k) {
        // Write your code here
        if (root==null) return 0;

        Queue<TreeNode> que = new LinkedList<>();

        int level = 0;

        que.offer(root);

        while(!que.isEmpty()) {
          int size = que.size();
          for (int i=0; i<size; i++) {
              TreeNode cur = que.poll();
              if (cur.left!=null) que.offer(cur.left);
              if (cur.right!=null) que.offer(cur.right);
          }

          level++;
          if (level==k) return size;

        }

        return 0;


    }
}
