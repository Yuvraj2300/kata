package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.structures.kata.GraphNode;
import com.structures.kata.TreeNode;

public class GraphAndTrees {

	/**
	 * @author yuvraj1.sharma
	 *
	 *         Route Between Nodes: Given a directed graph, design an algorithm to
	 *         find out whether there is a route between two nodes
	 *
	 */
	public boolean isThereAPath_BFS(GraphNode n1, GraphNode n2) {
		//perform bfs to search n2

		Queue<GraphNode> queue = new LinkedList<GraphNode>();
		n1.visited = true;
		queue.add(n1);

		while (!queue.isEmpty()) {
			GraphNode poll = queue.poll();

			if (poll == n2) {
				return true;
			}

			for (GraphNode node : poll.neighbors) {
				if (!node.visited) {
					queue.add(node);
				}
			}

		}

		return false;
	}

	@Test
	void testIsThereAPath() throws Exception {
		GraphNode n2 = new GraphNode(null);

		GraphNode d = new GraphNode(List.of(n2));
		GraphNode c = new GraphNode(List.of(d));
		GraphNode b = new GraphNode(List.of(d));
		GraphNode a = new GraphNode(List.of(b, c));

		GraphNode n1 = new GraphNode(List.of(a));

		assertTrue(isThereAPath_BFS(n1, n2));
	}


	@Test
	void testIsThereAPath_1() throws Exception {
		GraphNode n2 = new GraphNode(null);

		GraphNode d = new GraphNode();
		GraphNode c = new GraphNode(List.of(d));
		GraphNode b = new GraphNode(List.of(d));
		GraphNode a = new GraphNode(List.of(b, c));

		GraphNode n1 = new GraphNode(List.of(a));

		assertFalse(isThereAPath_BFS(n1, n2));
	}



	public TreeNode getTreeFromSortedArray(int[] arr, boolean needBST) {
		if (needBST) {
			Arrays.sort(arr);
		}

		return _getTree(arr, 0, arr.length - 1);
	}


	public TreeNode getTreeFromSortedArray(int[] arr) {
		Arrays.sort(arr);
		return _getTree(arr, 0, arr.length - 1);
	}


	private TreeNode _getTree(int[] arr, int start, int end) {
		if (end < start) {
			return null;
		}

		int mid = (end + start) / 2;

		TreeNode node = new TreeNode(arr[mid]);
		node.left = _getTree(arr, start, mid - 1);
		node.right = _getTree(arr, mid + 1, end);

		return node;

	}

	@Test
	void testGetNodeFromSortedArray() throws Exception {
		int[] array = { 2, 8, 4, 3, 1 };

		Arrays.sort(array);
		TreeNode tree = getTreeFromSortedArray(array);
		System.out.println(tree.toString());
	}




	public Map<Integer, List<Integer>> getValuesByLevel(TreeNode tree) {
		Map<Integer, List<Integer>> mapOfLevelAndListOfValuesPerLevel = new HashMap<Integer, List<Integer>>();

		_getValuesByLevel(tree, mapOfLevelAndListOfValuesPerLevel);

		return mapOfLevelAndListOfValuesPerLevel;
	}



	private void _getValuesByLevel(TreeNode tree, Map<Integer, List<Integer>> mapOfLevelAndListOfValuesPerLevel) {
		int level = 0;
		_collectLevelValues(tree, mapOfLevelAndListOfValuesPerLevel, level);
	}



	private void _collectLevelValues(TreeNode tree, Map<Integer, List<Integer>> mapOfLevelAndListOfValuesPerLevel, int level) {
		if (null == tree) {
			return;
		} else {
			_updateMapWithLevelValues(tree, mapOfLevelAndListOfValuesPerLevel, level);
			if (null != tree.left && null != tree.right) {
				level++;
				_collectLevelValues(tree.left, mapOfLevelAndListOfValuesPerLevel, level);
				_collectLevelValues(tree.right, mapOfLevelAndListOfValuesPerLevel, level);
			} else if (null == tree.left && null == tree.right) {
				return;
			} else if (null != tree.left) {
				level++;
				_collectLevelValues(tree.left, mapOfLevelAndListOfValuesPerLevel, level);
			} else {
				level++;
				_collectLevelValues(tree.right, mapOfLevelAndListOfValuesPerLevel, level);
			}

		}
	}

	private void _updateMapWithLevelValues(TreeNode tree, Map<Integer, List<Integer>> mapOfLevelAndListOfValuesPerLevel, int level) {
		if (mapOfLevelAndListOfValuesPerLevel.containsKey(level)) {
			List<Integer> list = mapOfLevelAndListOfValuesPerLevel.get(level);
			list.add(tree.val);

			mapOfLevelAndListOfValuesPerLevel.put(level, list);
		} else {
			List<Integer> listOfValues = new ArrayList<>();
			listOfValues.add(tree.val);

			mapOfLevelAndListOfValuesPerLevel.put(level, listOfValues);
		}
	}



