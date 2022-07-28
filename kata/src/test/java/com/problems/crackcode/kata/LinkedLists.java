package com.problems.crackcode.kata;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.structures.kata.ListNode;

public class LinkedLists {

	/**
	 * @author yuvraj1.sharma
	 *
	 *         Remove Dups! Write code to remove duplicates from an unsorted linked
	 *         list. FOLLOW UP How would you solve this problem if a temporary
	 *         buffer is not allowed?
	 */
	public ListNode removeDups(ListNode linkedList) {
		Set<Integer> setOfValues = new HashSet<>();

		ListNode copyToIterate = linkedList;
		ListNode previous = null;

		while (copyToIterate != null) {
			if (setOfValues.contains(copyToIterate.val)) {
				previous.next = copyToIterate.next;
			} else {
				setOfValues.add(copyToIterate.val);
				previous = copyToIterate;
			}

			copyToIterate = copyToIterate.next;
		}
		return linkedList;
	}



	@Test
	void testRemoveDups() throws Exception {

		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(15, 4, 12, 15, 2);

		ListNode updatedList = removeDups(linkedList);
		System.out.println(updatedList);
	}

	@Test
	void testRemoveDups_1() throws Exception {

		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(4, 4, 12, 15, 2);

		ListNode updatedList = removeDups(linkedList);
		System.out.println(updatedList);
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 *         Return Kth to Last: Implement an algorithm to find the kth to last
	 *         element of a singly linked list.
	 * 
	 */
	public ListNode removeKthFromLast(ListNode linkedList, int postitionFromEnd) {
		ListNode copyToIterate = linkedList;
		ListNode previous = null;


		int lengthOfLinkedList = 0;

		while (null != copyToIterate) {
			lengthOfLinkedList++;

			copyToIterate = copyToIterate.next;
		}

		int posOfElementToRemove = lengthOfLinkedList - postitionFromEnd;
		int counter = 0;

		copyToIterate = linkedList;

		while (null != copyToIterate) {
			if (counter == posOfElementToRemove) {
				previous.next = copyToIterate.next;
			} else {
				counter++;
			}

			previous = copyToIterate;
			copyToIterate = copyToIterate.next;
		}

		return linkedList;
	}



	@Test
	void testRemoveKthFromLast() throws Exception {
		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(4, 4, 12, 15, 2);

		ListNode updatedList = removeKthFromLast(linkedList, 2);
		System.out.println(updatedList);
	}


	/**
	 * @author yuvraj1.sharma
	 *
	 *         Delete Middle Node: Implement an algorithm to delete a node in the
	 *         middle (i.e., any node but the first and last node, not necessarily
	 *         the exact middle) of a singly linked list, given only access to that
	 *         node. EXAMPLE lnput:the node c from the linked list a->b->c->d->e->f
	 *         Result: nothing is returned, but the new linked list looks like a
	 *         ->b->d->e->f
	 * @throws Exception
	 *
	 *
	 */
	public void removeGivenNode(ListNode givenNode) throws Exception {
		if (Objects.isNull(givenNode) || Objects.isNull(givenNode.next)) {
			throw new Exception("Please provide a proper node");
		}

		givenNode.val = givenNode.next.val;
		givenNode.next = givenNode.next.next;
	}



	@Test
	void testRemoveGivenNode() throws Exception {
		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(4, 4, 12, 15, 2);

		removeGivenNode(linkedList.next.next);

		System.out.println(linkedList);
	}



	@Test
	void testRemoveGivenNode_1() throws Exception {
		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(4, 4, 12, 15, 2);

		assertThatThrownBy(() -> removeGivenNode(null)).isInstanceOf(Exception.class);

		System.out.println(linkedList);
	}


	public ListNode partitionTheList(ListNode listNode, int partitionOffset) {
		ListNode linkedList = getALinkedListWithFiveNodesForGivenValues(4, 4, 12, 15, 2);
		ListNode copyToIterate = linkedList;

		ListNode leftPartition = null;
		ListNode leftPartitionStart = null;
		ListNode leftPartitionEnd = null;

		ListNode rightPartition = null;
		ListNode rightPartitionStart = null;
		ListNode rightPartitionEnd = null;

		while (null != copyToIterate) {
			if (partitionOffset < copyToIterate.val) {
				if (Objects.isNull(leftPartition)) {
					leftPartitionStart = copyToIterate;
					leftPartitionEnd = leftPartitionStart;
				} else {
					leftPartitionEnd = copyToIterate;
					leftPartition.val = copyToIterate.val;
					leftPartition.next = copyToIterate.next;
				}

			} else if (partitionOffset >= copyToIterate.val) {
				if (Objects.isNull(rightPartition)) {
					rightPartitionStart = copyToIterate;
					rightPartitionEnd = rightPartitionStart;
				} else {
					rightPartitionEnd = copyToIterate;
					rightPartition.val = copyToIterate.val;
					rightPartition.next = copyToIterate.next;
				}

			}

			leftPartition = leftPartition.next;
			rightPartition = rightPartition.next;

			copyToIterate = copyToIterate.next;
		}

		//merge

		leftPartition.next = rightPartition;


		return null;
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 *         Sum Lists: You have two numbers represented by a linked list,where
	 *         each node contains a single digit. The digits are stored in reverse
	 *         order,such that the 1's digit is at the head of the list. Write a
	 *         function that adds the two numbers and returns the sum as a linked
	 *         list.
	 *
	 *         EXAMPLE <br>
	 *         Input: (7-> 1 -> 6) + (5 -> 9 -> 2).That is,617 + 295. Output: 2 -> 1
	 *         -> 9.That is,912. <br>
	 * 
	 * 
	 * 
	 * 
	 *         FOLLOW UP <br>
	 *         Suppose the digits are stored in forward order. Repeat the above
	 *         problem. Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
	 *         Output: 9 -> 1 -> 2.That is, 912.
	 * 
	 * 
	 */
	public ListNode sumOfLists(ListNode list1, ListNode list2) {
		return _processSum(list1, list2, 0);
	}



	private ListNode _processSum(ListNode list1, ListNode list2, int carry) {
		ListNode newNode = new ListNode();

		if (list1 == null && list2 == null) {
			return null;
		}


		int carryValueToSend = 0;
		int addedValue = 0;

		if (null != list1) {
			addedValue += list1.val;
		}

		if (null != list2) {
			addedValue += list2.val;
		}

		newNode.val = addedValue % 10;

		newNode.val += carry;

		if (addedValue > 9) {
			carryValueToSend = addedValue / 10;
		}

		newNode.next = _processSum(list1.next, list2.next, carryValueToSend);

		return newNode;
	}

	@Test
	void testLinkedListSums_2() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(7, 1, 6);
		ListNode linkedList2 = getALinkedListWithThreeNodesForGivenValues(5, 9, 2);

		ListNode sumLinkedList = sumOfLists(linkedList1, linkedList2);
		System.out.println(sumLinkedList);
	}

	@Test
	void testLinkedListSums_1() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(4, 8, 9);
		ListNode linkedList2 = getALinkedListWithThreeNodesForGivenValues(6, 4, 2);

		ListNode sumLinkedList = sumOfLists(linkedList1, linkedList2);
		System.out.println(sumLinkedList);
	}


