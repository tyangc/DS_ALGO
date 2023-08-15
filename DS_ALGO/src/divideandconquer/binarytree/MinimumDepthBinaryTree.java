package divideandconquer.binarytree;

import divideandconquer.binarytree.ClosestBinarySearchTreeValue.TreeNode;

//See bfs package.MinimumDepthBinaryTree for description

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

        if (root.left==null && root.right==null) return 1;

        if (root.left==null) return minDepth(root.right) + 1;

        if (root.right==null) return minDepth(root.left) + 1;
        
        return Math.min(minDepth(root.right), minDepth(root.left)) + 1 ;


        
    }

}

