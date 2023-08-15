package binarytree.divideconquer;



/*
 Given a binary tree, find the subtree with maximum sum. Return the root of the subtree.

LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum sum and the given binary tree is not an empty tree.

说明
样例
Example 1:

Input:
{1,-5,2,0,3,-4,-5}
Output:3
Explanation:
The tree is look like this:
     1
   /   \
 -5     2
 / \   /  \
0   3 -4  -5
The sum of subtree 3 (only one node) is the maximum. So we return 3.
Example 2:

Input:
{1}
Output:1
Explanation:
The tree is look like this:
   1
There is one and only one subtree in the tree. So we return 1.
  
 */
public class MaximumSubtreeNode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: the root of binary tree
     * @return: the maximum weight node
     */
    public static TreeNode findSubtree(TreeNode root) {
        // write your code here
        if (root==null) return null;
        
        return helper1(root).node;
        
    }
    
    private static ResultType helper1(TreeNode root) {
    	
    	if (root==null) return new ResultType(null, 0, Integer.MIN_VALUE);
    	
    	ResultType left = helper1(root.left);
    	ResultType right = helper1(root.right);
    	
    	int sum = root.val + left.resultSum + right.resultSum;
    	
    	ResultType newResult = new ResultType(root, sum, sum);
    	
    	if (left.nodeSum>newResult.nodeSum) {
    		newResult.node = left.node;
    		newResult.nodeSum = left.nodeSum;
    	}
    	
    	if (right.nodeSum>newResult.nodeSum) {
    		newResult.node = right.node;
    		newResult.nodeSum = right.nodeSum;
    	}
    	return newResult;
    }
    
    private static ResultType helper(TreeNode root) {
        //if (root==null) return null;
        
         TreeNode maxNode = null;
         int newMax = Integer.MIN_VALUE;
         int sum = 0; //Integer.MIN_VALUE;
         ResultType right = null, left = null;
         
        if (root.left==null && root.right==null) {
            return new ResultType(root, root.val, root.val);
        } else if (root.left==null) {
            right = helper(root.right);
            maxNode = right.node;
            newMax = right.nodeSum;
            if (root.val+right.resultSum>right.nodeSum) {
                maxNode = root;
                newMax = root.val+right.resultSum;
            }
            
            return new ResultType(maxNode, root.val+right.resultSum, newMax);
        } else if (root.right==null) {
            left = helper(root.left);
            maxNode = left.node;
            newMax = left.nodeSum;
            if (root.val+left.resultSum>left.nodeSum) {
                maxNode = root;
                newMax = root.val+left.resultSum;
            }
            
            return new ResultType(maxNode, root.val+left.resultSum, newMax);
        } else {
            left = helper(root.left);
            right = helper(root.right);
            maxNode = left.node;
            newMax = left.nodeSum;
            
            sum = root.val + left.resultSum + right.resultSum;
            if (right.nodeSum>newMax) {
                maxNode = right.node;
                newMax = right.nodeSum;
            }
            
            if (sum > newMax) {
                maxNode = root;
                newMax = sum;
            }
            
            return new ResultType(maxNode, sum, newMax);
            
        }
    } 
    
    
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

class ResultType {
    TreeNode node;
    int resultSum;
    int nodeSum;
    
    public ResultType(TreeNode n, int s, int ns) {
        this.node = n;
        this.resultSum = s;
        this.nodeSum = ns;
    }
    
}
