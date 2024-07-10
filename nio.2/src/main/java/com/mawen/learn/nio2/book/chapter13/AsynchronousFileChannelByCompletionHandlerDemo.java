package com.mawen.learn.nio2.book.chapter13;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Reading Bytes from a File and Displaying the Results in a Completion Handler
 *
 * <pre>{@code
 * 	java AsynchronousFileChannelByCompletionHandlerDemo poem.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 13-2
 * @since 2024/7/9
 */
public class AsynchronousFileChannelByCompletionHandlerDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java AsynchronousFileChannelByCompletionHandlerDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
		ByteBuffer buf = ByteBuffer.allocate(1024);
		Thread mainThd = Thread.currentThread();
		ch.read(buf, 0, null, new CompletionHandler<Integer, Void>() {
			@Override
			public void completed(Integer result, Void attachment) {
				System.out.println("Bytes read = " + result);
				mainThd.interrupt();
			}

			@Override
			public void failed(Throwable exc, Void attachment) {
				System.out.println("Failure: " + exc);
				mainThd.interrupt();
			}
		});
		System.out.println("Waiting for completion");

		try {
			mainThd.join();
		}
		catch (InterruptedException e) {
			System.out.println("Terminating");
		}
		ch.close();
	}
}
