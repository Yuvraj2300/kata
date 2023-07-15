package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BacktrackingApi {

	// @formatter:off
	/**
	 * In java Given an array nums of distinct integers, return all the possible permutations. You can return the answer
	 * in any order.
	 *
	 *
	 *
	 * Example 1:
	 *
	 * Input: nums = [1,2,3]
	 * Output: [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
	 *
	 * Example 2:
	 *
	 * Input: nums = [0,1]
	 * Output: [[0,1],[1,0]]
	 */
	// @formatter:on
	@Test
	@DisplayName("Test Get All Permutations Of Array")
	void testGetAllPermutationsOfArray() {
		List<List<Integer>> lists = possiblePermutations(new int[] { 1, 2, 3 });
		System.out.println(lists);
	}


	@Test
	@DisplayName("Test Get All Permutations Of Array")
	void testGetAllPermutationsOfArray1() {
		List<List<Integer>> lists = possiblePermutations(new int[] { 1, 2 });
		System.out.println(lists);
	}


	List<List<Integer>> possiblePermutations(int[] a) {
		List<List<Integer>> result = new ArrayList<>();

		List<Integer> temp = new ArrayList<>();
		_permuate(a, result, temp);

		return result;
	}

	private static void _permuate(int[] a, List<List<Integer>> result, List<Integer> temp) {
		if (temp.size() == a.length) {
			result.add(new ArrayList<>(temp));
		} else {
			int i = 0;
			while (i < a.length) {
				if (!temp.contains(a[i])) {
					temp.add(a[i]);
					_permuate(a, result, temp);
					temp.remove(temp.size() - 1);
				}
				i++;
			}
		}
	}


}
