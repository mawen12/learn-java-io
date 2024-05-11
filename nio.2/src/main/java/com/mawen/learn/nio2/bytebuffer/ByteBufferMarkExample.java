package com.mawen.learn.nio2.bytebuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Use {@link ByteBuffer#mark()} we can remember current position,
 * then use {@link ByteBuffer#reset()} to return it.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class ByteBufferMarkExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		FileInputStream fis = new FileInputStream("example.sql");

		FileChannel fc = fis.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(10);
		showStatus("init buffer", buffer);

		fc.read(buffer);
		showStatus("after read", buffer); // position to 10(last byte index + 1), remaining to 0

		buffer.position(0);
		showStatus("position to zero", buffer); // position to zero, remaining to 10

		buffer.mark();
		buffer.position(2);
		showStatus("position to two", buffer); // position to 2, remaining to 8

		buffer.reset();
		showStatus("reset buffer", buffer);// position to 0, remaining to 10

		fc.close();
	}

	private static void showTitle() {
		System.out.format("%-20s %-3s %-3s %-3s %-3s%n",
				"Title", "bufferPosition", "limit", "remaining", "capacity");

	}

	private static void showStatus(String where, Buffer b) throws IOException {
		System.out.format("%-25s %-11d %-6d %-7d %-3d%n",
				where, b.position(), b.limit(), b.remaining(), b.capacity());
	}
}
