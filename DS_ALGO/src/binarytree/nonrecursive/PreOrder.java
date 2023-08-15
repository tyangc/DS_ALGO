package binarytree.nonrecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;
public class PreOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Use iteration
	public List<Integer> preOrder(TreeNode root) {
		
		List<Integer> res = new ArrayList<>();
		
		if (root==null) return res;
		
		Stack<TreeNode> stack = new Stack<>();
		stack.push(root);
		
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			res.add(cur.val);
			if (cur.right!=null) {
				stack.push(cur.right);
			}
			
			if (cur.left!=null) {
				stack.push(cur.left);
			}
			
		}
		
		return res;
	}
	
	//Use divide and conquer - recursive
	public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();
        helper(root, res);

        
        return res;
    }

    private void helper(TreeNode root, List<Integer> res) {
      if (root == null) return;

      res.add(root.val);

      if (root.left != null) helper(root.left, res);  //Put if here will sped up the execution
      if (root.right != null) helper(root.right, res);
    }
}
