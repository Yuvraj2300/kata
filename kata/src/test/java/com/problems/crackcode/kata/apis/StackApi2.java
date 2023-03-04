package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackApi2 {

	@Test
	void testReverseStack() throws Exception {
		Stack<Integer> s = new Stack<Integer>();
		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);

		Stack<Integer> revStack = reverseStack(s);

		for (Integer i : revStack) {
			System.out.println(i);
		}
	}

	@Test
	void testReverseStack_1() throws Exception {
		Stack<Integer> s = new Stack<Integer>();
		s.push(3);
		s.push(2);
		s.push(1);

		Stack<Integer> revStack = reverseStack(s);

		for (Integer i : revStack) {
			System.out.println(i);
		}
	}

	<T> Stack<T> reverseStack(Stack<T> s) {
		Stack<T> sn = new Stack<T>();

		while (!s.isEmpty()) {
			sn.push(s.pop());
		}

		return sn;
	}

	@Test
	void testReverseStackRec() throws Exception {
		Stack<Integer> s = new Stack<Integer>();
		s.push(3);
		s.push(2);
		s.push(1);

		Stack<Integer> revStack = reverseStack_Rec(s);

		for (Integer i : revStack) {
			System.out.println(i);
		}
	}

	<T> Stack<T> reverseStack_Rec(Stack<T> s) {
		// Stack<T> sn = new Stack<T>();

		if (!s.isEmpty()) {
			T peek = s.peek();

			s.pop();
			reverseStack_Rec(s);

			s.push(peek);
		}

		return s;
	}

	@Test
	void testSortStRecur() throws Exception {
		Stack<Integer> st = new Stack<Integer>();
		st.push(3);
		st.push(2);
		st.push(1);

		System.out.println(st.peek());
		System.out.println(st);

		Stack<Integer> srtedStack = sortStackRecursion(st);
		System.out.println(srtedStack.peek());
		System.out.println(srtedStack);
	}

	Stack<Integer> sortStackRecursion(Stack<Integer> st) {
		if (!st.isEmpty()) {
			Integer pop = st.pop();
			sortStackRecursion(st);

			sorteInsert(st, pop);
		}
		return st;
	}

	private void sorteInsert(Stack<Integer> st, Integer pop) {
		if (st.isEmpty() || pop > st.peek()) {
			st.push(pop);
			return;
		}

		Integer temp = st.pop();
		sorteInsert(st, pop);
		st.push(temp);
	}

	@Test
	void testSortStackTmp() throws Exception {
		Stack<Integer> st = new Stack<Integer>();
		st.push(3);
		st.push(1);
		st.push(2);

		System.out.println(st.peek());
		System.out.println(st);

		Stack<Integer> srtedStack = sortStackTmp(st);
		System.out.println(srtedStack.peek());
		System.out.println(srtedStack);
	}

	// [34, 3, 31, 98, 92, 23]
	@Test
	void testSortStackTmp_1() throws Exception {
		Stack<Integer> st = new Stack<Integer>();
		st.push(34);
		st.push(3);
		st.push(31);
		st.push(98);
		st.push(92);
		st.push(21);

		System.out.println(st);

		Stack<Integer> srtedStack = sortStackTmp(st);

		System.out.println(srtedStack);

		while (!srtedStack.isEmpty()) {
			System.out.print(srtedStack.pop() + ", ");
		}
	}

	private Stack<Integer> sortStackTmp(Stack<Integer> st) {
		Stack<Integer> tmpSt = new Stack<Integer>();
		while (!st.isEmpty()) {
			int pop = st.pop();

			while (!tmpSt.isEmpty() && pop < tmpSt.peek()) {
				st.push(tmpSt.pop());
			}

			tmpSt.push(pop);
		}

		return tmpSt;
	}

	private void sortStRecur(Stack<Integer> st) {

	}

	@Test
	void testReveNoExSpace() throws Exception {
		Stack<Integer> st = new Stack<Integer>();
		st.push(1);
		st.push(2);
		st.push(3);

		System.out.println(st);

		Stack<Integer> revSt = reverseStackNoExSpace(st);

		System.out.println(revSt);
	}



	private Stack<Integer> reverseStackNoExSpace(Stack<Integer> st) {
		reverseStRec(st);
		return st;
	}



	private void reverseStRec(Stack<Integer> st) {
		if (!st.isEmpty()) {
			int pop = st.pop();
			reverseStRec(st);
			insertAtBotton(st, pop);
		}

	}



	private void insertAtBotton(Stack<Integer> st, int x) {
		if (st.isEmpty()) {
			st.push(x);
		} else {
			int temp = st.pop();
			insertAtBotton(st, x);
			st.push(temp);
		}
	}

	@Test
	void testReverseStringUsingStack() {
		String s = "Hello World";
		reverseStringUsingStack(s);
	}



	private void reverseStringUsingStack(String s) {
		int i = 0;
		Stack<Character> st = new Stack<Character>();

		while (i < s.length()) {
			if (s.charAt(i) != ' ') {
				st.push(s.charAt(i));
			} else {
				while (!st.isEmpty()) {
					System.out.print(st.pop());
				}
				System.out.print(' ');
			}
			i++;
		}

		while (!st.isEmpty()) {
			System.out.print(st.pop());
		}
	}



	@Test
	void testReverseQ() throws Exception {
		Queue<Integer> q = new LinkedList<Integer>();
		q.add(1);
		q.add(2);
		q.add(3);
		q.add(4);
		q.add(5);

		System.out.println("Unreversed : " + q);

		Queue<Integer> reversed = reverQSt(q);

		System.out.println("Reversed : " + reversed);
	}



	public Queue<Integer> reverQSt(Queue<Integer> queue) {
		Stack<Integer> st = new Stack<Integer>();

		while (!queue.isEmpty()) {
			st.push(queue.poll());
		}

		while (!st.isEmpty()) {
			queue.add(st.pop());
		}

		return queue;
	}


	@Test
	void testGetStockSpan() {
		int[] stocks = { 10, 4, 5, 90, 120, 80 };
		int[] stockSpan = getStockSpan(stocks);
		for (int span : stockSpan) {
			System.out.print(span + ",");
		}
	}



	@Test
	void testGetStockSpan_1() {
		int[] stocks = { 3, 1, 2, 2 };
		int[] stockSpan = getStockSpan(stocks);
		for (int span : stockSpan) {
			System.out.print(span + ",");
		}
	}



	public int[] getStockSpan(int[] stocks) {
		int[] spans = new int[stocks.length];
		Stack<Integer> indices = new Stack<Integer>();

		for (int i = 0; i < stocks.length; i++) {
			indices.push(i);
		}

		while (!indices.isEmpty()) {
			Integer index = indices.pop();
			int i = 1;
			int span = 1;
			while (i <= index && stocks[index - i] <= stocks[index]) {
				//				if (stocks[index - i] <= stocks[index]) {
				span++;
				//				}
				i++;
			}
			spans[index] = span;
		}
		return spans;
	}


	@Test
	void testGetListOfNges() {
		int[] inp = { 4, 5, 2, 25 };
		int[] listOfNGEs = getListOfNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetListOfNges_1() {
		int[] inp = { 13, 7, 6, 12 };
		int[] listOfNGEs = getListOfNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetListOfNges_2() {
		int[] inp = { 1, 98, 2, 25, 6 };
		int[] listOfNGEs = getListOfNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetListOfNges_3() {
		int[] inp = { 11, 13, 21, 3 };
		int[] listOfNGEs = getListOfNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}

	/*
	 * Below solution is a very crude solution but the time complexity is O(n)
	 *
	 * */
	int[] getListOfNGEs(int[] inp) {
		int[] op = new int[inp.length];
		Stack<Integer> valSt = new Stack<Integer>();

		for (int i = 0; i < inp.length; i++) {
			valSt.push(inp[i]);
		}

		int i = inp.length - 1;
		int currNge = 0;
		while (i >= 0 && !valSt.isEmpty()) {
			if (inp[i] == valSt.peek()) {
				op[i] = -1;
			} else {
				if (valSt.peek() > inp[i]) {
					currNge = valSt.pop();
					op[i] = currNge;
				} else {
					if (currNge > inp[i]) {
						valSt.pop();
						op[i] = currNge;
					} else {
						while (valSt.peek() < inp[i]) {
							valSt.pop();
						}
						continue;
					}
				}
			}
			i--;
		}

		return op;
	}



	@Test
	void testGetNges() {
		int[] inp = { 4, 5, 2, 25 };
		int[] listOfNGEs = getNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetNges_1() {
		int[] inp = { 13, 7, 6, 12 };
		int[] listOfNGEs = getNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetNges_2() {
		int[] inp = { 1, 98, 2, 25, 6 };
		int[] listOfNGEs = getNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}



	@Test
	void testGetNges_3() {
		int[] inp = { 11, 13, 21, 3 };
		int[] listOfNGEs = getNGEs(inp);

		for (int nge : listOfNGEs) {
			System.out.print(nge + ",");
		}
	}

	public int[] getNGEs(int[] a) {
		int[] op = new int[a.length];

		Stack<Integer> st = new Stack<>();
		st.push(0);

		int i = 1;
		while (i < a.length) {
			if (a[st.peek()] < a[i]) {
				while (!st.isEmpty() && a[st.peek()] < a[i]) {
					op[st.pop()] = a[i];
				}

				st.push(i);
			} else {
				st.push(i);
			}

			i++;
		}

		while (!st.isEmpty()) {
			op[st.pop()] = -1;
		}

		return op;
	}



	@Test
	void testGetNGF() {
		int a[] = { 1, 1, 2, 3, 4, 2, 1 };
		int[] nextGreaterFreqs = getNextGreaterFreq(a);

		for (int freq : nextGreaterFreqs) {
			System.out.print(freq + ",");
		}
	}



	@Test
	void testGetNGF1() {
		int a[] = { 1, 1, 2, 3 };
		int[] nextGreaterFreqs = getNextGreaterFreq(a);

		for (int freq : nextGreaterFreqs) {
			System.out.print(freq + ",");
		}
	}




	@Test
	void testGetNGF2() {
		int a[] = { 1, 1, 1, 2, 2, 2, 2, 11, 3, 3 };
		int[] nextGreaterFreqs = getNextGreaterFreq(a);
		//op 2,2,2,-1,-1,-1,-1,3,-1,-1,
		for (int freq : nextGreaterFreqs) {
			System.out.print(freq + ",");
		}
	}



	int[] getNextGreaterFreq(int[] a) {
		int[] op = new int[a.length];
		//get freq soemehow
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i])
				max = a[i];
		}

		int[] freq = new int[max + 1];
		for (int i = 0; i < a.length; i++) {
			freq[a[i]]++;
			/*
			you will have an array like this :
			idx : 0,1,2,3,4
			vals: 0,3,2,1,1
			 */
		}

		//stack to store indexes
		Stack<Integer> st = new Stack<>();
		st.push(0);

		//comparison to be done of the next exlement with the prev that is stored in a stack
		int i = 1;
		while (i < a.length) {
			if (freq[a[st.peek()]] < freq[a[i]]) {
				while (!st.isEmpty() && freq[a[st.peek()]] < freq[a[i]]) {
					op[st.pop()] = a[i];
				}
				st.push(i);
			} else {
				st.push(i);
			}

			i++;
		}
		while (!st.isEmpty()) {
			op[st.pop()] = -1;
		}

		return op;
	}




	@Test
	void testGetLRArray() {
		int[] a = { 5, 4, 3, 4, 5 };
		int maxLRPrd = getLRArray(a);
		assertEquals(8, maxLRPrd);
	}



	@Test
	void testGetLRArray_1() {
		int[] a = { 1, 1, 1, 1, 0, 1, 1, 1, 1, 1 };
		int maxLRPrd = getLRArray(a);
		assertEquals(24, maxLRPrd);
	}


	/**
	 * Given Consider first array as 1 when generating L(i) and R(i)
	 */
	int getLRArray(int[] a) {
		int[] ngeL = new int[a.length];
		int[] ngeR = new int[a.length];

		//for the right arr , nge on right
		Stack<Integer> st = new Stack<>();
		st.add(0);
		int i = 1;
		while (i < a.length) {
			if (a[st.peek()] > a[i]) {
				st.push(i);
			} else {
				while (!st.isEmpty() && a[st.peek()] < a[i]) {
					ngeR[st.pop()] = i + 1;
				}
				st.push(i);
			}
			i++;
		}

		while (!st.isEmpty()) {
			ngeR[st.pop()] = 0;
		}

		//for left
		assertTrue(st.isEmpty());

		st.push(a.length - 1);
		int j = a.length - 2;

		while (j >= 0) {
			if (a[st.peek()] > a[j]) {
				st.push(j);
			} else {
				while (!st.isEmpty() && a[st.peek()] < a[j]) {
					ngeL[st.pop()] = j + 1;
				}
				st.push(j);
			}
			j--;
		}

		while (!st.isEmpty()) {
			ngeL[st.pop()] = 0;
		}

		// now get the max product
		int k = 0;
		int maxPrd = -1;
		while (k < ngeR.length && k < ngeL.length) {
			maxPrd = Math.max(maxPrd, ngeL[k] * ngeR[k]);
			k++;
		}

		return maxPrd;
	}


	@Test
	void testSpanProblem() {
		int[] a = { 100, 80, 60, 70, 60, 75, 85 };
		int[] op = spanProblem(a);

		for (int i : op) {
			System.out.print(i + ", ");
		}
	}


	@Test
	void testSpanProblem_1() {
		int[] a = { 10, 4, 5, 90, 120, 80 };
		int[] op = spanProblem(a);

		for (int i : op) {
			System.out.print(i + ", ");
		}
	}



	int[] spanProblem(int[] a) {
		int[] op = new int[a.length];
		op[0] = 1;

		Stack<Integer> st = new Stack<>();
		st.push(0);
		for (int i = 1; i < a.length; i++) {
			while (!st.isEmpty() && a[st.peek()] <= a[i]) {
				st.pop();
			}

			op[i] = st.isEmpty() ? i + 1 : i - st.peek();

			//			if (st.isEmpty()) {
			//				op[i] = i + 1;
			//			} else {
			//				op[i] = i - st.peek();
			//			}
			st.push(i);
		}

		return op;
	}


	@Test
	void testNgePrblem() {
		int[] a = { 4, 5, 25, 2 };
		int[] nges = ngeProblem(a);

		for (int nge : nges) {
			System.out.print(nge + ", ");
		}
	}



	@Test
	void testNgePrblem1() {
		int[] a = { 1, 2, 6, 3 };
		int[] nges = ngeProblem(a);

		for (int nge : nges) {
			System.out.print(nge + ", ");
		}
	}


	@Test
	void testNgePrblem2() {
		int[] a = { 90, 1, 2, 3, 98 };
		int[] nges = ngeProblem(a);

		for (int nge : nges) {
			System.out.print(nge + ", ");
		}
	}

	int[] ngeProblem(int[] a) {
		int[] op = new int[a.length];
		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;

		while (i < a.length) {
			while (!st.isEmpty() && a[st.peek()] < a[i]) {
				op[st.pop()] = a[i];
			}

			st.push(i);
			i++;
		}

		while (!st.isEmpty()) {
			op[st.pop()] = -1;
		}

		return op;
	}


	@Test
	void testNGFProblem() {
		int[] a = { 1, 1, 2, 3, 4, 2, 1 };
		int[] ngfs = ngfProblem(a);

		for (int ngf : ngfs) {
			System.out.print(ngf + ", ");
		}
	}


	int[] ngfProblem(int[] a) {
		int[] op = new int[a.length];

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}

		int[] frqs = new int[max + 1];
		for (int i = 0; i < a.length; i++) {
			frqs[a[i]]++;
		}

		int i = 1;
		Stack<Integer> st = new Stack<>();
		st.push(0);

		while (i < a.length) {
			while (!st.isEmpty() && frqs[a[st.peek()]] < frqs[a[i]]) {
				op[st.pop()] = a[i];
			}
			st.push(i);
			i++;
		}

		while (!st.isEmpty()) {
			op[st.pop()] = -1;
		}

		return op;
	}

	@Test
	void testReverseStackWithTemp() {
		Stack<Integer> st = new Stack<>();
		st.add(34);
		st.add(3);
		st.add(31);
		st.add(98);
		st.add(92);
		st.add(23);

		reverseStackTemp(st).forEach(e -> System.out.print(e + ","));
	}



	<T extends Comparable<T>> Stack<T> reverseStackTemp(Stack<T> st) {
		Stack<T> tempStack = new Stack<>();

		while (!st.isEmpty()) {
			T temp = st.pop();
			if (tempStack.isEmpty()) {
				tempStack.push(temp);
			} else {
				while (!tempStack.isEmpty() && tempStack.peek().compareTo(temp) > 0) {
					st.push(tempStack.pop());
				}
				tempStack.push(temp);
			}
		}

		return tempStack;
	}


	@Test
	void testDeleteMiddleOfStack() {
		Stack<Integer> st = new Stack<>();
		st.add(1);
		st.add(2);
		st.add(3);
		st.add(4);
		st.add(5);
		Stack<Integer> updatedStack = deleteMiddleOfStack(st);
		updatedStack.forEach(e -> System.out.print(e + ", "));
	}



	@Test
	void testDeleteMiddleOfStack_1() {
		Stack<Integer> st = new Stack<>();
		st.add(1);
		st.add(2);
		st.add(3);
		st.add(4);
		st.add(5);
		st.add(6);
		Stack<Integer> updatedStack = deleteMiddleOfStack(st);
		updatedStack.forEach(e -> System.out.print(e + ", "));
	}


	<T> Stack<T> deleteMiddleOfStack(Stack<T> st) {
		int tlt = st.size();

		popMiddleHelper(st, tlt);

		return st;
	}

	private <T> void popMiddleHelper(Stack<T> st, int tlt) {
		if (st.size() == tlt / 2) {
			st.pop();
		} else {
			if (!st.isEmpty()) {
				T pop = st.pop();
				popMiddleHelper(st, tlt);
				st.push(pop);
			}
		}
	}



	@Test
	void testRectAreaInHist() {
		int[] a = { 6, 2, 5, 4, 5, 1, 6 };
		int maxArea = getRectAreaInHistogram(a);
		assertEquals(12, maxArea);
	}

	@Test
	void testRectAreaInHist1() {
		int[] a = { 6, 2, 5 };
		int maxArea = getRectAreaInHistogram(a);
		assertEquals(6, maxArea);
	}


	@Test
	void testRectAreaInHist2() {
		int[] a = { 3, 5, 1, 7, 5, 9 };
		int maxArea = getRectAreaInHistogram(a);
		assertEquals(15, maxArea);
	}


	int getRectAreaInHistogram(int[] a) {
		int maxArr = Integer.MIN_VALUE;
		Stack<Integer> st = new Stack<>();
		st.push(0);

		int i = 1;
		while (i < a.length) {
			if (st.isEmpty() || a[i] >= a[st.peek()]) {
				st.push(i);
				i++;
			} else {
				int currBar = st.pop();
				int ht = a[currBar];
				int wd = 0;
				if (st.isEmpty()) {
					wd = i;
				} else {
					wd = i - st.peek() - 1;
				}
				int currBarArea = ht * wd;

				if (currBarArea > maxArr) {
					maxArr = currBarArea;
				}
			}
		}

		while (!st.isEmpty()) {
			int currBar = st.pop();
			int ht = a[currBar];
			int wd = 0;
			if (st.isEmpty()) {
				wd = i;
			} else {
				wd = i - st.peek() - 1;
			}
			int currBarArea = ht * wd;

			if (currBarArea > maxArr) {
				maxArr = currBarArea;
			}
		}

		return maxArr;
	}


	@Test
	void testGetSpansOfStocks() {
		int[] a = { 100, 80, 60, 70, 60, 75, 85 };
		int[] spansOfStocks = getSpansOfStocks(a);
		Arrays.stream(spansOfStocks).forEach(span -> System.out.print(span + ", "));
	}


	@Test
	void testGetSpansOfStocks1() {
		int[] a = { 10, 4, 5, 90, 120, 80 };
		int[] spansOfStocks = getSpansOfStocks(a);
		Arrays.stream(spansOfStocks).forEach(span -> System.out.print(span + ", "));
	}

	int[] getSpansOfStocks(int[] a) {
		int i = 0;
		Stack<Integer> st = new Stack<>();
		int[] op = new int[a.length];

		while (i < a.length) {
			if (st.isEmpty()) {
				op[i] = 1;
				st.push(i++);
			} else if (a[i] >= a[st.peek()]) {
				while (!st.isEmpty() && a[i] >= a[st.peek()]) {
					Integer pop = st.pop();
				}
			}
			op[i] = st.isEmpty() ? i + 1 : i - st.peek();
			st.push(i++);
		}


		return op;
	}


	void sandbox() {
		//remove adjactent repeating characters
		String input = "abbaca";
		String output = "ca";

		/*
		 * while(i&j<lt && st. >)
		 * abbaca  i=0 j=1
		 * if (a[i]!=a[j])
		 * 		i++;j++;
		 * else(eqyq)
		 * 	remove(i)& (j)
		 * 	i=0;j=1;
		 *
		 * i=1 j =2
		 * b=b
		 * aaca
		 * i=0 j=1
		 *
		 * ca
		 * i=0
		 * j=1
		 *
		 *
		 * */


	}




}
