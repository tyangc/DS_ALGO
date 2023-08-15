package dfs.searchtree;

import java.util.ArrayList;
import java.util.List;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 * 480 · Binary Tree Paths
Algorithms
Easy
Accepted Rate
42%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, return all root-to-leaf paths.

Example
Example 1:

Input：{1,2,3,#,5}
Output：["1->2->5","1->3"]
Explanation：
   1
 /   \
2     3
 \
  5
Example 2:

Input：{1,2}
Output：["1->2"]
Explanation：
   1
 /   
2     
Tags
Company
Apple
Facebook
Google
 */
public class BinaryTreePath {
	//Using recursive dfs:
	
	public List<String> binaryTreePaths1(TreeNode root) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, root.val + "", res);
        return res;
    }

    private void dfs(TreeNode root, String path, List<String> res) {
        if (root == null) return;
        if ( root.left == null && root.right == null) {
            res.add(path);
            return;

        }

        if (root.left != null) dfs(root.left, path + "->" + root.left.val, res);
        if (root.right != null) dfs(root.right, path + "->" + root.right.val, res);
    }
	
    
    //Using divide and conquer
    
    public List<String> binaryTreePaths(TreeNode root) {
        // write your code here
        List<String> res = new ArrayList<>();
        if (root == null) {
            //
            return res;
        }

        if (root.left == null && root.right == null) {
            res.add(String.valueOf(root.val));
            return res;
        }

        List<String> left = binaryTreePaths(root.left);
        List<String> right = binaryTreePaths(root.right);
        
        for (String str : left) {
            res.add(root.val + "->" + str);
        }

        for (String str : right) {
            res.add(root.val + "->" + str);
        }

        // dfs(root, root.val + "", res);
        return res;
    }
}
