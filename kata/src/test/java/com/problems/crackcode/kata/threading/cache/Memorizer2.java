package com.problems.crackcode.kata.threading.cache;

import java.util.Map;
import java.util.concurrent.*;

public class Memorizer2<A, V> implements Computable<A, V> {
	final Map<A, Future<V>> cache = new ConcurrentHashMap<>();
	Computable<A, V> toCompute;

	public Memorizer2(Computable<A, V> toCompute) {
		this.toCompute = toCompute;
	}



	@Override
	public V compute(A inp) {
		// I cannot reason about why there is a while loop here
		// all i can understand is we need it for the case when
		// there is cancellation, hence the thread will try to recompute
		// otherwise it will just let it go !
		while (true) {
			Future<V> f = cache.get(inp);
			if (null == f) {
				FutureTask<V> ft = new FutureTask<>(() -> toCompute.compute(inp));
				//this is atomic - put if absent
				f = cache.putIfAbsent(inp, ft);
				//check then act
				if (f == null) {
					f = ft;
					ft.run();
				}
			}
			try {
				return f.get();
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			} catch (ExecutionException ex) {
				//do something
			}
		}
	}
}
