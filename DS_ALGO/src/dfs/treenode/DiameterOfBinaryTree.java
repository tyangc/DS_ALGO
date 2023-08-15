package dfs.treenode;

public class DiameterOfBinaryTree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	/**
     * @param root: a root of binary tree
     * @return: return a integer
     */
    public int diameterOfBinaryTree(TreeNode root) {
        // write your code here
        if (root==null) return 0;
        int[] res = {0};
        dfs(root, res);
        return res[0];
        
    }
    
    private int dfs(TreeNode root, int[] res) {
        if (root.left==null&&root.right==null) return 1;
        
        int left = root.left==null?0:dfs(root.left, res);
        int right = root.right==null?0:dfs(root.right, res);
        
        
        int larger = Math.max(left, right);
      
        res[0] = Math.max(res[0], Math.max(larger, left+right));  
        System.out.println(left +"|" + right+"|"+res[0]);
        return larger+1;//==0?1:larger;
        
    }
    
    //Consider each edge, otherwise we can think from the point of node
    private int dfs1(TreeNode root, int[] res) {
        if (root==null) return 0;
        
        int left = dfs(root.left, res);
        int right = dfs(root.right, res);
        
        
        int larger = Math.max(left, right);
      
        res[0] = Math.max(res[0], Math.max(larger, left+right));  
        System.out.println(left +"|" + right+"|"+res[0]);
        return larger+1;
        
    }
}

class TreeNode {
     public int val;
     public TreeNode left, right;
     public TreeNode(int val) {
         this.val = val;
         this.left = this.right = null;
     }
 }