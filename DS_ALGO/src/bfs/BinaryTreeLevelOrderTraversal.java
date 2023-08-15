package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 69 · Binary Tree Level Order Traversal

Easy
Accepted Rate
0%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, return the level order traversal of its nodes' values. (ie, from left to right, level by level).

The first data is the root node, followed by the value of the left and right son nodes, and "#" indicates that there is no child node.
The number of nodes does not exceed 20.
Example
Example 1:

Input：{1,2,3}
Output：[[1],[2,3]]
Explanation：
  1
 / \
2   3
it will be serialized {1,2,3}
level order traversal
Example 2:

Input：{1,#,2,3}
Output：[[1],[2],[3]]
Explanation：
1
 \
  2
 /
3
it will be serialized {1,#,2,3}
level order traversal
Challenge
Challenge 1: Using only 1 queue to implement it.

Challenge 2: Use BFS algorithm to do it.
 */
public class BinaryTreeLevelOrderTraversal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode root) {
        // write your code here

        List<List<Integer>> res = new ArrayList<>();
        if (root==null) return res;

        Queue<TreeNode> que = new LinkedList<>();
        List<Integer> tmp = null;

        que.offer(root);

        while(!que.isEmpty()) {
          int size = que.size();
          tmp = new ArrayList<>();

          for ( int i=0; i<size; i++ ) {
              TreeNode cur = que.poll();
              tmp.add(cur.val);
              if (cur.left!=null) que.offer(cur.left);
              if (cur.right!=null) que.offer(cur.right);
          }

          res.add(tmp);
        }

        return res;
    }
}
