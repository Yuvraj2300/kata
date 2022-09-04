package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import com.problems.crackcode.kata.exceptions.KataException;

public class ArraysApi {
	private void findThreeNumbers(int[] a, int arr_size, int sum) {

		Set<Integer> hs = new HashSet<>();

		for (int i = 0; i < a.length; i++) {
			int currSum = sum - a[i];
			if (hs.contains(currSum)) {
				for (int j = i + 1; j < a.length; j++) {
					if (hs.contains(currSum - a[j])) {
						System.out.println(a[i] + " " + a[j] + " " + (currSum - a[j]));
						return;
					}
					hs.add(a[j]);
				}
			}
			hs.add(a[i]);
		}

	}



	private void findThreeNumbers_TwoPointer(int[] a, int arr_size, int sum) {
		Arrays.sort(a);
		List<Integer> resultBuffer = new ArrayList<>();

		int l = 0;
		int h = arr_size - 1;



		while (l < h) {
			int currSum = a[l] + a[h];

			if (currSum == sum) {
				resultBuffer.add(a[l]);
				resultBuffer.add(a[h]);
				break;
			}

			if (currSum > sum) {
				h--;
			}

			if (currSum < sum) {
				l++;
			}
		}

		for (Integer integer : resultBuffer) {
			System.out.print(integer + " ");
		}
	}



	@Test
	void testFindThreeNumbers() throws Exception {
		int A[] = { 1, 4, 45, 6, 10, 8 };
		int sum = 22;
		int arr_size = A.length;

		findThreeNumbers(A, arr_size, sum);
	}



	@Test
	void testFindThreeNumbers_1() throws Exception {
		int A[] = { 10, 20, 35, 50, 75, 80 };
		int sum = 70;
		int arr_size = A.length;

		findThreeNumbers_TwoPointer(A, arr_size, sum);
	}


	private int findMajorityElement(int[] arr) {
		Map<Integer, Integer> mapOfValuesWithOccur = new HashMap<>();

		for (int i = 0; i < arr.length; i++) {
			if (mapOfValuesWithOccur.containsKey(arr[i])) {
				mapOfValuesWithOccur.put(arr[i], mapOfValuesWithOccur.get(arr[i]) + 1);
			} else {
				mapOfValuesWithOccur.put(arr[i], 1);
			}
		}

		Integer value = Collections.max(mapOfValuesWithOccur.entrySet(), Map.Entry.comparingByValue()).getKey();

		return value;
	}



	@Test
	void testFindMajorityElement() throws Exception {
		int arr[] = { 1, 1, 2, 1, 3, 5, 1 };
		int max = findMajorityElement(arr);
		assertEquals(1, max);
	}



	@Test
	void testFindMajorityElement_1() throws Exception {
		int arr[] = { 7, 7, 2, 7, 5, 1 };
		int max = findMajorityElement(arr);
		assertEquals(7, max);
	}


	@Test
	void testFindMajorityElement_2() throws Exception {
		int arr[] = { 3, 3, 4, 2, 4, 4, 2, 4, 4 };
		int max = findMajorityElement(arr);
		assertEquals(4, max);
	}



	private int findEquilib(int[] a) {
		int l = 0;
		int h = a.length - 1;

		return _binaryToFindEquilib(a, l, h);
	}



	private int _binaryToFindEquilib(int[] a, int l, int h) {
		int mid = (l + h) / 2;
		int sumL = 0;
		int sumR = 0;

		for (int i = 0; i < mid; i++) {
			sumL += a[i];
		}

		for (int i = a.length - 1; i > mid; i--) {
			sumR += a[i];
		}

		if (sumL == sumR) {
			return mid;
		} else if (sumL < sumR) {
			return _binaryToFindEquilib(a, l, h--);
		} else {
			return _binaryToFindEquilib(a, l++, h);
		}
	}


	@Test
	void testFindEquilibriumIndex() throws Exception {
		int a[] = { -7, 1, 5, 2, -4, 3, 0 };

		int equlibIdx = findEquilib(a);
		assertEquals(3, equlibIdx);

	}


