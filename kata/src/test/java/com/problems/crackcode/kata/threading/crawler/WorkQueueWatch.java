package com.problems.crackcode.kata.threading.crawler;

import java.util.concurrent.BlockingQueue;

public class WorkQueueWatch<T> implements Runnable {
	private BlockingQueue<T> workQueue;

	private volatile boolean isRunning;

	public synchronized boolean isRunning() {
		return isRunning;
	}

	public synchronized void setRunning(boolean running) {
		isRunning = running;
	}

	public WorkQueueWatch(BlockingQueue<T> workQueue) {
		this.setRunning(true);
		this.workQueue = workQueue;
	}



	@Override
	public void run() {
		while (isRunning) {
			try {
				monitorQueue();
				Thread.sleep(1000);
			} catch (Exception ex) {
				Thread currentThread = Thread.currentThread();
				this.setRunning(false);

				System.out.println("LOG : " + Thread.currentThread().getName() + " - Watch Interrupted, Thread name : " + currentThread.getName());
				currentThread.interrupt();
				break;
			}
		}
	}

	private void monitorQueue() {
		synchronized (workQueue) {
			System.out.println("LOG : " + Thread.currentThread().getName() + " - The queue looks like this for now");
			System.out.println("LOG : " + Thread.currentThread().getName() + workQueue);
		}
	}
}
