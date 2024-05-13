package com.mawen.learn.nio2.bytebuffer;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.mawen.learn.nio2.bytebuffer.Helper.*;
import static java.nio.file.StandardOpenOption.*;

/**
 * Use {@link java.nio.channels.FileChannel#read(ByteBuffer)} to get end of file position.
 * use {@link ByteBuffer#put(byte)} add to end of buffer,
 * use {@link }
 *
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class ByteBufferAppendExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		Path file = Paths.get("small.txt");
		Files.write(file, "Hello World".getBytes(StandardCharsets.UTF_8));

		ByteChannel fc = Files.newByteChannel(file, WRITE, READ);
		ByteBuffer buffer = ByteBuffer.allocate(20);
		showStatus("init buffer", buffer); // position to 0, limit to 20, remaining to 20, capacity to 20

		int read = fc.read(buffer);
		showStatus("after read", buffer); // position to 11(0-10 store hello world), limit to 20, remaining to 9, capacity to 20

		byte b = 33;
		buffer.put(b);
		showStatus("after putChart", buffer); // position to 13, limit to 20, remaining to 7, capacity to 20

		buffer.flip();
		showStatus("after flip", buffer); // position to 0, limit to 13, remaining to 13, capacity to 20

		buffer.position(read);
		showStatus("after position", buffer); // position to 11, limit to 13, remaining to 2, capacity to 20

		fc.write(buffer);
		showStatus("after write", buffer); // position to 13, limit to 13, remaining to 0, capacity to 20

		System.out.println(Files.readAllLines(file));

		Files.deleteIfExists(file);
	}

	private static void generateSmallFile() throws IOException {
		Path path = Paths.get("small.txt");

		String s = "Hello World";
		try (BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {
			writer.write(s, 0, s.length());
		}
	}

}
