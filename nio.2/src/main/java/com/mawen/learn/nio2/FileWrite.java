package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Write all bytes to small files.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class FileWrite {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("xanadu1.txt");

		byte[] buf = "Hello World".getBytes();
		Files.write(file, buf);

		Files.deleteIfExists(file);
	}
}
