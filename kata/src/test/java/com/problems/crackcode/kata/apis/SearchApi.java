package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

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



	@Test
	@DisplayName("Test Find Max element That is Increasing then Decreasing")
	void testFindMaxElementThatIsIncreasingThenDecreasing() {
		int element = findElementThatIsIncThenDec(new int[] { 1, 3, 50, 10, 9, 7, 6 });
		assertNotEquals(-1, element);
		assertEquals(50, element);
	}



	@Test
	@DisplayName("Test Find Max element That is Increasing then Decreasing")
	void testFindMaxElementThatIsIncreasingThenDecreasing_1() {
		int element = findElementThatIsIncThenDec(new int[] { 8, 10, 20, 80, 100, 200, 400, 500, 3, 2, 1 });
		assertNotEquals(-1, element);
		assertEquals(500, element);
	}

	//corner case
	@Test
	@DisplayName("Test Find Max element That is Increasing then Decreasing")
	void testFindMaxElementThatIsIncreasingThenDecreasing2() {
		int element = findElementThatIsIncThenDec(new int[] { 120, 100, 80, 20, 0 });
		assertNotEquals(-1, element);
		assertEquals(120, element);
	}


	int findElementThatIsIncThenDec(int[] a) {
		int l = 0;
		int h = a.length - 1;
		while (l <= h) {
			if (l == h)
				return a[l];

			int mid = l + (h - l) / 2;
			boolean c1 = a[mid - 1] < a[mid];
			boolean c2 = a[mid + 1] < a[mid];

			if (c1 & c2) {
				return a[mid];
			}

			if (!c1) {
				h = mid - 1;
			} else if (!c2) {
				l = mid + 1;
			}
		}

		return -1;
	}



	@Test
	@DisplayName("Test Find Number With Elements That areSmaller Before Greater After")
	void testFindNumberWithElementsThatAreSmallerBeforeGreaterAfter() {
		int element = findNumberSmallerBeforeGr8trAfter(new int[] { 5, 1, 4, 3, 6, 8, 10, 7, 9 });
		assertEquals(6, element);
	}



	@Test
	@DisplayName("Test Find Number With Elements That areSmaller Before Greater After")
	void testFindNumberWithElementsThatAreSmallerBeforeGreaterAfter1() {
		int element = findNumberSmallerBeforeGr8trAfter(new int[] { 5, 1, 4, 3, 6, 8, 4, 12, 99 });
		assertEquals(12, element);
	}



	@Test
	@DisplayName("Test Find Number With Elements That areSmaller Before Greater After")
	void testFindNumberWithElementsThatAreSmallerBeforeGreaterAfter2() {
		int element = findNumberSmallerBeforeGr8trAfter(new int[] { 1, 2, 3, 8, 99, 2 });
		assertEquals(2, element);
	}


	@Test
	@DisplayName("Test Find Number With Elements That areSmaller Before Greater After")
	void testFindNumberWithElementsThatAreSmallerBeforeGreaterAfter3() {
		int element = findNumberSmallerBeforeGr8trAfter(new int[] { 1, 2, 1, 8, 99, 2 });
		assertEquals(-1, element);
	}



	int findNumberSmallerBeforeGr8trAfter(int[] a) {
		int i = 0;
		int start = -1;

		Stack<Integer> st = new Stack<>();

		while (i < a.length) {
			if (st.isEmpty() || a[i] < a[st.peek()]) {
				if (start != -1 && a[i] < a[start])
					start = -1;

				st.push(i);
			} else {
				if (start == -1) {
					while (!st.isEmpty() && a[i] > a[st.peek()])
						st.pop();

					if (st.isEmpty())
						start = i;
				}

				st.push(i);
			}

			i++;
		}

		return start == -1 ? -1 : a[start];
	}



	@Test
	@DisplayName("Test Find Pair With Greatest Sum")
	void testFindPairWithGreatestSum() {
		int sum = findPairWithGreatestSum(new int[] { 12, 34, 10, 6, 40 });
		assertEquals(74, sum);
	}



	@Test
	@DisplayName("Test Find Pair With Greatest Sum")
	void testFindPairWithGreatestSum1() {
		int sum = findPairWithGreatestSum(new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 });
		assertEquals(17, sum);
	}


	int findPairWithGreatestSum(int[] a) {
		//firtGrtst+secondGrtst = LargestPair
		int f = Integer.MIN_VALUE;
		int s = Integer.MIN_VALUE;

		int i = 0;
		while (i < a.length) {
			if (a[i] > f) {
				if (f > s)
					s = f;
				f = a[i];
			}
			i++;
		}

		return f + s;
	}



	@Test
	@DisplayName("Test Merge Sort Algo")
	void testMergeSortAlgo() {
		int[] ints = mergeSortAlog(new int[] { 12, 11, 13, 5, 6, 7 });
		Arrays.stream(ints).forEach(i -> System.out.print(i + ", "));
	}


	int[] mergeSortAlog(int[] a) {
		int l = 0;
		int h = a.length - 1;
		_mergeSortHelper(a, l, h);

		return a;
	}

	private static void _mergeSortHelper(int[] a, int l, int h) {
		if (l < h) {
			int mid = l + (h - l) / 2;
			//callSort() on left part of mid(inclusive)
			_mergeSortHelper(a, l, mid);
			//callSort() on right part of mid(exclusive)
			_mergeSortHelper(a, mid + 1, h);
			//call a merge method on the whole array
			_merge(a, l, h, mid);
		}
	}

	private static void _merge(int[] a, int l, int h, int mid) {
		int lLt = mid - l + 1;
		int rLt = h - mid;

		int[] L = new int[lLt];
		int[] R = new int[lLt];

		for (int i = 0; i < lLt; i++) {
			L[i] = a[l + i];
		}
		for (int i = 0; i < rLt; i++) {
			R[i] = a[mid + 1 + i];
		}


		int i = 0, j = 0;
		int k = l;

		while (i < lLt && j < rLt) {
			if (L[i] < R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < lLt) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < rLt) {
			a[k] = R[j];
			j++;
			k++;
		}
	}




	@Test
	@DisplayName("Find Index For Key In Rotated Array")
	void findIndexForKeyInRotatedArray() {
		int idx = findIdxForKeyInRotatedArray(new int[] { 5, 6, 7, 8, 9, 10, 1, 2, 3 }, 3);
		assertEquals(8, idx);
	}



	@Test
	@DisplayName("Find Index For Key In Rotated Array")
	void findIndexForKeyInRotatedArray1() {
		int idx = findIdxForKeyInRotatedArray(new int[] { 5, 6, 7, 8, 9, 10, 1, 2, 3 }, 1);
		assertEquals(6, idx);
	}



	int findIdxForKeyInRotatedArray(int[] a, int k) {
		int l = 0;
		int h = a.length - 1;

		while (l <= h) {
			int mid = l + (h - l) / 2;

			if (a[mid] == k)
				return mid;

			if (a[l] < a[mid]) {
				if (k >= a[l] && k < a[mid]) {
					h = mid - 1;
				} else {
					l = mid + 1;
				}
			} else {
				if (k >= a[mid] && k <= a[h]) {
					l = mid + 1;
				} else {
					h = mid - 1;
				}
			}
		}
		return -1;
	}



	@Test
	@DisplayName("Test Find The Smallest Element In Rotate Yes Sorted Array")
	void testFindTheSmallestElementInRotateYesSortedArray() {
		int smallest = findTheSmallestInRotatedArray(new int[] { 6, 7, 1, 2, 3, 4, 5 });
		assertEquals(1, smallest);
	}



	@Test
	@DisplayName("Test Find The Smallest Element In Rotate Yes Sorted Array")
	void testFindTheSmallestElementInRotateYesSortedArray1() {
		int smallest = findTheSmallestInRotatedArray(new int[] { 5, 6, 1, 2, 3, 4 });
		assertEquals(1, smallest);
	}



	@Test
	@DisplayName("Test Find The Smallest Element In Rotate Yes Sorted Array")
	void testFindTheSmallestElementInRotateYesSortedArray2() {
		int smallest = findTheSmallestInRotatedArray(new int[] { 7, 8, 9, 3, 4, 5, 6 });
		assertEquals(3, smallest);
	}

	int findTheSmallestInRotatedArray(int[] a) {
		int l = 0;
		int h = a.length - 1;
		while (l <= h) {
			int mid = l + (h - l) / 2;

			if (a[mid] > a[mid + 1]) {
				return a[mid + 1];
			}

			if (a[mid - 1] > a[mid]) {
				return a[mid];
			}


			if (a[mid] < a[h]) {
				//means right half is sorted and the smallest element must be in the left sub array given the above condition on 777
				h = mid;
			} else {
				l = mid + 1;
			}
		}

		return -1;
	}



	@Test
	@DisplayName("Test Find The Fixed Point")
	void testFindTheFixedPoint() {
		int point = findTheFixedPointInTheArray(new int[] { -10, -5, 0, 3, 7 });
		assertEquals(3, point);
	}



	@Test
	@DisplayName("Test Find The Fixed Point")
	void testFindTheFixedPoint1() {
		int point = findTheFixedPointInTheArray(new int[] { 0, 2, 5, 8, 17 });
		assertEquals(0, point);
	}



	int findTheFixedPointInTheArray(int[] a) {
		int l = 0;
		int h = a.length - 1;

		while (l <= h) {
			int mid = l + (h - l) / 2;

			if (a[mid] == mid) {
				return mid;
			}

			if (a[mid] > mid) {
				h = mid;
			} else {
				l = mid + 1;
			}
		}


		return -1;
	}



	@Test
	@DisplayName("Test Find K Closest To X")
	void testFindKClosestToX() {
		int[] opAssert = { 30, 39, 42, 45 };
		int arr[] = { 12, 16, 22, 30, 35, 39, 42, 45, 48, 50, 53, 55, 56 };
		int[] op = findTheKClosestToX(arr, 35, 4);

		Arrays.sort(op);
		Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
		assertArrayEquals(opAssert, op);
	}



	int[] findTheKClosestToX(int[] a, int x, int k) {
		int[] op = new int[k];
		//find the crossover points
		int l = 0;
		int h = a.length - 1;

		int cs = -1;
		while (l <= h) {
			int mid = l + (h - l) / 2;

			if (a[mid] <= x && a[mid + 1] > x) {
				cs = mid;
				break;
			} else if (a[mid + 1] < x) {
				l = mid + 1;
			} else {
				h = mid - 1;
			}
		}

		if (cs != -1) {
			int cnt = 0;
			int cl = cs;
			int ch = cl + 1;
			if (a[cl] == x) {
				cl--;
			}

			while (cl >= 0 && ch < a.length && cnt < k) {
				if (x - a[cl] < a[ch] - x) {
					op[cnt] = a[cl--];
				} else {
					op[cnt] = a[ch++];
				}
				cnt++;
			}

			while (cl >= 0 && cnt < k) {
				op[cnt] = a[cl--];
				cnt++;
			}

			while (ch < a.length && cnt < k) {
				op[cnt] = a[ch++];
				cnt++;
			}
		}

		return op;
	}


}















