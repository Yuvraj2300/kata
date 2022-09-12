package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.Vector;

import org.junit.jupiter.api.Test;

import com.structures.kata.GraphNode;


public class GraphApi {

	private List<Integer> bfsOnGraph(GraphNode start) {
		List<Integer> listToReturn = new LinkedList<Integer>();

		Queue<GraphNode> processQueue = new LinkedList<>();

		start.setVisited(true);

		processQueue.add(start);

		while (!processQueue.isEmpty()) {
			GraphNode poll = processQueue.poll();
			listToReturn.add(poll.getIntVal());


			for (GraphNode neighbor : poll.getNeighbors()) {
				if (!neighbor.isVisited())
					processQueue.add(neighbor);
			}
		}

		return listToReturn;
	}





	@Test
	void testBfsOnGraph() {
		GraphNode start = new GraphNode(2, null);

		LinkedList<GraphNode> neighborsOfNode2 = new LinkedList<GraphNode>();
		neighborsOfNode2.add(start);
		GraphNode node2 = new GraphNode(1, neighborsOfNode2);

		LinkedList<GraphNode> neighborsOfNode3 = new LinkedList<GraphNode>();
		GraphNode node3 = new GraphNode(3, neighborsOfNode3);

		LinkedList<GraphNode> neighborsOfNode1 = new LinkedList<GraphNode>();
		neighborsOfNode1.add(node2);
		neighborsOfNode1.add(start);
		GraphNode node1 = new GraphNode(0, neighborsOfNode1);

		LinkedList<GraphNode> neighborsOfStartNode = new LinkedList<GraphNode>();
		neighborsOfStartNode.add(node1);
		neighborsOfStartNode.add(node3);
		start.setNeighbors(neighborsOfStartNode);


		List<Integer> bfsOrder = bfsOnGraph(start);
		System.out.println(bfsOrder);
	}





	private List<Integer> dfsOnGraph(GraphNode start) {
		List<Integer> listToReturn = new LinkedList<Integer>();

		Stack<GraphNode> processStack = new Stack<>();

		start.setVisited(true);

		processStack.add(start);

		while (!processStack.isEmpty()) {
			GraphNode poll = processStack.pop();
			listToReturn.add(poll.getIntVal());


			for (GraphNode neighbor : poll.getNeighbors()) {
				if (!neighbor.isVisited())
					processStack.add(neighbor);
			}
		}

		return listToReturn;
	}


	@Test
	void testDfsOnGraph() {
		GraphNode start = new GraphNode(2, null);

		LinkedList<GraphNode> neighborsOfNode2 = new LinkedList<GraphNode>();
		neighborsOfNode2.add(start);
		GraphNode node2 = new GraphNode(1, neighborsOfNode2);

		LinkedList<GraphNode> neighborsOfNode3 = new LinkedList<GraphNode>();
		GraphNode node3 = new GraphNode(3, neighborsOfNode3);

		LinkedList<GraphNode> neighborsOfNode1 = new LinkedList<GraphNode>();
		neighborsOfNode1.add(node2);
		neighborsOfNode1.add(start);
		GraphNode node1 = new GraphNode(0, neighborsOfNode1);

		LinkedList<GraphNode> neighborsOfStartNode = new LinkedList<GraphNode>();
		neighborsOfStartNode.add(node1);
		neighborsOfStartNode.add(node3);
		start.setNeighbors(neighborsOfStartNode);


		List<Integer> dfsOrder = dfsOnGraph(start);
		System.out.println(dfsOrder);
	}


}
