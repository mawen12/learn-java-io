package com.mawen.learn.nio2.book.chapter13;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Reading Bytes from a File and Polling the Returned Future for Completion
 *
 * <pre>{@code
 * 	java AsynchronousFileChannelDemo poem.txt
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 13-1
 * @since 2024/7/9
 */
public class AsynchronousFileChannelByFutureDemo {

	public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
		if (args.length != 1) {
			System.err.println("usage: java AsynchronousFileChannelDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		AsynchronousFileChannel ch = AsynchronousFileChannel.open(path);
		ByteBuffer buf = ByteBuffer.allocate(1024);

		Future<Integer> result = ch.read(buf, 0);
		while (!result.isDone()) {
			System.out.println("Sleeping...");
			Thread.sleep(500);
		}
		System.out.println("Finished = " + result.isDone());
		System.out.printf("Bytes read = " + result.get());
		ch.close();
	}
}
