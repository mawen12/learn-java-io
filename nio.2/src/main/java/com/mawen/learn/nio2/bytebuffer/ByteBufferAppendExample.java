package com.mawen.learn.nio2.bytebuffer;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class ByteBufferAppendExample {

	public static void main(String[] args) throws IOException {
		showTitle();

		Path file = Paths.get("small.txt");
		Files.write(file, "Hello World".getBytes());

		ByteChannel fc = Files.newByteChannel(file, WRITE, READ);
		ByteBuffer buffer = ByteBuffer.allocate(20);
		showStatus("init buffer", buffer);

		fc.read(buffer);
		showStatus("after read", buffer);

		buffer.putChar('!');
		showStatus("after putChart", buffer);

		buffer.flip();
		showStatus("after flip", buffer);

		fc.write(buffer);
		showStatus("after write", buffer);

		System.out.println(Files.readAllLines(file));;
	}

	private static void showTitle() {
		System.out.format("%-20s %-3s %-3s %-3s %-3s%n",
				"Title", "bufferPosition", "limit", "remaining", "capacity");

	}

	private static void showStatus(String where, Buffer b) throws IOException {
		System.out.format("%-25s %-11d %-6d %-7d %-3d%n",
				where, b.position(), b.limit(), b.remaining(), b.capacity());
	}
}
