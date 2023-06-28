package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
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
		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;

		while (i < a.length) {
			while (!st.isEmpty() && a[i] > a[st.peek()]) {
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
		int[] s = new int[a.length];
		s[0] = 1;
		int i = 1;
		Stack<Integer> st = new Stack<>();
		st.push(0);

		while (i < a.length) {
			if (!st.isEmpty() && a[i] >= a[st.peek()]) {
				while (!st.isEmpty() && a[i] >= a[st.peek()]) {
					st.pop();
				}
			}
			s[i] = st.isEmpty() ? i + 1 : i - st.peek();
			st.push(i);
			i++;
		}

		return s;
	}


	@Test
	@DisplayName("Test Revese String")
	void testReveseString() {
		String reversed = reverseString("abc");
		Assertions.assertEquals("cba", reversed);
	}


	String reverseString(String s) {
		Stack<Character> st = new Stack<>();
		char[] chars = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			st.push(chars[i]);
		}

		int i = 0;
		while (!st.isEmpty() && i < s.length()) {
			chars[i] = st.pop();
			i++;
		}

		return new String(chars);
	}


	@Test
	@DisplayName("Test Find Element With Next Greater Freq")
	void testFindElementWithNextGreaterFreq() {
		int[] expected = { -1, -1, 1, 2, 2, 1, -1 };
		int[] op = findElementWithNextGreaterFreq(new int[] { 1, 1, 2, 3, 4, 2, 1 });
		Assertions.assertArrayEquals(expected, op);
	}




	int[] findElementWithNextGreaterFreq(int[] a) {
		int[] op = new int[a.length];

		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			maxVal = a[i] > maxVal ? a[i] : maxVal;
		}

		int[] fmap = new int[maxVal + 1];
		for (int i = 0; i < a.length; i++) {
			fmap[a[i]]++;
		}

		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;
		while (i < a.length) {
			while (!st.isEmpty() && fmap[a[i]] > fmap[a[st.peek()]]) {
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
	@DisplayName("Test Sort Stack Using Stack")
	void testSortStackUsingStack() {
		Stack<Integer> st = new Stack<>();
		st.add(3);
		st.add(91);
		st.add(4);
		st.add(33);
		st.add(1);

		Stack<Integer> stack = sortAStackUsingAStack(st);
		System.out.println(stack);
	}


	Stack<Integer> sortAStackUsingAStack(Stack<Integer> st) {
		Stack<Integer> tempStack = new Stack<>();
		while (!st.isEmpty()) {
			int temp = st.pop();
			while (!tempStack.isEmpty() && temp < tempStack.peek()) {
				st.push(tempStack.pop());
			}
			tempStack.push(temp);
		}

		return tempStack;
	}


	@Test
	@DisplayName("Test Remove Middle Element")
	void testRemoveMiddleElement() {
		Stack<Integer> st = new Stack<>();
		st.push(5);
		st.push(4);
		st.push(3);
		st.push(2);
		st.push(1);

		System.out.println(st);
		Stack<Integer> op = removeTheMiddleElement(st);
		System.out.println(op);
	}



	@Test
	@DisplayName("Test Remove Middle Element")
	void testRemoveMiddleElement1() {
		Stack<Integer> st = new Stack<>();
		st.push(6);
		st.push(5);
		st.push(4);
		st.push(3);
		st.push(2);
		st.push(1);

		System.out.println(st);
		Stack<Integer> op = removeTheMiddleElement(st);
		System.out.println(op);
	}



	Stack<Integer> removeTheMiddleElement(Stack<Integer> st) {
		_removeMiddleHelper(st, st.size(), 0);
		return st;
	}

	private void _removeMiddleHelper(Stack<Integer> st, int size, int cntr) {
		if (st.isEmpty() || cntr == size)
			return;

		Integer currEle = st.pop();

		_removeMiddleHelper(st, size, cntr + 1);

		if (cntr != size / 2)
			st.push(currEle);
	}


	@Test
	@DisplayName("Test Find Largest Rect Area In Histogram")
	void testFindLargestRectAreaInHistogram() {
		int area = findTheLargestRectAreaInHistogram(new int[] { 6, 2, 5, 4, 5, 1, 6 });
		Assertions.assertEquals(12, area);
	}


	int findTheLargestRectAreaInHistogram(int[] a) {
		int i = 1;
		Stack<Integer> st = new Stack<>();
		st.push(0);
		int maxArr = Integer.MIN_VALUE;

		while (i < a.length) {
			if (st.isEmpty()) {
				st.push(i);
				i++;
			} else if (a[i] > a[st.peek()]) {
				st.push(i);
				i++;
			} else {
				int h = a[st.pop()];
				int arr = h * (st.isEmpty() ? i : i - st.peek() - 1);
				if (arr > maxArr) {
					maxArr = arr;
				}
			}
		}

		while (!st.isEmpty()) {
			int h = a[st.pop()];
			int arr = h * (st.isEmpty() ? i - 1 : i - st.peek() - 1);
			if (arr > maxArr) {
				maxArr = arr;
			}
		}

		return maxArr;
	}


	@Test
	@DisplayName("Test Reverse The Given Number")
	void testReverseTheGivenNumber() {
		int i = reverseGivenNumber(365);
		Assertions.assertEquals(563, i);
	}



	int reverseGivenNumber(int a) {
		int i = a;
		Stack<Integer> st = new Stack<>();

		_reverseHelper(i, st);

		StringBuilder sb = new StringBuilder();
		while (!st.isEmpty()) {
			sb.append(st.pop());
		}

		return Integer.valueOf(sb.toString());

		//		// @formatter:off
//		String str = st.stream()
//				.map(String::valueOf)
//				.reduce("",(x,y)->y+x);
//		// @formatter:on
		//
		//		return Integer.valueOf(str);
	}

	private void _reverseHelper(int i, Stack<Integer> st) {
		int x = -1;
		if (i / 10 != 0) {
			x = i % 10;
			_reverseHelper(i / 10, st);
		} else {
			x = i;
		}
		st.push(x);
	}


	@Test
	@DisplayName("Test Delete Consecutive Words And Return Size")
	void testDeleteConsecutiveWordsAndReturnSize() {
		int i = deleteConsecutiveWordsAndReturnSize(Arrays.asList("Tom", "Jerry", "Jerry", "Tom"));
		Assertions.assertEquals(0, i);

	}



	@Test
	@DisplayName("Test Delete Consecutive Words And Return Size")
	void testDeleteConsecutiveWordsAndReturnSize1() {
		int i = deleteConsecutiveWordsAndReturnSize(Arrays.asList("ab", "aa", "aa", "bcd", "ab"));
		Assertions.assertEquals(3, i);

	}


	@Test
	@DisplayName("Test Delete Consecutive Words And Return Size")
	void testDeleteConsecutiveWordsAndReturnSize2() {
		int i = deleteConsecutiveWordsAndReturnSize(Arrays.asList("ab", "aa", "aa", "aa", "ab"));
		Assertions.assertEquals(3, i);

	}


	int deleteConsecutiveWordsAndReturnSize(List<String> lst) {
		int i = 0;
		Stack<String> st = new Stack<String>();

		while (i < lst.size()) {
			if (st.empty() || (!st.isEmpty() && !st.peek().equals(lst.get(i)))) {
				st.push(lst.get(i));
			} else {
				while (!st.isEmpty() && st.peek().equals(lst.get(i))) {
					st.pop();
				}
			}
			i++;
		}

		return st.size();
	}
}
