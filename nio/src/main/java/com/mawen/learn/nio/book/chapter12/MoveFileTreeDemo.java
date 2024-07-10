package com.mawen.learn.nio.book.chapter12;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Moving a File Tree
 *
 * <pre>{@code
 * 	java MoveFileTreeDemo a a1
 * }</pre>
 *
 * @author <a href="1181963012mw@gmail.com">mawen12</a>
 * @see 12-47
 * @since 2024/7/8
 */
public class MoveFileTreeDemo {
	public static void main(String[] args) throws IOException {
		if (args.length != 2) {
			System.err.println("usage: java MoveFileTreeDemo srcpath destpath");
			return;
		}

		Path src = Paths.get(args[0]);
		Path dst = Paths.get(args[1]);

		Files.walkFileTree(src, new MoveVisitor(src, dst));
	}

	static class MoveVisitor extends SimpleFileVisitor<Path> {
		private Path srcPath;
		private Path dstPath;

		public MoveVisitor(Path srcPath, Path dstPath) {
			this.srcPath = srcPath;
			this.dstPath = dstPath;
		}

		@Override
		public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
			Path targetPath = dstPath.resolve(srcPath.relativize(dir));
			Files.copy(dir, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
			return FileVisitResult.CONTINUE;
		}

		@Override
		public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
			Path targetPath = dstPath.resolve(srcPath.relativize(file));
			Files.move(file, targetPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
			return FileVisitResult.CONTINUE;
		}
	}
}
