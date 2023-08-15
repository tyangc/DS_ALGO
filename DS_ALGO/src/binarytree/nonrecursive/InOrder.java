package binarytree.nonrecursive;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

public class InOrder {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//Use iteration
	public List<Integer> inOrder(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root==null) return res;
		
		Stack<TreeNode> stack = new Stack<>();
		while(root!=null) {
			stack.push(root);
			root = root.left;
		}
			
		while(!stack.isEmpty()) {
			TreeNode cur = stack.pop();
			res.add(cur.val);
			cur = cur.right;
			while(cur!=null) {
				stack.push(cur);
				cur = cur.left;
				
			}
		}
		
		return res;
	}
	
	//Use recursive - divide and conquer
	public List<Integer> inorderTraversal(TreeNode root) {
        // write your code here
        
		List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        dfs(root, res);
        return res;
    } 

    private void dfs(TreeNode root, List<Integer> res) {
      if (root == null) return;

      dfs(root.left, res);
      res.add(root.val);
      dfs(root.right, res);
    }
} 
