package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * Use {@link java.nio.channels.SeekableByteChannel} open a file, and random access file content.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/rafs.html">rafs</a>
 * @since 2024/5/11
 */
public class FileRandomAccess {

	static void createFile() throws IOException {
		String s = "Hello world";
		Path file = Paths.get("xanadu1.txt");
		Files.createFile(file);
	}

	public static void main(String[] args) throws IOException {

		createFile();

		String s = "I was here!\n";
		byte[] data = s.getBytes();
		ByteBuffer out = ByteBuffer.wrap(data);

		ByteBuffer copy = ByteBuffer.allocate(12);
		Path file = Paths.get("xanadu1.txt");

		try (FileChannel fc = FileChannel.open(file, READ, WRITE)) {
			// Read the first 12 bytes of the file.
			int nread;
			do {
				nread = fc.read(copy);
			} while (nread != -1 && copy.hasRemaining());

			// Write "I was here!" at the beginning of the file.
			fc.position(0);
			while (out.hasRemaining()) {
				fc.write(out);
			}
			out.rewind();

			// Move to the end of the file. Copy the first 12 bytes to the end of the file.
			// Then write "I was here!" again.
			long length = fc.size();
			fc.position(length - 1);
			copy.flip();
			while (copy.hasRemaining()) {
				fc.write(copy);
			}
			while (out.hasRemaining()) {
				fc.write(out);
			}
		}
		catch (IOException e) {
			System.out.println("I/O Exception: " + e);
		}

		Files.deleteIfExists(file);
	}
}
