package com.mawen.learn.nio2;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link Files#newBufferedWriter(Path, Charset, OpenOption...)} writes to a file using a {@link BufferedWriter}.
 *
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileBufferedWrite {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("xanadu1.txt");
		Charset charset = StandardCharsets.US_ASCII;
		String s = "Hello World";
		try (BufferedWriter writer = Files.newBufferedWriter(file, charset)) {
			writer.write(s, 0, s.length());
		}
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

		Files.deleteIfExists(file);
	}
}
