package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

}
