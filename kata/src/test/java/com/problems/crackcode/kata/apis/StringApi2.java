package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StringApi2 {

	@Test
	@DisplayName("Test Print Freq In Alph Order")
	void testPrintFreqInAlphOrder() {
		String op = printFrequencyInAlphOrder("aaabb");
		Assertions.assertEquals("a3b2", op);
	}

	String printFrequencyInAlphOrder(String s) {
		char[] sA = s.toCharArray();
		int[] cA = new int[123];
		int i = 0;

		while (i < sA.length) {
			cA[sA[i]]++;
			i++;
		}

		i = 0;
		StringBuilder sb = new StringBuilder();
		while (i < cA.length) {
			if (cA[i] > 0) {
				sb.append((char) i);
				sb.append(cA[i]);
			}
			i++;
		}

		return sb.toString();
	}



	@Test
	@DisplayName("Test Insert Character")
	void testInsertCharacter() {
		String updated = insertCharacter("yolo", new int[] { 1 });
		Assertions.assertEquals(updated, "y*olo");
	}


	@Test
	@DisplayName("Test Insert Character")
	void testInsertCharacter1() {
		String updated = insertCharacter("geeksforgeeks", new int[] { 1, 5, 7, 9 });
		Assertions.assertEquals(updated, "g*eeks*fo*rg*eekss");
	}


	@Test
	@DisplayName("Test Insert Character")
	void testInsertCharacter2() {
		String updated = insertCharacter("spacing", new int[] { 0, 1, 2, 3, 4, 5, 6 });
		Assertions.assertEquals(updated, "*s*p*a*c*i*n*g");
	}


	String insertCharacter(String s, int[] pos) {
		int i = 0;
		int j = 0;
		StringBuilder sb = new StringBuilder();
		char[] cA = s.toCharArray();
		while (i < cA.length) {
			if (j < pos.length && pos[j] == i) {
				sb.append('*');
				j++;
			} else {
				sb.append(cA[i]);
				i++;
			}
		}
		return sb.toString();
	}
}
