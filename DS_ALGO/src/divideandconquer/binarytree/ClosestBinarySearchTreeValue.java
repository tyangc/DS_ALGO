package divideandconquer.binarytree;
/*
 900. Closest Binary Search Tree Value

Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.

Example
Example1

Input: root = {5,4,9,2,#,8,10} and target = 6.124780
Output: 5
Explanation：
Binary tree {5,4,9,2,#,8,10},  denote the following structure:
        5
       / \
     4    9
    /    / \
   2    8  10
Example2

Input: root = {3,2,4,1} and target = 4.142857
Output: 4
Explanation：
Binary tree {3,2,4,1},  denote the following structure:
     3
    / \
  2    4
 /
1
Notice
Given target value is a floating point.
You are guaranteed to have only one unique value in the BST that is closest to the target.
 */
public class ClosestBinarySearchTreeValue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        return searchNode(root, target);
        
    }
    
    private int searchNode(TreeNode root, double target) {
        if (root==null) return  Integer.MIN_VALUE;  //MAX_VALUE won't pass some test cases
   
        int left = searchNode(root.left, target);
        int right = searchNode(root.right, target);
        
        return getCloser(root.val, getCloser(left, right, target), target);
    }
    
    private int getCloser(int left, int right, double target) {
        int res = Integer.MIN_VALUE;
        if (Math.abs(left-target) < Math.abs(right-target)){
            res = left;
        } else {
            res = right;
        }
        
        return res;
    }
    
    class TreeNode {
    	 public int val;
    	 public TreeNode left, right;
    	 public TreeNode(int val) {
	    	 this.val = val;
	    	 this.left = this.right = null;
    	 }
   	}
    
    //Using iteration way
    
    public int closestValueIteration(TreeNode root, double target) {
        if (root == null) return Integer.MIN_VALUE;
        int res = root.val;

        while(root != null) {
          if (Math.abs(res - target) >= Math.abs(root.val - target)) {
            res = root.val;
          }

          if ( target<root.val) {
            root = root.left;
          } else if (target>root.val){
            root = root.right;
          } else {  //With this one then it is faster
            return root.val;
          }
          
        }
        
        
        return res;
      }
    
    //Can also use lower boundary and upper boundary
}
