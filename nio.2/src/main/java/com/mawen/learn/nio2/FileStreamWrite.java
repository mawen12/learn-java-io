package com.mawen.learn.nio2;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.*;

/**
 * {@link Files#newOutputStream(Path, OpenOption...)} open a file for writing, return an unbuffered output stream.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileStreamWrite {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("xanadu1.txt");
		String s = "Hello World!";
		byte[] data = s.getBytes();

		try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE, APPEND))) {
			out.write(data, 0, data.length);
		}
		catch (IOException e) {
			System.err.println(e);
		}

		Files.deleteIfExists(file);
	}
}
