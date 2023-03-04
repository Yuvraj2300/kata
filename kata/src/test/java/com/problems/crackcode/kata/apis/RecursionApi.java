package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

	class LoginStringState {
		String loginString;
		int loginStringLength = 0;
		int stringParsedTill = 0;
	}

	public String passwordCracker(List<String> passwords, String loginAttempt) {
		StringBuilder sb = new StringBuilder();
		LoginStringState loginStringState = new LoginStringState();
		loginStringState.loginString = loginAttempt;
		loginStringState.loginStringLength = loginAttempt.length();

		sb = _getWord(loginStringState, passwords, sb, 0, 0);
		return sb.toString();
	}


	private StringBuilder _getWord(LoginStringState loginAttempt, List<String> strings, StringBuilder sb, int i, int j) {
		if (i == loginAttempt.loginStringLength) {
			return sb;
		}

		for (String string : strings) {
			if (j >= string.length()) {
				continue;
			}

			if (loginAttempt.loginString.charAt(i) != string.charAt(j)) {
				continue;
			}

			_updatePasswordBuilder(loginAttempt, string, sb, i, j);

		}

		if (loginAttempt.stringParsedTill != loginAttempt.loginStringLength) {
			sb.append(" ");
		}


		return _getWord(loginAttempt, strings, sb, loginAttempt.stringParsedTill, j);
	}




	private void _updatePasswordBuilder(LoginStringState loginAttempt, String string, StringBuilder sb, int i, int j) {
		StringBuilder internalSb = new StringBuilder();
		boolean appendFlag = false;
		while (j < string.length()) {
			if (loginAttempt.loginString.charAt(i) == string.charAt(j)) {
				internalSb.append(loginAttempt.loginString.charAt(i));
				i++;

				if (j == string.length() - 1) {
					appendFlag = true;
				}
			}
			j++;
		}

		if (appendFlag) {
			sb.append(internalSb);
			loginAttempt.stringParsedTill = i;
		}
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

		String password = passwordCracker(passwords, "wedowhatwemustbecausewecan");
		assertEquals("we do what we must because we can", password);
	}


	@Test
	void testPasswordCracker_1() throws Exception {
		List<String> passwords = new ArrayList<>();
		passwords.add("abra");
		passwords.add("ka");
		passwords.add("dabra");

		String password = passwordCracker(passwords, "kaabra");
		assertEquals("ka abra", password);
	}

	@Test
	void testPasswordCracker_2() throws Exception {
		List<String> passwords = new ArrayList<>();
		passwords.add("ab");
		passwords.add("ba");

		String password = passwordCracker(passwords, "aba");
		assertEquals("WRONG PASSWORD", password);
	}





	public static long stoneDivision(long n, List<Long> s) {
		// Write your code here
		System.out.println(n);
		System.out.println(s);

		long divisionWays = 0;
		for (Long splitter : s) {
			divisionWays = _findDecomposeWay(n, splitter, divisionWays);
		}

		return divisionWays;
	}


	private static long _findDecomposeWay(long n, Long splitter, long ways) {
		if (n == splitter) {
			return ways;
		}

		long quotient = n / splitter;
		long remainder = n % splitter;
		if (remainder == 0) {
			n = quotient;
		} else {
			return ways;
		}
		ways++;
		return _findDecomposeWay(n, splitter, ways);
	}



	@Test
	void testStoneDivision() throws Exception {
		long divisionSteps = stoneDivision(12, List.of(2L, 3L, 4L));
		assertEquals(4, divisionSteps);
	}


	static int[] kFactorization_array(int number, int[] factors) {
		Arrays.sort(factors);
		List<Integer> result = new ArrayList<>();
		minFactor(number, factors, factors.length - 1, result);
		if (result.size() == 0) {
			return new int[] { -1 };
		} else {
			result.add(1);
			return result.stream().mapToInt(k -> k).sorted().toArray();
		}
	}

	private static void minFactor(int number, int[] factors, int i, List<Integer> result) {
		if (number == 1) {
			return;
		}
		if (i < 0) {
			result.clear();
			return;
		}
		if (number % factors[i] == 0) {
			result.add(number);
			minFactor(number / factors[i], factors, i, result);
		} else {
			minFactor(number, factors, i - 1, result);
		}
	}

	class ListComparator<T extends Comparable<T>> implements Comparator<List<T>> {

		@Override
		public int compare(List<T> o1, List<T> o2) {
			for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
				int c = o1.get(i).compareTo(o2.get(i));
				if (c != 0) {
					return c;
				}
			}
			return Integer.compare(o1.size(), o2.size());
		}

	}

	public List<Integer> kFactorization(int n, List<Integer> A) {

		List<List<Integer>> factorSeries = new ArrayList<List<Integer>>();

		List<Integer> toAdd = null;
		int i = A.size() - 1;
		while (i >= 0) {
			toAdd = new ArrayList<Integer>();
			_minFactors(n, toAdd, A, i);
			toAdd.add(1);
			Collections.sort(toAdd);
			factorSeries.add(toAdd);
			i--;
		}


		Collections.sort(factorSeries, new ListComparator<>());
		System.out.println(factorSeries);
		return factorSeries.get(0);

	}

	private void _minFactors(int n, List<Integer> toReturn, List<Integer> factors, int i) {
		if (n == 1) {
			return;
		}

		if (i > factors.size() - 1) {
			toReturn.clear();
			return;
		}

		Integer currFactor = factors.get(i);
		if (n % currFactor == 0) {
			toReturn.add(n);
			_minFactors(n / currFactor, toReturn, factors, i);
		} else {
			i++;
			_minFactors(n, toReturn, factors, i);
		}
	}

	@Test
	void testKFactroizations() throws Exception {
		List<Integer> list = new ArrayList<>();
		list.add(2);
		list.add(3);
		list.add(4);
		List<Integer> op = kFactorization(12, list);
		//		int[] factors = { 2, 3, 4 };
		//		int[] oparr = kFactorization_array(12, factors);
		System.out.println(op);
	}


	@Test
	void testArraysByR() {
		int[] a = { 1, 2, 3, 4 };
		arraysByR(a, 2);
	}


	void arraysByR(int[] a, int r) {
		int[] data = new int[r];
		int idx = 1;
		int s = 0;
		int e = a.length - 1;


		recurPrint(r, data, idx, s, e, a);

	}

	private static void recurPrint(int r, int[] data, int idx, int s, int e, int[] a) {
		if (s == a.length - 1 || s > a.length) {
			return;
		}

		if (idx == r) {
			for (int i : data)
				System.out.print(i + ", ");

			System.out.println();
			recurPrint(r, data, 1, s, e, a);
		}

		data[0] = a[s];
		if (s < e) {
			data[idx] = a[e];
			recurPrint(r, data, ++idx, s, --e, a);
		} else {
			recurPrint(r, data, 1, ++s, a.length - 1, a);
		}
	}


	@Test
	void testPrintCombinations() {
		printSeq(3, 2);
	}


	@Test
	void testPrintCombinations1() {
		printSeq(5, 5);
	}



	@Test
	void testPrintCombinaxtions2() {
		printSeq(5, 3);
	}


	@Test
	void testPrintCombinaxtions3() {
		printSeq(7, 3);
	}




	void printSeq(int n, int k) {

		int[] a = new int[k];

		int len = 0;
		_printSeqHelper(n, k, a, len);
	}

	private static void _printSeqHelper(int n, int k, int[] a, int len) {
		if (len == k) {
			System.out.println();
			Arrays.stream(a).forEach(e -> System.out.print(e + " "));
			return;
		}

		//natural nums to be filled
		int i = len == 0 ? 1 : a[len - 1] + 1;
		len++;
		while (i <= n) {
			a[len - 1] = i;
			_printSeqHelper(n, k, a, len);
			i++;
		}
	}


}
