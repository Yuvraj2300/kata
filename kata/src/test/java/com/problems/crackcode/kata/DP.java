package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.structures.kata.InfiniteStack;

public class DP {

	enum Steps {

		RANDOM_STEP(
				0),

		STEP_ONE(
				1),
		STEP_TWO(
				2),
		STEP_THREE(
				3);

		int val;

		Steps(int val) {
			this.val = val;

		}

		private static final List<Steps> VALUES = Collections.unmodifiableList(Arrays.asList(values()));
		private static final int SIZE = VALUES.size();
		final Random RANDOM = new Random();

		public Steps getRandomStep() {
			return VALUES.get(RANDOM.nextInt(SIZE));
		}

		//		STEP_ONE(1),

	}

	/**
	 * @author yuvraj1.sharma
	 *
	 *         A child is running up a staircase with n steps and can hop either 1
	 *         step, 2 steps, or 3 steps at a time. Implement a method to count how
	 *         many possible ways the child can run up the stairs
	 */
	int countways(int numberOfStairs) {
		if (numberOfStairs == 0) {
			return 1;
		} else if (numberOfStairs < 0) {
			return 0;
		}

		int path1 = countways(numberOfStairs - 1);
		int path2 = countways(numberOfStairs - 2);
		int path3 = countways(numberOfStairs - 3);

		return path1 + path2 + path3;
	}


	@Test
	void testCountWays() throws Exception {
		int ways = countways(5);

		assertEquals(3, ways);
	}


	Set<Point> findPath(int[][] graph) {
		Set<Point> path = new LinkedHashSet<>();
		_getPath(graph, graph.length - 1, graph[0].length - 1, path);

		return path;
	}


	private void _getPath(int[][] graph, int i, int j, Set<Point> path) {
		if (i < 0 || j < 0) {
			return;
		} else if (0 != graph[i][j]) {
			boolean isAtOrigin = i == 0 && j == 0;

			if (isAtOrigin) {
				path.add(new Point(i, j, graph[i][j]));
			} else {
				//				_getPath(graph, i - 1, j, path);
				//				_getPath(graph, i, j - 1, path);
				_getPath(graph, i - 1, j, path);
				_getPath(graph, i, j - 1, path);
				path.add(new Point(i, j, graph[i][j]));
			}
		}

	}


	@Test
	void testFindPath() throws Exception {
		//		int[][] array = { { 1, 2, 3, 4, 5 }, { 0, 0, 5, 3, 2 }, { 0, 0, 23, 41, 45 } };
		int[][] array = { { 1, 3, 0, 0, 0 }, { 0, 2, 5, 1, 2 } };

		Set<Point> path = findPath(array);

		System.out.println(path);
	}


	@Test
	void testFindPath_1() throws Exception {
		//		int[][] array = { { 1, 2, 3, 4, 5 }, { 0, 0, 5, 3, 2 }, { 0, 0, 23, 41, 45 } };
		int[][] array = { { 1, 3, 1 }, { 0, 2, 5 } };

		Set<Point> path = findPath(array);

		System.out.println(path);
	}

	public boolean magicIndex(int[] arr) {

		return _getMagicIndex(arr, arr.length - 1);
	}


	public boolean magicIndexFast(int[] arr) {

		return _getMagicIndexFast(arr, 0, arr.length - 1);
	}

	private boolean _getMagicIndex(int[] arr, int i) {

		if (i < 0) {
			return false;
		}

		if (arr[i] != i) {
			return _getMagicIndex(arr, i - 1);
		} else {
			System.out.println("Magic Index At : " + i + ", for value " + arr[i]);
			return true;
		}

	}


	private boolean _getMagicIndexFast(int[] arr, int start, int end) {

		if (end < start) {
			return false;
		}

		int mid = (end + start) / 2;

		if (mid == arr[mid]) {
			System.out.println("Magic Index At : " + mid + ", for value " + arr[mid]);
			return true;
		} else if (mid < arr[mid]) {
			_getMagicIndexFast(arr, start, mid - 1);
		} else {
			_getMagicIndexFast(arr, mid + 1, end);
		}

		return false;
	}

