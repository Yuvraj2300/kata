package com.my.kata;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

public class MyTests {

	int consecOnes() {
//		failing case on leetcode with this logix : [1,0,1,1,0,1]
//		List<Integer> l1 = Arrays.asList(1, 1, 1, 0, 1, 1);
		List<Integer> l1 = Arrays.asList(1, 0, 1, 1, 1, 1, 0, 1);

		int count = 0;
		int countOf1sInAFrame = 0;

		for (int i : l1) {
			if (0 != _check1s(i)) {
				countOf1sInAFrame++;
			} else {
				count = _setCount(count, countOf1sInAFrame);
				countOf1sInAFrame = 0;
			}
		}

		return count;
	}

	private int _setCount(int count, int countOf1sInAFrame) {
		if (count < countOf1sInAFrame) {
			count = countOf1sInAFrame;
		}
		return count;
	}

	private int _check1s(int i) {
		if (i != 1) {
			return 0;
		} else {
			return 1;
		}
	}

	@Test
	void testConsec1() throws Exception {
		int consecOnes = consecOnes();
		assertEquals(4, consecOnes);
	}

	private List<Integer> getDuplicateZerosList(List<Integer> listToUpdate) {
		int size = listToUpdate.size();
		int op[] = new int[size];

		for (int i = 0; i < size; i++) {
			Integer currentVal = listToUpdate.get(i);
			if (currentVal != 0) {
				op[i] = listToUpdate.get(i);
				continue;
			} else if (size == op.length) {
				break;
			} else {
				if (i + 1 < size) {
					op[i + 1] = 0;
				}
			}
		}

		return Arrays.stream(op).boxed().collect(Collectors.toList());
	}

	@Test
	void testDuplicateZeros() throws Exception {
		List<Integer> listToUpdate = Arrays.asList(1, 0, 2, 3, 0, 4, 5);
		List<Integer> newList = getDuplicateZerosList(listToUpdate);

		assertEquals(listToUpdate.size(), newList.size());

		int countOfZerosnNewList = (int) newList.stream().filter(i -> 0 == i).count();
		assertEquals(4, countOfZerosnNewList);
	}

	/**
	 * 
	 * Given an array arr of integers, check if there exists two integers N and M
	 * such that N is the double of M ( i.e. N = 2 * M).
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: arr = [10,2,5,3] Output: true Explanation: N = 10 is the double of M =
	 * 5,that is, 10 = 2 * 5.
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	void testDoubleExists() throws Exception {
		int[] arr = new int[] { 10, 2, 5, 3 };
		assertTrue(checkIfDoubleIsThere(arr));

		int[] arr2 = new int[] { 11, 2, 5, 3 };
		assertFalse(checkIfDoubleIsThere(arr2));
		// Fails
		int[] arr3 = new int[] { -2, 0, 10, -19, 4, 6, -8 };
		assertFalse(checkIfDoubleIsThere(arr3));
	}

	private boolean checkIfDoubleIsThere(int[] arr) {
		Set<Integer> arrAsSet = Arrays.stream(arr).boxed().collect(Collectors.toSet());

		boolean anyMatch = arrAsSet.stream().anyMatch((i -> (arrAsSet.contains(i * 2))));

		return anyMatch;
	}

	private int removeDups(int[] nums) {
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] != nums[count]) {
				count++;
				nums[count] = nums[i];
			}
		}

		return count + 1;
	}

	@Test
	void testDeleteFrxomSortedArray() throws Exception {
		int[] nums = new int[] { 1, 1, 2 }; // Input array
		int[] expectedNums = new int[] { 1, 2 }; // The expected answer with correct length

		int k = removeDups(nums);

		assertEquals(k, expectedNums.length);

		for (int i = 0; i < k; i++) {
			assertEquals(nums[i], expectedNums[i]);
		}
	}

	/**
	 * 
	 * Given an array of integers arr, return true if and only if it is a valid
	 * mountain array.
	 * 
	 * Recall that arr is a mountain array if and only if:
	 * 
	 * arr.length >= 3 There exists some i with 0 < i < arr.length - 1 such that:
	 * arr[0] < arr[1] < ... < arr[i - 1] < arr[i] arr[i] > arr[i + 1] > ... >
	 * arr[arr.length - 1]
	 * 
	 * 
	 * Input: arr = [2,1] Output: false
	 * 
	 * Input: arr = [3,5,5] Output: false
	 * 
	 * Input: arr = [0,3,2,1] Output: true
	 * 
	 * @throws Exception
	 * 
	 */
	@Test
	void testValidMtnArray() throws Exception {
		int[] arr1 = new int[] { 2, 1 };
		int[] arr2 = new int[] { 3, 5, 5 };
		int[] arr3 = new int[] { 0, 3, 2, 1 };
		int[] arr4 = new int[] { 1, 7, 9, 5, 4, 1, 2 };

		boolean flag = false;

		flag = checkMtnArray(arr1);
		assertFalse(flag);

		flag = checkMtnArray(arr2);
		assertFalse(flag);

		flag = checkMtnArray(arr3);
		assertTrue(flag);

		flag = checkMtnArray(arr4);
		assertFalse(flag);
	}

