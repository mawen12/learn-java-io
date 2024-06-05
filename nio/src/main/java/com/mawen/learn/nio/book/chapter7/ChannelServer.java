package com.mawen.learn.nio.book.chapter7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * ServerSocketChannel
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-7
 * @since 2024/6/5
 */
public class ChannelServer {

	public static void main(String[] args) throws IOException {
		System.out.println("Starting server...");

		// obtain a server socket channel
		ServerSocketChannel ssc = ServerSocketChannel.open();
		// access the ServerSocket peer object and use this object to bind the socket/channel to port 9999
		ssc.socket().bind(new InetSocketAddress(9999));
		// configure the server socket channel to be nonblocking
		ssc.configureBlocking(false);

		// a message that identify the server socket channel's local socket address
		String msg = "Local address: " + ssc.socket().getLocalSocketAddress();
		// create a byte buffer
		ByteBuffer buffer = ByteBuffer.wrap(msg.getBytes(StandardCharsets.UTF_8));

		while (true) {
			System.out.print(".");
			// check for incoming connection
			SocketChannel sc = ssc.accept();
			if (sc != null) {
				System.out.println();
				// obtain the remote socket address
				System.out.println("Received connection from " + sc.socket().getRemoteSocketAddress());
				buffer.rewind();
				// write buffer content to the socket channel
				sc.write(buffer);
				// close socket channel
				sc.close();
			}
			else {
				try {
					// if a connection isn't detected, sleep for a faction of a second
					Thread.sleep(1000);
				}
				catch (InterruptedException e) {
					// shouldn't happen
					assert false;
				}
			}
		}
	}
}
