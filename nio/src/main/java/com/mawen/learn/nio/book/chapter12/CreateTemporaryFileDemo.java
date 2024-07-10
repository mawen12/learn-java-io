package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating an Empty Temporary File
 *
 * <pre>{@code
 * 	java CreateTemporaryFileDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-22
 * @since 2024/7/3
 */
public class CreateTemporaryFileDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java CreateTemporaryFileDemo path");
			return;
		}

		Files.createTempFile(Paths.get(args[0]), "video", null);
	}
}
