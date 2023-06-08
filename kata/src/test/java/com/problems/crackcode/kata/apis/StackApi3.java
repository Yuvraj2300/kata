package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Stack;

public class StackApi3 {


	@Test
	@DisplayName("Test Find NGEs")
	void testFindNgEs() {
		int[] expected = { 5, 25, 25, -1 };
		int[] nges = findNGEs(new int[] { 4, 5, 2, 25 });
		Assertions.assertArrayEquals(expected, nges);
	}


	@Test
	@DisplayName("Test Find NGEs")
	void testFindNgE1() {
		int[] expected = { -1, 4, 6, -1, -1 };
		int[] nges = findNGEs(new int[] { 98, 2, 4, 6, 1 });
		Assertions.assertArrayEquals(expected, nges);
	}



	int[] findNGEs(int[] a) {
		int[] op = new int[a.length];
		int i = 0;
		Stack<Integer> st = new Stack<>();

		while (i < a.length) {
			if (!st.isEmpty() && a[i] > st.peek()) {
				while (!st.isEmpty() && a[i] > a[st.peek()]) {
					op[st.pop()] = a[i];
				}
			}
			st.push(i++);
		}

		while (!st.isEmpty()) {

			op[st.pop()] = -1;
		}
		return op;
	}



	@Test
	@DisplayName("Test Solve Stock Span")
	void testSolveStockSpan() {
		int[] expected = { 1, 1, 1, 2, 1, 4, 6 };
		int[] ints = solveStockSpan(new int[] { 100, 80, 60, 70, 60, 75, 85 });
		Assertions.assertArrayEquals(expected, ints);
	}


	@Test
	@DisplayName("Test Solve Stock Span")
	void testSolveStockSpan1() {
		int[] expected = { 1, 2, 1, 2, 1, 4, 7 };
		int[] ints = solveStockSpan(new int[] { 100, 120, 60, 70, 60, 75, 185 });
		Assertions.assertArrayEquals(expected, ints);
	}



	int[] solveStockSpan(int[] a) {
		Stack<Integer> st = new Stack<>();
		int[] op = new int[a.length];
		op[0] = 1;
		st.push(0);
		int i = 1;
		while (i < a.length) {
			while (!st.isEmpty() && a[st.peek()] < a[i]) {
				st.pop();
			}
			op[i] = st.empty() ? i + 1 : i - st.peek();
			st.push(i);
			i++;
		}
		return op;
	}

}
