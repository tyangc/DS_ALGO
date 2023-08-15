package dfs.treenode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * 262 · heir tree
Algorithms
Medium
Accepted Rate
70%
Description
Solution4
Notes
Discuss16
Leaderboard
Record
Description
Please design a data structure MyTreeNodeof the heir tree, which contains the following methods:

addNode(MyTreeNode, val): Add a heir numbered 
�
�
�
val to a person.
deleteNode(MyTreeNode): Disqualifies a person from inheritance (but does not affect the inheritance of his heirs)
traverse(MyTreeNode): Query the inheritance order under a person. The inheritance order is traversed in the preorder traversal, which is, for each node, first it itself, and then it traverses each of its heirs in the order from first to last.
At the same time, you need to complete a constructor of the class that only receives 
�
�
�
val, and we will use it to construct a node with 
�
�
�
=
1
val=1 as the root of the heir tree.

Wechat reply 【Two Sigma】 get the latest requent Interview questions. (wechat id : jiuzhang1104)


Example
Example 1:

Input：
add(1,2)
add(2,3)
traverse(1)
add(2,5)
add(3,4)
traverse(1)
deleteNode(2)
traverse(1)
Output：[[1,2,3],[1,2,3,4,5],[1,3,4,5]]
Explanation：The tree is look like this.


Challenge
Can you finish deleteNode in 
�
(
1
)
O(1) time?

Tags
Depth First Search/DFS
Divide and Conquer
Tree
 */
public class MyTreeNode {
	/**
     * @param val: the val of the node
     * @return: a MyTreeNode Object
     */

    int val;
    List<MyTreeNode> heris;
    //MyTreeNode parent;
    boolean isDeleted;
    MyTreeNode(int val) {
        // write your code here
        this.val = val;

        this.heris = new ArrayList<>();

    }
    
    /**
     * @param root: the root treenode
     * @return: get the traverse of the treenode
     */
    public ArrayList<Integer> traverse(MyTreeNode root) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        if (root==null) return res;

        dfs(root, res);
        /*
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            MyTreeNode cur = stack.pop();
            Collections.reverse(cur.heris);
            for (MyTreeNode node : cur.heris) {
                stack.push(node);
            }
            Collections.reverse(cur.heris);
        }*/



        return res;
    }

    //Using iteration : 
    public ArrayList<Integer> traverse1(MyTreeNode root) {
        // write your code here
        ArrayList<Integer> res = new ArrayList<>();
        if (root==null) return res;

        //dfs(root, res);
    
        Stack<MyTreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            MyTreeNode cur = stack.pop();

            if (!cur.isDeleted) res.add(cur.val);
            Collections.reverse(cur.heris);
            for (MyTreeNode node : cur.heris) {
                stack.push(node);
            }
            Collections.reverse(cur.heris);
        }



        return res;
    }
    private void dfs(MyTreeNode node, List<Integer> list) {
        if (!node.isDeleted) {
            list.add(node.val);
        }

        for (MyTreeNode sub : node.heris) {
            dfs(sub, list);
        }
    }

    /**
     * @param root: the node where added
     * @param value: the added node's value
     * @return: add a node, return the node
     */
    public MyTreeNode addNode(MyTreeNode root, int value) {
        // write your code here
        MyTreeNode node = new MyTreeNode(value);
        //node.parent = root;
        root.heris.add(node);
        return node;
    }

    /**
     * @param root: the deleted node
     * @return: nothing
     */
    public void deleteNode(MyTreeNode root) {
        // write your code here

        root.isDeleted = true;
        /*
        if (root.parent==null) {
            root = null;
            return;
        }

        List<MyTreeNode> upHeris = root.parent.heris;

        int index = upHeris.indexOf(root);
        upHeris.remove(index);
        
        if (index>0) upHeris.addAll(index-1, root.heris);
        else upHeris.addAll(index, root.heris);
        root = null;
        */

    }
}
