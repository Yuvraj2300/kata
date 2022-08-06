package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

import org.junit.jupiter.api.Test;

import com.structures.kata.TrieNode;

public class TrieApi {
	public List<Integer> contacts(List<List<String>> queries) {
		System.out.println(queries);
		List<Integer> listToReturn = new ArrayList<>();
		TrieNode trie = null;

		for (List<String> query : queries) {

			if (query.get(0).equals("add")) {
				String requiredPartOfString = query.get(1);
				if (null == trie) {
					System.out.println("Adding a new value to trie");
					trie = _createNewTrie(requiredPartOfString);
				} else {
					System.out.println("Updating new value to trie");

					TrieNode trieRefToIterate = trie;
					TrieNode updatedTrieRef = _updateTrie(trieRefToIterate, requiredPartOfString);

					TrieNode nodeToUpdate = _findPointOfDirvergence(trieRefToIterate, requiredPartOfString);

					while (!(trieRefToIterate == nodeToUpdate)) {
						trieRefToIterate = trieRefToIterate.next;
					}

					if (null != updatedTrieRef) {
						trieRefToIterate.listOfNodesAttached.add(updatedTrieRef);
					}
				}
				System.out.println("Trie Updated : \n\t" + trie);
			} else if (query.get(0).equals("find")) {
				if (null == trie) {
					System.out.println("Trie has no data");
					listToReturn.add(0);
				}
				String requiredPartOfString = query.get(1);
				int hitsOfQuery = _findOccurancesOfSearchString(trie, requiredPartOfString);
				System.out.println("hits encountered :: " + hitsOfQuery);
				listToReturn.add(hitsOfQuery);
			}
		}
		return listToReturn;
	}




	private TrieNode _findPointOfDirvergence(TrieNode trie, String requiredPartOfString) {
		TrieNode previous = null;


		while (requiredPartOfString.length() > 0) {
			if (trie.val != requiredPartOfString.charAt(0)) {
				return previous;
			} else {
				previous = trie;
				trie = trie.next;
				requiredPartOfString = requiredPartOfString.substring(1);
			}
		}
		return previous;
	}


	private int _findOccurancesOfSearchString(TrieNode trie, String requiredPartOfString) {
		int count = 0;
		count = _findHits(trie, requiredPartOfString, count);
		return count;
	}


	private int _findHits(TrieNode trie, String requiredPartOfString, int count) {
		// @formatter:off
		char[] charArray = requiredPartOfString.toCharArray();
		
		for(char c : charArray) {
			String substring = requiredPartOfString.substring(1);
			if(trie.val==c) {
				if(substring.length()==0) {
					count++;
					return count+trie.listOfNodesAttached.size();
				}else {
					if(trie.next.val=='*') {
						count	+=	_checkLeft(trie, substring);
					}else {
						count +=_findHits(trie.next,substring,count);						
					}
					
				}
			}
		}
		// @formatter:on
		return count;
	}


	private int _checkLeft(TrieNode trie, String substring) {
		int leftCheck = 0;
		for (TrieNode node : trie.listOfNodesAttached) {
			leftCheck += _findHits(node, substring, leftCheck);
		}

		return leftCheck;
	}



	private TrieNode _updateTrie(TrieNode trie, String requiredPartOfString) {
		TrieNode trieNodeHead = trie;
		TrieNode trieNodeToIterate = trieNodeHead;
		return _updateTrie(trieNodeToIterate, requiredPartOfString, 0, requiredPartOfString.length());
	}


	private TrieNode _updateTrie(TrieNode trie, String string, int itr, int strLt) {

		if (itr == strLt) {
			return new TrieNode(true);
		}

		String substring = string.substring(1);
		if (null != trie.val && !trie.listOfNodesAttached.isEmpty()) {
			TrieNode toProcess = _processOnLevel(trie, substring, itr, strLt);
			//handle null
			if (null == toProcess) {
				return _updateTrie(trie.next, substring, ++itr, strLt);
			} else {
				return _updateTrie(toProcess, substring, ++itr, strLt);

			}
		} else if (null != trie.val && trie.val == string.charAt(0)) {
			return _updateTrie(trie.next, substring, ++itr, strLt);
		} else {
			TrieNode newNode = _createNewTrie(string, itr, strLt);

			return newNode;
		}

	}


