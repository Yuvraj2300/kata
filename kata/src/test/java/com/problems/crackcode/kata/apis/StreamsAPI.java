package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.*;


import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
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
		// @formatter:off
		Map<Character,Long> collect = Arrays.stream(a)
				.flatMap(str->str.chars().mapToObj(c->(char)c))
				.collect(Collectors.groupingBy(e->e,Collectors.counting()));

		// @formatter:on
		return collect;
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
		int reduce = Arrays.stream(a).filter(i->i%2==0).reduce(0,(x,y)->x+y);
		return reduce;
	}



	@Test
	@DisplayName("Test Find Length Of Strings & Sort in Desc")
	void testFindLengthOfStringsSortInDesc() {
		//Write a program that takes a list of strings and returns a list of the lengths of the strings in descending order using streams.
		int[] sortedByLength = findLengthOfStringSortInDesc(new String[] { "Hello", "World1", "One" });

		Arrays.stream(sortedByLength).forEach(s -> System.out.println(s + ", "));
	}



	int[] findLengthOfStringSortInDesc(String[] a) {
		// @formatter:off
		int[] integers = Arrays.stream(a)
				.map(String::length)
				.sorted((x,y)->y-x)
				.collect(Collectors.toList())
				.stream()
				.mapToInt(i->i)
				.toArray();
		// @formatter:on

		return integers;
	}



	@Test
	@DisplayName("Test Get Student's Average")
	void testGetStudentAverage() {
		//Write a program that takes a map of student names and LIST OF grades and returns a map of student names and average grades using streams.
		Map<String, Double> james = Collections.singletonMap("James", 73.33333333333333);
		Map<String, Double> avgScore = findAvgGrades(Collections.singletonMap("James", Arrays.asList(50, 80, 90)));

		Assertions.assertTrue(avgScore.containsKey("James"));
		Assertions.assertTrue(avgScore.get("James") instanceof Double);
		Assertions.assertNotNull(avgScore.get("James"));
		Assertions.assertEquals(james.get("James"), avgScore.get("James"));
	}



	Map<String, Double> findAvgGrades(Map<String, List<Integer>> mapOfStudentNameGrades) {
		// @formatter:off
		Map<String,Double> collect = mapOfStudentNameGrades.entrySet().stream()
				.collect(Collectors.toMap(e->e.getKey(),
						e->e.getValue().stream().mapToInt(i->i).average().getAsDouble()));
		// @formatter:on

		return collect;
	}



	@Test
	@DisplayName("Test Find the most Frequent Word")
	void testFindTheMostFrequentWord() {
		//Write a program that takes a stream of words and returns the most frequent word in the stream using streams.
		String theMostFrequentWord = findTheMostFrequentWord(new String[] { "Hey", "Hey", "Monica" });
		Assertions.assertEquals("Hey", theMostFrequentWord);

	}


	String findTheMostFrequentWord(String[] a) {
		// @formatter:off
		String s = Arrays.stream(a)
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.sorted(Comparator.comparing((Map.Entry<String,Long>	e)->e.getValue()).reversed())
				.findFirst()
				.map(e->e.getKey()).orElse(null);
		// @formatter:on
		return s;
	}



	@Test
	@DisplayName("Test Find the second greatest Value")
	void testFindTheSecondGreatestValue() {
		//Write a program that takes a stream of numbers and returns an optional value containing the second smallest number in the stream using streams.
		int theSecondGreatestNumber = findTheSecondGreatestNumber(new int[] { 98, 65, 23, 123, 3, 1 });
		Assertions.assertEquals(3, theSecondGreatestNumber);
	}


	int findTheSecondGreatestNumber(int[] a) {
		// @formatter:off
		Integer integer = Arrays.stream(a)
				.mapToObj(i->(Integer) i)
				.sorted(Comparator.comparingInt(o->o))
				.skip(1)
				.findFirst().orElse(-1);

		// @formatter:on
		return integer;
	}


	@Test
	@DisplayName("Test Get Sum Of Squares of the Numbers")
	void testGetSumOfSquaresOfTheNumbers() {
		//Write a program that takes a stream of numbers and returns the sum of the squares of the numbers in the stream.
		int sum = getTheSumOfTheSquaresOfTheNums(new int[] { 1, 2, 3 });
		Assertions.assertEquals(14, sum);
	}


	int getTheSumOfTheSquaresOfTheNums(int[] a) {
		// @formatter:off
		int reduce = Arrays.stream(a)
						.map(i->(int)Math.pow(i,2))
						.reduce(0,Integer::sum);
		// @formatter:on
		return reduce;
	}

	@Test
	@DisplayName("Test Get Strings To Length Map")
	void testGetStringsToLengthMap() {
		//Write a program that takes a stream of strings and returns a map where the keys are the strings and the values are the lengths of the strings.
		Map<String, Integer> helloMap = Collections.singletonMap("Hello", 5);
		Map<String, Integer> stringsToLengthMap = getStringsToLengthMap(new String[] { "Hello", "Bad", "Little" });
		Assertions.assertEquals(helloMap.get("Hello"), stringsToLengthMap.get("Hello"));
	}


	Map<String, Integer> getStringsToLengthMap(String[] s) {
		// @formatter:off
		return Arrays.stream(s)
				.collect(Collectors.toMap(Function.identity(),str->str.length()));
		// @formatter:on
	}


	@Test
	@DisplayName("Test Get String In Uppercase And Desc")
	void testGetStringInUppercaseAndDesc() {
		//Write a program that takes a stream of strings and returns a list of the strings in uppercase, sorted in descending order by length.
		String[] expected = { "HELLO", "YOUS", "HOW", "R" };
		String[] stringInUpperCaseAndDesc = getStringInUpperCaseAndDesc(new String[] { "hello", "how", "r", "yous" });
		Assertions.assertArrayEquals(expected, stringInUpperCaseAndDesc);
	}


	String[] getStringInUpperCaseAndDesc(String[] s) {
		// @formatter:off
		String[] strings = Arrays.stream(s)
				.map(str->str.toUpperCase())
				.sorted((x,y)->y.length()-x.length())
				.toArray(str->new String[str]);
		// @formatter:on

		return strings;
	}



	@Test
	@DisplayName("Test Get The Product Of Numbers")
	void testGetTheProductOfNumbers() {
		//Write a program that takes a stream of numbers and returns the product of all the numbers in the stream.
		int product = getTheProductOfNumbers(new int[] { 1, 2, 3 });
		Assertions.assertEquals(6, product);
	}



	int getTheProductOfNumbers(int[] a) {
		// @formatter:off
		int reduce = Arrays.stream(a)
				.reduce(1,(x,y)->x*y);
		// @formatter:on

		return reduce;
	}



	@Test
	@DisplayName("Test Get the Longest String Starts With A")
	void testGetTheLongestStringStartsWithA() {
		String expected = "aaaaaa";
		String op = getTheLongestStringStartsWithA(new String[] { "aa", "aaa", "aaaaaa", "a" });
		Assertions.assertEquals(expected, op);
	}



	@Test
	@DisplayName("Test Get the Longest String Starts With A")
	void testGetTheLongestStringStartsWithA_1() {
		String expected = "aaa";
		String op = getTheLongestStringStartsWithA(new String[] { "aa", "aaa", "a" });
		Assertions.assertEquals(expected, op);
	}


	//Write a program that takes a stream of strings and returns the longest string that starts with the letter ‘a’.
	String getTheLongestStringStartsWithA(String[] a) {
		// @formatter:off
		String op = Arrays.stream(a)
				.filter(s->s.startsWith("a"))
				.sorted((x,y)->y.length()-x.length())
				.findFirst()
				.orElse(null);
		// @formatter:on

		return op;
	}


}