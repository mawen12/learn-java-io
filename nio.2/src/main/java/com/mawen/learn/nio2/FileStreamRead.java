package com.mawen.learn.nio2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link Files#newInputStream(Path, OpenOption...)} opens a file for reading,
 * returns an unbuffered input stream for reading bytes from the line.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class FileStreamRead {

	public static void main(String[] args) {

		Path file = Paths.get("xanadu.txt");
		try (InputStream in = Files.newInputStream(file);
			 BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
			String line = null;
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
			}
		}
		catch (IOException e) {
			System.err.println(e);
		}
	}
}
