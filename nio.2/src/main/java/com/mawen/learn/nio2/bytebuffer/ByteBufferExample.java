package com.mawen.learn.nio2.bytebuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * ByteBuffer:
 * <li>mark: record an interesting spot in the buffer, when call mark(), then call reset() to restore that position</li>
 * <li>position: when filling the buffer, it is past the last byte filled in the buffer; when emptying the buffer, the position just past the last byte written from the buffer.</li>
 * <li>limit: when filling the buffer, it is the same as the capacity; when emptying the buffer, it is one past the last filled byte in the buffer.</li>
 * <li>remaining: unused size</li>
 * <li>capacity: underlying {@code byte[] byte array} size</li>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://www.mindprod.com/jgloss/bytebuffer.html">bytebuffer</a>
 * @since 2024/5/11
 */
public class ByteBufferExample {

	public static void main(String[] args) throws IOException {

		FileInputStream fis = new FileInputStream("example.sql");

		// allocate a channel to read that file
		FileChannel fc = fis.getChannel();

		// allocate a buffer, as big a chunk as we are willing to handle at a pop.
		ByteBuffer buffer = ByteBuffer.allocate(1024 * 15);
		showStatus("newly allocated read", fc, buffer);

		// read a chunk of raw bytes, up to 15K bytes long, -1 means EOF
		int bytesRead = fc.read(buffer);
		showStatus("after first read", fc, buffer);

		// flip from filling to emptying
		showStatus("before flip", fc, buffer);
		buffer.flip();
		showStatus("after flip", fc, buffer);
		byte[] receive = new byte[1024];
		buffer.get(receive);
		showStatus("after first get", fc, buffer);
		buffer.get(receive);
		showStatus("after second get", fc, buffer);

		// empty buffer to fill with more data
		buffer.clear();
		showStatus("after clear", fc, buffer);
		bytesRead = fc.read(buffer);
		showStatus("after second read", fc, buffer);

		// flip from filling to emptying
		showStatus("before flip", fc, buffer);
		buffer.flip();
		showStatus("after flip", fc, buffer);
		fc.close();
	}

	/**
	 * Display state of channel/buffer.
	 *
	 * @param where description of where we are in the program to label the state snapshot
	 * @param fc    FileChannel reading/writing
	 * @param b     Buffer to display state of:
	 * @throws IOException if I/O problems.
	 */
	private static void showStatus(String where, FileChannel fc, Buffer b) throws IOException {
		System.out.format("%-20s channelPosition: %d bufferPosition: %d limit: %d remaining: %d capacity: %d%n",
				where, fc.position(), b.position(), b.limit(), b.remaining(), b.capacity());
	}
}
