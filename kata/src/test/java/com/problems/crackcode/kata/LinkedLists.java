package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
