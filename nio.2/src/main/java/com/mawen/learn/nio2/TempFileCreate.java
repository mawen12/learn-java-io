package com.mawen.learn.nio2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;

/**
 * Use {@link Files#createTempFile(String, String, FileAttribute[])} to create a temporary file.
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/io/file.html">file</a>
 * @since 2024/5/11
 */
public class TempFileCreate {

	public static void main(String[] args) {

		try {
			Path tempFile = Files.createTempFile(null, ".myapp");
			System.out.format("The temporary file has been created: %s%n", tempFile);
			Files.deleteIfExists(tempFile);
		}
		catch (IOException e) {
			System.err.format("IOException: %s%n", e);
		}

	}
}
