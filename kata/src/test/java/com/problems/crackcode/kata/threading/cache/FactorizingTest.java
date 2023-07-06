package com.problems.crackcode.kata.threading.cache;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

public class FactorizingTest {
	@Test
	@DisplayName("Test Factorizing")
	void testFactorizing() {
		Factorizer factorizer = new Factorizer();
		Memorizer2<Integer, List<Integer>> compute = new Memorizer2<>(factorizer);
		//		System.out.println(compute.compute(200));
		for (int i = 0; i < 100; i++) {
			System.out.println(compute.compute(i));
		}
	}


	@Test
	@DisplayName("Test Just Chkecing")
	void testJustChkecing() {
		System.out.println(Runtime.getRuntime().availableProcessors());
	}

}
