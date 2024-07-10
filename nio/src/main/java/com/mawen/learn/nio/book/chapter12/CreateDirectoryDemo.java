package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating an Empty Directory
 *
 * <pre>{@code
 * 	java CreateDirectoryDemo x
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-29
 * @since 2024/7/5
 */
public class CreateDirectoryDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java CreateDirectoryDemo path");
			return;
		}

		Files.createDirectory(Paths.get(args[0]));
	}
}
