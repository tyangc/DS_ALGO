package dfs.treenode;

import java.util.Stack;

/*
 * 468 · Symmetric Binary Tree
Algorithms
Easy
Accepted Rate
47%
Description
Solution12
Notes
Discuss99+
Leaderboard
Record
Description
Given a binary tree, check whether it is a mirror of itself (i.e., symmetric around its center).

Wechat reply 【Two Sigma】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


Example
Example 1:

Input: {1,2,2,3,4,4,3}
Output: true
Explanation:
         1
        / \
       2   2
      / \ / \
      3 4 4 3

is a symmetric binary tree.
Example 2:

Input: {1,2,2,#,3,#,3}
Output: false
Explanation:
         1
        / \
        2  2
        \   \
         3   3

is not a symmetric binary tree.
Challenge
Can you solve it both recursively and iteratively?

Tags
Binary Tree
Depth First Search/DFS
Divide and Conquer
Related Problems

467
Complete Binary Tree
Easy

469
Same Tree
Easy
 */
public class SymmetricBinaryTree {
	//Use Recursive D&C
	
	public boolean isSymmetric(TreeNode root) {
        // write your code here
        if (root == null) return true;
        return isMirrored(root.left, root.right);

    }

    private boolean isMirrored(TreeNode a, TreeNode b ) {
        if (a==null && b==null) return true;

        if (a==null || b==null) return false;

        if (a.val != b.val) return false;

        return isMirrored(a.left, b.right) && isMirrored(a.right, b.left);
    }
    
    //Using Iteration
    public boolean isSymmetricIteration(TreeNode root) {
        // write your code here
        if (root == null) return true;
        
        String preOrderLeft = preOrder(root.left, false);
        String preOrderRight = preOrder(root.right, true);

        if (!preOrderLeft.equals(preOrderRight)) return false;

        String inOrderLeft = inOrder(root.left, false);
        String inOrderRight = inOrder(root.right, true);

        return inOrderLeft.equals(inOrderRight);

    }

    private String preOrder(TreeNode node, boolean reverse) {
        StringBuilder sb = new StringBuilder();

        if (node==null) return sb.toString();

        Stack<TreeNode> stack = new Stack<>();

        stack.push(node);

        while(!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            sb.append(cur.val);

            if (reverse) {
                if (cur.left != null) stack.push(cur.left);
            } else {
                if (cur.right != null) stack.push(cur.right);
            }

            if (reverse) {
                if (cur.right != null) stack.push(cur.right);
            } else {
                if (cur.left != null) stack.push(cur.left);
            }
        }

        return sb.toString();

    }


    private String inOrder(TreeNode node, boolean reverse) {
        StringBuilder sb = new StringBuilder();

        if (node==null) return sb.toString();

        Stack<TreeNode> stack = new Stack<>();

        while (node != null) {
            stack.push(node);
            if (reverse) node = node.right;
            else node = node.left;
        }

        while (!stack.isEmpty()) {
            TreeNode cur = stack.pop();
            sb.append(cur.val);
            if (reverse) cur = cur.left;
            else cur = cur.right;

            while(cur != null) {
                stack.push(cur);
                if (reverse) cur = cur.right;
                else cur = cur.left;
            }
        }

        return sb.toString();
    }
}
