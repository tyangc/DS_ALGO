package divideandconquer.binarytree;

import java.util.Stack;

import divideandconquer.binarytree.ClosestBinarySearchTreeValue.TreeNode;

/*
 453 · Flatten Binary Tree to Linked List
Algorithms
Easy
Accepted Rate 
43%

DescriptionSolutionNotesDiscussLeaderboard
Description
Flatten a binary tree to a fake "linked list" in pre-order traversal.

Here we use the right pointer in TreeNode as the next pointer in ListNode.

Don't forget to mark the left child of each node to null. Or you will get Time Limit Exceeded or Memory Limit Exceeded.

Example
Example 1:

Input:{1,2,5,3,4,#,6}
Output：{1,#,2,#,3,#,4,#,5,#,6}
Explanation：
     1
    / \
   2   5
  / \   \
 3   4   6

1
\
 2
  \
   3
    \
     4
      \
       5
        \
         6
Example 2:

Input:{1}
Output:{1}
Explanation：
         1
         1
Challenge
Do it in-place without any extra memory.

Tags
Depth First Search/DFS
Binary Tree
Divide and Conquer
Company
Microsoft
Related Problems
601
Flatten 2D Vector
Medium
528
Flatten Nested List Iterator
Medium
242
Convert Binary Tree to Linked Lists by Depth
Easy
378
Convert Binary Tree to Doubly Linked List
Medium
106
Convert Sorted List to Binary Search Tree
Medium
 */
public class FlatBinaryTreeLinkedList {

	/**
     * @param root: a TreeNode, the root of the binary tree
     * @return: nothing
     */
	//Use recursion
	public void flatten(TreeNode root) {
        // write your code here
        dfs(root);
	}
	
	private TreeNode dfs(TreeNode root) {
	      if (root==null) return null;
	      TreeNode tmp = root.right;
	      TreeNode tail = null; //dfs(root.left);
	        if (root.left != null) {
	          
	          root.right = root.left;
	          tail = dfs(root.left);
	          root.left = null;
	          if ( tmp!=null) {
	            tail.right = tmp;
	            //return dfs(tmp);
	          } else {
	            return tail;
	          }
	        } else {
	          if (tmp == null) return root;
	          //else return r;
	        
	        //return tail;
	        //flatten(tmp);
	        }
	          //TreeNode tailRight =  dfs(tmp);
	          //return tailRight==null? tmp : tailRight;

	          return dfs(tmp);
	        
	    }
	
	//Use iteration: 
    public void flatten1(TreeNode root) {
        // write your code here
        if (root==null) return;
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode p = root;
        
        while(p!=null || !stack.isEmpty()) {
            if (p.right!=null) {
                stack.push(p.right);
            }
            
            if (p.left!=null) {
            	//if (p.right != null) stack.push(p.right);  //It would be wrong if placed this statement here
                p.right = p.left;
                p.left = null;
            } else if (!stack.isEmpty()) {
                 TreeNode tmp = stack.pop();
                 p.right = tmp;
            }
            p = p.right;
        } 
        
        
    }
}
