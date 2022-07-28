package com.problems.crackcode.kata.sandbox;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

/**
 * @author yuvraj1.sharma
 *
 *         Java program to find count of each capital letter from string st =
 *         “abcAABBCCACBBCAacbbbccaaAAC”
 * 
 *         Java program to check whether two strings are anagram or not
 * 
 *         Java 8 program to merge two lists into a third list with unique
 *         elements and in descending order
 * 
 *         Java 8 program to find third largest element from a list
 *
 *
 */
public class Sandbox {
	//abcAABBCCACBBCAacbbbccaaAAC


	public int numberOfCapitalLetters(String input) {
		char[] strAsChar = input.toCharArray();

		int countForUpperCase = 0;
		int charSeen = 0;
		for (int i = 0; i < strAsChar.length; i++) {
			if (strAsChar[i] >= 68 && strAsChar[i] <= 122) {
				if (charSeen != strAsChar[i]) {
					charSeen = strAsChar[i];
					countForUpperCase++;
				}
			}
		}

		System.err.println("Number of upper case characters in the given string : " + countForUpperCase);
		return countForUpperCase;
	}


	@Test
	void testNumberOfUpperCaseChars() throws Exception {
		int number = numberOfCapitalLetters("abcAABBCCACBBCAacbbbccaaAAC");
	}


	/**
	 * @author yuvraj1.sharma
	 *
	 * 
	 *         SUB <--> USB
	 * 
	 */
	public boolean checkAnagram(String s1, String s2) {

		if (s1.length() != s2.length()) {
			return false;
		}


		char[] s1AsArray = s1.toCharArray();
		char[] s2AsArray = s2.toCharArray();

		Arrays.sort(s1AsArray);
		Arrays.sort(s2AsArray);

		for (int i = 0; i < s1AsArray.length; i++) {
			if (s1AsArray[i] != s2AsArray[i]) {
				return false;
			}
		}

		return true;
	}

	@Test
	void testCheckAnagram() throws Exception {
		boolean result = checkAnagram("sub", "usb");
		assertTrue(result);
	}


	public List<Integer> mergeTwoListUniqueAndDescending(List<Integer> l1, List<Integer> l2) {
		Set<Integer> l1AsSet = l1.stream().collect(Collectors.toSet());


		// @formatter:off
			Set<Integer> collectAsSet = l2.stream()
			.filter(val -> l1AsSet.contains(val))
			.collect(Collectors.toSet());
		// @formatter:on
		return collectAsSet.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());

	}



	@Test
	void testMergeTwoListUniqueAndDescending() throws Exception {
		List<Integer> op = mergeTwoListUniqueAndDescending(Lists.list(1, 1, 2, 3, 5), Lists.list(2, 5, 3, 6, 5));
		System.out.println(op);

	}


	public int findThirdLargest(List<Integer> l1) {

		//1,2,3,4,5,6
		// 	@formatter:off
		return l1.stream()
				.sorted(Comparator.reverseOrder())
				.distinct()
				.limit(2)
				.skip(1)
				.findFirst()
			.get();
 
		// @formatter:on

	}

}
