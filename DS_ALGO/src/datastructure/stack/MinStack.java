package datastructure.stack;
/*
 * https://www.lintcode.com/problem/12/description
 */
import java.util.Stack;

public class MinStack {
	Stack<Integer> stack;
    Stack<Integer> minStack;

    //Version 1
    public MinStack() {
        // do intialization if necessary
        stack = new Stack();
        minStack = new Stack();
    }

    /*
     * @param number: An integer
     * @return: nothing
     */
    public void push(int number) {
        // write your code here
        if (minStack.isEmpty()) {
            minStack.push(number);
        } else {
            minStack.push(Math.min(number, minStack.peek()));
        }
        
        stack.push(number);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        //int top  = stack.peek();
        //if (!minStack.isEmpty()) {
            minStack.pop();
        //}

        return stack.pop();
    }

    /*
     * @return: An integer
     */
    public int min() {
        // write your code here
        return minStack.peek();
    }
    
    //Version 2:
        

    public void push1(int number) {
        stack.push(number);
        if (minStack.empty() == true)
            minStack.push(number);
        else if (minStack.peek() >= number) // 这里考虑的相等的情况也会继续push
            minStack.push(number);
    }

    public int pop1() {
        if (stack.peek().equals(minStack.peek()))
            minStack.pop();
        return stack.pop();
    }

    public int min1() {
        return minStack.peek();
    }
   
}
