package test;

//import java.util.Collections;
import java.util.Stack;

public class CollectionAddAllStackTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Stack<Integer> A = new Stack<>();
		
		A.push(1);
		A.push(2);
		A.push(3);
		System.out.println(A);
		Stack<Integer> B = new Stack<>();
		
		B.addAll(A);
		
		Stack<Integer> C = new Stack<>();
		
		while(!A.isEmpty()) {
			C.push(A.pop());
		}
		System.out.println(B);
		
		System.out.println(C);
	}

}
