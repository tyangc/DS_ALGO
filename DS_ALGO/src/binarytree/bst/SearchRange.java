package binarytree.bst;

import java.util.ArrayList;
import java.util.List;

public class SearchRange {

	/**
     * @param root: param root: The root of the binary search tree
     * @param k1: An integer
     * @param k2: An integer
     * @return: return: Return all keys that k1<=key<=k2 in ascending order
     */
    public List<Integer> searchRange(TreeNode root, int k1, int k2) {
        // write your code here
        
        List<Integer> res = new ArrayList<>();
        if (root==null || k1>k2) return res;
        
        search(root, k1, k2, res);
        
        return res;    
        
    }
    
    //Doen't matter it is a bst or not
    private void search(TreeNode root, int k1, int k2, List<Integer> res) {
        if (root==null) return;
        
        if (root.val>=k1 && root.val<=k2) {
            res.add(root.val);
        }
        
        if (root.left!=null) {
            search(root.left, k1, k2, res);
        }
        
        if (root.right!=null) {
            search(root.right, k1, k2, res);
        }
        
        /*
        if (root.val<k1) {
            search(root.right, k1, k2, res);
        } else if (root.val>k2) {
            search(root.left, k1, k2, res);
        } else {
            res.add(root.val);
            if (root.left!=null) {
                search(root.left, k1, k2, res);
            }
            if (root.right!=null) {
                search(root.right, k1, k2, res);
            }
        }
       */
    }
}
