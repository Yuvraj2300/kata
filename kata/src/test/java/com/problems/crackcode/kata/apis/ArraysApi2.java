package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import com.problems.crackcode.kata.exceptions.KataException;

public class ArraysApi2 {

	@Test
	void testAllRoation() throws Exception {
		allRotations("geeks");
	}

	private void allRotations(String s) {
		int j = 0;
		char[] charArray = s.toCharArray();
		while (j < s.length()) {
			int i = 0;
			char temp = charArray[0];
			while (i < s.length()) {
				if (i < s.length() - 1) {
					charArray[i] = charArray[i + 1];
				} else {
					charArray[i] = temp;
				}
				i++;
			}
			System.out.println();
			for (char c : charArray) {
				System.out.print(c);
			}
			j++;
		}
	}



	@Test
	void testTwoSumPair() throws Exception {
		int A[] = { 1, 4, 45, 6, 10, -8 };
		int sum = 14;

		assertTrue(twoSumPair(A, sum));
	}


	@Test
	void testTwoSumPair_1() throws Exception {
		int A[] = { 0, -1, 2, -3, 1 };
		int sum = -2;

		assertTrue(twoSumPair(A, sum));
	}




	boolean twoSumPair(int[] arr, int sum) {
		//binary search the complement

		Arrays.sort(arr);
		int i = 0;
		while (i < arr.length) {
			int keyToSearch = sum - arr[i];

			if (binarySearchAKeyPresence(keyToSearch, i + 1, arr.length - 1, arr))
				return true;

			i++;
		}

		return false;
	}



	private boolean binarySearchAKeyPresence(int key, int low, int high, int[] arr) {
		while (low < high) {
			int mid = (low + high) / 2;

			if (arr[mid] == key) {
				return true;
			}

			if (key > arr[mid])
				return binarySearchAKeyPresence(key, mid + 1, high, arr);
			else
				return binarySearchAKeyPresence(key, low, mid - 1, arr);
		}

		return false;
	}



	@Test
	void testGetMajElement() throws Exception {
		int[] A = { 1, 1, 2, 1, 3, 5, 1 };
		assertEquals(1, getTheMajEelement(A));
	}


	@Test
	void testGetMajElement_1() throws Exception {
		int[] A = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };
		assertEquals(4, getTheMajEelement(A));
	}



	private int getTheMajEelement(int[] a) {
		int i = 0;
		Map<Integer, Integer> map = new HashMap<>();

		while (i < a.length) {
			map.put(a[i], map.getOrDefault(a[i], 0) + 1);
			i++;
		}

		int arrLt = a.length;

		return map.entrySet().stream().filter(e -> e.getValue() > arrLt / 2).findAny().get().getKey();
	}



	@Test
	void testGetEquilibIndex() throws Exception {
		int[] A = { -7, 1, 5, 2, -4, 3, 0 };
		assertEquals(3, getEquilibIndex(A));
	}



	@Test
	void testGetEquilibIndex_1() throws Exception {
		int[] A = { 1, 2, 3 };
		assertEquals(-1, getEquilibIndex(A));
	}



	int getEquilibIndex(int[] a) {
		int i = 0;
		int j = a.length - 1;
		int sumR = 0;
		int sumL = 0;

		while (i < a.length && j >= 0 && i < j) {
			sumR += a[i];
			sumL += a[j];
			i++;
			j--;

			if (sumL == sumR)
				return i++;

		}
		return -1;
	}



	@Test
	void testFindPeak() throws Exception {
		int arr[] = { 1, 3, 20, 4, 1, 0 };
		assertEquals(20, findPeak(arr));

		int arr2[] = { 5, 10, 20, 15 };
		assertEquals(20, findPeak(arr));
	}



	int findPeak(int[] arr) {
		int l = 0;
		int h = arr.length - 1;

		return _binSrchPeak(arr, l, h);

	}

	private int _binSrchPeak(int[] arr, int l, int h) {
		int mid = (l + h) / 2;

		if (arr[mid + 1] < arr[mid] && arr[mid - 1] < arr[mid]) {
			return arr[mid];
		} else if (arr[mid + 1] < arr[mid]) {
			return _binSrchPeak(arr, mid + 1, arr.length - 1);
		} else {
			return _binSrchPeak(arr, 0, mid - 1);
		}
	}

	@Test
	void testFindSubArray() throws Exception {
		int[] a = { 1, 4, 20, 3, 10, 5 };
		int sum = 33;

		SubArrayWindow subArray = findSubArray(a, sum);
		assertNotNull(subArray);
		assertEquals(2, subArray.getStart());
		assertEquals(4, subArray.getEnd());
	}



	@Test
	void testFindSubArray_1() throws Exception {
		int[] a = { 1, 4, 0, 0, 3, 10, 5 };
		int sum = 7;

		SubArrayWindow subArray = findSubArray(a, sum);
		assertNotNull(subArray);
		assertEquals(1, subArray.getStart());
		assertEquals(4, subArray.getEnd());
	}



	@Test
	void testFindSubArray_2() throws Exception {
		int[] a = { 1, 4 };
		int sum = 0;

		SubArrayWindow subArray = findSubArray(a, sum);
		assertNull(subArray);
	}


	SubArrayWindow findSubArray(int[] a, int sum) {
		SubArrayWindow subArrayWindow = null;

		int i = 0;
		int j = 0;

		int tempSum = 0;

		while (i < a.length) {
			tempSum += a[i];

			//corner case
			if (sum < tempSum) {
				return null;
			}

			if (tempSum > sum) {
				while (tempSum > sum) {
					tempSum -= a[j];
					j++;
				}
			}

			if (tempSum == sum) {
				subArrayWindow = new SubArrayWindow();
				subArrayWindow.setStart(j);
				subArrayWindow.setEnd(i);
				break;
			}


			i++;
		}
		return subArrayWindow;
	}




	class SubArrayWindow {
		int start;
		int end;

		public int getStart() {
			return start;
		}

		public void setStart(int start) {
			this.start = start;
		}

		public int getEnd() {
			return end;
		}

		public void setEnd(int end) {
			this.end = end;
		}
	}

}




