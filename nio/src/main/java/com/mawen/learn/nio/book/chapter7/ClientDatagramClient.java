package com.mawen.learn.nio.book.chapter7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-10
 * @since 2024/6/5
 */
public class ClientDatagramClient {

	final static int PORT = 9999;

	public static void main(String[] args) {
		// verify a single command-line argument has been specified
		if (args.length != 1) {
			System.err.println("usage: java ClientDatagramClient stocksymbol");
			return;
		}

		// create a datagram channel
		try (DatagramChannel dcClient = DatagramChannel.open()) {
			// create a pair of byte buffer
			ByteBuffer symbol = ByteBuffer.wrap(args[0].getBytes());
			ByteBuffer response = ByteBuffer.allocate(16);

			// create a socket address for communicating with
			InetSocketAddress sa = new InetSocketAddress("localhost", PORT);
			// send the symbol buffer to the server
			dcClient.send(symbol, sa);

			// receive a response buffer from the service, store its payload in the response server
			System.out.println("Receiving datagram from " + dcClient.receive(response));
			System.out.println("Open price: " + response.getFloat(0));
			System.out.println("Low price: " + response.getFloat(4));
			System.out.println("High price: " + response.getFloat(8));
			System.out.println("Close price: " + response.getFloat(12));
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