	private int findPeakElement(int[] a) {

		int l = 0;
		int h = a.length - 1;


		return _binaryToSearchForPeak(a, l, h);

	}



	private int _binaryToSearchForPeak(int[] a, int l, int h) {
		int mid = l + h / 2;

		if (mid == a.length - 1) {
			return a[mid];
		}

		if (a[mid] == a[mid - 1] && a[mid] == a[mid + 1]) {
			return a[mid];
		}


		if (a[mid] > a[mid + 1] && a[mid] > a[mid - 1]) {
			return a[mid];
		} else if (a[mid + 1] > a[mid]) {
			return _binaryToSearchForPeak(a, l + 1, h);
		} else {
			return _binaryToSearchForPeak(a, l, h - 1);
		}



	}



	@Test
	void testFindPeakElement() throws Exception {
		int a[] = { 5, 10, 20, 15 };

		int peakElement = findPeakElement(a);
		assertEquals(20, peakElement);
	}



	@Test
	void testFindPeakElement_1() throws Exception {
		int a[] = { 10, 20, 15, 2, 23, 90, 67 };

		int peakElement = findPeakElement(a);
		assertEquals(90, peakElement);
	}


	@Test
	void testFindPeakElement_2() throws Exception {
		int a[] = { 10, 20, 30, 40, 50 };

		int peakElement = findPeakElement(a);
		assertEquals(50, peakElement);
	}


	@Test
	void testFindPeakElement_3() throws Exception {
		int a[] = { 20, 20, 20, 20, 20 };

		int peakElement = findPeakElement(a);
		assertEquals(20, peakElement);
	}



	private List<Integer> findSubArray(int[] a, int sumToCheck) {
		List<Integer> range = new ArrayList<Integer>();

		int p = 0;
		int currSum = a[p];

		int i = 1;
		while (i < a.length) {

			if (currSum == sumToCheck) {
				range.add(p);
				range.add(i - 1);
				break;
			}

			if (currSum > sumToCheck) {
				currSum -= a[p];
				p++;
			} else {
				currSum += a[i];
				i++;
			}
		}



		return range;
	}



	@Test
	void testFindSubArray() throws Exception {
		int a[] = { 1, 4, 20, 3, 10, 5 };

		List<Integer> range = findSubArray(a, 33);
		System.out.println(range);
	}


	@Test
	void testFindSubArray_1() throws Exception {
		int a[] = { 1, 4, 0, 0, 3, 10, 5 };

		List<Integer> range = findSubArray(a, 7);
		System.out.println(range);
	}



	private int[] mySort(int[] a, String type) {
		if (StringUtils.isBlank(type)) {
			int l = 0;
			int h = a.length - 1;
			_mergeSort(a, l, h);
		}

		if ("quick".equals(type)) {
			_quickSort(a);
		}

		return a;
	}



	private void _quickSort(int[] a) {
		int l = 0;
		int h = a.length - 1;

		_quickSortUtil(a, l, h);
	}



	private void _quickSortUtil(int[] a, int l, int h) {
		int pivotIdx = l;
		if (l < h) {
			pivotIdx = _getPivotIdx(a, pivotIdx, l, h);
			//			System.out.println("Pivot is at  : " + pivotIdx);
			_quickSortUtil(a, l, pivotIdx - 1);
			_quickSortUtil(a, pivotIdx + 1, h);
		}
	}



	private int _getPivotIdx(int[] a, int pivotIdx, int l, int h) {
		while (l != pivotIdx || h != pivotIdx) {

			while (a[h] > a[pivotIdx]) {
				h--;
			}

			if (a[h] < a[pivotIdx]) {
				_swap(a, pivotIdx, h);
			}
			pivotIdx = h;

			while (a[l] < a[pivotIdx]) {
				l++;
			}

			if (a[l] > a[pivotIdx]) {
				_swap(a, pivotIdx, l);
			}
			pivotIdx = l;
		}
		return pivotIdx;
	}



