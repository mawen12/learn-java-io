package com.mawen.learn.nio.book.chapter6;

import java.nio.CharBuffer;

/**
 * Writing Characters to and Reading Them from a Character Buffer.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 6-4
 * @since 2024/6/3
 */
public class BufferFlipDemo {

	public static void main(String[] args) {
		String[] poem = {
				"Roses are read",
				"Violets are blue",
				"Sugar is sweet",
				"And so are your"
		};

		CharBuffer buffer = CharBuffer.allocate(50);

		for (int i = 0; i < poem.length; i++) {
			// Fill the buffer
			for (int j = 0; j < poem[i].length(); j++) {
				buffer.put(poem[i].charAt(j));
			}

			// Flip the buffer so that its contents can be read.
			buffer.flip();

			// Drain the buffer
			while (buffer.hasRemaining()) {
				System.out.print(buffer.get());
			}

			// Empty the buffer to prevent BufferOverflowException
			buffer.clear();

			System.out.println();
		}
	}
}
