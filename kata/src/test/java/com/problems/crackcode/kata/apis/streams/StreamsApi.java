package com.problems.crackcode.kata.apis.streams;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BooleanSupplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;

public class StreamsApi {

	private <T> T firstElement(Stream<T> stream) {
		Optional<T> findFirst = stream.findFirst();
		return findFirst.get();
	}

	private <T> T firstElement_1(Stream<T> stream) {
		Optional<T> findFirst = stream.reduce((first, second) -> first);
		return findFirst.get();
	}




	@Test
	void testGetTheFristElementFromStream() throws Exception {
		String[] words = { "welcome", "to", "geeks", "portal" };
		Stream<String> stream = Arrays.stream(words);
		Object element = firstElement(stream);

		assertEquals("welcome", (String) element);
	}



	@Test
	void testGetTheFristElementFromStream_1() throws Exception {
		Object element = firstElement(Stream.of(1, 2, 3, 4, 5, 6));

		assertEquals(1, (Integer) element);
	}


	@Test
	void testGetTheFristElementFromStream_1_1() throws Exception {
		String[] words = { "welcome", "to", "geeks", "portal" };
		Stream<String> stream = Arrays.stream(words);
		Object element = firstElement_1(stream);

		assertEquals("welcome", (String) element);
	}



	@Test
	void testGetTheFristElementFromStream_1_2() throws Exception {
		Object element = firstElement_1(Stream.of(1, 2, 3, 4, 5, 6));

		assertEquals(1, (Integer) element);
	}


	@Test
	void testGetTheLastElementFromStream() throws Exception {
		Object element = lastElement(Stream.of(1, 2, 3, 4, 5, 6));

		assertEquals(6, (Integer) element);
	}



	private <T> T lastElement(Stream<T> stream) {
		//		stream.reduce((f, s) -> s);
		//		stream.skip(n-1)
		return stream.sorted(Collections.reverseOrder()).findFirst().orElse(null);
	}



	@Test
	void testFindDups() throws Exception {
		Collection<Integer> dups = findDups(Stream.of(5, 13, 4, 21, 13, 27, 2, 59, 59, 34));
		for (Integer integer : dups) {
			System.out.println(integer);
		}
	}



	private Collection<Integer> findDups(Stream<Integer> stream) {
		//map approach
		// @formatter:off
		 stream.collect(
				Collectors.groupingBy(t->t,
									Collectors.counting()))
		.entrySet().stream()
		.filter(mapping->mapping.getValue()>1)
		.map(mapping->mapping.getKey())
		.collect(Collectors.toSet());

		 
		 //set approach
//		 Set<Integer> hs =	 new HashSet<>();
//		 
//		return stream.filter(e->!hs.add(e))
//		 .collect(Collectors.toSet());
//		 
		 
		// @formatter:on
		return null;
	}



	@Test
	void testCountOcurrence() throws Exception {
		long ocurrence = countOccurenceOf(Stream.of(1, 2, 56, 56, 56, 23, 4), 56);
		assertEquals(3, ocurrence);
	}



	private long countOccurenceOf(Stream<Integer> stream, int i) {
		return stream.filter((value) -> i == value).count();
	}



	private Stream<Integer> reverseStream(Stream<Integer> stream) {
		// @formatter:off
		Stream<Integer> collect = stream.collect(
				Collectors.collectingAndThen(Collectors.toList(), 
						//since multiple(parallel) lists
						list->{
							Collections.reverse(list);
							return list.stream();
						}
						));
		
		// @formatter:on

		return collect;
	}



	@Test
	void testReversingAParallelStream() throws Exception {
		List<Integer> list = List.of(3, 2, 4, 55, 3, 44);
		Stream<Integer> parallelStream = list.parallelStream();

		Stream<Integer> reversedStream = reverseStream(parallelStream);
		List<Integer> reversedLists = reversedStream.collect(Collectors.toList());
		for (Integer integer : reversedLists) {
			System.out.print(integer + ", ");
		}
	}





	private void pritnByIdx(Stream<Character> stream) {
		//useing AtomicInt since a stream could get parallel :)
		//could use a primitive here as well
		AtomicInteger ai = new AtomicInteger();

		// @formatter:off
		stream.map(c->{
						StringBuilder sb = new StringBuilder();
						sb.append(ai.getAndIncrement());
						sb.append("-->");
						sb.append(c);
						return sb;
					})
		.forEach(System.out::println);;
		// @formatter:on

	}



	@Test
	void testPrintByIndex() throws Exception {
		pritnByIdx(Stream.of('G', 'e', 'e', 'k', 's'));
	}



	@Test
	void testFlattenAListOfArrays() throws Exception {
		int[][] arrz = { { 1, 2 }, { 3, 4, 5 }, { 6, 7, 8, 9 } };
		flattenAListOfIntArrays(Stream.of(arrz));
	}

	private void flattenAListOfIntArrays(Stream<int[]> stream) {
		// @formatter:off
		int[] array = stream.flatMapToInt(s->Arrays.stream(s))
		.toArray();
		// @formatter:on

		for (int i : array) {
			System.out.print(i + " ");
		}
	}



