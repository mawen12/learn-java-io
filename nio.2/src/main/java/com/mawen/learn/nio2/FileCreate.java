package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileAttribute;

/**
 * Use {@link Files#createFile(Path, FileAttribute[])} to create an empty file.
 *
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @since 2024/5/11
 */
public class FileCreate {

	public static void main(String[] args) throws IOException {

		Path file = Paths.get("xanadu1.txt");

		try {
			Files.createFile(file);
		}
		catch (FileAlreadyExistsException e) {
			System.err.format("file named %s already exists%n", file);
		}
		catch (IOException e) {
			System.err.format("createFile error: %s%n", e);
		}

		Files.deleteIfExists(file);
	}
}
