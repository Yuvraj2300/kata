package com.problems.crackcode.kata.threading.cancellation;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.*;

public class HandlingUncaught {
	public static void main(String[] args) {
		callExecSafe();

	}

	static void callExecSafe() {
		SafeExecutor safeExecutor = new SafeExecutor();
		safeExecutor.runJob(() -> {
			for (int i = 0; i < 20; i++) {
				if (i == 5) {
					//throw a RuntimeException
					int x = i / 0;
				}
			}
		});

		safeExecutor.terminate();

	}


	void call() {
		Worker worker = new Worker(new Runnable() {
			@Override
			public void run() {

			}
		});

		worker.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			@Override
			public void uncaughtException(Thread t, Throwable e) {

			}
		});
	}



}

//using the below proactive method with ThreadPool Executors as where
// here we can basically tell executor what kind of thread should it create -
// meaning that we must override the behvior of ThreadFactory
// We can also craete a safteynet by overriding afterexecute in the constructor the ThreadPoolExec
class SafeExecutor {
	private ExecutorService exec;


	SafeExecutor() {
		exec = new ThreadPoolExecutor(2, 2, 0, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), new ThreadFactory() {
			@Override
			public Thread newThread(@NotNull Runnable r) {
				Thread t = new Thread(r);
				t.setUncaughtExceptionHandler((tr, e) -> {
					System.out.println("Thread with name : " + tr.getName() + ", encountered an error.");
					e.printStackTrace();
				});
				return t;
			}
		}) {
			@Override
			protected void afterExecute(Runnable r, Throwable t) {
				super.afterExecute(r, t);
				if (t != null) {
					System.out.println("Task encountered an issue :" + r);
					t.printStackTrace();
				}
			}
		};
	}

	public void runJob(Runnable task) {
		exec.execute(task);
	}

	public void terminate() {
		exec.shutdown();
		try {
			exec.awaitTermination(5, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

}





//Proactive method to handle such scenarios when creating unbounded threads
class Worker extends Thread {
	public Worker(Runnable target) {
		super(target);
	}


	@Override
	public void run() {
		Throwable thrown = null;
		try {
			while (!Thread.currentThread().isInterrupted()) {
				super.run();
			}
		} catch (Throwable t) {
			thrown = t;
		} finally {
			threadExit(this, thrown);
		}


	}

	private void threadExit(Worker worker, Throwable th) {
		//inform the owner about thread death
	}
}
