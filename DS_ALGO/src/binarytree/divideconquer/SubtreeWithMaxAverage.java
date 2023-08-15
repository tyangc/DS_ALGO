package binarytree.divideconquer;
/*
 * 597 · Subtree with Maximum Average
Algorithms
Easy
Accepted Rate
34%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given a binary tree, find the subtree with maximum average. Return the root of the subtree.

LintCode will print the subtree which root is your return node.
It's guaranteed that there is only one subtree with maximum average.

Example
Example 1

Input：
{1,-5,11,1,2,4,-2}
Output：11
Explanation:
The tree is look like this:
     1
   /   \
 -5     11
 / \   /  \
1   2 4    -2 
The average of subtree of 11 is 4.3333, is the maximun.
Example 2

Input：
{1,-5,11}
Output：11
Explanation:
     1
   /   \
 -5     11
The average of subtree of 1,-5,11 is 2.333,-5,11. So the subtree of 11 is the maximun.
Tags
Company
Amazon
 */
public class SubtreeWithMaxAverage {
	/**
     * @param root: the root of binary tree
     * @return: the root of the maximum average of subtree
     */
    public TreeNode findSubtree2(TreeNode root) {
        // write your code here
        Result res = findAvrMaxNode(root);
        return res.maxNode;
    }

    private Result findAvrMaxNode(TreeNode root) {
      if (root == null) {
        return new Result(-Double.MAX_VALUE, 0, 0);  //avr initialized to 0.0 won't work for negative value node
      }
      
      Result left = findAvrMaxNode(root.left);
      Result right = findAvrMaxNode(root.right);

      int curCnt = left.cnt + right.cnt + 1;
      int curSum = left.sum + right.sum + root.val;
      double curAvr = (double)curSum/curCnt;
      //System.out.println(curAvr);

      Result res = new Result(curAvr, curSum, curCnt);
      double tmp = Math.max(left.avr, right.avr);

      if (curAvr > tmp) {
        res.maxNode = root;
      } else {
        //double tmp = Math.max(left.avr, right.avr);
        res.avr = tmp;
        res.maxNode = left.avr>right.avr? left.maxNode : right.maxNode;
      }

      //if (res.maxNode == null) res.maxNode = root;
      //System.out.println("max:" + res.maxNode.val);

      

      return res;
    }

  class Result {
    double avr;
    int sum;
    int cnt;
    TreeNode maxNode;

    public Result(double avr, int sum, int cnt) {
      this.avr = avr;
      this.sum = sum; 
      this.cnt = cnt;
      this.maxNode = null;
    }
  }
  
  //Reduce the size of the Result object
  private class ResultType {
      public int sum, size;
      public ResultType(int sum, int size) {
          this.sum = sum;
          this.size = size;
      }
  }
  
  private TreeNode subtree = null;
  private ResultType subtreeResult = null;
  
  private ResultType helper(TreeNode root) {
      if (root == null) {
          return new ResultType(0, 0);
      }
      // 分治法计算左右子树的平均值
      ResultType left = helper(root.left);
      ResultType right = helper(root.right);
      // 当前subtree的结果是左右两颗子树的和的平均值加上自身
      ResultType result = new ResultType(
          left.sum + right.sum + root.val,
          left.size + right.size + 1
      );
      // 打擂台比较得到最大平均值的子树
      if (subtree == null ||
          subtreeResult.sum * result.size < result.sum * subtreeResult.size
      ) {
          subtree = root;
          subtreeResult = result;
      }
      return result;
  }
}
