package com.problems.crackcode.kata.threading.crawler;

import java.io.File;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class Indexer implements Runnable {
	final BlockingQueue<File> workQueue;
	final Set<String> index;

	public Indexer(BlockingQueue<File> workQueue, Set<String> index) {
		this.workQueue = workQueue;
		this.index = index;
	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("Taking work from the work queue.");
				indexFile(workQueue.take());
			} catch (InterruptedException e) {
				Thread currentThread = Thread.currentThread();

				System.out.println("Indexer Interrupted, Thread name : " + currentThread.getName());
				currentThread.interrupt();
				break;
			}
		}
	}

	private void indexFile(File file) {
		String name = file.getName();
		System.out.println("Indexing File with name : " + name);
		index.add(name);
	}
}
