package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Sandbox {
	//String Compression : aabccccaaa = a2b1c4a3
	public String compress(String s) {

		int i = 0;

		StringBuilder sb = new StringBuilder();
		while (i < s.length()) {
			int count = 0;
			int windowEnd = 0;
			for (int j = i; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					count++;
				} else {
					windowEnd = j;
					break;
				}
			}

			sb.append(s.charAt(i));
			sb.append(count);
			if (windowEnd != 0) {
				i = windowEnd;
			} else {
				break;
			}

		}

		return sb.toString();
	}


	@Test
	void testCompression() throws Exception {
		String compress = compress("aabccccaaa");
		System.out.println(compress);
		assertEquals("a2b1c4a3", compress);
	}


	class TreeNode {
		private int data;
		private TreeNode left;
		private TreeNode right;



		public TreeNode() {
			super();
		}



		public TreeNode(int data, TreeNode left, TreeNode right) {
			super();
			this.data = data;
			this.left = left;
			this.right = right;
		}



		public int getData() {
			return data;
		}

		public void setData(int data) {
			this.data = data;
		}

		public TreeNode getLeft() {
			return left;
		}

		public void setLeft(TreeNode left) {
			this.left = left;
		}

		public TreeNode getRight() {
			return right;
		}

		public void setRight(TreeNode right) {
			this.right = right;
		}
	}

	class Height {
		int h;
	}

	//	1-2-4

	// 	1-2-5

	public void leftViewOfTheTree(TreeNode root) {
		//get left view



		if (root == null) {
			return;
		}

		System.out.println(root.data);

		if (root.left != null) {
			leftViewOfTheTree(root.left);
		} else if (root.left == null) {
			leftViewOfTheTree(root.right);
		}

		leftViewOfTheTree(root.right);

	}

	@Test
	void testDiameter() throws Exception {
		TreeNode root = new TreeNode();
		root.data = 1;
		root.left = null;
		root.right = null;

		leftViewOfTheTree(null);
	}



}

