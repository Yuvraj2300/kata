package com.problems.crackcode.kata.apis.sorts;

import org.junit.jupiter.api.Test;

/**
 * @author yuvraj1.sharma
 * @implNote Quick sort , just like merge sort , 'splits' the array (If it is
 *           safe to say, i guess it is). The idea here is to select an index in
 *           the given array and sort the values 'around' the values of the
 *           selected index. <br>
 *           <br>
 *           The 'selected index' is generally called a PIVOT. The values before
 *           the pivot must be less than the value at the pivot index.
 *           Similarly, the values after the pivot must be greater than the
 *           value at the pivot index.<br>
 *           <br>
 *           Keep in mind that the GENERAL Order of the values pre and post the
 *           pivot value does not matter during the process of the logic. The
 *           order is 'dynamically' taken care of as the pivot index shifts. The
 *           MAIN GOAL here is to place the value on the pivot index at its
 *           correct place.<br>
 *           <br>
 *           <b>How this works :</b><br>
 *           How am I selecting the pivot values : Well, to begin with I am
 *           playing safe here by keeping the first (idx == 0) as the initial
 *           pivot. Once the calculation for next tpivot idx is invoked, the
 *           pivot moves around in the array and ends up at an appropriate
 *           location by the magnitude of the value.<br>
 *           <b>How do I decide that pivot is at it's right place ?</b><br>
 *           Well, I take two pointers apart from the pivot it self.
 *           <li>l=0</li>
 *           <li>h = arr.length-1</li> These two pointers move around based on
 *           conditions on pivot value's magnitude. And this movement stops on
 *           two conditions:
 *           <li>If values are not placed correctly</li>
 *           <li>if l == h == pivotidx</li>
 * 
 *           <br>
 *           <br>
 *           Once you have a new pivot values do the same stuff on rest of the
 *           array, i.e. : l to mid-1 & mid+1 to h while l<h. And Voila !, your
 *           values will be sorted
 * 
 */
public class QuickSort implements Sort {

	@Override
	public int[] sort(int[] a) {
		int l = 0;
		int h = a.length - 1;

		_quickSort(a, l, h);
		return a;
	}



	private void _quickSort(int[] a, int l, int h) {
		//Begin by selecting l as an initial pivot
		int pivot = l;
		if (l < h) {
			//get a pivot index. 
			pivot = _getAPivotIdx(a, pivot, l, h);
			//do the same for pivot-1
			_quickSort(a, l, pivot - 1);
			//do the same for pivot+1
			_quickSort(a, pivot + 1, h);
		}

	}



	private int _getAPivotIdx(int[] a, int pivotIdx, int l, int h) {
		while (pivotIdx != l || pivotIdx != h) {
			//start form the opposite side of the pivot
			//compare values from h to pivotIdx
			while (a[h] > a[pivotIdx]) {
				h--;
			}

			if (a[h] < a[pivotIdx]) {
				_swap(a, pivotIdx, h);
			}
			//bring the pivot index to h
			pivotIdx = h;


			//comapre values from l to pivot
			while (a[l] < a[pivotIdx]) {
				l++;
			}

			if (a[l] > a[pivotIdx]) {
				_swap(a, pivotIdx, l);

			}
			//bring the pivot index to h
			pivotIdx = l;

			//do this till pivot!=l!=h
			//means wrap this in a while condition for the same
		}
		return pivotIdx;
	}



	private void _swap(int[] a, int i, int j) {
		int temp = a[j];
		a[j] = a[i];
		a[i] = temp;
	}



	@Test
	void testSortMethod() throws Exception {
		int a[] = { 2, 43, 1, 0, 12 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}

	@Test
	void testSortMethod_1() throws Exception {
		int a[] = { 200, 100, -44, 2, 5, 1, 66, 3, 23, 5453 };
		int[] sortedArray = this.sort(a);
		for (int i : sortedArray) {
			System.out.print(i + " ");
		}
	}


}
