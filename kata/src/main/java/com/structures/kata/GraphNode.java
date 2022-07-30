package com.structures.kata;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class GraphNode {
	public String val = "a_node";
	public boolean visited = false;
	public List<GraphNode> neighbors = new ArrayList<>();

	public GraphNode(List<GraphNode> neighbors) {
		this.neighbors = neighbors;
	}
}
