package binarytree.bst;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * https://leetcode.com/problems/binary-tree-level-order-traversal/submissions/
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class BTLevelOrderTraverse {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)  return ans;
        
        Queue<TreeNode> que = new LinkedList<>();
        
        que.offer(root);
        
        while(!que.isEmpty()) {
            
            int size = que.size();
            
            List<Integer> tmp = new ArrayList<>();
            
            for (int i=0; i<size; i++) {
            
                TreeNode cur = que.poll();
                tmp.add(cur.val);
                
                if (cur.left != null) {
                    que.offer(cur.left);
                }
                
                if (cur.right != null) {
                    que.offer(cur.right);
                }
            }
            ans.add(tmp);
        }
        
        return ans;
    }
    
    
    //using two list
    public List<List<Integer>> levelOrder1(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)  return ans;
        
        List<TreeNode> level = new ArrayList<>();
        //Queue<TreeNode> que = new LinkedList<>();
        level.add(root);
        //que.offer(root);
        
        while(level.size()>0) {
            
            //int size = que.size();
            
            List<Integer> tmp = new ArrayList<>();
            List<TreeNode> next = new ArrayList<>();
            
            for (TreeNode cur : level) {
            
                //TreeNode cur = que.poll();
                tmp.add(cur.val);
                
                if (cur.left != null) {
                    next.add(cur.left);
                }
                
                if (cur.right != null) {
                    next.add(cur.right);
                }
            }
            ans.add(tmp);
            level = next;
        }
        
        return ans;
    }
    
    //Using Dummy flag 
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> ans = new ArrayList<>();
        if (root == null)  return ans;
        
        Queue<TreeNode> que = new LinkedList<>();
        
        que.offer(root);
        que.offer(null);
        
        while(!que.isEmpty()) {
            
            int size = que.size();
            
            List<Integer> tmp = new ArrayList<>();
            
            for (int i=0; i<size; i++) {
            
                TreeNode cur = que.poll();
                if (cur == null) {
                    
                    if (que.isEmpty()) {
                        ans.add(tmp);
                        return ans;
                    } else {
                        break;
                    }
                        
                } else {
                    tmp.add(cur.val);

                    if (cur.left != null) {
                        que.offer(cur.left);
                    }

                    if (cur.right != null) {
                        que.offer(cur.right);
                    }
                }
            }
            
            ans.add(tmp);
            que.offer(null);
            
        }
        
        return ans;
    }
}