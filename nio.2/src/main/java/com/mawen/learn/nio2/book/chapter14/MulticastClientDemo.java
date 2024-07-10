package com.mawen.learn.nio2.book.chapter14;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.StandardProtocolFamily;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.nio.channels.MembershipKey;

/**
 * Demonstrating a Channel-Based Multicast Client
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 14-4
 * @since 2024/7/10
 */
public class MulticastClientDemo {

	final static int PORT = 9999;

	public static void main(String[] args) throws IOException {
		NetworkInterface ni = NetworkInterface.getByInetAddress(InetAddress.getLocalHost());
		DatagramChannel dc = DatagramChannel.open(StandardProtocolFamily.INET)
				.setOption(StandardSocketOptions.SO_REUSEADDR, true)
				.bind(new InetSocketAddress(PORT))
				.setOption(StandardSocketOptions.IP_MULTICAST_IF, ni);

		InetAddress group = InetAddress.getByName("239.255.0.1");
		MembershipKey key = dc.join(group, ni);

		ByteBuffer response = ByteBuffer.allocate(50);
		while (true) {
			dc.receive(response);
			response.flip();
			while (response.hasRemaining()) {
				System.out.print((char)response.get());
			}
			System.out.println();
			response.clear();
		}

	}
}
