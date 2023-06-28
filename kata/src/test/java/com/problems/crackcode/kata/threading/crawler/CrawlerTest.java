package com.problems.crackcode.kata.threading.crawler;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CrawlerTest {
	@Test
	@DisplayName("Test Crawler")
	void testCrawler() throws InterruptedException, IOException {
		int consumers = 1;
		int bound = 16;
		int numOfCrawler = 0;

		Set<String> index = Collections.synchronizedSet(new HashSet<>());
		BlockingQueue<File> queue = new LinkedBlockingQueue(bound);

		List<File> rootsToIndex = new ArrayList<>();

		File file = new File("src/test/resources/myspace");

		if (file.exists() && file.canRead()) {
			rootsToIndex.add(file);
		} else {
			throw new RuntimeException("The path could not be loaded");
		}

		List<Thread> listOfCrawlerThreads = new ArrayList<>();
		List<Thread> listOfIndexerThreads = new ArrayList<>();

		Thread queueWatchThread = new Thread(new WorkQueueWatch<File>(queue));
		queueWatchThread.setName("queueWatchThread");
		//		queueWatchThread.start();

		for (File root : rootsToIndex) {
			FileFilter fileFilter = f -> f.getName().endsWith(".txt") || f.isDirectory();
			Thread crawlerThread = new Thread(new FileCrawler(fileFilter, root, index, queue));
			crawlerThread.setName("crawlerThread" + (++numOfCrawler));
			listOfCrawlerThreads.add(crawlerThread);
			crawlerThread.start();
		}

		for (int i = 0; i < consumers; i++) {
			Thread indexerThread = new Thread(new Indexer(queue, index));
			indexerThread.setName("indexerThread" + i);
			listOfIndexerThreads.add(indexerThread);
			indexerThread.start();
		}

		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				for (Thread crawlerThread : listOfCrawlerThreads) {
					crawlerThread.interrupt();
				}

				for (Thread indexerThread : listOfIndexerThreads) {
					indexerThread.interrupt();
				}

				//				queueWatchThread.interrupt();

				timer.cancel();
			}
		}, 10000);

		try {
			for (Thread crawler : listOfCrawlerThreads) {
				crawler.join();
			}
			for (Thread indexer : listOfIndexerThreads) {
				indexer.join();
			}

			//			queueWatchThread.interrupt();
		} catch (InterruptedException ex) {
			ex.printStackTrace();
		}
		System.out.println();
		System.out.println("Index: " + index);

		Assertions.assertEquals(4, index.size());
		Assertions.assertEquals(0, queue.size());

		for (Thread crawler : listOfCrawlerThreads) {
			Assertions.assertEquals(Thread.State.TERMINATED, crawler.getState());
		}

		for (Thread indexer : listOfIndexerThreads) {
			Assertions.assertEquals(Thread.State.TERMINATED, indexer.getState());
		}
	}
}
