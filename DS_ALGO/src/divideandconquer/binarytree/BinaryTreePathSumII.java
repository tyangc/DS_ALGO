package divideandconquer.binarytree;

import java.util.ArrayList;
import java.util.List;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

/*
 246 · Binary Tree Path Sum II

Medium
Accepted Rate
38%

DescriptionSolutionNotesDiscussLeaderboard
Description
Your are given a binary tree in which each node contains a value. Design an algorithm to get all paths which sum to a given value. The path does not need to start or end at the root or a leaf, but it must go in a straight line down.

Example
Example 1:

Input:
{1,2,3,4,#,2}
6
Output:
[[2, 4],[1, 3, 2]]
Explanation:
The binary tree is like this:
    1
   / \
  2   3
 /   /
4   2
for target 6, it is obvious 2 + 4 = 6 and 1 + 3 + 2 = 6.
Example 2:

Input:
{1,2,3,4}
10
Output:
[]
Explanation:
The binary tree is like this:
    1
   / \
  2   3
 /   
4   
for target 10, there is no way to reach it.
Tags
Related Problems
863
Binary Tree Path Sum IV
Medium
376
Binary Tree Path Sum
Easy
472
Binary Tree Path Sum III
Hard
94
Binary Tree Maximum Path Sum
Medium
 */
public class BinaryTreePathSumII {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param root: the root of binary tree
     * @param target: An integer
     * @return: all valid paths
     */
    public List<List<Integer>> binaryTreePathSum2(TreeNode root, int target) {
        // write your code here
        List<List<Integer>> res = new ArrayList<>();
        if (root==null) return res;
        dfs(root, target, new ArrayList<Integer>(), res );
        return res;

    }

    void dfs(TreeNode root, int target, List<Integer> path, List<List<Integer>> res) {
      if (root==null) return ;

      path.add(root.val);
      int sum=0;
      for (int i = path.size()-1; i>=0; i-- ) {
          sum+=path.get(i);
          if (sum==target) {
            res.add(new ArrayList<Integer>(path.subList(i, path.size())));
          }
      }

      dfs(root.left, target, path, res);
      dfs(root.right, target, path, res);
      path.remove(path.size()-1);
    }
}
