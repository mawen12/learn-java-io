package com.mawen.learn.nio.book.charpter6;

import java.nio.ByteBuffer;

/**
 * Creating Byte-Oriented Buffers vis Allocation and Wrapping.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 6-2
 * @since 2024/6/3
 */
public class BufferCreateDemo {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(10);
		if (buffer.hasArray()) {
			System.out.println("buffer array: " + buffer.array());
			System.out.println("Buffer array offset: " + buffer.arrayOffset());
			System.out.println("Capacity: " + buffer.capacity());
			System.out.println("Limit: " + buffer.limit());
			System.out.println("Position: " + buffer.position());
			System.out.println("Remaining: " + buffer.remaining());
			System.out.println();
		}

		byte[] bytes = new byte[200];
		ByteBuffer buffer2 = ByteBuffer.wrap(bytes);
		buffer2 = ByteBuffer.wrap(bytes, 10, 50);
		if (buffer2.hasArray()) {
			System.out.println("buffer2 array: " + buffer2.array());
			System.out.println("Buffer2 array offset: " + buffer2.arrayOffset());
			System.out.println("Capacity: " + buffer2.capacity());
			System.out.println("Limit: " + buffer2.limit());
			System.out.println("Position: " + buffer2.position());
			System.out.println("Remaining: " + buffer2.remaining());
		}
	}
}
