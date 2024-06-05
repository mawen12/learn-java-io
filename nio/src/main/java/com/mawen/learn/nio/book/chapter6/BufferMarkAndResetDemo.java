package com.mawen.learn.nio.book.chapter6;

import java.nio.ByteBuffer;

/**
 * Marking the Current Buffer Position and Resetting the Current Position to the Marked Position
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 6-5
 * @since 2024/6/3
 */
public class BufferMarkAndResetDemo {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(7);
		buffer.put((byte) 10).put((byte) 20).put((byte) 30).put((byte) 40);
		buffer.limit(4);
		buffer.position(1).mark().position(3);

		System.out.println(buffer.get());
		System.out.println();
		buffer.reset();
		while (buffer.hasRemaining()) {
			System.out.println(buffer.get());
		}
	}
}
