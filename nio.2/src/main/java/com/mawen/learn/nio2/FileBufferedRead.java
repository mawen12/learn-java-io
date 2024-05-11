package com.mawen.learn.nio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link Files#newBufferedReader(Path, Charset)} opens a file for reading, returning a {@link BufferedReader}
 * that can be used to read text from a file in an efficient manner.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileBufferedRead {

	public static void main(String[] args) {

		Path file = Paths.get("xanadu.txt");
		Charset charset = StandardCharsets.US_ASCII;

		try (BufferedReader reader = Files.newBufferedReader(file, charset)) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}
}
