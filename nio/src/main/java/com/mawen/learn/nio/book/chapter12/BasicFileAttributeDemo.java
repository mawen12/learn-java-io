package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Reading Basic File Attributes in Bulk
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-11
 * @since 2024/6/24
 */
public class BasicFileAttributeDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java BasicFileAttributeDemo path");
			return;
		}

		Path path = Paths.get(args[0]);
		BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
		System.out.printf("Creation time: %s%n", bfa.creationTime());
		System.out.printf("File key: %s%n", bfa.fileKey());
		System.out.printf("Is directory: %s%n", bfa.isDirectory());
		System.out.printf("Is other: %s%n", bfa.isOther());
		System.out.printf("Is regular file: %s%n", bfa.isRegularFile());
		System.out.printf("Is symbolic link: %s%n", bfa.isSymbolicLink());
		System.out.printf("Last access time: %s%n", bfa.lastAccessTime());
		System.out.printf("Last modified time: %s%n", bfa.lastModifiedTime());
		System.out.printf("Size: %d%n", bfa.size());
	}

}