	private void _swap(int[] a, int i, int j) {
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}



	@Test
	void testQuickSort() throws Exception {
		int[] a = { 16, 20, 1, 2, 19 };

		mySort(a, "quick");

		for (int i : a) {
			System.out.print(i + " ");
		}
	}


	@Test
	void testQuickSort_1() throws Exception {
		int[] a = { 90, 34, 1, 45, 21, -3 };

		mySort(a, "quick");


		for (int i : a) {
			System.out.print(i + " ");
		}
	}

	private int[] _mergeSort(int[] a, int l, int h) {

		if (l < h) {
			int mid = (h + l) / 2;

			_mergeSort(a, l, mid);
			_mergeSort(a, mid + 1, h);
			merge(a, l, h, mid);
		}
		return a;
	}




	private void merge(int[] a, int l, int h, int mid) {
		int splitSize1 = mid - l + 1;
		int splitSize2 = h - mid;

		int split1[] = new int[splitSize1];
		int split2[] = new int[splitSize2];

		for (int i = 0; i < splitSize1; i++) {
			split1[i] = a[l + i];
		}

		for (int i = 0; i < splitSize2; i++) {
			split2[i] = a[mid + 1 + i];
		}

		int p = l;

		int i = 0;
		int j = 0;
		while (i < splitSize1 && j < splitSize2) {
			if (split1[i] <= split2[j]) {
				a[p] = split1[i];
				i++;
			} else {
				a[p] = split2[j];
				j++;
			}
			p++;
		}


		while (i < splitSize1) {
			a[p] = split1[i];
			i++;
			p++;
		}


		while (j < splitSize2) {
			a[p] = split2[j];
			j++;
			p++;
		}
	}



	@Test
	void testSortArray() {
		int a[] = { 2, 6, 18, 4 };
		int sorteda[] = mySort(a, "");

		for (int i : sorteda) {
			System.out.print(i + ",");
		}
	}


	private void sortTwoArray(int[] a, int[] b) {
		int size1 = a.length;
		int size2 = b.length;

		int i = size1 - 1;
		int j = size2 - 1;

		//		if (size2 > size1) {
		while (i >= 0) {
			while (j >= 0) {
				if (b[j] < a[i]) {
					int temp = a[i];
					a[i] = b[j];
					b[j] = temp;
				}
				j--;
			}
			j = size2 - 1;
			i--;
		}

		mySort(a, "");
		mySort(b, "");
	}
	//&& i >= 0	
	//	else {
	//			
	//		}

	//	}

	@Test
	void testSortTwoArrays() throws Exception {
		int[] a = { 4, 3, 8 };
		int[] b = { 6, 18, 12, 5 };

		sortTwoArray(a, b);

		for (int i : a) {
			System.out.print(i + ",");
		}

		System.out.println();
		for (int i : b) {
			System.out.print(i + ",");
		}
	}



	@Test
	void testSortTwoArrays_1() throws Exception {
		int[] a = { 12, 3, 8, 4 };
		int[] b = { 6, 18, 12, 5 };

		sortTwoArray(a, b);

		for (int i : a) {
			System.out.print(i + ",");
		}

		System.out.println();
		for (int i : b) {
			System.out.print(i + ",");
		}
	}


	@Test
	void testSortTwoArrays_2() throws Exception {
		int[] a = { 12, 3, 8, 4 };
		int[] b = { 6, 18, 12 };

		sortTwoArray(a, b);

		for (int i : a) {
			System.out.print(i + ",");
		}

		System.out.println();
		for (int i : b) {
			System.out.print(i + ",");
		}
	}



	private int[] mergeAndSortTwoArray(int[] a, int[] b) {
		int size1 = a.length;
		int size2 = b.length;

		int[] res = new int[size1 + size2];
		int p = 0;

		int i = 0;
		int j = 0;
		while (i < size1) {
			res[p] = a[i];
			i++;
			p++;
		}


		while (j < size2) {
			res[p] = b[j];
			j++;
			p++;
		}

		int[] sorted = mySort(res, "");

		return sorted;
	}



