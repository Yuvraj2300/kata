package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import org.junit.jupiter.api.Test;

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

}
