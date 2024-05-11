package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Use {@link Files#newByteChannel(Path, OpenOption...)} to read a file, it returns an {@link SeekableByteChannel}.
 * {@link SeekableByteChannel} supports both reading and writing.
 *
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileByteChannelRead {

	public static void main(String[] args) {

		Path file = Paths.get("xanadu.txt");

		// Files.newByteChannel() defaults to StandardOpenOption.READ
		try (SeekableByteChannel sbc = Files.newByteChannel(file)) {
			final int BUFFER_CAPACITY = 10;
			ByteBuffer buf = ByteBuffer.allocate(BUFFER_CAPACITY);

			// Read the bytes with the proper encoding for this platform.
			// If you skip this step, you might see foreign or illegible characters.
			String encoding = System.getProperty("file.encoding");
			while (sbc.read(buf) > 0) {
				buf.flip();
				System.out.println(Charset.forName(encoding).decode(buf));
				buf.clear();
			}
		}
		catch (IOException e) {
			throw new RuntimeException(e);
		}

	}
}
