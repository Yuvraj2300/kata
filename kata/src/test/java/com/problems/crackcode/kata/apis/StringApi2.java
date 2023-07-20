package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

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



	@Test
	@DisplayName("Test Get Min Flips")
	void testGetMinFlips() {
		int flips = getMinFlipsForAlternatingBinary("1001");
		Assertions.assertEquals(2, flips);
	}


	@Test
	@DisplayName("Test Get Min Flips")
	void testGetMinFlips1() {
		int flips = getMinFlipsForAlternatingBinary("0001010111");
		Assertions.assertEquals(2, flips);
	}


	int getMinFlipsForAlternatingBinary(String s) {
		int min = Math.min(_minFlipsWithExpected(s, '0'), _minFlipsWithExpected(s, '1'));
		return min;
	}



	private static int _minFlipsWithExpected(String s, char expected) {
		int i = 0;
		int flips = 0;
		char[] chars = s.toCharArray();

		while (i < chars.length) {
			if (chars[i] == expected) {
				flips++;
			}
			expected = expected == '0' ? '1' : '0';
			i++;
		}
		return flips;
	}


	@Test
	@DisplayName("Convert Number To Binary")
	void convertNumberToBinary() {
		String s = convertANumberToBinary(5);
		Assertions.assertEquals("101", s);
	}


	String convertANumberToBinary(int n) {
		int x = n;
		StringBuilder sb = new StringBuilder();

		while (x != 0) {
			int rem = x % 2;
			sb.append(rem);
			x = x / 2;
		}
		return sb.toString();
	}


	@Test
	@DisplayName("Print SubStrings")
	void printSubStrings() {
		String[] expected = { "a", "ab", "abc", "b", "bc", "c", };
		String[] substrings = substringsOfString("abc");
		Assertions.assertArrayEquals(expected, substrings);
	}


	@Test
	@DisplayName("Print SubStrings")
	void printSubStrings1() {
		substringsOfString("abcd");
	}


	String[] substringsOfString(String s) {
		List<String> toReturn = new LinkedList<>();

		char[] chars = s.toCharArray();
		int start = 0;
		while (start < chars.length) {
			int end = start;
			while (end < chars.length) {
				StringBuilder sb = new StringBuilder();
				int printer = start;
				while (printer <= end) {
					System.out.print(chars[printer]);
					sb.append(chars[printer]);
					printer++;
				}
				System.out.print(", ");
				toReturn.add(sb.toString());
				end++;
			}
			start++;
		}
		return toReturn.stream().toArray(str -> new String[str]);
	}
}
