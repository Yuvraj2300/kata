package com.problems.crackcode.kata.apis.concurrency;

import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;

public class SynchornizationSample {
	static boolean stopFlag = false;

	private static synchronized void requestStop() {
		stopFlag = true;
	}

	private static synchronized void requestStart() {
		stopFlag = false;
	}

	int i = 0;

	private void synchVersion() throws InterruptedException {

		Thread backGroundThread = new Thread(() -> {
			while (!stopFlag) {
				i++;
			}
		});
		backGroundThread.start();

		Thread.sleep(2000);
		System.out.println(i);
		requestStop();
	}

	@Test
	void testSyncVersion() {
		try {
			synchVersion();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void unsynchVersion() throws Exception {
		Thread backGroundThread = new Thread(() -> {
			while (!stopFlag) {
				i++;
			}
		});
		backGroundThread.start();

		Thread.sleep(2000);
		System.out.println(i);
		stopFlag = true;
	}


	@Test
	void testUnSyncVersion() {
		try {
			unsynchVersion();
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
