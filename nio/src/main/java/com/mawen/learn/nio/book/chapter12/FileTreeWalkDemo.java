package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Visiting a File Tree and Reporting Last Modified Times and Sizes
 *
 * <pre>{@code
 * 	java FileTreeWalkDemo .
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-44
 * @since 2024/7/8
 */
public class FileTreeWalkDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 1) {
			System.err.println("usage: java FileTreeWalkDemo path");
			return;
		}

		Files.walkFileTree(Paths.get(args[0]), new DoNothingVisitor());
	}

	static class DoNothingVisitor extends SimpleFileVisitor<Path> {

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.printf("preVisitDirectory: %s%n", dir);
			System.out.printf("  lastModifiedTime: %s%n", attrs.lastModifiedTime());
			System.out.printf("  size: %d%n%n", attrs.size());
			return super.preVisitDirectory(dir, attrs);
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.printf("visitFile: %s%n%n", file);
			System.out.printf("  lastModifiedTime: %s%n", attrs.lastModifiedTime());
			System.out.printf("  size: %d%n%n", attrs.size());
			return super.visitFile(file, attrs);
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			System.out.printf("visitFileFailed: %s %s%n%n", file, exc);
			return super.visitFileFailed(file, exc);
		}

		@Override
		public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
			System.out.printf("postVisitDirectory: %s %s%n%n", dir, exc);
			return super.postVisitDirectory(dir, exc);
		}
	}
}
