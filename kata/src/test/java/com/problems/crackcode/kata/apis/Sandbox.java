package com.problems.crackcode.kata.apis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class Sandbox {
	//String Compression : aabccccaaa = a2b1c4a3
	public String compress(String s) {

		int i = 0;

		StringBuilder sb = new StringBuilder();
		while (i < s.length()) {
			int count = 0;
			int windowEnd = 0;
			for (int j = i; j < s.length(); j++) {
				if (s.charAt(i) == s.charAt(j)) {
					count++;
				} else {
					windowEnd = j;
					break;
				}
			}

			sb.append(s.charAt(i));
			sb.append(count);
			if (windowEnd != 0) {
				i = windowEnd;
			} else {
				break;
			}

		}

		return sb.toString();
	}


	@Test
	void testCompression() throws Exception {
		String compress = compress("aabccccaaa");
		System.out.println(compress);
		assertEquals("a2b1c4a3", compress);
	}

}