	@Test
	void testLinkedListSums() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(7, 1, 6);
		ListNode linkedList2 = getALinkedListWithThreeNodesForGivenValues(5, 9, 2);

		ListNode sumLinkedList = sumOfLists(linkedList1, linkedList2);
		System.out.println(sumLinkedList);
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 *         FOLLOW UP <br>
	 *         Suppose the digits are stored in forward order. Repeat the above
	 *         problem. Input: (6 -> 1 -> 7) + (2 -> 9 -> 5).That is,617 + 295.
	 *         Output: 9 -> 1 -> 2.That is, 912.
	 * 
	 * 
	 */
	public ListNode sumOfLists_ForwardOrder(ListNode list1, ListNode list2) {
		return null;
	}

	@Test
	void testLinkedListSumsForwardOrder() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(6, 1, 7);
		ListNode linkedList2 = getALinkedListWithThreeNodesForGivenValues(2, 9, 5);

		ListNode sumLinkedList = sumOfLists(linkedList1, linkedList2);
		System.out.println(sumLinkedList);
	}




	public boolean palindromeLinkedList(ListNode listToCheck) {
		int iterationCap = 0;

		return _checkPalindromLinkedList(listToCheck, iterationCap);
	}



	private boolean _checkPalindromLinkedList(ListNode listToCheck, int iterationCap) {
		ListNode head = listToCheck;
		int length = _getLengthOfTheLinkedList(listToCheck);
		ListNode last = _getLastNodeOfLinkedList(listToCheck, length, iterationCap);




		while (iterationCap <= length / 2) {
			if (head.val != last.val) {
				return false;
			} else {
				iterationCap++;
				return _checkPalindromLinkedList(head.next, iterationCap);
			}
		}

		return true;
	}



	private ListNode _getLastNodeOfLinkedList(ListNode listToCheck, int size, int valueFromEnd) {
		int cap = size - valueFromEnd;
		int check = 0;
		while (listToCheck.next != null) {
			if (++check != cap) {
				//				check++;
				listToCheck = listToCheck.next;
			} else {
				return listToCheck;
			}
		}

		return listToCheck;
	}



	private int _getLengthOfTheLinkedList(ListNode listToCheck) {
		int size = 0;
		while (listToCheck != null) {
			size++;
			listToCheck = listToCheck.next;
		}
		return size;
	}



	@Test
	void testLinkedListPalindrome() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(1, 2, 1);

		assertTrue(palindromeLinkedList(linkedList1));
	}


	@Test
	void testLinkedListPalindrome_1() throws Exception {
		ListNode linkedList1 = getALinkedListWithThreeNodesForGivenValues(1, 2, 2);

		assertFalse(palindromeLinkedList(linkedList1));
	}

	@Test
	void testLinkedListPalindrome_3() throws Exception {
		ListNode linkedList1 = getALinkedListWithFiveNodesForGivenValues(1, 1, 2, 1, 1);

		assertTrue(palindromeLinkedList(linkedList1));
	}


	@Test
	void testLinkedListPalindrome_4() throws Exception {
		ListNode linkedList1 = getALinkedListWithFourNodesForGivenValues(2, 3, 3, 2);

		assertTrue(palindromeLinkedList(linkedList1));
	}

	@Test
	void testLinkedListPalindrome_5() throws Exception {
		ListNode linkedList1 = getALinkedListWithFourNodesForGivenValues(2, 3, 1, 2);

		assertFalse(palindromeLinkedList(linkedList1));
	}



	public ListNode getStartingOfTheLoopInLinkedList(ListNode linkedList) throws Exception {
		Map<ListNode, Integer> mapOfNodeAndOccurence = new HashMap<>();

		ListNode refToIterate = linkedList;

		while (refToIterate.next != null) {
			mapOfNodeAndOccurence.put(linkedList, mapOfNodeAndOccurence.getOrDefault(linkedList, 1));

			if (mapOfNodeAndOccurence.containsKey(refToIterate)) {
				return refToIterate;
			}

			refToIterate = refToIterate.next;
		}

		throw new Exception("There was no loop in the linkedlist");
	}



	private ListNode getALinkedListWithThreeNodesForGivenValues(int val1, int val2, int val3) {
		ListNode node1 = new ListNode(val1);
		ListNode node2 = new ListNode(val2);
		ListNode node3 = new ListNode(val3);

		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(null);

		return node1;
	}


	private ListNode getALinkedListWithFourNodesForGivenValues(int val1, int val2, int val3, int val4) {
		ListNode node1 = new ListNode(val1);
		ListNode node2 = new ListNode(val2);
		ListNode node3 = new ListNode(val3);
		ListNode node4 = new ListNode(val4);

		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(null);

		return node1;
	}


	private ListNode getALinkedListWithFiveNodesForGivenValues(int val1, int val2, int val3, int val4, int val5) {
		ListNode node1 = new ListNode(val1);
		ListNode node2 = new ListNode(val2);
		ListNode node3 = new ListNode(val3);
		ListNode node4 = new ListNode(val4);
		ListNode node5 = new ListNode(val5);

		node1.setNext(node2);
		node2.setNext(node3);
		node3.setNext(node4);
		node4.setNext(node5);
		node5.setNext(null);

		return node1;
	}

}
