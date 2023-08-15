package binarytree.divideconquer;

//import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 描述
Given a binary tree, find its maximum depth.

The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 */
public class MaximumDepthOfBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxDepth(TreeNode root) {
        // write your code here
        if (root==null) return 0;
        
        int res = divideConquer(root);
        return res;
    }
    
    private int divideConquer(TreeNode root) {
        if (root==null) return 0;
        
        int left = divideConquer(root.left);
        int right = divideConquer(root.right);
        
        return Math.max(left, right) + 1;
    }
    
    class TreeNode {
    	int val;
    	TreeNode left;
    	TreeNode right;
    	
    	public TreeNode(int val) {
    		this.val = val;
    		left = right = null;
    	}
    }
}
