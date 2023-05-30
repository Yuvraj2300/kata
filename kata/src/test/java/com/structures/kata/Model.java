package com.structures.kata;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

interface FI {
	void doSome();
}

public class Model {


	int v1;

	public Model(int v1) {
		this.v1 = v1;
	}

	void doSomeConc(FI fi) {
		fi.doSome();
	}

}
