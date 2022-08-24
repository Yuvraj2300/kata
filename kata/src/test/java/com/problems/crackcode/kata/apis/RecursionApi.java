package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.problems.crackcode.kata.exceptions.KataException;

public class RecursionApi {

	public int calculateExponenet(int x, int y) {

		return calculateExponenetHelper(x, y, 1);
	}

	private int calculateExponenetHelper(int x, int y, int res) {
		if (y == 0) {
			return res;
		}
		return calculateExponenetHelper(x, --y, x * res);
	}


	@Test
	void testCacExponenet() throws Exception {
		int cal = calculateExponenet(2, 2);
		assertEquals(4, cal);
	}


	@Test
	void testCacExponenet_1() throws Exception {
		int cal = calculateExponenet(3, 2);
		assertEquals(9, cal);
	}

	@Test
	void testCacExponenet_2() throws Exception {
		int cal = calculateExponenet(2, 4);
		assertEquals(16, cal);
	}


	@Test
	void testCacExponenet_3() throws Exception {
		int cal = calculateExponenet(2, 10);
		assertEquals(1024, cal);
	}


	@Test
	void testCacExponenet_4() throws Exception {
		int cal = calculateExponenet(10, 2);
		assertEquals(100, cal);
	}


	int powerSumWays(int x, int n) {
		int y = (int) Math.sqrt(x);
		Integer ways = 0;
		return _getSumWays(y, n, 0, x, ways);
	}


	int _getSumWays(int a, int n, int sum, int sumToMntn, int ways) {

		if (a < 0) {
			return ways;
		}

		if (sum == sumToMntn) {
			sum = 0;
			ways += 1;
		} else {
			int temp = sum;
			sum += calculateExponenet(a, n);
			if (sum > sumToMntn) {
				sum = temp;
			}
		}
		return _getSumWays(--a, n, sum, sumToMntn, ways);
	}


	@Test
	void testPoerySumWays() throws Exception {
		int ways = powerSumWays(13, 2);
		assertEquals(1, ways);
	}


	@Test
	void testPoerSumWays_1() throws Exception {
		int ways = powerSumWays(10, 2);
		assertEquals(1, ways);
	}

	@Test
	void testPoerSumWays_2() throws Exception {
		int ways = powerSumWays(100, 2);
		assertEquals(2, ways);
	}

	@Test
	void testPoerSumWays_3() throws Exception {
		int ways = powerSumWays(100, 3);
		assertEquals(1, ways);
	}



	public List<String> crosswordPuzzle(List<String> crosswordAsList, String wordsList) {
		// Write your code here
		int i = crosswordAsList.size();
		int j = crosswordAsList.get(0).length();

		char[][] crossword = new char[i][j];

		int row = 0;
		while (row < crosswordAsList.size()) {
			for (int col = 0; col < crosswordAsList.get(row).length(); col++) {
				crossword[row][col] = crosswordAsList.get(row).charAt(col);
			}
			row++;
		}


		String[] words = wordsList.split(";");

		for (String word : words) {
			_updateGrid(crossword, word);
		}

		List<String> updatedPuzzle = new ArrayList<>();
		int newRow = 0;
		while (newRow < crossword.length) {
			for (int col = 0; col < crossword[newRow].length; col++) {
				updatedPuzzle.add(((Character) crossword[newRow][col]).toString());
			}
		}
		return updatedPuzzle;
	}



	private char[][] _updateGrid(char[][] crossword, String word) {

		int wordLtItr = 0;

		//check if the word fits vertically
		_checkHorizontalFit(crossword, word, wordLtItr, 0, 0);

		//check if the word fits horizontally
		_checkVerticalFit(crossword, word, wordLtItr, 0, 0);

		return crossword;
	}


	private void _checkHorizontalFit(char[][] crossword, String word, int wordLtItr, int i, int j) {

	}


	private void _checkVerticalFit(char[][] crossword, String word, int wordLtItr, int i, int j) {

	}



	@Test
	void testCrosswordPuzzle() throws Exception {
		List<String> crossword = new ArrayList<>();
		crossword.add("+-++++++++");
		crossword.add("+-++++++++");
		crossword.add("+-++++++++");
		crossword.add("+-----++++");
		crossword.add("+-+++-++++");
		crossword.add("+-+++-++++");
		crossword.add("+++++-++++");
		crossword.add("++------++");
		crossword.add("+++++-++++");
		crossword.add("+++++-++++");


		String words = "LONDON;DELHI;ICELAND;ANKARA";

		crosswordPuzzle(crossword, words);
	}



