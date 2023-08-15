package bfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
 70 · Binary Tree Level Order Traversal II
Medium Accepted Rate1%

Description
Given a binary tree, return the bottom-up level order traversal of its nodes' values. (ie, from left to right, level by level from leaf to root).

Example
Example 1:

Input:
{1,2,3}
Output:
[[2,3],[1]]
Explanation:
    1
   / \
  2   3
it will be serialized {1,2,3}
level order traversal
Example 2:

Input:
{3,9,20,#,#,15,7}
Output:
[[15,7],[9,20],[3]]
Explanation:
    3
   / \
  9  20
    /  \
   15   7
it will be serialized {3,9,20,#,#,15,7}
level order traversal
Tags
Related Problems
760
Binary Tree Right Side View
Medium
618
Search Graph Nodes
 */
public class BinaryTreeLevelOrderTraversalII {
	/**
     * @param root: A tree
     * @return: buttom-up level order a list of lists of integer
     */
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();

        if (root==null) return res;

        List<TreeNode> first = new ArrayList<>();
        List<TreeNode> second = new ArrayList<>();
        List<Integer> tmp = null;

        first.add(root);

        while(first.size()>0) {
          tmp = new ArrayList<>();
          second = new ArrayList<>();
          for (TreeNode t : first) {
            tmp.add(t.val);
            if (t.left!=null) {
              second.add(t.left);
            }
            if (t.right!=null) {
              second.add(t.right);
            }
          }
          res.add(tmp);
          first = second;
        }

        Collections.reverse(res);
        return res;

    }
}
