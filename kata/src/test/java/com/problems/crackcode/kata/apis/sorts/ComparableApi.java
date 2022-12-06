package com.problems.crackcode.kata.apis.sorts;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.jupiter.api.Test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

public class ComparableApi {
	@Data
	@AllArgsConstructor
	@NoArgsConstructor
	@ToString
	class Fruit implements Comparable<Fruit> {
		private String fruitName;
		private String fruitDesc;
		private int quantity;


		@Override
		public int compareTo(Fruit comparison) {
			//comparing by quatity
			int comparisonQuantity = comparison.getQuantity();

			//descending
			return comparisonQuantity - this.quantity;

			//ascending
			//			return this.quantity - comparisonQuantity;
		}
	}

	@Test
	void testSortingAnArrayOfFruits_ThrowsExceptionAsNoComparable() throws Exception {
		Fruit[] fruitArr = new Fruit[3];
		fruitArr[0] = new Fruit("Apple", "A Good Fruit", 1);
		fruitArr[1] = new Fruit("Orange", "A Good Fruit", 5);
		fruitArr[2] = new Fruit("Mango", "A Good Fruit", 21);

		Arrays.sort(fruitArr);

		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}

		System.out.println();
		System.out.println("#########################_____________________________#############################");
		System.out.println("#########################_____________________________#############################");
		System.out.println("#########################_____________________________#############################");
		System.out.println();

		//		Arrays.sort(fruitArr, new Comparator<Fruit>() {
		//			@Override
		//			public int compare(Fruit o1, Fruit o2) {
		//				return o1.getFruitName().compareTo(o2.getFruitName());
		//			}
		//		});

		Arrays.sort(fruitArr, (f1, f2) -> {
			return f1.getFruitName().compareTo(f2.getFruitName());
		});

		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}
	}


	@Test
	void testSortingAnArrayOfFruits() throws Exception {
		Fruit[] fruitArr = new Fruit[3];
		fruitArr[0] = new Fruit("Apple", "A Good Fruit", 1);
		fruitArr[1] = new Fruit("Orange", "A Good Fruit", 5);
		fruitArr[2] = new Fruit("Mango", "A Good Fruit", 21);

		Arrays.sort(fruitArr);

		for (Fruit fruit : fruitArr) {
			System.out.println(fruit);
		}

	}


}