	@Test
	void testMergeAndSortTwoUnsortedArray() throws Exception {
		int[] a = { 12, 3, 8, 4 };
		int[] b = { 6, 18, 12 };

		int[] mergedSortedArray = mergeAndSortTwoArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}


	@Test
	void testMergeAndSortTwoUnsortedArray_1() throws Exception {
		int[] a = { 4, 3, 8 };
		int[] b = { 6, 18, 12, 5 };

		int[] mergedSortedArray = mergeAndSortTwoArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}


	@Test
	void testMergeAndSortTwoUnsortedArray_2() throws Exception {
		int[] b = { 4, 3, 8 };
		int[] a = { 6, 18, 12, 5 };

		int[] mergedSortedArray = mergeAndSortTwoArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}



	private int[] mergeTwoSortedArraysAsSorted(int[] a, int[] b) {
		int size1 = a.length;
		int size2 = b.length;

		int[] res = new int[size1 + size2];

		int i = 0;
		int j = 0;
		int p = 0;

		while (i < size1 && j < size2) {
			if (a[i] <= b[j]) {
				res[p] = a[i];
				i++;
			} else {
				res[p] = b[j];
				j++;
			}
			p++;
		}

		while (i < size1) {
			res[p] = a[i];
			i++;
			p++;
		}

		while (j < size2) {
			res[p] = b[j];
			j++;
			p++;
		}

		return res;
	}



