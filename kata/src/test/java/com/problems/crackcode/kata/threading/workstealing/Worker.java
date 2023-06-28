package com.problems.crackcode.kata.threading.workstealing;

import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class Worker implements Runnable {
	final Deque<Runnable> deque;
	final List<Worker> listOfAllWorkers;

	private volatile boolean running;

	public boolean isRunning() {
		return running;
	}

	public Worker(Deque<Runnable> deque, List<Worker> listOfAllWorkers) {
		this.deque = deque;
		this.listOfAllWorkers = listOfAllWorkers;
		this.running = true;
	}

	@Override
	public void run() {
		doJob();
	}

	private void doJob() {
		while (running) {
			Runnable poll = deque.poll();
			if (Objects.nonNull(poll)) {
				poll.run();
			} else {
				stealWork();
			}
		}
	}


	private void stealWork() {
		for (Worker worker : listOfAllWorkers) {
			if (this != worker) {
				Runnable lastWork = worker.getLastWork();
				if (Objects.nonNull(lastWork)) {
					deque.offer(lastWork);
					return;
				}
			}
		}
		this.running = false;
	}

	public Runnable getLastWork() {
		return this.deque.pollLast();
	}
}
