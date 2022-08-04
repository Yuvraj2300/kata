package com.problems.crackcode.kata;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import javax.swing.text.StyleContext.SmallAttributeSet;

import org.junit.jupiter.api.Test;

import com.structures.kata.TreeNode;

public class BSTOps {


	public TreeNode createABinaryTreeFromArray(int[] array) {
		TreeNode tree = new TreeNode();
		Arrays.sort(array);

		int start = 0;
		int end = array.length - 1;

		return _createBST(array, start, end);
	}



	private TreeNode _createBST(int[] arr, int start, int end) {
		int mid = (end + start) / 2;

		if (end < start) {
			return null;
		}

		TreeNode node = new TreeNode(arr[mid]);
		node.left = _createBST(arr, start, mid - 1);
		node.right = _createBST(arr, mid + 1, end);


		return node;
	}



	public TreeNode searching(TreeNode node, int valToSearch) {
		if (null == node) {
			return null;
		}

		if (valToSearch < node.val) {
			return searching(node.left, valToSearch);
		} else if (valToSearch > node.val) {
			return searching(node.right, valToSearch);
		}

		if (valToSearch == node.val) {
			return node;
		} else {
			return null;
		}
	}



	@Test
	void testSearchTee() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = searching(tree, 12);

		assertNotNull(result);

