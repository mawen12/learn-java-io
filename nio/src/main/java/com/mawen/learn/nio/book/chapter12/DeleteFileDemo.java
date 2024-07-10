package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Deleting a File When It exists
 *
 * <pre>{@code
 * 	java DeleteFileDemo x
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-39
 * @since 2024/7/8
 */
public class DeleteFileDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java DeleteFileDemo path");
			return;
		}

		if (!Files.deleteIfExists(Paths.get(args[0]))) {
			System.err.printf("%s does not exist\n", args[0]);
		}
	}
}
