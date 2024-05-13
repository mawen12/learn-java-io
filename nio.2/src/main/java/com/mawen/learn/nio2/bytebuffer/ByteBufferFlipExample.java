package com.mawen.learn.nio2.bytebuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;

import com.sun.org.apache.xml.internal.resolver.helpers.FileURL;

/**
 * Use {@link ByteBuffer#flip()} to flip buffer, it will set limit to current position
 * and then position is set to zero, discard the mark.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class ByteBufferFlipExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		FileInputStream fis = new FileInputStream("example.sql");

		FileChannel fc = fis.getChannel();

		ByteBuffer buffer = ByteBuffer.allocate(10);
		showStatus("init buffer", buffer); // position to 0, capacity to 10, limit and remaining are same as capacity

		fc.read(buffer);
		showStatus("after read", buffer); // position to 10(last byte index + 1), remaining to 0

		buffer.flip();
		showStatus("after first flip", buffer); // position to 0, remaining to 10

		buffer.flip();
		showStatus("after second flip", buffer); // position to 0, limit to 0, remaining to 0

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
