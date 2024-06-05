package com.mawen.learn.nio.book.chapter7;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;


/**
 * DatagramChannel for Server
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-9
 * @since 2024/6/5
 */
public class ClientDatagramServer {

	static final int PORT = 9999;

	public static void main(String[] args) {
		System.out.println("server starting and listening on port " + PORT + " for incoming requests...");

		// create a datagram channel
		try (DatagramChannel dcServer = DatagramChannel.open()) {
			// bind channel to 9999
			dcServer.socket().bind(new InetSocketAddress(PORT));

			ByteBuffer symbol = ByteBuffer.allocate(4);
			ByteBuffer payload = ByteBuffer.allocate(16);

			while (true) {
				payload.clear();
				symbol.clear();

				// check it is preparation for receiving new information from a client
				SocketAddress sa = dcServer.receive(symbol);
				if (sa == null) {
					return;
				}

				System.out.println("Received request from " + sa);
				String stockSymbol = new String(symbol.array(), 0, 4);
				System.out.println("Symbol: " + stockSymbol);

				// check the stock symbol to see if it equals MSFT
				if (stockSymbol.toUpperCase().equals("MSFT")) {
					payload.putFloat(0, 37.40f); // open share price
					payload.putFloat(4, 37.22f); // low share price
					payload.putFloat(8, 37.58f); // high share price
					payload.putFloat(12, 37.41f); // close share price
				}
				else {
					payload.putFloat(0, 0.0f);
					payload.putFloat(4, 0.0f);
					payload.putFloat(8, 0.0f);
					payload.putFloat(12, 0.0f);
				}
				dcServer.send(payload, sa);
			}
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
