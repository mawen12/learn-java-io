package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.PosixFilePermission;

/**
 * Reading POSIX File Attributes in Bulk
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-15
 * @since 2024/6/26
 */
public class POSIXFileAttributeReadDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java POSIXFileAttributeReadDemo file");
			return;
		}

		Path path = Paths.get(args[0]);
		PosixFileAttributes pfa = Files.readAttributes(path, PosixFileAttributes.class);
		System.out.printf("Group: %s%n", pfa.group());
		for (PosixFilePermission perm : pfa.permissions()) {
			System.out.printf("Permission: %s%n", perm);
		}
	}
}
