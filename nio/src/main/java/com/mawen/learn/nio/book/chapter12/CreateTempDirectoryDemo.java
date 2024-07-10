package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating an Empty Temporary Directory
 *
 * <pre>{@code
 * 	java CreateTempDirectoryDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-30
 * @since 2024/7/5
 */
public class CreateTempDirectoryDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java CreateTempDirectoryDemo path");
			return;
		}

		Files.createTempDirectory(Paths.get(args[0]), "images");
	}
}
