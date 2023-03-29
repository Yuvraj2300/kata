package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SearchApi {

	@Test
	@DisplayName("Test Find The Missing Number")
	void testFindTheMissingNumber() {
		int n = 8;
		assertEquals(5, findMissingNumber(new int[] { 1, 2, 4, 6, 3, 7, 8 }, 8));
	}



	@Test
	@DisplayName("Test Find The Missing Number")
	void testFindTheMissingNumber1() {
		int n = 5;
		assertEquals(3, findMissingNumber(new int[] { 1, 4,2,5 }, n));
	}



	int findMissingNumber(int[] a, int n) {
		int[] hash = new int[n + 1];
		int i = 0;
		while (i < a.length) {
			hash[a[i]]++;
			i++;
		}

		for (int j = 1; j < a.length; j++) {
			if (hash[j] == 0) {
				return j;
			}
		}

		return -1;
	}


}
