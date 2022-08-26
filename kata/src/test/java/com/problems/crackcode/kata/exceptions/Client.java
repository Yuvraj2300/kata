package com.problems.crackcode.kata.exceptions;

import org.junit.jupiter.api.Test;

public class Client {
	public void doClientStuff() {
		ToDoSomething tds = new ToDoSomething();
		tds.doSomething();
	}


	@Test
	void testDoClientStuff() throws Exception {
		Client c = new Client();
		c.doClientStuff();
	}
}
