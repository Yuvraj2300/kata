package com.structures.kata;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GraphNode {
	public String val = "a_node";
	private int intVal;
	public boolean visited = false;
	public List<GraphNode> neighbors = new ArrayList<>();



	public GraphNode(List<GraphNode> neighbors) {
		this.neighbors = neighbors;
	}

	@Override
	public String toString() {
		return "GrahNode : [ val : " + this.val + ", neighbors : [" + this.neighbors + "], visited : " + this.visited + "]";
	}

	public GraphNode(int intVal, List<GraphNode> neighbors) {
		super();
		this.intVal = intVal;
		this.neighbors = neighbors;
	}
}
