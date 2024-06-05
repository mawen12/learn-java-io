package com.mawen.learn.nio.book.chapter6;

import java.nio.ByteBuffer;

/**
 * Writing Bytes to and Reading Them from a Buffer.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 6-3
 * @since 2024/6/3
 */
public class BufferWriteReadDemo {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(7);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println();

		buffer.put((byte) 10).put((byte) 20).put((byte) 30);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println();

		for (int i = 0, end = buffer.position(); i < end; i++) {
			System.out.println(buffer.get(i));
		}
	}
}