	private TrieNode _processOnLevel(TrieNode trie, String string, int itr, int strLt) {
		TrieNode apply = null;
		if (!trie.listOfNodesAttached.isEmpty()) {

			BiFunction<TrieNode, Character, TrieNode> checkValOnNode = (node, charToCheck) -> {
				if (charToCheck == node.val) {
					return node;
				} else {
					return null;
				}
			};
			for (TrieNode node : trie.listOfNodesAttached) {
				apply = checkValOnNode.apply(node, string.charAt(0));
				if (null != apply) {
					return apply;
				} else {
					continue;
				}
			}
		}

		//default behav
		return apply;
	}


	private TrieNode _createNewTrie(String string) {
		System.out.println("creating trie for stirng  : " + string);

		return _createNewTrie(string, 0, string.length());
	}



	private TrieNode _createNewTrie(String str, int itr, int strLt) {

		if (itr == strLt) {
			return new TrieNode('*', true);
		}

		TrieNode trie = new TrieNode();

		trie.val = str.charAt(0);
		String substring = str.substring(1);

		trie.next = _createNewTrie(substring, ++itr, strLt);

		return trie;
	}



	@Test
	void testContacts() throws Exception {
		List<String> command0 = new ArrayList<>();
		command0.add("add");
		command0.add("ed");

		List<String> command1 = new ArrayList<>();
		command1.add("add");
		command1.add("eddie");

		List<String> command2 = new ArrayList<>();
		command2.add("add");
		command2.add("edward");

		List<String> c3 = new ArrayList<>();
		c3.add("find");
		c3.add("ed");

		List<String> c4 = new ArrayList<>();
		c4.add("add");
		c4.add("edwina");

		List<String> c5 = new ArrayList<>();
		c5.add("find");
		c5.add("edw");

		List<String> c6 = new ArrayList<>();
		c6.add("find");
		c6.add("a");


		List<List<String>> listOfCommands = new ArrayList<List<String>>();
		listOfCommands.add(command0);
		listOfCommands.add(command1);
		listOfCommands.add(command2);
		listOfCommands.add(c3);
		listOfCommands.add(c4);
		listOfCommands.add(c5);
		listOfCommands.add(c6);


		List<Integer> hits = contacts(listOfCommands);
		assertNotNull(hits);
		System.out.println(hits);
		assertEquals(3, hits.get(0));
		assertEquals(2, hits.get(1));
		assertEquals(0, hits.get(2));
	}


	@Test
	void testContacts_1() throws Exception {
		List<String> c1 = new ArrayList<>();
		c1.add("add");
		c1.add("yuvraj");

		List<String> c2 = new ArrayList<>();
		c2.add("add");
		c2.add("yuvaraj");

		List<String> c3 = new ArrayList<>();
		c3.add("find");
		c3.add("yuv");


		List<List<String>> listOfCommands = new ArrayList<List<String>>();
		listOfCommands.add(c1);
		listOfCommands.add(c2);
		listOfCommands.add(c3);

		List<Integer> hits = contacts(listOfCommands);
		assertNotNull(hits);
		System.out.println(hits);
		assertEquals(2, hits.get(0));
	}


	@Test
	void testContacts_2() throws Exception {

		List<String> c1 = new ArrayList<>();
		c1.add("add");
		c1.add("hac");

		List<String> c2 = new ArrayList<>();
		c2.add("add");
		c2.add("hackerrank");

		List<String> c3 = new ArrayList<>();
		c3.add("find");
		c3.add("hac");

		List<String> c4 = new ArrayList<>();
		c4.add("find");
		c4.add("hak");

		List<List<String>> listOfCommands = new ArrayList<List<String>>();
		listOfCommands.add(c1);
		listOfCommands.add(c2);
		listOfCommands.add(c3);
		listOfCommands.add(c4);


		List<Integer> hits = contacts(listOfCommands);
		assertNotNull(hits);
		System.out.println(hits);
		assertEquals(2, hits.get(0));
		assertEquals(0, hits.get(1));
	}


	@Test
	void testContacts_3() throws Exception {
		List<String> commands = new ArrayList<>();
		commands.add("add yuvraj");
		commands.add("add yuvaraj");
		commands.add("find y");

		List<List<String>> listOfCommands = new ArrayList<List<String>>();
		listOfCommands.add(commands);

		List<Integer> hits = contacts(listOfCommands);
		assertNotNull(hits);
		System.out.println(hits);
		assertEquals(2, hits.get(0));
	}
}
