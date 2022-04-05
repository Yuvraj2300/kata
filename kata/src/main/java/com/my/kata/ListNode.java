package com.my.kata;

/**
 * @author yuvrajsharma
 * @implNote Definition of a singly linked list
 */
public class ListNode {
	int val;
	ListNode next;

	ListNode() {
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

}
