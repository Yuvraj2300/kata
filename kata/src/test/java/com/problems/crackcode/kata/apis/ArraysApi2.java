package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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

		@Override
		public String toString() {
			return "SubArrayWindow [start=" + start + ", end=" + end + "]";
		}



	}



	@Test
	void testSortAlternatively() throws Exception {
		int[] a = { 7, 1, 2, 3, 4, 5, 6 };

		int[] sol = sortAlternatively(a);

		for (int i : sol) {
			System.out.print(i + ", ");
		}
	}



	@Test
	void testSortAlternatively_1() throws Exception {
		int[] a = { 1, 6, 9, 4, 3, 7, 8, 2 };

		int[] sol = sortAlternatively(a);

		for (int i : sol) {
			System.out.print(i + ", ");
		}
	}



	int[] sortAlternatively(int[] a) {
		int[] toRet = new int[a.length];

		Arrays.sort(a);
		int i = 0;
		int j = a.length - 1;
		int k = 0;

		while (i <= j) {
			if (i == j) {
				toRet[k] = a[j];
				k++;
				break;
			}

			toRet[k] = a[j];
			k++;
			toRet[k] = a[i];
			k++;

			i++;
			j--;
		}

		return toRet;
	}



	@Test
	void testSortInWaveForm() throws Exception {
		int[] a = { 10, 5, 6, 3, 2, 20, 100, 80 };
		int[] sortedArr = sortInWaveForm(a);

		for (int i : sortedArr) {
			System.out.print(i + ", ");
		}
	}



	@Test
	void testSortInWaveForm_1() throws Exception {
		int[] a = { 10, 90, 49, 2, 1, 5, 23 };
		int[] sortedArr = sortInWaveForm(a);

		for (int i : sortedArr) {
			System.out.print(i + ", ");
		}
	}



	int[] sortInWaveForm(int[] a) {
		int i = 0;

		while (i < a.length) {
			if (i % 2 == 0) {
				if (i < a.length - 1 && !(a[i] > a[i + 1])) {
					_swap(a, i);
				}
			} else {
				if (i < a.length - 1 && !(a[i] < a[i + 1])) {
					_swap(a, i);
				}

			}
			i++;
		}
		return a;
	}



	@Test
	void testSortInWaveForm2_1() throws Exception {
		int[] a = { 10, 5, 6, 3, 2, 20, 100, 80 };
		int[] sortedArr = sortInWaveForm(a);

		for (int i : sortedArr) {
			System.out.print(i + ", ");
		}
	}



	@Test
	void testSortInWaveForm2_2() throws Exception {
		int[] a = { 10, 90, 49, 2, 1, 5, 23 };
		int[] sortedArr = sortInWaveForm(a);

		for (int i : sortedArr) {
			System.out.print(i + ", ");
		}
	}





	int[] sortInWaveForm_1(int[] a) {
		int i = 0;

		while (i < a.length) {
			if (i > 0 && !(a[i - 1] > a[i])) {
				_swap(a, i);
				if (i < a.length - 1 && !(a[i] < a[i + 1])) {
					_swap(a, i);
				}
			}

			i += 2;
		}
		return a;
	}




	private void _swap(int[] a, int i) {
		int temp = a[i];
		a[i] = a[i + 1];
		a[i + 1] = temp;
	}


	@Test
	void testGetArraySortedBy() throws Exception {
		int[] a = { 2, 5, 2, 8, 5, 6, 8, 8 };
		getArraySortedByFreq(a);
	}



	@Test
	void testGetArraySortedBy_1() throws Exception {
		int[] a = { 2, 5, 2, 6, -1, 9999999, 5, 8, 8, 8 };
		getArraySortedByFreq(a);
	}



	void getArraySortedByFreq(int[] a) {
		Map<Integer, Integer> hm = new HashMap<>();
		int i = 0;

		while (i < a.length) {
			hm.put(a[i], hm.getOrDefault(a[i], 0) + 1);
			i++;
		}

		// @formatter:off
		Map<Integer, Integer> valueSortedMap = 
				hm.entrySet().stream()
					.sorted(Collections.reverseOrder(Entry.comparingByValue()))
					.collect(
							Collectors.toMap(
									Entry::getKey, Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
 

// @formatter:on

		for (Map.Entry<Integer, Integer> e : valueSortedMap.entrySet()) {
			int j = 0;
			while (j < e.getValue()) {
				System.out.print(e.getKey() + ", ");
				j++;
			}
		}
	}



	@Test
	void testMergeSortedArraysContent() throws Exception {
		int[] a = { 1, 5, 9, 10, 15, 20 };
		int[] b = { 2, 3, 8, 13 };

		PairOfArrays sortedPair = mergeSortedArraysContents(new PairOfArrays(a, b));

		for (int i : sortedPair.getA()) {
			System.out.print(i + ", ");
		}
		System.out.println();
		for (int i : sortedPair.getB()) {
			System.out.print(i + ", ");
		}

	}



	@Test
	void testMergeSortedArraysContent_1() throws Exception {
		int[] a = { 10 };
		int[] b = { 2, 3 };

		PairOfArrays sortedPair = mergeSortedArraysContents(new PairOfArrays(a, b));

		for (int i : sortedPair.getA()) {
			System.out.print(i + ", ");
		}
		System.out.println();
		for (int i : sortedPair.getB()) {
			System.out.print(i + ", ");
		}

	}



	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	class PairOfArrays {
		int[] a;
		int[] b;
	}

	//when it comes to sorting and swapping always do from the posterior end :P 
	PairOfArrays mergeSortedArraysContents(PairOfArrays pair) {
		int[] a = pair.getA();
		int[] b = pair.getB();

		int i = 0;
		int j = 0;
		int k = a.length - 1;

		while (i <= k && j < b.length) {
			if (a[i] < b[j]) {
				i++;
			} else {
				//swap from k
				int temp = a[k];
				a[k] = b[j];
				b[j] = temp;
				k--;

				j++;
			}
		}


		Arrays.sort(a);
		Arrays.sort(b);

		pair.setA(a);
		pair.setB(b);

		return pair;
	}

	@Test
	void testMergeSortedArrays() throws Exception {
		int[] a = { 1, 2, 3, 0, 0, 0 };
		int[] b = { 2, 5, 6 };

		int[] mergedArr = mergeSortedArraysWithBuffer(a, b);

		for (int i : mergedArr) {
			System.out.print(i + ", ");
		}
	}



	@Test
	void testMergeSortedArrays_1() throws Exception {
		int[] a = { 10, 12, 13, 14, 18, 0, 0, 0, 0, 0 };
		int[] b = { 16, 17, 19, 20, 22 };

		int[] mergedArr = mergeSortedArraysWithBuffer(a, b);

		for (int i : mergedArr) {
			System.out.print(i + ", ");
		}
	}



	int[] mergeSortedArraysWithBuffer(int[] a, int[] b) {
		//assuming a is always given as teh array with buffer in it
		//assuming buffer is 0s
		int i = 0;
		while (a[i + 1] != 0) {
			i++;
		}
		int j = b.length - 1;
		int k = i + j + 1;

		while (j >= 0) {
			if (i >= 0 && j >= 0 && a[i] >= b[j]) {
				a[k] = a[i];
				i--;
			} else {
				a[k] = b[j];
				j--;
			}

			k--;
		}

		return a;
	}



	@Test
	void testRotateArrayByN() throws Exception {
		int[] a = { 1, 2, 3, 4, 5, 6, 7 };
		int n = 2;
		int[] arr = rotateArrayByN_ExtraSpace(a, n);
		for (int i : arr) {
			System.out.print(i + ", ");
		}
	}



	@Test
	void testRotateArrayByN_1() throws Exception {
		int[] a = { 3, 4, 5, 6, 7, 1, 2 };
		int n = 2;
		int[] arr = rotateArrayByN_ExtraSpace(a, n);
		for (int i : arr) {
			System.out.print(i + ", ");
		}
	}



	int[] rotateArrayByN_ExtraSpace(int[] a, int n) {
		int[] toReturn = new int[a.length];
		int i = 0;
		int j = n;

		while (j < a.length) {
			toReturn[i] = a[j];
			i++;
			j++;
		}

		int k = 0;
		while (i < toReturn.length && k < n) {
			toReturn[i] = a[k];
			i++;
			k++;
		}

		return toReturn;
	}



	@Test
	void testFindMinSubArrayWithSumGreaterThan() throws Exception {
		int[] a = { 1, 4, 45, 6, 0, 19 };
		int x = 51;
		assertEquals(3, findMinSubArrayWithSumGreaterThan(a, x));
	}



	@Test
	void testFindMinSubArrayWithSumGreaterThan_1() throws Exception {
		int[] a = { 1, 10, 5, 2, 7 };
		int x = 9;
		assertEquals(1, findMinSubArrayWithSumGreaterThan(a, x));
	}



	@Test
	void testFindMinSubArrayWithSumGreaterThan_2() throws Exception {
		int[] a = { 1, 11, 100, 1, 0, 200, 3, 2, 1, 250 };
		int x = 280;
		assertEquals(4, findMinSubArrayWithSumGreaterThan(a, x));
	}



	int findMinSubArrayWithSumGreaterThan(int[] a, int x) {
		int sl = 0;
		int sr = 0;
		int sum = 0;

		while (sr < a.length && sum < x) {
			sum += a[sr];
			sr++;
		}

		while (sl < sr && sum > x) {
			sum -= a[sl];
			sl++;
		}

		return sr - sl + 1;
	}



	@Test
	void testFindStartOfSubArrayWithMaxAvgSum() throws Exception {
		int arr[] = { 1, 12, -5, -6, 50, 3 };
		int k = 4;
		assertEquals(1, findStartOfSubArrayWithMaxAvgSum(arr, k));
	}

	int findStartOfSubArrayWithMaxAvgSum(int[] a, int k) {
		int i = 0;
		int j = 0;
		int sum = 0;
		int maxSum = 0;
		int maxSumStart = 0;

		while (j < k) {
			sum += a[i];
			j++;
		}

		maxSum = sum;
		maxSumStart = i;

		while (j < a.length) {
			sum = sum + a[j];

			if (j - i != k) {
				sum -= a[i];
				i++;
			}

			if (sum > maxSum) {
				maxSum = sum;
				maxSum = i;
			}
			j++;
		}


		return maxSum;
	}



	@Test
	void testSizeOfArrayWithMaxSum() throws Exception {
		int[] a = { 1, -2, 1, 1, -2, 1 };
		assertEquals(2, findSizeOfTheArrayWithMaxSum(a));
	}



	@Test
	void testSizeOfArrayWithMaxSum_1() throws Exception {
		int[] a = { 1, 0, 1, 1, -2, 1 };
		assertEquals(4, findSizeOfTheArrayWithMaxSum(a));
	}



	@Test
	void testSizeOfArrayWithMaxSum_2() throws Exception {
		int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		assertEquals(5, findSizeOfTheArrayWithMaxSum(a));
	}



	int findSizeOfTheArrayWithMaxSum(int[] a) {
		int i = 0;
		int start = 0;
		int end = 0;
		int currSum = 0;
		int maxSum = 0;

		while (i < a.length) {
			currSum += a[i];
			if (maxSum < currSum) {
				maxSum = currSum;
				end = i;
			}

			//since we want max
			if (currSum < 0) {
				currSum = 0;
				start = i + 1;
			}
			i++;
		}

		return end - start + 1;
	}




	@Test
	void testSubarrayWithMaxSum() throws Exception {
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		assertEquals(7, findMaxSumOfSubArray(a));
	}



	@Test
	void testSubarrayWithMaxSum_1() throws Exception {
		int a[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		assertEquals(6, findMaxSumOfSubArray(a));
	}

	//if all the elements of the array are negative then the largest negative number is the answer
	//If the array contains all non-positive numbers, 
	//then a solution is any subarray of size 1 containing the maximal value of the array (or the empty subarray, if it is permitted).
	int findMaxSumOfSubArray(int[] a) {
		int currSum = 0;
		int maxSum = 0;


		int i = 0;
		while (i < a.length) {
			currSum = Math.max(0, currSum + a[i]);
			maxSum = Math.max(maxSum, currSum);
			i++;
		}
		return maxSum;
	}



	@Test
	void testSubarrayWithMaxSumPos() throws Exception {
		int a[] = { -2, -3, 4, -1, -2, 1, 5, -3 };
		assertEquals(7, findMaxSumOfSubArray_1(a));
	}



	@Test
	void testSubarrayWithMaxSumPos_1() throws Exception {
		int a[] = { -2, 1, -3, 4, -1, 2, 1, -5, 4 };
		assertEquals(6, findMaxSumOfSubArray_1(a));
	}

	@Test
	void testSubarrayWithMaxSumPos_2() throws Exception {
		int a[] = { -2, -1, -3, -4, -1, -2, -1, -5, -4 };
		//assuming zero is permiited if not then a logic would be needed track the largest negative number
		assertEquals(0, findMaxSumOfSubArray_1(a));
	}


	//keeping track of positions
	int findMaxSumOfSubArray_1(int[] a) {
		int currSum = 0;
		int maxSum = 0;
		int start = 0;
		int end = 0;

		int j = 0;

		while (j < a.length) {
			currSum += a[j];

			if (currSum < 0) {
				currSum = 0;
				start = j + 1;
			}

			//			maxSum = Math.max(maxSum, currSum);
			//write if block to update max and end
			if (maxSum < currSum) {
				maxSum = currSum;
				end = j;
			}

			j++;

		}

		System.out.println("Size of the array : " + (end - start + 1) + ", start : " + start + " end : " + end);

		return maxSum;
	}


	@Test
	void testSortTwoArraysAmongTem() throws Exception {
		int[] a1 = { 1, 5, 9, 10, 15, 20 };
		int[] a2 = { 2, 3, 8, 13 };
		sortTwoArraysAmongThem(a1, a2);

		Arrays.sort(a1);
		Arrays.sort(a2);

		for (int i : a1) {
			System.out.print(i + ", ");
		}
		System.out.println();

		for (int i : a2) {
			System.out.print(i + ", ");
		}
	}



	void sortTwoArraysAmongThem(int[] a1, int[] a2) {
		int i = 0;
		int j = 0;
		int k = a1.length - 1;

		while (i < a1.length && k >= 0) {
			if (a1[i] < a2[j]) {
				i++;
			} else {
				int temp = a1[k];
				a1[k] = a2[j];
				a2[j] = temp;

				k--;
				j++;
			}
		}

	}



	@Test
	void testFindSubArrayWindow() throws Exception {
		int[] a = { -2, -3, 4, -1, -2, 1, 5, -3 };
		SubArrayWindow window = findSubArrayWindow(a);
	}



	@Test
	void testFindSubArrayWindow_1() throws Exception {
		int[] a = { 1, -2, 1, 1, -2, 1 };
		SubArrayWindow window = findSubArrayWindow(a);
	}



	@Test
	void testFindSubArrayWindow_2() throws Exception {
		int[] a = { -2, -3, 4, -1, -2, 1, 5, -3, -9, -4 };
		SubArrayWindow window = findSubArrayWindow(a);
	}



	SubArrayWindow findSubArrayWindow(int[] a) {
		int start = 0;
		int end = 0;
		int maxSum = 0;

		int currSum = 0;

		int s = 0;
		int i = 0;

		while (i < a.length) {
			currSum += a[i];

			if (currSum < 0) {
				s = i + 1;
				currSum = 0;
			}
			if (maxSum < currSum) {
				maxSum = currSum;
				start = s;
				end = i;
			}
			i++;
		}

		SubArrayWindow window = new SubArrayWindow();
		window.setStart(start);
		window.setEnd(end);

		System.out.println("max sum : " + maxSum);
		System.out.println("window : " + window);
		return window;
	}



	@Test
	void testSortArrayWithBuffer() throws Exception {
		int[] a = { 10, 12, 13, 14, 18, 0, 0, 0, 0, 0 };
		int[] b = { 16, 17, 19, 20, 22 };

		sortArrayWithBuffer(a, b);
	}



	int[] sortArrayWithBuffer(int[] a, int[] b) {
		int k = 0;
		while (a[k + 1] != 0) {
			k++;
		}

		int i = a.length - 1;
		int j = b.length - 1;

		while (i >= 0 && j >= 0) {
			if (a[k] < b[j]) {
				a[i] = b[j];
			} else {
				int temp = a[k];
				a[k] = b[j];
				a[i] = temp;
			}
			i--;
			j--;
		}



		return a;
	}
}



