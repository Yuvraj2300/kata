package com.problems.crackcode.kata.threading.crawler;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileFilter;
import java.util.*;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CrawlerTest {
	@Test
	@DisplayName("Test Crawler")
	void testCrawler() {
		int consumers = 4;
		int bound = 16;

		Set<String> index = Collections.synchronizedSet(new HashSet<>());
		BlockingQueue<File> queue = new LinkedBlockingQueue(bound);

		List<File> rootsToIndex = new ArrayList<>();
		File root1 = new File("test/resources/myspace");
		//		File root2 = new File("");

		List<Thread> listOfCrawlerThreads = new ArrayList<>();
		List<Thread> listOfIndexerThreads = new ArrayList<>();

		for (File root : rootsToIndex) {
			Thread crawlerThread = new Thread(new FileCrawler(f -> f.getName().endsWith(".txt"), root, index, queue));
			listOfCrawlerThreads.add(crawlerThread);
		}

		for (int i = 0; i < consumers; i++) {
			Thread indexerThread = new Thread(new Indexer(queue, index));
			listOfIndexerThreads.add(indexerThread);
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

				timer.cancel();
			}
		}, 10000);

	}

}