	@Test
	void testFlattenAListOfArrays_1() throws Exception {
		String[][] arrz = { { "a", "b", "c" }, { "f", "d" }, { "z", "y" } };
		flattenAListOfStringArrays(Stream.of(arrz));
	}



	private void flattenAListOfStringArrays(Stream<String[]> stream) {
		// @formatter:off
		String[] array = stream
						.flatMap(str -> Arrays.stream(str))
						.filter(s->!s.equals("a"))
						.toArray(String[]::new);
		// @formatter:on
		for (String string : array) {
			System.out.print(string + " ");
		}
	}


	@Data
	class Developer {

		private Integer id;
		private String name;
		private Set<String> book;

		//getters, setters, toString

		public void addBook(String book) {
			if (this.book == null) {
				this.book = new HashSet<>();
			}
			this.book.add(book);
		}
	}


	@Test
	void testFindBooksDevsAreReading() throws Exception {
		Developer o1 = new Developer();
		o1.setName("dev1");
		o1.addBook("Java 8 in Action");
		o1.addBook("Spring Boot in Action");
		o1.addBook("Effective Java (3rd Edition)");

		Developer o2 = new Developer();
		o2.setName("dev2");
		o2.addBook("Learning Python, 5th Edition");
		o2.addBook("Effective Java (3rd Edition)");

		List<Developer> listOfDevs = new ArrayList<>();
		listOfDevs.add(o1);
		listOfDevs.add(o2);

		Collection<String> listOfBooks = getBooksDevsAreReading(listOfDevs);
		for (String string : listOfBooks) {
			System.out.println(string);
		}
	}

	private Collection<String> getBooksDevsAreReading(List<Developer> devs) {
		// @formatter:off
		return devs.stream()
				.map(Developer::getBook)
				.flatMap(books->books.stream())
				.collect(Collectors.toSet());
		// @formatter:on

	}



	@Data
	@AllArgsConstructor
	class Order {

		private Integer id;
		private String invoice;
		private List<LineItem> lineItems;
		private BigDecimal total;
	}

	@Data
	@AllArgsConstructor
	class LineItem {
		private Integer id;
		private String item;
		private Integer qty;
		private BigDecimal price;
		private BigDecimal total;
	}



	@Test
	void testGetSumOfAllTheOrders() throws Exception {
		LineItem item1 = new LineItem(1, "apple", 1, new BigDecimal("1.20"), new BigDecimal("1.20"));
		LineItem item2 = new LineItem(2, "orange", 2, new BigDecimal(".50"), new BigDecimal("1.00"));
		Order order1 = new Order(1, "A0000001", Arrays.asList(item1, item2), new BigDecimal("2.20"));

		LineItem item3 = new LineItem(3, "monitor BenQ", 5, new BigDecimal("99.00"), new BigDecimal("495.00"));
		LineItem item4 = new LineItem(4, "monitor LG", 10, new BigDecimal("120.00"), new BigDecimal("1200.00"));
		Order order2 = new Order(2, "A0000002", Arrays.asList(item3, item4), new BigDecimal("1695.00"));

		LineItem item5 = new LineItem(5, "One Plus 8T", 3, new BigDecimal("499.00"), new BigDecimal("1497.00"));
		Order order3 = new Order(3, "A0000003", Arrays.asList(item5), new BigDecimal("1497.00"));

		List<Order> listOfOrders = List.of(order1, order2, order3);

		assertTrue(getSumOfAllTheOrders(listOfOrders));
	}



	private boolean getSumOfAllTheOrders(List<Order> order) {
		// @formatter:off
		BigDecimal sumOfAllLineItems = order.stream()
										.map(Order::getLineItems)
										.flatMap(lineItems->lineItems.stream())
										.map(LineItem::getTotal)
										.reduce(BigDecimal.ZERO,BigDecimal::add); 

		BigDecimal sumOfAllOrders = order.stream()
										.map(Order::getTotal)
										.reduce(BigDecimal.ZERO,BigDecimal::add);
		// @formatter:on

		System.out.println("Sum of all the LineItems : " + sumOfAllLineItems);
		System.out.println("Sum of all the Orders : " + sumOfAllOrders);

		if (sumOfAllLineItems.equals(sumOfAllOrders))
			return true;
		else
			return false;

	}



	@Test
	void testCountWordsInAListOfString() throws Exception {
		List<String> lines = new ArrayList<>();
		lines.add("hello world Java");
		lines.add("hello world Python");
		lines.add("hello world Node");
		lines.add("hello world Rust");
		lines.add("hello world Flutter");

		int words = countWords(lines.stream());
		assertEquals(15, words);
	}



	private int countWords(Stream<String> stream) {
		Stream<String> wordsStream = stream.flatMap(lines -> Stream.of(lines.split(" +")));
		long count = wordsStream.count();
		return (int) count;
	}



