package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Deleting a File Tree
 *
 * <pre>{@code
 * 	java DeleteFileTreeDemo a
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-46
 * @since 2024/7/8
 */
public class DeleteFileTreeDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java DeleteFileTreeDemo path");
			return;
		}

		Files.walkFileTree(Paths.get(args[0]), new DeleteVisitor());
	}

	static class DeleteVisitor extends SimpleFileVisitor<Path> {
		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			if (exc == null) {
				if (Files.deleteIfExists(dir)) {
					System.out.printf("deleted directory %s%n", dir);
				}
				else {
					System.out.printf("couldn't delete directory %s%n", dir);
				}
			}
			else {
				throw exc;
			}

			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			if (Files.deleteIfExists(file)) {
				System.out.printf("deleted regular file %s%n", file);
			}
			else {
				System.out.printf("couldn't delete regular file %s%n", file);
			}
			return FileVisitResult.CONTINUE;
		}
	}

}
