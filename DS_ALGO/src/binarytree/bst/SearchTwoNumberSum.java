package binarytree.bst;

import java.util.ArrayList;
import java.util.List;

public class SearchTwoNumberSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	//Morris algo:
	
	/*
     * @param : the root of tree
     * @param : the target sum
     * @return: two numbers from tree which sum is n
     */
    public int[] twoSum(TreeNode root, int n) {
        // write your code here
        //int[] res = new int[2];
        if (root==null) return null;
        
        List<Integer> list = new ArrayList<>();
        
        while(root!=null) {
            if (root.left!=null) {
                TreeNode cur = root.left;
                while(cur.right!=null && cur.right!=root) {
                    cur = cur.right;
                }
                
                if (cur.right==root) {
                    list.add(root.val);
                    root = root.right;
                } else {
                    cur.right = root;
                    root = root.left;
                }
            } else {
                list.add(root.val);
                root = root.right;
            }
        }
        
        return getTwo(list, n);
    }
    
    private int[] getTwo(List<Integer> list, int sum) {
        
        int[] res = new int[2];
        
        int i=0, j=list.size()-1;
        
        while(i<j) {
            int m=list.get(i), n=list.get(j);
            
            if (m+n<sum) {
                i++;
            } else if (m+n>sum) {
                j--;
            } else {
                res[0] = m;
                res[1] = n;
                return res;
            }
        }
        return null;
    }
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;
	
	public TreeNode(int val) {
		this.val = val;
		left = right = null;
	}
}
