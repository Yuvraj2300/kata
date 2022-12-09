package com.problems.crackcode.kata.apis;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.junit.jupiter.api.Test;

import com.problems.crackcode.kata.exceptions.KataException;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

public class TreeApi2 {
	@Data
	@AllArgsConstructor
	@ToString(includeFieldNames = false)
	class Node {
		int hd = 0;
		String strVal;
		int val;
		Node left;
		Node right;

		public Node(int val) {
			super();
			this.val = val;
		}

		public Node(String strVal) {
			super();
			this.strVal = strVal;
		}



	}


	@Data
	@AllArgsConstructor
	@ToString
	class BinaryTree {
		Node root;

		public BinaryTree() {
			root = null;
		}
	}



	@Test
	void testCalcualteDiameter() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);

		assertEquals(3, calculateDiameter(tree.getRoot()));
	}



	private int calculateDiameter(Node root) {
		//diameter is a combinatiopn for max dia from left or right and max height thru root
		if (null == root)
			return 0;

		int lheight = height(root.left);
		int rheight = height(root.right);

		int ldiameter = calculateDiameter(root.left);
		int rdiameter = calculateDiameter(root.right);

		return Math.max(rheight + lheight, Math.max(ldiameter, rdiameter));
	}



	private int height(Node node) {
		if (null == node) {
			return 0;
		}
		return 1 + Math.max(height(node.left), height(node.right));
	}



	@Test
	void testLevelInsertion() throws Exception {
		Node root = new Node(10);

		root.left = new Node(11);
		root.left.left = new Node(7);
		root.right = new Node(9);
		root.right.left = new Node(15);
		root.right.right = new Node(8);

		Node updated = insertLevel(root, 12);
		System.out.println(updated);
	}



	private Node insertLevel(Node root, int key) {
		Queue<Node> procQueue = new LinkedList<>();

		procQueue.add(root);

		while (!procQueue.isEmpty()) {
			Node poppedNode = procQueue.remove();

			if (_checkAvailableSpaceAndAdd(key, procQueue, poppedNode)) {
				break;
			}
		}
		return root;
	}



	private boolean _checkAvailableSpaceAndAdd(int key, Queue<Node> procQueue, Node toCheck) {
		boolean flag = false;
		if (toCheck.left != null) {
			procQueue.add(toCheck.left);
		} else {
			toCheck = new Node(key);
			System.out.println();
			flag = true;
		}

		if (toCheck.right != null) {
			procQueue.add(toCheck.right);
		} else {
			toCheck = new Node(key);
			System.out.println();
			flag = true;
		}

		return flag;
	}



	@Test
	void testDeleteKeyFromTree() throws Exception {
		int key = 10;
		Node root = new Node(10);
		root.left = new Node(20);
		root.right = new Node(30);

		Node updated = deleteKeyFromTree(root, key);
		System.out.println(updated);
	}



	@Test
	void testDeleteKeyFromTree_1() throws Exception {
		int key = 12;
		Node root = new Node(13);
		root.left = new Node(12);
		root.left.left = new Node(4);
		root.left.right = new Node(19);

		root.right = new Node(10);
		root.right.left = new Node(4);
		root.right.right = new Node(91);

		System.out.println("Before Deltion Algo :");
		inOrderPrint(root);

		Node updated = deleteKeyFromTree(root, key);
		System.out.println();
		System.out.println("After Deltion Algo :");
		inOrderPrint(updated);
	}


	public void inOrderPrint(Node root) {
		if (!Objects.isNull(root)) {
			inOrderPrint(root.left);
			System.out.print(root.val + " ");
			inOrderPrint(root.right);
		}
	}



	public void inOrderPrintStr(Node root) {
		if (!Objects.isNull(root)) {
			inOrderPrintStr(root.left);
			System.out.print(root.strVal + " ");
			inOrderPrintStr(root.right);
		}
	}


	private Node deleteKeyFromTree(Node root, int key) {
		// TODO Auto-generated method stub
		if (null == root)
			throw new KataException("");

		Queue<Node> procQueue = new LinkedList<TreeApi2.Node>();
		procQueue.add(root);

		Node keyNode = null;
		Node last = null;
		Node pop = null;

		while (!procQueue.isEmpty()) {
			pop = procQueue.remove();

			if (pop.val == key) {
				keyNode = pop;
			}

			if (!Objects.isNull(pop.left)) {
				last = pop;
				procQueue.add(pop.left);
			}

			if (!Objects.isNull(pop.right)) {
				last = pop;
				procQueue.add(pop.right);
			}
		}

		if (Objects.nonNull(keyNode)) {
			keyNode.val = pop.val;

			if (last.right == pop)
				last.right = null;
			else
				last.left = null;
		}

		return root;
	}


	void callCheck(String s) {
		System.out.println("in string");
	}

	void callCheck(Object o) {
		System.out.println("in object");
	}

	//arises ambiguity
	//	void callCheck(Integer o) {
	//		System.out.println("in integer");
	//	}


	void checkRemove() {
		ArrayList<Integer> al = new ArrayList<>();
		for (int i = -3; i < 3; i++) {
			al.add(i);
		}
		System.out.println(al);
		for (int i = 0; i < 3; i++) {
			al.remove(i);
			System.out.println(al);
		}

		System.out.println(al);


		new Thread(System.out::println);

		ExecutorService es = Executors.newCachedThreadPool();
		//		es.submit(System.out::println); // will not allow, as overload resolution will not happen.


	}

	@Test
	void testOverloadings() throws Exception {
		callCheck(null);
		checkRemove();
	}


	@Test
	void testCompleteBinaryTree() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(20);
		tree.root.right = new Node(30);
		tree.root.left.right = new Node(40);
		tree.root.left.left = new Node(50);
		tree.root.right.left = new Node(60);
		tree.root.left.left.left = new Node(80);
		tree.root.right.right = new Node(70);
		tree.root.left.left.right = new Node(90);
		tree.root.left.right.left = new Node(80);
		tree.root.left.right.right = new Node(90);
		tree.root.right.left.left = new Node(80);
		tree.root.right.left.right = new Node(90);
		tree.root.right.right.left = new Node(80);
		tree.root.right.right.right = new Node(90);


		assertTrue(checkCompleteTree(tree));
	}



	@Test
	void testCompleteBinaryTree_1() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(20);
		tree.root.right = new Node(30);

		assertTrue(checkCompleteTree(tree));
	}



	private boolean checkCompleteTree(BinaryTree tree) {
		return checkComplete(tree.root);
	}


	private boolean checkComplete(Node root) {
		if (null == root)
			return true;

		if (null == root.left && null == root.right)
			return true;

		if (null != root.left && null != root.right)
			return checkComplete(root.left) && checkComplete(root.right);

		return false;
	}


	@Test
	void testCreateTreeFromPreAndInorder() throws Exception {
		String[] inOrder = { "D", "B", "E", "A", "F", "C" };
		String[] preOrder = { "A", "B", "D", "E", "C", "F" };

		inOrderPrintStr(createTreeFromPreAndInorder(inOrder, preOrder));

	}



	private Node createTreeFromPreAndInorder(String[] inOrder, String[] preOrder) {
		int stInOd = 0;
		int endInOd = inOrder.length - 1;

		return _createTree(inOrder, preOrder, 0, stInOd, endInOd);
	}



	private Node _createTree(String[] inOrder, String[] preOrder, int preOdItr, int stInOd, int endInOd) {
		if (endInOd < stInOd) {
			return null;
		}

		String nodeVal = preOrder[preOdItr++];
		Node new_node = new Node(nodeVal);

		int valIdx = _searchValInInOd(inOrder, endInOd, nodeVal);

		if (-1 == valIdx)
			return null;

		new_node.left = _createTree(inOrder, preOrder, preOdItr, stInOd, valIdx - 1);
		new_node.right = _createTree(inOrder, preOrder, preOdItr, valIdx + 1, inOrder.length - 1);

		return new_node;
	}



	private int _searchValInInOd(String[] inOrder, int endInOd, String nodeVal) {
		for (int i = 0; i < endInOd; i++) {
			if (inOrder[i].equals(nodeVal)) {
				return i;
			}
		}
		return -1;
	}



	@Test
	void testConvertATernaryToBT() throws Exception {
		String exp = "a?b:c";
		Node tree = convertTernaryToBT(exp, 0);
		System.out.println(tree);
		inOrderPrintStr(tree);
	}



	@Test
	void testConvertATernaryToBT_1() throws Exception {
		String exp = "a?b?c:d:e";
		Node tree = convertTernaryToBT(exp, 0);
		System.out.println(tree);
		inOrderPrintStr(tree);
	}


	private Node convertTernaryToBT(String exp, int i) {
		if (i > exp.length()) {
			return null;
		}

		Node node = new Node(String.valueOf(exp.charAt(i)));

		i++;

		if (i < exp.length() && ':' == exp.charAt(i)) {
			node.left = convertTernaryToBT(exp, i + 1);
		}

		if (i < exp.length() && '?' == exp.charAt(i)) {
			node.right = convertTernaryToBT(exp, i + 1);
		}

		return node;
	}


	@Test
	void testCheckIfCousin() {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.left.right.right = new Node(15);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);
		tree.root.right.left.right = new Node(8);

		Node Node1, Node2;
		Node1 = tree.root.left.left;
		Node2 = tree.root.right.right;
		assertTrue(checkIfCousin(tree.root, Node1, Node2));
	}


	@Test
	void testCheckIfCousin_1() {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);
		tree.root.left.right.right = new Node(15);
		tree.root.right.left = new Node(6);
		tree.root.right.right = new Node(7);
		tree.root.right.left.right = new Node(8);

		Node Node1, Node2;
		Node1 = tree.root.left;
		Node2 = tree.root.right.right;
		assertFalse(checkIfCousin(tree.root, Node1, Node2));
	}

	boolean checkIfCousin(Node root, Node n1, Node n2) {

		int l1 = level(root, n1, 0);
		int l2 = level(root, n2, 0);


		return l1 == l2 && !isSibling(root, n1, n2);
	}



	private boolean isSibling(Node root, Node n1, Node n2) {
		if (root == null)
			return false;
		//
		//		if (root.left == n1 && root.right == n2)
		//			return true;
		//
		//		if (root.left == n2 && root.right == n1)
		//			return true;
		//
		//		return isSibling(root.left, n1, n2) || isSibling(root.right, n1, n2);

		// @formatter:off
		return (root.left == n1 && root.right == n2) ||
					(root.left == n2 && root.right == n1) ||
						isSibling(root.left, n1, n2) ||
							isSibling(root.right, n1, n2);
		// @formatter:on
	}



	private int level(Node root, Node node, int level) {
		if (null == root)
			return 0;

		if (root == node)
			return level;

		int leftLevel = level(root.left, node, level + 1);
		if (leftLevel != 0)
			return leftLevel;

		return level(root.right, node, level + 1);
	}


	@Test
	void testCheckIfAllNodesAtSamelevel() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(12);
		tree.root.left = new Node(5);
		tree.root.left.left = new Node(3);
		tree.root.left.right = new Node(9);
		tree.root.left.left.left = new Node(1);
		tree.root.left.right.left = new Node(1);

		assertTrue(sameLevelLeaves(tree.root, 0, 0));

	}


	//	@Test
	//	void testCheckIfAllNodesAtSamelevel_1() throws Exception {
	//		BinaryTree tree = new BinaryTree();
	//		tree.root = new Node(12);
	//		tree.root.left = new Node(5);
	//		tree.root.right = new Node(7);
	//		tree.root.left.left = new Node(3);
	//
	//		assertFalse(sameLevelLeaves(tree.root, 0, 0));
	//
	//	}
	//



	boolean sameLevelLeaves(Node root, int level, int maxLeafLevel) {

		if (null == root) {
			return true;
		}

		if (root.left == null && root.right == null) {
			if (maxLeafLevel == 0) {
				maxLeafLevel = level;
				return true;
			}

			if (maxLeafLevel == level)
				return true;
		}

		return sameLevelLeaves(root.left, level, maxLeafLevel) && sameLevelLeaves(root.right, level, maxLeafLevel);
	}


	@Test
	void testSumTree() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(6);
		tree.root.right = new Node(4);

		assertTrue(checkIfTreeIsSumTree(tree.root));
	}



	@Test
	void testSumTree_1() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(26);
		tree.root.left = new Node(10);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(6);
		tree.root.right.right = new Node(3);

		assertTrue(checkIfTreeIsSumTree(tree.root));
	}

	@Test
	void testSumTree_2() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(26);
		tree.root.left = new Node(10);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(6);
		//		tree.root.right.right = new Node(3);

		assertFalse(checkIfTreeIsSumTree(tree.root));
	}

	boolean checkIfTreeIsSumTree(Node root) {
		if (null == root)
			return true;

		//		if (null == root.left || null == root.right) {
		//			return false;
		//		}

		if (null == root.left && null == root.right)
			return true;
		else if (root.left != null && root.right != null && root.val == root.right.val + root.left.val)
			return true;
		else
			return checkIfTreeIsSumTree(root.right) && checkIfTreeIsSumTree(root.left);

	}



	@Test
	void testTreeFullOrNot() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(20);
		tree.root.right = new Node(30);
		tree.root.left.right = new Node(40);
		tree.root.left.left = new Node(50);
		tree.root.right.left = new Node(60);
		tree.root.left.left.left = new Node(80);
		tree.root.right.right = new Node(70);
		tree.root.left.left.right = new Node(90);
		tree.root.left.right.left = new Node(80);
		tree.root.left.right.right = new Node(90);
		tree.root.right.left.left = new Node(80);
		tree.root.right.left.right = new Node(90);
		tree.root.right.right.left = new Node(80);
		tree.root.right.right.right = new Node(90);

		assertTrue(checkTreeFullorNot(tree.root));
	}




	@Test
	void testTreeFullOrNot_1() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(10);
		tree.root.left = new Node(20);
		tree.root.right = new Node(30);
		tree.root.left.right = new Node(40);
		tree.root.left.left = new Node(50);
		tree.root.right.left = new Node(60);
		tree.root.left.left.left = new Node(80);
		tree.root.right.right = new Node(70);
		tree.root.left.left.right = new Node(90);
		tree.root.left.right.left = new Node(80);
		tree.root.left.right.right = new Node(90);
		tree.root.right.left.left = new Node(80);
		tree.root.right.left.right = new Node(90);
		tree.root.right.right.left = new Node(80);

		assertFalse(checkTreeFullorNot(tree.root));
	}

	boolean checkTreeFullorNot(Node root) {
		if (null == root) {
			return true;
		}

		if (null == root.left && null == root.right) {
			return true;
		}

		if (root.left != null && root.right != null) {
			return checkTreeFullorNot(root.left) && checkTreeFullorNot(root.right);
		}

		return false;
	}


	@Test
	void testCheckPerfectBinaryTree() throws Exception {
		Node root = null;
		root = new Node(10);
		root.left = new Node(20);
		root.right = new Node(30);

		//		root.left.left = new Node(40);
		//		root.left.right = new Node(50);
		//		root.right.left = new Node(60);
		//		root.right.right = new Node(70);

		assertTrue(checkPerfectBinaryTree(root));
	}


	boolean checkPerfectBinaryTree(Node root) {
		return checkPerfect(root, new LeaveDepth(), 0);
	}


	class LeaveDepth {
		int depth;
	}

	private boolean checkPerfect(Node root, LeaveDepth depth, int level) {
		if (null == root)
			return true;

		if (null == root.left && null == root.right) {
			depth.depth = level + 1;
			//return (depth == level + 1);
			return true;
		}

		if (root.left == null || root.right == null) {
			return false;
		}
		boolean leftFlag = checkPerfect(root.left, depth, level + 1);
		boolean rightFlag = checkPerfect(root.right, depth, level + 1);

		return leftFlag && rightFlag;
		//		return checkPerfect(root.left, depth, level + 1) && checkPerfect(root.right, depth, level + 1);
	}




	@Test
	void testSameLevelLeaves() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(12);
		tree.root.left = new Node(5);
		tree.root.left.left = new Node(3);
		tree.root.left.right = new Node(9);
		tree.root.left.left.left = new Node(1);
		tree.root.left.right.left = new Node(1);

		assertTrue(checkLeafSameLevel(tree.root, new LeaveDepth(), 0));
	}


	@Test
	void testSameLevelLeaves_1() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(12);
		tree.root.left = new Node(5);
		tree.root.right = new Node(7);
		tree.root.left.left = new Node(3);

		assertFalse(checkLeafSameLevel(tree.root, new LeaveDepth(), 0));
	}



	boolean checkLeafSameLevel(Node root, LeaveDepth depth, int currLevel) {
		if (Objects.isNull(root))
			return true;

		if (Objects.isNull(root.left) && Objects.isNull(root.right)) {
			if (depth.depth == 0) {
				depth.depth = currLevel;
			}
			return depth.depth == currLevel;

		}

		return checkLeafSameLevel(root.left, depth, currLevel + 1) && checkLeafSameLevel(root.right, depth, currLevel + 1);
	}



	@Test
	void testCheckIfTreeIsAMirror() throws Exception {
		Node a = new Node(1);
		Node b = new Node(1);
		a.left = new Node(2);
		a.right = new Node(3);
		a.left.left = new Node(4);
		a.left.right = new Node(5);

		b.left = new Node(3);
		b.right = new Node(2);
		b.right.left = new Node(5);
		b.right.right = new Node(4);

		assertTrue(checkIfTreeIsAMirror(a, b));
	}



	@Test
	void testCheckIfTreeIsAMirror_1() throws Exception {
		Node a = new Node(1);
		Node b = new Node(1);
		a.left = new Node(2);
		a.right = new Node(3);
		a.left.left = new Node(4);
		a.left.right = new Node(5);

		b.left = new Node(3);
		b.right = new Node(12);
		b.right.left = new Node(5);
		b.right.right = new Node(4);

		assertFalse(checkIfTreeIsAMirror(a, b));
	}



	boolean checkIfTreeIsAMirror(Node a, Node b) {
		if (Objects.isNull(a) && Objects.isNull(b))
			return true;

		if (Objects.isNull(a) || Objects.isNull(b))
			return false;

		// @formatter:off
		return a.val==b.val	&&
				checkIfTreeIsAMirror(a.right, b.left) &&
					checkIfTreeIsAMirror(a.left, b.right);
		// @formatter:on

	}



	@Test
	void testIfTreeIdentical() throws Exception {
		Node a = new Node(1);
		a.left = new Node(2);
		a.right = new Node(3);
		a.left.left = new Node(4);
		a.left.right = new Node(5);

		Node b = new Node(1);
		b.left = new Node(2);
		b.right = new Node(3);
		b.left.left = new Node(4);
		b.left.right = new Node(5);

		assertTrue(checkIfTreeIdentical(a, b));
	}


	@Test
	void testIfTreeIdentical_1() throws Exception {
		Node a = new Node(1);
		a.left = new Node(2);
		a.right = new Node(3);
		a.left.left = new Node(4);
		a.left.right = new Node(5);

		Node b = new Node(1);
		b.left = new Node(2);
		b.right = new Node(3);
		b.left.left = new Node(4);
		b.left.right = new Node(4);

		assertFalse(checkIfTreeIdentical(a, b));
	}




	boolean checkIfTreeIdentical(Node a, Node b) {
		if (Objects.isNull(a) && Objects.isNull(b))
			return true;

		if (Objects.isNull(a) || Objects.isNull(b))
			return false;

		// @formatter:off
		return a.val==b.val	&&
					checkIfTreeIdentical(a.left, b.left) &&
						checkIfTreeIdentical(a.right, b.right);
 
		// @formatter:on

	}




	@Test
	void testSumOfAllNodes() throws Exception {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.right.left.right = new Node(8);

		assertEquals(36, sumOfAllNodes(root));
	}


	int sumOfAllNodes(Node root) {
		return sum(root, 0);
	}



	private int sum(Node root, int currSum) {
		if (Objects.isNull(root))
			return 0;

		currSum = root.val + sum(root.left, currSum) + sum(root.right, currSum);

		return currSum;
	}


	@Test
	void testSumOfAllNodesLevel() throws Exception {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.right.left.right = new Node(8);

		assertEquals(36, sumLevelOrder(root));
	}

	private int sumLevelOrder(Node root) {
		Queue<Node> q = new LinkedList<TreeApi2.Node>();
		q.add(root);

		int sum = 0;

		while (!q.isEmpty()) {
			Node currNode = q.poll();

			sum += currNode.val;

			if (Objects.nonNull(currNode.left))
				q.add(currNode.left);

			if (Objects.nonNull(currNode.right))
				q.add(currNode.right);
		}

		return sum;
	}




	@Test
	void testSumOfAllNodes2() throws Exception {
		Node root = new Node(1);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(6);
		root.right.right = new Node(7);
		root.right.left.right = new Node(8);

		assertEquals(36, sum2(root, 0));
	}


	private int sum2(Node root, int currSum) {
		if (root == null)
			return 0;

		currSum = root.val;
		currSum += sum2(root.left, currSum);
		currSum += sum2(root.right, currSum);
		return currSum;
	}





	class SumVar {
		public int sum = 0;
	}

	void sumOfMatchingParentNodes(Node root, int x, SumVar sumVar) {
		if (Objects.isNull(root))
			return;

		if (root.left != null && root.left.val == x || root.right != null && root.right.val == x) {
			sumVar.sum += root.val;
		}

		sumOfMatchingParentNodes(root.left, x, sumVar);
		sumOfMatchingParentNodes(root.right, x, sumVar);

	}


	@Test
	void SumOfLeftLeaves() {
		Node root = new Node(9);
		root.left = new Node(8);
		root.left.left = new Node(5);
		root.left.right = new Node(2);
		root.right = new Node(6);
		root.right.left = new Node(1);


		sumOfLeftLeaves(root, new SumVar());
	}

	void sumOfLeftLeaves(Node root, SumVar sumVar) {
		if (Objects.isNull(root))
			return;


		if (root.left != null && root.left.left == null && root.left.right == null) {
			sumVar.sum += root.left.val;
		}

		sumOfLeftLeaves(root.left, sumVar);
		sumOfLeftLeaves(root.right, sumVar);

	}



	@Test
	void testCheckIfRootValPairsExistInPath() throws Exception {
		Node root = new Node(8);
		root.left = new Node(5);
		root.right = new Node(4);
		root.left.left = new Node(9);
		root.left.right = new Node(7);
		root.left.right.left = new Node(1);
		root.left.right.right = new Node(12);
		root.left.right.right.right = new Node(2);
		root.right.right = new Node(11);
		root.right.right.left = new Node(3);

		assertTrue(checkIfRootValPairsExistInPath(root));
	}



	boolean checkIfRootValPairsExistInPath(Node root) {
		return checkForRootSumPath(root, new HashSet<Integer>(), root.val);
	}



	boolean checkForRootSumPath(Node root, Set<Integer> nodeSet, int rootData) {
		if (root == null) {
			return false;
		}

		int compl = rootData - root.val;
		if (nodeSet.contains(compl))
			return true;

		nodeSet.add(root.val);

		boolean res = checkForRootSumPath(root.left, nodeSet, rootData) || checkForRootSumPath(root.right, nodeSet, rootData);

		nodeSet.remove(root.val);

		return res;
	}


	@Test
	void testGetMaxSumLeaf() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(-15);
		tree.root.left = new Node(5);
		tree.root.right = new Node(6);
		tree.root.left.left = new Node(-8);
		tree.root.left.right = new Node(1);
		tree.root.left.left.left = new Node(2);
		tree.root.left.left.right = new Node(6);
		tree.root.right.left = new Node(3);
		tree.root.right.right = new Node(9);
		tree.root.right.right.right = new Node(0);
		tree.root.right.right.right.left = new Node(4);
		tree.root.right.right.right.right = new Node(-1);
		tree.root.right.right.right.right.left = new Node(10);

		assertEquals(27, getMaxSumLeafToLeaf(tree.root, new SumVar()));
	}


	int getMaxSumLeafToLeaf(Node root, SumVar sumVar) {
		if (root == null)
			return 0;

		//		//this is for our logic to work in case any child was sent as a null
		//		if (root.left == null) {
		//			root.left = new Node(0);
		//		} else if (root.right == null) {
		//			root.right = new Node(0);
		//		}

		if (root.left == null && root.right == null)
			return root.val;

		int rs = getMaxSumLeafToLeaf(root.right, sumVar);
		int ls = getMaxSumLeafToLeaf(root.left, sumVar);

		if (root.left != null && root.right != null) {
			sumVar.sum = Math.max(sumVar.sum, ls + rs + root.val);

			return Math.max(rs, ls) + sumVar.sum;
		}

		return (root.left == null) ? rs + root.val : ls + root.val;
	}



	@Test
	void testSumOfMaxSubTree() throws Exception {
		Node root = new Node(1);
		root.left = new Node(-2);
		root.right = new Node(3);
		root.left.left = new Node(4);
		root.left.right = new Node(5);
		root.right.left = new Node(-6);
		root.right.right = new Node(2);

		assertEquals(7, sumOfMaxSubTree(root, new SumVar()));
	}



	int sumOfMaxSubTree(Node root, SumVar sumVar) {
		if (root == null)
			return 0;

		//left subtree
		//right subtree

		//check if sum is greater than maxSum till now


		return sumVar.sum;
	}

	@Test
	void testFindSizeOfTheTree() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);

		assertEquals(5, findSizeOfTree(tree.root));
	}


	int findSizeOfTree(Node root) {
		if (root == null)
			return 0;

		return 1 + findSizeOfTree(root.left) + findSizeOfTree(root.right);
	}



	@Test
	void testMaxDepth() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.left = new Node(4);
		tree.root.left.right = new Node(5);

		assertEquals(3, maxDepth(tree.root));
	}


	int maxDepth(Node root) {
		if (root == null)
			return 0;

		int ld = maxDepth(root.left);
		int rd = maxDepth(root.right);

		return Math.max(ld, rd) + 1;
	}


	@Test
	void testMinPath() throws Exception {

	}



	class MinDist {
		int dist = 0;
	}



	int minPath(Node node, int level, MinDist minDist) {
		if (Objects.isNull(node))
			return 0;

		if (node.left == null && node.right == null)
			if (level < minDist.dist)
				minDist.dist = level;

		minPath(node.left, level + 1, minDist);
		minPath(node.right, level + 1, minDist);

		return minDist.dist;
	}



	@Test
	void testGetTheBottomView() throws Exception {
		Node root = new Node(20);
		root.left = new Node(8);
		root.right = new Node(22);
		root.left.left = new Node(5);
		root.left.right = new Node(3);
		root.right.left = new Node(4);
		root.right.right = new Node(25);
		root.left.right.left = new Node(10);
		root.left.right.right = new Node(14);


		Map<Integer, Node> mapOfNodesOnBottomView = getTheBottomView(root);

		System.out.print("Nodes at bottom : -> ");
		mapOfNodesOnBottomView.entrySet().stream().forEach(e -> {
			System.out.print(e.getValue().getVal() + ", ");
		});
	}



	private Map<Integer, Node> getTheBottomView(Node node) {
		Map<Integer, Node> mapOfNodesOnBottomView = new HashMap<Integer, Node>();
		int hd = 0;

		Queue<Node> q = new LinkedList<TreeApi2.Node>();

		q.add(node);

		while (!q.isEmpty()) {
			Node poppedNode = q.remove();
			hd = poppedNode.hd;
			mapOfNodesOnBottomView.put(hd, poppedNode);

			if (!Objects.isNull(poppedNode.left)) {
				poppedNode.left.hd = hd - 1;
				q.add(poppedNode.left);
			}

			if (!Objects.isNull(poppedNode.right)) {
				poppedNode.right.hd = hd + 1;
				q.add(poppedNode.right);
			}
		}

		return mapOfNodesOnBottomView;
	}



	@Test
	void testGetTheTopView() throws Exception {
		BinaryTree tree = new BinaryTree();
		tree.root = new Node(1);
		tree.root.left = new Node(2);
		tree.root.right = new Node(3);
		tree.root.left.right = new Node(4);
		tree.root.left.right.right = new Node(5);
		tree.root.left.right.right.right = new Node(6);

		Map<Integer, Node> mapOfTopView = getTheTopView(tree.root);

		System.out.println("The TopView ::--> ");
		mapOfTopView.entrySet().stream().forEach(e -> {
			System.out.print(e.getValue().val + ", ");
		});
	}



	private Map<Integer, Node> getTheTopView(Node node) {
		if (null == node)
			return Collections.emptyMap();

		Map<Integer, Node> mapOfTopView = new HashMap<Integer, TreeApi2.Node>();

		int hd = 0;
		Queue<Node> q = new LinkedList<Node>();
		q.add(node);


		while (!q.isEmpty()) {
			Node poppedNode = q.remove();

			hd = poppedNode.hd;

			if (!mapOfTopView.containsKey(hd)) {
				mapOfTopView.put(hd, poppedNode);
			}

			if (null != poppedNode.left) {
				poppedNode.left.hd = hd - 1;
				q.add(poppedNode.left);
			}

			if (null != poppedNode.right) {
				poppedNode.right.hd = hd + 1;
				q.add(poppedNode.right);
			}

		}

		return mapOfTopView;
	}

	@Test
	void testGetLeftViewOfTheTree() throws Exception {
		Node root = new Node(10);
		root.left = new Node(2);
		root.right = new Node(3);
		root.left.left = new Node(7);
		root.left.right = new Node(8);
		root.right.right = new Node(15);
		root.right.left = new Node(12);
		root.right.right.left = new Node(14);

		Set<Node> leftViewOfTree = getLeftViewOfTree(root);
		leftViewOfTree.stream().forEach(e -> System.out.println(e.val));
	}


	Set<Node> getLeftViewOfTree(Node root) {
		if (null == root)
			return Collections.emptySet();

		Set<Node> set = new HashSet<TreeApi2.Node>();
		Queue<Node> q = new LinkedList<TreeApi2.Node>();

		q.add(root);

		while (!q.isEmpty()) {
			int actualSize = q.size();
			for (int i = 0; i < actualSize; i++) {
				Node polled = q.poll();

				if (0 == i) {
					set.add(polled);
				}

				if (null != polled.left)
					q.add(polled.getLeft());


				if (null != polled.right)
					q.add(polled.getRight());
			}
		}

		return set;
	}

}




