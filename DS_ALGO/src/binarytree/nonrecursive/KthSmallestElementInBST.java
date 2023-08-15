package binarytree.nonrecursive;

import java.util.Stack;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 902. Kth Smallest Element in a BST

Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.

Example
Example 1:

Input：{1,#,2},2
Output：2
Explanation：
	1
	 \
	  2
The second smallest element is 2.
Example 2:

Input：{2,1,3},1
Output：1
Explanation：
  2
 / \
1   3
The first smallest element is 1.
Challenge
What if the BST is modified (insert/delete operations) often and you need to find the kth smallest frequently? How would you optimize the kthSmallest routine?

Notice
You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 */

/*
 {38,34,40,19,36,39,41,8,23,35,37,#,#,#,48,5,11,22,29,#,#,#,#,45,49,4,7,10,14,21,#,24,31,42,46,#,#,3,#,6,#,9,#,13,18,20,#,#,26,30,33,#,44,#,47,1,#,#,#,#,#,12,#,16,#,#,#,25,27,#,#,32,#,43,#,#,#,0,2,#,#,15,17,#,#,#,28}
43
Output
16
Expected
42
 */
		
public class KthSmallestElementInBST {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: the given BST
     * @param k: the given k
     * @return: the kth smallest element in BST
     */
    public int kthSmallest(TreeNode root, int k) {
        // write your code here
        
        //List<Integer> res = new ArrayList<>();
        
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode p = root;
        int count=0;
        
        while(p!=null) {
            stack.push(p);
            p=p.left;
        }
        
        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            count++;
            if (count==k) return cur.val;
            
            cur = cur.right;
            //if (cur!=null) {  
                //stack.push(cur); //This will fail the test above
            while(cur!=null) {
                stack.push(cur);
                cur=cur.left;
            }
        
            //}
        }
        
        return -1;
    }
	
}
