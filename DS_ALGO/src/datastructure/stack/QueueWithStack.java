package datastructure.stack;

import java.util.Stack;

/*
 40 · Implement Queue by Two Stacks
Algorithms
Medium
Accepted Rate
52%

DescriptionSolutionNotesDiscussLeaderboard
Description
As the title described, you should only use two stacks to implement a queue's actions.

The queue should support push(element), pop() and top() where pop is pop the first(a.k.a front) element in the queue.

Both pop and top methods should return the value of first element.

Suppose the queue is not empty when the pop() function is called.

Example
Example 1:

Input:
    push(1)
    pop()    
    push(2)
    push(3)
    top()    
    pop()     
Output:
    1
    2
    2
Example 2:

Input:
    push(1)
    push(2)
    push(2)
    push(3)
    push(4)
    push(5)
    push(6)
    push(7)
    push(1)
Output:
[]
Challenge
implement it by two stacks, do not use any other data structure and push, pop and top should be O(1) by AVERAGE.

Related Problems
955
Implement Queue by Circular Array
Medium
546
Implement Queue by Interface
Easy
493
Implement Queue by Linked List II
Easy
492
Implement Queue by Linked List
Easy
12
Min Stack
Medium
 */
public class QueueWithStack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	Stack<Integer> A;
    Stack<Integer> B;


    public QueueWithStack() {
        // do intialization if necessary
        A = new Stack<>();
        B = new Stack<>();

    }

    /*
     * @param element: An integer
     * @return: nothing
     */
    public void push(int element) {
        // write your code here
        
        while(!B.isEmpty()) {
          A.push(B.pop());
        }
        A.push(element);
    }

    /*
     * @return: An integer
     */
    public int pop() {
        // write your code here
        while(!A.isEmpty()) {
          B.push(A.pop());
        }
        return B.pop();
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        while(!A.isEmpty()) {
          B.push(A.pop());
        }
        return B.peek();
    }
	
}
