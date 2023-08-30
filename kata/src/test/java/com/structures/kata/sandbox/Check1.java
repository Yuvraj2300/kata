package com.structures.kata.sandbox;

// @formatter:off
import java.util.Arrays; /**
 * I/P : [1,-1,-2,0,4,0,5,6,8,9]
 * O/p : [-1,-2,0,0,1,4,5,6,8,9]
 *
 *
 * I/P : [1,-2,-1,0,4,0,5,6,8,9]
 * O/p : [-2,-1,0,0,1,4,5,6,8,9]
 *
 * Arrange the list such that it follows the order <-ve elements><zeros><+ve elements>
 *
 * TC - 0(N)
 * SC - O(1)
 *
 *
 * */
// @formatter:on
public class Check1 {


	//1,-1,-2,0,4,0,5,6,8,9

	//-1,1,-2
	//-1,-2,1,0,4,0,5,6,8,9


	//1,-1,-2,0,4,0,5,6,8,9

	public static void main(String[] args) {
		int[] a = new int[] { 1, -1, -2, 0, 4, 0, 5, 6, 8, 9 };
		//		Check1.arrangeNegPos(a);

		//		Arrays.stream(a).forEach(i -> System.out.print(i + ", "));

		// @formatter:off
		/**
		 *
		 * Given a string s, find the length of the longest substring without repeating characters.
		 *
		 * Input: s = "abcabcbb"
		 * Output: 3
		 * Explanation: The answer is "abc", with the length of 3.
		 *
		 * Input: s = "bbbbb"
		 * Output: 1
		 * Explanation: The answer is "b", with the length of 1.
		 *
		 * Input: s = "pwwkew"
		 * Output: 3
		 * Explanation: The answer is "wke", with the length of 3.
		 * Notice that the answer must be a substring, "pwke" is a subsequence and not a substring.
		 *
		 * TC - O(N)
		 * SC - O(1)
		 *	n
		 * String s= "";
		 *
		 * m
		 *char[]
		 * if(!)
		 * 	s.concat(s);
		 * else{
		 * 	longet = s.lt
		 * 	s= ""
		 * }
		 *
		 * s= null;
		 * */
		// @formatter:on
		//		int longestSub1 = Check1.lonxgestSubstring("abcabccbb");
		//		System.out.println(longestSub1);

		//		int longestSub2 = Check1.lonxgestSubstring("bbbbb");
		//		System.out.println(longestSub2);
		int longestSub3 = Check1.lonxgestSubstring("pwwkew");
		System.out.println(longestSub3);

	}


	//abcabccbb

	//3iter abc
	//4iter abc 3
	//5itera


	static int lonxgestSubstring(String s) {

		if (s.length() == 0) {
			return 0;
		}

		int i = 0;
		int longestLtSub = 0;
		String subS = "";
		while (i < s.length()) {
			if (_checkSubStrHasCurrChar(subS, s.charAt(i))) {
				if (longestLtSub < subS.length()) {
					longestLtSub = subS.length();
				}
				subS = String.valueOf(s.charAt(i));
			} else {
				String str = String.valueOf(s.charAt(i));
				subS = subS.concat(str);
			}

			i++;
		}
		subS = null;
		return longestLtSub;
	}

	private static boolean _checkSubStrHasCurrChar(String subS, char toCheck) {
		int i = 0;
		while (i < subS.length()) {
			if (subS.charAt(i) == toCheck) {
				return true;
			}
			i++;
		}
		return false;
	}


	static int[] arrangeNegPos(int[] a) {
		int i = 0;
		int k = -1;
		while (i < a.length - 1) {
			if (a[i] <= 0) {
				k++;
				_swap(i, k, a);
			}
			i++;
		}
		return a;
	}

	private static void _swap(int i, int k, int[] a) {
		int temp = a[i];
		a[i] = a[k];
		a[k] = temp;
	}

}




