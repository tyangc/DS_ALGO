package datastructure.stack;

import java.util.LinkedList;
import java.util.Queue;

/*
 * https://www.lintcode.com/problem/494/
 */
public class StackWithTwoQueues {
	/*
     * @param x: An integer
     * @return: nothing
     */

    Queue<Integer> q1 = new LinkedList(); 
    Queue<Integer> q2 = new LinkedList();

    public void push(int x) {
        // write your code here
        q1.offer(x);
    
    }

    /*
     * @return: nothing
     */
    public void pop() {
        // write your code here
        if (q1.isEmpty()) return;

        shuffle();
        q1.poll();
        swapQueue();
        System.out.println(q1.peek());
        System.out.println(q2.peek());
    }

    /*
     * @return: An integer
     */
    public int top() {
        // write your code here
        if (q1.isEmpty()) return -1;
        //if (q1.size() == 1) return q1.peek();  //Not necessary
        shuffle();
        int ret = q1.peek();
        q2.offer(q1.poll());
        swapQueue();
        return ret;
    }

    /*
     * @return: True if the stack is empty
     */
    public boolean isEmpty() {
        // write your code here
        return q1.isEmpty();
    }

    private void swapQueue() {  //swapQueue(Queue q1, Queue q2) :  this is wrong!!! pass by copy of the reference pointer
        Queue tmp = q1;
        q1 = q2;
        q2 = tmp;
    }

    private void shuffle() {
        while(q1.size() > 1) {
            q2.offer(q1.poll());
        }
    }
}
