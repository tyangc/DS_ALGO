package binarytree.divideconquer;

//import binarytree.bst.TreeNode;

/*
 描述
Given a binary tree, determine if it is height-balanced.

For this problem, a height-balanced binary tree is defined as a binary tree in which the depth of the two subtrees of every node never differ by more than 1.

说明
样例
Example  1:
	Input: tree = {1,2,3}
	Output: true
	
	Explanation:
	This is a balanced binary tree.
		  1  
		 / \                
		2  3

	
Example  2:
	Input: tree = {3,9,20,#,#,15,7}
	Output: true
	
	Explanation:
	This is a balanced binary tree.
		  3  
		 / \                
		9  20                
		  /  \                
		 15   7 

	
Example  3:
	Input: tree = {1,#,2,3,4}
	Output: false
	
	Explanation:
	This is not a balanced tree. 
	The height of node 1's right sub-tree is 2 but left sub-tree is 0.
		  1  
		   \                
		   2                
		  /  \                
		 3   4
	
 */

public class BalancedBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: The root of binary tree.
     * @return: True if this Binary tree is Balanced, or false.
     */
    public boolean isBalanced(TreeNode root) {
        // write your code here
        if (root==null) return true;
        int[] res = divideConquer(root);
        if (res[0]==1) return true;
        return false;
    }
    
    private int[] divideConquer(TreeNode root) {
        if (root==null) {
           return new int[]{1, 0};
        }
        
        int[] resLeft = divideConquer(root.left);
        int[] resRight = divideConquer(root.right);
        
        int height = Math.max(resLeft[1], resRight[1]) + 1;
        int isBalanced = 1; 
        
        if (resLeft[0]==0 || resRight[0]==0) {
            isBalanced = 0;
        }
        
        if (Math.abs(resLeft[1] - resRight[1]) > 1) {
            isBalanced = 0;
        }
        
        int[] res = new int[] {isBalanced, height};
        return res;
    }
    
    public class TreeNode {
    	public int val;
    	public TreeNode left;
    	public TreeNode right;
    	
    	public TreeNode(int val) {
    		this.val = val;
    		left = right = null;
    	}
    }
}
