package com.problems.crackcode.kata.exceptions;

public class ToDoSomething {
	public int doSomething() {
		throw new KataException("un implemented");
	}

	public int doSomethingChecked() throws KataCheckedException {
		throw new KataCheckedException("un implemented");
	}



}
