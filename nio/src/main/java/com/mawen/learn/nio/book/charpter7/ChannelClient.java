package com.mawen.learn.nio.book.charpter7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * SocketChannel
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-8
 * @since 2024/6/5
 */
public class ChannelClient {

	public static void main(String[] args) {
		// obtain a socket channel
		try (SocketChannel sc = SocketChannel.open()) {
			// configure socket channel to be nonblocking
			sc.configureBlocking(false);

			// create an address to the previous channel server application
			InetSocketAddress addr = new InetSocketAddress("localhost", 9999);
			// initiate a connection to the address
			sc.connect(addr);

			// repeatedly invoke finishConnection() util the method returns true
			// when return ture, indicate a connection to the remote server application
			while (!sc.finishConnect()) {
				System.out.println("waiting to finish connection");
			}

			int readBytes;
			ByteBuffer buffer = ByteBuffer.allocate(200);

			// repeatedly read content into the buffer
			while ((readBytes = sc.read(buffer)) != -1) {
				System.out.println("Read size: " + readBytes);
				if (readBytes > 0) {
					buffer.flip();
					// output the buffer content to the standard output stream
					while (buffer.hasRemaining()) {
						System.out.print((char)buffer.get());
					}
					buffer.clear();
				}
			}
			System.out.println();
			System.out.println("End...");
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
