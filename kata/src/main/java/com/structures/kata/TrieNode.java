package com.structures.kata;

public class TrieNode {
	public String val;
	public TrieNode left;
	public TrieNode right;

	public boolean wordComplete;


	TrieNode() {
	}

	TrieNode(String val) {
		this.val = val;
	}

	public TrieNode(String val, TrieNode left, TrieNode right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}

	@Override
	public String toString() {
		return "TrieNode [val=" + val + ", left=" + left + ", right=" + right + "]";
	}
}
