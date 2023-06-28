package com.problems.crackcode.kata.threading.synchronizers.semaphore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Semaphore;

public class BoundedList<T> {
	Semaphore sem;
	List<T> list;

	public BoundedList(int bound) {
		this.sem = new Semaphore(bound);
		this.list = Collections.synchronizedList(new ArrayList<T>());
	}

	boolean addToBoundedList(T ele) throws InterruptedException {
		sem.acquire();
		boolean added = false;
		try {
			added = list.add(ele);
			//the calling thread will hold the permint untle it calls remove
			return added;
		} finally {
			if (!added) {
				sem.release();
			}
		}
	}


	boolean removeFromBoundedList(T ele) {
		boolean remove = list.remove(ele);
		if (remove) {
			//release the permit upon remove
			sem.release();
		}

		return remove;
	}

}
