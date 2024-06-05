package com.mawen.learn.nio.book.charpter7;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

/**
 * Copying Bytes from an Input Channel to an Output Chanel
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 7-1
 * @since 2024/6/4
 */
public class ChannelDemo {

	public static void main(String[] args) {
		ReadableByteChannel src = Channels.newChannel(System.in);
		WritableByteChannel dest = Channels.newChannel(System.out);

		try {
			copy(src, dest);
//			copyAlt(src, dest);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		finally {
			try {
				src.close();
				dest.close();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Approach one:
	 *
	 * <p>minimize operating system I/O calls (via the write() method calls),
	 * although more data may end up being copied as a result of the {@link ByteBuffer#compact()}
	 *
	 * <p>one loop, one write
	 */
	static void copy(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		// allocate a direct buffer for performing I/O more efficiently
		ByteBuffer buffer = ByteBuffer.allocateDirect(100);
		while (src.read(buffer) != -1) {
			print("read", buffer);
			buffer.flip();
			print("flip", buffer);
			dest.write(buffer);
			print("write", buffer);
			// because write() might not drain the buffer,
			// compact() ensures that unwritten buffer content isn't overwritten during the next read operation
			buffer.compact();
			print("compact", buffer);
		}
		// may be write() might not drain the buffer, so use hasRemaining() and write() to completely drain the buffer
		buffer.flip();
		while (buffer.hasRemaining()) {
			dest.write(buffer);
		}
	}

	/**
	 * Approach two:
	 *
	 * <p>eliminate data copying, although more operating system I/O calls might occur.
	 *
	 * <p>one loop, multi write
	 */
	static void copyAlt(ReadableByteChannel src, WritableByteChannel dest) throws IOException {
		// allocate a direct buffer for performing I/O more efficiently
		ByteBuffer buffer = ByteBuffer.allocateDirect(2048);
		while (src.read(buffer) != -1) {
			print("read", buffer);
			buffer.flip();
			print("flip", buffer);
			while (buffer.hasRemaining()) {
				dest.write(buffer);
			}
			print("write", buffer);
			buffer.clear();
			print("clear", buffer);
		}
	}

	static void print(String title, ByteBuffer buffer) {
		System.out.println("After " + title + "...");
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println();
	}
}