	private boolean checkMtnArray(int[] arr) {
		int length = arr.length;
		int ltVerifier = 0;

		if (length < 3) {
			return false;
		}

		while (ltVerifier < length && arr[ltVerifier] < arr[ltVerifier + 1]) {
			ltVerifier++;
		}

		if (ltVerifier == 0 || ltVerifier == length - 1)
			return false;

		while (ltVerifier < length && arr[ltVerifier] > arr[ltVerifier]) {
			ltVerifier++;
		}

		return ltVerifier == length - 1;
	}

	/**
	 * @throws Exception
	 * 
	 *                   Given an integer array nums, return the third distinct
	 *                   maximum number in this array. If the third maximum does not
	 *                   exist, return the maximum number.
	 * 
	 * 
	 * 
	 *                   Example 1:
	 * 
	 *                   Input: nums = [3,2,1] Output: 1 Explanation: The first
	 *                   distinct maximum is 3. The second distinct maximum is 2.
	 *                   The third distinct maximum is 1. Example 2:
	 * 
	 *                   Input: nums = [1,2] Output: 2 Explanation: The first
	 *                   distinct maximum is 2. The second distinct maximum is 1.
	 *                   The third distinct maximum does not exist, so the maximum
	 *                   (2) is returned instead.
	 * 
	 * 
	 */
	@Test
	void testThirdMaxNumber() throws Exception {
		int[] arr1 = new int[] { 3, 2, 1 };
		int[] arr2 = new int[] { 1, 2 };
		int[] arr3 = new int[] { 2, 2, 3, 1 };

		int op1 = thirdMax(arr1);
		int op2 = thirdMax(arr2);
		int op3 = thirdMax(arr3);

		assertEquals(1, op1);
		assertEquals(2, op2);
		assertEquals(1, op3);

	}

	private int thirdMax(int[] arr1) {
		List<Integer> asList = Arrays.stream(arr1).boxed().distinct().collect(Collectors.toList());
		if (asList.size() > 3) {
			return asList.get(2);
		} else {
			return asList.get(asList.size() - 1);
		}
	}

	/**
	 * 
	 * Write a function that reverses a string. The input string is given as an
	 * array of characters s.
	 * 
	 * You must do this by modifying the input array in-place with O(1) extra
	 * memory.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 
	 * Input: s = ["h","e","l","l","o"] Output: ["o","l","l","e","h"] Example 2:
	 * 
	 * Input: s = ["H","a","n","n","a","h"] Output: ["h","a","n","n","a","H"]
	 * 
	 * 
	 * @throws Exception
	 */
	@Test
	void testReverseStringRecursive() throws Exception {
		String[] arr1 = new String[] { "h", "e", "l", "l", "o" };
		String[] arr2 = new String[] { "H", "a", "n", "n", "a", "h" };

		reverseRecursive(arr1);
		reverseRecursive(arr2);

		System.out.println("########Output on arr 1 : #########");
		for (String s : arr1) {
			// op should be : o l l e h
			System.out.print(s + ", ");
		}
		System.out.println();
		System.out.println("########Output on arr 2 : #########");
		for (String s : arr2) {
			// op should be : h a n n a H
			System.out.print(s + ", ");
		}

	}

	private void reverseRecursive(String[] arr) {
		int i = 0;
		int length = arr.length;
		reverse(i, arr, length);
	}

