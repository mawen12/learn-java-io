package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Read all bytes or lines from small files.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileRead {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("xanadu.txt");

		// readAllBytes
		byte[] fileArray = Files.readAllBytes(file);
		System.out.println(new String(fileArray, StandardCharsets.UTF_8));

		// readAllLines
		List<String> lines = Files.readAllLines(file);
		System.out.println(lines);
	}
}
