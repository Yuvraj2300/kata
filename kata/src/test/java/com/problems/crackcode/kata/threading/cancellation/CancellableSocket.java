package com.problems.crackcode.kata.threading.cancellation;

import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.FutureTask;
import java.util.concurrent.RunnableFuture;

public abstract class CancellableSocket<T> implements CancellableType<T> {
	Socket socket;

	public CancellableSocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void cancel() {
		try {
			if (socket != null) {
				synchronized (this) {
					socket.close();
				}
			}
		} catch (IOException ignore) {
			//log that socket was cancelled
		}
	}

	@Override
	public RunnableFuture<T> newTask() {
		return new FutureTask<>(this) {
			@Override
			public boolean cancel(boolean mayInterruptIfRunning) {
				try {
					CancellableSocket.this.cancel();
				} finally {
					return super.cancel(mayInterruptIfRunning);
				}
			}
		};
	}
}
