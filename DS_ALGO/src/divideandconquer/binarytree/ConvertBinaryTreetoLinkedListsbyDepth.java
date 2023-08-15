package divideandconquer.binarytree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import binarytree.divideconquer.BalancedBinaryTree.TreeNode;

public class ConvertBinaryTreetoLinkedListsbyDepth {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/**
     * @param root the root of binary tree
     * @return a lists of linked list
     */
	
	//This is a smart dvivide and conquer
	public List<ListNode> binaryTreeToLists(TreeNode root) {
        // Write your code here
        List<ListNode> res = new ArrayList<>();
        if (root==null) return res;
        int dep = 1;
        dfs(root, dep, res); 

        return res;

    }

    void dfs(TreeNode root, int dep, List<ListNode> res) {
      if (root==null) return;

      ListNode cur = new ListNode(root.val);
      if (dep>res.size()) {
        
        res.add(cur);
      } else {
          cur.next = res.get(dep-1);
          res.set(dep-1, cur);
      }

      dfs(root.right, dep+1, res);
      dfs(root.left, dep+1, res);
    }
	
    public List<ListNode> binaryTreeToListsBfs(TreeNode root) {
        // Write your code here
        List<ListNode> res = new ArrayList<>();
        if (root==null) return res;
        Queue<TreeNode> que = new LinkedList();

        que.offer(root);

        while(!que.isEmpty()) {
          int size = que.size();
          ListNode head = new ListNode(0),  pre = head;
          for (int i=0; i<size; i++) {
            TreeNode cur = que.poll();
            ListNode ln = new ListNode(cur.val);
                         
            pre.next = ln;
            pre = ln;

            if (cur.left!=null) {
              que.offer(cur.left);
            }

            if (cur.right!=null) {
              que.offer(cur.right);
            }
          }
          res.add(head.next); 
        }

        return res;

    }
}

class ListNode {
	 int val;
	      ListNode next;
	      ListNode(int x) { val = x; }
	  }