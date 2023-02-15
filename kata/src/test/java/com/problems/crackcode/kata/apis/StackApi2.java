package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;

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



	private void reverseStringUsingStack(@NotNull String s) {
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
			System.out.print(span+",");
		}
	}



	@Test
	void testGetStockSpan_1() {
		int[] stocks = { 3, 1, 2, 2 };
		int[] stockSpan = getStockSpan(stocks);
		for (int span : stockSpan) {
			System.out.print(span+",");
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
