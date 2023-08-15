package divideandconquer.binarytree;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 578 · Lowest Common Ancestor III
Medium Accepted Rate 35%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.
The nearest common ancestor of two nodes refers to the nearest common node among all the parent nodes of two nodes (including the two nodes).
Return null if LCA does not exist.

node A or node B may not exist in tree.
Each node has a different value

Example
Example1

Input: 
{4, 3, 7, #, #, 5, 6}
3 5
5 6
6 7 
5 8
Output: 
4
7
7
null
Explanation:
  4
 / \
3   7
   / \
  5   6

LCA(3, 5) = 4
LCA(5, 6) = 7
LCA(6, 7) = 7
LCA(5, 8) = null

Example2

Input:
{1}
1 1
Output: 
1
Explanation:
The tree is just a node, whose value is 1.
Tags
Company:
LinkedIn Facebook
 */
public class LCAIII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//Better solution:
	public TreeNode lowestCommonAncestor3Shorter(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        TreeNodeWrapper LCA = new TreeNodeWrapper();  //Can be replaced by a global variable
        helper(root, A, B, LCA);
        return LCA.node;
    }
    
    private ResultType helper(TreeNode root, TreeNode A, TreeNode B, TreeNodeWrapper LCA) {
        if (root == null) {
            return new ResultType(false, false);
        }
        
        ResultType left = helper(root.left, A, B, LCA);
        ResultType right = helper(root.right, A, B, LCA);
        ResultType result = new ResultType(false, false);
        
        result.hasA = left.hasA || right.hasA || root == A;
        result.hasB = left.hasB || right.hasB || root == B;
        
        if (LCA.node == null && result.hasA && result.hasB) {
            LCA.node = root;
            System.out.println(LCA.node.val);
        }
        
        return result;
    }

	/*
     * @param root: The root of the binary tree.
     * @param A: A TreeNode
     * @param B: A TreeNode
     * @return: Return the LCA of the two nodes.
     */
    public TreeNode lowestCommonAncestor3(TreeNode root, TreeNode A, TreeNode B) {
        // write your code here
        Result res = helper(root, A, B);

        if (res.findA && res.findB) return res.node;
        return null;

    }

    Result helper(TreeNode root, TreeNode A, TreeNode B) {
        if (root==null) return new Result();
        Result res = new Result();
        if (root==A) {
          res.node = root;
          res.findA = true;
        }

        if (root==B) {
          res.node = root;
          res.findB = true;
        }

        Result left = helper(root.left, A, B);
        Result right = helper(root.right, A, B);

        if (left.findA && left.findB) return left;
        if (right.findA && right.findB) return right;

        
        if ((left.findA && right.findB) || (left.findB && right.findA)) {
          res.findA=true;
          res.findB=true;
          res.node = root;
          return res;
        }

        if (left.findA || right.findA) {
          res.findA=true;
          res.node = A;
          if (res.findB) res.node = root;
          return res;
        }
        if (left.findB || right.findB) {
          res.findB=true;
          res.node = B;
          if (res.findA) res.node = root;
          return res;
        }
        
        
        return res;
    }
}

class Result {
	  TreeNode node;
	  boolean findA, findB;

	}

class ResultType {

    //TreeNode node;
    boolean hasA, hasB;
    ResultType(boolean hasA, boolean hasB) {  //This constructor can be omitted
        this.hasA = hasA;
        this.hasB = hasB;
        
    }
}

class TreeNodeWrapper {
  TreeNode node;
}