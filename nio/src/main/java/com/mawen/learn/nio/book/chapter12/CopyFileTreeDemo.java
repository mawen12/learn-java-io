package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;

/**
 * Copying a File Tree
 *
 * <pre>{@code
 * 	java CopyFileTreeDemo s t
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-45
 * @since 2024/7/8
 */
public class CopyFileTreeDemo {

	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java CopyFileTreeDemo source target");
			return;
		}

		Path source = Paths.get(args[0]);
		Path target = Paths.get(args[1]);

		if (!Files.exists(source)) {
			System.err.printf("%s source path doesn't exists%n", source);
			return;
		}

		if (!Files.isDirectory(source)) { // Is source a non-directory?
			if (Files.exists(target)) {
				if (Files.isDirectory(target)) {
					target = target.resolve(source.getFileName());
				}
			}

			try {
				Files.copy(source, target, StandardCopyOption.REPLACE_EXISTING);
			}
			catch (IOException e) {
				System.err.printf("I/O error: %s%n", e.getMessage());
			}
			return;
		}

		if (Files.exists(target) && !Files.isDirectory(target)) { // Is target an existing file?
			System.err.printf("%s is not a directory%n", target);
			return;
		}

		EnumSet<FileVisitOption> options = EnumSet.of(FileVisitOption.FOLLOW_LINKS);
		CopyVisitor copier = new CopyVisitor(source, target);
		Files.walkFileTree(source, options, Integer.MAX_VALUE, copier);
	}

	static class CopyVisitor extends SimpleFileVisitor<Path> {
		private Path fromPath;
		private Path toPath;

		private StandardCopyOption copyOption = StandardCopyOption.REPLACE_EXISTING;

		public CopyVisitor(Path fromPath, Path toPath) {
			this.fromPath = fromPath;
			this.toPath = toPath;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			System.out.println("dir = " + dir);
			System.out.println("fromPath = " + fromPath);
			System.out.println("toPath = " + toPath);
			System.out.println("fromPath.relativize(dir) = " + fromPath.relativize(dir));
			System.out.println("toPath.resolve(fromPath.relativize(dir)) = " + toPath.resolve(fromPath.relativize(dir)));

			Path targetPath = toPath.resolve(fromPath.relativize(dir));
			if (!Files.exists(targetPath)) {
				Files.createDirectory(targetPath);
			}
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			System.out.println("file = " + file);
			System.out.println("fromPath = " + fromPath);
			System.out.println("toPath = " + toPath);
			System.out.println("fromPath.relativize(file) = " + fromPath.relativize(file));
			System.out.println("toPath.resolve(fromPath.relativize(file)) = " + toPath.resolve(fromPath.relativize(file)));

			Files.copy(file, toPath.resolve(fromPath.relativize(file)), copyOption);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
			System.err.println(exc);
			return FileVisitResult.CONTINUE;
		}
	}
}