	@Test
	void testGetValuesByLevel() throws Exception {
		int[] arr = { 1, 2, 3 };

		TreeNode tree = getTreeFromSortedArray(arr);

		printInOrder(tree);

		System.out.println();

		Map<Integer, List<Integer>> valuesByLevel = getValuesByLevel(tree);

		for (Map.Entry<Integer, List<Integer>> entry : valuesByLevel.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}



	@Test
	void testGetValuesByLevel_1() throws Exception {
		int[] arr = { 45, 123, 5, 34, 124 };

		TreeNode tree = getTreeFromSortedArray(arr);
		System.out.println(tree);
		System.out.println(" ");
		printInOrder(tree);

		System.out.println();

		Map<Integer, List<Integer>> valuesByLevel = getValuesByLevel(tree);

		for (Map.Entry<Integer, List<Integer>> entry : valuesByLevel.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
	}



	public boolean validateBST(TreeNode tree) {

		if (tree == null) {
			return true;
		}

		if (tree.left != null && tree.left.val > tree.val) {
			return false;
		}

		if (tree.right != null && tree.right.val < tree.val) {
			return false;
		}


		if (!validateBST(tree.left) || !validateBST(tree.right)) {
			return false;
		}

		return true;
	}



	@Test
	void testValdiateBST() throws Exception {
		int[] arr = { 45, 123, 5, 34, 124 };

		TreeNode tree = getTreeFromSortedArray(arr, true);
		printInOrder(tree);
		assertTrue(validateBST(tree));
	}


	@Test
	void testValdiateBST_1() throws Exception {
		int[] arr = { 3, 2, 1 };

		TreeNode tree = getTreeFromSortedArray(arr, true);
		System.out.println(tree);
		printInOrder(tree);
		assertTrue(validateBST(tree));
	}




	/**
	 * @author yuvraj1.sharma
	 * 
	 * 
	 *         Build Order: You are given a list of projects and a list of
	 *         dependencies (which is a list of pairs of projects, where the second
	 *         project is dependent on the first project). All of a project's
	 *         dependencies must be built before the project is. Find a build order
	 *         that will allow the projects to be built. If there is no valid build
	 *         order, return an error. EXAMPLE Input: projects: a, b, c, d, e, f
	 *         dependencies: (a, d), (f, b), (b, d), (f, a), (d, c) Output: f, e, a,
	 *         b, d, c
	 */
	public Set<String> getBuildOrder(List<GraphNode> listOfDependencies) {

		Set<String> orderOfDependencies = new LinkedHashSet<String>();
		Queue<GraphNode> dependencyProcessor = new LinkedList<GraphNode>();

		for (GraphNode graphNode : listOfDependencies) {

			if (!graphNode.neighbors.isEmpty()) {

				graphNode.neighbors.stream().allMatch((dependency) -> dependency.visited);

				for (GraphNode dependency : graphNode.neighbors) {
					dependencyProcessor.add(dependency);
					dependency.visited = true;

					while (!dependencyProcessor.isEmpty()) {
						dependencyProcessor.remove();
						orderOfDependencies.add(dependency.val);

						for (GraphNode circularDependency : dependency.neighbors) {
							if (!circularDependency.visited) {
								circularDependency.visited = true;
								dependencyProcessor.add(circularDependency);
							}
						}
					}
				}
			} else {
				orderOfDependencies.add(graphNode.val);
			}

		}


		return orderOfDependencies;
	}



	@Test
	void testGetBuildOrder() throws Exception {

		GraphNode a = _getAGraphNode("a");
		GraphNode b = _getAGraphNode("b");
		GraphNode c = _getAGraphNode("c");
		GraphNode d = _getAGraphNode("d");
		GraphNode e = _getAGraphNode("e");
		GraphNode f = _getAGraphNode("f");

		a.neighbors = List.of(f);
		b.neighbors = List.of(f);
		c.neighbors = List.of(d);
		d.neighbors = List.of(b, a);
		//e.neighbors=List.of(d);

		List<GraphNode> listOfDependencies = new ArrayList<>();
		listOfDependencies.add(a);
		listOfDependencies.add(b);
		listOfDependencies.add(c);
		listOfDependencies.add(d);
		listOfDependencies.add(e);
		listOfDependencies.add(f);

		Set<String> buildOrder = getBuildOrder(listOfDependencies);

		for (String string : buildOrder) {
			System.out.print(string + " ");
		}


	}

	private GraphNode _getAGraphNode(String name) {
		GraphNode node = new GraphNode();
		node.val = name;
		//f
		return node;
	}



	private void printInOrder(TreeNode tree) {
		if (tree == null) {
			return;
		}

		printInOrder(tree.left);
		System.out.print(tree.val + " ");
		printInOrder(tree.right);
	}

}
