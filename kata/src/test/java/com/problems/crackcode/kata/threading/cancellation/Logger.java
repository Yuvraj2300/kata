package com.problems.crackcode.kata.threading.cancellation;

import java.io.PrintWriter;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.ReentrantLock;

public class Logger {
	BlockingQueue<String> logQueue;

	PrintWriter writer;

	LoggerThread loggerThread;


	public Logger(BlockingQueue<String> logQueue, PrintWriter writer) {
		this.logQueue = logQueue;
		this.writer = writer;
		this.loggerThread = new LoggerThread(logQueue, writer);
		loggerThread.start();

	}


	public void writeLog(String message) {
		try {
			logQueue.put(message);
		} catch (InterruptedException e) {
			Thread.currentThread().interrupt();
		}

	}
}

class LoggerThread extends Thread {
	BlockingQueue<String> logQueue;

	PrintWriter writer;

	public LoggerThread(BlockingQueue<String> logQueue, PrintWriter writer) {
		super();
		this.logQueue = logQueue;
		this.writer = writer;
	}

	@Override
	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			ReentrantLock lock = new ReentrantLock();
			lock.lock();
			try {
				writer.println(logQueue.take());
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				super.interrupt();
			}
			lock.unlock();
		}
	}
}


