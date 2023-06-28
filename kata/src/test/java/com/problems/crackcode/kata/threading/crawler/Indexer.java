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
//				Thread.sleep(2000);
				System.out.println("LOG : " + Thread.currentThread().getName() + " - Taking work from the work queue.");
				indexFile(workQueue.take());
			} catch (InterruptedException e) {
				Thread currentThread = Thread.currentThread();

				System.out.println("LOG : " + Thread.currentThread().getName() + " - Indexer Interrupted, Thread name : " + currentThread.getName());
				currentThread.interrupt();
				break;
			}
		}
	}

	private void indexFile(File file) {
		String name = file.getName();
		System.out.println("LOG : " + Thread.currentThread().getName() + " - Indexing File with name : " + name);
		index.add(name);
		System.out.println("LOG : " + Thread.currentThread().getName() + " - File with name : " + name + " Indexed !");
	}
}
