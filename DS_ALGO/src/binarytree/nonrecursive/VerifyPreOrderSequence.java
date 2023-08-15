package binarytree.nonrecursive;

import java.util.Stack;

public class VerifyPreOrderSequence {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public boolean isValidSerialization(String preorder) {
	    String[] arr = preorder.split(",");
	 
	    Stack<String> stack = new Stack<>();
	    for(String s: arr){
	        if(stack.isEmpty() || !s.equals("#")){
	            stack.push(s);
	        }else{
	            while(!stack.isEmpty() && stack.peek().equals("#")){
	                stack.pop();
	                if(stack.isEmpty()){
	                    return false;
	                }else{
	                    stack.pop();
	                }
	            }
	            stack.push("#");
	        }            
	    }
	 
	    return stack.size()==1 && stack.peek().equals("#");
	}
}