	@Test
	void testGetAListOfCharactersFromAString() throws Exception {
		List<Character> charList = getAListOfCharactersFromAString("Geeks");
		for (Character character : charList) {
			System.out.print(character + " ");
		}
	}



	private List<Character> getAListOfCharactersFromAString(String string) {
		return string.chars().mapToObj(c -> (char) c).collect(Collectors.toList());
	}


	@Test
	void testConvertIterableToStream() throws Exception {
		Iterable<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5);
		convertIterableToStream(iterable).forEach(System.out::print);
	}

	private <T> Stream<T> convertIterableToStream(Iterable<T> iterable) {
		Spliterator<T> spliterator = iterable.spliterator();
		Stream<T> stream = StreamSupport.stream(spliterator, false);
		return stream;
	}


	@Test
	void testConvertIteratorToStream() throws Exception {
		Iterator<Integer> iterable = Arrays.asList(1, 2, 3, 4, 5).iterator();
		convertIteratorToStream(iterable).forEach(System.out::print);
	}

	private <T> Stream<T> convertIteratorToStream(Iterator<T> iterator) {
		Spliterator<T> spliteratorUnknownSize = Spliterators.spliteratorUnknownSize(iterator, 0);
		return StreamSupport.stream(spliteratorUnknownSize, false);
	}


	@Test
	void testConvertIntStreamToString() throws Exception {
		String strObtained = convertIntStreamToString("Something".chars());
		System.out.println(strObtained);
	}

	private String convertIntStreamToString(IntStream chars) {
		StringBuilder collect = chars.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append);
		return collect.toString();
	}



	@Test
	void testConvertCharArrayIntStream() throws Exception {
		Character[] sArr = new String("something".toCharArray()).chars().mapToObj(c -> (char) c).toArray(Character[]::new);
		convertCharArrayToIntStream(sArr);
	}

	private void convertCharArrayToIntStream(Character[] cs) {
		Stream.of(cs).flatMapToInt(c -> IntStream.of(c));
	}


	@Test
	void testRemoveAGivenElement() {
		int[] a = { 2, 3, 4, 5, 6 };
		int[] ua = removeAGivenElement(a, 4);
		for (int i : ua) {
			System.out.print(i + ", ");
		}
	}

	private int[] removeAGivenElement(int[] a, int i) {
		return Arrays.stream(a).filter(val -> !(val == i)).toArray();
	}


	@Test
	void testConvertMapToAStream() throws Exception {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "One");
		map.put(2, "Two");
		map.put(3, "Three");
		convertMapToAStream(map);
	}

	private void convertMapToAStream(Map<Integer, String> map) {
		map.entrySet().stream().forEach(System.out::println);
		;
	}



	@Test
	void testConvertListOfIntegerToListOfString() throws Exception {
		List<Integer> listOfInt = new ArrayList<>();
		listOfInt.add(1);
		listOfInt.add(2);
		listOfInt.add(3);
		listOfInt.add(4);
		List<String> lIS = convertListOfIntegerToListOfString(listOfInt);
		lIS.forEach(System.out::print);
	}



	private List<String> convertListOfIntegerToListOfString(List<Integer> listOfInt) {
		// TODO Auto-generated method stub
		return listOfInt.stream().map(i -> String.valueOf(i)).collect(Collectors.toList());
	}



	@Test
	void testGetASliceOfStream() throws Exception {
		int[] arr = { 11, 34, 546, 76, 34, 23, 67, 56, 7, 34 };

		Stream<int[]> slicedArr = getASliceOfArray(arr, 5, 8);
		Integer[] array = slicedArr.toArray(Integer[]::new);
		for (Integer integer : array) {
			System.out.println(integer);
		}
	}



	private <T> Stream<int[]> getASliceOfArray(int[] arr, int startIndex, int endIndex) {
		return Stream.of(arr).skip(startIndex).limit(endIndex - startIndex + 1);
	}



	/**
	 * @author yuvraj1.sharma
	 * @implSpec Nice, important
	 */
	@Test
	void testFindIndexOfAValueInAnArray() throws Exception {
		int[] arr = { 2, 3, 4, 5, 67, 8, 38, 1 };
		assertEquals(3, findIndexAValueInAnArray(arr, 5));
	}



	private Integer findIndexAValueInAnArray(int[] arr, int val) {
		// TODO Auto-generated method stub
		// @formatter:off
		return IntStream.range(0, arr.length - 1)
							.filter(idx->val==arr[idx])
							.findFirst()
							.orElse(-1);
		// @formatter:on
	}



	@Test
	void testCheckIfAValueExistsInAnIntegerArray() throws Exception {
		int[] arr = { 1, 5, 34, 5, 2, 3, 19 };
		assertTrue(checkIfAValueExistsInAnIntegerArray(arr, 3));
	}



	private Boolean checkIfAValueExistsInAnIntegerArray(int[] arr, int valToCheck) {
		// TODO Auto-generated method stub
		return IntStream.of(arr).anyMatch(currVal -> currVal == valToCheck);
	}
}




