package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

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
		assertEquals(3, findMissingNumber(new int[] { 1, 4, 2, 5 }, n));
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



	@Test
	@DisplayName("Test Finding the first repeating Number")
	void testFindingTheFirstRepeatingNumber() {
		assertEquals(5, findTheFirstRepeatingNumber(new int[] { 10, 5, 3, 4, 3, 5, 6 }));
	}



	@Test
	@DisplayName("Test Finding the first repeating Number")
	void testFindingTheFirstRepeatingNumber1() {
		assertEquals(6, findTheFirstRepeatingNumber(new int[] { 6, 10, 5, 4, 9, 120, 4, 6, 10 }));
	}



	int findTheFirstRepeatingNumber(int[] a) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++)
			max = Math.max(max, a[i]);

		int[] hash = new int[max + 1];

		int i = 0;
		while (i < a.length) {
			hash[a[i]]++;
			i++;
		}

		for (int j = 0; j < a.length; j++) {
			if (hash[a[j]] > 1)
				return a[j];
		}

		return -1;
	}



	@Test
	@DisplayName("Test Find Repeating And Missing")
	void testFindRepeatingAndMissing() {
		int[] repeatingAndMissing = findRepeatingAndMissing(new int[] { 3, 1, 3 });
		assertEquals(2, repeatingAndMissing[0]);
		assertEquals(3, repeatingAndMissing[1]);
	}



	int[] findRepeatingAndMissing(int[] a) {
		int[] op = new int[2];

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			max = Math.max(max, a[i]);
		}

		int[] hash = new int[max + 1];

		int i = 0;
		while (i < a.length) {
			hash[a[i]]++;
			i++;
		}

		for (int j = 1; j < a.length; j++) {
			if (hash[j] == 0)
				op[0] = j;
		}

		for (int j = 0; j < a.length; j++) {
			if (hash[a[j]] > 1) {
				op[1] = a[j];
			}
		}

		return op;
	}


	@Test
	@DisplayName("Test count 1s in sorted Array")
	void testCount1SInSortedArray() {
		assertEquals(3, count1sInSortedArray(new int[] { 1, 1, 1, 0, 0, 0 }));
	}


	@Test
	@DisplayName("Test count 1s in sorted Array")
	void testCount1SInSortedArray1() {
		assertEquals(0, count1sInSortedArray(new int[] { 0, 0, 0, 0, 0, 0 }));
	}



	@Test
	@DisplayName("Test count 1s in sorted Array")
	void testCount1SInSortedArray2() {
		assertEquals(5, count1sInSortedArray(new int[] { 1, 1, 1, 1, 1, 0 }));
	}


	int count1sInSortedArray(int[] a) {
		int l = 0;
		int r = a.length - 1;

		while (l < r) {
			int mid = l + (r - l) / 2;
			if (a[mid + 1] == 0 && a[mid] == 1) {
				return mid + 1;
			} else if (a[mid + 1] == 1) {
				l = mid + 1;
			} else {
				r = mid - 1;
			}

		}

		return 0;
	}


	@Test
	@DisplayName("Test Find Sum Closest To Zero")
	void testFindSumClosestToZero() {
		int[] op = findSumClosestToZero(new int[] { 1, 60, -10, 70, -80, 85 });
		assertEquals(85, op[0]);
		assertEquals(-80, op[1]);
	}



	@Test
	@DisplayName("Test Find Sum Closest To Zero")
	void testFindSumClosestToZero1() {
		int[] op = findSumClosestToZero(new int[] { 5, 3, -90, 92, 6, 5 });
		Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
		assertEquals(92, op[0]);
		assertEquals(-90, op[1]);
	}


	int[] findSumClosestToZero(int[] a) {
		int[] op = new int[2];
		Arrays.sort(a);

		int minSum = Integer.MAX_VALUE;
		int l = 0;
		int h = a.length - 1;

		while (l < h) {
			int currSum = a[h] + a[l];
			if (currSum < minSum) {
				minSum = currSum;
				op[0] = a[h];
				op[1] = a[l];
			}

			if (currSum < 0) {
				h--;
			} else {
				l++;
			}
		}
		return op;
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiff() {
		int[] pair = findPairWithDiff(new int[] { 5, 20, 3, 2, 50, 80 }, 78);
		assertNotNull(pair);
		Arrays.stream(pair).forEach(i -> System.out.print(i + ", "));

		assertEquals(80, pair[0]);
		assertEquals(2, pair[1]);
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiff2() {
		int[] pair = findPairWithDiff(new int[] { 90, 70, 20, 80, 50 }, 45);
		assertNull(pair);
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiff3() {
		int[] pair = findPairWithDiff(new int[] { 5, 20, 3, 50, 80 }, 75);
		assertNotNull(pair);
		Arrays.stream(pair).forEach(i -> System.out.print(i + ", "));

		assertEquals(80, pair[0]);
		assertEquals(5, pair[1]);
	}


	int[] findPairWithDiff(int[] a, int n) {
		int[] op = new int[2];
		int l = 0;
		int h = a.length - 1;

		Arrays.sort(a);
		while (l < h) {
			int currDiff = a[h] - a[l];

			if (currDiff == n) {
				op[0] = a[h];
				op[1] = a[l];
				return op;
			}

			if (currDiff > n) {
				l++;
			} else {
				h--;
			}
		}

		return null;
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiffSol2() {
		int[] pair = findPairWithDiffSol2(new int[] { 5, 20, 3, 2, 50, 80 }, 78);
		assertFalse(Arrays.stream(pair).allMatch(i -> i == 0));
		Arrays.stream(pair).forEach(i -> System.out.print(i + ", "));

		assertTrue(Arrays.stream(pair).anyMatch(i -> i == 2));
		assertTrue(Arrays.stream(pair).anyMatch(i -> i == 80));
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiffSol2_2() {
		int[] pair = findPairWithDiffSol2(new int[] { 90, 70, 20, 80, 50 }, 45);
		assertTrue(Arrays.stream(pair).allMatch(i -> i == 0));
	}



	@Test
	@DisplayName("Test Find Pairs With Diff")
	void testFindPairsWithDiffSol2_3() {
		int[] pair = findPairWithDiff(new int[] { 5, 20, 3, 50, 80 }, 75);
		assertFalse(Arrays.stream(pair).allMatch(i -> i == 0));
		Arrays.stream(pair).forEach(i -> System.out.print(i + ", "));

		assertTrue(Arrays.stream(pair).anyMatch(i -> i == 80));
		assertTrue(Arrays.stream(pair).anyMatch(i -> i == 5));
	}



	int[] findPairWithDiffSol2(int[] a, int n) {
		int[] op = new int[2];
		HashMap<Integer, Integer> hm = new HashMap<>();
		for (int i : a) {
			hm.put(i, hm.getOrDefault(i, 0) + 1);
			if (n == 0 && hm.get(i) > 1) {
				op[0] = i;
				op[1] = i;
				return op;
			}
		}

		for (int i : a) {
			if (hm.containsKey(n + i)) {
				op[0] = i;
				op[1] = n + i;
			}
		}

		return op;
	}


	@Test
	@DisplayName("Test Find Common Elements In Three Sorted Arrays")
	void testFindCommonElementsInThreeSortedArrays() {
		// @formatter:off
		int[] commons = findCommonElementsInThreeSortedArrays(new int[] { 1, 5, 10, 20, 40, 80 },
																new int[] {6, 7, 20, 80, 100} ,
																new int[] {3, 4, 15, 20, 30, 70, 80, 120});
		// @formatter:on

		assertTrue(Arrays.stream(commons).anyMatch(i -> i == 20));
		assertTrue(Arrays.stream(commons).anyMatch(i -> i == 80));
	}



	@Test
	@DisplayName("Test Find Common Elements In Three Sorted Arrays")
	void testFindCommonElementsInThreeSortedArrays1() {
		// @formatter:off
		int[] commons = findCommonElementsInThreeSortedArrays(new int[] { 1,2,3 },
																new int[] {4,5,6} ,
																new int[] {7,8,9});
		// @formatter:on
		assertTrue(commons.length == 0);
	}



	@Test
	@DisplayName("Test Find Common Elements In Three Sorted Arrays")
	void testFindCommonElementsInThreeSortedArrays3() {
		// @formatter:off
		int[] commons = findCommonElementsInThreeSortedArrays(new int[] { 1, 5, 10, 20, 40, 80 },
																new int[] {1,5,10} ,
																new int[] {1});
		// @formatter:on

		assertTrue(commons.length == 1);
		assertTrue(Arrays.stream(commons).anyMatch(i -> i == 1));
	}



	int[] findCommonElementsInThreeSortedArrays(int[] a, int[] b, int[] c) {
		List<Integer> l = new ArrayList<>();

		int i = 0;
		while (i < a.length) {
			if (binSearch(a[i], b) && binSearch(a[i], c))
				l.add(a[i]);
			i++;
		}

		return l.stream().mapToInt(val -> val).toArray();
	}

	private boolean binSearch(int i, int[] b) {
		int l = 0;
		int h = b.length - 1;

		while (l <= h) {
			int mid = l + (h - l) / 2;
			if (b[mid] == i)
				return true;

			if (b[mid] > i) {
				h = mid - 1;
			} else {
				l = mid + 1;
			}
		}

		return false;
	}



	@Test
	@DisplayName("Find the Kth Smallest Element")
	void findTheKthSmallestElementInMatrixTest() {
		// @formatter:off
		int mat[][] = {
				{ 10, 20, 30, 40 },
				{ 15, 25, 35, 45 },
				{ 25, 29, 37, 48 },
				{ 32, 33, 39, 50 },
		};
		// @formatter:on
		System.out.println("7th smallest element is " + findKthSmallestNumberInMatrix(7, mat));
	}


// Returns the WRONG answer but well, E for effort as they say
	int findKthSmallestNumberInMatrix(int k, int[][] mat) {
		int l = mat[0][0];
		int h = mat[mat.length - 1][mat[0].length - 1];

		while (l <= h) {
			int mid = l + (h - l) / 2;
			int greaterThan = _findElementsGreater(mid, mat);

			if (greaterThan >= k)
				h = mid - 1;
			else
				l = mid + 1;
		}

		return l;
	}

	private int _findElementsGreater(int mid, int[][] mat) {
		int i = 0;
		int greaterThan = 0;
		int n = mat.length;

		while (i < n) {
			if (mid < mat[i][0]) {
				return greaterThan;
			}

			if (mid >= mat[i][n - 1]) {
				greaterThan += n;
				i++;
				continue;
			}

			int inRowBump = 0;
			int jump = n / 2;
			while (jump / 2 >= 1 && inRowBump + jump < n && mat[i][inRowBump + jump] <= mid) {
				inRowBump += jump;
				jump /= 2;
			}

			greaterThan += inRowBump;
			i++;
		}

		return greaterThan;
	}

}















