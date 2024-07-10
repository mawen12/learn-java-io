package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Obtaining and Outputting All Entries in a Directory
 *
 * <pre>{@code
 * 	java DirectoryStreamDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-32
 * @since 2024/7/5
 */
public class DirectoryStreamDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java DirectoryStreamDemo dirpath");
			return;
		}

		Path path = Paths.get(args[0]);
		DirectoryStream<Path> ds = Files.newDirectoryStream(path);
		for (Path p : ds) {
			System.out.println(p);
		}
	}
}
