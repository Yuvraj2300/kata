package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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




	public TreeNode getTreeFromSortedArray(int[] arr) {
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

}
