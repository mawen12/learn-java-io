package com.mawen.learn.nio.book.charpter6;

import java.nio.ByteBuffer;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/6/4
 */
public class BufferCompactDemo2 {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);

		buffer.put((byte) 1).put((byte) 2).put((byte) 3).put((byte) 4);
		print("Write", buffer);

		buffer.flip();
		print("flip", buffer);

		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
		print("get", buffer);

		buffer.compact();
		print("compact", buffer);

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