		if (null != result) {
			assertEquals(12, result.val);
		}

	}


	@Test
	void testSearchTee_1() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = searching(tree, 100);

		assertNull(result);
	}



	public TreeNode insertANode(TreeNode tree, int valToAdd) {
		if (null == tree) {
			tree = new TreeNode(valToAdd);
			tree.left = null;
			tree.right = null;

			return tree;
		}

		if (valToAdd > tree.val) {
			tree.right = insertANode(tree.right, valToAdd);
		} else {
			tree.left = insertANode(tree.left, valToAdd);
		}

		return tree;
	}


	@Test
	void testinsertANode() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = insertANode(tree, 100);


		TreeNode searchHit = searching(result, 100);

		assertNotNull(searchHit);

		assertEquals(100, searchHit.val);
	}


	@Test
	void testinsertANode_1() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = insertANode(tree, 2);

		TreeNode searchHit = searching(result, 2);

		assertNotNull(searchHit);

		assertEquals(2, searchHit.val);
	}



	public TreeNode deleteANode(TreeNode tree, int valToDelete) {
		if (null == tree) {
			return null;
		} else if (valToDelete < tree.val) {
			tree.left = deleteANode(tree.left, valToDelete);
		} else if (valToDelete > tree.val) {
			tree.right = deleteANode(tree.right, valToDelete);
		} else if (null != tree.left && null != tree.right) {
			TreeNode successor = findLargestInOrderSuccessor(tree.left);
			tree.val = successor.val;
			deleteANode(tree.left, successor.val);
		} else {
			if (null == tree.left && null == tree.right) {
				tree = null;
			} else if (null != tree.left) {
				tree = tree.left;
			} else {
				tree = tree.right;
			}
		}

		return tree;
	}


	private TreeNode findLargestInOrderSuccessor(TreeNode tree) {
		if (tree == null || tree.right == null) {
			return tree;
		}

		return findLargestInOrderSuccessor(tree.right);
	}



	@Test
	void testDeleteANode() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = deleteANode(tree, 78);

		assertNotNull(result);
		System.out.println(result);

		TreeNode searchHit = searching(result, 78);

		assertNull(searchHit);
	}


	@Test
	void testDeleteANode_1() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = deleteANode(tree, 45);

		assertNotNull(result);
		System.out.println(result);

		TreeNode searchHit = searching(result, 45);

		assertNull(searchHit);
	}


	@Test
	void testDeleteANode_2() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		TreeNode result = deleteANode(tree, 12);

		assertNotNull(result);
		System.out.println(result);

		TreeNode searchHit = searching(result, 12);

		assertNull(searchHit);
	}


	public int getHeightOfTree(TreeNode tree) {
		if (null == tree) {
			return 0;
		}


		int leftHt = getHeightOfTree(tree.left);
		int rightHt = getHeightOfTree(tree.right);

		return leftHt < rightHt ? rightHt + 1 : leftHt + 1;
	}


	@Test
	void testHeightOfTree() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getHeightOfTree(tree);
		System.out.println(treeHt);
	}



	@Test
	void testHeightOfTree_1() throws Exception {
		int[] arr = { 45, 56, 39 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getHeightOfTree(tree);
		System.out.println(treeHt);
	}

	@Test
	void testHeightOfTree_2() throws Exception {
		int[] arr = { 45, 39, 78, 54, 80 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getHeightOfTree(tree);
		System.out.println(treeHt);
	}


	public int getNumberOfNodes(TreeNode tree) {
		if (null == tree) {
			return 0;
		}

		int leftNodes = getHeightOfTree(tree.left);
		int rightNodes = getHeightOfTree(tree.right);
		return leftNodes + rightNodes + 1;
	}



	@Test
	void testNodesOfTree() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getNumberOfNodes(tree);
		System.out.println(treeHt);

		assertEquals(7, treeHt);
	}



	@Test
	void testNodesOfTree_1() throws Exception {
		int[] arr = { 45, 56, 39 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getNumberOfNodes(tree);
		System.out.println(treeHt);

		assertEquals(3, treeHt);
	}

	@Test
	void testNodesOfTree_2() throws Exception {
		int[] arr = { 45, 39, 78, 54, 80 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getNumberOfNodes(tree);
		System.out.println(treeHt);

		assertEquals(5, treeHt);
	}


	public int getNumberOfInternalNodes(TreeNode tree) {
		if (null == tree) {
			return 0;
		}

		if (null == tree.left && null == tree.right) {
			return 0;
		} else {
			return getNumberOfInternalNodes(tree.left) + getNumberOfInternalNodes(tree.right) + 1;

		}
	}

	@Test
	void testInternalNodes() throws Exception {
		int[] arr = { 45, 56, 39, 12, 10, 34, 32, 78, 54, 89, 67 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getNumberOfInternalNodes(tree);
		System.out.println(treeHt);

		assertEquals(7, treeHt);
	}


	@Test
	void testInternalNodes_1() throws Exception {
		int[] arr = { 45, 56, 39 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getNumberOfInternalNodes(tree);
		System.out.println(treeHt);

		assertEquals(1, treeHt);
	}


	@Test
	void testInternalNodes_2() throws Exception {
		int[] arr = { 45, 39, 78, 54, 80 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		System.out.println(tree);
		int treeHt = getNumberOfInternalNodes(tree);
		System.out.println(treeHt);

		assertEquals(3, treeHt);
	}



	public int getExternalNodes(TreeNode tree) {
		if (null == tree) {
			return 0;
		}

		if (null == tree.left || null == tree.right) {
			return 1;
		}

		return getExternalNodes(tree.left) + getExternalNodes(tree.right);
	}



	@Test
	void testExternalNodes() throws Exception {
		int[] arr = { 45, 56, 39 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int treeHt = getExternalNodes(tree);
		System.out.println(treeHt);

		assertEquals(2, treeHt);
	}


	public TreeNode createMirrorImage(TreeNode tree) {
		if (tree != null) {
			TreeNode left = createMirrorImage(tree.left);
			TreeNode right = createMirrorImage(tree.right);
			tree.left = right;
			tree.right = left;
		}
		return tree;
	}



	@Test
	void testMirrorImage() throws Exception {
		int[] arr = { 45, 56, 39 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		printInOrder(tree);
		System.out.println(tree);

		TreeNode mirrorTree = createMirrorImage(tree);
		assertNotNull(mirrorTree);

		printInOrder(mirrorTree);
		System.out.println(mirrorTree);
	}


	@Test
	void testMirrorImage_1() throws Exception {
		int[] arr = { 1, 2, 3 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		printInOrder(tree);
		System.out.println(tree);

		TreeNode mirrorTree = createMirrorImage(tree);
		assertNotNull(mirrorTree);

		printInOrder(mirrorTree);
		System.out.println(mirrorTree);
	}


	@Test
	void testMirrorImage_2() throws Exception {
		int[] arr = { 3, 2, 4, 5, 2 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		printInOrder(tree);
		System.out.println(tree);

		TreeNode mirrorTree = createMirrorImage(tree);
		assertNotNull(mirrorTree);

		printInOrder(mirrorTree);
		System.out.println(mirrorTree);
	}





	public TreeNode deleteABinaryTree(TreeNode tree) {

		if (null == tree) {
			return null;
		}

		tree.left = deleteABinaryTree(tree.left);
		tree.right = deleteABinaryTree(tree.right);

		tree = null;
		return tree;
	}


	@Test
	void testDeleteATree() throws Exception {
		int[] arr = { 1, 2, 3 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		printInOrder(tree);

		TreeNode mirrorTree = deleteABinaryTree(tree);
		assertNull(mirrorTree);

		printInOrder(mirrorTree);
	}



	@Test
	void testDeleteATree_1() throws Exception {
		int[] arr = { 3, 2, 4, 5, 2 };

		TreeNode tree = createABinaryTreeFromArray(arr);
		printInOrder(tree);

		TreeNode mirrorTree = deleteABinaryTree(tree);
		assertNull(mirrorTree);

		printInOrder(mirrorTree);
	}



	public int findSmallest(TreeNode tree) {
		if (null == tree || null == tree.left) {
			return tree.val;
		}

		return findSmallest(tree.left);
	}



	@Test
	void testFindSmallest() throws Exception {
		int[] arr = { 3, 2, 4, 5, 2 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		int samllestVal = findSmallest(tree);
		System.out.println(samllestVal);
	}


	@Test
	void testFindSmallest_1() throws Exception {
		int[] arr = { 45, 123, 5, 34, 124 };

		TreeNode tree = createABinaryTreeFromArray(arr);

		printInOrder(tree);

		int samllestVal = findSmallest(tree);

		System.out.println();
		System.out.println(samllestVal);
	}


	public void printInOrder(TreeNode tree) {
		if (tree == null) {
			return;
		}

		printInOrder(tree.left);
		System.out.print(tree.val + " ");
		printInOrder(tree.right);
	}
}
