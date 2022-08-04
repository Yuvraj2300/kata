package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SearchingSorting {


	/**
	 * @author yuvraj1.sharma
	 *
	 *         Sorted Merge: You are given two sorted arrays, A and B, where A has a
	 *         large enough buffer at the end to hold B. Write a method to merge B
	 *         into A in sorted order.
	 *
	 *
	 */
	public int[] sortedMerge(int[] a, int[] b) {
		int indexAMax = _findActualElementsLength(a) - 1;
		int indexBMax = _findActualElementsLength(b) - 1;
		int indexMerged = indexAMax + indexBMax - 1;

		while (indexBMax >= 0) {
			if (indexAMax >= 0 && a[indexAMax] > b[indexBMax]) {
				a[indexMerged] = a[indexBMax];
				indexAMax--;
			} else {
				a[indexMerged] = b[indexBMax];
				indexBMax--;
			}
			indexMerged--;
		}

		return a;
	}


	private int _findActualElementsLength(int[] a) {
		int i = 0;
		while (i < a.length) {
			if (a[i] != 0) {
				i++;
			} else {
				break;
			}
		}
		return i;
	}


	@Test
	void testSortedMerge() throws Exception {
		int[] a = { 1, 2, 3, 4, 5, 0, 0, 0, 0, 0, 0, };
		int[] b = { 3, 4, 7, 9 };

		int[] sortedMerge = sortedMerge(a, b);


		for (int i : sortedMerge) {
			System.out.print(i + " ");
		}
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 *         Search in Rotated Array: Given a sorted array of n integers that has
	 *         been rotated an unknown number of times, write code to find an
	 *         element in the array. You may assume that the array was originally
	 *         sorted in increasing order. EXAMPLE lnput:find5in{15, 16, 19, 20, 25,
	 *         1, 3, 4, 5, 7, 10, 14} Output: 8 (the index of 5 in the array)
	 */

	//{15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14}
	public int searchIndex(int[] arr, int valToSearch) {
		return searchRotated(arr, valToSearch, 0, arr.length - 1);
	}


	private int searchRotated(int[] arr, int valToSearch, int start, int end) {
		System.out.println("Searching in frame of  : " + start + " : " + end);
		int mid = (end + start) / 2;

		if (end < start) {
			return 0;
		}

		if (valToSearch == arr[mid]) {
			return mid;
		}
		if (valToSearch < arr[mid]) {
			if (arr[mid] > arr[mid + 1]) {
				return searchRotated(arr, valToSearch, mid, arr.length - 1);
			} else {
				return searchRotated(arr, valToSearch, start, mid - 1);
			}
		} else {//valToSearch > arr[mid]

			if (arr[mid + 1] < arr[mid]) {
				return searchRotated(arr, valToSearch, mid + 1, arr.length - 1);
			} else if (!(arr[mid - 1] < arr[mid + 1])) {
				return searchRotated(arr, valToSearch, 0, mid);
			} else {
				return searchRotated(arr, valToSearch, mid + 1, end);
			}
		}

		//		return pos;
	}



	@Test
	void testSearchInRotatedArray() throws Exception {
		int[] array = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int idx = searchIndex(array, 5);
		System.out.println(idx);

		assertEquals(8, idx);
	}


	@Test
	void testSearchInRotatedArray_1() throws Exception {
		int[] array = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int idx = searchIndex(array, 19);
		System.out.println(idx);

		assertEquals(2, idx);
	}


	@Test
	void testSearchInRotatedArray_2() throws Exception {
		int[] array = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int idx = searchIndex(array, 15);
		System.out.println(idx);

		assertEquals(0, idx);
	}


	@Test
	void testSearchInRotatedArray_3() throws Exception {
		int[] array = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };
		int idx = searchIndex(array, 10);
		System.out.println(idx);

		assertEquals(10, idx);
	}
}
