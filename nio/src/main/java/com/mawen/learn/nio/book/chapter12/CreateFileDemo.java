package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Creating an Empty File
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-21
 * @since 2024/7/2
 */
public class CreateFileDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java CreateFileDemo path");
			return;
		}
		Files.createFile(Paths.get(args[0]));
	}
}
