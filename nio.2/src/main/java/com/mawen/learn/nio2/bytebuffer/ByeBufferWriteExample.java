package com.mawen.learn.nio2.bytebuffer;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.mawen.learn.nio2.bytebuffer.Helper.*;

/**
 * Use {@link java.nio.channels.FileChannel#write(ByteBuffer)} to read from bye buffer, and write to file.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://www.mindprod.com/jgloss/bytebuffer.html">bytebuffer</a>
 * @since 2024/5/13
 */
public class ByeBufferWriteExample {

	public static void main(String[] args) throws IOException {

		showTitle();

		Path path = Paths.get("test.txt");
		SeekableByteChannel fc = Files.newByteChannel(path, StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.READ);

		String s = "Hello World";
		ByteBuffer buffer = ByteBuffer.allocate(1024);
		showStatus("init buffer", buffer); // position to 0, limit to 1024, remaining to 1024, capacity to 1024

		buffer.put(s.getBytes());
		showStatus("put buffer", buffer); // position to 11, limit to 1024, remaining to 1013, capacity to 1024

		buffer.flip();
		showStatus("flip buffer", buffer); // position to 0, limit to 11, remaining to 11, capacity to 1024

		// only write position from 0 to 11
		fc.write(buffer);
		showStatus("read buffer", buffer); // position to 11, limit to 11, remaining to 0, capacity to 1024

		System.out.println(Files.readAllLines(path));

		fc.close();

		Files.deleteIfExists(path);
	}
}
