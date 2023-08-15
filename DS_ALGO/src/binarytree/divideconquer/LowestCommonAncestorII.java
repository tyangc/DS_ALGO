package binarytree.divideconquer;

import java.util.HashSet;
import java.util.Set;

/*
 474 · Lowest Common Ancestor II
Algorithms
Easy
Accepted Rate
50%

Description
Given the root and two nodes in a Binary Tree. Find the lowest common ancestor(LCA) of the two nodes.

The nearest common ancestor of two nodes refers to the nearest common node among all the parent nodes of two nodes (including the two nodes).

In addition to the left and right son pointers, each node also contains a father pointer, parent, pointing to its own father.

Example
Example 1:

Input：{4,3,7,#,#,5,6},3,5
Output：4
Explanation：
     4
     / \
    3   7
       / \
      5   6
LCA(3, 5) = 4
Example 2:

Input：{4,3,7,#,#,5,6},5,6
Output：7
Explanation：
      4
     / \
    3   7
       / \
      5   6
LCA(5, 6) = 7
Tags
Related Problems
578
Lowest Common Ancestor III
Medium
88
Lowest Common Ancestor of a Binary Tree
Medium
 */
public class LowestCommonAncestorII {
	 /*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        Set<ParentTreeNode> set = new HashSet<>();

        ParentTreeNode cur = A;
        while(cur != null) {
          set.add(cur);
          cur = cur.parent;
        }

        cur = B;

        while(cur != null) {
          if (set.contains(cur)) {
            return cur;
          }
          cur = cur.parent;
        }

        return null;
    }
}

class ParentTreeNode {
	     public ParentTreeNode parent, left, right; 
}