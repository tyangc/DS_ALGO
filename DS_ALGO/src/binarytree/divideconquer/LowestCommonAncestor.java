package binarytree.divideconquer;

public class LowestCommonAncestor {
	/*
     * @param root: The root of the binary search tree.
     * @param A: A TreeNode in a Binary.
     * @param B: A TreeNode in a Binary.
     * @return: Return the least common ancestor(LCA) of the two nodes.
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        
        return helper(root, A, B);
        
    }
    
    private TreeNode helper(TreeNode root, TreeNode A, TreeNode B) {
        
        if (root == null || root == A || root == B) return root;

        TreeNode left = helper(root.left, A, B);

        TreeNode right = helper(root.right, A, B);

        if (left == null && right == null ) return null;

        if (left != null && right != null) return root;

        return left !=null ? left : right;
        
    }
}
