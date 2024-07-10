package com.mawen.learn.nio2.book.chapter13;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Launching a Server That Handles Connections and Reads/Writes Asynchronously
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 13-3
 * @since 2024/7/9
 */
public class AsynchronousServerSocketChannelDemo {

	private final static int PORT = 19090;

	private final static String HOST = "localhost";

	public static void main(String[] args) {
		AsynchronousServerSocketChannel channelServer;

		try {
			channelServer = AsynchronousServerSocketChannel.open();
			channelServer.bind(new InetSocketAddress(HOST, PORT));
			System.out.printf("Server listening at %s%n", channelServer.getLocalAddress());
		}
		catch (IOException e) {
			e.printStackTrace();
			System.err.println("Unable to open or bind server socket channel");
			return;
		}

		Attachment att = new Attachment();
		att.channelServer = channelServer;
		channelServer.accept(att, new ConnectionHandler());

		try {
			Thread.currentThread().join();
		}
		catch (InterruptedException e) {
			System.out.println("Server terminating");
		}
	}

	/**
	 * Bundling Fields That the Server and Clients Use to Communicate
	 *
	 * @see 13-4
	 */
	static class Attachment {

		public AsynchronousServerSocketChannel channelServer;
		public AsynchronousSocketChannel channelClient;
		public boolean isReadMode;
		public ByteBuffer buffer;
		public SocketAddress clientAddr;
	}

	/**
	 * Managing Connections from Clients
	 *
	 * @see 13-5
	 */
	static class ConnectionHandler implements CompletionHandler<AsynchronousSocketChannel, Attachment> {

		@Override
		public void completed(AsynchronousSocketChannel channelClient, Attachment att) {
			try {
				SocketAddress clientAddr = channelClient.getRemoteAddress();
				System.out.printf("Accepted connection from %s%n", clientAddr);

				att.channelServer.accept(att, this);

				Attachment newAtt = new Attachment();
				newAtt.channelServer = att.channelServer;
				newAtt.channelClient = channelClient;
				newAtt.isReadMode = true;
				newAtt.buffer = ByteBuffer.allocate(2048);
				newAtt.clientAddr = clientAddr;
				ReadWriteHandler rwh = new ReadWriteHandler();
				channelClient.read(newAtt.buffer, newAtt, rwh);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void failed(Throwable exc, Attachment attachment) {
			System.out.println("Failed to accept connection");
		}
	}

	/**
	 * Managing Reads and Writes with the Client
	 *
	 * @see 13-6
	 */
	static class ReadWriteHandler implements CompletionHandler<Integer, Attachment> {

		private final static Charset CSUTF8 = StandardCharsets.UTF_8;

		@Override
		public void completed(Integer result, Attachment att) {
			if (result == -1) {
				try {
					att.channelClient.close();
					System.out.printf("Stopped listening to client %s%n", att.clientAddr);
				}
				catch (IOException e) {
					e.printStackTrace();
				}
				return;
			}

			if (att.isReadMode) {
				att.buffer.flip();
				int limit = att.buffer.limit();
				byte[] bytes = new byte[limit];
				att.buffer.get(bytes, 0, limit);
				System.out.printf("Client at %s sends message: %s%n", att.clientAddr, new String(bytes, CSUTF8));

				att.isReadMode = false;

				att.buffer.rewind();
				att.channelClient.write(att.buffer, att, this);
			}
			else {
				att.isReadMode = true;

				att.buffer.clear();
				att.channelClient.read(att.buffer, att, this);
			}
		}

		@Override
		public void failed(Throwable exc, Attachment attachment) {
			System.out.println("Connection with client broken");
		}
	}
}
