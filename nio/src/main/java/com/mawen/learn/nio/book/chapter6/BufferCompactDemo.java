package com.mawen.learn.nio.book.chapter6;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * {@link ByteBuffer#compact()} will copy the elements of [{@link ByteBuffer#position()}, {@link ByteBuffer#limit()}) to [0, {@link ByteBuffer#limit()} - {@link ByteBuffer#position()}).
 * Then the {@link ByteBuffer#position()} will be set to {@link ByteBuffer#limit()} - {@link ByteBuffer#position()}.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/6/3
 */
public class BufferCompactDemo {

	public static void main(String[] args) {
		ByteBuffer buffer = ByteBuffer.allocate(7);
		buffer.put((byte) 0).put((byte) 0).put((byte) 0);
		buffer.put((byte) 1).put((byte) 2).put((byte) 3);
		buffer.position(3);

		print(buffer);

		buffer.compact();

		print(buffer);
	}


	static void print(ByteBuffer buffer) {
		System.out.println("Buffer elements: " + Arrays.toString(buffer.array()));
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println();
	}
}
