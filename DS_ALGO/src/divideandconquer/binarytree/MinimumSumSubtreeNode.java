package divideandconquer.binarytree;

import divideandconquer.binarytree.ClosestBinarySearchTreeValue.TreeNode;

/*
 * 
 596. Minimum Subtree

 Given a binary tree, find the subtree with minimum sum. Return the root of the subtree.
 Example
Example 1:

Input:
{1,-5,2,1,2,-4,-5}
Output:1
Explanation:
The tree is look like this:
     1
   /   \
 -5     2
 / \   /  \
1   2 -4  -5 
The sum of whole tree is minimum, so return the root.
Example 2:

Input:
{1}
Output:1
Explanation:
The tree is look like this:
   1
There is one and only one subtree in the tree. So we return 1.
Notice
LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with minimum sum and the given binary tree is not an empty tree.


 */

public class MinimumSumSubtreeNode {

	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        Result res =  new Result(Integer.MAX_VALUE, root);
        findMinSubTree(root, res);
        return res.root;
    }
    
    private int findMinSubTree(TreeNode root, Result res) {
        if (root==null) return 0;
        
        int left = findMinSubTree(root.left, res);
        int right = findMinSubTree(root.right, res);
        
        int sumCur = root.val + left + right;
        if (res.sum>sumCur) {
            res.sum=sumCur; 
            res.root = root;
        }
        
        return sumCur;
    }
    
    class Result {
        int sum;
        TreeNode root;
        public Result(int sum, TreeNode cur) {
            this.root = cur;
            this.sum = sum;
        }
    }

	
}
