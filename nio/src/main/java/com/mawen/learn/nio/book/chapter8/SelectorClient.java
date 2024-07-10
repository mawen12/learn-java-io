package com.mawen.learn.nio.book.chapter8;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Date;

/**
 * Receiving Time from the Server
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 8-2
 * @since 2024/6/17
 */
public class SelectorClient {

	final static int DEFAULT_PORT = 9999;

	static ByteBuffer bb = ByteBuffer.allocateDirect(8);

	public static void main(String[] args) {
		int port = DEFAULT_PORT;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		}

		try {
			SocketChannel sc = SocketChannel.open();
			InetSocketAddress addr = new InetSocketAddress("localhost", port);
			sc.connect(addr);

			long time = 0;
			while (sc.read(bb) != -1) {
				bb.flip();
				while (bb.hasRemaining()) {
					// 实际传递的是 long，为64-bit的数值, bb.get()每次获取8-bit的值，因此会将对应的值拆分，因为byte是有符号的数值，导致可能出现首位1而补码未负值的情况。
					// time <<= 8 因此每次仅获取8bit，而且是高位，所以需要值向左移动，便于填补低位
					time <<= 8;
					// bb.get() returns a 32-bit integer representation of an 8-bit byte
					// bb.get() & 255 就是为了解决补位的情况
					// time |= 是为了将计算的值录入到低位
					time |= bb.get() & 255;
				}
				bb.clear();
			}
			System.out.println(new Date(time));
			sc.close();
		}
		catch (IOException e) {
			System.err.println("I/O error: " + e.getMessage());
		}
	}
}
