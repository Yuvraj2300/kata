package com.problems.crackcode.kata.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import org.junit.jupiter.api.Test;

import com.problems.crackcode.kata.exceptions.KataException;
import com.structures.kata.TreeNode;

public class TreeApi {
	private TreeNode convertArrayToTree(int[] values) {
		int i = 0;
		TreeNode tree = _setArrayToTree(values, i);

		return tree;
	}



	private TreeNode _setArrayToTree(int[] values, int i) {
		TreeNode root = null;
		if (i < values.length) {
			root = new TreeNode(values[i]);

			root.left = _setArrayToTree(values, 2 * i + 1);
			root.right = _setArrayToTree(values, 2 * i + 2);
		}

		return root;
	}



	public TreeNode insertLevelOrder(int[] arr, int i) {
		TreeNode root = null;
		// Base case for recursion
		if (i < arr.length) {
			root = new TreeNode(arr[i]);

			// insert left child
			root.left = insertLevelOrder(arr, 2 * i + 1);

			// insert right child
			root.right = insertLevelOrder(arr, 2 * i + 2);
		}
		return root;
	}


	@Test
	void testConvertArrayToTree() throws Exception {
		int[] values = { 2, 5, 3, 2, 6 };
		Arrays.sort(values);
		TreeNode root = convertArrayToTree(values);
		printInOrder(root);
		System.out.println();
		TreeNode root2 = insertLevelOrder(values, 0);
		printInOrder(root2);
	}



	public void printInOrder(TreeNode root) {
		if (null == root) {
			return;
		}
		printInOrder(root.left);
		System.out.print(root.val + " ");
		printInOrder(root.right);
	}



	public void printPreOrder(TreeNode root) {
		if (null == root) {
			return;
		}

		System.out.println(root.val);
		printPreOrder(root.left);
		printPreOrder(root.right);
	}



	public void printPostOrder(TreeNode root) {
		if (null == root) {
			return;
		}

		printPreOrder(root.left);
		printPreOrder(root.right);
		System.out.println(root.val);
	}


	public int getHeightOfTheTree(TreeNode root) {
		if (null == root) {
			return 0;
		}

		int leftHeight = 1 + getHeightOfTheTree(root.left);
		int rightHeight = 1 + getHeightOfTheTree(root.right);

		return Math.max(leftHeight, rightHeight);
	}


	@Test
	void testGetHeightOfTree() throws Exception {
		int[] values = { 2, 5, 3, 2, 6 };
		Arrays.sort(values);
		TreeNode root = convertArrayToTree(values);
		printInOrder(root);
		int height = getHeightOfTheTree(root);
		System.out.println();
		System.out.println(height);
	}


	class NodeByLevel {
		int val;
		int level;
	}



	public Map<Integer, List<Integer>> getNodesByLevel(TreeNode root) {
		Map<Integer, List<Integer>> mapOfLevelAndNodes = new HashMap<>();

		Queue<TreeNode> nodesQueue = new LinkedList<>();
		Queue<Integer> levelsQueue = new LinkedList<>();

		List<Integer> listOfNeighborNodes;

		nodesQueue.add(root);
		levelsQueue.add(0);

		while (!nodesQueue.isEmpty()) {
			listOfNeighborNodes = new ArrayList<Integer>();

			TreeNode currNode = nodesQueue.poll();
			Integer level = levelsQueue.poll();

			if (mapOfLevelAndNodes.containsKey(level)) {
				List<Integer> listInMap = mapOfLevelAndNodes.get(level);
				listInMap.add(currNode.val);
				mapOfLevelAndNodes.put(level, listInMap);
			} else {
				List<Integer> listInMap = new ArrayList<>();
				listInMap.add(currNode.val);
				mapOfLevelAndNodes.put(level, listInMap);
			}


			if (currNode.left != null) {
				nodesQueue.add(currNode.left);
				listOfNeighborNodes.add(currNode.left.val);
			}

			if (currNode.right != null) {
				nodesQueue.add(currNode.right);
				listOfNeighborNodes.add(currNode.right.val);
			}

			for (Integer nodeInteger : listOfNeighborNodes) {
				levelsQueue.add(level + 1);
			}
		}

		return mapOfLevelAndNodes;
	}



	@Test
	void testLevelOrderTree() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		Map<Integer, List<Integer>> nodesByLevel = getNodesByLevel(root);
		System.out.println(nodesByLevel);
	}


	@Test
	void testLevelOrderTree_1() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);

		root.right = new TreeNode(3);
		Map<Integer, List<Integer>> nodesByLevel = getNodesByLevel(root);
		System.out.println(nodesByLevel);
	}



	public Map<Integer, List<Integer>> getNodesForTopView(TreeNode root) {
		if (root == null) {
			throw new KataException("some thing bad happened");
		}

		Map<Integer, List<Integer>> mapOfHorizontalDistanceAndNodes = new HashMap<>();

		Queue<TreeNode> nodeQueue = new LinkedList<TreeNode>();
		Queue<Integer> levelQueue = new LinkedList<Integer>();

		List<Integer> nextValuesAtSameLevel;

		nodeQueue.add(root);
		levelQueue.add(0);

		while (!nodeQueue.isEmpty()) {
			nextValuesAtSameLevel = new ArrayList<>();

			TreeNode currNode = nodeQueue.poll();
			Integer level = levelQueue.poll();

			if (mapOfHorizontalDistanceAndNodes.containsKey(level)) {
				List<Integer> listInMap = mapOfHorizontalDistanceAndNodes.get(level);
				listInMap.add(currNode.val);
				mapOfHorizontalDistanceAndNodes.put(level, listInMap);
			} else {
				List<Integer> listInMap = new ArrayList<>();
				listInMap.add(currNode.val);
				mapOfHorizontalDistanceAndNodes.put(level, listInMap);
			}

			if (currNode.left != null) {
				nodeQueue.add(currNode.left);
				levelQueue.add(level - 1);
				nextValuesAtSameLevel.add(level - 1);
			}

			if (currNode.right != null) {
				nodeQueue.add(currNode.right);
				levelQueue.add(level + 1);
				nextValuesAtSameLevel.add(level + 1);
			}

		}



		return mapOfHorizontalDistanceAndNodes;
	}





	@Test
	void testPrintTopView() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		Map<Integer, List<Integer>> nodesByLevel = getNodesForTopView(root);
		System.out.println(nodesByLevel);
	}



	@Test
	void testPrintTopView_1() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);

		root.right = new TreeNode(3);
		Map<Integer, List<Integer>> nodesByLevel = getNodesForTopView(root);
		System.out.println(nodesByLevel);
	}




}
