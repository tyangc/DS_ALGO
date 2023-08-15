package amazon;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Function;

public class MergTwoSortList {

	public static Node<Integer> mergeTwoLists(Node<Integer> l1, Node<Integer> l2) {
        // write your code here
        Node<Integer> p = new Node<>(0);
        Node<Integer> org = p, p1 = l1, p2 = l2;

        while(p1!=null && p2!=null) {
            if (p1.val<p2.val) {
                p.next = p1;
                p1 = p1.next;

            } else {
                p.next = p2;
                p2 = p2.next;

            }
            p = p.next;
        }

        //if (p1!=null) p.next=p1;

        //if (p2!=null) p.next=p2;
        
        p.next = p1 != null ? p1 : p2;
        
        return org.next;

    }
	
	public static <T> Node<T> buildList(Iterator<String> iter, Function<String, T> f) {
        if (!iter.hasNext()) return null;
        String val = iter.next();
        Node<T> next = buildList(iter, f);
        return new Node<T>(f.apply(val), next);
    }

    public static List<String> splitWords(String s) {
        //return s.isEmpty() ? List.of() : Arrays.asList(s.split(" "));
    	return s.isEmpty() ? new ArrayList<String>() : Arrays.asList(s.split(" "));
    }

    public static <T> void formatList(Node<T> node, List<String> out) {
        if (node == null) return;
        out.add(node.val.toString());
        formatList(node.next, out);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Node<Integer> l1 = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        Node<Integer> l2 = buildList(splitWords(scanner.nextLine()).iterator(), Integer::parseInt);
        scanner.close();
        Node<Integer> res = mergeTwoLists(l1, l2);
        ArrayList<String> resArr = new ArrayList<>();
        formatList(res, resArr);
        System.out.println(String.join(" ", resArr));
    }
	
    public static class Node<T> {
        public T val;
        public Node<T> next;

        public Node(T val) {
            this(val, null);
        }

        public Node(T val, Node<T> next) {
            this.val = val;
            this.next = next;
        }
    }
}
