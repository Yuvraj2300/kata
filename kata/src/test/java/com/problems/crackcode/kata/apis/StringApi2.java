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
		char[] a = s.toCharArray();
		List<String> lst = new LinkedList<>();

		int start = 0;
		while (start < a.length) {
			int end = start;
			while (end < a.length) {
				StringBuilder sb = new StringBuilder();
				int printer = start;
				while (printer <= end) {
					sb.append(a[printer]);
					printer++;
				}
				lst.add(sb.toString());
				end++;
			}
			start++;
		}
		return lst.stream().toArray(i -> new String[i]);
	}


	@Test
	@DisplayName("TestGetStringSequences")
	void testGetStringSequences() {
		List<String> op = getSequencesOfString("abc");
		System.out.println(op);
	}

	//aaa
	@Test
	@DisplayName("TestGetStringSequences")
	void testGetStringSequences1() {
		//abc
		List<String> op = getSequencesOfString("aaa");
		System.out.println(op);
	}

	private List<String> getSequencesOfString(String s) {
		String ans = "";
		List<String> lst = new ArrayList<>();
		_sequenceHelper(lst, ans, s);
		return lst;
	}

	private void _sequenceHelper(List<String> lst, String ans, String s) {
		if (s.length() == 0) {
			lst.add(new String(ans));
			return;
		}
		_sequenceHelper(lst, ans + s.charAt(0), s.substring(1));
		_sequenceHelper(lst, ans, s.substring(1));
	}



	@Test
	@DisplayName("Test Get Number Of Distinct Subsequences")
	void testGetNumberOfDistinctSubsequences() {
		int count = getNumberOfDistinctSubsequences("banana", "ban");
		Assertions.assertEquals(3, count);
	}


	@Test
	@DisplayName("Test Get Number Of Distinct Subsequences")
	void testGetNumberOfDistinctSubsequences1() {
		int count = getNumberOfDistinctSubsequences("geeksforgeeks", "ge");
		Assertions.assertEquals(6, count);
	}


	int getNumberOfDistinctSubsequences(String s, String t) {
		String ans = "";
		List<Integer> lst = new ArrayList<>();
		_helperNumOfDistSubSeq(s, t, ans, lst);
		return lst.size();
	}

	private void _helperNumOfDistSubSeq(String s, String t, String ans, List<Integer> lst) {
		if (s.isEmpty()) {
			if (ans.equals(t)) {
				lst.add(1);
			}
			return;
		} else if (ans.equals(t)) {
			lst.add(1);
			return;
		}

		_helperNumOfDistSubSeq(s.substring(1), t, ans + s.charAt(0), lst);
		_helperNumOfDistSubSeq(s.substring(1), t, ans, lst);

	}

	@Test
	@DisplayName("Test Get Number Of Distinct Subsequences")
	void testGetNumberOfDistinctSubsequences_check() {
		int count = getNumberOfDistinctSubsequencescheck("banana", "ban");
		Assertions.assertEquals(3, count);
	}

	private int getNumberOfDistinctSubsequencescheck(String s, String t) {
		String ans = "";
		List<Integer> lst = new ArrayList<>();
		return _helperNumOfDistSubSeq1(s, t, ans, 0);
	}

	private int _helperNumOfDistSubSeq1(String s, String t, String ans, int count) {
		if (s.isEmpty()) {
			if (ans.equals(t)) {
				count++;
			}
			return count;
		} else if (ans.equals(t)) {
			count++;
			return count;
		}

		count = _helperNumOfDistSubSeq1(s.substring(1), t, ans + s.charAt(0), count) + _helperNumOfDistSubSeq1(s.substring(1), t, ans, count);
		return count;
	}


	@Test
	@DisplayName("Test Find the Superstring")
	void testFindTheSuperstring() {
		//This is a weird question tbh but it is interesting
		// @formatter:off
		/**
		 * Given a set of n strings arr[], find the smallest string that contains each string in the given set as substring. We may assume that no string in arr[] is substring of another string.
		 * Examples:
		 *
		 * Input:  arr[] = {"geeks", "quiz", "for"}
		 * Output: geeksquizfor
		 *
		 * Input:  arr[] = {"catg", "ctaagt", "gcta", "ttca", "atgcatc"}
		 * Output: gctaagttcatgcatc
		 * */
		// @formatter:on
		//		String[] arr = { "catgc", "ctaagt", "gcta", "ttca", "atgcatc" };
		String[] arr = { "ab", "bc", "cd" };
		//		String[] arr = { "geeks", "quiz", "for" };

		int len = arr.length;

		System.out.println("The Shortest Superstring is " + findShortestSuperstring(arr));
	}

	static String str = "";

	private String findShortestSuperstring(String[] a) {
		int k = a.length;

		while (k > 1) {
			String combinedString = "";
			int maxOverlap = Integer.MIN_VALUE;
			int leftIdx = 0;
			int rightIdx = 0;
			for (int i = 0; i < a.length; i++) {
				for (int j = i + 1; j < a.length; j++) {
					StringOverlapWithMaxOverlap overlapObj = _findOverlap(a[i], a[j]);
					if (maxOverlap < overlapObj.currOverlap) {
						maxOverlap = overlapObj.currOverlap;
						combinedString = overlapObj.combinedString;

						leftIdx = i;
						rightIdx = j;
					}
				}
			}
			//reduce k once you have found overlaps for the first iteration
			k--;
			if (maxOverlap == Integer.MIN_VALUE) {
				//no overlap
				a[0] += a[k];
			} else {
				a[leftIdx] = combinedString;
				//	since the value at k might not have been used yet, so you must replace the used string
				//	at right index with the unused value.
				a[rightIdx] = a[k];
			}
		}

		return a[0];
	}

	private StringOverlapWithMaxOverlap _findOverlap(String s1, String s2) {
		int overlap = Integer.MIN_VALUE;

		String combinedString = "";
		int min = Math.min(s1.length(), s2.length());

		for (int i = 1; i < min; i++) {
			if (s1.substring(0, i).equals(s2.substring(s2.length() - i))) {
				if (i > overlap) {
					overlap = i;
					combinedString = s1 + s2.substring(i);
				}
			}
		}

		for (int i = 1; i < min; i++) {
			if (s1.substring(s1.length() - i).equals(s2.substring(0, i))) {
				if (i > overlap) {
					overlap = i;
					combinedString = s1 + s2.substring(i);
				}
			}
		}

		return new StringOverlapWithMaxOverlap(combinedString, overlap);
	}

	class StringOverlapWithMaxOverlap {
		public StringOverlapWithMaxOverlap(String combinedString, int currOverlap) {
			this.combinedString = combinedString;
			this.currOverlap = currOverlap;
		}

		String combinedString;
		int currOverlap;
	}

}

