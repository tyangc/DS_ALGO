package datastructure.heap;
/*
 130 · Heapify
Algorithms Medium Accepted Rate 46%

DescriptionSolutionNotesDiscussLeaderboard
Description
Given an integer array, heapify it into a min-heap array.

For a heap array A, A[0] is the root of heap, and for each A[i], A[i * 2 + 1] is the left child of A[i] and A[i * 2 + 2] is the right child of A[i].
Clarification
What is heap? What is heapify? What if there is a lot of solutions?

Heap is a data structure, which usually have three methods: push, pop and top. where "push" add a new element the heap, "pop" delete the minimum/maximum element in the heap, "top" return the minimum/maximum element.
Convert an unordered integer array into a heap array. If it is min-heap, for each element A[i], we will get A[i * 2 + 1] >= A[i] and A[i * 2 + 2] >= A[i].
Return any of them.
Example
Example 1

Input : [3,2,1,4,5]
Output : [1,2,3,4,5]
Explanation : return any one of the legitimate heap arrays
Challenge
O(n) time complexity

Tags
Heap
 */
public class Heapify {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	/*
     * @param A: Given an integer array
     * @return: nothing
     */
	
	//Heapify 的具体实现方法。时间复杂度为 $O(n)$，使用的是 siftdown 之所以是 O(n) 是因为从第 N/2 个位置开始往下 siftdown，
	//那么就有大约 N/4 个数在 siftdown 中最多交换 1 次，N/8 个数最多交换 2 次，N/16 个数最多交换 3 次。 所以 $O(N/4 1 + N/8 2 + N/16 3 + ... + 1 LogN) = O(N)$
	
    public void heapify(int[] A) {
        // write your code here
        for (int i = A.length/2; i>=0; i--) {
          siftDown(A, i);
        }
    }

    private void siftDown(int[] A, int k) {
      while(2*k+1 < A.length) {
        int son = 2*k+1;
        if (2*k+2<A.length && A[2*k+2]<A[son]) son = 2*k+2;

        if (A[son]>=A[k]) break;
        int tmp = A[k];
        A[k] = A[son];
        A[son] = tmp;
        k = son;
      }
    }
}
