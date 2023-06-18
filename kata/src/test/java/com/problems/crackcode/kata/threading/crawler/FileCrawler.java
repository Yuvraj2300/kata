package com.problems.crackcode.kata.threading.crawler;

import java.io.File;
import java.io.FileFilter;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.BlockingQueue;

public class FileCrawler implements Runnable {

	private boolean running;

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	final FileFilter fileFilter;
	final File root;
	final Set<String> index;
	final BlockingQueue<File> workQueue;

	public FileCrawler(FileFilter fileFilter, File root, Set<String> index, BlockingQueue workQueue) {
		this.fileFilter = fileFilter;
		this.root = root;
		this.index = index;
		this.workQueue = workQueue;
	}

	@Override
	public void run() {
		try {
			if (isRunning()) {
				crawl(root);
			}
		} catch (Exception e) {
			System.out.println("ERROR : " + e.getMessage());
			Thread currentThread = Thread.currentThread();
			System.out.println("Crawler Interrupted, Thread name : " + currentThread.getName());

			//housekeeping
			this.setRunning(false);
			workQueue.clear();

			currentThread.interrupt();
		}
	}

	private void crawl(File root) throws InterruptedException {
		File[] files = root.listFiles(fileFilter);
		if (Objects.nonNull(files)) {
			for (File file : files) {
				if (file.isDirectory()) {
					System.out.println("Crawiling into directory : " + file.getName());
					crawl(file);
				} else {
					if (!alreadyIndexed(file)) {
						System.out.println("File " + file.getName() + " is not indexed, putting the file on the work queue");
						workQueue.put(file);
					}
				}
			}
		}

	}

	private boolean alreadyIndexed(File file) {
		System.out.println("Checking if file : " + file.getName() + " is already indexed.");
		if (index.contains(file)) {
			return true;
		}
		return false;
	}
}
