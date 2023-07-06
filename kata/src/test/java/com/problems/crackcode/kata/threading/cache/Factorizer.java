package com.problems.crackcode.kata.threading.cache;

import java.util.ArrayList;
import java.util.List;

public class Factorizer implements Computable<Integer, List<Integer>> {
	@Override
	public List<Integer> compute(Integer inp) {
		return _factorize(inp);
	}

	private List<Integer> _factorize(Integer inp) {
		List<Integer> op = new ArrayList<>();
		int i = 2;
		while (i < inp) {
			while (inp % i == 0) {
				op.add(i);
				inp /= i;
			}
			i++;
		}
		return op;
	}
}