	@Test
	void testMergeTwoSortedArraysAsSorted() throws Exception {
		int[] b = { 1, 3, 4, 5 };
		int[] a = { 2, 4, 6 };

		int[] mergedSortedArray = mergeTwoSortedArraysAsSorted(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}


	@Test
	void testMergeTwoSortedArraysAsSorted_1() throws Exception {
		int[] a = { 5, 8, 9 };
		int[] b = { 4, 7, 8 };

		int[] mergedSortedArray = mergeTwoSortedArraysAsSorted(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}



	private int[] mergeTwoSortedArraysAsSortedInBiggerArray(int[] a, int[] b) {
		int size1 = a.length;
		int size2 = b.length;

		int[] biggerArray = null;
		int[] smallerArray = null;

		int i = 0;
		int j = 0;

		int actualValsOccupied = 0;
		int totalIdx = 0;

		if (size1 > size2) {
			for (int k = 0; k < size1; k++) {
				if (a[k] != -1) {
					actualValsOccupied++;
				} else {
					break;
				}
			}

			biggerArray = a;
			smallerArray = b;

			totalIdx = size1 - 1;
			j = size2 - 1;
		} else {
			for (int k = 0; k < size2; k++) {
				if (b[k] != -1) {
					actualValsOccupied++;
				} else {
					break;
				}
			}

			biggerArray = b;
			smallerArray = a;

			totalIdx = size2 - 1;
			j = size1 - 1;
		}

		i = actualValsOccupied - 1;

		while (i >= 0 && j >= 0) {
			if (biggerArray[i] > smallerArray[j]) {
				biggerArray[totalIdx] = biggerArray[i];
				i--;
			} else {
				biggerArray[totalIdx] = smallerArray[j];
				j--;
			}
			totalIdx--;
		}

		return biggerArray;
	}



	@Test
	void testMergeTwoSortedArraysAsSortedInBiggerArray() throws Exception {
		int a[] = { 10, 12, 13, 14, 18, -1, -1, -1, -1, -1 };
		int b[] = { 16, 17, 19, 20, 22 };

		int[] mergedSortedArray = mergeTwoSortedArraysAsSortedInBiggerArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}

	@Test
	void testMergeTwoSortedArraysAsSortedInBiggerArray_1() throws Exception {
		int b[] = { 10, 12, 13, 14, 18, -1, -1, -1, -1, -1 };
		int a[] = { 16, 17, 19, 20, 22 };

		int[] mergedSortedArray = mergeTwoSortedArraysAsSortedInBiggerArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}

	@Test
	void testMergeTwoSortedArraysAsSortedInBiggerArray_2() throws Exception {
		int a[] = { 1, 2, 3, 5, -1, -1, -1, -1, -1 };
		int b[] = { 16, 17, 19, 20, 22 };

		int[] mergedSortedArray = mergeTwoSortedArraysAsSortedInBiggerArray(a, b);

		for (int i : mergedSortedArray) {
			System.out.print(i + ",");
		}
	}



	private int[] rotateArrayBy(int[] a, int rotateTimes) {
		while (rotateTimes > 0) {
			int i = 0;
			int first = a[0];
			while (i < a.length) {
				if (i == a.length - 1) {
					a[i] = first;
				} else {
					a[i] = a[i + 1];
				}

				i++;
			}

			rotateTimes--;
		}

		return a;
	}



	@Test
	void testArrayRotation() throws Exception {
		int a[] = { 1, 2, 3, 4, 5, 6, 7 };

		int[] rotatedArray = rotateArrayBy(a, 2);

		for (int i : rotatedArray) {
			System.out.print(i + ",");
		}
	}



	private int timesRightRotated(int[] a) {

		int l = 0;
		int h = a.length - 1;

		int rotations = _binaryToFindTimesOfRightRotation(a, l, h);
		return rotations;
	}



	private int _binaryToFindTimesOfRightRotation(int[] a, int l, int h) {

		if (!(l < h)) {
			return 0;
		}

		int mid = (h + l) / 2;

		if (mid == 0) {
			return 0;
		}

		if (a[mid] < a[mid - 1]) {
			return mid;
		}

		if (a[mid + 1] < a[mid]) {
			return mid + 1;
		}

		if (a[h] > a[mid]) {
			return _binaryToFindTimesOfRightRotation(a, l, mid);
		} else {
			return _binaryToFindTimesOfRightRotation(a, mid + 1, h);
		}
	}



	@Test
	void testFindNumberOfTimesRoatedRightRotated() throws Exception {
		int a[] = { 6, 7, 1, 2, 3, 4, 5 };

		int timesRotated = timesRightRotated(a);
		assertEquals(2, timesRotated);
	}



	@Test
	void testFindNumberOfTimesRoatedRightRotated_1() throws Exception {
		int a[] = { 1, 2, 3, 4, 5 };

		int timesRotated = timesRightRotated(a);
		assertEquals(0, timesRotated);
	}




	private int timesLeftRotated(int[] a) {
		int l = 0;
		int h = a.length - 1;

		return _binaryToSearchLeftRotation(a, l, h);
	}



	private int _binaryToSearchLeftRotation(int[] a, int l, int h) {
		int mid = (h + l) / 2;

		//		if (a[mid] < a[mid + 1]) {
		//			return a.length - mid + 1;
		//		}

		if (mid == 0) {
			return 0;
		}
		if (a[mid] < a[mid - 1]) {
			return a.length - mid - 1 + 1;
		}

		if (a[h] < a[mid]) {
			return _binaryToSearchLeftRotation(a, mid + 1, h);
			//check right;
		} else {
			return _binaryToSearchLeftRotation(a, l, mid);
		}
	}


	@Test
	void testFindNumberOfTimesRoatedLeftRotated() throws Exception {
		int a[] = { 3, 4, 5, 6, 7, 1, 2 };

		int timesRotated = timesLeftRotated(a);
		assertEquals(2, timesRotated);
	}


	@Test
	void testFindNumberOfTimesRoatedLeftRotated_1() throws Exception {
		int a[] = { 1, 2, 4, 6, 8 };

		int[] rotatedArray = rotateArrayBy(a, 3);

		int timesRotated = timesLeftRotated(rotatedArray);
		assertEquals(3, timesRotated);
	}


	@Test
	void testFindNumberOfTimesRoatedLeftRotated_2() throws Exception {
		int a[] = { 1, 2, 4, 6, 8 };

		int[] rotatedArray = rotateArrayBy(a, 4);

		int timesRotated = timesLeftRotated(rotatedArray);
		assertEquals(4, timesRotated);
	}

	@Test
	void testFindNumberOfTimesRoatedLeftRotated_3() throws Exception {
		int a[] = { 1, 2, 3, 4 };

		int[] rotatedArray = rotateArrayBy(a, 0);

		int timesRotated = timesLeftRotated(rotatedArray);
		assertEquals(0, timesRotated);
	}



	private int findMinValInARotatedArray(int[] a) {
		int l = 0;
		int h = a.length - 1;

		return _binrayToFindMinValInArray(a, l, h);

	}



	private int _binrayToFindMinValInArray(int[] a, int l, int h) {
		if (!(l < h)) {
			return a[0];
		}

		int mid = (h + l) / 2;

		if (a[mid] < a[mid - 1]) {
			return a[mid];
		}

		if (a[mid + 1] < a[mid]) {
			return a[mid + 1];
		}


		if (a[h] > a[mid]) {
			return _binrayToFindMinValInArray(a, l, mid);
		} else {
			return _binrayToFindMinValInArray(a, mid + 1, h);
		}
	}



	@Test
	void testFindMinimumValuesFromRotatedArray() throws Exception {
		int a[] = { -20, 1, 3, 4 };

		int[] rotatedArray = rotateArrayBy(a, 3);

		int minVal = findMinValInARotatedArray(rotatedArray);
		assertEquals(-20, minVal);
	}



	@Test
	void testFindMinimumValuesFromRotatedArray_1() throws Exception {
		int a[] = { 3, 4, 5, 6, 7, 1, 2 };

		int minVal = findMinValInARotatedArray(a);
		assertEquals(1, minVal);
	}



	private int[] rearrangeAsAi(int[] a) {
		Arrays.sort(a);

		int[] res = new int[a.length];

		int p = 0;
		while (p < a.length) {
			int search = Arrays.binarySearch(a, p);
			if (search != 0 && search >= 0) {
				res[p] = a[search];
			} else {
				res[p] = -1;
			}
			p++;
		}

		return res;
	}


	@Test
	void testRearrangeValuesAsAi() throws Exception {
		int[] a = { -1, -1, 6, 1, 9, 3, 2, -1, 4, -1 };

		int[] updatedArr = rearrangeAsAi(a);

		for (int i : updatedArr) {
			System.out.print(i + ", ");
		}
	}


	private int[] sendValueBackOfArray(int[] a, int valToSendBack) {
		int size = a.length;

		int i = 0;
		int j = 0;

		while (j < size) {
			if (a[j] != valToSendBack) {
				a[i] = a[j];
				i++;
			}
			j++;
		}

		while (i < size) {
			a[i] = valToSendBack;
			i++;
		}

		return a;
	}


	@Test
	void testSendAValueToEndOfArray() throws Exception {
		int[] a = { 1, 2, 0, 4, 6, 0, 0 };

		int[] updatedArr = sendValueBackOfArray(a, 0);

		for (int i : updatedArr) {
			System.out.print(i + ", ");
		}
	}


	@Test
	void testSendAValueToEndOfArray_1() throws Exception {
		int[] a = { 1, 2, 2, 4, 6, 0, 0 };

		int[] updatedArr = sendValueBackOfArray(a, 2);

		for (int i : updatedArr) {
			System.out.print(i + ", ");
		}
	}



	private int kthSmallestElement(int[] a, int kthVal) {

		Set<Integer> set = new TreeSet<>();

		for (int i : a) {
			set.add(i);
		}

		int p = 0;
		for (int i : set) {
			if (p == kthVal - 1) {
				return i;
			}
			p++;
		}

		return -1;
	}



	@Test
	void testGetKthSmallestElement() throws Exception {
		int[] a = { 7, 10, 4, 3, 20, 15 };

		int value = kthSmallestElement(a, 3);

		assertEquals(7, value);
	}
}
