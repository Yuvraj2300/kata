package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

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
		int[] nge = new int[a.length];

		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;

		while (i < a.length) {
			while (!st.isEmpty() && a[st.peek()] < a[i]) {
				nge[st.pop()] = a[i];
			}
			st.push(i);
			i++;
		}

		while (!st.isEmpty()) {
			nge[st.pop()] = -1;
		}

		return nge;
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
		int[] op = new int[a.length];
		Stack<Integer> st = new Stack();
		st.push(0);
		op[0] = 1;
		int i = 1;

		while (i < a.length) {
			while (!st.isEmpty() && a[st.peek()] < a[i]) {
				st.pop();
			}
			op[i] = st.isEmpty() ? i + 1 : i - st.peek();
			st.push(i);
			i++;
		}

		return op;
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


	// @formatter:off

	/**
	 *LEETCODE
You are given an integer array cookies, where cookies[i] denotes the number of cookies in the ith bag. You are also given an integer k that denotes the number of children to distribute all the bags of cookies to. All the cookies in the same bag must go to the same child and cannot be split up.

The unfairness of a distribution is defined as the maximum total cookies obtained by a single child in the distribution.

Return the minimum unfairness of all distributions.



Example 1:

Input: cookies = [8,15,10,20,8], k = 2
Output: 31
Explanation: One optimal distribution is [8,15,8] and [10,20]
- The 1st child receives [8,15,8] which has a total of 8 + 15 + 8 = 31 cookies.
- The 2nd child receives [10,20] which has a total of 10 + 20 = 30 cookies.
The unfairness of the distribution is max(31,30) = 31.
It can be shown that there is no distribution with an unfairness less than 31.
Example 2:

Input: cookies = [6,1,3,2,2,4,1,2], k = 3
Output: 7
Explanation: One optimal distribution is [6,1], [3,2,2], and [4,1,2]
- The 1st child receives [6,1] which has a total of 6 + 1 = 7 cookies.
- The 2nd child receives [3,2,2] which has a total of 3 + 2 + 2 = 7 cookies.
- The 3rd child receives [4,1,2] which has a total of 4 + 1 + 2 = 7 cookies.
The unfairness of the distribution is max(7,7,7) = 7.
It can be shown that there is no distribution with an unfairness less than 7.

	 */
	// @formatter:on
	@Test
	@DisplayName("Test find Unfairness")
	void testFindUnfairness() {
		int unfairness = findUnfairness(new int[] { 8, 15, 10, 20, 8 }, 2);
		Assertions.assertEquals(31, unfairness);
	}


	@Test
	@DisplayName("Test find Unfairness")
	void testFindUnfairness1() {
		int unfairness = findUnfairness(new int[] { 6, 1, 3, 2, 2, 4, 1, 2 }, 3);
		Assertions.assertEquals(7, unfairness);
	}


	@Test
	@DisplayName("Test find Unfairness")
	void testFindUnfairness2() {
		int unfairness = findUnfairness(new int[] { 1, 8, 16, 5, 6, 14 }, 6);
		Assertions.assertEquals(16, unfairness);
	}

	@Test
	@DisplayName("Test find Unfairness")
	void testFindUnfairness3() {
		int unfairness = findUnfairness(new int[] { 3, 19, 17, 19, 10 }, 4);
		Assertions.assertEquals(19, unfairness);
	}

	int findUnfairness(int[] a, int k) {

		if (k >= a.length) {
			Arrays.sort(a);
			return a[a.length - 1];
		}

		int cm = 0;
		for (int i = 0; i < a.length; i++) {
			cm += a[i];
		}
		cm = cm / k;

		int[] t = new int[k];
		int min = Integer.MAX_VALUE;
		int ti = 0;

		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;

		int currSum = a[0];
		while (i < a.length) {
			if (ti < t.length && currSum + a[i] > cm) {
				while (!st.isEmpty() && currSum + a[i] > cm) {
					t[ti] += a[st.pop()];
				}
				min = t[ti] < min ? t[ti] : min;

				ti++;
				currSum = 0;
			} else {
				currSum += a[i];
				st.push(i);
				i++;
			}

		}

		while (!st.isEmpty() && ti < t.length) {
			if (ti < t.length) {
				t[ti] += a[st.pop()];
			}
		}
		//		min = t[ti] < min ? t[ti] : min;
		min = t[ti - 1] < min ? t[ti - 1] : min;
		if (st.isEmpty()) {
			return min;
		} else {
			while (!st.isEmpty()) {
				min += a[st.pop()];
			}
		}

		return min;
	}
}
