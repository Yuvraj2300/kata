package com.structures.kata;

import java.util.ArrayList;
import java.util.List;

public class TrieNode {
	public TrieNode next;
	public Character val;
	public List<TrieNode> listOfNodesAttached = new ArrayList<TrieNode>();

	public boolean wordComplete = false;


	public TrieNode() {
	}

	public TrieNode(boolean wordComplete) {
		super();
		this.wordComplete = wordComplete;
	}


	public TrieNode(Character val, boolean wordComplete) {
		super();
		this.val = val;
		this.wordComplete = wordComplete;
	}



	public TrieNode(TrieNode next, List<TrieNode> listOfChars, boolean wordComplete) {
		super();
		this.next = next;
		this.listOfNodesAttached = listOfChars;
		this.wordComplete = wordComplete;
	}




	@Override
	public String toString() {
		if (!this.listOfNodesAttached.isEmpty()) {
		// @formatter:off
		return 		
				"TrieNode : [val : " 
				+ val 
				+ ", wordComplete : "
				+ wordComplete 
				+ ", \n\t\t\t## next --> ["
				+ next
				+ ", \nwordsOriginating : " 
				+ listOfNodesAttached.size()
				+ ", \n\tlinkedWords : " 
				+ listOfNodesAttached
				+ "]";
	// @formatter:on
		} else {
		// @formatter:off
		return 		
				"TrieNode : [val : " 
				+ val 
				+ ", wordComplete : "
				+ wordComplete 
				+ ", \n\t## next --> ["
				+ next
				+ "]";
// @formatter:on
		}


	}
}
