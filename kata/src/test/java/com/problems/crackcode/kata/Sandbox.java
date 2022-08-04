package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

public class Sandbox {


	/**
	 * @author yuvraj1.sharma
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	/**
	 * @author yuvraj1.sharma
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	public static List<Integer> compareTriplets(List<Integer> a, List<Integer> b) {
		// Write your code here
		BiFunction<Integer, Integer, Integer> myFunc = (Integer x, Integer y) -> {
			if (x == y) {
				return 0;
			} else if (x > y) {
				return 1;
			} else if (x < y) {
				return 2;
			} else {
				return 0;
			}
		};

		List<Integer> listToReturn = new ArrayList<>();
		listToReturn.add(0);
		listToReturn.add(0);

		Iterator<Integer> aiIterator = a.iterator();
		Iterator<Integer> bIterator = b.iterator();

		while (aiIterator.hasNext() && bIterator.hasNext()) {
			Integer result = myFunc.apply(aiIterator.next(), bIterator.next());

			if (result == 0) {
				listToReturn.set(0, listToReturn.get(0) + 1);
				listToReturn.set(1, listToReturn.get(1) + 1);
			} else if (1 == result) {
				listToReturn.set(0, listToReturn.get(0) + 1);
			} else if (2 == result) {
				listToReturn.set(1, listToReturn.get(1) + 1);
			}

		}
		return listToReturn;
	}


	@Test
	void tstCompareTriplets() throws Exception {
		List<Integer> compareTriplets = compareTriplets(List.of(3, 4, 5), List.of(5, 4, 3));

		assertNotNull(compareTriplets);
		System.out.println(compareTriplets);
	}




	public static int hackerlandRadioTransmitters(List<Integer> x, int k) {
		// Write your code here
		Collections.sort(x);

		int i = 0;
		int counter = 0;

		while (i < x.size() - 1) {
			counter += 1;
			i = i + k - 1;
		}

		return counter;
	}


	private static int _getCount(int k, List<Integer> x, int start, int end, int count) {
		if (start > end) {
			return count;
		}

		int mid = (end + start) / 2;

		if (mid + k - 1 > x.size()) {
			count += 1;
			return count;
		} else {
			count += 1;
			return _getCount(k, x, mid + k + 1, x.size() - 1, count);
		}
	}



	@Test
	void testHackerlandRadio() throws Exception {
		List<Integer> x = new ArrayList<>();
		x.add(1);
		x.add(2);
		x.add(3);
		x.add(4);
		x.add(5);
		int hackerlandRadioTransmitters = hackerlandRadioTransmitters(x, 1);
		assertEquals(2, hackerlandRadioTransmitters);
	}


	@Test
	void testHackerlandRadio_1() throws Exception {
		List<Integer> x = new ArrayList<>();
		x.add(7);
		x.add(2);
		x.add(4);
		x.add(6);
		x.add(5);
		x.add(9);
		x.add(12);
		x.add(11);

		int hackerlandRadioTransmitters = hackerlandRadioTransmitters(x, 2);
		assertEquals(3, hackerlandRadioTransmitters);
	}


	@Test
	void testHackerlandRadio_2() throws Exception {
		List<Integer> x = new ArrayList<>();
		x.add(9);
		x.add(5);
		x.add(4);
		x.add(2);
		x.add(6);
		x.add(15);
		x.add(12);

		int hackerlandRadioTransmitters = hackerlandRadioTransmitters(x, 2);
		assertEquals(3, hackerlandRadioTransmitters);
	}



	public static int camelcase(String s) {
		char[] arr = s.toCharArray();
		int wordCount = 1;
		int i = 0;
		while (i < arr.length) {
			if (arr[i] >= 65 && arr[i] <= 90) {
				wordCount++;
			}
			i++;
		}
		return wordCount;
	}

	@Test
	void testCamelCase() throws Exception {
		int words = camelcase("isOk");
		assertEquals(2, words);
	}

}
