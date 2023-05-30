package com.structures.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ModelTest {
	@Test
	@DisplayName("Test Name")
	void testModel() {
		Model model = new Model(1);
		//anonymous inner class
		model.doSomeConc(new FI() {
			@Override
			public void doSome() {
				System.out.println("In DO SOME");
			}
		});

		//lambda way
		model.doSomeConc(() -> System.out.println("something lamda"));

	}

}
