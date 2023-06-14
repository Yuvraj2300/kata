package com.problems.crackcode.kata.apis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
						.sorted((Map.Entry<String,Long> e1,Map.Entry<String,Long>e2)->Math.toIntExact((e2.getValue()-e1.getValue())))
						.findFirst()
						.map(e->e.getKey())
						.orElse(null);
		// @formatter:on
		return s;
	}



	@Test
	@DisplayName("Test Find the second greatest Value")
	void testFindTheSecondGreatestValue() {
		//Write a program that takes a stream of numbers and returns an optional value containing the second smallest number in the stream using streams.
		int theSecondGreatestNumber = findTheSecondGreatestNumber(new int[] { 98, 65, 23, 123, 3, 1 });
		Assertions.assertEquals(98, theSecondGreatestNumber);
	}


	int findTheSecondGreatestNumber(int[] a) {
		// @formatter:off
		int integer=	Arrays.stream(a)
							.mapToObj(Integer::valueOf)
							.sorted(Comparator.comparingInt(i->(int)i).reversed())
							.skip(1)
							.findFirst()
							.orElse(-1);
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
				.mapToObj(Double::valueOf)
				.map(i->Math.pow(i,2))
				.reduce(0.0,(x,y)->x+y)
				.intValue();

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
				.collect(Collectors.toMap(Function.identity(),a->a.length()));
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
				.sorted((s1,s2)->s2.length()-s1.length())
				.toArray(e->new String[e]);
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
		String op = getTheLongestStringStartsWithA(new String[] { "ba", "aaa", "aaaaaa", "a" });
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
				.filter(str->str.startsWith("a"))
				.sorted(Comparator.comparing(String::length).reversed())
				.findFirst()
				.orElse(null);
		// @formatter:on

		return op;
	}



	@Test
	void testFindDups() throws Exception {
		int[] expected = { 1, 2, 3, 4 };

		int[] op = findDups(new int[] { 4, 4, 4, 1, 1, 2, 3, 2, 3, 5, 7, 3, 89 });
		Assertions.assertArrayEquals(expected, op);
	}



	int[] findDups(int[] ints) {
		// @formatter:off
		int[] op = Arrays.stream(ints)
				.mapToObj(Integer::valueOf)
				.collect(Collectors.groupingBy(Function.identity(),Collectors.counting()))
				.entrySet().stream()
				.filter(e->e.getValue()>1)
				.sorted((e1,e2)->e1.getKey()-e2.getKey())
				.mapToInt(e->e.getKey())
				.toArray();
		// @formatter:on
		return op;
	}


	@Test
	@DisplayName("Test Get the number of times a  given number ocurred")
	void testGetTheNumberOfTimesAGivenNumberOcurred() {
		int occurances = timeOcurred(new int[] { 1, 2, 5, 5, 3, 4, 5, 6, 3, 5 }, 5);
		Assertions.assertEquals(4, occurances);
	}



	private int timeOcurred(int[] ints, int x) {
		// @formatter:off
		long count = Arrays.stream(ints)
				.filter(i->i==x)
				.count();
		// @formatter:on
		return (int) count;
	}



	@Test
	@DisplayName("Test Get Sum Of Squares Of Event")
	void testGetSumOfSquaresOfEvent() {
		//	Write a program that takes a stream of numbers and returns the sum of the squares of the even numbers.
		double sumSquaresOfEvent = getSumSquaresOfEvent(new int[] { 1, 2, 3, 4 });
		Assertions.assertEquals(20, (int) sumSquaresOfEvent);
	}



	double getSumSquaresOfEvent(int[] a) {
		// @formatter:off
		double reduce = Arrays.stream(a)
				.filter(i->i%2==0)
				.mapToDouble(i->Math.pow(i,2))
				.reduce(0.0,(x,y)->x+y);
		// @formatter:on

		return reduce;
	}



	class Employee {
		String name;
		int ID;
		Department department;

		Salary salary;

		public Employee(String name, int ID, Department department, Salary salary) {
			this.name = name;
			this.ID = ID;
			this.department = department;
			this.salary = salary;
		}

		public String getName() {
			return name;
		}

		public int getID() {
			return ID;
		}

		public Department getDepartment() {
			return department;
		}

		public Salary getSalary() {
			return salary;
		}

		class Department {
			String id;
			String name;

			public Department(String id, String name) {
				this.id = id;
				this.name = name;
			}

			public String getId() {
				return id;
			}

			public String getName() {
				return name;
			}
		}

		class Salary {
			int fixedPay;

			public int getFixedPay() {
				return fixedPay;
			}
		}
	}



	@Test
	@DisplayName("Test Get Grouping By Departments And Avg Salary")
	void testGetGroupingByDepartmentsAndAvgSalary() {
		//	Write a program that takes a stream of employees and returns the average salary of the employees grouped by their department.

	}



	Map<String, Double> getGroupingByDepartmentsAndAvgSalary(List<Employee> listOfEmployees) {
		// @formatter:off
			Map<String,Double> collect = listOfEmployees.stream()
						.collect(Collectors.groupingBy(e->e.getDepartment(),Collectors.mapping(e->e.getSalary(),Collectors.toList())))
						.entrySet().stream()
						.collect(Collectors.groupingBy(e->e.getKey().getName(),Collectors.averagingDouble(t->t.getValue().stream().mapToDouble(i->i.getFixedPay()).average().getAsDouble())));
		// @formatter:on

		Map<String, Double> collect1 = listOfEmployees.stream().collect(Collectors.groupingBy(e -> e.getDepartment().getName(), Collectors.averagingInt(e -> e.getSalary().getFixedPay())));

		return null;
	}

	class Product {
		String name;
		int price;
		int vat;
		int tax;

		public Product(String name, int price, int vat, int tax) {
			this.name = name;
			this.price = price;
			this.vat = vat;
			this.tax = tax;
		}

		public String getName() {
			return name;
		}

		public int getPrice() {
			return price;
		}

		public int getVat() {
			return vat;
		}

		public int getTax() {
			return tax;
		}
	}

	//	Write a program that takes a stream of products and returns a list of the names of the products sorted by their price in descending order.

	List<String> getProductsSortedByTotalPrice(List<Product> products) {
		// @formatter:off
		List<String> collect = products.stream()
				.sorted((pdt1,pdt2)->((pdt2.getPrice()+pdt2.getVat()+pdt2.getTax())-(pdt1.getPrice()+pdt1.getVat()+pdt1.getTax())))
				.map(Product::getName)
				.collect(Collectors.toList());

		// @formatter:on
		return collect;
	}


	//	Write a program that takes a stream of transactions and returns the total amount of the transactions that occurred in the last month.
	class Transaction {
		int amount;
		Date date;
	}

	int calculateTotalAmountOfTransaction(List<Transaction> transactions) {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONDAY, -1);
		Date lastMonth = cal.getTime();
		return transactions.stream().filter(t -> t.date.after(lastMonth)).mapToInt(t -> t.amount).sum();
	}



	@Test
	@DisplayName("Test List of All Integers In Asc Order")
	void testListOfAllIntegersInAscOrder() {
		//	Write a program that takes a list of lists of integers and returns a new list containing all the integers from all the lists, sorted in ascending order.
		List<List<Integer>> listOfLists = new ArrayList<>();
		listOfLists.add(Arrays.asList(4, 3, 1, 5, 6));
		listOfLists.add(Arrays.asList(81, 34, 21, 55, 91));
		listOfLists.add(Arrays.asList(9, 8, 5, 10, 7));

		List<Integer> listOfAllIntsInAscOrder = getListOfAllIntsInAscOrder(listOfLists);
		for (Integer i : listOfAllIntsInAscOrder) {
			System.out.print(i + ", ");
		}
	}


	List<Integer> getListOfAllIntsInAscOrder(List<List<Integer>> list) {
		List<Integer> op =
				// @formatter:off
		list.stream()
				.flatMap(l->l.stream())
				.sorted(Comparator.comparingInt(i->i))
				.collect(Collectors.toList());
		// @formatter:on

		return op;
	}

	//	Write a program that takes a list of strings and returns a new list containing all the distinct characters from all the strings, sorted in alphabetical order.
	List<Character> getAllCharsInAlphOrder(List<String> list) {
		// @formatter:off
		List<Character> collect = list.stream()
									.flatMap(s->s.chars().mapToObj(c->(char)c))
									.distinct()
									.sorted()
									.collect(Collectors.toList());
		// @formatter:on
		return collect;
	}

	//	Write a program that takes a list of sets of integers and returns a new set containing the union of all the sets.
	Set<Integer> getAllTheIntegersInTheSet(List<Set<Integer>> list) {
		// @formatter:off
		Set<Integer> collect = list.stream()
				.flatMap(l->l.stream())
				.collect(Collectors.toSet());
		// @formatter:on
		return collect;
	}

	//	Write a program that takes a stream of streams of integers and returns the sum of all the integers.
	int getSumOffAllIntegersIntheStreamOfStreams(Stream<Stream<Integer>> stream) {
		// @formatter:off
		int sum = stream.flatMap(s->s)
				.mapToInt(i->i)
				.reduce(0,(x,y)->x+y);
		// @formatter:on

		return sum;
	}


	//	Write a program that takes a list of optional integers and returns the sum of all the non-empty optionals.
	int getSumOfNonEmptyOptionals(List<Optional<Integer>> list) {
		// @formatter:off
			list.stream()
					.filter(o->o.isPresent())
					.map(o->o.get())
					.mapToInt(Integer::intValue)
					.sum();
		// @formatter:on
		return -1;
	}

}