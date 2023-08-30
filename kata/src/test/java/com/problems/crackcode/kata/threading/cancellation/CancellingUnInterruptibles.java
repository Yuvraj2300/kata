package com.problems.crackcode.kata.threading.cancellation;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.util.concurrent.*;

public class CancellingUnInterruptibles {

}



class ReaderThread extends Thread {
	Socket socket;

	InputStream inStrm;

	public ReaderThread(Socket socket, InputStream inStrm) throws IOException {
		this.socket = socket;
		this.inStrm = socket.getInputStream();
	}

	@Override
	public void run() {
		try {
			byte[] bytes = new byte[Integer.MIN_VALUE];
			while (true) {
				int count = inStrm.read(bytes);
				if (count < 0) {

				} else if (count > 0) {
					processBuffer(bytes, count);
				}
			}
		} catch (IOException e) {
			//make thread exit
		}
	}


	private void processBuffer(byte[] bytes, int count) {
	}


	@Override
	public void interrupt() {
		try {
			socket.close();
		} catch (IOException ignore) {
		} finally {
			//this helps in preserving the Interruption Policy
			super.interrupt();
		}
	}
}