	@Test
	void tstMagicIndex() throws Exception {
		int[] arr = { 0, 1, 2, 3 };
		Arrays.sort(arr);
		magicIndexFast(arr);
	}


	@Test
	void tstMagicIndex_1() throws Exception {
		int[] arr = { 1, 1, 1, 1 };
		Arrays.sort(arr);
		assertTrue(magicIndexFast(arr));
	}

	public int recursiveMultiply(int a, int b) {
		int multiple = 0;

		return _multiply(a, b, multiple);
	}


	private int _multiply(int a, int b, int multiple) {
		if (b == 0) {
			return multiple;
		}

		multiple = multiple + a;

		return _multiply(a, --b, multiple);
	}


	@Test
	void testMultiply() throws Exception {
		int recursiveMultiply = recursiveMultiply(2, 5);

		assertEquals(10, recursiveMultiply);
	}


	@Test
	void testMultiply_1() throws Exception {
		int recursiveMultiply = recursiveMultiply(5, 5);

		assertEquals(25, recursiveMultiply);
	}


	public void towerOfHanoi(InfiniteStack<Integer> start, InfiniteStack<Integer> destination, InfiniteStack<Integer> buffer, int noOfDisksAtStart) {

		if (noOfDisksAtStart == 1) {
			destination.push(start.pop());
		} else {
			towerOfHanoi(start, buffer, destination, noOfDisksAtStart - 1);

			destination.push(start.pop());

			towerOfHanoi(buffer, destination, start, noOfDisksAtStart - 1);
		}

	}


	@Test
	void testTowerOfHanoi() throws Exception {
		InfiniteStack<Integer> tower1 = new InfiniteStack<Integer>(Integer.class);
		InfiniteStack<Integer> tower2 = new InfiniteStack<Integer>(Integer.class);
		InfiniteStack<Integer> tower3 = new InfiniteStack<Integer>(Integer.class);

		tower1.push(34);

		towerOfHanoi(tower1, tower3, tower2, tower1.length());
		assertEquals(34, tower3.peek());
	}


	@Test
	void testTowerOfHanoi_1() throws Exception {
		InfiniteStack<Integer> tower1 = new InfiniteStack<Integer>(Integer.class);
		InfiniteStack<Integer> tower2 = new InfiniteStack<Integer>(Integer.class);
		InfiniteStack<Integer> tower3 = new InfiniteStack<Integer>(Integer.class);

		tower1.push(34);
		tower1.push(2);
		tower1.push(1);

		towerOfHanoi(tower1, tower3, tower2, tower1.length());
		assertEquals(1, tower3.peek());
	}



	public Set<String> getAllPermutation(String s) {
		Set<String> permutations = new HashSet<>();
		_getAllPerm(s, "", permutations);
		return permutations;
	}


	private void _getAllPerm(String staticPart, String swapAble, Set<String> permutations) {
		if (staticPart.length() == 0) {
			permutations.add(swapAble);
		}

		for (int i = 0; i < staticPart.length(); i++) {
			char ch = staticPart.charAt(i);

			String str = staticPart.substring(0, i) + staticPart.substring(i + 1);

			_getAllPerm(str, swapAble + ch, permutations);
		}
	}


	@Test
	void testGetAllPermutations() throws Exception {
		String s = "ABC";

		Set<String> allPermutation = getAllPermutation(s);

		System.out.println(allPermutation);
	}


	@Test
	void testGetAllPermutations_1() throws Exception {
		String s = "ABBC";

		Set<String> allPermutation = getAllPermutation(s);

		System.out.println(allPermutation);
	}

}




class Point {


	public Point(int x, int y, int val) {
		super();
		this.x = x;
		this.y = y;
		this.val = val;
	}

	int x;
	int y;
	int val;

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[x : " + x + ", y :" + y + ", val : " + val + "]";
	}
}




