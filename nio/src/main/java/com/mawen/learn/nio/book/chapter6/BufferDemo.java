package com.mawen.learn.nio.book.chapter6;

import java.nio.Buffer;
import java.nio.ByteBuffer;

/**
 * Demonstrate a Byte-Oriented Buffer.ß
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 6-1
 * @since 2024/6/3
 */
public class BufferDemo {

	public static void main(String[] args) {
		Buffer buffer = ByteBuffer.allocate(7);
		System.out.println("Capacity: " + buffer.capacity());
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println("Changing buffer limit to 5");
		buffer.limit(5);
		System.out.println("Limit: " + buffer.limit());
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println("Changing buffer position to 3");
		buffer.position(3);
		System.out.println("Position: " + buffer.position());
		System.out.println("Remaining: " + buffer.remaining());
		System.out.println(buffer);
	}
}
