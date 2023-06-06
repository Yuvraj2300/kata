package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;
import org.hamcrest.MatcherAssert.*;


import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StreamsAPI {

	@Test
	@DisplayName("Test String Array To Freq Map")
	void testStringArrayToFreqMap() {
		String[] a = { "AA", "BA", "CB", "CC" };
		Map<Character, ? extends Number> mapOfCharFrq = stringArrayToFrqMap(a);
		System.out.println(mapOfCharFrq);
	}



	private Map<Character, ? extends Number> stringArrayToFrqMap(String[] a) {
		//a string array is an object or objects
		// so we need to pull each string and then break it into single char somehow
		// @formatter:off
		Map<Character,Long> collectedMap = Arrays.stream(a)
											.flatMap(s->s.chars()
												.mapToObj(c->(char)c)
											).collect(Collectors.groupingBy(Function.identity(),Collectors.counting()));
		// @formatter:on
		return collectedMap;
	}



	@Test
	@DisplayName("Test Sum Of Even Number In Array")
	void testSumOfEvenNumberInArray() {
		//Write a program that takes an array of integers and returns the sum of the even numbers in the array using streams.
		int sum = getSumOfEvenNumbers(new int[] { 1, 2, 3, 4, 5, 6 });
		Assertions.assertEquals(12, sum);
	}


	int getSumOfEvenNumbers(int[] a) {
		// @formatter:off
		int sum = Arrays.stream(a)
				.filter(i -> i % 2 == 0)
				.sum();

		int sum1 = Arrays.stream(a).filter(i->i%2==0).reduce(0,(x,y)->x+y);
		// @formatter:on
		return sum;
	}



	@Test
	@DisplayName("Test Find Length Of Strings & Sort in Desc")
	void testFindLengthOfStringsSortInDesc() {
		//Write a program that takes a list of strings and returns a list of the lengths of the strings in descending order using streams.
		Integer[] sortedByLength = findLengthOfStringSortInDesc(new String[] { "Hello", "World1", "One" });

		Arrays.stream(sortedByLength).forEach(s -> System.out.println(s + ", "));
	}

	Integer[] findLengthOfStringSortInDesc(String[] a) {
		// @formatter:off
		Integer[] strings = Arrays.stream(a)
				.sorted((s1,s2)->s2.length()-s1.length())
				.map(s->s.length())
				.toArray(s->new Integer[s]);
		// @formatter:on

		return strings;
	}




/*
* Write a program that takes a map of student names and grades and returns a map of student names and average grades using streams.
Write a program that takes a stream of words and returns the most frequent word in the stream using streams.
Write a program that takes a stream of numbers and returns an optional value containing the second smallest number in the stream using streams.
* */
}
