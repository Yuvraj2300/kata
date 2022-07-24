package com.problems.crackcode.kata;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.HashMap;
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


	/**
	 * @author yuvraj1.sharma
	 *
	 *         Find the number of lexographical pairs in a string
	 */
	public int getLexPairs(String s) {
		int lexPairs = 0;
		int i = 0;

		while (i < s.length()) {
			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) < s.charAt(j)) {
					lexPairs++;
				}
			}
			i++;
		}

		System.out.println("Number of Lex Pairs Found : " + lexPairs);
		return lexPairs;
	}


	@Test
	void testGetLexPairs() throws Exception {
		assertEquals(6, getLexPairs("abcd"));
	}



	@Test
	void testGetLexPainrs_1() throws Exception {
		assertEquals(38, getLexPairs("cvfdghfsvsas"));
	}


	@Test
	void testGetLexPainrs_2() throws Exception {
		assertEquals(3, getLexPairs("hpwd"));
	}




	/**
	 * @author yuvraj1.sharma
	 *
	 *         Implement a method to perform basic string compression using the
	 *         counts of repeated characters. For example, the string aabcccccaaa
	 *         would become a2blc5a3. If the "compressed" string would not become
	 *         smaller than the original string, your method should return the
	 *         original string. You can assume the string has only uppercase and
	 *         lowercase letters (a - z).
	 */
	public String stringCompression(String s) {
		//aaabbccc == a3b2c3
		int i = 0;

		StringBuilder sb = new StringBuilder();

		while (i < s.length()) {
			int occurence = 1;
			int windowEnd = 0;

			for (int j = i + 1; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					occurence++;
				} else {
					windowEnd = j;
					break;
				}
			}

			sb.append(s.charAt(i));
			sb.append(occurence);

			if (!(windowEnd == 0)) {
				i = windowEnd;
			} else {
				break;
			}

		}

		if (sb.toString().length() < s.length()) {
			return sb.toString();
		} else {
			return s;
		}
	}


	@Test
	void testStringCompression() throws Exception {
		String op = stringCompression("aaabbccc");
		assertEquals("a3b2c3", op);
	}

	@Test
	void testStringCompression_1() throws Exception {
		String op = stringCompression("aabb");
		assertEquals("aabb", op);
	}

	@Test
	void testStringCompression_3() throws Exception {
		String op = stringCompression("aaabb");
		assertEquals("a3b2", op);
	}


	/**
	 * @author yuvraj1.sharma
	 * 
	 *         2 X 3 ===> 3 X 2
	 *
	 */
	public char[][] rotatePic(char[][] pic) {

		int origRows = 0;
		int origCols = 0;

		while (origRows < pic.length) {
			for (; origCols < pic[origRows].length; origCols++) {
				//empty just to increment columns
			}
			origRows++;
		}

		char[][] rotatedPic = new char[origCols][origRows];


		int i = 0;
		while (i < pic.length) {
			for (int j = 0; j < pic[i].length; j++) {
				rotatedPic[j][i] = pic[i][j];
			}
			i++;
		}

		return rotatedPic;
	}



	@Test
	void testRotatePic() throws Exception {
		char[][] pic = { { 'a', 'b' }, { 'c', 'd' }, { 'e', 'f' } };
		//		int length = pic[1].length;
		//		System.out.println(length);

		char[][] rotatedPic = rotatePic(pic);
		//		System.out.println(rotatedPic);
		//		assertEquals(1, rotatedPic.length);
		//		assertEquals(2, rotatedPic[1].length);
	}


	/**
	 * @author yuvraj1.sharma
	 *
	 *         correct impl
	 */
	public int[][] rotatePic(int[][] pic) {

		int origRows = 0;
		int origCols = 0;

		while (origRows < pic.length) {
			for (; origCols < pic[origRows].length; origCols++) {
				//empty just to increment columns
			}
			origRows++;
		}

		int[][] rotatedPic = new int[origCols][origRows];


		int i = pic.length - 1;

		while (i >= 0) {
			for (int j = 0; j < pic[i].length; j++) {
				rotatedPic[j][pic.length - 1 - i] = pic[i][j];
			}
			i--;
		}

		return rotatedPic;
	}


	@Test
	void testRotatePic_int() throws Exception {
		// @formatter:off
		
		int nums[][] = { 
					{ 1, 2, 3, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 10, 11, 12 },
					{ 13, 14, 15, 16 } 
				};
// @formatter:on

		int[][] rotatedPic = rotatePic(nums);

		System.out.println(rotatedPic);
		//		assertEquals(1, rotatedPic.length);
		//		assertEquals(2, rotatedPic[1].length);
	}



	/**
	 * @author yuvraj1.sharma
	 *
	 *         Zero Matrix: Write an algorithm such that if an element in an MxN
	 *         matrix is 0, its entire row and column are set to 0.
	 */
	public int[][] replaceWithZeros(int[][] pic) {
		int[][] newPic = new int[pic.length][pic[0].length];
		Map<Integer, Boolean> mapOfChangedRows = new HashMap<>();
		Map<Integer, Boolean> mapOfChangedCols = new HashMap<>();
		int j = 0;

		while (j < pic[0].length) {
			for (int i = 0; i < pic.length; i++) {
				if (pic[i][j] == 0) {
					_updateColumnToZero(newPic, j, mapOfChangedRows);
					_updateRowToZero(newPic, i, mapOfChangedCols);
				} else {
					if (!(mapOfChangedCols.containsKey(i) && mapOfChangedRows.containsKey(j))) {
						newPic[i][j] = pic[i][j];
					}
				}
			}
			j++;
		}


		return newPic;
	}



	private void _updateRowToZero(int[][] newPic, int row, Map<Integer, Boolean> mapOfChangedRows) {
		for (int col = 0; col < newPic[row].length; col++) {
			newPic[row][col] = 0;
		}
		mapOfChangedRows.put(row, true);
	}



	private void _updateColumnToZero(int[][] newPic, int cols, Map<Integer, Boolean> mapOfChangedCols) {
		for (int rows = 0; rows < newPic.length; rows++) {
			newPic[rows][cols] = 0;
		}
		mapOfChangedCols.put(cols, true);
	}


	@Test
	void testReplaceWithZeros() throws Exception {
		// @formatter:off
		int nums[][] = { 
					{ 1, 2, 0, 4 },
					{ 5, 6, 7, 8 },
					{ 9, 0, 11, 12 },
					{ 13, 14, 15, 16 } 
				};
		// @formatter:on

		int[][] updated = replaceWithZeros(nums);

	}


	/**
	 * @author yuvraj1.sharma
	 *
	 *         String Rotation: Assumeyou have a method i5Sub 5tring which checks if
	 *         one word is a substring of another. Given two strings, 51 and 52,
	 *         write code to check if 52 is a rotation of 51 using only one call to
	 *         i5SubString (e.g.,"waterbottle" is a rotation of"erbottlewat")
	 *
	 */
	public boolean isRotation(String sample, String suspect) {

		if (sample.length() == suspect.length()) {
			String stringUpdatedSample = sample + sample;

			return isSubstring(stringUpdatedSample, suspect);

		}

		return false;
	}



	private boolean isSubstring(String s1s1, String s2) {
		// TODO Auto-generated method stub
		int len = s2.length();
		for (int i = 0; i < len; i++) {
			System.out.println(s1s1.subSequence(i, i + len));
			if (s1s1.subSequence(i, i + len).equals(s2))
				return true;
		}
		return false;
	}


	@Test
	void testIsRotation() throws Exception {
		boolean rotationCheck = isRotation("waterbottle", "erbottlewat");
		assertTrue(rotationCheck);
	}

	@Test
	void testIsRotation_1() throws Exception {
		boolean rotationCheck = isRotation("yuvraj", "vrajyu");
		assertTrue(rotationCheck);
	}

	@Test
	void testIsRotation_2() throws Exception {
		boolean rotationCheck = isRotation("arpit", "pitar");
		assertTrue(rotationCheck);
	}

}




