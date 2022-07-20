package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

/**
 * @author yuvraj1.sharma
 * @implNote From chapter of string & array prblems from book : Cracking the
 *           Code Interview
 */
public class StringNdArrays {


	/**
	 * @author yuvraj1.sharma
	 *
	 * @implNote <b>Question</b> : Is Unique: Implement an algorithm to determine if
	 *           a string has all unique characters. What if you cannot use
	 *           additional data structures ?
	 * 
	 * @implSpec Assuming that the string being passed is consisting of ASCII
	 *           characters only and not UNICODE Chars(If UNICODE chars are passed
	 *           that would mean increasing the storage space) <br>
	 *           <p>
	 *           Another assumption being that capital letters & small letters are
	 *           equal <br>
	 *           <p>
	 *           Time Complexity : O(n)
	 */
	public boolean hasUniqueChars(String s) {

		Character charSeen = null;

		for (int i = 0; i < s.length(); i++) {

			Character currentChar = s.charAt(i);

			if (null == charSeen) {
				charSeen = currentChar;
				continue;
			}

			if (charSeen.compareTo(currentChar) == 0) {
				return false;
			}

			charSeen = currentChar;
		}
		return true;
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 * @implNote Here, we are essentially storing the ASCII value of the char in an
	 *           array and then checking if the same place/index in the array is
	 *           filled or not. <br>
	 *           <p>
	 *           Time Complexity : O(n)
	 */
	public boolean hasUniqueChars_approach2(String s) {
		int lengthOfString = s.length();

		if (lengthOfString > 128) {
			return false;
		}

		boolean[] flags = new boolean[128];

		for (int i = 0; i < lengthOfString; i++) {
			int currentCharVal = s.charAt(i);

			if (flags[currentCharVal]) {
				return false;
			}

			flags[currentCharVal] = true;

		}

		return true;
	}


	@Test
	void testUniqueChars() {
		boolean flag = hasUniqueChars("String");
		boolean flag2 = hasUniqueChars_approach2("String");
		assertTrue(flag);
		assertTrue(flag2);
	}

	@Test
	void testUniqueChars_1() {
		boolean flag = hasUniqueChars("Sttring");
		boolean flag2 = hasUniqueChars_approach2("Sttring");
		assertFalse(flag);
		assertFalse(flag2);
	}


	@Test
	void testUniqueChars_2() {
		boolean flag = hasUniqueChars("Sttrring");
		boolean flag2 = hasUniqueChars_approach2("Sttrring");
		assertFalse(flag);
		assertFalse(flag2);
	}

	@Test
	void testUniqueChars_3() {
		boolean flag = hasUniqueChars("");
		boolean flag2 = hasUniqueChars_approach2("Yuvraj");
		assertTrue(flag);
		assertTrue(flag2);
	}

	@Test
	void testUniqueChars_4() {
		boolean flag = hasUniqueChars("Yuvvraj");
		boolean flag2 = hasUniqueChars_approach2("Yuvvraj");
		assertFalse(flag);
		assertFalse(flag2);
	}


	@Test
	void testUniqueChars_5() {
		boolean flag = hasUniqueChars("attention");
		boolean flag2 = hasUniqueChars_approach2("attention");
		assertFalse(flag);
		assertFalse(flag2);
	}


	@Test
	void testUniqueChars_6() {
		boolean flag = hasUniqueChars("Aattention");
		boolean flag2 = hasUniqueChars_approach2("Aattention");
		assertFalse(flag);
		assertFalse(flag2);
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 */
	public boolean checkPermutation_Simplistic(String s1, String s2) {
		if (s1.length() == s2.length()) {
			return true;
		}

		//sort check
		String sortedS1 = _sortString(s1);
		String sortedS2 = _sortString(s2);

		if (!sortedS1.equals(sortedS2)) {
			return false;
		}

		return true;
	}



	private String _sortString(String s) {
		char[] charArray = s.toCharArray();
		Arrays.sort(charArray);
		return new String(charArray);
	}



	@Test
	void checkPermutation() throws Exception {
		boolean flag1 = checkPermutation_Simplistic("god", "dog");
		assertTrue(flag1);


		boolean flag2 = checkPermutation_Simplistic("good", "dog");
		assertFalse(flag2);
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 * @implNote method to convert whitespace with %20 as an inplace
	 *           operation(stupidity ofcourse, that's why)
	 */
	public void urlify_inPlace(String phrase) {
		//e.g. : I am will -9 
		// I%20am%20will 

		//count whitespaces in the given string
		int numberOfWhitespaces = _countWhitespaceInString(phrase);

		//extra space needed in the array
		//using 3 as %20 has three characters in it
		int extraSpaceReqd = numberOfWhitespaces * 3;

		char[] updatedString = new char[phrase.length() + extraSpaceReqd];

		//start from the end of the given phrase and then fill the new char array and convert to string
		int indxLt = phrase.length() - 1;
		int emptyIndexHelper = 0;

		for (int i = indxLt; i >= 0; i--) {

			if (phrase.charAt(i) == ' ') {
				updatedString[indxLt + extraSpaceReqd - emptyIndexHelper] = '0';
				emptyIndexHelper++;
				updatedString[indxLt + extraSpaceReqd - emptyIndexHelper] = '2';
				emptyIndexHelper++;
				updatedString[indxLt + extraSpaceReqd - emptyIndexHelper] = '%';
				emptyIndexHelper++;
			} else {
				updatedString[indxLt + extraSpaceReqd - emptyIndexHelper] = phrase.charAt(i);
				emptyIndexHelper++;
			}
		}

		//to print this madness
		for (char c : updatedString) {
			System.out.print(c);
			//			System.out.print(" ");
		}
	}



	private int _countWhitespaceInString(String phrase) {
		int whiteSpaceCount = 0;

		char[] stringAsCharArray = phrase.toCharArray();

		for (char c : stringAsCharArray) {
			if (c == ' ') {
				whiteSpaceCount++;
			}
		}
		return whiteSpaceCount;
	}


	@Test
	void testUrlify() throws Exception {
		urlify_inPlace("I am will");
		urlify_inPlace("Mr. John Smith");
		urlify_inPlace("Hey how arey ya ?");
	}


	/**
	 * @author yuvraj1.sharma
	 *
	 * @implNote I just got to know that palindromes have at most one character that
	 *           has odd occurrence
	 */
	public boolean permutationOfPalindrome(String phrase) {
		Map<Character, Integer> mapOfCharacterAndOccurrence = new HashMap<>();
		char[] charArray = phrase.toLowerCase().toCharArray();

		for (char c : charArray) {
			if (c == ' ') {
				continue;
			}

			if (!mapOfCharacterAndOccurrence.containsKey(c)) {
				mapOfCharacterAndOccurrence.put(c, 1);
				continue;
			}

			Integer ocurrence = mapOfCharacterAndOccurrence.get(c);
			mapOfCharacterAndOccurrence.put(c, ocurrence + 1);
		}


		int oddCharCount = 0;

		for (Map.Entry<Character, Integer> entry : mapOfCharacterAndOccurrence.entrySet()) {
			if (entry.getValue() % 2 == 1) {
				oddCharCount++;
			}
		}

		if (oddCharCount > 1) {
			return false;
		}

		return true;
	}



	@Test
	void testPermutationPalindrome() throws Exception {
		//TACO CAT
		assertTrue(permutationOfPalindrome("Tact Coa"));
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 *         One Away: There are three types of edits that can be performed on
	 *         strings: insert a character, remove a character, or replace a
	 *         character. Given two strings, write a function to check if they are
	 *         one edit (or zero edits) away. EXAMPLE pale, ple -> true pales, pale
	 *         -> true pale, bale -> true pale, bae -> false
	 */
	public boolean oneAway(String originalString, String modifiedString) {
		if (originalString.length() == modifiedString.length()) {
			int changes = _checkForChangesInTheStrings(originalString, modifiedString);
			if (changes > 1) {
				return false;
			}
		} else if (originalString.length() + 1 == modifiedString.length()) {
			return _checkForOnlyOneReplacement(originalString, modifiedString, "ONE_REPLACE");
		} else if (originalString.length() - 1 == modifiedString.length()) {
			return _checkForOnlyOneReplacement(originalString, modifiedString, "ONE_ADD");
		}

		return true;
	}



	private boolean _checkForOnlyOneReplacement(String s1, String s2, String scenario) {
		//determing the shorter string
		if (!(s1.length() < s2.length())) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
		}

		int indexForReplacement = 0;

		for (int i = 0; i < s1.length(); i++) {

			if (!(indexForReplacement > 0)) {
				if (s1.charAt(i) != s2.charAt(i)) {
					indexForReplacement++;
				}
			} else {
				if (s1.charAt(i) != s2.charAt(indexForReplacement)) {
					indexForReplacement++;
				}
			}
		}

		if (indexForReplacement > 1) {
			return false;
		} else if (indexForReplacement == 1) {
			return true;
		} else if (indexForReplacement == 0) {
			return true;
		} else {
			return false;
		}
	}

	private String _determineShorterString(String s1, String s2) {
		if (s1.length() < s2.length()) {
			return s1;
		} else {
			return s2;
		}
	}



	private int _checkForChangesInTheStrings(String s1, String s2) {
		int changeCount = 0;

		if (!(s1.length() < s2.length())) {
			String temp = s1;
			s1 = s2;
			s2 = temp;
		}

		for (int i = 0; i < s1.length(); i++) {
			if (s1.charAt(i) != s2.charAt(i)) {
				changeCount++;
			}
		}
		return changeCount;
	}


	@Test
	void testOneAwayOneEditInString() throws Exception {
		assertTrue(oneAway("pale", "ple"));
	}


	@Test
	void testOneAwayOneEditInString_1() throws Exception {
		assertTrue(oneAway("pale", "pales"));
	}



	@Test
	void testOneAwayOneEditInString_2() throws Exception {
		assertFalse(oneAway("pale", "bae"));
	}

	@Test
	void testOneAwayOneEditInString_3() throws Exception {
		assertTrue(oneAway("pale", "bale"));
	}

	@Test
	void testOneAwayOneEditInString_4() throws Exception {
		assertTrue(oneAway("yuvraj", "yuvaraj"));
	}


	@Test
	void testOneAwayOneEditInString_5() throws Exception {
		assertFalse(oneAway("yuvraj", "buvaraj"));
	}
}




