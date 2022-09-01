package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
		TreeNode node;
		int level;

		public NodeByLevel(TreeNode node, int level) {
			super();
			this.node = node;
			this.level = level;
		}


	}



	public Map<Integer, List<Integer>> getNodesByLevel(TreeNode root) {
		Map<Integer, List<Integer>> mapOfLevelAndNodes = new HashMap<>();

		Queue<NodeByLevel> nodeByLevelQueue = new LinkedList<>();

		nodeByLevelQueue.add(new NodeByLevel(root, 0));

		while (!nodeByLevelQueue.isEmpty()) {
			NodeByLevel currNodeLevelPair = nodeByLevelQueue.poll();

			int level = currNodeLevelPair.level;
			TreeNode currNode = currNodeLevelPair.node;
			int val = currNode.val;

			if (mapOfLevelAndNodes.containsKey(level)) {
				List<Integer> listInMap = mapOfLevelAndNodes.get(level);
				listInMap.add(currNode.val);
				mapOfLevelAndNodes.put(level, listInMap);
			} else {
				List<Integer> listInMap = new ArrayList<>();
				listInMap.add(val);
				mapOfLevelAndNodes.put(level, listInMap);
			}

			if (currNode.left != null) {
				nodeByLevelQueue.add(new NodeByLevel(currNode.left, level + 1));
			}
			if (currNode.right != null) {
				nodeByLevelQueue.add(new NodeByLevel(currNode.right, level + 1));
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


	@Test
	void testTopView() throws Exception {
		TreeNode root = new TreeNode(20);
		root.left = new TreeNode(8);
		root.right = new TreeNode(22);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(25);
		root.left.right.left = new TreeNode(10);
		root.left.right.right = new TreeNode(14);

		Map<Integer, List<Integer>> nodesByLevel = getNodesForTopView(root);
		System.out.println(nodesByLevel);
	}



	class StateWithDirectionFlag {
		int level;
		TreeNode node;

		public StateWithDirectionFlag(int level, TreeNode node) {
			super();
			this.level = level;
			this.node = node;
		}
	}

	public void printLeftView(TreeNode root) {
		Map<Integer, Queue<Integer>> mapOfLevelWithValsFromLeft = new HashMap<Integer, Queue<Integer>>();
		Queue<StateWithDirectionFlag> queueForLeftNodes = new LinkedList<>();

		queueForLeftNodes.add(new StateWithDirectionFlag(0, root));


		while (!queueForLeftNodes.isEmpty()) {
			StateWithDirectionFlag poll = queueForLeftNodes.poll();
			TreeNode currNode = poll.node;

			int level = poll.level;
			if (mapOfLevelWithValsFromLeft.containsKey(level)) {
				Queue<Integer> listInMap = mapOfLevelWithValsFromLeft.get(level);
				listInMap.add(currNode.val);
				mapOfLevelWithValsFromLeft.put(level, listInMap);
			} else {
				Queue<Integer> listInMap = new LinkedList<>();
				listInMap.add(currNode.val);
				mapOfLevelWithValsFromLeft.put(level, listInMap);
			}


			if (currNode.left != null) {
				queueForLeftNodes.add(new StateWithDirectionFlag(level + 1, currNode.left));
			}

			if (currNode.right != null) {
				queueForLeftNodes.add(new StateWithDirectionFlag(level + 1, currNode.right));
			}
		}
		System.out.println(mapOfLevelWithValsFromLeft);

		for (Map.Entry<Integer, Queue<Integer>> entry : mapOfLevelWithValsFromLeft.entrySet()) {
			System.out.print(entry.getValue().poll() + " ");
		}

	}


	@Test
	void testPrintLeftView() throws Exception {
		TreeNode root = new TreeNode(10);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(8);
		root.right.right = new TreeNode(15);
		root.right.left = new TreeNode(12);
		root.right.right.left = new TreeNode(14);

		printLeftView(root);

	}

	@Test
	void testPrintLeftView_1() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);

		root.right = new TreeNode(3);
		root.right.left = new TreeNode(6);
		root.right.right = new TreeNode(7);

		printLeftView(root);
	}


	@Test
	void testPrintLeftView_2() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.right = new TreeNode(4);
		root.left.right.right = new TreeNode(5);
		root.left.right.right.right = new TreeNode(6);

		root.right = new TreeNode(3);

		printLeftView(root);
	}


	class NodeDistances {
		int nodeDistanceFromRoot = 0;
		int nodeDistnaceFromCosestLeaf = 0;
		int rootDistFromCurrentLeaf = 0;
		boolean crossingNode = false;

		@Override
		public String toString() {
			return "NodeDistances [nodeDistance=" + nodeDistanceFromRoot + ", closestLeaf=" + nodeDistnaceFromCosestLeaf + "]";
		}

	}

	class LeafNodePojo {
		TreeNode node;
		boolean visited;
		int rootDistFromCurrentLeaf = 0;
		boolean crossingNode = false;



		public LeafNodePojo(TreeNode node, boolean visited) {
			super();
			this.node = node;
			this.visited = visited;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(node);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof LeafNodePojo))
				return false;
			LeafNodePojo other = (LeafNodePojo) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return Objects.equals(node, other.node);
		}



		@Override
		public String toString() {
			return "LeadNodePojo [node=" + node + ", visited=" + visited + "]";
		}



		private TreeApi getEnclosingInstance() {
			return TreeApi.this;
		}
	}

	public int getClosestLeaf(TreeNode root, TreeNode nodeToSearch) {
		int minDistance = Integer.MAX_VALUE;
		Queue<LeafNodePojo> leafNodesQueue = new LinkedList<>();

		NodeDistances nodeDistances = new NodeDistances();
		_getDistanceOfRootToNode(root, nodeToSearch, nodeDistances);

		System.out.println(nodeDistances);
		_getNodeDisatanceFromRoot(root, nodeToSearch, nodeDistances, leafNodesQueue, 0);

		while (!leafNodesQueue.isEmpty()) {
			int distance = 0;
			LeafNodePojo poll = leafNodesQueue.poll();
			if (poll.crossingNode) {
				distance = poll.rootDistFromCurrentLeaf - nodeDistances.nodeDistanceFromRoot;
			} else {
				distance = nodeDistances.nodeDistanceFromRoot + poll.rootDistFromCurrentLeaf;
			}

			if (distance < minDistance) {
				minDistance = distance;
			}

		}

		return minDistance;
	}



	private void _getNodeDisatanceFromRoot(TreeNode root, TreeNode nodeToSearch, NodeDistances nodeDistances, Queue<LeafNodePojo> leafNodesQueue, int level) {

		if (root == null) {
			return;
		}

		if (nodeToSearch == root) {
			nodeDistances.crossingNode = true;
		}


		if (_isLeaf(root)) {
			//			nodeDistances.rootDistFromCurrentLeaf++;
			LeafNodePojo currentLeafNode = new LeafNodePojo(root, true);
			currentLeafNode.rootDistFromCurrentLeaf = level;
			currentLeafNode.crossingNode = nodeDistances.crossingNode;

			if (!leafNodesQueue.contains(currentLeafNode)) {
				currentLeafNode.visited = true;
				leafNodesQueue.add(currentLeafNode);
			}

			return;

		}
		_getNodeDisatanceFromRoot(root.left, nodeToSearch, nodeDistances, leafNodesQueue, level + 1);
		_getNodeDisatanceFromRoot(root.right, nodeToSearch, nodeDistances, leafNodesQueue, level + 1);
	}



	boolean _isLeaf(TreeNode node) {
		if (node.left == null && null == node.right) {
			return true;
		}

		return false;
	}



	private void _getDistanceOfRootToNode(TreeNode root, TreeNode nodeToSearch, NodeDistances nodeDistances) {
		if (root == null) {
			return;
		}


		if (root.val == nodeToSearch.val) {
			return;
		}

		if (root.left != null && root.left.val == nodeToSearch.val) {
			nodeDistances.nodeDistanceFromRoot++;
			return;
		}


		if (root.right != null && root.right.val == nodeToSearch.val) {
			nodeDistances.nodeDistanceFromRoot++;
			return;
		}

		_getDistanceOfRootToNode(root.left, nodeToSearch, nodeDistances);
		_getDistanceOfRootToNode(root.right, nodeToSearch, nodeDistances);
	}



	@Test
	void testFindNearestLeatNode() throws Exception {
		TreeNode root = new TreeNode(10);

		TreeNode nodeToSearch = new TreeNode(13);

		root.left = new TreeNode(12);
		root.right = nodeToSearch;
		root.right.left = new TreeNode(14);

		int closestLeafValue = getClosestLeaf(root, nodeToSearch);
		assertEquals(1, closestLeafValue);
	}


	@Test
	void testFindNearestLeatNode_1() throws Exception {
		TreeNode root = new TreeNode(10);

		TreeNode nodeToSearch = new TreeNode(13);

		root.left = new TreeNode(12);
		root.right = nodeToSearch;
		root.right.left = new TreeNode(14);

		root.right.left.left = new TreeNode(21);
		root.right.left.left.left = new TreeNode(1);
		root.right.left.left.right = new TreeNode(2);

		root.right.left.right = new TreeNode(22);
		root.right.left.right.left = new TreeNode(3);
		root.right.left.right.right = new TreeNode(4);


		root.right.right = new TreeNode(15);

		root.right.right.left = new TreeNode(23);
		root.right.right.left.left = new TreeNode(5);
		root.right.right.left.right = new TreeNode(6);


		root.right.right.right = new TreeNode(24);
		root.right.right.right.left = new TreeNode(7);
		root.right.right.right.right = new TreeNode(8);

		int closestLeafValue = getClosestLeaf(root, nodeToSearch);
		assertEquals(2, closestLeafValue);
	}



	private int sumOfAllNodes(TreeNode root) {

		if (null == root) {
			return 0;
		}

		int sumLeft = root.val + sumOfAllNodes(root.left);
		int sumRight = root.val + sumOfAllNodes(root.right);


		return sumLeft + sumRight - root.val;
	}



	@Test
	void testSumOfAllNodes() throws Exception {
		TreeNode root = new TreeNode(15);

		root.left = new TreeNode(10);
		root.left.left = new TreeNode(8);
		root.left.right = new TreeNode(12);


		root.right = new TreeNode(20);
		root.right.left = new TreeNode(16);
		root.right.right = new TreeNode(25);

		int sum = sumOfAllNodes(root);
		assertEquals(106, sum);
	}



	@Test
	void testSumOfAllNodes_1() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);

		int sum = sumOfAllNodes(root);
		assertEquals(6, sum);
	}


	private int sumOfParentNodesWithChildX(TreeNode root, int numToCheck) {
		List<Integer> listOfNubers = new ArrayList<>();
		int sum = 0;

		_getValuesWithNum(root, numToCheck, listOfNubers);


		for (Integer i : listOfNubers) {
			sum += i;
		}


		return sum;
	}



	private void _getValuesWithNum(TreeNode root, int numToCheck, List<Integer> listOfNubers) {
		if (null == root) {
			return;
		}

		if ((null != root.left && root.left.val == numToCheck) || (null != root.right && root.right.val == numToCheck)) {
			listOfNubers.add(root.val);
		}

		_getValuesWithNum(root.left, numToCheck, listOfNubers);
		_getValuesWithNum(root.right, numToCheck, listOfNubers);
	}



	@Test
	void testSumOfParentNodesHavingChildX() throws Exception {
		TreeNode root = new TreeNode(4);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(7);
		root.left.right = new TreeNode(2);


		root.right = new TreeNode(5);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(3);

		int sum = sumOfParentNodesWithChildX(root, 2);
		assertEquals(11, sum);
	}

	@Test
	void testSumOfParentNodesHavingChildX_1() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(4);
		root.left.right = new TreeNode(5);


		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.right = new TreeNode(7);

		int sum = sumOfParentNodesWithChildX(root, 4);
		assertEquals(5, sum);
	}


	private int sumOfAllTheLeftLeaves(TreeNode root) {
		List<Integer> buffer = new ArrayList<>();
		int sum = 0;

		_getLeftLeavesVal(root, buffer);

		for (Integer i : buffer) {
			sum += i;
		}

		return sum;
	}



	private void _getLeftLeavesVal(TreeNode root, List<Integer> buffer) {
		if (null == root) {
			return;
		}

		if (null != root.left && null == root.left.left && null == root.left.right) {
			buffer.add(root.left.val);
		}

		_getLeftLeavesVal(root.left, buffer);
		_getLeftLeavesVal(root.right, buffer);

	}



	@Test
	void testSumOfAllTheLeftLeaves() throws Exception {
		TreeNode root = new TreeNode(9);
		root.left = new TreeNode(8);
		root.left.left = new TreeNode(5);
		root.left.right = new TreeNode(2);


		root.right = new TreeNode(6);
		root.right.left = new TreeNode(1);

		int sum = sumOfAllTheLeftLeaves(root);
		assertEquals(6, sum);
	}



	enum LeafDirection {
		LEFT,
		RIGHT;
	}

	class NodeLevelLeaves {
		int level;
		TreeNode nodeRef;


		public NodeLevelLeaves(int level, TreeNode node) {
			super();
			this.level = level;
			this.nodeRef = node;
		}


		@Override
		public String toString() {
			return "NodeLevelLeaves [level=" + level + ", node=" + nodeRef + "]";
		}

	}

	class SumLeaves {
		int sum;
		List<TreeNode> listOfLeavesCovered = new LinkedList<TreeNode>();
		LeafDirection leafDirection;



		public SumLeaves(int sum, List<TreeNode> listOfLeavesCovered) {
			super();
			this.sum = sum;
			this.listOfLeavesCovered = listOfLeavesCovered;
		}



		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(leafDirection, sum);
			return result;
		}



		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof SumLeaves))
				return false;
			SumLeaves other = (SumLeaves) obj;
			if (!getEnclosingInstance().equals(other.getEnclosingInstance()))
				return false;
			return leafDirection == other.leafDirection && sum == other.sum;
		}



		@Override
		public String toString() {
			return "SumLeaves [sum=" + sum + ", listOfLeavesCovered=" + listOfLeavesCovered + "]";
		}


		private TreeApi getEnclosingInstance() {
			return TreeApi.this;
		}
	}



	private int maxPathBwTwoLeaves(TreeNode root) {
		int maxSum = 0;
		Map<TreeNode, SumLeaves> mapOfNodeLeaves = new HashMap<>();

		Queue<NodeLevelLeaves> levelQueue = new LinkedList<>();
		levelQueue.add(new NodeLevelLeaves(0, root));

		while (!levelQueue.isEmpty()) {
			NodeLevelLeaves poll = levelQueue.poll();
			int level = poll.level;
			TreeNode currNode = poll.nodeRef;

			List<TreeNode> listOfLeaves = new LinkedList<>();
			SumLeaves sumLeaves = new SumLeaves(0, listOfLeaves);
			_getSumAndListOfLeavesCovered(currNode, sumLeaves, null);
			mapOfNodeLeaves.put(currNode, sumLeaves);

			if (null != currNode.left) {
				levelQueue.add(new NodeLevelLeaves(level + 1, currNode.left));
			}

			if (null != currNode.right) {
				levelQueue.add(new NodeLevelLeaves(level + 1, currNode.right));
			}
		}

		for (Map.Entry<TreeNode, SumLeaves> entry : mapOfNodeLeaves.entrySet()) {
			int sum = entry.getValue().sum;
			if (maxSum < sum) {
				maxSum = sum;
			}
		}

		return maxSum;
	}



	private void _getSumAndListOfLeavesCovered(TreeNode root, SumLeaves sumLeaves, LeafDirection leafDirection) {
		if (null == root) {
			return;
		}

		sumLeaves.sum += root.val;

		if (_isLeaf(root)) {
			sumLeaves.listOfLeavesCovered.add(root);
			sumLeaves.leafDirection = leafDirection;
		}

		_getSumAndListOfLeavesCovered(root.left, sumLeaves, LeafDirection.LEFT);
		_getSumAndListOfLeavesCovered(root.right, sumLeaves, LeafDirection.RIGHT);
	}



	@Test
	void testMaxPathBetweenTwoLeaves() throws Exception {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.left.left = new TreeNode(20);
		root.left.right = new TreeNode(6);
		root.left.right.left = new TreeNode(7);

		root.right = new TreeNode(-4);
		root.right.right = new TreeNode(-8);

		int sum = maxPathBwTwoLeaves(root);
		assertEquals(35, sum);
	}



	@Test
	void testMaxPathBetweenTwoLeaves_1() throws Exception {
		TreeNode root;
		root = new TreeNode(-15);
		root.left = new TreeNode(5);
		root.right = new TreeNode(6);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(1);
		root.left.left.left = new TreeNode(2);
		root.left.left.right = new TreeNode(6);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(9);
		root.right.right.right = new TreeNode(0);
		root.right.right.right.left = new TreeNode(4);
		root.right.right.right.right = new TreeNode(-1);
		root.right.right.right.right.left = new TreeNode(10);
		int sum = maxPathBwTwoLeaves(root);
		assertEquals(27, sum);
	}




}
