package com.structures.kata;

/**
 * @author yuvrajsharma
 * @implNote Definition of a singly linked list
 */
public class ListNode {
	public int val;
	public ListNode next;

	public ListNode() {
	}

	public ListNode(int val) {
		super();
		this.val = val;
	}

	public ListNode(int val, ListNode next) {
		super();
		this.val = val;
		this.next = next;
	}

	public int getVal() {
		return val;
	}

	public void setVal(int val) {
		this.val = val;
	}

	public ListNode getNext() {
		return next;
	}

	public void setNext(ListNode next) {
		this.next = next;
	}

	@Override
	public String toString() {
		return "ListNode [val=" + val + ", next=" + next + "]";
	}


	public void appendToTail(int d) {
		ListNode end = new ListNode(d);
		ListNode n = this;
		while (n.next != null) {
			n = n.next;
		}
		n.next = end;
	}
}
