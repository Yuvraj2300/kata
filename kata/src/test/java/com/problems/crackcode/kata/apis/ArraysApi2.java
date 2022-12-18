package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

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




}




