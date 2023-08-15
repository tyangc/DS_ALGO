package divideandconquer.binarytree;

import java.util.HashSet;
import java.util.Set;

/*
 474 · Lowest Common Ancestor II
Easy Accepted Rate 48%

DescriptionSolutionNotesDiscussLeaderboard
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
public class LCAwithParent {

	/*
     * @param root: The root of the tree
     * @param A: node in the tree
     * @param B: node in the tree
     * @return: The lowest common ancestor of A and B
     */
    public ParentTreeNode lowestCommonAncestorII(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        Set<ParentTreeNode> parentSet = new HashSet<>();
        ParentTreeNode cur = A;

        while(cur!=null) {
          parentSet.add(cur);
          cur = cur.parent;
        }

        cur = B;
        while(cur!=null) {
          if (parentSet.contains(cur)) {
            return cur;
          }

          cur = cur.parent;

        }

        return null;
    }
    
    //Very clean concise.
    public ParentTreeNode lowestCommonAncestorIITwoPointer(ParentTreeNode root, ParentTreeNode A, ParentTreeNode B) {
        // write your code here
        ParentTreeNode p1 = A, p2 = B;
    	while (p1 != p2) {
            p1 = p1.parent == null ? B : p1.parent;
            p2 = p2.parent == null ? A : p2.parent; 
        }
        return p1;
    }
}

class ParentTreeNode {
	      public ParentTreeNode parent, left, right;
	  }
