package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.junit.jupiter.api.DisplayName;
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

		int[] expected = { 10, 12, 13, 14, 16, 17, 18, 19, 20, 22 };

		int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
		Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

		assertArrayEquals(expected, mergedArr);
	}


	@Test
	void testMergeSortedArrays_2() throws Exception {
		int[] a = { 10, 12, 13, 14, 16, 0, 0, 0, 0, 0 };
		int[] b = { 16, 17, 19, 20, 22 };

		int[] expected = { 10, 12, 13, 14, 16, 16, 17, 19, 20, 22 };

		int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
		Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

		assertArrayEquals(expected, mergedArr);
	}


	@Test
	void testMergeSortedArrays_3() throws Exception {
		int[] a = { 10, 12, 13, 14, 20, 0, 0, 0, 0, 0 };
		int[] b = { 16, 17, 19, 20, 22 };

		int[] expected = { 10, 12, 13, 14, 16, 17, 19, 20, 20, 22 };

		int[] mergedArr = mergeSortedArraysWithBuffer(a, b);
		Arrays.stream(mergedArr).forEach(i -> System.out.print(i + ", "));

		assertArrayEquals(expected, mergedArr);
	}


	int[] mergeSortedArraysWithBuffer(int[] a, int[] b) {
		// assuming a is the array with buffer always
		int k = -1;
		for (int i = 0; i < a.length; i++) {
			if (a[i + 1] == 0) {
				k = i;
				break;
			}
		}

		int i = a.length - 1;
		int j = b.length - 1;

		while (i > k) {
			if (b[j] >= a[k]) {
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



	@Test
	void testGetMaxLength() throws Exception {
		assertEquals(10, getMaxLength(11));
	}


	int getMaxLength(int n) {
		int low = 0;
		int high = 1000;

		while (high - low > 1) {
			int mid = (high - low) / 2 + low;

			if (totalCost(mid) <= n) {
				low = mid;
			} else {
				high = mid - 1;
			}
		}

		if (totalCost(high) <= n) {
			return high;
		}

		if (totalCost(low) <= n) {
			return low;
		}

		return 0;
	}



	private int totalCost(int n) {
		int cnt = 0;
		for (int i = 1; i <= n; i *= 10) {
			cnt += (n - i + 1);
		}
		return cnt;
	}

	@Test
	void testGetAllRotaions() throws Exception {
		getAllRots("geeks");
	}

	void getAllRots(String s) {
		int j = 0;
		int i = 0;
		char[] charArray = s.toCharArray();
		int lt = charArray.length;
		int looper = 0;

		while (looper < lt) {
			char init = charArray[j];
			while (i < s.length()) {
				if (i == lt - 1) {
					charArray[i] = init;
				} else {
					charArray[i] = charArray[i + 1];
				}
				i++;
			}
			System.out.println(new String(charArray));
			i = 0;
			looper++;
		}
	}


	@Test
	void testGetSortedMergeArray() {
		int[] a = { 10, 12, 13, 14, 18, 0, 0, 0, 0, 0 };
		int[] b = { 16, 17, 19, 20, 22 };
		int[] sortedMergeArray = getSortedMergeArray(a, b);

		for (int i : sortedMergeArray) {
			System.out.print(i + ", ");
		}
	}


	/**
	 * Give first array as the larger one please
	 */
	public int[] getSortedMergeArray(int[] a, int[] b) {
		int i = 0;
		while (a[i + 1] != 0) {
			i++;
		}

		int j = a.length - 1;
		int k = b.length - 1;

		while (k >= 0) {
			if (a[i] < b[k]) {
				a[j] = b[k];
			} else {
				int temp = a[i];
				a[i] = b[k];
				a[j] = temp;
			}
			k--;
			j--;
		}

		return a;
	}



	@Test
	@DisplayName("Test Find Second Greatest Value")
	void testFindSecondGreatestValue() {
		int op = findSecondGreatestValue(new int[] { 12, 35, 1, 10, 34, 1 });
		assertEquals(34, op);
	}



	int findSecondGreatestValue(int[] a) {
		int fg = Integer.MIN_VALUE;
		int sg = Integer.MIN_VALUE;

		int i = 0;
		while (i < a.length) {
			if (a[i] > fg) {
				sg = fg;
				fg = a[i];
			}

			if (a[i] < fg && a[i] > sg)
				sg = a[i];

			i++;
		}

		if (sg != Integer.MIN_VALUE) {
			return sg;
		}
		return -1;
	}



	@Test
	@DisplayName("Test Move All The Zeros To The Left")
	void testMoveAllTheZerosToTheLeft() {
		int[] expected = { 34, 42, 15, 5, 0, 0 };
		int[] op = moveZerosToTheLeft(new int[] { 0, 0, 34, 42, 15, 5 });

		assertArrayEquals(expected, op);
	}




	int[] moveZerosToTheLeft(int[] a) {
		int count = 0;
		int i = 0;

		while (i < a.length) {
			if (a[i] != 0) {
				a[count] = a[i];
				count++;
			}
			i++;
		}

		while (count < a.length) {
			a[count] = 0;
			count++;
		}
		return a;
	}



	@Test
	@DisplayName("Test Vals At Event Must Be Greater")
	void testValsAtEventMustBeGreater() {
		int[] expected = { 1, 2, 1, 2 };
		int[] op = valsAtEvenMustBeGreater(new int[] { 1, 2, 2, 1 });

		assertArrayEquals(expected, op);
	}


	@Test
	@DisplayName("Test Vals At Event Must Be Greater")
	void testValsAtEventMustBeGreater2() {
		int[] expected = { 1, 3, 2, 5, 2 };
		int[] op = valsAtEvenMustBeGreater(new int[] { 1, 3, 2, 2, 5 });

		assertArrayEquals(expected, op);
	}


	@Test
	@DisplayName("Test Vals At Event Must Be Greater")
	void testValsAtEventMustBeGreater3() {
		int[] expected = { 2, 5 };
		int[] op = valsAtEvenMustBeGreater(new int[] { 5, 2 });

		assertArrayEquals(expected, op);
	}



	int[] valsAtEvenMustBeGreater(int[] a) {
		int i = 0;
		while (i < a.length) {
			if ((i + 1) % 2 == 0) {
				if (i > 0 && a[i] < a[i - 1]) {
					int temp = a[i - 1];
					a[i - 1] = a[i];
					a[i] = temp;
				}
			} else {
				if (i > 0 && a[i - 1] < a[i]) {
					int temp = a[i - 1];
					a[i - 1] = a[i];
					a[i] = temp;
				}
			}
			i++;
		}

		return a;
	}



	@Test
	@DisplayName("Test Arrange Max Min From Sorted")
	void testArrangeMaxMinFromSorted() {
		int[] expected = { 7, 1, 6, 2, 5, 3, 4 };
		int[] op = arrangeMaxMinFromSorted(new int[] { 1, 2, 3, 4, 5, 6, 7 });

		assertArrayEquals(expected, op);
	}


	int[] arrangeMaxMinFromSorted(int[] a) {
		int[] op = new int[a.length];

		int l = 0;
		int h = a.length - 1;

		boolean flag = true;

		int i = 0;
		while (i < op.length) {
			if (flag) {
				op[i] = a[h--];
				flag = false;
			} else {
				op[i] = a[l++];
				flag = true;
			}
			i++;
		}

		return op;
	}



	@Test
	@DisplayName("Test Event Before Odd Solution 1")
	void testEventBeforeOddSolution1() {
		int[] a = { 1, 3, 2, 4, 7, 6, 9, 10 };
		int[] expected = { 2, 4, 6, 10, 7, 1, 9, 3 };
		int[] op = evenBeforeOddSol1(a);

		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Event Before Odd Solution 1")
	void testEventBeforeOddSolution2() {
		int[] a = { 1, 9, 1, 3, 7, 6, 9, 10 };
		//		int[] expected = { 2, 4, 6, 10, 7, 1, 9, 3 };
		int[] op = evenBeforeOddSol1(a);
		Arrays.stream(op).forEach(i -> System.out.print(i + ","));
		//		assertArrayEquals(expected, op);
	}

	int[] evenBeforeOddSol1(int[] a) {
		//idea is to keep a ptr before the odd postion
		int oddPtr = -1;
		int i = 0;
		while (i < a.length) {
			if (a[i] % 2 == 0) {
				oddPtr++;
				int temp = a[i];
				a[i] = a[oddPtr];
				a[oddPtr] = temp;
			}
			i++;
		}

		return a;
	}


	int[] evenBeforeOddSol2(int[] a) {
		int[] idxA = new int[a.length];
		int[] op = new int[a.length];

		int i = 0;
		int j = 0;
		while (i < a.length) {
			if (a[i] % 2 == 0) {
				idxA[j] = i;
				j++;
			}
			i++;
		}

		i = 0;

		while (i < a.length) {
			if (a[i] % 2 != 0) {
				idxA[j] = i;
				j++;
			}
			i++;
		}

		for (int k = 0; k < idxA.length; k++) {
			op[k] = a[idxA[k]];
		}

		return op;
	}

	@Test
	@DisplayName("Rotate Array")
	void testRotateArray() {
		int[] expected = { 3, 4, 5, 6, 7, 1, 2 };
		int[] op = rotateArray(new int[] { 1, 2, 3, 4, 5, 6, 7 }, 2);

		assertArrayEquals(expected, op);
	}



	int[] rotateArray(int[] a, int d) {
		while (d > 0) {
			int first = a[0];
			int i = 0;
			while (i < a.length) {
				if (i != a.length - 1) {
					a[i] = a[i + 1];
				} else {
					a[i] = first;
				}
				i++;
			}
			d--;
		}
		return a;
	}



	@Test
	@DisplayName("Test Find Sum Array Index With Given Sum")
	void testFindSumArrayIndexWithGivenSum() {
		int[] expect = { 2, 4 };
		int[] inp = { 1, 4, 20, 3, 10, 5 };
		int[] op = findSubArrayIdxWithSum(inp, 33);
		assertArrayEquals(expect, op);
	}


	int[] findSubArrayIdxWithSum(int[] a, int sum) {
		int[] op = new int[2];
		int i = 0;
		int currSum = 0;
		int j = 0;
		while (i < a.length) {
			if (currSum < sum) {
				currSum += a[i];
			}
			while (j < i && currSum > sum) {
				currSum -= a[j];
				j++;
			}
			if (currSum == sum) {
				break;
			} else {
				i++;
			}
		}
		op[0] = j;
		op[1] = i;
		return op;
	}


	@Test
	@DisplayName("Test Make A of I as I")
	void testMakeAOfIAsI() {
		int[] expected = { -1, 1, 2, -1, 4 };
		int[] op = makeAofIAsI(new int[] { -1, -1, 4, 1, 2 });
		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Make A of I as I")
	void testMakeAOfIAsI_1() {
		int[] expected = { -1, 1, 2, 3, 4, -1, 6, -1, -1, 9 };
		int[] op = makeAofIAsI(new int[] { -1, -1, 6, 1, 9, 3, 2, -1, 4, -1 });
		assertArrayEquals(expected, op);
	}

	int[] makeAofIAsI(int[] a) {
		int i = 0;
		while (i < a.length) {
			if (a[i] != -1 && a[i] != i) {
				if (a[a[i]] == -1) {
					a[a[i]] = a[i];
					a[i] = -1;
					i++;
				} else {
					int temp = a[i];
					a[i] = a[a[i]];
					a[temp] = temp;
				}
			} else {
				i++;
			}
		}
		return a;
	}


	@Test
	@DisplayName("Test My Quick Sort")
	void testMyQuickSort() {
		int[] expected = { 1, 3, 5, 5, 7, 34 };
		int[] sorted = quickSort(new int[] { 5, 34, 1, 3, 5, 7 });

		assertArrayEquals(expected, sorted);
	}



	@Test
	@DisplayName("Test My Quick Sort")
	void testMyQuickSort1() {
		int[] expected = { 1, 2, 3, 6, 88, 89, 90 };
		int[] sorted = quickSort(new int[] { 90, 89, 1, 6, 3, 2, 88 });

		assertArrayEquals(expected, sorted);
	}


	int[] quickSort(int[] a) {
		_qSrtHlpr(a, 0, a.length - 1);
		return a;
	}

	private void _qSrtHlpr(int[] a, int l, int h) {
		if (l < h) {
			int pi = _partitionForSortAsc(a, l, h);
			_qSrtHlpr(a, l, pi - 1);
			_qSrtHlpr(a, pi + 1, h);
		}
	}

	private int _partitionForSortAsc(int[] a, int l, int h) {
		int i = l;
		int pe = a[h];
		int k = l - 1;
		while (i < h) {
			if (a[i] < pe) {
				k++;
				swap(a, i, k);
			}
			i++;
		}

		swap(a, h, ++k);

		return k;
	}


	@Test
	@DisplayName("Test Swap Negatives With Positives")
	void testSwapNegativesWithPositives() {
		int[] op = swapNegativesPositives(new int[] { -2, -3, -1, 6, 9, -1, 3 });
		Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
	}


	@Test
	@DisplayName("Test Swap Negatives With Positives")
	void testSwapNegativesWithPositives1() {
		int[] op = swapNegativesPositives(new int[] { -2, -3, -1, 6, 9, -1, -13 });
		Arrays.stream(op).forEach(i -> System.out.print(i + ", "));
	}



	int[] swapNegativesPositives(int[] a) {
		int l = 0;
		int h = a.length - 1;
		//assuming h is the partition value always

		int i = 0;
		int k = l - 1;
		while (i < h) {
			if (a[i] < 0) {
				k++;
				swap(a, k, i);
			}
			i++;
		}
		//bring the partition element at it's right position
		swap(a, k + 1, h);
		int pi = -1;
		//this is to make sure that you do not end up swapping a negative value
		//as we are partitioning on the basis of a[i]<0 and not using element at the pivot
		if (a[k + 1] < 0) {
			pi = k + 2;
		} else {
			pi = k + 1;
		}
		//all values at and beyond pi should be positive now

		i = 0;
		while (i < a.length) {
			if (a[i] < 0 && pi <= h) {
				swap(a, i + 1, pi);
				pi++;
			}
			i++;
		}

		return a;
	}



	@Test
	@DisplayName("Test Swap as Per Mapping Given")
	void testSwapAsPerMappingGiven() {
		int[] expected = { 11, 10, 12 };

		int[] map = { 1, 0, 2 };
		int[] a = { 10, 11, 12 };
		int[] op = swapAsPerMappingGiven(a, map);

		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Swap as Per Mapping Given")
	void testSwapAsPerMappingGiven1() {
		int[] expected = { 40, 60, 90, 50, 70 };

		int[] map = { 3, 0, 4, 1, 2 };
		int[] a = { 50, 40, 70, 60, 90 };
		int[] op = swapAsPerMappingGiven(a, map);

		assertArrayEquals(expected, op);
	}


	int[] swapAsPerMappingGiven(int[] a, int[] map) {
		int i = 0;
		while (i < a.length) {
			if (a[i] != a[map[i]]) {
				swap(a, i, map[i]);
				swap(map, i, map[i]);
			}

			i++;
		}

		return a;
	}


	@Test
	@DisplayName("Test Find Smallest Missing Number")
	void testFindSmallestMissingNumber() {
		int op = findSmallestMissingNo(new int[] { 0, 1, 2, 6, 9 });
		assertNotEquals(-1, op);
		assertEquals(3, op);
	}



	@Test
	@DisplayName("Test Find Smallest Missing Number")
	void testFindSmallestMissingNumber1() {
		int op = findSmallestMissingNo(new int[] { 4, 5, 10, 11 });
		assertNotEquals(-1, op);
		assertEquals(0, op);
	}



	@Test
	@DisplayName("Test Find Smallest Missing Number")
	void testFindSmallestMissingNumber2() {
		int op = findSmallestMissingNo(new int[] { 0, 1, 2, 3, 4, 5, 6, 7, 10 });
		assertNotEquals(-1, op);
		assertEquals(8, op);
	}



	int findSmallestMissingNo(int[] a) {
		int l = 0;
		int h = a.length - 1;

		while (l <= h) {
			if (a[l] != l) {
				return l;
			}
			int mid = l + (h - l) / 2;

			if (a[mid] == mid) {
				l = mid + 1;
			} else if (a[mid] > mid) {
				h = mid;
			}

		}
		return -1;
	}



	@Test
	@DisplayName("Test Find Sub Array With Sum Greater Than Given")
	void testFindSubArrayWithSumGreaterThanGiven() {
		int expected[] = { 4, 45, 6 };
		int[] op = findSubArrayWithSumGreaterThanGiven(new int[] { 1, 4, 45, 6, 0, 19 }, 51);
		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Find Sub Array With Sum Greater Than Given")
	void testFindSubArrayWithSumGreaterThanGiven_1() {
		int expected[] = { 10 };
		int[] op = findSubArrayWithSumGreaterThanGiven(new int[] { 1, 10, 5, 2, 7 }, 9);
		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Find Sub Array With Sum Greater Than Given")
	void testFindSubArrayWithSumGreaterThanGiven_2() {
		int expected[] = { 100, 1, 0, 200 };
		int[] op = findSubArrayWithSumGreaterThanGiven(new int[] { 1, 11, 100, 1, 0, 200, 3, 2, 1, 250 }, 280);
		assertArrayEquals(expected, op);
	}



	@Test
	@DisplayName("Test Find Sub Array With Sum Greater Than Given")
	void testFindSubArrayWithSumGreaterThanGiven_3() {
		int expected[] = { 50, 1 };
		int[] op = findSubArrayWithSumGreaterThanGiven(new int[] { 1, 4, 45, 6, 0, 19, 50, 1 }, 51);
		assertArrayEquals(expected, op);
	}


	int[] findSubArrayWithSumGreaterThanGiven(int[] a, int x) {
		int end = 0;
		int start = 0;
		int fs = 0;
		int fe = 0;
		int sum = 0;
		int minL = a.length;

		while (end < a.length) {
			while (end < a.length && sum <= x) {
				sum += a[end];
				end++;
			}

			while (start < a.length && sum > x) {
				sum -= a[start];
				start++;
			}

			if (end - start < minL) {
				minL = end - start + 1;
				fs = start - 1;
				fe = end - 1;

				sum = 0;
				start = end;
			}
		}

		int[] op = new int[minL];
		for (int i = fs; i <= fe; i++) {
			op[i - fs] = a[i];
		}
		return op;
	}



	@Test
	@DisplayName("Test Merge Two Sorted Arrays In Relative Order")
	void testMergeTwoSortedArraysInRelativeOrder() {
		// @formatter:off
		mergeTwoSortedArraysInRelativeOrders(new int[] {1, 5, 9, 10, 15, 20},
				new int[] {2, 3, 8, 13});
		// @formatter:on

	}


	void mergeTwoSortedArraysInRelativeOrders(int[] a, int[] b) {
		int j = b.length - 1;

		while (j >= 0) {
			int i = a.length - 1;
			int last = a[a.length - 1];
			while (i >= 0 && a[i] > b[j]) {
				a[i] = a[i - 1];
				i--;
			}

			a[i + 1] = b[j];
			b[j] = last;

			j--;
		}


		Arrays.stream(a).forEach(i -> System.out.print(i + ", "));
		System.out.println();
		Arrays.stream(b).forEach(i -> System.out.print(i + ", "));
	}



	@Test
	@DisplayName("Test Find Majority Element")
	void testFindMajorityElement() {
		int majorityElement = findMajorityElement(new int[] { 3, 3, 4, 2, 4, 4, 2, 4, 4 });
		assertEquals(4, majorityElement);
	}



	@Test
	@DisplayName("Test Find Majority Element")
	void testFindMajorityElement1() {
		int majorityElement = findMajorityElement(new int[] { 3, 3, 4, 2, 4, 4, 2, 4 });
		assertEquals(-1, majorityElement);
	}


	int findMajorityElement(int[] a) {
		int reqLt = a.length / 2;

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (max < a[i]) {
				max = a[i];
			}
		}

		int freqHash[] = new int[max + 1];

		for (int i = 0; i < a.length; i++) {
			freqHash[a[i]]++;
		}

		for (int i = 0; i < a.length; i++) {
			if (freqHash[a[i]] > reqLt) {
				return a[i];
			}
		}

		return -1;
	}



	@Test
	@DisplayName("Find Pair with Sum In Sorted Array")
	void findPairWithSumInSortedArray() {
		int[] expected = { 4, 5 };
		int[] op = findPairWithSumInSortedArray(new int[] { 1, 2, 4, 5, 6 }, 9);
		assertArrayEquals(expected, op);
	}


	@Test
	@DisplayName("Find Pair with Sum In Sorted Array")
	void findPairWithSumInSortedArray1() {
		int[] expected = { 20, 50 };
		int[] op = findPairWithSumInSortedArray(new int[] { 10, 20, 35, 50, 75, 80 }, 70);
		assertArrayEquals(expected, op);
	}


	int[] findPairWithSumInSortedArray(int[] a, int x) {
		int[] op = new int[2];
		int i = 0;
		int j = a.length - 1;
		while (i <= j) {
			int sum = a[i] + a[j];
			if (sum == x) {
				op[0] = a[i];
				op[1] = a[j];
				return op;
			} else if (sum > x) {
				j--;
			} else {
				i++;
			}
		}
		return op;
	}



	@Test
	@DisplayName("Test Find Point Of Eqlbrm")
	void testFindPointOfEqlbrm() {
		int ptOfEqlbrm = findPointOfEqulbrm(new int[] { -7, 1, 5, 2, -4, 3, 0 });
		assertEquals(3, ptOfEqlbrm);
	}



	@Test
	@DisplayName("Test Find Point Of Eqlbrm")
	void testFindPointOfEqlbrm1() {
		int ptOfEqlbrm = findPointOfEqulbrm(new int[] { 1, 2, 3 });
		assertEquals(-1, ptOfEqlbrm);
	}



	int findPointOfEqulbrm(int[] a) {
		int i = 0;
		int j = a.length - 1;
		int sL = 0;
		int sR = 0;
		while (i <= j) {
			sL += a[i++];
			sR += a[j--];

			if (sL == sR && i == j) {
				return i;
			}
		}

		return -1;
	}


	void swap(int[] a, int i, int j) {
		int temp = a[i];
		a[i] = a[j];
		a[j] = temp;
	}



	@Test
	@DisplayName("Test Move All Zeros To End")
	void testMoveAllZerosToEnd() {
		int[] expected = { 1, 2, 4, 3, 5, 0, 0, 0 };
		int[] op = moveAllZerosToEnd(new int[] { 1, 2, 0, 4, 3, 0, 5, 0 });
		assertArrayEquals(expected, op);
	}


	@Test
	@DisplayName("Test Move All Zeros To End")
	void testMoveAllZerosToEnd1() {
		int[] expected = { 5, 4, 23, 1, 0 };
		int[] op = moveAllZerosToEnd(new int[] { 5, 4, 0, 23, 1 });
		assertArrayEquals(expected, op);
	}



	int[] moveAllZerosToEnd(int[] a) {
		int i = 0;
		int j = 0;

		while (i < a.length) {
			if (a[i] != 0) {
				a[j] = a[i];
				j++;
			}
			i++;
		}

		while (j < a.length) {
			a[j] = 0;
			j++;
		}

		return a;
	}



	@Test
	@DisplayName("Test Find Leaders in The Array")
	void testFindLeadersInTheArray() {
		int[] expected = { 17, 5, 2 };
		int[] op = findLeadersInTheArray(new int[] { 16, 17, 4, 3, 5, 2 });
		assertArrayEquals(expected, op);
	}



	int[] findLeadersInTheArray(int[] a) {
		Stack<Integer> st = new Stack<>();
		st.push(0);
		int i = 1;
		while (i < a.length) {
			while (!st.isEmpty() && a[i] > a[st.peek()]) {
				st.pop();
			}
			st.push(i);
			i++;
		}
		return st.stream().map(index -> a[index]).mapToInt(v -> v.intValue()).toArray();
	}


	@Test
	@DisplayName("Find Range With Sum")
	void findRangeWithSum() {
		int[] expected = { 2, 4 };
		List<? super Number> rangeWithSum = findRangeWithSum(new int[] { 1, 4, 20, 3, 10, 5 }, 33);
		rangeWithSum.forEach(i -> System.out.print(i + ", "));
	}


	List<? super Number> findRangeWithSum(int[] a, int x) {
		List<? super Number> op = new LinkedList<>();
		int i = 0;
		int j = 0;
		int csum = 0;
		while (i < a.length && csum < x) {
			csum += a[i++];
		}

		while (j < a.length && csum >= x) {
			csum -= a[++j];
		}

		op.add(i);
		op.add(j - 1);

		return op;
	}



	@Test
	@DisplayName("Test Find Distinct Value")
	void testFindDistinctValue() {
		int[] expected = { 1, 4, 3, 5, 6, 7 };
		int[] op = findDistinctValues(new int[] { 1, 4, 3, 1, 3, 5, 5, 7, 6, 7 });
		Set<Integer> asSet = Arrays.stream(expected).mapToObj(i -> i).collect(Collectors.toSet());
		for (int i : op) {
			assertTrue(asSet.contains(i));
		}
	}


	int[] findDistinctValues(int[] a) {
		int maxVal = Integer.MIN_VALUE;
		for (int i = 0; i < a.length; i++) {
			if (a[i] > maxVal)
				maxVal = a[i];
		}
		int[] f = new int[maxVal + 1];
		List<Integer> opList = new LinkedList<>();

		int i = 0;
		while (i < a.length) {
			f[a[i]]++;
			i++;
		}

		for (int j = 0; j < f.length; j++) {
			if (f[j] == 1)
				opList.add(j);
		}

		return opList.stream().mapToInt(k -> k).toArray();
	}

}




