package binarytree.bst;

/*Given the root of a binary search tree and 2 numbers min and max, 
 * trim the tree such that all the numbers in the new tree are between min and max (inclusive). 
 * The resulting tree should still be a valid binary search tree. 
 */
public class TrimBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: given BST
     * @param minimum: the lower limit
     * @param maximum: the upper limit
     * @return: the root of the new tree 
     */
    public TreeNode trimBST(TreeNode root, int minimum, int maximum) {
        // write your code here
        if (root==null) return root;
        
        TreeNode tmp = new TreeNode(0);
        tmp.left = root;
        
        tmp = removeNode(tmp, minimum, maximum);
        
        if (tmp==null) return null;
        
        return tmp.left;
    }
    
    private TreeNode removeNode(TreeNode root, int min, int max) {
        if (root==null) return root;
        
        while (root.left!=null) {  //If only use "if" will only trim one level
            if (root.left.val<min) {
                root.left = removeNode(root.left.right, min, max);
            } else if (root.left.val>max) {
                root.left = removeNode(root.left.left, min, max);
            } else {
                root.left = removeNode(root.left, min, max);
                break;
            }
        }
        
        while (root.right!=null ) {
            if (root.right.val>max) {
                root.right = removeNode(root.right.left, min, max);
            } else if (root.right.val<min){
                root.right = removeNode(root.right.right, min, max);
            } else {
                root.right = removeNode(root.right, min, max);
                break;
            }
        }
        return root;
    }
}
