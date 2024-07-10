package com.mawen.learn.nio2.book.chapter13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutionException;

/**
 * Launching
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 13-7
 * @since 2024/7/9
 */
public class AsynchronousSocketChannelDemo {

	private final static Charset CSUTF8 = StandardCharsets.UTF_8;

	private final static int PORT = 19090;

	private static final String HOST = "localhost";

	public static void main(String[] args) {
		AsynchronousSocketChannel channel;

		try {
			channel = AsynchronousSocketChannel.open();
		}
		catch (IOException e) {
			System.err.println("Unable to open client socket channel");
			return;
		}

		try {
			channel.connect(new InetSocketAddress(HOST, PORT)).get();
			System.out.printf("Client at %s connected%n", channel.getLocalAddress());
		}
		catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
			System.err.println("Server not responding");
			return;
		}
		catch (IOException e) {
			System.err.println("Unable to obtain client socket channel's local address");
			return;
		}

		Attachment att = new Attachment();
		att.channel = channel;
		att.isReadMode = false;
		att.buffer = ByteBuffer.allocate(2048);
		att.mainThd = Thread.currentThread();

		byte[] data = "Hello".getBytes(CSUTF8);
		att.buffer.put(data);
		att.buffer.flip();
		channel.write(att.buffer, att, new ReadWriteHandler());

		try {
			att.mainThd.join();
		}
		catch (InterruptedException e) {
			System.out.println("Client terminating");
		}
	}

	/**
	 * Binding Fields for Subsequent Communication
	 *
	 * @see 13-8
	 */
	static class Attachment {
		public AsynchronousSocketChannel channel;
		public boolean isReadMode;
		public ByteBuffer buffer;
		public Thread mainThd;
	}

	/**
	 * Managing Reads and Writes with the Server
	 *
	 * @see 13-9
	 */
	static class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

		private final static Charset CSUTF8 = StandardCharsets.UTF_8;

		private final BufferedReader conReader = new BufferedReader(new InputStreamReader(System.in));

		@Override
		public void completed(Integer result, Attachment att) {
			if (att.isReadMode) {
				att.buffer.flip();
				int limit = att.buffer.limit();
				byte[] bytes = new byte[limit];
				att.buffer.get(bytes, 0, limit);
				String msg = new String(bytes, CSUTF8);
				System.out.printf("Server responded: %s%n", msg);

				try {
					msg = "";
					while (msg.isEmpty()) {
						System.out.print("Enter message (\"end\" to quit): ");
						msg = conReader.readLine();
					}
					if (msg.equalsIgnoreCase("end")) {
						att.mainThd.interrupt();
						return;
					}
				}
				catch (IOException e) {
					e.printStackTrace();
					System.err.println("Unable to read from console");
				}

				att.isReadMode = false;
				att.buffer.clear();
				byte[] data = msg.getBytes(CSUTF8);
				att.buffer.put(data);
				att.buffer.flip();
				att.channel.write(att.buffer, att, this);
			}
			else {
				att.isReadMode = true;

				att.buffer.clear();
				att.channel.read(att.buffer, att, this);
			}
		}

		@Override
		public void failed(Throwable exc, Attachment att) {
			System.err.println("Server not responding");
			System.exit(1);
		}
	}
}

