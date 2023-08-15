package datastructure.stack;
/*
 * https://www.lintcode.com/problem/859/
 */
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;

public class MaxStack {
	private Stack<Item> stack;
    private Queue<Item> heap;
    private Set<Item> mark;
    int pos;

    public MaxStack() {
        // do intialization if necessary
        stack = new Stack();
        heap = new PriorityQueue();
        /* will also work
        heap = new PriorityQueue(new Comparator<Item>(){
            public int compare(Item a, Item b) {
                if (a.val != b.val) return b.val - a.val;
                return b.pos - a.pos;
            }
        });
         */
        mark = new HashSet();
        pos = 0;
    }

    private void removeMarkedFromHeap() {
        
        while(!heap.isEmpty() && mark.contains(heap.peek())) {
            Item itm = heap.peek();
            mark.remove(itm);
            heap.poll();
            
        }
        
        /*
        for (Item itm : mark) {
            heap.remove(itm);
            mark.remove(itm);
        }
        */
    }

    private void removeMarkedFromStack() {
        /*
        while(mark.size()!=0) {
            stack.pop(mark.remove());
        }
        */
        while(!stack.isEmpty() && mark.contains(stack.peek())) {
            
            mark.remove(stack.peek());
            stack.pop();
        }
    }

    /*
     * @param number: An integer
     * @return: nothing
     */    
    public void push(int x) {
        // write your code here
        //removeMarkedFromStack();
        //removeMarkedFromQueue();
        Item itm = new Item(x, pos);
        stack.push(itm);
        heap.offer(itm);
        pos++;
    }

    public int pop() {
        // write your code here
        removeMarkedFromStack();
        mark.add(stack.peek());
        return stack.pop().val;
    }

    /*
     * @return: An integer
     */    
    public int top() {
        // write your code here
        removeMarkedFromStack();
        return stack.peek().val;
    }

    /*
     * @return: An integer
     */    
    public int peekMax() {
        // write your code here
        removeMarkedFromHeap();
        return heap.peek().val;
    }

    /*
     * @return: An integer
     */    
    public int popMax() {
        // write your code here
        removeMarkedFromHeap();
        
        Item itm = heap.poll();
        mark.add(itm);
        return itm.val;
    }

    class Item implements Comparable<Item>{
        int val;
        int pos;

        public Item(int v, int p) {
            val = v;
            pos = p;
        }

        public int compareTo(Item another) {
            if (another.val != this.val) {
                return another.val - this.val;
            }
            return another.pos - this.pos;
        }
    }
}
