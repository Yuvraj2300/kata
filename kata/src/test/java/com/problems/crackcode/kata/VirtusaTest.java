package com.problems.crackcode.kata;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

public class VirtusaTest {

	// @formatter:off
	/**
	 * @author yuvraj1.sharma
	 *
	 *         Write a program which takes two strings as input from the user (str1
	 *         and str2). This program should print two strings as output (op1 and
	 *         op2).
	 * 
	 *         op1 should contain all the characters which are present in str1 but
	 *         NOT present in str2.
	 * 
	 *         op2 should contain all the characters which are present in str2 but
	 *         NOT present in str1.
	 * 
	 * 			STR1	STR2			OP1		OP2
	 * 			ABC		BC				A		<null>
	 * 			BC		BANGLORE		C		ANGLORE
	 */
	// @formatter:on
	public void stringReplacementQuestion(String s1, String s2) {
		Map<Character, Integer> mapofCharAndOccurnceForS1 = new LinkedHashMap<>();
		Map<Character, Integer> mapofCharAndOccurnceForS2 = new LinkedHashMap<>();
		Map<String, Map<Character, Integer>> mapOfStringAndMapOfCharAndOccurnce = new HashMap<>();

		for (char c : s1.toCharArray()) {
			_updateMapWithCharOccurnces(mapofCharAndOccurnceForS1, c);
			mapOfStringAndMapOfCharAndOccurnce.put(s1, mapofCharAndOccurnceForS1);
		}

		for (char c : s2.toCharArray()) {
			_updateMapWithCharOccurnces(mapofCharAndOccurnceForS2, c);
			mapOfStringAndMapOfCharAndOccurnce.put(s2, mapofCharAndOccurnceForS2);
		}

		Map<Character, Integer> mapForS1 = mapOfStringAndMapOfCharAndOccurnce.get(s1);
		Map<Character, Integer> mapForS2 = mapOfStringAndMapOfCharAndOccurnce.get(s2);

		StringBuilder op1Bldr = new StringBuilder();
		StringBuilder op2Bldr = new StringBuilder();

		for (Map.Entry<Character, Integer> entry : mapForS1.entrySet()) {
			if (!mapForS2.containsKey(entry.getKey())) {
				op1Bldr.append(entry.getKey());
			}
		}

		for (Map.Entry<Character, Integer> entry : mapForS2.entrySet()) {
			if (!mapForS1.containsKey(entry.getKey())) {
				op2Bldr.append(entry.getKey());
			}
		}
		System.out.println("op1: " + op1Bldr.toString());
		System.out.println("op2: " + op2Bldr.toString());
	}

	private void _updateMapWithCharOccurnces(Map<Character, Integer> mapOfCharAndOccurnce, char c) {
		if (!mapOfCharAndOccurnce.containsKey(c)) {
			mapOfCharAndOccurnce.put(c, 1);
		} else {
			mapOfCharAndOccurnce.put(c, mapOfCharAndOccurnce.get(c) + 1);
		}
	}


	@Test
	void testStringReplacement() throws Exception {
		stringReplacementQuestion("ABC", "BC");

		System.out.println();
		System.out.println("################");
		System.out.println("################");
		System.out.println();

		stringReplacementQuestion("BC", "BANGLORE");
	}

}