	private void reverse(int i, String[] arr, int length) {
		int j = length - 1 - i;

		if (i >= j) {
			return;
		} else if (i < length) {
			String temp = arr[i];
			arr[i] = arr[j];
			arr[j] = temp;
			i++;
			reverse(i, arr, length);
		} else {
			return;
		}
	}

	/**
	 * 
	 * Given the head of a singly linked list, reverse the list, and return the
	 * reversed list.
	 * 
	 * 
	 * Input: head = [1,2,3,4,5] Output: [5,4,3,2,1]
	 * 
	 * Input: head = [1,2] Output: [2,1]
	 * 
	 * Input: head = [] Output: []
	 * 
	 * @throws Exception
	 */
	@Test
	void testReverseALinkedListRecursion() throws Exception {
		ListNode node5 = new ListNode(5, null);
		ListNode node4 = new ListNode(4, node5);
		ListNode node3 = new ListNode(3, node4);
		ListNode node2 = new ListNode(2, node3);
		ListNode node1 = new ListNode(1, node2);

		ListNode reversedNode = reversNodeRecursive(node1);

		assertNotNull(reversedNode);
		assertEquals(node4, reversedNode.getNext());

	}

	private ListNode reversNodeRecursive(ListNode node) {
		// TODO Auto-generated method stub
		return _reverse(node);
	}

	private ListNode _reverse(ListNode head) {
		// base case to reach the end of the list
		if (head.next == null) {
			return head;
		}

		// gives me the last element (which is to be the new one)
		ListNode newHead = _reverse(head.next);

		// original List 4->5->null
		// now to make 5->4 we go back and reset the pointers
		head.next.next = head;
		head.next = null;

		return newHead;
	}

	// @formatter:off
 

	/**
	 * You are given the root of a binary search tree (BST) and an integer val.
	 * 
	 * Find the node in the BST that the node's value equals val and return the
	 * subtree rooted with that node. If such a node does not exist, return null.
	 * 
	 * 
	 * 
	 * Example 1:
	 * 			4
	 * 			/\
	 * 		2		7
	 * 		/\
	 * 1		3
	 * 
	 * Input: root = [4,2,7,1,3], val = 2 Output: [2,1,3]
	 * 
	 * @throws Exception
	 */
	// @formatter:on

	@Test
	void testSearchBST() throws Exception {
		TreeNode leaf1 = new TreeNode(1, null, null);
		TreeNode leaf2 = new TreeNode(3, null, null);
		TreeNode node1 = new TreeNode(2, leaf1, leaf2);
		TreeNode leafNode2 = new TreeNode(7, null, null);
		TreeNode root = new TreeNode(4, node1, leafNode2);

		TreeNode op = searchBST(root, 2);

		assertNotNull(op);

		assertEquals(2, op.val);
		assertEquals(node1, op);
		assertEquals(leaf1, op.left);
		assertEquals(leaf2, op.right);

	}

	private TreeNode searchBST(TreeNode root, int val) {
		if (root.val == val) {
			return root;
		}
		while (root.left != null) {
			return searchBST(root.left, val);
		}
		while (root.right != null) {
			return searchBST(root.right, val);
		}

		return null;
	}
// @formatter:off
 

	/**
	 *Given a non-negative integer N, the task is to find the Nth row of Pascal’s Triangle. 
	 *	
	 *Note: The row index starts from 0.  
	 * 
	 *  1 
	 	1 1 
		1 2 1 
		1 3 3 1 
		1 4 6 4 1 
		
		Input: rowIndex = 3
		Output: [1,3,3,1]
		Example 2:
		
		Input: rowIndex = 0
		Output: [1]
		Example 3:
		
		Input: rowIndex = 1
		Output: [1,1]

		
	 * @throws Exception
	 */
// @formatter:on
	@Test
	void testPascalsTriangle() throws Exception {
		List<Integer> row = getRow(4);

		assertNotNull(row);
		System.out.println("Value of the list  : " + row);
	}

	private List<Integer> getRow(int rowIndex) {
		List<Integer> l = new ArrayList<>();
		l.add(1);

		if (0 == rowIndex) {
			return l;
		}

		List<Integer> adjRow = getRow(rowIndex - 1);

		for (int i = 1; i < adjRow.size(); i++) {
			int valToAdd = adjRow.get(i - 1) + adjRow.get(i);
			l.add(valToAdd);
		}

		l.add(1);
		return l;
	}

}
