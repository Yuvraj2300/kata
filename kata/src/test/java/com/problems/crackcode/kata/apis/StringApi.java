package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

public class StringApi {


	class Pairing {
		char a;
		char b;

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + getEnclosingInstance().hashCode();
			result = prime * result + Objects.hash(a, b);
			return result;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (!(obj instanceof Pairing))
				return false;
			Pairing other = (Pairing) obj;

			return a == other.a && b == other.b;
		}

		private StringApi getEnclosingInstance() {
			return StringApi.this;
		}


	}

	public int alternate(String input) {

		Set<Character> charsToRemove = new HashSet<>();

		//to remove when
		Character charToRemove = null;
		//case 1 :a character repeats it self consecutively
		for (int i = 0; i < input.length(); i++) {
			char currChar = input.charAt(i);

			int j = i + 1;
			if (j < input.length()) {
				if (currChar == input.charAt(j)) {
					charsToRemove.add(currChar);
				}
			}
			//case 2  : a character that does not occur again ?
		}

		System.out.println(charsToRemove);

		return 0;
	}

	@Test
	void testAlternate() {
		int count = alternate("abaacdabd");
		assertEquals(4, count);
	}

	private int getMaxVowels(String S1, String S2, int K) {
		//this is default OUTPUT. You can change it.
		int result = -404;

		//write your Logic here:
		Set<Character> vowelsSet = new HashSet<>();
		vowelsSet.add('a');
		vowelsSet.add('e');
		vowelsSet.add('i');
		vowelsSet.add('o');
		vowelsSet.add('u');

		int operationsPerformed = 0;
		int vowelsInS1 = 0;
		int i = 0;
		int j = 0;

		char[] s1Arr = S1.toCharArray();
		char[] s2Arr = S2.toCharArray();

		while (i < s1Arr.length) {
			if (!vowelsSet.contains(s1Arr[i])) {
				while (j < s2Arr.length && operationsPerformed <= K) {
					if (vowelsSet.contains(s2Arr[j])) {
						char temp = s2Arr[j];
						s2Arr[j] = s1Arr[i];
						s1Arr[i] = temp;

						vowelsInS1++;
						operationsPerformed++;
						break;
					}
					j++;
				}
			} else {
				vowelsInS1++;
			}
			i++;
		}
		return vowelsInS1;
	}


	@Test
	void testMaxVowels() throws Exception {
		int maxVowelsinS1 = getMaxVowels("abc", "aeiou", 5);
		assertEquals(3, maxVowelsinS1);
	}


	@Test
	void testMaxVowels_1() throws Exception {
		int maxVowelsinS1 = getMaxVowels("zzzzz", "zzzzz", 5);
		assertEquals(0, maxVowelsinS1);
	}


	@Test
	void testMaxVowels_2() throws Exception {
		int maxVowelsinS1 = getMaxVowels("abcdeigh", "aeiou", 3);
		assertEquals(7, maxVowelsinS1);
	}



	private Boolean checkPanagram(String input) {
		char[] charArray = input.toLowerCase().replaceAll(" ", "").toCharArray();
		boolean[] boolArray = new boolean[26];

		int i = 0;
		while (i < charArray.length) {

			if (boolArray[charArray[i] - 'a'] != true) {
				boolArray[charArray[i] - 'a'] = true;
			}

			i++;
		}

		for (boolean b : boolArray) {
			if (!b) {
				return false;
			}
		}

		return true;
	}



	@Test
	void testPanagram() throws Exception {
		String input = "The quick brown fox jumps over the lazy dog";
		assertTrue(checkPanagram(input));
	}



	@Test
	void testPanagram_1() throws Exception {
		String input = "The quick brown fox jumps over the dog";
		assertFalse(checkPanagram(input));
	}



	private void sortStringOfCharacters(String string) {
		int i = 0;
		int[] alphOcrr = new int[26];

		while (i < string.length()) {
			alphOcrr[string.charAt(i) - 'a']++;
			i++;
		}


		for (int j = 0; j < alphOcrr.length; j++) {
			if (alphOcrr[j] > 0) {
				for (int k = 0; k < alphOcrr[j]; k++) {
					System.out.print((char) (j + 'a') + ", ");
				}
			}
		}


	}

	private void sortStringOfCharactersMergeSort(String input) {
		char[] a = input.toCharArray();

		_mergeSortOnCharArray(a);
	}

	private void _mergeSortOnCharArray(char[] a) {
		int l = 0;
		int h = a.length - 1;
		_mergeSortOnCharHelper(a, l, h);
	}



	private void _mergeSortOnCharHelper(char[] a, int l, int h) {
		if (l < h) {
			int mid = (h + l) / 2;

			_mergeSortOnCharHelper(a, l, mid);
			_mergeSortOnCharHelper(a, mid + 1, h);
			//merge
			_merge(a, l, h, mid);

		}
	}

	private void _merge(char[] a, int l, int h, int mid) {
		int splitSize1 = mid - l + 1;
		int splitSize2 = h - mid;

		char[] split1 = new char[splitSize1];
		char[] split2 = new char[splitSize2];

		for (int i = 0; i < split1.length; i++) {
			split1[i] = a[l + i];
		}

		for (int i = 0; i < split2.length; i++) {
			split2[i] = a[mid + 1 + i];
		}

		int i = 0;
		int j = 0;

		int p = l;

		while (i < splitSize1 && j < splitSize2) {
			if (a[p] < split1[i]) {
				a[p] = split1[i];
				i++;
			} else {
				a[p] = split2[j];
				j++;
			}
			p++;
		}


		while (i < splitSize1) {
			a[p] = split1[i];
			p++;
			i++;
		}


		while (j < splitSize2) {
			a[p] = split2[j];
			p++;
			i++;
		}
	}



	@Test
	void testSortStringOfCharacters() throws Exception {
		sortStringOfCharacters("bbccdefbbaa");
		//		sortStringOfCharactersMergeSort("bbccdefbbaa");
	}



	private List<String> sortByWordsLength(List<String> listOfWords) {
		// TODO Auto-generated method stub
		listOfWords.sort(Comparator.comparing(s -> s.length()));
		return listOfWords;
	}



	@Test
	void testSortByWordsLength() throws Exception {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add("going");
		listOfWords.add("I");
		listOfWords.add("going");
		listOfWords.add("am");
		listOfWords.add("to");
		listOfWords.add("Santorni");

		List<String> sortedListOfWords = sortByWordsLength(listOfWords);
	}



	private int findInAnArrayOfStrings(String[] a, String toFind) {
		int i = 0;
		int j = 0;

		while (i < a.length) {
			if (!StringUtils.isBlank(a[i])) {
				if (toFind.equals(a[i]))
					return j;
			}

			j++;
			i++;
		}


		return -1;
	}



	@Test
	void testFindInAnArrayOfStrings() throws Exception {
		String toFind = "quiz";
		String[] array = org.assertj.core.util.Arrays.array("for", "", "quiz");
		int position = findInAnArrayOfStrings(array, toFind);
		assertEquals(2, position);
	}



	@Test
	void testFindInAnArrayOfStrings_1() throws Exception {
		String toFind = "quiz";
		String[] array = org.assertj.core.util.Arrays.array("for", "", "", "", "geeks", "ide", "", "practice", "", "", "quiz", "", "");
		int position = findInAnArrayOfStrings(array, toFind);
		assertEquals(10, position);
	}





	private int findInAnArrayOfStringsBinSearch(String[] a, String toFind) {
		int l = 0;
		int h = a.length - 1;

		return _binarySearchToFindTheRequiredString(a, toFind, l, h);
	}



	private int _binarySearchToFindTheRequiredString(String[] a, String toFind, int l, int h) {
		if (l < h) {
			int mid = (h + l) / 2;

			int midToUse = mid;

			while (StringUtils.isBlank(a[midToUse])) {
				midToUse--;
			}

			if (toFind.equals(a[midToUse])) {
				return midToUse;
			} else if (a[midToUse].length() < toFind.length()) {
				return _binarySearchToFindTheRequiredString(a, toFind, mid + 1, h);
			} else {
				return _binarySearchToFindTheRequiredString(a, toFind, l, mid);
			}

		} else {
			return -1;
		}
	}



	private String reverseString(String s) {
		int i = 0;
		int j = s.length() - 1;

		char[] a = s.toCharArray();

		while (i < j) {
			char temp = a[i];
			a[i] = a[j];
			a[j] = temp;
			i++;
			j--;
		}


		return String.valueOf(a);
	}



	@Test
	void testReverseString() throws Exception {
		String s = "geeks";
		String rev = reverseString(s);
		assertEquals("skeeg", rev);
	}



	@Test
	void testReverseString_1() throws Exception {
		String s = "yuvraj";
		String rev = reverseString(s);
		assertEquals("jarvuy", rev);
	}



	private List<String> printAllRotationsOfString(String string) {
		List<String> listToReturn = new ArrayList<>();

		char[] a = string.toCharArray();
		int rotations = string.length();
		int i = 0;

		while (i < rotations) {
			int j = 0;
			char firstChar = a[j];

			while (j < a.length) {
				if (j == a.length - 1)
					a[j] = firstChar;
				else
					a[j] = a[j + 1];

				j++;
			}
			listToReturn.add(String.valueOf(a));
			i++;
		}

		return listToReturn;
	}



	@Test
	void testPrintAllRotations() throws Exception {
		List<String> roations = printAllRotationsOfString("geeks");
		for (String rotation : roations) {
			System.out.println(rotation);
		}
	}


	@Test
	void testPrintAllRotations_1() throws Exception {
		List<String> roations = printAllRotationsOfString("abc");
		for (String rotation : roations) {
			System.out.println(rotation);
		}
	}



	private int countWordsInAGivenString(String[] words, String input) {
		int foundCount = 0;

		Set<String> setOfWords = Arrays.stream(words).map(String::toLowerCase).collect(Collectors.toSet());
		String[] tokens = input.split(" ");

		for (String token : tokens) {
			token = token.toLowerCase();
			token = token.replaceAll("[^A-Za-z0-9]", "");
			if (setOfWords.contains(token))
				foundCount++;
		}

		return foundCount;
	}



	@Test
	void testCountWordsInAGivenString() throws Exception {
		String[] words = { "welcome", "to", "geeks", "portal" };
		int foundWords = countWordsInAGivenString(words, "geeksforgeeks is a computer science portal for geeks.");
		assertEquals(2, foundWords);
	}



	@Test
	void testCountWordsInAGivenString_1() throws Exception {
		String[] words = { "Save", "Water", "Save", "Yourself" };
		int foundWords = countWordsInAGivenString(words, "Save");
		assertEquals(1, foundWords);
	}

}

