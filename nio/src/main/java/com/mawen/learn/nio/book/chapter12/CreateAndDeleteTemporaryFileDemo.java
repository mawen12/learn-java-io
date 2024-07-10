package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Registering a Temporary File for Deletion on Application Exit
 *
 * <pre>{@code
 * 	java CreateAndDeleteTemporaryFileDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-23
 * @since 2024/7/4
 */
public class CreateAndDeleteTemporaryFileDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage java CreateAndDeleteTemporaryFileDemo path");
			return;
		}

		Path path = Files.createTempFile(Paths.get(args[0]), "video", null);
		path.toFile().deleteOnExit();
	}
}
