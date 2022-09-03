package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

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



	private int[] mySort(int[] a) {
		int l = 0;
		int h = a.length - 1;
		_mergeSort(a, l, h);



		return a;
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
		int sorteda[] = mySort(a);

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

		mySort(a);
		mySort(b);
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

		int[] sorted = mySort(res);

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

}