	public BigInteger superDigit(String n, int k) {
		StringBuilder sb = new StringBuilder();
		_getTheNumberToProcess(n, k, sb);

		String string = sb.toString();
		BigInteger bi = new BigInteger(string);
		BigInteger superDigit = _getSuperDigit(bi, new BigInteger("0"));
		System.out.println(superDigit);
		return superDigit;
	}

	private BigInteger _getSuperDigit(BigInteger bi, BigInteger sum) {
		BigInteger bi10 = new BigInteger("10");
		BigInteger remainingPart = bi.divide(bi10);
		BigInteger lastDigit = bi.mod(bi10);

		if (lastDigit.compareTo(bi) == 0 && remainingPart.compareTo(BigInteger.ZERO) == 0) {
			return bi;
		}

		bi = _decompose(bi, sum);
		return _getSuperDigit(bi, sum);
	}



	private BigInteger _decompose(BigInteger bi, BigInteger sum) {
		BigInteger bi10 = new BigInteger("10");
		BigInteger remainingPart = bi.divide(bi10);
		BigInteger lastDigit = bi.mod(bi10);

		if (lastDigit.compareTo(bi) == 0 && remainingPart.compareTo(BigInteger.ZERO) == 0) {
			return sum.add(lastDigit);
		}
		sum = sum.add(lastDigit);
		return _decompose(remainingPart, sum);
	}



	private void _getTheNumberToProcess(String n, int k, StringBuilder sb) {
		if (k == 0) {
			return;
		}

		k--;
		sb.append(n);
		_getTheNumberToProcess(n, k, sb);
	}


	@Test
	void testSuperDigit() throws Exception {
		BigInteger superDigit = superDigit("9875", 4);
		assertEquals(0, superDigit.compareTo(new BigInteger("8")));
	}

	@Test
	void testSuperDigit_1() throws Exception {
		BigInteger superDigit = superDigit("593", 10);
		assertEquals(0, superDigit.compareTo(new BigInteger("8")));
	}

	//	@Test
	//	void testSuperDigit_2() throws Exception {
	//		BigInteger superDigit = superDigit("3546630947312051453014172159647935984478824945973141333062252613718025688716704470547449723886626736", 100000);
	//		assertEquals(0, superDigit.compareTo(new BigInteger("5")));
	//	}

	//	/593 10/


	public String passwordCracker(List<String> passwords, String loginAttempt) {
		//		String[] passwords = new String[passwordsList.size()];
		//
		//		for (int i = 0; i < passwords.length; i++) {
		//			passwords[i] = passwordsList.get(i);
		//		}
		StringBuilder sb = new StringBuilder();
		for (String s : passwords) {
			char[] currentStringArray = s.toCharArray();
			StringBuilder smallerSb = new StringBuilder();

			try {
				StringBuilder smalerSb = _getWord(loginAttempt, currentStringArray, smallerSb, 0, 0);
				sb.append(smalerSb);
				sb.append(" ");
			} catch (Exception ex) {
				return ex.getMessage();
			}
		}


		return sb.toString();
	}

	private StringBuilder _getWord(String loginAttempt, char[] currentStringArray, StringBuilder sb, int i, int j) {

// @formatter:off
		if (//currentStringArray[i] != loginAttempt.charAt(j) ||
				i > currentStringArray.length || 
					j > loginAttempt.length()) {
			throw new KataException("WRONG COMBINATION");
		}
// @formatter:on

		if (j == currentStringArray.length) {
			return sb;
		} else if (currentStringArray[i] == loginAttempt.charAt(i)) {
			sb.append(loginAttempt.charAt(j));
			j++;
			i++;
		} else {
			j++;
		}

		return _getWord(loginAttempt.substring(1), currentStringArray, sb, i, j);
	}


	@Test
	void testPasswordCracker() throws Exception {
		List<String> passwords = new ArrayList<>();
		passwords.add("because");
		passwords.add("can");
		passwords.add("do");
		passwords.add("must");
		passwords.add("we");
		passwords.add("what");
		passwordCracker(passwords, "wedowhatwemustbecausewecan");
	}

}
