package com.problems.crackcode.kata.threading.cache;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public class Memorizer1<A, V> implements Computable<A, V> {
	//using concurrent HM to avoid concurrent access issues
	//this way we do not have to make the compute method syncornized
	// since we have delegated thread safety to cHM !
	//cHM is thread safe thanks to synchrnization on  segmentations
	final Map<A, V> cache = new ConcurrentHashMap<>();
	Computable<A, V> toCompute;

	public Memorizer1(Computable<A, V> toCompute) {
		this.toCompute = toCompute;
	}


	// THis is mostly thread safe but there can be one issue here
	// since we know calculations can be LONG ! that can cause another thread to
	//look for result that is already being calculated
	// For e.g. :->
	// Thread 1: -// checks in cache not found so calculate
	// t=0 f(BIG_CALC1)---------3 Mins-------- RESULT t=3
	//
	// Thread 2: - // checks not found so starts caclcuation too
	// 			t=1	f(BIG_CALC1)-------------------- 		t= 4

	// THis kind of issue can causes uncecssary efficenicy issues
	// We need to store information for what's processing is already being done
	// if you think creating a new DS to store that info is right, then u r wrong
	// it is better to store a FUTURE object as a value in thes ame cHM
	@Override
	public V compute(A inp) {
		V result = cache.get(inp);
		if (Objects.isNull(result)) {
			result = toCompute.compute(inp);
			cache.put(inp, result);
		}
		return result;
	}
}
