package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.FileTime;
import java.time.Instant;

/**
 * Getting and Setting Single Basic File Attribute
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-12
 * @since 2024/6/24
 */
public class BasicFileAttributeOperationDemo {

	public static void main(String[] args) throws IOException {
		if (args.length < 1 || args.length > 2) {
			System.err.println("usage: java BasicFileAttributeOperationDemo path [set]");
			return;
		}

		Path path = Paths.get(args[0]);
		boolean setAttr = false;
		if (args.length == 2) {
			setAttr = true;
		}

		System.out.printf("Creating time: %s%n", Files.getAttribute(path, "creationTime"));
		System.out.printf("File key: %s%n", Files.getAttribute(path, "fileKey"));
		System.out.printf("Is directory: %s%n", Files.getAttribute(path, "isDirectory"));
		System.out.printf("Is other: %s%n", Files.getAttribute(path, "isOther"));
		System.out.printf("Is regular file: %s%n", Files.getAttribute(path, "isRegularFile"));
		System.out.printf("Is symbolic link: %s%n", Files.getAttribute(path, "isSymbolicLink"));
		System.out.printf("Last access time: %s%n", Files.getAttribute(path, "lastAccessTime"));
		System.out.printf("Last modified time: %s%n", Files.getAttribute(path, "lastModifiedTime"));
		System.out.printf("Size: %s%n", Files.getAttribute(path, "size"));

		if (setAttr) {
			Files.setAttribute(path, "lastModifiedTime", FileTime.from(Instant.now().plusSeconds(60 * 60 * 24 * 7)));
			System.out.printf("Last modified time: %s%n", Files.getAttribute(path, "lastModifiedTime"));
		}
	}
}
