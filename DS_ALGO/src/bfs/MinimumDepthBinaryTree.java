package bfs;

import java.util.LinkedList;
import java.util.Queue;

import divideandconquer.binarytree.ClosestBinarySearchTreeValue.TreeNode;



/*
 155 · Minimum Depth of Binary Tree
Easy Accepted Rate 0%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, find its minimum depth.

The minimum depth is the number of nodes along the shortest path from the root node down to the nearest leaf node.

Example
Example 1:

Input: {}
Output: 0
Example 2:

Input:  {1,#,2,3}
Output: 3	
Explanation:
	1
	 \ 
	  2
	 /
	3    
it will be serialized {1,#,2,3}
Example 3:

Input:  {1,2,3,#,#,4,5}
Output: 2	
Explanation: 
      1
     / \ 
    2   3
       / \
      4   5  
it will be serialized {1,2,3,#,#,4,5}
 */
public class MinimumDepthBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if (root==null) return 0;

        Queue<TreeNode> que = new LinkedList<>();
        int cnt=1;
        que.offer(root);
        while(!que.isEmpty()) {
          //cnt++;
          int size = que.size();  //size() can not be in the for loop
          for (int i=0; i<size; i++) {
            TreeNode cur = que.poll();
            
            if (cur.left==null && cur.right==null) {
              return cnt;
            }

            if (cur.left!=null) {
              que.offer(cur.left);
            }
            
            if (cur.right!=null) {
              que.offer(cur.right);
            } 
            
          }
          cnt++;
        }

        return cnt;
    }
}

class TreeNode {
	 public int val;
	 public TreeNode left, right;
	 public TreeNode(int val) {
   	 this.val = val;
   	 this.left = this.right = null;
	 }
}
