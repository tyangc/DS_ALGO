package bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

//import binarytree.divideconquer.BalancedBinaryTree.TreeNode1;

public class TreeTraversalByLevel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		TreeTraversalByLevel ttl = new TreeTraversalByLevel();
		TreeNode1 root = new TreeNode1(1);
		TreeNode1 l = new TreeNode1(2);
		TreeNode1 r = new TreeNode1(3);
		root.left = l;
		root.right = r;
		
		System.out.println(ttl.levelOrder2(root));
		
				
	}

	/**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder(TreeNode1 root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) return result;
        
        LinkedList<TreeNode1> que = new LinkedList<>();
        que.offer(root);
        
        while(que.size()!=0) {
            List<Integer> list = new ArrayList<>();
            int size = que.size();
            
            for (int i=0; i<size; i++) {
                TreeNode1 cur = que.poll();
                list.add(cur.val);
                
                if (cur.left!=null) {
                    que.offer(cur.left);
                }
                
                if (cur.right!=null) {
                    que.offer(cur.right);
                }
            }
            
            result.add(list);
        }
        
        return result;
    }
    
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrder2(TreeNode1 root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if (root==null) return result;
        
        ArrayList<TreeNode1> que = new ArrayList<>();
        que.add(root);
        
        while(que.size()!=0) {
            result.add(toIntList(que));
            ArrayList<TreeNode1> nextQue = new ArrayList<>();
            
            for (TreeNode1 n : que) {
                if (n.left!=null) {
                    nextQue.add(n.left);
                }
                
                if (n.right!=null) {
                    nextQue.add(n.right);
                }
            }
            
            que = nextQue;
        }
        
        return result;
    }
    
    private static List<Integer> toIntList(List<TreeNode1> que) {
        /*
        List<Integer> list = new ArrayList<>();
        
        for (TreeNode1 n : que) {
            list.add(n.val);
 
        }
        
        return list;
        */
        return que.stream()
                .map(e->e.val)
                .collect(Collectors.toList());
                
    }
    
    /**
     * @param root: A Tree
     * @return: Level order a list of lists of integer
     */
    public List<List<Integer>> levelOrderDummyNode(TreeNode1 root) {
        // write your code here
        List<List<Integer>> result = new ArrayList<>();
        if(root==null) return result;
        
        Queue<TreeNode1> que = new LinkedList<>();
        que.offer(root);
        que.offer(null);
        
        List<Integer> list = new ArrayList<>();
        
        while(!que.isEmpty()) {
            TreeNode1 node = que.poll();
            
            if (node==null) {
                
                if (que.size()>0) {
                    result.add(list);
                    list=new ArrayList<>();
                    que.offer(null);
                    
                } else {
                    result.add(list);
                    break;                    
                }
                
            } else {
                
                list.add(node.val);
                
                if (node.left!=null) {
                    que.offer(node.left);
                }
                
                if (node.right!=null) {
                    que.offer(node.right);
                }
                
            }
            
        }
        
        return result;
        
    }
    
    
}

class TreeNode1 {
    public int val;
    public TreeNode1 left, right;
    public TreeNode1(int val) {
    	this.val = val;
	      	this.left = this.right = null;
	    } 
}

/*

*/