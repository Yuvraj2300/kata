package com.problems.crackcode.kata.apis.sorts;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * @author yuvraj1.sharma
 * 
 * @implNote This is my implementation of merge sort In merge sort the idea is
 *           to break the given array to granular pieces and then sort those
 *           granular pieces. Once Sorted then the main array can be updated
 *           with the sorted values <br>
 *           As far as time complexity is concerned , it is O(nLogn), as we are
 *           splitting the array awhile iterating over all the n elements of it
 */
public class MergeSort implements Sort {

	@Override
	public int[] sort(int[] a) {
		//split the array on the basis of a mid point

		int l = 0;
		int h = a.length - 1;

		//do this splitting till l<h
		_splitForMergeSort(a, l, h);
		return a;
	}



	private void _splitForMergeSort(int[] a, int l, int h) {
		if (l < h) {
			int mid = (h + l) / 2;
			_splitForMergeSort(a, l, mid);
			_splitForMergeSort(a, mid + 1, h);

			//now update the array with sorted values
			_updateArray(a, l, h, mid);
		}
	}



	private void _updateArray(int[] a, int l, int h, int mid) {
		// 0,1,2,3,..........lth........hth.....n-1,n
		int splitSize1 = mid - l + 1;
		int splitSize2 = h - mid;

		int[] split1 = new int[splitSize1];
		int[] split2 = new int[splitSize2];

		//fill up the splits from main aray
		for (int i = 0; i < split1.length; i++) {
			split1[i] = a[l + i];
		}

		for (int i = 0; i < split2.length; i++) {
			split2[i] = a[mid + 1 + i];
		}

		//compare and update the main array

		//iterator for split1
		int i = 0;
		//iterator for split2
		int j = 0;

		//iterator for main array
		// this needs to being a l as that would be the point you want to start editing the main array
		int p = l;

		while (i < splitSize1 && j < splitSize2) {
			if (split1[i] < split2[j]) {
				a[p] = split1[i];
				i++;
			} else {
				a[p] = split2[j];
				j++;
			}
			p++;
		}

		//fill up remaining values
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
		//should be done!
	}



	@Test
	void testSotrtMethod() throws Exception {
		int a[] = { 2, 43, 1, 0, 12 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}

	@Test
	void testSotrtMethod_1() throws Exception {
		int a[] = { 200, 100, -44, 2, 5, 1, 66, 3, 23, 5453 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}


	@Test
	void testSortMethod_2() throws Exception {
		int a[] = { 100, 3, 54, 6, -56 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}



	@Test
	void testSortMethod_3() throws Exception {
		int a[] = { 1, 2, 3, 4, 5, 6 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}


}
