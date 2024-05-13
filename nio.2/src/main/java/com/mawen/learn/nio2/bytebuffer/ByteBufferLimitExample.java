package com.mawen.learn.nio2.bytebuffer;

import java.io.IOException;
import java.nio.ByteBuffer;

import static com.mawen.learn.nio2.bytebuffer.Helper.*;

/**
 * Use {@link ByteBuffer#limit()} return a value, limit the position must be less than that value, or throws {@link java.nio.BufferUnderflowException}.
 * {@link ByteBuffer#flip()} will set limit value to the current position.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/13
 */
public class ByteBufferLimitExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		ByteBuffer buffer = ByteBuffer.allocate(10);
		showStatus("init buffer", buffer); // position to 0, limit to 10, remaining to 10, capacity to 10

		buffer.putChar('!');
		showStatus("put char buffer", buffer); // position to 2, limit to 10, remaining to 8, capacity to 10

		buffer.flip();
		showStatus("flip buffer", buffer); // position to 0, limit to 2, remaining to 2, capacity to 10

		System.out.println(buffer.get());
		showStatus("get first buffer", buffer); // position to 1, limit to 2, remaining to 1, capacity to 10

		System.out.println(buffer.get());
		showStatus("get second buffer", buffer); // position to 2, limit to 2, remaining to 0, capacity to 10

		buffer.flip();
		showStatus("flip buffer", buffer); // position to 0, limit to 2, remaining to 2, capacity to 10

		System.out.println(buffer.getChar());
		showStatus("get char buffer", buffer); // position to 0, limit to 2, remaining to 2, capacity to 10

//		System.out.println(buffer.get()); // throw exception
//		showStatus("get third buffer", buffer);
	}
}
