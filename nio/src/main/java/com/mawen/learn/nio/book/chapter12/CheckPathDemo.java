package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Checking Paths for Various Conditions
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-20
 * @since 2024/7/2
 */
public class CheckPathDemo {

	public static void main(String[] args) throws IOException {
		if (args.length < 1 || args.length > 2) {
			System.err.println("usage: java CheckPathDemo path1 [path2]");
			return;
		}

		Path path1 = Paths.get(args[0]);
		System.out.printf("Path1: %s%n", path1);
		System.out.printf("Exists: %b%n", Files.exists(path1));
		System.out.printf("Not Exists: %b%n", Files.notExists(path1));
		System.out.printf("Is directory: %b%n", Files.isDirectory(path1));
		System.out.printf("Is executable: %b%n", Files.isExecutable(path1));

		try {
			System.out.printf("Hidden: %b%n", Files.isHidden(path1));
		}
		catch (IOException e) {
			e.printStackTrace();
		}

		System.out.printf("Is readable: %b%n", Files.isReadable(path1));
		System.out.printf("Is regular file: %b%n", Files.isRegularFile(path1));
		System.out.printf("Is writable: %b%n", Files.isWritable(path1));

		if (args.length == 2) {
			Path path2 = Paths.get(args[1]);
			System.out.printf("Path2: %s%n", path2);
			System.out.printf("Is same path: %b%n", Files.isSameFile(path1, path2));
		}
	}
}
