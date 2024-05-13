package com.mawen.learn.nio2.bytebuffer;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.mawen.learn.nio2.bytebuffer.Helper.*;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/13
 */
public class ByteBufferReadExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		Path file = Paths.get("xanadu.txt");

		ByteChannel fc = Files.newByteChannel(file, StandardOpenOption.READ);
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		showStatus("init buffer", buffer); // position to 0, capacity to 1024, limit and remaining as same as capacity

		fc.read(buffer);
		showStatus("read buffer", buffer); // position to 148, remaining to 876, limit and remaining as same as capacity

		buffer.flip();
		showStatus("flip buffer", buffer); // position to 0, limit to 148, remaining to 148

		byte[] bytes = new byte[buffer.limit()];
		buffer.get(bytes);
		showStatus("get from buffer", buffer); // position to 148, limit to 148, remaining to 0

		System.out.println(new String(bytes));

		fc.close();
	}
}
