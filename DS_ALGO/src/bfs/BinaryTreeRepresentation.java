package bfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BinaryTreeRepresentation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TreeNode root = new TreeNode(1);
		TreeNode left = new TreeNode(2);
		TreeNode right = new TreeNode(3);
		root.left = left;
		root.right = right;
		left.left = new TreeNode(4);
		right.right = new TreeNode(5);
		
		System.out.println(serialize(root));
		System.out.println(serialize(deserialize(serialize1(root))));
	}

	static class TreeNode {
    	public int val;
    	public TreeNode left;
    	public TreeNode right;
    	
    	public TreeNode(int val) {
    		this.val = val;
    		left = right = null;
    	}
    }
	
	//Two tiers implementation
	private static String serialize(TreeNode root) {
		if (root==null) return "{}";
		
		List<TreeNode> que = new ArrayList<>();
		que.add(root);
		
		for (int i=0; i<que.size(); i++) {
			TreeNode cur = que.get(i);
			if (cur==null) continue;
			que.add(cur.left);
			que.add(cur.right);
			
		}
		
		return queueToString(que);
	}
	
	private static String queueToString(List<TreeNode> que) {
		while(que.get(que.size()-1)==null) {
			que.remove(que.size()-1);
		}
		
		List<String> res = new ArrayList<>();
				
		for (int i=0; i<que.size(); i++) {
			TreeNode cur = que.get(i);
			if (cur!=null) {
				res.add(String.valueOf(cur.val));
			} else {
				res.add("#");
			}
		}
		return "{" + String.join(",", res) + "}";
	}
	
	//One Tier implementation
	public static String serialize1(TreeNode root) {
        // write your code here
        if (root==null) {
            return "{}";
        }

        List<String> res = new ArrayList<>();

        List<TreeNode> que = new ArrayList<>();

        que.add(root);

        for (int i=0; i<que.size(); i++) {
            TreeNode cur = que.get(i);
            if (cur !=null ) {
                res.add(cur.val + "");
                que.add(cur.left);
                que.add(cur.right);
            } else {
                res.add("#");
            }
            
        }

        while(res.get(res.size()-1).equals("#")) {
        	res.remove(res.size()-1);
        }
        	
        
        return "{" + String.join(",", res) + "}";
    }
	
	
	private static TreeNode deserialize(String data) {
		if (data.equals("{}")) return null;
		String[] arr = data.substring(1, data.length()-1).split(",");
		List<TreeNode> que = new ArrayList<>();
        TreeNode root = new TreeNode(Integer.valueOf(arr[0]));
        que.add(root);
        int index=0;
        boolean isLeft = true;

        for (int i=1; i<arr.length; i++) {
            TreeNode node = que.get(index);  //Do not judge if node is null here, more redundant code 
            String val = arr[i];
            if (!val.equals("#")) {  //Equivalent to node not null -- Symmetric relationship cross two layers
                TreeNode tmp = new TreeNode(Integer.valueOf(val));
                if (isLeft) {
                    node.left = tmp;
                } else {
                    node.right = tmp;
                }

                que.add(tmp);
            }
            if (!isLeft) {
                index++;
            }

            isLeft = !isLeft;
        }

        return root;
		
		
	}
}
