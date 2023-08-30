package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TreeApi3 {

	@Test
	@DisplayName("Test Create A BST From Array")
	void testCreateABstFromArray() {
		BST tree = createABSTFromArray(new int[] { 5, 3, 7, 1, 4, 6, 8 });
		printInOrder(tree);
	}

	private void printInOrder(BST tree) {
		printInOrder(tree.root);
	}

	private void printInOrder(Node node) {
		if (null == node) {
			return;
		}
		printInOrder(node.left);
		System.out.print(node.key + ", ");
		printInOrder(node.right);
	}



	public BST createABSTFromArray(int[] a) {
		BST bst = new BST();
		for (int i = 0; i < a.length; i++) {
			insertToBST(bst, a[i]);
		}
		return bst;
	}



	private void insertToBST(BST bst, int i) {
		bst.root = insertToBST(bst.root, i);
	}

	private Node insertToBST(Node node, int x) {
		if (node == null)
			return new Node(x);
		if (x > node.key) {
			node.right = insertToBST(node.right, x);
		} else {
			node.left = insertToBST(node.left, x);
		}

		return node;
	}



	@Test
	@DisplayName("Test Find Max Depth of Tree")
	void testFindMaxDepthOfTree() {
		Tree tree = new Tree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.right.right = new Node(6);

		int maxDepth = findMaxDepth(tree);
		Assertions.assertEquals(3, maxDepth, "The max depth of the tree should be 3");

	}




	int findMaxDepth(Tree tree) {
		return maxDepthOfTree(tree.root);
	}

	private int maxDepthOfTree(Node node) {
		if (node == null) {
			return 0;
		} else {
			int maxDepthLeft = maxDepthOfTree(node.left) + 1;
			int maxDepthRight = maxDepthOfTree(node.right) + 1;

			return Math.max(maxDepthLeft, maxDepthRight);
		}
	}

}




class BST {
	Node root;
}

class Tree {
	Node root;
}

class Node {
	int key;
	Node left;
	Node right;

	public Node(int key) {
		this.key = key;
	}
}