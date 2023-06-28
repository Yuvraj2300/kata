package com.problems.crackcode.kata.threading.cache;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Memorizer<A, V> implements Computable<A, V> {
	final Map<A, V> cache = new HashMap<>();
	Computable<A, V> toCompute;

	public Memorizer(Computable<A, V> toCompute) {
		this.toCompute = toCompute;
	}


	//syncronized to keep mutex for the current thread and make this atomic
	//but this is a loop hole !
	//since if the calculation is BIG then threads will be blocked
	//even if it is small, then also threads can only go one by one
	//that means people have to wait
	@Override
	public synchronized V compute(A inp) {
		V result = cache.get(inp);
		if (Objects.isNull(result)) {
			result = toCompute.compute(inp);
			cache.put(inp, result);
		}
		return result;
	}
}
